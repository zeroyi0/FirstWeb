package library.dao.impl;

import library.dao.LoginDao;
import library.model.User;

import java.util.List;

public class LoginDaoImpl extends BaseDao implements LoginDao {

    private LoginDaoImpl(){}
    public static final LoginDao INSTANCE = new LoginDaoImpl();
    public static LoginDao getInstance() {
        return INSTANCE;
    }

    @Override
    public User findByUserName(Object userName) {
        String sql = "SELECT U_ID, U_NAME, U_PWD, U_IDENTITY, U_MAILBOX FROM User WHERE U_NAME = ?";
        List<User> users = executeQuery(User.class ,sql, userName);
        User user = null;
        if (users.size() != 0) {
            user = users.get(0);
        }
        return user;
    }

    @Override
    public User findByUserId(long userId) {
        return findByUserName(userId);
    }

}
