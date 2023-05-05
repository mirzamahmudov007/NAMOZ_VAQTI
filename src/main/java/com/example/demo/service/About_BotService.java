package com.example.demo.service;

import com.example.demo.model.Members;
import com.example.demo.model.User;
import com.example.demo.repository.MembersRepo;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
@Slf4j
public class About_BotService {

    private final MembersRepo membersRepository;
    private final UserRepository userRepository;

    public About_BotService(MembersRepo membersRepository, UserRepository userRepository) {
        this.membersRepository = membersRepository;
        this.userRepository = userRepository;
    }


    public void memebers(Long id) {
        Members members = new Members();
        members.setId(1L);
        members.setMembersNumber(id);
        membersRepository.save(members);
    }

    public String newUser() {
        List<User> users = userRepository.findAll();
        String text = String.valueOf(users.size());
        return text;
    }

    public String getView(int i) {
        List<User> users = userRepository.findAll();
      return "@"+users.get(i).getUsername();
    }
    public int getViewcount() {
        List<User> users = userRepository.findAll();
        return users.size();
    }
}