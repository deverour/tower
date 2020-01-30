package deverour.tower.service;

import deverour.tower.domain.User;

public interface UserService {

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);

    public String changepassword(String username, String oldpassword, String newpassword);

}
