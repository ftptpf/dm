package ru.ftptpf.reflection.model;

public class ReflectionApiExample {

    public static void main(String[] args) {
        User user = new User(25L, "Ivan");
        Class<? extends User> userClass = user.getClass();
        Class<User> userClass1 = User.class;
        System.out.println(userClass == userClass1);
    }
}