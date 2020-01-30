package deverour.tower.service.impl;

import deverour.tower.domain.User;
import deverour.tower.mapper.LoginMapper;
import deverour.tower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User login(User user) {
        return loginMapper.findUser(user);
    }

    @Override
    public String changepassword(String username, String oldpassword, String newpassword) {
        User newuser = new User();
        User olduser= new User();
        newuser.setUsername(username);
        newuser.setPassword(oldpassword);
        newuser.setNewpassword(newpassword);
        olduser.setUsername(username);
        olduser.setPassword(oldpassword);
        User loginUser = loginMapper.findUser(olduser);
        if(loginUser != null){
            loginMapper.changepassword(newuser);
            return "success";
        }else {
            return "fail";
        }
    }
}
