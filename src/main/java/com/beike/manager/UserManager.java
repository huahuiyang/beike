package com.beike.manager;

import com.beike.dao.User;
import com.beike.dao.UserDAO;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by huahui.yang on 3/22/15.
 */
public class UserManager {
    private static final Logger LOGGER = Logger.getLogger(UserManager.class);

    private static UserDAO userDAO = new UserDAO();

    public Integer addUser(User user) {
        userDAO.save(user);
        return user.getId();
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
//        System.out.println(userManager.addUser(new User("hello","password")));
        System.out.println(userManager.getAllUsers());
    }
}
