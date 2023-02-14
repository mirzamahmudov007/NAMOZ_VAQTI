package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDto userDto;

    public UserService(UserRepository userRepository, UserDto userDto) {
        this.userRepository = userRepository;
        this.userDto = userDto;
    }

    public User checkUserSave(Message message) {
        User user = new User();
        user = userRepository.findByChatId(message.getChatId());
        if (userRepository.existsByChatId(message.getChatId())) {
            return user;
        } else {
            return saveUser(message);
        }
    }


    public User saveUser(Message message) {
        User user = new User();
        user.setChatId(message.getChatId());
        user.setLastName(message.getFrom().getLastName());
        user.setUsername(message.getFrom().getUserName());
        user.setFirstName(message.getFrom().getFirstName());
        user.setStep(null);
        user.setUserLang(null);
        return userRepository.save(user);
    }

    public Boolean existByChatId(Long chatId) {
        return userRepository.existsByChatId(chatId);
    }

    public void saveStep(String step, Long id) {
        User user = userRepository.getById(id);
        if (id != null) {
            user.setId(id);
            user.setStep(step);
        }
    }

    public User update(User user, Long id, String step) {
        User user1 = new User();
        user1.setId(user.getId());
        user1.setChatId(user.getChatId());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());
        user1.setFirstName(user.getFirstName());
        user1.setUserRegion(user.getUserRegion());
        user1.setStep(step);
        user1.setData(user.getData());
        return userRepository.save(user1);
    }

    public User updateData(User user, Long id, String data) {
        User user1 = new User();
        user1.setId(user.getId());
        user1.setChatId(user.getChatId());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());
        user1.setFirstName(user.getFirstName());
        user1.setUserRegion(user.getUserRegion());
        user1.setStep(user.getStep());
        user1.setData(data);
        return userRepository.save(user1);
    }

    public User updateLatLang(User user, Long id, String lat, String lang) {
        User user1 = new User();
        user1.setId(id);
        user1.setChatId(user.getChatId());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());
        user1.setFirstName(user.getFirstName());
        user1.setStep(user.getStep());
        user1.setData(user.getData());
        user1.setLat(lat);
        user1.setLang(lang);
        return userRepository.save(user1);
    }

    public User getPhoneNumberCodeById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("IN findById - no role found by id: {}", id);
            return null;
        }
        log.info("IN findById - role: {} found by id: {}", user, id);
        return user;
    }

    public User findByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }
}
