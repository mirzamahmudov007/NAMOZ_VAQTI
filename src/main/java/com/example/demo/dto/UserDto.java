package com.example.demo.dto;

//import gayratjon.uz.taqvimbot.model.User;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDto {

    private Long id;
    private Long chatId;

    private String username;

    private String firstName;

    private String lastName;

    private String step;

    public User convertToUser(Message message) {
        User user = new User();
        user.setChatId(message.getChatId());
        user.setLastName(message.getFrom().getLastName());
        user.setUsername(message.getFrom().getUserName());
        user.setFirstName(message.getFrom().getFirstName());
        return user;
    }

    public User convertTOUser(User user) {
        if (id != null)
            user.setId(id);
        user.setStep(step);
        user.setChatId(chatId);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

}
