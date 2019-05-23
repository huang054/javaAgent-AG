package com.umbrella.robot.agent;

import java.lang.instrument.Instrumentation;

/**
 * public static void premain(String agentArgs, Instrumentation inst); //【1】
 * public static void premain(String agentArgs); //【2】
 * 其中，【1】和【2】同时存在时，【1】会优先被执行，而【2】则会被忽略。
 *
 */
public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is an agent.");
        System.out.println("args:" + agentArgs + "\n");
    }
}
