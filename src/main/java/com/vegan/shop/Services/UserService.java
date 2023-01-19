package com.vegan.shop.Services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.User;

import com.vegan.shop.Repositories.UserRepository;

@Service
public class UserService extends BaseService<User>{
    private final UserRepository userRepository;
    // private RoleRepository roleRepository;
    // private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    // @Autowired
    // private UserDetailsServiceImplementation userDetailsService;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
    
    //Registrar el User y hacer Hash a su password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }

    // public User register(User user) {
    //     String encodedPassword = userDetailsService.bCryptPasswordEncoder().encode(user.getPassword());
    //     user.setPassword(encodedPassword);
    //     user = super.save(user);
    //     userDetailsService.autoLogin(user); //Punto Clave.
    //     return user;
    // }

    //Encontrar un User por su email
    public User findByEmail(String email){
        Optional<User> optional = userRepository.findByEmail(email);
        return optional.isPresent() ? optional.get() : null;
    }

    //Log In User
    public boolean authenticateUser(String email, String password) {
        
        
        User user = findByEmail(email);
        if (user == null) {
            return false;
        } else {
            if (BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    // public void saveWithUserRole(User user) {
    //     user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    //     user.setRoles(RoleRepository.findByName("ROLE_USER"));
    //     userRepository.save(user);
    // }

    // public void saveUserWithAdminRole(User user) {
    //     user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    //     user.setRoles(RoleRepository.findByName("ROLE_ADMIN"));
    //     userRepository.save(user);
    // }    
}