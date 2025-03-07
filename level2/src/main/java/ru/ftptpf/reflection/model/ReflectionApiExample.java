package ru.ftptpf.reflection.model;

import java.lang.reflect.*;

public class ReflectionApiExample {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        User user = new User(25L, "Ivan");
/*        Class<? extends User> userClass = user.getClass();
        Class<User> userClass1 = User.class;
        System.out.println(userClass == userClass1);*/
        testConstructor();
        testField(user);
        testMethod(user);
    }

    private static void testConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<User> constructor = User.class.getConstructor(Long.class, String.class);
        User petr = constructor.newInstance(5L, "Petr");
        System.out.println(petr);
    }

    private static void testField(User user) throws IllegalAccessException {
        Field[] declaredFields = User.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object value = declaredField.get(user);
            System.out.println(value);
            System.out.println(declaredField.getModifiers());
            System.out.println(Modifier.isPrivate(declaredField.getModifiers()));
        }
    }

    private static void testMethod(User user) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = user.getClass().getDeclaredMethod("getName");
        System.out.println(method.invoke(user));
    }
}