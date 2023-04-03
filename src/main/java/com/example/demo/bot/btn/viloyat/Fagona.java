package com.example.demo.bot.btn.viloyat;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Fagona {
    public SendMessage namanganTumans(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Farg‘ona shahar");
        inlineKeyboardButton1.setCallbackData("Farg‘ona shahar");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Uchko‘prik");
        inlineKeyboardButton1.setCallbackData("Uchko‘prik");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("So‘x");
        inlineKeyboardButton1.setCallbackData("So‘x");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Bag‘dod");
        inlineKeyboardButton1.setCallbackData("Bag‘dod");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Yozyovon");
        inlineKeyboardButton1.setCallbackData("Yozyovon");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Quva");
        inlineKeyboardButton1.setCallbackData("Quva");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Qo‘qon");
        inlineKeyboardButton1.setCallbackData("Qo‘qon");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Beshariq");
        inlineKeyboardButton1.setCallbackData("Beshariq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Quvasoy");
        inlineKeyboardButton1.setCallbackData("Quvasoy");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Farg‘ona");
        inlineKeyboardButton1.setCallbackData("Farg‘ona");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Toshloq");
        inlineKeyboardButton1.setCallbackData("Toshloq ");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("O‘zbekiston");
        inlineKeyboardButton1.setCallbackData("O‘zbekiston");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Qo‘shtepa");
        inlineKeyboardButton1.setCallbackData("Qo‘shtepa");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Marg‘ilon");
        inlineKeyboardButton1.setCallbackData("Marg‘ilon");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Buvayda");
        inlineKeyboardButton1.setCallbackData("Buvayda");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Dang‘ara");
        inlineKeyboardButton1.setCallbackData("Dang‘ara");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Oltiariq");
        inlineKeyboardButton1.setCallbackData("Oltiariq");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Rishton");
        inlineKeyboardButton1.setCallbackData("Rishton");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Furqat");
        inlineKeyboardButton1.setCallbackData("Furqat");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 =new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\t");
        inlineKeyboardButton1.setCallbackData("werxw");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        keyboardButtonsRow1=new ArrayList<>();
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("joylashuv bo'yicha ");
        inlineKeyboardButton1.setCallbackData("location");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText("➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖\n" +
                "     O'zingiz joylashgan Tumanni tanlang:\n" +
                "➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    } public SendMessage namanganTumansK(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Фарғона шаҳар ");
        inlineKeyboardButton1.setCallbackData("Farg‘ona shahar");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Учкўприк ");
        inlineKeyboardButton1.setCallbackData("Uchko‘prik");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Сўх ");
        inlineKeyboardButton1.setCallbackData("So‘x");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Бағдод ");
        inlineKeyboardButton1.setCallbackData("Bag‘dod");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Ёзёвон");
        inlineKeyboardButton1.setCallbackData("Yozyovon");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Қува ");
        inlineKeyboardButton1.setCallbackData("Quva");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Қўқон ");
        inlineKeyboardButton1.setCallbackData("Qo‘qon");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Бешариқ   ");
        inlineKeyboardButton1.setCallbackData("Beshariq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Қувасой");
        inlineKeyboardButton1.setCallbackData("Quvasoy");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Фарғона ");
        inlineKeyboardButton1.setCallbackData("Farg‘ona");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Тошлоқ");
        inlineKeyboardButton1.setCallbackData("Toshloq");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Ўзбекистон ");
        inlineKeyboardButton1.setCallbackData("O‘zbekiston");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Қўштепа ");
        inlineKeyboardButton1.setCallbackData("Qo‘shtepa");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Марғилон ");
        inlineKeyboardButton1.setCallbackData("Marg‘ilon");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Бувайда ");
        inlineKeyboardButton1.setCallbackData("Buvayda");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Данғара ");
        inlineKeyboardButton1.setCallbackData("Dang‘ara");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Олтиариқ ");
        inlineKeyboardButton1.setCallbackData("Oltiariq");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Риштон");
        inlineKeyboardButton1.setCallbackData("Rishton");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Фурқат ");
        inlineKeyboardButton1.setCallbackData("Furqat");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 =new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\t");
        inlineKeyboardButton1.setCallbackData("werxw");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        keyboardButtonsRow1=new ArrayList<>();
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("жойлашув бўйича ");
        inlineKeyboardButton1.setCallbackData("location");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText("➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖\n" +
                "Ўзингиз жойлашган Туманни танланг:\n" +
                "➖➖➖➖➖➖➖➖➖➖➖➖➖➖➖");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
