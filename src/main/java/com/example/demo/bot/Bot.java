package com.example.demo.bot;

import com.example.demo.bot.Step.UserStep;
import com.example.demo.bot.api.PrayTime;
import com.example.demo.bot.btn.InlineBtn;
import com.example.demo.bot.btn.RegionBtn;
import com.example.demo.bot.btn.viloyat.*;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@PropertySource("classpath:telegram.properties")
@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;
    private final InlineBtn btn;

    private final PrayTime prayTime;
    private final Navoiy navoiy;
    private final Xorazm xorazm;
    private final UserService userService;
    private final Samarqand samarqand;
    private final RegionBtn regionBtn;
    private final RestTemplate restTemplate;
    private final Namangan namangan;

    private final Buxoro buxoro;
    private final Jizzah jizzah;
    private final Fagona fagona;
    private final Toshkent toshkent;
    private final Qashqadaryo qashqadaryo;
    private final Andijon andijon;

    public Bot(@Lazy InlineBtn btn, PrayTime prayTime, Navoiy navoiy, Xorazm xorazm, @Lazy UserService userService, Samarqand samarqand, RegionBtn regionBtn, @Lazy RestTemplate restTemplate, @Lazy Namangan namangan, Buxoro buxoro, Jizzah jizzah, @Lazy Fagona fagona, @Lazy Toshkent toshkent, Qashqadaryo qashqadaryo, @Lazy Andijon andijon) {
        this.btn = btn;
        this.prayTime = prayTime;
        this.navoiy = navoiy;
        this.xorazm = xorazm;
        this.userService = userService;
        this.samarqand = samarqand;
        this.regionBtn = regionBtn;
        this.restTemplate = restTemplate;
        this.namangan = namangan;
        this.buxoro = buxoro;
        this.jizzah = jizzah;
        this.fagona = fagona;
        this.toshkent = toshkent;
        this.qashqadaryo = qashqadaryo;
        this.andijon = andijon;
    }

