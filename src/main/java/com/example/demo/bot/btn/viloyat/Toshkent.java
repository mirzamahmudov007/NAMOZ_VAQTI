package com.example.demo.bot.btn.viloyat;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Toshkent {
    public SendMessage namanganTumans(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Toshknet shahar");
        inlineKeyboardButton1.setCallbackData("ToshknetSH");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Chilonzor ");
        inlineKeyboardButton1.setCallbackData("chilonzor");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Quyi Chirchiq");
        inlineKeyboardButton1.setCallbackData("quyiC");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Bo'stonliq ");
        inlineKeyboardButton1.setCallbackData("bostonliq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Yangiyo'l ");
        inlineKeyboardButton1.setCallbackData("yangiyol");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Zangiota ");
        inlineKeyboardButton1.setCallbackData("zangota");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Ohangaron ");
        inlineKeyboardButton1.setCallbackData("ohangaron");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Oqqo'rg'on");
        inlineKeyboardButton1.setCallbackData("oqqq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Parkent ");
        inlineKeyboardButton1.setCallbackData("ppp");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Piskent ");
        inlineKeyboardButton1.setCallbackData("piskent");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("O'rta Chirchiq");
        inlineKeyboardButton1.setCallbackData("ortachirchiq");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Yuqori Chirchiq");
        inlineKeyboardButton1.setCallbackData("yuqorichirchiq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Bekobod");
        inlineKeyboardButton1.setCallbackData("bekobod");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\t");
        inlineKeyboardButton1.setCallbackData("a");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        keyboardButtonsRow1 = new ArrayList<>();
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
        inlineKeyboardButton1.setText("Тошкнет шаҳар");
        inlineKeyboardButton1.setCallbackData("ToshknetSH");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Чилонзор ");
        inlineKeyboardButton1.setCallbackData("chilonzor");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Қуйи Чирчиқ");
        inlineKeyboardButton1.setCallbackData("quyiC");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Бўстонлиқ ");
        inlineKeyboardButton1.setCallbackData("bostonliq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Янгийўл ");
        inlineKeyboardButton1.setCallbackData("yangiyol");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Зангиота ");
        inlineKeyboardButton1.setCallbackData("zangota");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Оҳангарон ");
        inlineKeyboardButton1.setCallbackData("ohangaron");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Оққўрғон ");
        inlineKeyboardButton1.setCallbackData("oqqq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(" Паркент");
        inlineKeyboardButton1.setCallbackData("ppp");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Пискент ");
        inlineKeyboardButton1.setCallbackData("piskent");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Ўрта Чирчиқ");
        inlineKeyboardButton1.setCallbackData("ortachirchiq");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Юқори Чирчиқ");
        inlineKeyboardButton1.setCallbackData("yuqorichirchiq");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Бекобод ");
        inlineKeyboardButton1.setCallbackData("bekobod");
        keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\t");
        inlineKeyboardButton1.setCallbackData("a");
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        rowList.add(keyboardButtonsRow1);


        keyboardButtonsRow1 = new ArrayList<>();
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
