package com.company.invoicing.services;

import com.company.invoicing.models.Company;
import com.company.invoicing.models.Role;
import com.company.invoicing.models.User;
import com.company.invoicing.repositoriums.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 21.12.2016..
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(password);
        userRepository.saveAndFlush(user);
    }

    public User findOne(long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(long id) {
        userRepository.delete(id);
    }

    public void update(User user) {
        User oldUser=userRepository.findOne(user.getUser_id());
        if(!oldUser.getPassword().equals(user.getPassword())) {
            String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(password);
        }
        userRepository.saveAndFlush(user);
    }

    public User authenticate(User user){
        List<User> users=userRepository.findAll();
        if(users!=null) {
            for (User u : users) {
                if(u.getUsername().equals(user.getUsername())) {
                    if (u.getPassword().equals(user.getPassword())) {
                        return u;
                    }
                }
            }
        }

        return null;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void change_password(String username, String password){
        User user=userRepository.findByUsername(username);
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(password);
        userRepository.save(user);
    }
}
