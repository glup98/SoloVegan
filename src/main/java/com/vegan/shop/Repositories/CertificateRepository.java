package com.vegan.shop.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.User;

@Repository
public interface CertificateRepository extends BaseRepository<Certificate>
{
    @Query("SELECT c FROM Certificate c WHERE :user NOT MEMBER OF c.users")
    List<Certificate> findCertificatesNotBelongingToUser(@Param("user") User user);

    // devuelva un booleano true si encuentra a un "User" especifico dentro de un "Certificate" especifico
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Certificate c JOIN c.users u WHERE c.id = :certificateId AND u.id = :userId")
    boolean userBelongsToCertificate(@Param("certificateId") Long certificateId, @Param("userId") Long userId);
}
