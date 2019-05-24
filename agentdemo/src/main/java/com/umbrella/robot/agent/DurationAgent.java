package com.umbrella.robot.agent;

import java.lang.instrument.Instrumentation;

/**
 * 执行主类的main函数前会线运行DurationAgent的premain方法，DurationAgent类的全路径在MANIFEST.MF文件中配置好<br>
 * 使用inst.addTransformer(ClassFileTransformer transformer)注册一个转换器，转换器中编写真正的字节码注入逻辑
 * @author Kim
 * @create 2018-01-30 18:03
 **/
public class DurationAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Executing premain.........");
        inst.addTransformer(new DurationTransformer(agentArgs));
    }
}