//    public Bot(InlineBtn btn, UserService userService) {
//        this.btn = btn;
//        this.userService = userService;
//    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            User user = userService.findByChatId(message.getChatId());
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/start")) {
                    if (!userService.existByChatId(message.getChatId())) {
                        User userSave = userService.saveUser(message);
                        user = userSave;
                    }
                    sendInlineBtn(user.getChatId()); // inline btn
                    user = userService.update(user, user.getId(), UserStep.select_lang); // step saved
                } else if (text.equals("Hududni o'zgartirish ⚙️")) {
                    getRegionInline(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_lt);
                } else if (text.equals("KRIL yozuviga o'tish \uD83D\uDD04")) {
                    if (user.getStep().equals(UserStep.select_lang_lt)) {
                        sendInlineKeyBoardRegionK(user.getChatId());
                        userService.update(user, user.getId(), UserStep.select_lang_krl);
                    } else {
                        String lt1 = user.getStep().substring(0, 11);
                        String lt2 = user.getStep().substring(15, user.getStep().length());
                        MuvaffaqiyatliKrl(user.getChatId());
                        userService.update(user, user.getId(), lt1 + "_krl_" + lt2);
                    }
                } else if (text.equals("ЛОТИН ёзувига ўтиш\uD83D\uDD04")) {
                    if (user.getStep().equals(UserStep.select_lang_krl)) {
                        getRegionInline(user.getChatId());
                        userService.update(user, user.getId(), UserStep.select_lang_lt);
                    } else {
                        String krl1 = user.getStep().substring(0, 11);
                        String krl2 = user.getStep().substring(14, user.getStep().length());
                        MuvaffaqiyatliLt(user.getChatId());
                        System.out.println(user.getStep());
                        userService.update(user, user.getId(), krl1 + "_lt_" + krl2);
                    }
                } else if (text.equals("Ҳудудни ўзгартириш⚙️")) {
                    sendInlineKeyBoardRegionK(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl);
                } else if (text.equals("⏰ Bugungi nomoz vaqtlari")) {
                    userService.updateData(user, user.getId(), user.getData());
                    if (user.getData().equals("Chust")) { // 41.00615643758215, 71.23771796120751
                        sendMessage(user.getChatId().toString(), setApi("40.999931", "71.236137", "Namangan, Chust"));
                    } else if (user.getData().equals("Namangan")) { // Namangan shahar
                        sendMessage(user.getChatId().toString(), setApi("41.007162", "71.643712", "Namangan, Namangan shahar"));
                    } else if (user.getData().equals("Kosonsoy")) { // Kosonsoy shahar
                        sendMessage(user.getChatId().toString(), setApi("41.19608642817571", "71.52652970907708", "Namangan, Kosonsoy"));
                    } else if (user.getData().equals("Chortoq")) { // Chortoq shahar 41.06368431981446, 71.8230565658223
                        sendMessage(user.getChatId().toString(), setApi("41.06368431981446", "71.8230565658223", "Namangan, Chortoq"));
                    } else if (user.getData().equals("Norin")) { // Namangan shahar 40.940509509996495, 72.00654567901067
                        sendMessage(user.getChatId().toString(), setApi("40.940509509996495", "72.00654567901067", "Namangan, Norin"));
                    } else if (user.getData().equals("Mingbuloq")) { // Namangan shahar 40.78917645308457, 71.38875951462421
                        sendMessage(user.getChatId().toString(), setApi("40.78917645308457", "71.38875951462421", "Namangan, Mingbuloq"));
                    } else if (user.getData().equals("Pop")) { // Namangan shahar 41.0364117324324, 70.75487013756214
                        sendMessage(user.getChatId().toString(), setApi("41.0364117324324", "70.75487013756214", "Namangan, Pop"));
                    } else if (user.getData().equals("To'raqo'rg'on")) { // Namangan shahar 40.99316007632018, 71.51626227933346
                        sendMessage(user.getChatId().toString(), setApi("40.99316007632018", "71.51626227933346", "Namangan, To'raqo'rg'on"));
                    } else if (user.getData().equals("Uchqo'rg'on")) { // Namangan shahar 41.12068842248576, 72.08711447153537
                        sendMessage(user.getChatId().toString(), setApi("41.12068842248576", "72.08711447153537", "Namangan, Uchqo'rg'on"));
                    } else if (user.getData().equals("uychi")) { // Namangan shahar 41.06721589284562, 71.92292974977094
                        sendMessage(user.getChatId().toString(), setApi("71.92292974977094", "71.92292974977094", "Namangan, Uychi"));
                    } else if (user.getData().equals("yangiqorgon")) { // Namangan shahar 41.198968289144275, 71.71703917988339
                        sendMessage(user.getChatId().toString(), setApi("41.198968289144275", "71.71703917988339", "Namangan, Yangoqo'rg'on"));
                    } else if (user.getData().equals("ToshknetSH")) { // 41.31862648082324, 69.26185247247427
                        sendMessage(user.getChatId().toString(), setApi("41.31862648082324", "69.26185247247427", "Toshkent ,Toshkent Shahar"));
                    } else if (user.getData().equals("chilonzor")) { // 41.25966068034852, 69.17981683014095
                        sendMessage(user.getChatId().toString(), setApi("41.25966068034852", "69.17981683014095", "Toshkent ,Chilonzor"));
                    } else if (user.getData().equals("quyiC")) { // 41.324249737732416, 69.27336159543128
                        sendMessage(user.getChatId().toString(), setApi("41.324249737732416", "69.27336159543128", "Toshkent ,Quyi Chirchiq"));
                    } else if (user.getData().equals("bostonliq")) { // 41.29962474317497, 69.24013777030345
                        sendMessage(user.getChatId().toString(), setApi("41.29962474317497", "69.24013777030345", "Toshkent ,Bo'stonliq"));
                    } else if (user.getData().equals("yangiyol")) { // 41.106601409336605, 69.00099564918969
                        sendMessage(user.getChatId().toString(), setApi("41.106601409336605", "69.00099564918969", "Toshkent ,Yangiyo'l"));
                    } else if (user.getData().equals("zangota")) { // 41.19119250475624, 69.14481581613583
                        sendMessage(user.getChatId().toString(), setApi("41.19119250475624", "69.14481581613583", "Toshkent ,Zangiota"));
                    } else if (user.getData().equals("ohangaron")) { // 40.91797520041142, 69.63525266877876
                        sendMessage(user.getChatId().toString(), setApi("40.91797520041142", "69.63525266877876", "Toshkent ,Ohangaron"));
                    } else if (user.getData().equals("oqqq")) { // 40.88044288436734, 69.04266437650311
                        sendMessage(user.getChatId().toString(), setApi("40.88044288436734", "69.04266437650311", "Toshkent ,Oqqo'rg'on"));
                    } else if (user.getData().equals("ppp")) { // 41.297497902393545, 69.68451728729993
                        sendMessage(user.getChatId().toString(), setApi("41.297497902393545", "69.68451728729993", "Toshkent ,Parkent"));
                    } else if (user.getData().equals("ortachirchiq")) { // 41.20590993388904, 69.27922461238849
                        sendMessage(user.getChatId().toString(), setApi("41.20590993388904", "69.27922461238849", "Toshkent ,O'rta Chirchiq"));
                    } else if (user.getData().equals("yuqorichirchiq")) { // 41.20911424680261, 69.49407488275496
                        sendMessage(user.getChatId().toString(), setApi("41.20911424680261", "69.49407488275496", "Toshkent ,Yuqori Chirchiq"));
                    } else if (user.getData().equals("bekobod")) { // 40.454055968860445, 69.1819780480247
                        sendMessage(user.getChatId().toString(), setApi("40.454055968860445", "69.1819780480247", "Toshkent ,Bekobod"));
                    } else if (user.getData().equals("Farg‘ona shahar")) { //40.37949214005235, 71.78153222147638
                        sendMessage(user.getChatId().toString(), setApi("40.37949214005235", "71.78153222147638", "Farg'ona, Farg‘ona shahar"));
                    } else if (user.getData().equals("Uchko‘prik")) { //40.53267491097347, 71.02892566146784
                        sendMessage(user.getChatId().toString(), setApi("40.53267491097347", "71.02892566146784", "Farg'ona, Uchko‘prik"));
                    } else if (user.getData().equals("So‘x")) { //39.968927659480165, 71.12908694248515
                        sendMessage(user.getChatId().toString(), setApi("39.968927659480165", "71.12908694248515", "Farg'ona, So‘x"));
                    } else if (user.getData().equals("Yozyovon")) { //40.62131813678478, 71.6480687836224
                        sendMessage(user.getChatId().toString(), setApi("40.62131813678478", "71.6480687836224", "Farg'ona, Yozyovon"));
                    } else if (user.getData().equals("Quva")) { //40.524573367810504, 72.06257627873157
                        sendMessage(user.getChatId().toString(), setApi("40.524573367810504", "72.06257627873157", "Farg'ona, Quva"));
                    } else if (user.getData().equals("Qo‘qon")) { //40.524621966621304, 70.93356479162965
                        sendMessage(user.getChatId().toString(), setApi("40.524621966621304", "70.93356479162965", "Farg'ona, Qo‘qon"));
                    } else if (user.getData().equals("Beshariq")) { //40.39690886265036, 70.57359975866977
                        sendMessage(user.getChatId().toString(), setApi("40.39690886265036", "70.57359975866977", "Farg'ona, Beshariq"));
                    } else if (user.getData().equals("Quvasoy")) { //
                        sendMessage(user.getChatId().toString(), setApi("40.30222", "71.97444", "Farg'ona, Quvasoy"));
                    } else if (user.getData().equals("Farg‘ona")) { //40.320714145540286, 71.70209565835899
                        sendMessage(user.getChatId().toString(), setApi("40.320714145540286", "71.70209565835899", "Farg'ona, Farg‘ona"));
                    } else if (user.getData().equals("Toshloq")) { //40.56269866588281, 71.84044642257277
                        sendMessage(user.getChatId().toString(), setApi("40.56269866588281", "71.84044642257277", "Farg'ona, Toshloq"));
                    } else if (user.getData().equals("O‘zbekiston")) { //40.330297796679396, 70.85868054864669
                        sendMessage(user.getChatId().toString(), setApi("40.330297796679396", "70.85868054864669", "Farg'ona, O‘zbekiston"));
                    } else if (user.getData().equals("Qo‘shtepa")) { //40.53692345413236, 71.6415020647065
                        sendMessage(user.getChatId().toString(), setApi("40.53692345413236", "71.6415020647065", "Farg'ona, Qo‘shtepa"));
                    } else if (user.getData().equals("Marg‘ilon")) { //40.47635685369382, 71.71252435127899
                        sendMessage(user.getChatId().toString(), setApi("40.47635685369382", "71.71252435127899", "Farg'ona, Marg‘ilon"));
                    } else if (user.getData().equals("Buvayda")) { //40.645693110093404, 71.10204505839337
                        sendMessage(user.getChatId().toString(), setApi("40.645693110093404", "71.10204505839337", "Farg'ona, Buvayda"));
                    } else if (user.getData().equals("Dang‘ara")) { //40.58139710281787, 70.91477380795894
                        sendMessage(user.getChatId().toString(), setApi("40.58139710281787", "70.91477380795894", "Farg'ona, Dang‘ara"));
                    } else if (user.getData().equals("Oltiariq")) { //40.39370084174377, 71.48670380912914
                        sendMessage(user.getChatId().toString(), setApi("40.39370084174377", "71.48670380912914", "Farg'ona, Oltiariq"));
                    } else if (user.getData().equals("Rishton")) { //40.36410679785719, 71.27208639645364
                        sendMessage(user.getChatId().toString(), setApi("40.36410679785719", "71.27208639645364", "Farg'ona, Rishton"));
                    } else if (user.getData().equals("Furqat")) { //40.51673390388502, 70.71177286893743
                        sendMessage(user.getChatId().toString(), setApi("40.51673390388502", "70.71177286893743", "Farg'ona, Furqat"));
                    } else if (user.getData().equals("Bag‘dod")) { //40.49387968871484, 71.20366862141532
                        sendMessage(user.getChatId().toString(), setApi("40.49387968871484", "71.20366862141532", "Farg'ona, Bag‘dod"));
                    } else if (user.getData().equals("Andijon shahri")) {//40.7865585435806, 72.31208083702941
                        sendMessage(user.getChatId().toString(), setApi("40.7865585435806", "72.31208083702941", "Andijon, Andijon shahar"));
                    } else if (user.getData().equals("Xonobod")) { //40.79989825348597, 72.98951560333182
                        sendMessage(user.getChatId().toString(), setApi("40.454055968860445", "72.98951560333182", "Andijon, Xonobod"));
                    } else if (user.getData().equals("Asaka")) {// 40.6619355989725, 72.24428848141633
                        sendMessage(user.getChatId().toString(), setApi("40.6619355989725", "72.24428848141633", "Andijon, Asaka"));
                    } else if (user.getData().equals("Baliqchi")) {//40.8681067477643, 71.90901428107483
                        sendMessage(user.getChatId().toString(), setApi("40.8681067477643", "71.90901428107483", "Andijon, Baliqchi"));
                    } else if (user.getData().equals("Bo'z")) { //40.67522368198782, 71.90202999274003
                        sendMessage(user.getChatId().toString(), setApi("40.67522368198782", "71.90202999274003", "Andijon, Bo'z"));
                    } else if (user.getData().equals("Buloqboshi")) { //40.61824097722169, 72.4589485266868
                        sendMessage(user.getChatId().toString(), setApi("40.61824097722169", "72.4589485266868", "Andijon, Buloqboshi"));
                    } else if (user.getData().equals("Izboskan")) { //40.926914438296166, 72.23046114826215
                        sendMessage(user.getChatId().toString(), setApi("40.926914438296166", "72.23046114826215", "Andijon, Izboskan"));
                    } else if (user.getData().equals("Jalolquduq")) {//40.73260859991856, 72.61228519306135
                        sendMessage(user.getChatId().toString(), setApi("40.73260859991856", "72.61228519306135", "Andijon, Jalolquduq"));
                    } else if (user.getData().equals("Marhamat")) { //40.51393917604782, 72.29981218880005
                        sendMessage(user.getChatId().toString(), setApi("40.51393917604782", "72.29981218880005", "Andijon, Marhamat"));
                    } else if (user.getData().equals("Oltinko'l")) {// 40.78257767662204, 72.15630346953014
                        sendMessage(user.getChatId().toString(), setApi("40.78257767662204", "72.15630346953014", "Andijon, Oltinko'l"));
                    } else if (user.getData().equals("Paxtaobod")) { //40.977855409832266, 72.42257164080198
                        sendMessage(user.getChatId().toString(), setApi("40.977855409832266", "72.42257164080198", "Andijon, Paxtaobod"));
                    } else if (user.getData().equals("Qo'rg'ontepa")) { // 40.737458816612616, 72.80489172467401
                        sendMessage(user.getChatId().toString(), setApi("40.737458816612616", "72.80489172467401", "Andijon, Qo'rg'ontepa"));
                    } else if (user.getData().equals("Ulug'nor")) { //40.7812938834895, 71.65829940832162
                        sendMessage(user.getChatId().toString(), setApi("40.7812938834895", "71.65829940832162", "Andijon, Ulug'nor"));
                    } else if (user.getData().equals("Xo'jaobod")) { // 40.644265790848365, 72.59695277036937
                        sendMessage(user.getChatId().toString(), setApi("40.644265790848365", "72.59695277036937", "Andijon, Xo'jaobod"));
                    } else if (user.getData().equals("Shahrixon")) { //40.705658566969085, 72.04954603823948
                        sendMessage(user.getChatId().toString(), setApi("40.705658566969085", "72.04954603823948", "Andijon, Shahrixon"));
                    } else if (user.getData().equals("Samarqand shahri")) {//39.65329327101519, 66.95826234725476
                        sendMessage(user.getChatId().toString(), setApi("39.65329327101519", "66.95826234725476", "Samarqand, Samarqand shahri"));
                    } else if (user.getData().equals("Bulung'ur")) {//39.70791766177822, 67.28142857964608
                        sendMessage(user.getChatId().toString(), setApi("39.70791766177822", "67.28142857964608", "Samarqand, Bulung'ur"));
                    } else if (user.getData().equals("Ishtixon")) {//39.98829349793204, 66.53144028860048
                        sendMessage(user.getChatId().toString(), setApi("39.98829349793204", "66.53144028860048", "Samarqand, Ishtixon"));
                    } else if (user.getData().equals("Jomboy")) {//39.76625238583371, 67.07779535428304
                        sendMessage(user.getChatId().toString(), setApi("39.76625238583371", "67.07779535428304", "Samarqand, Jomboy"));
                    } else if (user.getData().equals("Kattaqo'rg'on tumani")) { // 39.981981100516265, 66.2896029199572
                        sendMessage(user.getChatId().toString(), setApi("39.981981100516265", "66.2896029199572", "Samarqand, Kattaqo'rg'on shahri"));
                    } else if (user.getData().equals("Kattaqo'rg'on shahri")) { // 39.8993149735457, 66.2633149577346
                        sendMessage(user.getChatId().toString(), setApi("39.8993149735457", "66.2633149577346", "Samarqand, Kattaqo'rg'on tumani"));
                    } else if (user.getData().equals("Narpay")) {//39.92524679769102, 65.98627826494945
                        sendMessage(user.getChatId().toString(), setApi("39.92524679769102", "65.98627826494945", "Samarqand, Narpay"));
                    } else if (user.getData().equals("Nurobod")) {//39.57127098105206, 66.02799000748902
                        sendMessage(user.getChatId().toString(), setApi("39.57127098105206", "66.02799000748902", "Samarqand, Nurobod"));
                    } else if (user.getData().equals("Oqdaryo")) {//39.839873784601465, 66.73834694617673
                        sendMessage(user.getChatId().toString(), setApi("39.839873784601465", "66.73834694617673", "Samarqand, Oqdaryo"));
                    } else if (user.getData().equals("Past darg'om")) { // 39.704352292734065, 66.62242053041332
                        sendMessage(user.getChatId().toString(), setApi("39.704352292734065", "66.62242053041332", "Samarqand, Past darg'om"));
                    } else if (user.getData().equals("Urgut")) {//39.447002526177904, 67.17173665735707
                        sendMessage(user.getChatId().toString(), setApi("39.447002526177904", "67.17173665735707", "Samarqand, Urgut"));
                    } else if (user.getData().equals("Paxtachi")) {//39.888515759299004, 65.6089318735053
                        sendMessage(user.getChatId().toString(), setApi("39.888515759299004", "65.6089318735053", "Samarqand, Paxtachi"));
                    } else if (user.getData().equals("Poyariq")) {//40.05104556455984, 66.85419779064077
                        sendMessage(user.getChatId().toString(), setApi("40.05104556455984", "66.85419779064077", "Samarqand, Poyariq"));
                    } else if (user.getData().equals("Samarqand tumani")) { //39.59818617036431, 66.90471762188967
                        sendMessage(user.getChatId().toString(), setApi("39.59818617036431", "66.90471762188967", "Samarqand, Samarqand tumani"));
                    } else if (user.getData().equals("Toyloq")) { //39.56442359774873, 67.14018598905683
                        sendMessage(user.getChatId().toString(), setApi("39.56442359774873", "67.14018598905683", "Samarqand, Toyloq"));
                    } else if (user.getData().equals("Qo'shrabot")) { //40.38655726843597, 66.5100175021684
                        sendMessage(user.getChatId().toString(), setApi("40.38655726843597", "66.5100175021684", "Samarqand, Qo'shrabot"));
                    } else if (user.getData().equals("Jizzax shahri")) {//40.11977007458388, 67.85232343283624
                        sendMessage(user.getChatId().toString(), setApi("40.11977007458388", "67.85232343283624", "Jizzah, Jizzax shahri"));
                    } else if (user.getData().equals("Arnasoy")) { //40.59901232957254, 67.79358090175431
                        sendMessage(user.getChatId().toString(), setApi("40.59901232957254", "67.79358090175431", "Jizzah, Arnasoy"));
                    } else if (user.getData().equals("Baxmal")) {//39.78225382213002, 67.7250016175458
                        sendMessage(user.getChatId().toString(), setApi("39.78225382213002", "67.7250016175458", "Jizzah, Baxmal"));
                    } else if (user.getData().equals("Forish")) {//40.70901562393393, 67.18181520202268
                        sendMessage(user.getChatId().toString(), setApi("40.70901562393393", "67.18181520202268", "Jizzah, Forish"));
                    } else if (user.getData().equals("G'allaorol")) { //40.10205495202923, 67.32846413281408
                        sendMessage(user.getChatId().toString(), setApi("40.10205495202923", "67.32846413281408", "Jizzah, G'allaorol"));
                    } else if (user.getData().equals("Do'stlik")) { //40.58181976770775, 68.04548305263552
                        sendMessage(user.getChatId().toString(), setApi("40.58181976770775", "68.04548305263552", "Jizzah, Do'stlik"));
                    } else if (user.getData().equals("Jizzax tumani")) { //40.11455358141378, 67.82473708142044
                        sendMessage(user.getChatId().toString(), setApi("40.11455358141378", "67.82473708142044", "Jizzah, Jizzax tumani"));
                    } else if (user.getData().equals("Mirzacho'l")) {//40.724412215707, 68.06118184566003
                        sendMessage(user.getChatId().toString(), setApi("40.724412215707", "68.06118184566003", "Jizzah, Mirzacho'l"));
                    } else if (user.getData().equals("Paxtakor")) { //40.35950221136383, 68.00475878330718
                        sendMessage(user.getChatId().toString(), setApi("40.35950221136383", "68.00475878330718", "Jizzah, Paxtakor"));
                    } else if (user.getData().equals("Yangiobod")) {//39.99243484998208, 68.74527110184545
                        sendMessage(user.getChatId().toString(), setApi("39.99243484998208", "68.74527110184545", "Jizzah, Yangiobod"));
                    } else if (user.getData().equals("Zafarobod")) { //40.41330581212753, 67.76451866402623
                        sendMessage(user.getChatId().toString(), setApi("40.41330581212753", "67.76451866402623", "Jizzah, Zafarobod"));
                    } else if (user.getData().equals("Zarband")) { //40.189983986463055, 68.11455808514548
                        sendMessage(user.getChatId().toString(), setApi("40.189983986463055", "68.11455808514548", "Jizzah, Zarband"));
                    } else if (user.getData().equals("Zomin")) { //39.88553183671697, 68.3643022291486
                        sendMessage(user.getChatId().toString(), setApi("39.88553183671697", "68.3643022291486", "Jizzah, Zomin"));
                    } else if (user.getData().equals("Buxoro shahri")) {//39.77672367865153, 64.4210785732946
                        sendMessage(user.getChatId().toString(), setApi("39.77672367865153", "64.4210785732946", "Buxoro, Buxoro shahri"));
                    } else if (user.getData().equals("Buxoro tumani")) { //39.63566090876281, 64.30230294221505
                        sendMessage(user.getChatId().toString(), setApi("39.63566090876281", "64.30230294221505", "Buxoro, Buxoro tumani"));
                    } else if (user.getData().equals("G'ijduvon")) {//40.601906240839995, 64.71053498588797
                        sendMessage(user.getChatId().toString(), setApi("40.601906240839995", "64.71053498588797", "Buxoro, G'ijduvon"));
                    } else if (user.getData().equals("Jondor")) {//39.971219891973426, 63.5714954269693
                        sendMessage(user.getChatId().toString(), setApi("39.971219891973426", "63.5714954269693", "Buxoro, Jondor"));
                    } else if (user.getData().equals("Kogon shahri")) { //39.72355224686954, 64.55132893267286
                        sendMessage(user.getChatId().toString(), setApi("39.72355224686954", "64.55132893267286", "Buxoro, Kogon shahri"));
                    } else if (user.getData().equals("Kogon tumani")) { //39.69809076622412, 64.53702710674044
                        sendMessage(user.getChatId().toString(), setApi("39.69809076622412", "64.53702710674044", "Buxoro, Kogon tumani"));
                    } else if (user.getData().equals("Olot")) { //39.281691000170674, 64.03768298578096
                        sendMessage(user.getChatId().toString(), setApi("39.281691000170674", "64.03768298578096", "Buxoro, Olot"));
                    } else if (user.getData().equals("Peshku")) {//40.73303621917936, 63.21959281775061
                        sendMessage(user.getChatId().toString(), setApi("40.73303621917936", "63.21959281775061", "Buxoro, Peshku"));
                    } else if (user.getData().equals("Qorako'l")) { //39.936970007976, 62.941145138407805
                        sendMessage(user.getChatId().toString(), setApi("39.936970007976", "62.941145138407805", "Buxoro, Qorako'l"));
                    } else if (user.getData().equals("Qorovulbozor")) {//39.46527348718642, 64.67070586334809
                        sendMessage(user.getChatId().toString(), setApi("39.46527348718642", "64.67070586334809", "Buxoro, Qorovulbozor"));
                    } else if (user.getData().equals("Romitan")) { //40.731071302974954, 62.530718928951856
                        sendMessage(user.getChatId().toString(), setApi("40.731071302974954", "62.530718928951856", "Buxoro, Romitan"));
                    } else if (user.getData().equals("Shofirkon")) { // 40.12540773474785, 64.48270729594604
                        sendMessage(user.getChatId().toString(), setApi("40.12540773474785", "64.48270729594604", "Buxoro, Shofirkon"));
                    } else if (user.getData().equals("Vobkent")) { //40.15547517789773, 64.53244365200904
                        sendMessage(user.getChatId().toString(), setApi("40.15547517789773", "64.53244365200904", "Buxoro, Vobkent"));
                    } else if (user.getData().equals("Bog'ot")) {//41.33744153309746, 60.87694940015311
                        sendMessage(user.getChatId().toString(), setApi("41.33744153309746", "60.87694940015311", "Xorazm, Bog'ot"));
                    } else if (user.getData().equals("Gurlan")) {//41.90536583933732, 60.37982747138814
                        sendMessage(user.getChatId().toString(), setApi("41.90536583933732", "60.37982747138814", "Xorazm, Gurlan"));
                    } else if (user.getData().equals("Shovot")) {//41.710201238586826, 60.27012648475865
                        sendMessage(user.getChatId().toString(), setApi("41.710201238586826", "60.27012648475865", "Xorazm, Shovot"));
                    } else if (user.getData().equals("Qo'shko'pir")) {//41.526982196908484, 60.269577660413084
                        sendMessage(user.getChatId().toString(), setApi("41.526982196908484", "60.269577660413084", "Xorazm, Qo'shko'pir"));
                    } else if (user.getData().equals("Yangibozor")) {// 41.75025881608148, 60.54049313655263
                        sendMessage(user.getChatId().toString(), setApi("41.75025881608148", "60.54049313655263", "Xorazm, Yangibozor"));
                    } else if (user.getData().equals("Urganch shahri")) {//41.547992018041974, 60.618264246685676
                        sendMessage(user.getChatId().toString(), setApi("41.547992018041974", "60.618264246685676", "Xorazm, Urganch shahri"));
                    } else if (user.getData().equals("Urganch tumani")) {//41.61625437893366, 60.533593007029786
                        sendMessage(user.getChatId().toString(), setApi("41.61625437893366", "60.533593007029786", "Xorazm, Urganch tumani"));
                    } else if (user.getData().equals("Xazorasp")) {//41.30448683950074, 61.09745692690238
                        sendMessage(user.getChatId().toString(), setApi("41.30448683950074", "61.09745692690238", "Xorazm, Xazorasp"));
                    } else if (user.getData().equals("Xiva")) {//41.39184570391674, 60.34725590732044
                        sendMessage(user.getChatId().toString(), setApi("41.39184570391674", "60.34725590732044", "Xorazm, Xiva"));
                    } else if (user.getData().equals("Xonqa")) {//41.481393767464844, 60.829570881655584
                        sendMessage(user.getChatId().toString(), setApi("41.481393767464844", "60.829570881655584", "Xorazm, Xonqa"));
                    } else if (user.getData().equals("Yangiariq")) {// 41.346206916399666, 60.54804614851794
                        sendMessage(user.getChatId().toString(), setApi("41.346206916399666", "60.54804614851794", "Xorazm, Yangiariq"));
                    } else if (user.getData().equals("Navoiy shahri")) {//40.10166507561083, 65.3575344176788
                        sendMessage(user.getChatId().toString(), setApi("40.10166507561083", "65.3575344176788", "Navoiy, Navoiy shahri"));
                    } else if (user.getData().equals("Karmana")) {//40.14372322026956, 65.35467198347476
                        sendMessage(user.getChatId().toString(), setApi("40.14372322026956", "65.35467198347476", "Navoiy, Karmana"));
                    } else if (user.getData().equals("Konimex")) {// 40.277684998973186, 65.14210146382663
                        sendMessage(user.getChatId().toString(), setApi("40.277684998973186", "65.14210146382663", "Navoiy, Konimex"));
                    } else if (user.getData().equals("Navbahor")) {//40.232621293600054, 65.23269831157204
                        sendMessage(user.getChatId().toString(), setApi("40.232621293600054", "65.23269831157204", "Navoiy, Navbahor"));
                    } else if (user.getData().equals("Nurota")) {//40.61096195334514, 65.93817362794783
                        sendMessage(user.getChatId().toString(), setApi("40.61096195334514", "65.93817362794783", "Navoiy, Nurota"));
                    } else if (user.getData().equals("Qiziltepa")) {//39.8966432527515, 64.76528114807608
                        sendMessage(user.getChatId().toString(), setApi("39.8966432527515", "64.76528114807608", "Navoiy, Qiziltepa"));
                    } else if (user.getData().equals("Tomdi")) {//42.29906147558487, 64.90150727134733
                        sendMessage(user.getChatId().toString(), setApi("42.29906147558487", "64.90150727134733", "Navoiy, Tomdi"));
                    } else if (user.getData().equals("Uchquduq")) {//42.44642264030942, 62.6945789709221
                        sendMessage(user.getChatId().toString(), setApi("42.44642264030942", "62.6945789709221", "Navoiy, Uchquduq"));
                    } else if (user.getData().equals("Xatirchi")) {//40.230069075732, 65.9409253080852
                        sendMessage(user.getChatId().toString(), setApi("40.230069075732", "65.9409253080852", "Navoiy, Xatirchi"));
                    } else if (user.getData().equals("Zarafshon")) {//41.574460147847816, 64.18331570807477
                        sendMessage(user.getChatId().toString(), setApi("41.574460147847816", "64.18331570807477", "Navoiy, Zarafshon"));
                    } else if (user.getData().equals("Qarshi shahri")) {//38.86191354410989, 65.78491933285783
                        sendMessage(user.getChatId().toString(), setApi("38.86191354410989", "65.78491933285783", "Qashqadaryo, Qarshi shahri"));
                    } else if (user.getData().equals("Qarshi tumani")) {//38.779267614177556, 65.75278333536036
                        sendMessage(user.getChatId().toString(), setApi("38.779267614177556", "65.75278333536036", "Qashqadaryo, Qarshi tumani"));
                    } else if (user.getData().equals("Chiroqchi")) {//39.18112111196543, 66.25891591391694
                        sendMessage(user.getChatId().toString(), setApi("39.18112111196543", "66.25891591391694", "Qashqadaryo, Chiroqchi"));
                    } else if (user.getData().equals("Dehqonobod")) {//38.35730703486065, 66.47533103897435
                        sendMessage(user.getChatId().toString(), setApi("38.35730703486065", "66.47533103897435", "Qashqadaryo, Dehqonobod"));
                    } else if (user.getData().equals("G'uzor")) {//38.587010422831746, 66.04489530163235
                        sendMessage(user.getChatId().toString(), setApi("38.587010422831746", "66.04489530163235", "Qashqadaryo, G'uzor"));
                    } else if (user.getData().equals("Kasbi")) {//38.93540618287719, 65.43383657129233
                        sendMessage(user.getChatId().toString(), setApi("38.93540618287719", "65.43383657129233", "Qashqadaryo, KasbiKasbi"));
                    } else if (user.getData().equals("Kitob")) {// 39.2152414606226, 67.03598426370185
                        sendMessage(user.getChatId().toString(), setApi("39.2152414606226", "67.03598426370185", "Qashqadaryo, Kitob"));
                    } else if (user.getData().equals("Koson")) {// 39.16770611275984, 65.77183802497865
                        sendMessage(user.getChatId().toString(), setApi("39.16770611275984", "65.77183802497865", "Qashqadaryo, Koson"));
                    } else if (user.getData().equals("Mirishkor")) {// 38.874194821533756, 64.92111490671182
                        sendMessage(user.getChatId().toString(), setApi("38.874194821533756", "64.92111490671182", "Qashqadaryo, Mirishkor"));
                    } else if (user.getData().equals("Muborak")) {//39.30420398425692, 65.25997593377225
                        sendMessage(user.getChatId().toString(), setApi("39.30420398425692", "65.25997593377225", "Qashqadaryo, Muborak"));
                    } else if (user.getData().equals("Nishon")) {//38.53608390327857, 65.48400423357076
                        sendMessage(user.getChatId().toString(), setApi("38.53608390327857", "65.48400423357076", "Qashqadaryo, Nishon"));
                    } else if (user.getData().equals("Qamashi")) {// 38.75660026948466, 66.60185133772104
                        sendMessage(user.getChatId().toString(), setApi("38.75660026948466", "66.60185133772104", "Qashqadaryo, Qamashi"));
                    } else if (user.getData().equals("Shahrisabz")) {//38.99171024720661, 67.2009187750465
                        sendMessage(user.getChatId().toString(), setApi("38.99171024720661", "67.2009187750465", "Qashqadaryo, Shahrisabz"));
                    } else if (user.getData().equals("Yakkabog'")) {//38.90742740574835, 66.75665915180723
                        sendMessage(user.getChatId().toString(), setApi("38.90742740574835", "66.75665915180723", "Qashqadaryo, Yakkabog'"));
                    } else if (user.getData().equals("location")) {
                        sendMessage(user.getChatId().toString(), setApi(user.getLat(), user.getLang(), "Lakatsiya bo'yicha"));
                        userService.updateData(user, user.getId(), "location");
                    }
                } else if (text.equals("⏰ Бугунги номоз вақтлари")) {
                    if (user.getData().equals("Andijon shahri")) {//40.7865585435806, 72.31208083702941
                        sendMessage(user.getChatId().toString(), setApiK("40.7865585435806", "72.31208083702941", "Aндижон, Aндижон шаҳри"));
                    } else if (user.getData().equals("Xonobod")) { //40.79989825348597, 72.98951560333182
                        sendMessage(user.getChatId().toString(), setApiK("40.454055968860445", "72.98951560333182", "Aндижон, Хонобод"));
                    } else if (user.getData().equals("Asaka")) {// 40.6619355989725, 72.24428848141633
                        sendMessage(user.getChatId().toString(), setApiK("40.6619355989725", "72.24428848141633", "Aндижон, Aсака"));
                    } else if (user.getData().equals("Baliqchi")) {//40.8681067477643, 71.90901428107483
                        sendMessage(user.getChatId().toString(), setApiK("40.8681067477643", "71.90901428107483", "Aндижон, Балиқчи"));
                    } else if (user.getData().equals("Bo'z")) { //40.67522368198782, 71.90202999274003
                        sendMessage(user.getChatId().toString(), setApiK("40.67522368198782", "71.90202999274003", "Aндижон, Бўз"));
                    } else if (user.getData().equals("Buloqboshi")) { //40.61824097722169, 72.4589485266868
                        sendMessage(user.getChatId().toString(), setApiK("40.61824097722169", "72.4589485266868", "Aндижон, Булоқбоши"));
                    } else if (user.getData().equals("Izboskan")) { //40.926914438296166, 72.23046114826215
                        sendMessage(user.getChatId().toString(), setApiK("40.926914438296166", "72.23046114826215", "Aндижон, Избоскан"));
                    } else if (user.getData().equals("Jalolquduq")) {//40.73260859991856, 72.61228519306135
                        sendMessage(user.getChatId().toString(), setApiK("40.73260859991856", "72.61228519306135", "Aндижон, Жалолқудуқ"));
                    } else if (user.getData().equals("Marhamat")) { //40.51393917604782, 72.29981218880005
                        sendMessage(user.getChatId().toString(), setApiK("40.51393917604782", "72.29981218880005", "Aндижон, Марҳамат"));
                    } else if (user.getData().equals("Oltinko'l")) {// 40.78257767662204, 72.15630346953014
                        sendMessage(user.getChatId().toString(), setApiK("40.78257767662204", "72.15630346953014", "Aндижон, Олтинкўл"));
                    } else if (user.getData().equals("Paxtaobod")) { //40.977855409832266, 72.42257164080198
                        sendMessage(user.getChatId().toString(), setApiK("40.977855409832266", "72.42257164080198", "Aндижон, Пахтаобод"));
                    } else if (user.getData().equals("Qo'rg'ontepa")) { // 40.737458816612616, 72.80489172467401
                        sendMessage(user.getChatId().toString(), setApiK("40.737458816612616", "72.80489172467401", "Aндижон, Қўрғонтепа"));
                    } else if (user.getData().equals("Ulug'nor")) { //40.7812938834895, 71.65829940832162
                        sendMessage(user.getChatId().toString(), setApiK("40.7812938834895", "71.65829940832162", "Aндижон, Улуғнор"));
                    } else if (user.getData().equals("Xo'jaobod")) { // 40.644265790848365, 72.59695277036937
                        sendMessage(user.getChatId().toString(), setApiK("40.644265790848365", "72.59695277036937", "Aндижон, Хўжаобод"));
                    } else if (user.getData().equals("Shahrixon")) { //40.705658566969085, 72.04954603823948
                        sendMessage(user.getChatId().toString(), setApiK("40.705658566969085", "72.04954603823948", "Aндижон, Шаҳрихон"));
                    }
                    if (user.getData().equals("ToshknetSH")) { // 41.31862648082324, 69.26185247247427
                        sendMessage(user.getChatId().toString(), setApiK("41.31862648082324", "69.26185247247427", "Тошкент ,Тошкент Шаҳар"));
                    } else if (user.getData().equals("chilonzor")) { // 41.25966068034852, 69.17981683014095
                        sendMessage(user.getChatId().toString(), setApiK("41.25966068034852", "69.17981683014095", "Тошкент ,Чилонзор"));
                    } else if (user.getData().equals("quyiC")) { // 41.324249737732416, 69.27336159543128
                        sendMessage(user.getChatId().toString(), setApiK("41.324249737732416", "69.27336159543128", "Тошкент ,Қуйи Чирчиқ"));
                    } else if (user.getData().equals("bostonliq")) { // 41.29962474317497, 69.24013777030345
                        sendMessage(user.getChatId().toString(), setApiK("41.29962474317497", "69.24013777030345", "Тошкент ,Бўстонлиқ"));
                    } else if (user.getData().equals("yangiyol")) { // 41.106601409336605, 69.00099564918969
                        sendMessage(user.getChatId().toString(), setApiK("41.106601409336605", "69.00099564918969", "Тошкент ,Янгийўл"));
                    } else if (user.getData().equals("zangota")) { // 41.19119250475624, 69.14481581613583
                        sendMessage(user.getChatId().toString(), setApiK("41.19119250475624", "69.14481581613583", "Тошкент ,Зангиота"));
                    } else if (user.getData().equals("ohangaron")) { // 40.91797520041142, 69.63525266877876
                        sendMessage(user.getChatId().toString(), setApiK("40.91797520041142", "69.63525266877876", "Тошкент ,Оҳангарон"));
                    } else if (user.getData().equals("oqqq")) { // 40.88044288436734, 69.04266437650311
                        sendMessage(user.getChatId().toString(), setApiK("40.88044288436734", "69.04266437650311", "Тошкент ,Оққўрғон"));
                    } else if (user.getData().equals("ppp")) { // 41.297497902393545, 69.68451728729993
                        sendMessage(user.getChatId().toString(), setApiK("41.297497902393545", "69.68451728729993", "Тошкент ,Оққўрғон"));
                    } else if (user.getData().equals("ortachirchiq")) { // 41.20590993388904, 69.27922461238849
                        sendMessage(user.getChatId().toString(), setApiK("41.20590993388904", "69.27922461238849", "Тошкент ,Ўрта Чирчиқ"));
                    } else if (user.getData().equals("yuqorichirchiq")) { // 41.20911424680261, 69.49407488275496
                        sendMessage(user.getChatId().toString(), setApiK("41.20911424680261", "69.49407488275496", "Тошкент ,Юқори Чирчиқ"));
                    } else if (user.getData().equals("bekobod")) { // 40.454055968860445, 69.1819780480247
                        sendMessage(user.getChatId().toString(), setApiK("40.454055968860445", "69.1819780480247", "Тошкент ,Бекобод"));
                    }
                    if (user.getData().equals("Samarqand shahri")) {//39.65329327101519, 66.95826234725476
                        sendMessage(user.getChatId().toString(), setApiK("39.65329327101519", "66.95826234725476", "Самарқанд, Самарқанд шаҳри"));
                    } else if (user.getData().equals("Bulung'ur")) {//39.70791766177822, 67.28142857964608
                        sendMessage(user.getChatId().toString(), setApiK("39.70791766177822", "67.28142857964608", "Самарқанд, Булунғур"));
                    } else if (user.getData().equals("Ishtixon")) {//39.98829349793204, 66.53144028860048
                        sendMessage(user.getChatId().toString(), setApiK("39.98829349793204", "66.53144028860048", "Самарқанд, Иштихон"));
                    } else if (user.getData().equals("Jomboy")) {//39.76625238583371, 67.07779535428304
                        sendMessage(user.getChatId().toString(), setApiK("39.76625238583371", "67.07779535428304", "Самарқанд, Жомбой"));
                    } else if (user.getData().equals("Kattaqo'rg'on tumani")) { // 39.981981100516265, 66.2896029199572
                        sendMessage(user.getChatId().toString(), setApiK("39.981981100516265", "66.2896029199572", "Самарқанд, Каттақўрғон шаҳри"));
                    } else if (user.getData().equals("Kattaqo'rg'on shahri")) { // 39.8993149735457, 66.2633149577346
                        sendMessage(user.getChatId().toString(), setApiK("39.8993149735457", "66.2633149577346", "Самарқанд, Каттақўрғон тумани"));
                    } else if (user.getData().equals("Narpay")) {//39.92524679769102, 65.98627826494945
                        sendMessage(user.getChatId().toString(), setApiK("39.92524679769102", "65.98627826494945", "Самарқанд, Нарпай"));
                    } else if (user.getData().equals("Nurobod")) {//39.57127098105206, 66.02799000748902
                        sendMessage(user.getChatId().toString(), setApiK("39.57127098105206", "66.02799000748902", "Самарқанд, Нуробод"));
                    } else if (user.getData().equals("Oqdaryo")) {//39.839873784601465, 66.73834694617673
                        sendMessage(user.getChatId().toString(), setApiK("39.839873784601465", "66.73834694617673", "Самарқанд, Оқдарё"));
                    } else if (user.getData().equals("Past darg'om")) { // 39.704352292734065, 66.62242053041332
                        sendMessage(user.getChatId().toString(), setApiK("39.704352292734065", "66.62242053041332", "Самарқанд, Паст дарғом"));
                    } else if (user.getData().equals("Urgut")) {//39.447002526177904, 67.17173665735707
                        sendMessage(user.getChatId().toString(), setApiK("39.447002526177904", "67.17173665735707", "Самарқанд, Ургут"));
                    } else if (user.getData().equals("Paxtachi")) {//39.888515759299004, 65.6089318735053
                        sendMessage(user.getChatId().toString(), setApiK("39.888515759299004", "65.6089318735053", "Самарқанд, Пахтачи"));
                    } else if (user.getData().equals("Poyariq")) {//40.05104556455984, 66.85419779064077
                        sendMessage(user.getChatId().toString(), setApiK("40.05104556455984", "66.85419779064077", "Самарқанд, Пояриқ"));
                    } else if (user.getData().equals("Samarqand tumani")) { //39.59818617036431, 66.90471762188967
                        sendMessage(user.getChatId().toString(), setApiK("39.59818617036431", "66.90471762188967", "Самарқанд, Самарқанд тумани"));
                    } else if (user.getData().equals("Toyloq")) { //39.56442359774873, 67.14018598905683
                        sendMessage(user.getChatId().toString(), setApiK("39.56442359774873", "67.14018598905683", "Самарқанд, Тойлоқ"));
                    } else if (user.getData().equals("Qo'shrabot")) { //40.38655726843597, 66.5100175021684
                        sendMessage(user.getChatId().toString(), setApiK("40.38655726843597", "66.5100175021684", "Самарқанд, Қўшработ"));
                    }
                    if (user.getData().equals("Buxoro shahri")) {//39.77672367865153, 64.4210785732946
                        sendMessage(user.getChatId().toString(), setApiK("39.77672367865153", "64.4210785732946", "Бухоро, Бухоро шаҳри"));
                    } else if (user.getData().equals("Buxoro tumani")) { //39.63566090876281, 64.30230294221505
                        sendMessage(user.getChatId().toString(), setApiK("39.63566090876281", "64.30230294221505", "Бухоро, Бухоро тумани"));
                    } else if (user.getData().equals("G'ijduvon")) {//40.601906240839995, 64.71053498588797
                        sendMessage(user.getChatId().toString(), setApiK("40.601906240839995", "64.71053498588797", "Бухоро, Ғиждувон"));
                    } else if (user.getData().equals("Jondor")) {//39.971219891973426, 63.5714954269693
                        sendMessage(user.getChatId().toString(), setApiK("39.971219891973426", "63.5714954269693", "Бухоро, Жондор"));
                    } else if (user.getData().equals("Kogon shahri")) { //39.72355224686954, 64.55132893267286
                        sendMessage(user.getChatId().toString(), setApiK("39.72355224686954", "64.55132893267286", "Бухоро, Когон шаҳри"));
                    } else if (user.getData().equals("Kogon tumani")) { //39.69809076622412, 64.53702710674044
                        sendMessage(user.getChatId().toString(), setApiK("39.69809076622412", "64.53702710674044", "Бухоро, Когон тумани"));
                    } else if (user.getData().equals("Olot")) { //39.281691000170674, 64.03768298578096
                        sendMessage(user.getChatId().toString(), setApiK("39.281691000170674", "64.03768298578096", "Бухоро, Олот"));
                    } else if (user.getData().equals("Peshku")) {//40.73303621917936, 63.21959281775061
                        sendMessage(user.getChatId().toString(), setApiK("40.73303621917936", "63.21959281775061", "Бухоро, Пешку"));
                    } else if (user.getData().equals("Qorako'l")) { //39.936970007976, 62.941145138407805
                        sendMessage(user.getChatId().toString(), setApiK("39.936970007976", "62.941145138407805", "Бухоро, Қоракўл"));
                    } else if (user.getData().equals("Qorovulbozor")) {//39.46527348718642, 64.67070586334809
                        sendMessage(user.getChatId().toString(), setApiK("39.46527348718642", "64.67070586334809", "Бухоро, Қоровулбозор"));
                    } else if (user.getData().equals("Romitan")) { //40.731071302974954, 62.530718928951856
                        sendMessage(user.getChatId().toString(), setApiK("40.731071302974954", "62.530718928951856", "Бухоро, Ромитан"));
                    } else if (user.getData().equals("Shofirkon")) { // 40.12540773474785, 64.48270729594604
                        sendMessage(user.getChatId().toString(), setApiK("40.12540773474785", "64.48270729594604", "Бухоро, Шофиркон"));
                    } else if (user.getData().equals("Vobkent")) { //40.15547517789773, 64.53244365200904
                        sendMessage(user.getChatId().toString(), setApiK("40.15547517789773", "64.53244365200904", "Бухоро, Вобкент"));
                    }
                    if (user.getData().equals("Navoiy shahri")) {//40.10166507561083, 65.3575344176788
                        sendMessage(user.getChatId().toString(), setApiK("40.10166507561083", "65.3575344176788", "Навоий, Навоий шаҳри"));
                    } else if (user.getData().equals("Karmana")) {//40.14372322026956, 65.35467198347476
                        sendMessage(user.getChatId().toString(), setApiK("40.14372322026956", "65.35467198347476", "Навоий, Кармана"));
                    } else if (user.getData().equals("Konimex")) {// 40.277684998973186, 65.14210146382663
                        sendMessage(user.getChatId().toString(), setApiK("40.277684998973186", "65.14210146382663", "Навоий, Конимех"));
                    } else if (user.getData().equals("Navbahor")) {//40.232621293600054, 65.23269831157204
                        sendMessage(user.getChatId().toString(), setApiK("40.232621293600054", "65.23269831157204", "Навоий, Навбаҳор"));
                    } else if (user.getData().equals("Nurota")) {//40.61096195334514, 65.93817362794783
                        sendMessage(user.getChatId().toString(), setApiK("40.61096195334514", "65.93817362794783", "Навоий, Нурота"));
                    } else if (user.getData().equals("Qiziltepa")) {//39.8966432527515, 64.76528114807608
                        sendMessage(user.getChatId().toString(), setApiK("39.8966432527515", "64.76528114807608", "Навоий, Қизилтепа"));
                    } else if (user.getData().equals("Tomdi")) {//42.29906147558487, 64.90150727134733
                        sendMessage(user.getChatId().toString(), setApiK("42.29906147558487", "64.90150727134733", "Навоий, Томди"));
                    } else if (user.getData().equals("Uchquduq")) {//42.44642264030942, 62.6945789709221
                        sendMessage(user.getChatId().toString(), setApiK("42.44642264030942", "62.6945789709221", "Навоий, Учқудуқ"));
                    } else if (user.getData().equals("Xatirchi")) {//40.230069075732, 65.9409253080852
                        sendMessage(user.getChatId().toString(), setApiK("40.230069075732", "65.9409253080852", "Навоий, Хатирчи"));
                    } else if (user.getData().equals("Zarafshon")) {//41.574460147847816, 64.18331570807477
                        sendMessage(user.getChatId().toString(), setApiK("41.574460147847816", "64.18331570807477", "Навоий, Зарафшон"));
                    }
                    if (user.getData().equals("Chust")) { // 41.00615643758215, 71.23771796120751
                        sendMessage(user.getChatId().toString(), setApiK("40.999931", "71.236137", "Наманган, Чуст"));
                    } else if (user.getData().equals("Namangan")) { // Namangan shahar
                        sendMessage(user.getChatId().toString(), setApiK("41.007162", "71.643712", "Наманган, Наманган шаҳар"));
                    } else if (user.getData().equals("Kosonsoy")) { // Kosonsoy shahar
                        sendMessage(user.getChatId().toString(), setApiK("41.19608642817571", "71.52652970907708", "Наманган, Косонсой"));
                    } else if (user.getData().equals("Chortoq")) { // Chortoq shahar 41.06368431981446, 71.8230565658223
                        sendMessage(user.getChatId().toString(), setApiK("41.06368431981446", "71.8230565658223", "Наманган, Чортоқ"));
                    } else if (user.getData().equals("Norin")) { // Namangan shahar 40.940509509996495, 72.00654567901067
                        sendMessage(user.getChatId().toString(), setApiK("40.940509509996495", "72.00654567901067", "Наманган, Норин"));
                    } else if (user.getData().equals("Mingbuloq")) { // Namangan shahar 40.78917645308457, 71.38875951462421
                        sendMessage(user.getChatId().toString(), setApiK("40.78917645308457", "71.38875951462421", "Наманган, Мингбулоқ"));
                    } else if (user.getData().equals("Pop")) { // Namangan shahar 41.0364117324324, 70.75487013756214
                        sendMessage(user.getChatId().toString(), setApiK("41.0364117324324", "70.75487013756214", "Наманган, Поп"));
                    } else if (user.getData().equals("To'raqo'rg'on")) { // Namangan shahar 40.99316007632018, 71.51626227933346
                        sendMessage(user.getChatId().toString(), setApiK("40.99316007632018", "71.51626227933346", "Наманган, Тўрақўрғон"));
                    } else if (user.getData().equals("Uchqo'rg'on")) { // Namangan shahar 41.12068842248576, 72.08711447153537
                        sendMessage(user.getChatId().toString(), setApiK("41.12068842248576", "72.08711447153537", "Наманган, Учқўрғон"));
                    } else if (user.getData().equals("uychi")) { // Namangan shahar 41.06721589284562, 71.92292974977094
                        sendMessage(user.getChatId().toString(), setApiK("71.92292974977094", "71.92292974977094", "Наманган, Уйчи"));
                    } else if (user.getData().equals("yangiqorgon")) { // Namangan shahar 41.198968289144275, 71.71703917988339
                        sendMessage(user.getChatId().toString(), setApiK("41.198968289144275", "71.71703917988339", "Наманган, Янгиқўргон"));
                    }
                    if (user.getData().equals("Farg‘ona shahar")) { //40.37949214005235, 71.78153222147638
                        sendMessage(user.getChatId().toString(), setApiK("40.37949214005235", "71.78153222147638", "Фарғона, Фарғона шаҳар"));
                    } else if (user.getData().equals("Uchko‘prik")) { //40.53267491097347, 71.02892566146784
                        sendMessage(user.getChatId().toString(), setApiK("40.53267491097347", "71.02892566146784", "Фарғона, Учкўприк"));
                    } else if (user.getData().equals("So‘x")) { //39.968927659480165, 71.12908694248515
                        sendMessage(user.getChatId().toString(), setApiK("39.968927659480165", "71.12908694248515", "Фарғона, Сўх"));
                    } else if (user.getData().equals("Yozyovon")) { //40.62131813678478, 71.6480687836224
                        sendMessage(user.getChatId().toString(), setApiK("40.62131813678478", "71.6480687836224", "Фарғона, Ёзёвон"));
                    } else if (user.getData().equals("Quva")) { //40.524573367810504, 72.06257627873157
                        sendMessage(user.getChatId().toString(), setApiK("40.524573367810504", "72.06257627873157", "Фарғона, Қува"));
                    } else if (user.getData().equals("Qo‘qon")) { //40.524621966621304, 70.93356479162965
                        sendMessage(user.getChatId().toString(), setApiK("40.524621966621304", "70.93356479162965", "Фарғона, Қўқон"));
                    } else if (user.getData().equals("Beshariq")) { //40.39690886265036, 70.57359975866977
                        sendMessage(user.getChatId().toString(), setApiK("40.39690886265036", "70.57359975866977", "Фарғона, Бешариқ"));
                    } else if (user.getData().equals("Quvasoy")) { //
                        sendMessage(user.getChatId().toString(), setApiK("40.30222", "71.97444", "Фарғона, Қувасой"));
                    } else if (user.getData().equals("Farg‘ona")) { //40.320714145540286, 71.70209565835899
                        sendMessage(user.getChatId().toString(), setApiK("40.320714145540286", "71.70209565835899", "Фарғона, Фарғона"));
                    } else if (user.getData().equals("Toshloq")) { //40.56269866588281, 71.84044642257277
                        sendMessage(user.getChatId().toString(), setApiK("40.56269866588281", "71.84044642257277", "Фарғона, Тошлоқ"));
                    } else if (user.getData().equals("O‘zbekiston")) { //40.330297796679396, 70.85868054864669
                        sendMessage(user.getChatId().toString(), setApiK("40.330297796679396", "70.85868054864669", "Фарғона, Ўзбекистон"));
                    } else if (user.getData().equals("Qo‘shtepa")) { //40.53692345413236, 71.6415020647065
                        sendMessage(user.getChatId().toString(), setApiK("40.53692345413236", "71.6415020647065", "Фарғона, Қўштепа"));
                    } else if (user.getData().equals("Marg‘ilon")) { //40.47635685369382, 71.71252435127899
                        sendMessage(user.getChatId().toString(), setApiK("40.47635685369382", "71.71252435127899", "Фарғона, Марғилон"));
                    } else if (user.getData().equals("Buvayda")) { //40.645693110093404, 71.10204505839337
                        sendMessage(user.getChatId().toString(), setApiK("40.645693110093404", "71.10204505839337", "Фарғона, Бувайда"));
                    } else if (user.getData().equals("Dang‘ara")) { //40.58139710281787, 70.91477380795894
                        sendMessage(user.getChatId().toString(), setApiK("40.58139710281787", "70.91477380795894", "Фарғона, Данғара"));
                    } else if (user.getData().equals("Oltiariq")) { //40.39370084174377, 71.48670380912914
                        sendMessage(user.getChatId().toString(), setApiK("40.39370084174377", "71.48670380912914", "Фарғона, Олтиариқ"));
                    } else if (user.getData().equals("Rishton")) { //40.36410679785719, 71.27208639645364
                        sendMessage(user.getChatId().toString(), setApiK("40.36410679785719", "71.27208639645364", "Фарғона, Риштон"));
                    } else if (user.getData().equals("Furqat")) { //40.51673390388502, 70.71177286893743
                        sendMessage(user.getChatId().toString(), setApiK("40.51673390388502", "70.71177286893743", "Фарғона, Фурқат"));
                    } else if (user.getData().equals("Bag‘dod")) { //40.49387968871484, 71.20366862141532
                        sendMessage(user.getChatId().toString(), setApiK("40.49387968871484", "71.20366862141532", "Фарғона, Бағдод"));
                    }
                    if (user.getData().equals("Jizzax shahri")) {//40.11977007458388, 67.85232343283624
                        sendMessage(user.getChatId().toString(), setApiK("40.11977007458388", "67.85232343283624", "Жиззах, Жиззах шаҳри"));
                    } else if (user.getData().equals("Arnasoy")) { //40.59901232957254, 67.79358090175431
                        sendMessage(user.getChatId().toString(), setApiK("40.59901232957254", "67.79358090175431", "Жиззах, Aрнасой"));
                    } else if (user.getData().equals("Baxmal")) {//39.78225382213002, 67.7250016175458
                        sendMessage(user.getChatId().toString(), setApiK("39.78225382213002", "67.7250016175458", "Жиззах, Бахмал"));
                    } else if (user.getData().equals("Forish")) {//40.70901562393393, 67.18181520202268
                        sendMessage(user.getChatId().toString(), setApiK("40.70901562393393", "67.18181520202268", "Жиззах, Фориш"));
                    } else if (user.getData().equals("G'allaorol")) { //40.10205495202923, 67.32846413281408
                        sendMessage(user.getChatId().toString(), setApiK("40.10205495202923", "67.32846413281408", "Жиззах, Ғаллаорол"));
                    } else if (user.getData().equals("Do'stlik")) { //40.58181976770775, 68.04548305263552
                        sendMessage(user.getChatId().toString(), setApiK("40.58181976770775", "68.04548305263552", "Жиззах, Дўстлик"));
                    } else if (user.getData().equals("Jizzax tumani")) { //40.11455358141378, 67.82473708142044
                        sendMessage(user.getChatId().toString(), setApiK("40.11455358141378", "67.82473708142044", "Жиззах, Жиззах тумани"));
                    } else if (user.getData().equals("Mirzacho'l")) {//40.724412215707, 68.06118184566003
                        sendMessage(user.getChatId().toString(), setApiK("40.724412215707", "68.06118184566003", "Жиззах, Мирзачўл"));
                    } else if (user.getData().equals("Paxtakor")) { //40.35950221136383, 68.00475878330718
                        sendMessage(user.getChatId().toString(), setApiK("40.35950221136383", "68.00475878330718", "Жиззах, Пахтакор"));
                    } else if (user.getData().equals("Yangiobod")) {//39.99243484998208, 68.74527110184545
                        sendMessage(user.getChatId().toString(), setApiK("39.99243484998208", "68.74527110184545", "Жиззах, Янгиобод"));
                    } else if (user.getData().equals("Zafarobod")) { //40.41330581212753, 67.76451866402623
                        sendMessage(user.getChatId().toString(), setApiK("40.41330581212753", "67.76451866402623", "Жиззах, Зафаробод"));
                    } else if (user.getData().equals("Zarband")) { //40.189983986463055, 68.11455808514548
                        sendMessage(user.getChatId().toString(), setApiK("40.189983986463055", "68.11455808514548", "Жиззах, Зарбанд"));
                    } else if (user.getData().equals("Zomin")) { //39.88553183671697, 68.3643022291486
                        sendMessage(user.getChatId().toString(), setApiK("39.88553183671697", "68.3643022291486", "Жиззах, Зомин"));
                    }
                    if (user.getData().equals("Bog'ot")) {//41.33744153309746, 60.87694940015311
                        sendMessage(user.getChatId().toString(), setApiK("41.33744153309746", "60.87694940015311", "Хоразм, Боғот"));
                    }
                    if (user.getData().equals("Gurlan")) {//41.90536583933732, 60.37982747138814
                        sendMessage(user.getChatId().toString(), setApiK("41.90536583933732", "60.37982747138814", "Хоразм, Гурлан"));
                    }
                    if (user.getData().equals("Shovot")) {//41.710201238586826, 60.27012648475865
                        sendMessage(user.getChatId().toString(), setApiK("41.710201238586826", "60.27012648475865", "Хоразм, Шовот"));
                    }
                    if (user.getData().equals("Qo'shko'pir")) {//41.526982196908484, 60.269577660413084
                        sendMessage(user.getChatId().toString(), setApiK("41.526982196908484", "60.269577660413084", "Хоразм, Қўшкўпир "));
                    }
                    if (user.getData().equals("Yangibozor")) {// 41.75025881608148, 60.54049313655263
                        sendMessage(user.getChatId().toString(), setApiK("41.75025881608148", "60.54049313655263", "Хоразм, Янгибозор "));
                    }
                    if (user.getData().equals("Urganch shahri")) {//41.547992018041974, 60.618264246685676
                        sendMessage(user.getChatId().toString(), setApiK("41.547992018041974", "60.618264246685676", "Хоразм, Урганч шаҳри"));
                    }
                    if (user.getData().equals("Urganch tumani")) {//41.61625437893366, 60.533593007029786
                        sendMessage(user.getChatId().toString(), setApiK("41.61625437893366", "60.533593007029786", "Хоразм, Урганч тумани"));
                    }
                    if (user.getData().equals("Xazorasp")) {//41.30448683950074, 61.09745692690238
                        sendMessage(user.getChatId().toString(), setApiK("41.30448683950074", "61.09745692690238", "Хоразм, Хазорасп "));
                    }
                    if (user.getData().equals("Xiva")) {//41.39184570391674, 60.34725590732044
                        sendMessage(user.getChatId().toString(), setApiK("41.39184570391674", "60.34725590732044", "Хоразм, Хива "));
                    }
                    if (user.getData().equals("Xonqa")) {//41.481393767464844, 60.829570881655584
                        sendMessage(user.getChatId().toString(), setApiK("41.481393767464844", "60.829570881655584", "Хоразм, Хонқа"));
                    }
                    if (user.getData().equals("Yangiariq")) {// 41.346206916399666, 60.54804614851794
                        sendMessage(user.getChatId().toString(), setApiK("41.346206916399666", "60.54804614851794", "Хоразм, Янгиариқ"));
                    }
                    if (user.getData().equals("Navoiy shahri")) {//40.10166507561083, 65.3575344176788
                        sendMessage(user.getChatId().toString(), setApi("40.10166507561083", "65.3575344176788", "Navoiy, Navoiy shahri"));
                    }
                    if (user.getData().equals("Karmana")) {//40.14372322026956, 65.35467198347476
                        sendMessage(user.getChatId().toString(), setApi("40.14372322026956", "65.35467198347476", "Navoiy, Karmana"));
                    }
                    if (user.getData().equals("Konimex")) {// 40.277684998973186, 65.14210146382663
                        sendMessage(user.getChatId().toString(), setApi("40.277684998973186", "65.14210146382663", "Navoiy, Konimex"));
                    }
                    if (user.getData().equals("Navbahor")) {//40.232621293600054, 65.23269831157204
                        sendMessage(user.getChatId().toString(), setApi("40.232621293600054", "65.23269831157204", "Navoiy, Navbahor"));
                    }
                    if (user.getData().equals("Nurota")) {//40.61096195334514, 65.93817362794783
                        sendMessage(user.getChatId().toString(), setApi("40.61096195334514", "65.93817362794783", "Navoiy, Nurota"));
                    }
                    if (user.getData().equals("Qiziltepa")) {//39.8966432527515, 64.76528114807608
                        sendMessage(user.getChatId().toString(), setApi("39.8966432527515", "64.76528114807608", "Navoiy, Qiziltepa"));
                    }
                    if (user.getData().equals("Tomdi")) {//42.29906147558487, 64.90150727134733
                        sendMessage(user.getChatId().toString(), setApi("42.29906147558487", "64.90150727134733", "Navoiy, Tomdi"));
                    }
                    if (user.getData().equals("Uchquduq")) {//42.44642264030942, 62.6945789709221
                        sendMessage(user.getChatId().toString(), setApi("42.44642264030942", "62.6945789709221", "Navoiy, Uchquduq"));
                    }
                    if (user.getData().equals("Xatirchi")) {//40.230069075732, 65.9409253080852
                        sendMessage(user.getChatId().toString(), setApi("40.230069075732", "65.9409253080852", "Navoiy, Xatirchi"));
                    }
                    if (user.getData().equals("Zarafshon")) {//41.574460147847816, 64.18331570807477
                        sendMessage(user.getChatId().toString(), setApi("41.574460147847816", "64.18331570807477", "Navoiy, Zarafshon"));
                    }
                    if (user.getData().equals("Qarshi shahri")) {//38.86191354410989, 65.78491933285783
                        sendMessage(user.getChatId().toString(), setApiK("38.86191354410989", "65.78491933285783", "Қашқадарё, Қарши шаҳри "));
                    } else if (user.getData().equals("Qarshi tumani")) {//38.779267614177556, 65.75278333536036
                        sendMessage(user.getChatId().toString(), setApiK("38.779267614177556", "65.75278333536036", "Қашқадарё, Қарши тумани"));
                    } else if (user.getData().equals("Chiroqchi")) {//39.18112111196543, 66.25891591391694
                        sendMessage(user.getChatId().toString(), setApiK("39.18112111196543", "66.25891591391694", "Қашқадарё, Чироқчи "));
                    } else if (user.getData().equals("Dehqonobod")) {//38.35730703486065, 66.47533103897435
                        sendMessage(user.getChatId().toString(), setApiK("38.35730703486065", "66.47533103897435", "Қашқадарё, Деҳқонобод"));
                    } else if (user.getData().equals("G'uzor")) {//38.587010422831746, 66.04489530163235
                        sendMessage(user.getChatId().toString(), setApiK("38.587010422831746", "66.04489530163235", "Қашқадарё, Ғузор "));
                    } else if (user.getData().equals("Kasbi")) {//38.93540618287719, 65.43383657129233
                        sendMessage(user.getChatId().toString(), setApiK("38.93540618287719", "65.43383657129233", "Қашқадарё, Касби "));
                    } else if (user.getData().equals("Kitob")) {// 39.2152414606226, 67.03598426370185
                        sendMessage(user.getChatId().toString(), setApiK("39.2152414606226", "67.03598426370185", "Қашқадарё, Китоб "));
                    } else if (user.getData().equals("Koson")) {// 39.16770611275984, 65.77183802497865
                        sendMessage(user.getChatId().toString(), setApiK("39.16770611275984", "65.77183802497865", "Қашқадарё, Косон "));
                    } else if (user.getData().equals("Mirishkor")) {// 38.874194821533756, 64.92111490671182
                        sendMessage(user.getChatId().toString(), setApiK("38.874194821533756", "64.92111490671182", "Қашқадарё, Миришкор "));
                    } else if (user.getData().equals("Muborak")) {//39.30420398425692, 65.25997593377225
                        sendMessage(user.getChatId().toString(), setApiK("39.30420398425692", "65.25997593377225", "Қашқадарё, Муборак "));
                    } else if (user.getData().equals("Nishon")) {//38.53608390327857, 65.48400423357076
                        sendMessage(user.getChatId().toString(), setApiK("38.53608390327857", "65.48400423357076", "Қашқадарё, Нишон "));
                    } else if (user.getData().equals("Qamashi")) {// 38.75660026948466, 66.60185133772104
                        sendMessage(user.getChatId().toString(), setApiK("38.75660026948466", "66.60185133772104", "Қашқадарё, Қамаши "));
                    } else if (user.getData().equals("Shahrisabz")) {//38.99171024720661, 67.2009187750465
                        sendMessage(user.getChatId().toString(), setApiK("38.99171024720661", "67.2009187750465", "Қашқадарё, Шаҳрисабз"));
                    } else if (user.getData().equals("Yakkabog'")) {//38.90742740574835, 66.75665915180723
                        sendMessage(user.getChatId().toString(), setApiK("38.90742740574835", "66.75665915180723", "Қашқадарё, Яккабог"));
                    }
                    userService.updateData(user, user.getId(), user.getData());
                }


            } else if (message.hasLocation()) {
                String lat = message.getLocation().getLatitude().toString();
                String lang = message.getLocation().getLongitude().toString();
                userService.updateLatLang(user, user.getId(), lat, lang);
                if (user.getStep().toString().substring(12,13).equals("k")){
                    sendMessage(user.getChatId().toString(), setApiK(lat, lang, "Лакатсия Жойида"));
                }else {
                    sendMessage(user.getChatId().toString(), setApi(lat, lang, "Lakatsiya bo'yicha"));
                }}
        } else if (update.hasCallbackQuery()) {
            User user = userService.findByChatId(update.getCallbackQuery().getMessage().getChatId());
            String data = update.getCallbackQuery().getData();
            if (user.getStep().equals(UserStep.select_lang)) {
                if (data.equals("lt")) {
                    getRegionInline(user.getChatId());
                    user = userService.update(user, user.getId(), UserStep.select_lang_lt);
                } else if (data.equals("krl")) {
                    sendInlineKeyBoardRegionK(user.getChatId());
                    user = userService.update(user, user.getId(), UserStep.select_lang_krl);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt)) {
                if (data.equals("namangan")) {
                    namanganTumans(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_lt_namangan);
                } else if (data.equals("toshkent")) {
                    sendInlineToshknet(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_lt_toshkent);
                } else if (data.equals("andijon")) {
                    andijonTumans(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_lt_andijon);
                } else if (data.equals("fargona")) {
                    sendInlineFargona(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_lt_fargona);
                } else if (data.equals("samarqand")) {
                    sendInlineSamarqand(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_lt_samarqand);
                } else if (data.equals("jizzah")) {
                    setJizzahK(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_lt_jizzah);
                } else if (data.equals("buxoro")) {
                    sendInlineBuxoro(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_lt_buxoro);
                } else if (data.equals("xorazm")) {
                    sendInlineXorazm(user.getChatId());
                    userService.update(user, user.getChatId(), UserStep.select_lang_lt_xorazm);
                } else if (data.equals("navoiy")) {
                    sendInlineNavoiy(user.getChatId());
                    userService.update(user, user.getChatId(), UserStep.select_lang_lt_navoiy);
                } else if (data.equals("qashqadaryo")) {
                    sendInlineQashqadaryo(user.getChatId());
                    userService.update(user, user.getChatId(), UserStep.select_lang_lt_qashqadaryo);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt_namangan)) {
                if (data.equals("Chust")) { // 41.00615643758215, 71.23771796120751
                    sendMessage(user.getChatId().toString(), setApi("40.999931", "71.236137", "Namangan, " + data));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Namangan")) { // Namangan shahar
                    sendMessage(user.getChatId().toString(), setApi("41.007162", "71.643712", "Namangan, Namangan shahar"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Kosonsoy")) { // Kosonsoy shahar
                    sendMessage(user.getChatId().toString(), setApi("41.19608642817571", "71.52652970907708", "Namangan, " + data));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Chortoq")) { // Chortoq shahar 41.06368431981446, 71.8230565658223
                    sendMessage(user.getChatId().toString(), setApi("41.06368431981446", "71.8230565658223", "Namangan, " + data));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Norin")) { // Namangan shahar 40.940509509996495, 72.00654567901067
                    sendMessage(user.getChatId().toString(), setApiK("40.940509509996495", "72.00654567901067", "Наманган, Норин"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Mingbuloq")) { // Namangan shahar 40.78917645308457, 71.38875951462421
                    sendMessage(user.getChatId().toString(), setApi("40.78917645308457", "71.38875951462421", "Namangan, " + data));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Pop")) { // Namangan shahar 41.0364117324324, 70.75487013756214
                    sendMessage(user.getChatId().toString(), setApi("41.0364117324324", "70.75487013756214", "Namangan, " + data));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("To'raqo'rg'on")) { // Namangan shahar 40.99316007632018, 71.51626227933346
                    sendMessage(user.getChatId().toString(), setApi("40.99316007632018", "71.51626227933346", "Namangan, " + data));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Uchqo'rg'on")) { // Namangan shahar 41.12068842248576, 72.08711447153537
                    sendMessage(user.getChatId().toString(), setApi("41.12068842248576", "72.08711447153537", "Namangan, " + data));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("uychi")) { // Namangan shahar 41.06721589284562, 71.92292974977094
                    sendMessage(user.getChatId().toString(), setApi("71.92292974977094", "71.92292974977094", "Namangan, " + data));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("yangiqorgon")) { // Namangan shahar 41.198968289144275, 71.71703917988339
                    sendMessage(user.getChatId().toString(), setApi("41.198968289144275", "71.71703917988339", "Namangan, " + data));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt_toshkent)) {
                if (data.equals("ToshknetSH")) { // 41.31862648082324, 69.26185247247427
                    sendMessage(user.getChatId().toString(), setApi("41.31862648082324", "69.26185247247427", "Toshkent ,Toshkent Shahar"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("chilonzor")) { // 41.25966068034852, 69.17981683014095
                    sendMessage(user.getChatId().toString(), setApi("41.25966068034852", "69.17981683014095", "Toshkent ,Chilonzor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("quyiC")) { // 41.324249737732416, 69.27336159543128
                    sendMessage(user.getChatId().toString(), setApi("41.324249737732416", "69.27336159543128", "Toshkent ,Quyi Chirchiq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("bostonliq")) { // 41.29962474317497, 69.24013777030345
                    sendMessage(user.getChatId().toString(), setApi("41.29962474317497", "69.24013777030345", "Toshkent ,Bo'stonliq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("yangiyol")) { // 41.106601409336605, 69.00099564918969
                    sendMessage(user.getChatId().toString(), setApi("41.106601409336605", "69.00099564918969", "Toshkent ,Yangiyo'l"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("zangota")) { // 41.19119250475624, 69.14481581613583
                    sendMessage(user.getChatId().toString(), setApi("41.19119250475624", "69.14481581613583", "Toshkent ,Zangiota"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("ohangaron")) { // 40.91797520041142, 69.63525266877876
                    sendMessage(user.getChatId().toString(), setApi("40.91797520041142", "69.63525266877876", "Toshkent ,Ohangaron"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("oqqq")) { // 40.88044288436734, 69.04266437650311
                    sendMessage(user.getChatId().toString(), setApi("40.88044288436734", "69.04266437650311", "Toshkent ,Oqqo'rg'on"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("ppp")) { // 41.297497902393545, 69.68451728729993
                    sendMessage(user.getChatId().toString(), setApi("41.297497902393545", "69.68451728729993", "Toshkent ,Parkent"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("ortachirchiq")) { // 41.20590993388904, 69.27922461238849
                    sendMessage(user.getChatId().toString(), setApi("41.20590993388904", "69.27922461238849", "Toshkent ,O'rta Chirchiq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("yuqorichirchiq")) { // 41.20911424680261, 69.49407488275496
                    sendMessage(user.getChatId().toString(), setApi("41.20911424680261", "69.49407488275496", "Toshkent ,Yuqori Chirchiq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("bekobod")) { // 40.454055968860445, 69.1819780480247
                    sendMessage(user.getChatId().toString(), setApi("40.454055968860445", "69.1819780480247", "Toshkent ,Bekobod"));
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt_fargona)) {
                if (data.equals("Farg‘ona shahar")) { //40.37949214005235, 71.78153222147638
                    sendMessage(user.getChatId().toString(), setApi("40.37949214005235", "71.78153222147638", "Farg'ona, Farg‘ona shahar"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Uchko‘prik")) { //40.53267491097347, 71.02892566146784
                    sendMessage(user.getChatId().toString(), setApi("40.53267491097347", "71.02892566146784", "Farg'ona, Uchko‘prik"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("So‘x")) { //39.968927659480165, 71.12908694248515
                    sendMessage(user.getChatId().toString(), setApi("39.968927659480165", "71.12908694248515", "Farg'ona, So‘x"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Yozyovon")) { //40.62131813678478, 71.6480687836224
                    sendMessage(user.getChatId().toString(), setApi("40.62131813678478", "71.6480687836224", "Farg'ona, Yozyovon"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Quva")) { //40.524573367810504, 72.06257627873157
                    sendMessage(user.getChatId().toString(), setApi("40.524573367810504", "72.06257627873157", "Farg'ona, Quva"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qo‘qon")) { //40.524621966621304, 70.93356479162965
                    sendMessage(user.getChatId().toString(), setApi("40.524621966621304", "70.93356479162965", "Farg'ona, Qo‘qon"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Beshariq")) { //40.39690886265036, 70.57359975866977
                    sendMessage(user.getChatId().toString(), setApi("40.39690886265036", "70.57359975866977", "Farg'ona, Beshariq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Quvasoy")) { //
                    sendMessage(user.getChatId().toString(), setApi("40.30222", "71.97444", "Farg'ona, Quvasoy"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Farg‘ona")) { //40.320714145540286, 71.70209565835899
                    sendMessage(user.getChatId().toString(), setApi("40.320714145540286", "71.70209565835899", "Farg'ona, Farg‘ona"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Toshloq")) { //40.56269866588281, 71.84044642257277
                    sendMessage(user.getChatId().toString(), setApi("40.56269866588281", "71.84044642257277", "Farg'ona, Toshloq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("O‘zbekiston")) { //40.330297796679396, 70.85868054864669
                    sendMessage(user.getChatId().toString(), setApi("40.330297796679396", "70.85868054864669", "Farg'ona, O‘zbekiston"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qo‘shtepa")) { //40.53692345413236, 71.6415020647065
                    sendMessage(user.getChatId().toString(), setApi("40.53692345413236", "71.6415020647065", "Farg'ona, Qo‘shtepa"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Marg‘ilon")) { //40.47635685369382, 71.71252435127899
                    sendMessage(user.getChatId().toString(), setApi("40.47635685369382", "71.71252435127899", "Farg'ona, Marg‘ilon"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Buvayda")) { //40.645693110093404, 71.10204505839337
                    sendMessage(user.getChatId().toString(), setApi("40.645693110093404", "71.10204505839337", "Farg'ona, Buvayda"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Dang‘ara")) { //40.58139710281787, 70.91477380795894
                    sendMessage(user.getChatId().toString(), setApi("40.58139710281787", "70.91477380795894", "Farg'ona, Dang‘ara"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Oltiariq")) { //40.39370084174377, 71.48670380912914
                    sendMessage(user.getChatId().toString(), setApi("40.39370084174377", "71.48670380912914", "Farg'ona, Oltiariq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Rishton")) { //40.36410679785719, 71.27208639645364
                    sendMessage(user.getChatId().toString(), setApi("40.36410679785719", "71.27208639645364", "Farg'ona, Rishton"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Furqat")) { //40.51673390388502, 70.71177286893743
                    sendMessage(user.getChatId().toString(), setApi("40.51673390388502", "70.71177286893743", "Farg'ona, Furqat"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Bag‘dod")) { //40.49387968871484, 71.20366862141532
                    sendMessage(user.getChatId().toString(), setApi("40.49387968871484", "71.20366862141532", "Farg'ona, Bag‘dod"));
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt_andijon)) {
                if (data.equals("Andijon shahri")) {//40.7865585435806, 72.31208083702941
                    sendMessage(user.getChatId().toString(), setApi("40.7865585435806", "72.31208083702941", "Andijon, Andijon shahar"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Xonobod")) { //40.79989825348597, 72.98951560333182
                    sendMessage(user.getChatId().toString(), setApi("40.454055968860445", "72.98951560333182", "Andijon, Xonobod"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Asaka")) {// 40.6619355989725, 72.24428848141633
                    sendMessage(user.getChatId().toString(), setApi("40.6619355989725", "72.24428848141633", "Andijon, Asaka"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Baliqchi")) {//40.8681067477643, 71.90901428107483
                    sendMessage(user.getChatId().toString(), setApi("40.8681067477643", "71.90901428107483", "Andijon, Baliqchi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Bo'z")) { //40.67522368198782, 71.90202999274003
                    sendMessage(user.getChatId().toString(), setApi("40.67522368198782", "71.90202999274003", "Andijon, Bo'z"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Buloqboshi")) { //40.61824097722169, 72.4589485266868
                    sendMessage(user.getChatId().toString(), setApi("40.61824097722169", "72.4589485266868", "Andijon, Buloqboshi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Izboskan")) { //40.926914438296166, 72.23046114826215
                    sendMessage(user.getChatId().toString(), setApi("40.926914438296166", "72.23046114826215", "Andijon, Izboskan"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Jalolquduq")) {//40.73260859991856, 72.61228519306135
                    sendMessage(user.getChatId().toString(), setApi("40.73260859991856", "72.61228519306135", "Andijon, Jalolquduq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Marhamat")) { //40.51393917604782, 72.29981218880005
                    sendMessage(user.getChatId().toString(), setApi("40.51393917604782", "72.29981218880005", "Andijon, Marhamat"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Oltinko'l")) {// 40.78257767662204, 72.15630346953014
                    sendMessage(user.getChatId().toString(), setApi("40.78257767662204", "72.15630346953014", "Andijon, Oltinko'l"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Paxtaobod")) { //40.977855409832266, 72.42257164080198
                    sendMessage(user.getChatId().toString(), setApi("40.977855409832266", "72.42257164080198", "Andijon, Paxtaobod"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qo'rg'ontepa")) { // 40.737458816612616, 72.80489172467401
                    sendMessage(user.getChatId().toString(), setApi("40.737458816612616", "72.80489172467401", "Andijon, Qo'rg'ontepa"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Ulug'nor")) { //40.7812938834895, 71.65829940832162
                    sendMessage(user.getChatId().toString(), setApi("40.7812938834895", "71.65829940832162", "Andijon, Ulug'nor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Xo'jaobod")) { // 40.644265790848365, 72.59695277036937
                    sendMessage(user.getChatId().toString(), setApi("40.644265790848365", "72.59695277036937", "Andijon, Xo'jaobod"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Shahrixon")) { //40.705658566969085, 72.04954603823948
                    sendMessage(user.getChatId().toString(), setApi("40.705658566969085", "72.04954603823948", "Andijon, Shahrixon"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt_samarqand)) {
                if (data.equals("Samarqand shahri")) {//39.65329327101519, 66.95826234725476
                    sendMessage(user.getChatId().toString(), setApi("39.65329327101519", "66.95826234725476", "Samarqand, Samarqand shahri"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Bulung'ur")) {//39.70791766177822, 67.28142857964608
                    sendMessage(user.getChatId().toString(), setApi("39.70791766177822", "67.28142857964608", "Samarqand, Bulung'ur"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Ishtixon")) {//39.98829349793204, 66.53144028860048
                    sendMessage(user.getChatId().toString(), setApi("39.98829349793204", "66.53144028860048", "Samarqand, Ishtixon"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Jomboy")) {//39.76625238583371, 67.07779535428304
                    sendMessage(user.getChatId().toString(), setApi("39.76625238583371", "67.07779535428304", "Samarqand, Jomboy"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Kattaqo'rg'on tumani")) { // 39.981981100516265, 66.2896029199572
                    sendMessage(user.getChatId().toString(), setApi("39.981981100516265", "66.2896029199572", "Samarqand, Kattaqo'rg'on shahri"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Kattaqo'rg'on shahri")) { // 39.8993149735457, 66.2633149577346
                    sendMessage(user.getChatId().toString(), setApi("39.8993149735457", "66.2633149577346", "Samarqand, Kattaqo'rg'on tumani"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Narpay")) {//39.92524679769102, 65.98627826494945
                    sendMessage(user.getChatId().toString(), setApi("39.92524679769102", "65.98627826494945", "Samarqand, Narpay"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Nurobod")) {//39.57127098105206, 66.02799000748902
                    sendMessage(user.getChatId().toString(), setApi("39.57127098105206", "66.02799000748902", "Samarqand, Nurobod"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Oqdaryo")) {//39.839873784601465, 66.73834694617673
                    sendMessage(user.getChatId().toString(), setApi("39.839873784601465", "66.73834694617673", "Samarqand, Oqdaryo"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Past darg'om")) { // 39.704352292734065, 66.62242053041332
                    sendMessage(user.getChatId().toString(), setApi("39.704352292734065", "66.62242053041332", "Samarqand, Past darg'om"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Urgut")) {//39.447002526177904, 67.17173665735707
                    sendMessage(user.getChatId().toString(), setApi("39.447002526177904", "67.17173665735707", "Samarqand, Urgut"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Paxtachi")) {//39.888515759299004, 65.6089318735053
                    sendMessage(user.getChatId().toString(), setApi("39.888515759299004", "65.6089318735053", "Samarqand, Paxtachi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Poyariq")) {//40.05104556455984, 66.85419779064077
                    sendMessage(user.getChatId().toString(), setApi("40.05104556455984", "66.85419779064077", "Samarqand, Poyariq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Samarqand tumani")) { //39.59818617036431, 66.90471762188967
                    sendMessage(user.getChatId().toString(), setApi("39.59818617036431", "66.90471762188967", "Samarqand, Samarqand tumani"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Toyloq")) { //39.56442359774873, 67.14018598905683
                    sendMessage(user.getChatId().toString(), setApi("39.56442359774873", "67.14018598905683", "Samarqand, Toyloq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qo'shrabot")) { //40.38655726843597, 66.5100175021684
                    sendMessage(user.getChatId().toString(), setApi("40.38655726843597", "66.5100175021684", "Samarqand, Qo'shrabot"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
                sendKunOy(user.getChatId(), data);
            } else if (user.getStep().equals(UserStep.select_lang_lt_jizzah)) {
                if (data.equals("Jizzax shahri")) {//40.11977007458388, 67.85232343283624
                    sendMessage(user.getChatId().toString(), setApi("40.11977007458388", "67.85232343283624", "Jizzah, Jizzax shahri"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Arnasoy")) { //40.59901232957254, 67.79358090175431
                    sendMessage(user.getChatId().toString(), setApi("40.59901232957254", "67.79358090175431", "Jizzah, Arnasoy"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Baxmal")) {//39.78225382213002, 67.7250016175458
                    sendMessage(user.getChatId().toString(), setApi("39.78225382213002", "67.7250016175458", "Jizzah, Baxmal"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Forish")) {//40.70901562393393, 67.18181520202268
                    sendMessage(user.getChatId().toString(), setApi("40.70901562393393", "67.18181520202268", "Jizzah, Forish"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("G'allaorol")) { //40.10205495202923, 67.32846413281408
                    sendMessage(user.getChatId().toString(), setApi("40.10205495202923", "67.32846413281408", "Jizzah, G'allaorol"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Do'stlik")) { //40.58181976770775, 68.04548305263552
                    sendMessage(user.getChatId().toString(), setApi("40.58181976770775", "68.04548305263552", "Jizzah, Do'stlik"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Jizzax tumani")) { //40.11455358141378, 67.82473708142044
                    sendMessage(user.getChatId().toString(), setApi("40.11455358141378", "67.82473708142044", "Jizzah, Jizzax tumani"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Mirzacho'l")) {//40.724412215707, 68.06118184566003
                    sendMessage(user.getChatId().toString(), setApi("40.724412215707", "68.06118184566003", "Jizzah, Mirzacho'l"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Paxtakor")) { //40.35950221136383, 68.00475878330718
                    sendMessage(user.getChatId().toString(), setApi("40.35950221136383", "68.00475878330718", "Jizzah, Paxtakor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Yangiobod")) {//39.99243484998208, 68.74527110184545
                    sendMessage(user.getChatId().toString(), setApi("39.99243484998208", "68.74527110184545", "Jizzah, Yangiobod"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Zafarobod")) { //40.41330581212753, 67.76451866402623
                    sendMessage(user.getChatId().toString(), setApi("40.41330581212753", "67.76451866402623", "Jizzah, Zafarobod"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Zarband")) { //40.189983986463055, 68.11455808514548
                    sendMessage(user.getChatId().toString(), setApi("40.189983986463055", "68.11455808514548", "Jizzah, Zarband"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Zomin")) { //39.88553183671697, 68.3643022291486
                    sendMessage(user.getChatId().toString(), setApi("39.88553183671697", "68.3643022291486", "Jizzah, Zomin"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
                sendKunOy(user.getChatId(), data);
            } else if (user.getStep().equals(UserStep.select_lang_lt_buxoro)) {
                if (data.equals("Buxoro shahri")) {//39.77672367865153, 64.4210785732946
                    sendMessage(user.getChatId().toString(), setApi("39.77672367865153", "64.4210785732946", "Buxoro, Buxoro shahri"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Buxoro tumani")) { //39.63566090876281, 64.30230294221505
                    sendMessage(user.getChatId().toString(), setApi("39.63566090876281", "64.30230294221505", "Buxoro, Buxoro tumani"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("G'ijduvon")) {//40.601906240839995, 64.71053498588797
                    sendKunOy(user.getChatId(), data);
                    sendMessage(user.getChatId().toString(), setApi("40.601906240839995", "64.71053498588797", "Buxoro, G'ijduvon"));
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Jondor")) {//39.971219891973426, 63.5714954269693
                    sendMessage(user.getChatId().toString(), setApi("39.971219891973426", "63.5714954269693", "Buxoro, Jondor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Kogon shahri")) { //39.72355224686954, 64.55132893267286
                    sendMessage(user.getChatId().toString(), setApi("39.72355224686954", "64.55132893267286", "Buxoro, Kogon shahri"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Kogon tumani")) { //39.69809076622412, 64.53702710674044
                    sendMessage(user.getChatId().toString(), setApi("39.69809076622412", "64.53702710674044", "Buxoro, Kogon tumani"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Olot")) { //39.281691000170674, 64.03768298578096
                    sendMessage(user.getChatId().toString(), setApi("39.281691000170674", "64.03768298578096", "Buxoro, Olot"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Peshku")) {//40.73303621917936, 63.21959281775061
                    sendMessage(user.getChatId().toString(), setApi("40.73303621917936", "63.21959281775061", "Buxoro, Peshku"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qorako'l")) { //39.936970007976, 62.941145138407805
                    sendMessage(user.getChatId().toString(), setApi("39.936970007976", "62.941145138407805", "Buxoro, Qorako'l"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qorovulbozor")) {//39.46527348718642, 64.67070586334809
                    sendMessage(user.getChatId().toString(), setApi("39.46527348718642", "64.67070586334809", "Buxoro, Qorovulbozor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Romitan")) { //40.731071302974954, 62.530718928951856
                    sendMessage(user.getChatId().toString(), setApi("40.731071302974954", "62.530718928951856", "Buxoro, Romitan"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Shofirkon")) { // 40.12540773474785, 64.48270729594604
                    sendMessage(user.getChatId().toString(), setApi("40.12540773474785", "64.48270729594604", "Buxoro, Shofirkon"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Vobkent")) { //40.15547517789773, 64.53244365200904
                    sendMessage(user.getChatId().toString(), setApi("40.15547517789773", "64.53244365200904", "Buxoro, Vobkent"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt_xorazm)) {
                if (data.equals("Bog'ot")) {//41.33744153309746, 60.87694940015311
                    sendMessage(user.getChatId().toString(), setApi("41.33744153309746", "60.87694940015311", "Xorazm, Bog'ot"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);

                }
                if (data.equals("Gurlan")) {//41.90536583933732, 60.37982747138814
                    sendMessage(user.getChatId().toString(), setApi("41.90536583933732", "60.37982747138814", "Xorazm, Gurlan"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Shovot")) {//41.710201238586826, 60.27012648475865
                    sendMessage(user.getChatId().toString(), setApi("41.710201238586826", "60.27012648475865", "Xorazm, Shovot"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qo'shko'pir")) {//41.526982196908484, 60.269577660413084
                    sendMessage(user.getChatId().toString(), setApi("41.526982196908484", "60.269577660413084", "Xorazm, Qo'shko'pir"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Yangibozor")) {// 41.75025881608148, 60.54049313655263
                    sendMessage(user.getChatId().toString(), setApi("41.75025881608148", "60.54049313655263", "Xorazm, Yangibozor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Urganch shahri")) {//41.547992018041974, 60.618264246685676
                    sendMessage(user.getChatId().toString(), setApi("41.547992018041974", "60.618264246685676", "Xorazm, Urganch shahri"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Urganch tumani")) {//41.61625437893366, 60.533593007029786
                    sendMessage(user.getChatId().toString(), setApi("41.61625437893366", "60.533593007029786", "Xorazm, Urganch tumani"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Xazorasp")) {//41.30448683950074, 61.09745692690238
                    sendMessage(user.getChatId().toString(), setApi("41.30448683950074", "61.09745692690238", "Xorazm, Xazorasp"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Xiva")) {//41.39184570391674, 60.34725590732044
                    sendMessage(user.getChatId().toString(), setApi("41.39184570391674", "60.34725590732044", "Xorazm, Xiva"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Xonqa")) {//41.481393767464844, 60.829570881655584
                    sendMessage(user.getChatId().toString(), setApi("41.481393767464844", "60.829570881655584", "Xorazm, Xonqa"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Yangiariq")) {// 41.346206916399666, 60.54804614851794
                    sendMessage(user.getChatId().toString(), setApi("41.346206916399666", "60.54804614851794", "Xorazm, Yangiariq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt_navoiy)) {
                if (data.equals("Navoiy shahri")) {//40.10166507561083, 65.3575344176788
                    sendMessage(user.getChatId().toString(), setApi("40.10166507561083", "65.3575344176788", "Navoiy, Navoiy shahri"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Karmana")) {//40.14372322026956, 65.35467198347476
                    sendMessage(user.getChatId().toString(), setApi("40.14372322026956", "65.35467198347476", "Navoiy, Karmana"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Konimex")) {// 40.277684998973186, 65.14210146382663
                    sendMessage(user.getChatId().toString(), setApi("40.277684998973186", "65.14210146382663", "Navoiy, Konimex"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Navbahor")) {//40.232621293600054, 65.23269831157204
                    sendMessage(user.getChatId().toString(), setApi("40.232621293600054", "65.23269831157204", "Navoiy, Navbahor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Nurota")) {//40.61096195334514, 65.93817362794783
                    sendMessage(user.getChatId().toString(), setApi("40.61096195334514", "65.93817362794783", "Navoiy, Nurota"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qiziltepa")) {//39.8966432527515, 64.76528114807608
                    sendMessage(user.getChatId().toString(), setApi("39.8966432527515", "64.76528114807608", "Navoiy, Qiziltepa"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Tomdi")) {//42.29906147558487, 64.90150727134733
                    sendMessage(user.getChatId().toString(), setApi("42.29906147558487", "64.90150727134733", "Navoiy, Tomdi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Uchquduq")) {//42.44642264030942, 62.6945789709221
                    sendMessage(user.getChatId().toString(), setApi("42.44642264030942", "62.6945789709221", "Navoiy, Uchquduq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Xatirchi")) {//40.230069075732, 65.9409253080852
                    sendMessage(user.getChatId().toString(), setApi("40.230069075732", "65.9409253080852", "Navoiy, Xatirchi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Zarafshon")) {//41.574460147847816, 64.18331570807477
                    sendMessage(user.getChatId().toString(), setApi("41.574460147847816", "64.18331570807477", "Navoiy, Zarafshon"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt_qashqadaryo)) {
                if (data.equals("Qarshi shahri")) {//38.86191354410989, 65.78491933285783
                    sendMessage(user.getChatId().toString(), setApi("38.86191354410989", "65.78491933285783", "Qashqadaryo, Qarshi shahri"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qarshi tumani")) {//38.779267614177556, 65.75278333536036
                    sendMessage(user.getChatId().toString(), setApi("38.779267614177556", "65.75278333536036", "Qashqadaryo, Qarshi tumani"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Chiroqchi")) {//39.18112111196543, 66.25891591391694
                    sendMessage(user.getChatId().toString(), setApi("39.18112111196543", "66.25891591391694", "Qashqadaryo, Chiroqchi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Dehqonobod")) {//38.35730703486065, 66.47533103897435
                    sendMessage(user.getChatId().toString(), setApi("38.35730703486065", "66.47533103897435", "Qashqadaryo, Dehqonobod"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("G'uzor")) {//38.587010422831746, 66.04489530163235
                    sendMessage(user.getChatId().toString(), setApi("38.587010422831746", "66.04489530163235", "Qashqadaryo, G'uzor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Kasbi")) {//38.93540618287719, 65.43383657129233
                    sendMessage(user.getChatId().toString(), setApi("38.93540618287719", "65.43383657129233", "Qashqadaryo, KasbiKasbi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Kitob")) {// 39.2152414606226, 67.03598426370185
                    sendMessage(user.getChatId().toString(), setApi("39.2152414606226", "67.03598426370185", "Qashqadaryo, Kitob"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Koson")) {// 39.16770611275984, 65.77183802497865
                    sendMessage(user.getChatId().toString(), setApi("39.16770611275984", "65.77183802497865", "Qashqadaryo, Koson"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Mirishkor")) {// 38.874194821533756, 64.92111490671182
                    sendMessage(user.getChatId().toString(), setApi("38.874194821533756", "64.92111490671182", "Qashqadaryo, Mirishkor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Muborak")) {//39.30420398425692, 65.25997593377225
                    sendMessage(user.getChatId().toString(), setApi("39.30420398425692", "65.25997593377225", "Qashqadaryo, Muborak"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Nishon")) {//38.53608390327857, 65.48400423357076
                    sendMessage(user.getChatId().toString(), setApi("38.53608390327857", "65.48400423357076", "Qashqadaryo, Nishon"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Qamashi")) {// 38.75660026948466, 66.60185133772104
                    sendMessage(user.getChatId().toString(), setApi("38.75660026948466", "66.60185133772104", "Qashqadaryo, Qamashi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Shahrisabz")) {//38.99171024720661, 67.2009187750465
                    sendMessage(user.getChatId().toString(), setApi("38.99171024720661", "67.2009187750465", "Qashqadaryo, Shahrisabz"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("Yakkabog'")) {//38.90742740574835, 66.75665915180723
                    sendMessage(user.getChatId().toString(), setApi("38.90742740574835", "66.75665915180723", "Qashqadaryo, Yakkabog'"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOy(user.getChatId(), data);
                } else if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_lt_location);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl)) {
                if (data.equals("location")) {
                    sendMessage(user.getChatId().toString(), "(KRL)Iltimos o'zingiz joylashgan joy lakatsiyasini botga tashlang...");
                    userService.update(user, user.getId(), UserStep.select_lang_krl_location);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("andijon")) {
                    andijonTumansK(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_andijon);
                } else if (data.equals("toshkent")) {
                    getToshkentKrl(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_toshkent);
                } else if (data.equals("samarqand")) {
                    getsamarqandKrl(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_samarqand);
                } else if (data.equals("buxoro")) {
                    getBuxoroK(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_buxoro);
                } else if (data.equals("navoiy")) {
                    getNamvoiyK(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_navoiy);
                } else if (data.equals("namangan")) {
                    namanganTumansK(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_namangan);
                } else if (data.equals("fargona")) {
                    fagonaTumans(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_fargona);
                } else if (data.equals("jizzah")) {
                    setJizzahK(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_jizzah);
                } else if (data.equals("xorazm")) {
                    setXorazmK(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_xorazm);
                } else if (data.equals("qashqadaryo")) {
                    getQashqa(user.getChatId());
                    userService.update(user, user.getId(), UserStep.select_lang_krl_qashqadaryo);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_andijon)) {
                if (data.equals("Andijon shahri")) {//40.7865585435806, 72.31208083702941
                    sendMessage(user.getChatId().toString(), setApiK("40.7865585435806", "72.31208083702941", "Aндижон, Aндижон шаҳри"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Xonobod")) { //40.79989825348597, 72.98951560333182
                    sendMessage(user.getChatId().toString(), setApiK("40.454055968860445", "72.98951560333182", "Aндижон, Хонобод"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Asaka")) {// 40.6619355989725, 72.24428848141633
                    sendMessage(user.getChatId().toString(), setApiK("40.6619355989725", "72.24428848141633", "Aндижон, Aсака"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Baliqchi")) {//40.8681067477643, 71.90901428107483
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Bo'z")) { //40.67522368198782, 71.90202999274003
                    sendMessage(user.getChatId().toString(), setApiK("40.67522368198782", "71.90202999274003", "Aндижон, Бўз"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Buloqboshi")) { //40.61824097722169, 72.4589485266868
                    sendMessage(user.getChatId().toString(), setApiK("40.61824097722169", "72.4589485266868", "Aндижон, Булоқбоши"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Izboskan")) { //40.926914438296166, 72.23046114826215
                    sendMessage(user.getChatId().toString(), setApiK("40.926914438296166", "72.23046114826215", "Aндижон, Избоскан"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Jalolquduq")) {//40.73260859991856, 72.61228519306135
                    sendMessage(user.getChatId().toString(), setApiK("40.73260859991856", "72.61228519306135", "Aндижон, Жалолқудуқ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Marhamat")) { //40.51393917604782, 72.29981218880005
                    sendMessage(user.getChatId().toString(), setApiK("40.51393917604782", "72.29981218880005", "Aндижон, Марҳамат"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Oltinko'l")) {// 40.78257767662204, 72.15630346953014
                    sendMessage(user.getChatId().toString(), setApiK("40.78257767662204", "72.15630346953014", "Aндижон, Олтинкўл"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Paxtaobod")) { //40.977855409832266, 72.42257164080198
                    sendMessage(user.getChatId().toString(), setApiK("40.977855409832266", "72.42257164080198", "Aндижон, Пахтаобод"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Qo'rg'ontepa")) { // 40.737458816612616, 72.80489172467401
                    sendMessage(user.getChatId().toString(), setApiK("40.737458816612616", "72.80489172467401", "Aндижон, Қўрғонтепа"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Ulug'nor")) { //40.7812938834895, 71.65829940832162
                    sendMessage(user.getChatId().toString(), setApiK("40.7812938834895", "71.65829940832162", "Aндижон, Улуғнор"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Xo'jaobod")) { // 40.644265790848365, 72.59695277036937
                    sendMessage(user.getChatId().toString(), setApiK("40.644265790848365", "72.59695277036937", "Aндижон, Хўжаобод"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Shahrixon")) { //40.705658566969085, 72.04954603823948
                    sendMessage(user.getChatId().toString(), setApiK("40.705658566969085", "72.04954603823948", "Aндижон, Шаҳрихон"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_toshkent)) {

                if (data.equals("ToshknetSH")) { // 41.31862648082324, 69.26185247247427
                    sendMessage(user.getChatId().toString(), setApiK("41.31862648082324", "69.26185247247427", "Тошкент ,Тошкент Шаҳар"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("chilonzor")) { // 41.25966068034852, 69.17981683014095
                    sendMessage(user.getChatId().toString(), setApiK("41.25966068034852", "69.17981683014095", "Тошкент ,Чилонзор"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("quyiC")) { // 41.324249737732416, 69.27336159543128
                    sendMessage(user.getChatId().toString(), setApiK("41.324249737732416", "69.27336159543128", "Тошкент ,Қуйи Чирчиқ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("bostonliq")) { // 41.29962474317497, 69.24013777030345
                    sendMessage(user.getChatId().toString(), setApiK("41.29962474317497", "69.24013777030345", "Тошкент ,Бўстонлиқ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("yangiyol")) { // 41.106601409336605, 69.00099564918969
                    sendMessage(user.getChatId().toString(), setApiK("41.106601409336605", "69.00099564918969", "Тошкент ,Янгийўл"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("zangota")) { // 41.19119250475624, 69.14481581613583
                    sendMessage(user.getChatId().toString(), setApiK("41.19119250475624", "69.14481581613583", "Тошкент ,Зангиота"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("ohangaron")) { // 40.91797520041142, 69.63525266877876
                    sendMessage(user.getChatId().toString(), setApiK("40.91797520041142", "69.63525266877876", "Тошкент ,Оҳангарон"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("oqqq")) { // 40.88044288436734, 69.04266437650311
                    sendMessage(user.getChatId().toString(), setApiK("40.88044288436734", "69.04266437650311", "Тошкент ,Оққўрғон"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("ppp")) { // 41.297497902393545, 69.68451728729993
                    sendMessage(user.getChatId().toString(), setApiK("41.297497902393545", "69.68451728729993", "Тошкент ,Паркент"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("ortachirchiq")) { // 41.20590993388904, 69.27922461238849
                    sendMessage(user.getChatId().toString(), setApiK("41.20590993388904", "69.27922461238849", "Тошкент ,Ўрта Чирчиқ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("yuqorichirchiq")) { // 41.20911424680261, 69.49407488275496
                    sendMessage(user.getChatId().toString(), setApiK("41.20911424680261", "69.49407488275496", "Тошкент ,Юқори Чирчиқ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("bekobod")) { // 40.454055968860445, 69.1819780480247
                    sendMessage(user.getChatId().toString(), setApiK("40.454055968860445", "69.1819780480247", "Тошкент ,Бекобод"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_samarqand)) {
                if (data.equals("Samarqand shahri")) {//39.65329327101519, 66.95826234725476
                    sendMessage(user.getChatId().toString(), setApiK("39.65329327101519", "66.95826234725476", "Самарқанд, Самарқанд шаҳри"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Bulung'ur")) {//39.70791766177822, 67.28142857964608
                    sendMessage(user.getChatId().toString(), setApiK("39.70791766177822", "67.28142857964608", "Самарқанд, Булунғур"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Ishtixon")) {//39.98829349793204, 66.53144028860048
                    sendMessage(user.getChatId().toString(), setApiK("39.98829349793204", "66.53144028860048", "Самарқанд, Иштихон"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Jomboy")) {//39.76625238583371, 67.07779535428304
                    sendMessage(user.getChatId().toString(), setApiK("39.76625238583371", "67.07779535428304", "Самарқанд, Жомбой"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Kattaqo'rg'on tumani")) { // 39.981981100516265, 66.2896029199572
                    sendMessage(user.getChatId().toString(), setApiK("39.981981100516265", "66.2896029199572", "Самарқанд, Каттақўрғон шаҳри"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Kattaqo'rg'on shahri")) { // 39.8993149735457, 66.2633149577346
                    sendMessage(user.getChatId().toString(), setApiK("39.8993149735457", "66.2633149577346", "Самарқанд, Каттақўрғон тумани"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Narpay")) {//39.92524679769102, 65.98627826494945
                    sendMessage(user.getChatId().toString(), setApiK("39.92524679769102", "65.98627826494945", "Самарқанд, Нарпай"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Nurobod")) {//39.57127098105206, 66.02799000748902
                    sendMessage(user.getChatId().toString(), setApiK("39.57127098105206", "66.02799000748902", "Самарқанд, Нуробод"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Oqdaryo")) {//39.839873784601465, 66.73834694617673
                    sendMessage(user.getChatId().toString(), setApiK("39.839873784601465", "66.73834694617673", "Самарқанд, Оқдарё"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Past darg'om")) { // 39.704352292734065, 66.62242053041332
                    sendMessage(user.getChatId().toString(), setApiK("39.704352292734065", "66.62242053041332", "Самарқанд, Паст дарғом"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Urgut")) {//39.447002526177904, 67.17173665735707
                    sendMessage(user.getChatId().toString(), setApiK("39.447002526177904", "67.17173665735707", "Самарқанд, Ургут"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Paxtachi")) {//39.888515759299004, 65.6089318735053
                    sendMessage(user.getChatId().toString(), setApiK("39.888515759299004", "65.6089318735053", "Самарқанд, Пахтачи"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Poyariq")) {//40.05104556455984, 66.85419779064077
                    sendMessage(user.getChatId().toString(), setApiK("40.05104556455984", "66.85419779064077", "Самарқанд, Пояриқ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Samarqand tumani")) { //39.59818617036431, 66.90471762188967
                    sendMessage(user.getChatId().toString(), setApiK("39.59818617036431", "66.90471762188967", "Самарқанд, Самарқанд тумани"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Toyloq")) { //39.56442359774873, 67.14018598905683
                    sendMessage(user.getChatId().toString(), setApiK("39.56442359774873", "67.14018598905683", "Самарқанд, Тойлоқ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Qo'shrabot")) { //40.38655726843597, 66.5100175021684
                    sendMessage(user.getChatId().toString(), setApiK("40.38655726843597", "66.5100175021684", "Самарқанд, Қўшработ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_buxoro)) {
                if (data.equals("Buxoro shahri")) {//39.77672367865153, 64.4210785732946
                    sendMessage(user.getChatId().toString(), setApiK("39.77672367865153", "64.4210785732946", "Бухоро, Бухоро шаҳри"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Buxoro tumani")) { //39.63566090876281, 64.30230294221505
                    sendMessage(user.getChatId().toString(), setApiK("39.63566090876281", "64.30230294221505", "Бухоро, Бухоро тумани"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("G'ijduvon")) {//40.601906240839995, 64.71053498588797
                    sendMessage(user.getChatId().toString(), setApiK("40.601906240839995", "64.71053498588797", "Бухоро, Ғиждувон"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Jondor")) {//39.971219891973426, 63.5714954269693
                    sendMessage(user.getChatId().toString(), setApiK("39.971219891973426", "63.5714954269693", "Бухоро, Жондор"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Kogon shahri")) { //39.72355224686954, 64.55132893267286
                    sendMessage(user.getChatId().toString(), setApiK("39.72355224686954", "64.55132893267286", "Бухоро, Когон шаҳри"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Kogon tumani")) { //39.69809076622412, 64.53702710674044
                    sendMessage(user.getChatId().toString(), setApiK("39.69809076622412", "64.53702710674044", "Бухоро, Когон тумани"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Olot")) { //39.281691000170674, 64.03768298578096
                    sendMessage(user.getChatId().toString(), setApiK("39.281691000170674", "64.03768298578096", "Бухоро, Олот"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Peshku")) {//40.73303621917936, 63.21959281775061
                    sendMessage(user.getChatId().toString(), setApiK("40.73303621917936", "63.21959281775061", "Бухоро, Пешку"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Qorako'l")) { //39.936970007976, 62.941145138407805
                    sendMessage(user.getChatId().toString(), setApiK("39.936970007976", "62.941145138407805", "Бухоро, Қоракўл"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Qorovulbozor")) {//39.46527348718642, 64.67070586334809
                    sendMessage(user.getChatId().toString(), setApiK("39.46527348718642", "64.67070586334809", "Бухоро, Қоровулбозор"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Romitan")) { //40.731071302974954, 62.530718928951856
                    sendMessage(user.getChatId().toString(), setApiK("40.731071302974954", "62.530718928951856", "Бухоро, Ромитан"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Shofirkon")) { // 40.12540773474785, 64.48270729594604
                    sendMessage(user.getChatId().toString(), setApiK("40.12540773474785", "64.48270729594604", "Бухоро, Шофиркон"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Vobkent")) { //40.15547517789773, 64.53244365200904
                    sendMessage(user.getChatId().toString(), setApiK("40.15547517789773", "64.53244365200904", "Бухоро, Вобкент"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_navoiy)) {
                if (data.equals("Navoiy shahri")) {//40.10166507561083, 65.3575344176788
                    sendMessage(user.getChatId().toString(), setApiK("40.10166507561083", "65.3575344176788", "Навоий, Навоий шаҳри"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Karmana")) {//40.14372322026956, 65.35467198347476
                    sendMessage(user.getChatId().toString(), setApiK("40.14372322026956", "65.35467198347476", "Навоий, Кармана"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Konimex")) {// 40.277684998973186, 65.14210146382663
                    sendMessage(user.getChatId().toString(), setApiK("40.277684998973186", "65.14210146382663", "Навоий, Конимех"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Navbahor")) {//40.232621293600054, 65.23269831157204
                    sendMessage(user.getChatId().toString(), setApiK("40.232621293600054", "65.23269831157204", "Навоий, Навбаҳор"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Nurota")) {//40.61096195334514, 65.93817362794783
                    sendKunOyKrla(user.getChatId(), data);
                    sendMessage(user.getChatId().toString(), setApiK("40.61096195334514", "65.93817362794783", "Навоий, Нурота"));
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Qiziltepa")) {//39.8966432527515, 64.76528114807608
                    sendMessage(user.getChatId().toString(), setApiK("39.8966432527515", "64.76528114807608", "Навоий, Қизилтепа"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Tomdi")) {//42.29906147558487, 64.90150727134733
                    sendMessage(user.getChatId().toString(), setApiK("42.29906147558487", "64.90150727134733", "Навоий, Томди"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
                if (data.equals("Uchquduq")) {//42.44642264030942, 62.6945789709221
                    sendMessage(user.getChatId().toString(), setApiK("42.44642264030942", "62.6945789709221", "Навоий, Учқудуқ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
                if (data.equals("Xatirchi")) {//40.230069075732, 65.9409253080852
                    sendMessage(user.getChatId().toString(), setApiK("40.230069075732", "65.9409253080852", "Навоий, Хатирчи"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
                if (data.equals("Zarafshon")) {//41.574460147847816, 64.18331570807477
                    sendMessage(user.getChatId().toString(), setApiK("41.574460147847816", "64.18331570807477", "Навоий, Зарафшон"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_namangan)) {
                if (data.equals("Chust")) { // 41.00615643758215, 71.23771796120751
                    sendMessage(user.getChatId().toString(), setApiK("40.999931", "71.236137", "Наманган, Чуст"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Namangan")) { // Namangan shahar
                    sendMessage(user.getChatId().toString(), setApiK("41.007162", "71.643712", "Наманган, Наманган шаҳар"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Kosonsoy")) { // Kosonsoy shahar
                    sendKunOyKrla(user.getChatId(), data);
                    sendMessage(user.getChatId().toString(), setApiK("41.19608642817571", "71.52652970907708", "Наманган, Косонсой"));
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Chortoq")) { // Chortoq shahar 41.06368431981446, 71.8230565658223
                    sendKunOyKrla(user.getChatId(), data);
                    sendMessage(user.getChatId().toString(), setApiK("41.06368431981446", "71.8230565658223", "Наманган, Чортоқ"));
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Norin")) { // Namangan shahar 40.940509509996495, 72.00654567901067
                    sendMessage(user.getChatId().toString(), setApiK("40.940509509996495", "72.00654567901067", "Наманган, Норин"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Mingbuloq")) { // Namangan shahar 40.78917645308457, 71.38875951462421
                    sendMessage(user.getChatId().toString(), setApiK("40.78917645308457", "71.38875951462421", "Наманган, Мингбулоқ"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Pop")) { // Namangan shahar 41.0364117324324, 70.75487013756214
                    sendMessage(user.getChatId().toString(), setApiK("41.0364117324324", "70.75487013756214", "Наманган, Поп"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("To'raqo'rg'on")) { // Namangan shahar 40.99316007632018, 71.51626227933346
                    sendMessage(user.getChatId().toString(), setApiK("40.99316007632018", "71.51626227933346", "Наманган, Тўрақўрғон"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Uchqo'rg'on")) { // Namangan shahar 41.12068842248576, 72.08711447153537
                    sendMessage(user.getChatId().toString(), setApiK("41.12068842248576", "72.08711447153537", "Наманган, Учқўрғон"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("uychi")) { // Namangan shahar 41.06721589284562, 71.92292974977094
                    sendMessage(user.getChatId().toString(), setApiK("71.92292974977094", "71.92292974977094", "Наманган, Уйчи"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("yangiqorgon")) { // Namangan shahar 41.198968289144275, 71.71703917988339
                    sendKunOyKrla(user.getChatId(), data);
                    sendMessage(user.getChatId().toString(), setApiK("41.198968289144275", "71.71703917988339", "Наманган, Янгиқўргон"));
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_fargona)) {
                if (data.equals("Farg‘ona shahar")) { //40.37949214005235, 71.78153222147638
                    sendKunOyKrla(user.getChatId(), data);
                    sendMessage(user.getChatId().toString(), setApiK("40.37949214005235", "71.78153222147638", "Фарғона, Фарғона шаҳар"));
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Uchko‘prik")) { //40.53267491097347, 71.02892566146784
                    sendMessage(user.getChatId().toString(), setApiK("40.53267491097347", "71.02892566146784", "Фарғона, Учкўприк"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("So‘x")) { //39.968927659480165, 71.12908694248515
                    sendMessage(user.getChatId().toString(), setApiK("39.968927659480165", "71.12908694248515", "Фарғона, Сўх"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Yozyovon")) { //40.62131813678478, 71.6480687836224
                    sendMessage(user.getChatId().toString(), setApiK("40.62131813678478", "71.6480687836224", "Фарғона, Ёзёвон"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Quva")) { //40.524573367810504, 72.06257627873157
                    sendKunOyKrla(user.getChatId(), data);
                    sendMessage(user.getChatId().toString(), setApiK("40.524573367810504", "72.06257627873157", "Фарғона, Қува"));
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Qo‘qon")) { //40.524621966621304, 70.93356479162965
                    sendMessage(user.getChatId().toString(), setApiK("40.524621966621304", "70.93356479162965", "Фарғона, Қўқон"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Beshariq")) { //40.39690886265036, 70.57359975866977
                    sendMessage(user.getChatId().toString(), setApiK("40.39690886265036", "70.57359975866977", "Фарғона, Бешариқ"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Quvasoy")) { //
                    sendMessage(user.getChatId().toString(), setApiK("40.30222", "71.97444", "Фарғона, Қувасой"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Farg‘ona")) { //40.320714145540286, 71.70209565835899
                    sendMessage(user.getChatId().toString(), setApiK("40.320714145540286", "71.70209565835899", "Фарғона, Фарғона"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Toshloq")) { //40.56269866588281, 71.84044642257277
                    sendMessage(user.getChatId().toString(), setApiK("40.56269866588281", "71.84044642257277", "Фарғона, Тошлоқ"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("O‘zbekiston")) { //40.330297796679396, 70.85868054864669
                    sendMessage(user.getChatId().toString(), setApiK("40.330297796679396", "70.85868054864669", "Фарғона, Ўзбекистон"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Qo‘shtepa")) { //40.53692345413236, 71.6415020647065
                    sendMessage(user.getChatId().toString(), setApiK("40.53692345413236", "71.6415020647065", "Фарғона, Қўштепа"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Marg‘ilon")) { //40.47635685369382, 71.71252435127899
                    sendMessage(user.getChatId().toString(), setApiK("40.47635685369382", "71.71252435127899", "Фарғона, Марғилон"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Buvayda")) { //40.645693110093404, 71.10204505839337
                    sendMessage(user.getChatId().toString(), setApiK("40.645693110093404", "71.10204505839337", "Фарғона, Бувайда"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Dang‘ara")) { //40.58139710281787, 70.91477380795894
                    sendMessage(user.getChatId().toString(), setApiK("40.58139710281787", "70.91477380795894", "Фарғона, Данғара"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Oltiariq")) { //40.39370084174377, 71.48670380912914
                    sendMessage(user.getChatId().toString(), setApiK("40.39370084174377", "71.48670380912914", "Фарғона, Олтиариқ"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Rishton")) { //40.36410679785719, 71.27208639645364
                    sendMessage(user.getChatId().toString(), setApiK("40.36410679785719", "71.27208639645364", "Фарғона, Риштон"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Furqat")) { //40.51673390388502, 70.71177286893743
                    sendMessage(user.getChatId().toString(), setApiK("40.51673390388502", "70.71177286893743", "Фарғона, Фурқат"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Bag‘dod")) { //40.49387968871484, 71.20366862141532
                    sendMessage(user.getChatId().toString(), setApiK("40.49387968871484", "71.20366862141532", "Фарғона, Бағдод"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_jizzah)) {
                if (data.equals("Jizzax shahri")) {//40.11977007458388, 67.85232343283624
                    sendMessage(user.getChatId().toString(), setApiK("40.11977007458388", "67.85232343283624", "Жиззах, Жиззах шаҳри"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Arnasoy")) { //40.59901232957254, 67.79358090175431
                    sendMessage(user.getChatId().toString(), setApiK("40.59901232957254", "67.79358090175431", "Жиззах, Aрнасой"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Baxmal")) {//39.78225382213002, 67.7250016175458
                    sendMessage(user.getChatId().toString(), setApiK("39.78225382213002", "67.7250016175458", "Жиззах, Бахмал"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Forish")) {//40.70901562393393, 67.18181520202268
                    sendMessage(user.getChatId().toString(), setApiK("40.70901562393393", "67.18181520202268", "Жиззах, Фориш"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("G'allaorol")) { //40.10205495202923, 67.32846413281408
                    sendMessage(user.getChatId().toString(), setApiK("40.10205495202923", "67.32846413281408", "Жиззах, Ғаллаорол"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Do'stlik")) { //40.58181976770775, 68.04548305263552
                    sendMessage(user.getChatId().toString(), setApiK("40.58181976770775", "68.04548305263552", "Жиззах, Дўстлик"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Jizzax tumani")) { //40.11455358141378, 67.82473708142044
                    sendMessage(user.getChatId().toString(), setApiK("40.11455358141378", "67.82473708142044", "Жиззах, Жиззах тумани"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Mirzacho'l")) {//40.724412215707, 68.06118184566003
                    sendMessage(user.getChatId().toString(), setApiK("40.724412215707", "68.06118184566003", "Жиззах, Мирзачўл"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Paxtakor")) { //40.35950221136383, 68.00475878330718
                    sendMessage(user.getChatId().toString(), setApiK("40.35950221136383", "68.00475878330718", "Жиззах, Пахтакор"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Yangiobod")) {//39.99243484998208, 68.74527110184545
                    sendMessage(user.getChatId().toString(), setApiK("39.99243484998208", "68.74527110184545", "Жиззах, Янгиобод"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Zafarobod")) { //40.41330581212753, 67.76451866402623
                    sendMessage(user.getChatId().toString(), setApiK("40.41330581212753", "67.76451866402623", "Жиззах, Зафаробод"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Zarband")) { //40.189983986463055, 68.11455808514548
                    sendMessage(user.getChatId().toString(), setApiK("40.189983986463055", "68.11455808514548", "Жиззах, Зарбанд"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Zomin")) { //39.88553183671697, 68.3643022291486
                    sendMessage(user.getChatId().toString(), setApiK("39.88553183671697", "68.3643022291486", "Жиззах, Зомин"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_xorazm)) {
                if (data.equals("Bog'ot")) {//41.33744153309746, 60.87694940015311
                    sendMessage(user.getChatId().toString(), setApiK("41.33744153309746", "60.87694940015311", "Хоразм, Боғот"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Gurlan")) {//41.90536583933732, 60.37982747138814
                    sendMessage(user.getChatId().toString(), setApiK("41.90536583933732", "60.37982747138814", "Хоразм, Гурлан"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Shovot")) {//41.710201238586826, 60.27012648475865
                    sendMessage(user.getChatId().toString(), setApiK("41.710201238586826", "60.27012648475865", "Хоразм, Шовот"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Qo'shko'pir")) {//41.526982196908484, 60.269577660413084
                    sendMessage(user.getChatId().toString(), setApiK("41.526982196908484", "60.269577660413084", "Хоразм, Қўшкўпир "));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Yangibozor")) {// 41.75025881608148, 60.54049313655263
                    sendMessage(user.getChatId().toString(), setApiK("41.75025881608148", "60.54049313655263", "Хоразм, Янгибозор "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Urganch shahri")) {//41.547992018041974, 60.618264246685676
                    sendMessage(user.getChatId().toString(), setApiK("41.547992018041974", "60.618264246685676", "Хоразм, Урганч шаҳри"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Urganch tumani")) {//41.61625437893366, 60.533593007029786
                    sendMessage(user.getChatId().toString(), setApiK("41.61625437893366", "60.533593007029786", "Хоразм, Урганч тумани"));
                    sendKunOyKrla(user.getChatId(), data);
                    userService.updateData(user, user.getId(), data);
                } else if (data.equals("Xazorasp")) {//41.30448683950074, 61.09745692690238
                    sendMessage(user.getChatId().toString(), setApiK("41.30448683950074", "61.09745692690238", "Хоразм, Хазорасп "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Xiva")) {//41.39184570391674, 60.34725590732044
                    sendMessage(user.getChatId().toString(), setApiK("41.39184570391674", "60.34725590732044", "Хоразм, Хива "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Xonqa")) {//41.481393767464844, 60.829570881655584
                    sendMessage(user.getChatId().toString(), setApiK("41.481393767464844", "60.829570881655584", "Хоразм, Хонқа"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Yangiariq")) {// 41.346206916399666, 60.54804614851794
                    sendMessage(user.getChatId().toString(), setApiK("41.346206916399666", "60.54804614851794", "Хоразм, Янгиариқ"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_lt_navoiy)) {
                if (data.equals("Navoiy shahri")) {//40.10166507561083, 65.3575344176788
                    sendMessage(user.getChatId().toString(), setApi("40.10166507561083", "65.3575344176788", "Navoiy, Navoiy shahri"));
                    userService.updateData(user, user.getId(), data);

                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Karmana")) {//40.14372322026956, 65.35467198347476
                    sendMessage(user.getChatId().toString(), setApi("40.14372322026956", "65.35467198347476", "Navoiy, Karmana"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Konimex")) {// 40.277684998973186, 65.14210146382663
                    sendMessage(user.getChatId().toString(), setApi("40.277684998973186", "65.14210146382663", "Navoiy, Konimex"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Navbahor")) {//40.232621293600054, 65.23269831157204
                    sendMessage(user.getChatId().toString(), setApi("40.232621293600054", "65.23269831157204", "Navoiy, Navbahor"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Nurota")) {//40.61096195334514, 65.93817362794783
                    sendMessage(user.getChatId().toString(), setApi("40.61096195334514", "65.93817362794783", "Navoiy, Nurota"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Qiziltepa")) {//39.8966432527515, 64.76528114807608
                    sendMessage(user.getChatId().toString(), setApi("39.8966432527515", "64.76528114807608", "Navoiy, Qiziltepa"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Tomdi")) {//42.29906147558487, 64.90150727134733
                    sendMessage(user.getChatId().toString(), setApi("42.29906147558487", "64.90150727134733", "Navoiy, Tomdi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Uchquduq")) {//42.44642264030942, 62.6945789709221
                    sendMessage(user.getChatId().toString(), setApi("42.44642264030942", "62.6945789709221", "Navoiy, Uchquduq"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Xatirchi")) {//40.230069075732, 65.9409253080852
                    sendMessage(user.getChatId().toString(), setApi("40.230069075732", "65.9409253080852", "Navoiy, Xatirchi"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Zarafshon")) {//41.574460147847816, 64.18331570807477
                    sendMessage(user.getChatId().toString(), setApi("41.574460147847816", "64.18331570807477", "Navoiy, Zarafshon"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
            } else if (user.getStep().equals(UserStep.select_lang_krl_qashqadaryo)) {
                if (data.equals("Qarshi shahri")) {//38.86191354410989, 65.78491933285783
                    sendMessage(user.getChatId().toString(), setApiK("38.86191354410989", "65.78491933285783", "Қашқадарё, Қарши шаҳри "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Qarshi tumani")) {//38.779267614177556, 65.75278333536036
                    sendMessage(user.getChatId().toString(), setApiK("38.779267614177556", "65.75278333536036", "Қашқадарё, Қарши тумани"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Chiroqchi")) {//39.18112111196543, 66.25891591391694
                    sendMessage(user.getChatId().toString(), setApiK("39.18112111196543", "66.25891591391694", "Қашқадарё, Чироқчи "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Dehqonobod")) {//38.35730703486065, 66.47533103897435
                    sendMessage(user.getChatId().toString(), setApiK("38.35730703486065", "66.47533103897435", "Қашқадарё, Деҳқонобод"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("G'uzor")) {//38.587010422831746, 66.04489530163235
                    sendMessage(user.getChatId().toString(), setApiK("38.587010422831746", "66.04489530163235", "Қашқадарё, Ғузор "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Kasbi")) {//38.93540618287719, 65.43383657129233
                    sendMessage(user.getChatId().toString(), setApiK("38.93540618287719", "65.43383657129233", "Қашқадарё, Касби "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Kitob")) {// 39.2152414606226, 67.03598426370185
                    sendMessage(user.getChatId().toString(), setApiK("39.2152414606226", "67.03598426370185", "Қашқадарё, Китоб "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Koson")) {// 39.16770611275984, 65.77183802497865
                    sendMessage(user.getChatId().toString(), setApiK("39.16770611275984", "65.77183802497865", "Қашқадарё, Косон "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Mirishkor")) {// 38.874194821533756, 64.92111490671182
                    sendMessage(user.getChatId().toString(), setApiK("38.874194821533756", "64.92111490671182", "Қашқадарё, Миришкор "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Muborak")) {//39.30420398425692, 65.25997593377225
                    sendMessage(user.getChatId().toString(), setApiK("39.30420398425692", "65.25997593377225", "Қашқадарё, Муборак "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Nishon")) {//38.53608390327857, 65.48400423357076
                    sendMessage(user.getChatId().toString(), setApiK("38.53608390327857", "65.48400423357076", "Қашқадарё, Нишон "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Qamashi")) {// 38.75660026948466, 66.60185133772104
                    sendMessage(user.getChatId().toString(), setApiK("38.75660026948466", "66.60185133772104", "Қашқадарё, Қамаши "));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Shahrisabz")) {//38.99171024720661, 67.2009187750465
                    sendMessage(user.getChatId().toString(), setApiK("38.99171024720661", "67.2009187750465", "Қашқадарё, Шаҳрисабз"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                } else if (data.equals("Yakkabog'")) {//38.90742740574835, 66.75665915180723
                    sendMessage(user.getChatId().toString(), setApiK("38.90742740574835", "66.75665915180723", "Қашқадарё, Яккабог"));
                    userService.updateData(user, user.getId(), data);
                    sendKunOyKrla(user.getChatId(), data);
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    private SendMessage sendMessage(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        return sendMessage;
    }

    public void sendInlineBtn(Long chatId) {
        try {
            execute(btn.sendInlineKeyBoardMessage(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendRegionBtn(Long chatId) {
        try {
            execute(regionBtn.getRegionBtn(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public void namanganTumans(Long chatId) {
        try {
            execute(namangan.namanganTumans(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void namanganTumansK(Long chatId) {
        try {
            execute(namangan.namanganTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void setJizzahK(Long chatId) {
        try {
            execute(jizzah.namanganTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void setXorazmK(Long chatId) {
        try {
            execute(xorazm.namanganTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void getToshkentKrl(Long chatId) {
        try {
            execute(toshkent.namanganTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void getBuxoroK(Long chatId) {
        try {
            execute(buxoro.namanganTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void getNamvoiyK(Long chatId) {
        try {
            execute(navoiy.navoiyTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void getsamarqandKrl(Long chatId) {
        try {
            execute(samarqand.namanganTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void getQashqa(Long chatId) {
        try {
            execute(qashqadaryo.namanganTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void andijonTumans(Long chatId) {
        try {
            execute(andijon.namanganTumans(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void fagonaTumans(Long chatId) {
        try {
            execute(fagona.namanganTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void andijonTumansK(Long chatId) {
        try {
            execute(andijon.namanganTumansK(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearBtn(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup(new ReplyKeyboardMarkup());
        sendMessage.setChatId(chatId);
        sendMessage.setText("ddd");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public String setApi(String lat, String lng, String text) {
        return prayTime.main(Double.parseDouble(lat), Double.parseDouble(lng), text);
    }

    public String setApiK(String lat, String lng, String text) {
        return prayTime.mainK(Double.parseDouble(lat), Double.parseDouble(lng), text);
    }

    public void sendKunOy(Long chatId, String data) {
        try {
            execute(regionBtn.kunOy(chatId, data));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendKunOyKrl(Long chatId, String data) {
        try {
            execute(regionBtn.kunOyKrl(chatId, data));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void MuvaffaqiyatliKrl(Long chatId) {
        try {
            execute(regionBtn.MuvaffaqiyatliKrl(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void MuvaffaqiyatliLt(Long chatId) {
        try {
            execute(regionBtn.MuvaffaqiyatliLt(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendKunOyKrla(Long chatId, String data) {
        try {
            execute(regionBtn.kunOyKrl(chatId, data));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void getRegionInline(Long id) {
        try {
            execute(btn.sendInlineKeyBoardRegion(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineKeyBoardRegion(Long id) throws TelegramApiException {
        execute(namangan.namanganTumans(id));
    }

    private void sendInlineKeyBoardRegionK(Long id) {
        try {
            execute(btn.sendInlineKeyBoardRegionK(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineToshknet(Long id) {
        try {
            execute(toshkent.namanganTumans(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void getRegionBtnKrl(Long id) {
        try {
            execute(regionBtn.getRegionBtnKrl(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineQashqadaryo(Long id) {
        try {
            execute(qashqadaryo.namanganTumans(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineFargona(Long id) {
        try {
            execute(fagona.namanganTumans(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineBuxoro(Long id) {
        try {
            execute(buxoro.namanganTumans(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineNavoiy(Long id) {
        try {
            execute(navoiy.navoiyTumans(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineXorazm(Long id) {
        try {
            execute(xorazm.namanganTumans(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineSamarqand(Long id) {
        try {
            execute(samarqand.namanganTumans(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineJizzah(Long id) {
        try {
            execute(jizzah.namanganTumans(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void tilniOzgartirish(Long id) {
        try {
            execute(btn.tilniOzgartirish(id));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
