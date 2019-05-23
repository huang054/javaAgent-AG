package com.umbrella.robot.agent;

import com.rong.kim.common.Lion;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

public class AgentMainTraceAgent {
    public static void agentmain(String agentArgs, Instrumentation inst)
            throws UnmodifiableClassException {
        System.out.println("Agent Main called");
        System.out.println("agentArgs : " + agentArgs);
        inst.addTransformer(new ClassFileTransformer() {

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer)
                    throws IllegalClassFormatException {

                if ("com/rong/kim/common/Lion".equals(className)) {
                    System.out.println("Instrumenting......");
                    try {
                        ClassPool classPool = ClassPool.getDefault();
                        CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(
                                classfileBuffer));
                        CtMethod[] methods = ctClass.getDeclaredMethods();
                        for (CtMethod method : methods) {
                            method.addLocalVariable("startTime", CtClass.longType);
                            method.insertBefore("startTime = System.nanoTime();");
                            method.insertAfter("System.out.println(\"Execution Duration "
                                    + "(nano sec): \"+ (System.nanoTime() - startTime) );");
                        }
                        classfileBuffer = ctClass.toBytecode();
                        ctClass.detach();
                        System.out.println("Instrumentation complete.");
                    } catch (Throwable ex) {
                        System.out.println("Exception: " + ex);
                        ex.printStackTrace();
                    }
                }
                return classfileBuffer;
            }
        }, true);
        inst.retransformClasses(Lion.class);
    }
}