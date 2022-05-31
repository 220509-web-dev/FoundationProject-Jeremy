package com.test;

import com.dao.UserDAO;
import com.dao.UserDaoPostgres;
import com.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class UserTests {

    static UserDAO userDAO = new UserDaoPostgres();

    @Test
    public void checkNull(int id) throws IllegalAccessException {
        User user = userDAO.getUserById(id);
        for (Field f : user.getClass().getDeclaredFields())
            if (f.get(this) != null)
                System.out.println("BAD BOY!");
        System.out.println("GOOD BOY!");
    }

}
