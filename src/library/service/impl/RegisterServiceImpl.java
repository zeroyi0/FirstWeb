package library.service.impl;


import library.dao.LoginDao;
import library.dao.RegisterDao;
import library.dao.impl.LoginDaoImpl;
import library.dao.impl.RegisterDaoImpl;
import library.model.User;
import library.model.status.RegisterStatus;
import library.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
    //单例
    private RegisterServiceImpl() {}
    private static final RegisterService INTANCE = new RegisterServiceImpl();
    public static RegisterService getIntance() {
        return INTANCE;
    }

    //进入下一层
    RegisterDao registerDao = new RegisterDaoImpl();

    @Override
    /**
     * 注册
     * @param user
     */
    public int register(User user) {
        //DAO层
        LoginDao loginDao = LoginDaoImpl.getInstance();
        User findUser1 = loginDao.findByUserName(user.getUserName());
        //User findUser2 = loginDao.findByUserId(user.getId());
        // 没有该用户，则注册该用户
       if (findUser1 == null) {
           // 设置权限
           String uIdentity = user.getIdentity();
           if ("教师".equals(uIdentity)) {
               user.setIdentity(User.ADMIN);
           }else {
               user.setIdentity(User.GUEST);
           }

           //保存注册信息,并返回结果
           if (registerDao.save(user)) {
               return RegisterStatus.REGISTER_SUCCESS;
           }else {
               return RegisterStatus.ID_HAS_EXIST;
           }
       }
        return RegisterStatus.NAME_HAS_EXIST;
    }
}
