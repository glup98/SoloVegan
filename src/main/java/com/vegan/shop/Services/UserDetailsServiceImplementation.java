// package com.vegan.shop.Services;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.vegan.shop.Models.Role;
// import com.vegan.shop.Models.User;
// import com.vegan.shop.Repositories.UserRepository;

// @Service
// public class UserDetailsServiceImplementation implements UserDetailsService {
//     @Autowired
//     private UserRepository userRepository;

//     @Bean //Punto Clave
//     public BCryptPasswordEncoder bCryptPasswordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
//     // 1
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Optional<User> optional = userRepository.findByEmail(username);
//         User user = optional.isPresent() ? optional.get() : null;
        
//         if(user == null) {
//             throw new UsernameNotFoundException("User not found");
//         }

//         return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
//     }

//     // 2
//     private List<GrantedAuthority> getAuthorities(User user){
//         List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//         for(Role role : user.getRoles()) {
//             GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//             authorities.add(grantedAuthority);
//         }
//         return authorities;
//     }
    
//     public void autoLogin(User user) { //Punto Clave para cuando un usuario se registra.
//         UserDetails userDetails = loadUserByUsername(user.getEmail());
//         UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
//         SecurityContextHolder.getContext().setAuthentication(auth);
//     }
    
// }