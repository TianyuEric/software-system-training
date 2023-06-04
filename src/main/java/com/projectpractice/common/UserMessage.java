package com.projectpractice.common;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.common
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-03  19:34
 * @Description: ThreadLocal配置
 * @Version: 1.0
 */
public class UserMessage {
    private static final ThreadLocal<String> local = new ThreadLocal<>();

    private UserMessage() {
    }

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
