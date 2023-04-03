package com.example.demo.bot.btn.viloyat;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Xorazm {
    public SendMessage namanganTumans(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Bog'ot");
        inlineKeyboardButton1.setCallbackData("Bog'ot");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Gurlan");
        inlineKeyboardButton1.setCallbackData("Gurlan");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Shovot");
        inlineKeyboardButton1.setCallbackData("Shovot");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Qo'shko'pir");
        inlineKeyboardButton1.setCallbackData("Qo'shko'pir");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Yangibozor");
        inlineKeyboardButton1.setCallbackData("Yangibozor");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Urganch shahri");
        inlineKeyboardButton1.setCallbackData("Urganch shahri");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Urganch tumani");
        inlineKeyboardButton1.setCallbackData("Urganch tumani");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Xazorasp");
        inlineKeyboardButton1.setCallbackData("Xazorasp");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Xiva");
        inlineKeyboardButton1.setCallbackData("Xiva");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Xonqa");
        inlineKeyboardButton1.setCallbackData("Xonqa");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Yangiariq");
        inlineKeyboardButton1.setCallbackData("Yangiariq");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\t ");
        inlineKeyboardButton1.setCallbackData("xw");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        keyboardButtonsRow1 = new ArrayList<>();
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("joylashuv orqali");
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
    }
    public SendMessage namanganTumansK(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Боғот ");
        inlineKeyboardButton1.setCallbackData("Bog'ot");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Гурлан ");
        inlineKeyboardButton1.setCallbackData("Gurlan");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Шовот  ");
        inlineKeyboardButton1.setCallbackData("Shovot");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Қўшкўпир  ");
        inlineKeyboardButton1.setCallbackData("Qo'shko'pir");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Янгибозор  ");
        inlineKeyboardButton1.setCallbackData("Yangibozor");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Урганч шаҳри  ");
        inlineKeyboardButton1.setCallbackData("Urganch shahri");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Урганч тумани ");
        inlineKeyboardButton1.setCallbackData("Urganch tumani");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Хазорасп  ");
        inlineKeyboardButton1.setCallbackData("Xazorasp");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Хива  ");
        inlineKeyboardButton1.setCallbackData("Xiva");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Хонқа  ");
        inlineKeyboardButton1.setCallbackData("Xonqa");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Янгиариқ  ");
        inlineKeyboardButton1.setCallbackData("Yangiariq");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\t ");
        inlineKeyboardButton1.setCallbackData("xw");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        keyboardButtonsRow1 = new ArrayList<>();
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("жойлашув бўйича");
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
