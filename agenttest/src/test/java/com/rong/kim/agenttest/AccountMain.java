package com.rong.kim.agenttest;

import com.rong.kim.common.Lion;

public class AccountMain {
    public static void main(String[] args) throws InterruptedException {
        for (;;) {
            new Lion().runLion();
            Thread.sleep(5000);
        }

    }
}
