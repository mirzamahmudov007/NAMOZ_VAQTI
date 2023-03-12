package com.example.demo.bot.btn;

import com.example.demo.bot.api.PrayTime;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegionBtn {

    private final PrayTime prayTime;

    public RegionBtn(PrayTime prayTime) {
        this.prayTime = prayTime;
    }

    public SendMessage getRegionBtn(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("Tilni o'zgartirish");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("O'zingiz joylashgan hududni tanlang(Hozircha Faqat Toshkent):");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public SendMessage getRegionBtnKrl(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("ЛОТИН ёзувига ўтиш\uD83D\uDD04");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("✅");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public SendMessage kunOy(Long chatId, String data) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("⏰ Bugungi nomoz vaqtlari");
        row.add(button);
        rows.add(row);

        button = new KeyboardButton();
        row = new KeyboardRow();
        button.setText("KRIL yozuviga o'tish \uD83D\uDD04");
        row.add(button);
        button = new KeyboardButton();
        button.setText("Hududni o'zgartirish ⚙️");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("✅ Manzilingiz( " + data + " ) saqlandi namoz vaqtini bilish uchun pastdagi tugmalardan foydalaning.");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public SendMessage buttongaVatBnJonatish(Long chatId, double lat, double lang, String text, String data) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("⏰ Bugungi namoz vaqtlari");
        row.add(button);
        rows.add(row);

        button = new KeyboardButton();
        row = new KeyboardRow();
        button.setText("KRIL yozuviga o'tish \uD83D\uDD04");
        row.add(button);
        button = new KeyboardButton();
        button.setText("Hududni o'zgartirish ⚙️");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText(prayTime.mainLT(lat, lang, text, data));
        sendMessage.setChatId(chatId.toString());
//        sendMessage.setReplyMarkup(new ReplyKeyboardRemove());
        return sendMessage;
    }

    public SendMessage buttongaVatBnJonatishKrl(Long chatId, double lat, double lang, String text, String data) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("⏰ Бугунги нaмоз вақтлари");
        row.add(button);
        rows.add(row);

        button = new KeyboardButton();
        row = new KeyboardRow();
        button.setText("ЛОТИН ёзувига ўтиш\uD83D\uDD04");
        row.add(button);
        button = new KeyboardButton();
        button.setText("Ҳудудни ўзгартириш⚙️");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText(prayTime.mainK(lat, lang, text, data));
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }


    public SendMessage MuvaffaqiyatliKrl(Long chatId, String data) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("⏰ Бугунги нaмоз вақтлари");
        row.add(button);
        rows.add(row);

        button = new KeyboardButton();
        row = new KeyboardRow();
        button.setText("ЛОТИН ёзувига ўтиш\uD83D\uDD04");
        row.add(button);
        button = new KeyboardButton();
        button.setText("Ҳудудни ўзгартириш⚙️");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("✅");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public SendMessage MuvaffaqiyatliLt(Long chatId, String data) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("⏰ Bugungi namoz vaqtlari");
        row.add(button);
        rows.add(row);

        button = new KeyboardButton();
        row = new KeyboardRow();
        button.setText("KRIL yozuviga o'tish \uD83D\uDD04");
        row.add(button);
        button = new KeyboardButton();
        button.setText("Hududni o'zgartirish ⚙️");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("✅");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public SendMessage namozVaqtiReply(Long chatId, String data) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("⏰ Bugungi nomoz vaqtlari");
        row.add(button);
        rows.add(row);

        button = new KeyboardButton();
        row = new KeyboardRow();
        button.setText("KRIL yozuviga o'tish\uD83D\uDD04");
        row.add(button);
        button = new KeyboardButton();
        button.setText("Hududni o'zgartirish ⚙️");
        row.add(button);
        rows.add(row);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("  s" + data);
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }


    public SendMessage lTkll(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("ЛОТИН ёзувига ўтиш\uD83D\uDD04");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("✅");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }


    public SendMessage kllLt(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("KRIL yozuviga o'tish \uD83D\uDD04");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("✅");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }


    public SendMessage locationBTN(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setRequestLocation(true);
        button.setText("\uD83D\uDCCD lakatsiyani jo'natish");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("Ботга жойлашувингизни (лакация) ташланг.");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public SendMessage locationBTNK(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setRequestLocation(true);
        button.setText("\uD83D\uDCCD лакацияни жўнатиш");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("Botga joylashuvingizni (lakatsiya) tashlang.");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }


}
