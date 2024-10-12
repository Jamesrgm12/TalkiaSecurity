package com.upc.talkia_proyect.servicesImpl;

import com.upc.talkia_proyect.entities.Suscription;
import com.upc.talkia_proyect.repositories.SuscriptionRepository;
import com.upc.talkia_proyect.services.SuscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SuscriptionServiceImpl implements SuscriptionService {

    @Autowired
    private SuscriptionRepository suscriptionRepository;

    @Override
    public List<Suscription> listSuscriptions() {
        return suscriptionRepository.findAll();
    }

    @Override
    public Suscription getSuscriptionById(int susId) {
        return suscriptionRepository.getSuscriptionById(susId);
    }
}
