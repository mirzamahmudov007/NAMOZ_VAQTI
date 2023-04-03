package com.example.demo.bot.btn.viloyat;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Namangan {

    public SendMessage namanganTumans(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Namangan shahar");
        inlineKeyboardButton1.setCallbackData("Namangan");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Chust");
        inlineKeyboardButton1.setCallbackData("Chust");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Kosonsoy");
        inlineKeyboardButton1.setCallbackData("Kosonsoy");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Mingbuloq");
        inlineKeyboardButton1.setCallbackData("Mingbuloq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Chortoq");
        inlineKeyboardButton1.setCallbackData("Chortoq");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Norin");
        inlineKeyboardButton1.setCallbackData("Norin");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Pop");
        inlineKeyboardButton1.setCallbackData("Pop");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("To'raqo'rg'on");
        inlineKeyboardButton1.setCallbackData("To'raqo'rg'on");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Uchqo'rg'on");
        inlineKeyboardButton1.setCallbackData("Uchqo'rg'on");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Uychi");
        inlineKeyboardButton1.setCallbackData("uychi");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Yangiqo'rgon");
        inlineKeyboardButton1.setCallbackData("yangiqorgon");
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
    }  public SendMessage namanganTumansK(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Наманган шаҳар ");
        inlineKeyboardButton1.setCallbackData("Namangan");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Чуст");
        inlineKeyboardButton1.setCallbackData("Chust");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(" Косонсой");
        inlineKeyboardButton1.setCallbackData("Kosonsoy");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Мингбулоқ ");
        inlineKeyboardButton1.setCallbackData("Mingbuloq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Чортоқ  ");
        inlineKeyboardButton1.setCallbackData("Chortoq");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(" Норин ");
        inlineKeyboardButton1.setCallbackData("Norin");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Поп ");
        inlineKeyboardButton1.setCallbackData("Pop");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(" Тўрақўрғон");
        inlineKeyboardButton1.setCallbackData("To'raqo'rg'on");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Учқўрғон ");
        inlineKeyboardButton1.setCallbackData("Uchqo'rg'on");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Уйчи");
        inlineKeyboardButton1.setCallbackData("uychi");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Янгиқўргон ");
        inlineKeyboardButton1.setCallbackData("yangiqorgon");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\t ");
        inlineKeyboardButton1.setCallbackData("xw");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        keyboardButtonsRow1 = new ArrayList<>();
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("жойлашув орқали ");
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
    