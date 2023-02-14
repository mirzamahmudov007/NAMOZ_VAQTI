package com.example.demo.bot.btn;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegionBtn {
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
        sendMessage.setText("✅ LOTIN yozuviga muvaffaqqiyatli o'zgartirildi.");
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
        sendMessage.setText("✅ Manzilingiz(" + data + ") saqlandi nomoz vaqtini bilish uchun pastdagi tugmalardan foydalaning.");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public SendMessage kunOyKrl(Long chatId, String data) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("⏰ Бугунги номоз вақтлари");
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
        sendMessage.setText("✅ Манзилингиз сақланди нaмоз вақтини билиш учун пастдаги тугмалардан фойдаланинг.");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public SendMessage MuvaffaqiyatliKrl(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("⏰ Бугунги номоз вақтлари");
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
        sendMessage.setText("✅ КРИЛ ёзувига Муваффаққиятли ўзгартирилди.");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public SendMessage MuvaffaqiyatliLt(Long chatId) {
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
        sendMessage.setText("✅ LOTIN yozuviga muvaffaqqiyatli o'zgartirildi.");
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

    public SendMessage kunOyKrl(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        button.setText("Кунлик Тақвим");
        row.add(button);
        button = new KeyboardButton();
        button.setText("Ойлик Тақвим");
        row.add(button);
        rows.add(row);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("Керакли буйруқни киритинг: ");
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }
}
