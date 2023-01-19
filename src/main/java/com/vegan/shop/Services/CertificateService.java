package com.vegan.shop.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.User;
import com.vegan.shop.Repositories.CertificateRepository;

@Service
public class CertificateService extends BaseService<Certificate> {

    private final CertificateRepository certificateRepository;

    public CertificateService(CertificateRepository certificateRepository) 
    {
        super(certificateRepository);
        this.certificateRepository = certificateRepository;
    }
    
    public List<Certificate> preferencesThatUserHasNotChosen(User user)
    {
    return certificateRepository.findCertificatesNotBelongingToUser(user);
    }

    //Devuelve el repositorio que implementa una query.
    public boolean userBelongsToCertificate(Long certificateId, Long userId)
    {
        return certificateRepository.userBelongsToCertificate(certificateId, userId);
    }
}
