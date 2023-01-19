package com.vegan.shop.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.User;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository <User> 
{
    Optional<User> findByEmail(String email);
}