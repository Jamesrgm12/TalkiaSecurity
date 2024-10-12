package com.upc.talkia_proyect.servicesImpl;

import com.upc.talkia_proyect.entities.Level;
import com.upc.talkia_proyect.repositories.LevelRepository;
import com.upc.talkia_proyect.services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelRepository levelRepository;
    @Override
    public List<Level> listLevels() {
        return levelRepository.findAll();
    }
}
