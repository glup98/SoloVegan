package com.vegan.shop.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vegan.shop.Models.NutritionalInformation;

@Repository
public interface NutricionalInformationRepository extends BaseRepository<NutritionalInformation> 
{
    @Query("SELECT ni FROM NutritionalInformation ni JOIN ni.products p WHERE p.id = :productId")
    List<NutritionalInformation> findByProductId(@Param("productId") Long productId);
}
