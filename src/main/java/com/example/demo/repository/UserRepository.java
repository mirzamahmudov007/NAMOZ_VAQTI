package com.example.demo.repository;

//import gayratjon.uz.taqvimbot.model.User;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByChatId(Long chatId);

    User findByChatId(Long chatId);

}
