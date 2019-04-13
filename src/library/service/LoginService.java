package library.service;

import library.model.User;

public interface LoginService {

    public int login(User user);

    public User getUserInfo(String userName);

}
