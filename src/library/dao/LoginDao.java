package library.dao;

import library.model.User;

public interface LoginDao {

    public User findByUserName(Object userName);

    public User findByUserId (long userId);

}
