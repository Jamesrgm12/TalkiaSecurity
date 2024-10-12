package com.upc.talkia_proyect.servicesImpl;

import com.upc.talkia_proyect.dtos.queries.ShowContentHistoryDTO;
import com.upc.talkia_proyect.dtos.queries.ShowHistorialContentDTO;
import com.upc.talkia_proyect.entities.Content;
import com.upc.talkia_proyect.entities.User;
import com.upc.talkia_proyect.entities.UserContent;
import com.upc.talkia_proyect.repositories.ContentRepository;
import com.upc.talkia_proyect.repositories.UserContentRepository;
import com.upc.talkia_proyect.repositories.UserRepository;
import com.upc.talkia_proyect.services.UserContentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserContentServiceImpl implements UserContentService {

    @Autowired
    UserContentRepository userContentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContentRepository contentRepository;

    @Transactional
    @Override
    public Integer insertUserContent(Integer contentId, Integer UserId) {

        User user = userRepository.findById(UserId).get();
        Content content = contentRepository.findById(contentId).get();
        UserContent userContent = new UserContent();

        userContent.setUser(user);
        userContent.setContent(content);
        userContentRepository.save(userContent);
        return 1;
    }

    @Override
    public List<ShowHistorialContentDTO> listUserContent(){
        return userContentRepository.listUserContent();
    }

    @Override
    public List<ShowContentHistoryDTO> ListUserContentByUser(int userId) {
        return userContentRepository.ListUserContentByUser(userId);
    }
}
