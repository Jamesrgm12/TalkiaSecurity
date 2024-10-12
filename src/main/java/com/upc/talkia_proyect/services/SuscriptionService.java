package com.upc.talkia_proyect.services;


import com.upc.talkia_proyect.entities.Suscription;

import java.util.List;
public interface SuscriptionService {
    public List<Suscription> listSuscriptions();
    public Suscription getSuscriptionById(int susId);

}
