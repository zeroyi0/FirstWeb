package library.service.impl;

import library.dao.LoginDao;
import library.dao.impl.LoginDaoImpl;
import library.model.User;
import library.model.status.LoginStatus;
import library.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private LoginServiceImpl(){}
    public static final LoginService INSTANCE = new LoginServiceImpl();
    public static LoginService getInstance() {
        return INSTANCE;
    }

    private LoginDao loginDao = LoginDaoImpl.getInstance();

    @Override
    public int login(User user) {
        User dbUser = loginDao.findByUserName(user.getUserName());
        if (dbUser == null) {
            return LoginStatus.USER_NOT_EXISTS;
        }
        if (!dbUser.getUserPwd().equals(user.getUserPwd())) {
            return LoginStatus.USER_PWD_WRONG;
        }
        return LoginStatus.LOGIN_SUCCESS;
    }

    @Override
    public User getUserInfo(String userName) {
        return loginDao.findByUserName(userName);
    }
}
