package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.entities.Suscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuscriptionRepository extends JpaRepository<Suscription, Integer> {
    public Suscription getSuscriptionById(int susId);


}
