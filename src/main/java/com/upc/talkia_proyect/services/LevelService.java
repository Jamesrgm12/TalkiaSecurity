package com.upc.talkia_proyect.services;

import com.upc.talkia_proyect.entities.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LevelService {
    public List<Level> listLevels();
}
