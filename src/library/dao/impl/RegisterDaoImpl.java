package library.dao.impl;

import library.dao.RegisterDao;
import library.model.User;

public class RegisterDaoImpl extends BaseDao implements RegisterDao {

    @Override
    public boolean save(User user) {
        int row = super.add(user);
        if (row > 0) {
            return true;
        }
        return false;
    }
}
