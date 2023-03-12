package com.example.demo.service;

import com.example.demo.model.Members;
import com.example.demo.repository.MembersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Slf4j
public class About_BotService {

    private final MembersRepo membersRepository;

    public About_BotService(MembersRepo membersRepository) {
        this.membersRepository = membersRepository;
    }


    public void memebers(Long id) {
        Members members=new Members();
        members.setId(1L);
        members.setMembersNumber(id);
        membersRepository.save(members);
    }

    public Long getMembers(){
        Members members = membersRepository.getById(1L);
        return members.getId();
    }
}
