package library;


import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface AutoWired {

}

class Dog{

    void liuliu() {
        System.out.println("有人在遛我");
    }
}
class Cat{}
class User{

    @AutoWired
    Dog dog;

    Cat cat;

    void liuDog() {
        dog.liuliu();
    }
}

class Spring {

    private Map map = new LinkedHashMap();

    void createInstance(Class clazz) throws Exception {

        // 要被创建的实例
        Object instance = clazz.newInstance();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {

            AutoWired at = field.getAnnotation(AutoWired.class);
            if (at == null) {
                continue;
            }
            // 获取类型名
            Class ftype = field.getType();
            String ftypeName = ftype.getSimpleName();
            // 根据类型名从工厂中拿到对应的对象
            Object val = map.get(ftypeName);
            field.setAccessible(true);
            // 将从工厂中拿到的对象注入到要被创建实例中
            field.set(instance, val);
        }

        map.put(clazz.getSimpleName(), instance);
    }

    Object getInstance(String ftypeName) {
        return map.get(ftypeName);
    }


    void createDog() {
        Dog dog = new Dog();
        map.put("dog", dog);
    }

    void createUser() {
        User user = new User();

        Dog dog = (Dog) map.get("dog");
        user.dog = dog;

        map.put("user", user);
    }

    User getUser() {
        return (User) map.get("user");
    }

}

public class Test2 {

    public static void main(String[] args) throws Exception {
//        User zhangsan = new User();
//
//        Dog dog = new Dog();
//        zhangsan.dog = dog;

        Spring spring = new Spring();
        spring.createInstance(Dog.class);
        spring.createInstance(User.class);

        User user = (User) spring.getInstance(User.class.getSimpleName());
        user.liuDog();

        jingjing();

    }
    public static void jingjing() {
        System.out.println("hello");
    }
}
