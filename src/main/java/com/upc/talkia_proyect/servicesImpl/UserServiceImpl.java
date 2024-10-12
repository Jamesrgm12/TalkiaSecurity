package com.upc.talkia_proyect.servicesImpl;

import com.upc.talkia_proyect.dtos.queries.ShowSuscriptionDetailsDTO;
import com.upc.talkia_proyect.entities.Level;
import com.upc.talkia_proyect.entities.User;
import com.upc.talkia_proyect.repositories.LevelRepository;
import com.upc.talkia_proyect.repositories.UserRepository;
import com.upc.talkia_proyect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }


    @Override
    @Transactional
    public boolean existsUser(String userName, String password){
        for(User user: listUsers()) {
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    @Override
    @Transactional
    public User updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        }
        return null;
    }


    @Override
    public User getUserById(int userId){
        return userRepository.getUserById(userId);
    }

    @Override
    public List<User> listUsersByRegisterDate(LocalDate startDate, LocalDate endDate){
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atStartOfDay();

        return userRepository.listUsersByRegisterDate(startDateTime, endDateTime);
    }

    @Override
    public List<User> listUsersByStatus(String status){
        return userRepository.listUsersByStatus(status);
    }

    @Override
    public  List<User> getUserByUserNameContains(String username){
        return userRepository.getUserByUserNameContains(username);
    }

    @Override
    public ShowSuscriptionDetailsDTO getCurrentSuscription(int userId){
        return userRepository.getCurrentSuscription(userId);
    }

    @Override
    public void updateLevelUser(int userId){
        User user = userRepository.getUserById(userId);
        double userPoints = user.getTotalPoints();
        switch (user.getLevel().getId()){
            case 1:
                if(userPoints >200){
                    Level level = levelRepository.findById(2);
                    user.setLevel(level);
                }
                break;
            case 2:
                if(userPoints >500){
                    Level level = levelRepository.findById(3);
                    user.setLevel(level);
                }
                break;
            default:
                break;
        }

    }
}
