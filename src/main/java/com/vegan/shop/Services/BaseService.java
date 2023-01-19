package com.vegan.shop.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vegan.shop.Repositories.BaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class BaseService <T>
{
    private final BaseRepository <T> baseRepository;

    public T findById(Long id)
    {
        
        Optional <T> optional = baseRepository.findById(id);
        if(optional.isPresent())
        {
            return optional.get();
        }
        else 
        {
            return null;
        }
    }

    //listar modelos

    public List <T> findAll()
    {
        return baseRepository.findAll();
    }

    
    //guardar
    public T save(T object)
    {
        return baseRepository.save(object);
    }
}
