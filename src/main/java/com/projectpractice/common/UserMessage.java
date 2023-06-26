package com.projectpractice.common;

//ThreadLocal配置
public class UserMessage {
    private static final ThreadLocal<String> local = new ThreadLocal<>();


    public static String getUsername() {
            return local.get();
    }
    public static void setUsername(String username) {
        local.set(username);
    }

    public static void remove(){
        local.remove();
    }

}
