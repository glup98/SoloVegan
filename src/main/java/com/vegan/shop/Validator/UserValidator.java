package com.vegan.shop.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.vegan.shop.Models.User;

@Component
public class UserValidator implements Validator {
@Override
public boolean supports(Class<?> clazz) {
    return User.class.equals(clazz);
}

@Override
public void validate(Object target, Errors errors) {
    User user = (User) target;
    if(validatePassword(user) == false){
        errors.rejectValue("password", "notMatch");
    }
    if(areOnlyLetters(user.getFirstName()) == false){
        errors.rejectValue("name", "onlyLetters");
    }
    if(areOnlyLetters(user.getLastName()) == false){
        errors.rejectValue("lastName", "onlyLetters");
    }
}

private boolean validatePassword(User user){
    return user.getPassword().equals(user.getPasswordConfirmation());
}

private boolean areOnlyLetters(String cad){
    return cad.matches("[a-zA-Z]+ *[a-zA-Z]*");
}
}