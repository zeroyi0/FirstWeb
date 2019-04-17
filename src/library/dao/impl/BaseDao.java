
package library.dao.impl;

import library.annotation.Table;
import library.annotation.TableField;
import library.util.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BaseDao {

    public static final String URL = "jdbc:mysql://biandandan.top:3306/library?useUnicode=true&amp&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";

    public static final String USER_NAME = "library";

    public static final String PASS_WORD = "1zhixiaoxiong";

    private Connection conn;

    /**
     * 连接数据库
     */
    private boolean connect() {
        boolean isSuccess = false;
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 创建连接
            conn = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
            // 连接成功
            isSuccess = true;
        } catch (ClassNotFoundException e) {
            System.out.println("驱动无法加载");
        } catch (SQLException e) {
            System.out.println("连接数据库失败");
        }
        return isSuccess;
    }
    /**
     * 关闭数据库
     */
    private boolean close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭连接失败");
            return false;
        }
        return true;
    }
    /**
     * 增加
     * @param obj
     * @return
     */
    public int add(Object obj) {
        // 连接数据库
        connect();
        //对象所在的类
        Class objClazz = obj.getClass();

        Field[] fields = objClazz.getDeclaredFields();

        StringBuffer sb = new StringBuffer("INSERT INTO ");

        //获取这个类的注解
        Table table = (Table)objClazz.getAnnotation(Table.class);
        String tableName = null;
        if (table != null) {
            tableName = table.value();
        }else {
            tableName = objClazz.getSimpleName();
        }
        sb.append(tableName);

        StringBuffer sbFields = new StringBuffer("( ");
        StringBuffer sbValues = new StringBuffer(" VALUES( ");

        try {
            // 获取语柄
            Statement stmt = conn.createStatement();
            int count = 0;
            for (Field field: fields) {
                // 判断属性是否为静态
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                if (isStatic) {
                    continue;
                }
                String fname = field.getName();
                // 属性注解
                TableField fieldAnnotation = field.getAnnotation(TableField.class);
                if (fieldAnnotation != null) {
                    fname = fieldAnnotation.value();
                }
                // sbFields
                sbFields.append(fname + ", ");
                // 设置属性可被访问
                field.setAccessible(true);
                Object fvalue = field.get(obj);
                if (fvalue instanceof String) {
                    fvalue = "'" + field.get(obj) + "'";
                }
                // sbValues
                sbValues.append(fvalue + ", ");
            }
            sbFields.deleteCharAt(sbFields.length() - 2);
            sbFields.append(")");
            sbValues.deleteCharAt(sbValues.length() - 2);
            sbValues.append(")");
            sb = sb.append(sbFields.append(sbValues));

            return stmt.executeUpdate(sb.toString());

        } catch (Exception e) {
            System.out.println("获取语柄失败！");
        } finally {
            close();
        }
        return -1;
    }

    /**
     *  删除一行
     * @param obj
     * @param wrapper 删除条件 (例：name = "")
     * @return
     * @throws Exception
     */
    public int deleteDate(Object obj, String wrapper) throws Exception {
        if (StringUtils.isEmpty(wrapper)) {
            throw new Exception("删除条件不能为空！");
        }
        connect();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            Class clazz = obj.getClass();
            //DELECT FROM tableName WHERE id = ?
            StringBuffer sb = new StringBuffer("DELETE FROM ");
            Table tableName = (Table) clazz.getAnnotation(Table.class);
            if (tableName != null) {
                sb.append(tableName.value());
            }else {
                //类名
                sb.append(clazz.getSimpleName());
            }
            sb.append(" WHERE " + wrapper);
            return stmt.executeUpdate(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return -1;
    }
    /**
     * 修改数据
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql, Object ...params) {
        connect();
        PreparedStatement pstmt = null;
        try {
            // 创建语柄
            pstmt = conn.prepareStatement(sql);
            // 装填参数
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            // 执行
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return -1;
    }
    /**
     * 执行sql查询， 返回一个list
     * @param clazz 返回的对象类型
     * @param sql sql语句
     */
    public List executeQuery(Class clazz, String sql, Object ...params) {
        // 返回的查询结果
        List queryResult = new LinkedList();
        // 连接数据库
        connect();
        PreparedStatement stat = null;
        try {
            // 获取语柄
            stat = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stat.setObject(i + 1, params[i]);         // 装填参数
            }
            // 执行查询
            ResultSet rs = stat.executeQuery();
            // 获取所有属性
            Field[] fields = clazz.getDeclaredFields();
            //查询结果  //SELECT bookId,bookName FROM book WHERE author = '小蠢熊';
             while (rs.next()) { //  一行结果
                // 获取对象（无参构造方法）
                Object obj = clazz.newInstance();          //暂时Book对象
                // 每列的值
                for (Field field : fields) {
                    String fname = field.getName();
                    // 属性是否有注解
                    TableField tbFieldAnnotation = field.getAnnotation(TableField.class);
                    if (tbFieldAnnotation != null) {
                        fname = tbFieldAnnotation.value();
                    }
                    // 开启访问权限
                    field.setAccessible(true);
                    if (getColumnValue(fname, rs) || !isStatic(field)) {
                        String ftypeName = field.getType().getSimpleName();
                        if ("Long".equals(ftypeName) || "long".equals(ftypeName)) {
                            field.set(obj, rs.getLong(fname));
                        } else if ("Integer".equals(ftypeName) || "int".equals(ftypeName)) {
                            field.set(obj, rs.getInt(fname));
                        } else {
                            field.set(obj, rs.getObject(fname));
                        }
                    }
                }
                queryResult.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("执行失败：" + e.getLocalizedMessage());
        } catch (IllegalAccessException e) {
            System.out.println("对象参数有误");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return queryResult;
    }
    /**
     *  利用异常返回结果
     */
    public boolean getColumnValue(String colName, ResultSet rs) {
        try {
            rs.getObject(colName);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    /**
     * 判断该属性是否为静态属性
     */
    public boolean isStatic(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isStatic(modifiers);
    }
}

