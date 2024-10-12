package com.upc.talkia_proyect.services;

import com.upc.talkia_proyect.dtos.queries.ShowContentHistoryDTO;
import com.upc.talkia_proyect.dtos.queries.ShowHistorialContentDTO;

import java.util.List;

public interface UserContentService {

    public Integer insertUserContent(Integer contentId, Integer userId);
    public List<ShowHistorialContentDTO> listUserContent();
    public List<ShowContentHistoryDTO> ListUserContentByUser(int userId);
}
