package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final About_BotService botServicel;
    private final UserRepository userRepository;
    private final UserDto userDto;

    public UserService(About_BotService botServicel, UserRepository userRepository, UserDto userDto) {
        this.botServicel = botServicel;
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
        user = userRepository.save(user);
        botServicel.memebers(user.getId());
        return user;
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
        user1 = user;
        user1.setStep(step);
        return userRepository.save(user1);
    }

    public User updateData(User user, Long id, String data) {
        User user1 = new User();
        user1 = user;
        user1.setData(data);
        return userRepository.save(user1);
    }

    public User updateOxirgiIsh(User user, Long id, String data) {
        User user1 = new User();
        user1 = user;
        user1.setOxirgiIsh(data);
        return userRepository.save(user1);
    }

    public User bugungiNVMsgID(User user, Long id, Integer msgID) {
        User user1 = new User();
        user1 = user;
        user1.setBugungiNamozVatiMsgID(msgID);
        return userRepository.save(user1);
    }

    public User updateDeleteMessage(User user, Long id, Message message) {
        User user1 = new User();
        user1 = user;
        message.setMessageId(message.getMessageId() + 1);
        user1.setMessage(message);
        return userRepository.save(user1);
    }


    public User updateLatLang(User user, Long id, double lat, double lang) {
        User user1 = new User();
        user1 = user;
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

    public String newUser() {
        List<User> users = userRepository.findAll();
        Date date1 = new Date();
        List<String> stringList = new ArrayList<>();
        String text = null;
        int i = 1;
        for (User u :
                users) {
            if (u.getDate().getYear() == date1.getYear()
                    && u.getDate().getMonth() == date1.getMonth()
                    && u.getDate().getDay() == date1.getDay()) {
                text = i + ". @" + u.getUsername() + "\n";
                stringList.add(text);
                i++;
            }
        }
        for (int j = 0; j < stringList.size(); j++) {
            text = stringList.get(j);
        }
        return text;
    }

    public User findByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }
}
