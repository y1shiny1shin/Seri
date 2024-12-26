package com.ezgame.ctf;

public class Payload {
    static {
        try {
            Runtime.getRuntime().exec("code");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
