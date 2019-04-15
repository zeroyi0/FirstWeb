//package library;
//
//import library.dao.LoginDao;
//import library.dao.impl.LoginDaoImpl;
//import library.model.User;
//
//import java.lang.reflect.Field;
//
//public class Test {
//
//    public static void testField() throws Exception {
//        User user = new User();
//        user.setId(123l);
//        user.setUserName("zhangsan");
//
//        Class userClazz = user.getClass();
//        Field[] fields = userClazz.getDeclaredFields();
//        for (Field field :
//                fields) {
//            field.setAccessible(true);
//            System.out.println(field.get(user));
//        }
//    }
//
//    public static void testJson() {
//        User user = new User(1l, "zhangsan", "123", User.ADMIN, "linf.z@qq.com");
//    }
//
//    public static void testMysql() {
//        LoginDao loginDao = LoginDaoImpl.getInstance();
//        User user = loginDao.findByUserName("xiaoxiong");
//        System.out.println(user);
//    }
//
//    public static void main(String[] args) throws Exception {
////        testJson();
////        testMysql();
//    }
//
//}
