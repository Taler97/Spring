package org.example;

public class Test {
    public static void main(String[] args) throws Exception {
        // 尝试直接加载类
        Class<?> clazz = Class.forName("org.example.TeacherService");
        System.out.println("类加载成功: " + clazz);

        // 检查文件位置
        String path = clazz.getResource("TeacherService.class").toString();
        System.out.println("类文件位置: " + path);
    }
}