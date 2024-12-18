package com.example.demo.bot.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PrayTime {

    private static final String API_URL = "http://api.aladhan.com/v1/timings?latitude=%s&longitude=%s&method=2";

    public String main(double latitude, double longitude, String text) {
        return getPrayerTimes(latitude, longitude, text, false);
    }

    public String mainLT(double latitude, double longitude, String text, String data) {
        return getPrayerTimes(latitude, longitude, text, false);
    }

    public String mainK(double latitude, double longitude, String text, String data) {
        return getPrayerTimes(latitude, longitude, text, true);
    }

    private String getPrayerTimes(double latitude, double longitude, String text, boolean isKiril) {
        // API URL manzilini yaratish
        String url = String.format(API_URL, latitude, longitude);

        // RestTemplate yordamida API dan ma'lumot olish
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        try {
            // JSON ma'lumotlarini to'g'ri formatlash
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode dataNode = root.path("data");
            JsonNode timings = dataNode.path("timings");

            // Namoz vaqtlarini olish
            String fajr = timings.path("Fajr").asText();
            String sunrise = timings.path("Sunrise").asText();
            String dhuhr = timings.path("Dhuhr").asText();
            String asr = timings.path("Asr").asText();
            String maghrib = timings.path("Maghrib").asText();
            String isha = timings.path("Isha").asText();

            // Joriy vaqtni olish
            String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Namoz vaqtlari va boshqa ma'lumotlarni shakllantirish
            String timeFormat = isKiril ? "Тонг: %s\n\n" : "Tong: %s\n\n";

            // Namoz vaqtlarini o'zgartirish
            String time = buildPrayerTimeMessage(text, formattedDate, fajr, sunrise, dhuhr, asr, maghrib, isha, isKiril);

            return time;
        } catch (Exception e) {
            e.printStackTrace();
            return "Namoz vaqtlarini olishda xatolik yuz berdi.";
        }
    }

    private String buildPrayerTimeMessage(String text, String formattedDate, String fajr, String sunrise, String dhuhr, String asr, String maghrib, String isha, boolean isKiril) {
        String message =
                "\uD83C\uDF10 ️ *" + text + "*\n" + // Location header
                        "\uD83D\uDCC5️  *" + formattedDate + "*\n" + // Date header
                        "━━━━━━━━━━━━━━━━━━━━━\n" + // Separator line
                        formatTimeRow("🌅 Tong", fajr, isKiril) +
                        formatTimeRow("\uD83C\uDF04 Quyosh", sunrise, isKiril) +
                        formatTimeRow("\uD83C\uDFDE Peshin", dhuhr, isKiril) +
                        formatTimeRow("\uD83C\uDF07 Asr", asr, isKiril) +
                        formatTimeRow("\uD83C\uDF05 Shom", maghrib, isKiril) +
                        formatTimeRow("\uD83C\uDF0C Xufton", isha, isKiril) +
                        "\n\uD83D\uDCAC @HidoyatBot_bot"; // Footer with bot reference

        return message;
    }

    private String formatTimeRow(String label, String time, boolean isKiril) {
        if (isKiril) {
            label = label.replace("Tong", "Тонг")
                    .replace("Quyosh", "Қуёш")
                    .replace("Peshin", "Пешин")
                    .replace("Asr", "Аср")
                    .replace("Shom", "Шом")
                    .replace("Xufton", "Хуфтон");
        }
        return String.format("%s: *%s*\n", label, time); // Bold formatting for times
    }
}
