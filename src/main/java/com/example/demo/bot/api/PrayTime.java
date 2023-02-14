package com.example.demo.bot.api;
//--------------------- Copyright Block ----------------------
/*

PrayTime.java: Prayer Times Calculator (ver 1.0)
Copyright (C) 2007-2010 PrayTimes.org

Java Code By: Hussain Ali Khan
Original JS Code By: Hamid Zarrabi-Zadeh

License: GNU LGPL v3.0

TERMS OF USE:
	Permission is granted to use this code, with or
	without modification, in any website or application
	provided that credit is given to the original work
	with a link back to PrayTimes.org.

This program is distributed in the hope that it will
be useful, but WITHOUT ANY WARRANTY.

PLEASE DO NOT REMOVE THIS COPYRIGHT BLOCK.

*/


import com.batoulapps.adhan.*;
import com.batoulapps.adhan.data.DateComponents;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PrayTime {

    public String main(double latitude, double longitude, String text) {

        Coordinates coordinates = new Coordinates(latitude, longitude);
        DateComponents date = DateComponents.from(new Date());
        CalculationParameters params =
                CalculationMethod.MUSLIM_WORLD_LEAGUE.getParameters();
        params.madhab = Madhab.HANAFI;
        params.adjustments.fajr = 2;
        PrayerTimes prayerTimes = new PrayerTimes(coordinates, date, params);

        SunnahTimes sunnahTimes = new SunnahTimes(prayerTimes);
        System.out.println(prayerTimes.asr);

        String asr = String.valueOf(prayerTimes.asr.toString().substring(prayerTimes.asr.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String fajr = String.valueOf(prayerTimes.fajr.toString().substring(prayerTimes.fajr.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String sunrise = String.valueOf(prayerTimes.sunrise.toString().substring(prayerTimes.sunrise.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String maghrib = String.valueOf(prayerTimes.maghrib.toString().substring(prayerTimes.maghrib.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String isha = String.valueOf(prayerTimes.isha.toString().substring(prayerTimes.isha.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String dhuhr = String.valueOf(prayerTimes.dhuhr.toString().substring(prayerTimes.dhuhr.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));

        System.out.println(asr);
        Date now = new Date();
        String time =
                " \uD83D\uDDFA️ " + text + "\n " +
                        "\uD83D\uDDD3️" + (now.getYear() + 1900) + "-yil " + now.getDate() + "-" + getMonth(now.getMonth()) + " \n \n" +
                        " \uD83C\uDF04Tong: 0" + prayerTimes.fajr.getHours() + ":" + fajr + "\n " +
                        "\uD83C\uDF24️Quyosh: 0" + prayerTimes.sunrise.getHours() + ":" + sunrise + "\n" +
                        " ☀️Peshin: " + prayerTimes.dhuhr.getHours() + ":" + dhuhr + "\n" +
                        "  ⛅️Asr: " + prayerTimes.asr.getHours() + ":" + asr + "\n" +
                        "  \uD83C\uDF05Shom: " + prayerTimes.maghrib.getHours() + ":" + maghrib + "\n" +
                        "  \uD83C\uDF19Xufton: " + prayerTimes.isha.getHours() + ":" + isha + "\n \n @orginal_namoz_bot";
        return time;
    }

    public String mainK(double latitude, double longitude, String text) {

        Coordinates coordinates = new Coordinates(latitude, longitude);
        DateComponents date = DateComponents.from(new Date());
        CalculationParameters params =
                CalculationMethod.MUSLIM_WORLD_LEAGUE.getParameters();
        params.madhab = Madhab.HANAFI;
        params.adjustments.fajr = 2;
        PrayerTimes prayerTimes = new PrayerTimes(coordinates, date, params);

        SunnahTimes sunnahTimes = new SunnahTimes(prayerTimes);
        System.out.println(prayerTimes.asr);

        String asr = String.valueOf(prayerTimes.asr.toString().substring(prayerTimes.asr.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String fajr = String.valueOf(prayerTimes.fajr.toString().substring(prayerTimes.fajr.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String sunrise = String.valueOf(prayerTimes.sunrise.toString().substring(prayerTimes.sunrise.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String maghrib = String.valueOf(prayerTimes.maghrib.toString().substring(prayerTimes.maghrib.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String isha = String.valueOf(prayerTimes.isha.toString().substring(prayerTimes.isha.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));
        String dhuhr = String.valueOf(prayerTimes.dhuhr.toString().substring(prayerTimes.dhuhr.toString().indexOf(":") + 1, prayerTimes.asr.toString().lastIndexOf(":")));

        System.out.println(asr);
        Date now = new Date();
        String time =//🌤
                "  \uD83D\uDDFA️ " + text + "\n " +
                        "\uD83D\uDDD3️" + (now.getYear() + 1900) + "-йил " + now.getDate() + "-" + getMonthK(now.getMonth()) + " \n \n" +
                        " \uD83C\uDF04Тонг: 0" + prayerTimes.fajr.getHours() + ":" + fajr + "\n " +
                        "⛅️Қуёш: 0" + prayerTimes.sunrise.getHours() + ":" + sunrise + "\n" +
                        " ☀️️️Пешин: " + prayerTimes.dhuhr.getHours() + ":" + dhuhr + "\n" +
                        "  \uD83C\uDF24Aср: " + prayerTimes.asr.getHours() + ":" + asr + "\n" +
                        "  \uD83C\uDF05Шом: " + prayerTimes.maghrib.getHours() + ":" + maghrib + "\n" +
                        "  \uD83C\uDF19Хуфтон: " + prayerTimes.isha.getHours() + ":" + isha + "\n \n @orginal_namoz_bot";
        return time;
    }


    public String getMonth(int n) {
        switch (n) {
            case 0: {
                return "Yanvar";
            }
            case 1: {
                return "Ferval";
            }
            case 2: {
                return "Mart";
            }
            case 3: {
                return "Aprel";
            }
            case 4: {
                return "May";
            }
            case 5: {
                return "Iyun";
            }
            case 6: {
                return "Iyul";
            }
            case 7: {
                return "Avgust";
            }
            case 8: {
                return "Sentabr";
            }
            case 9: {
                return "Oktabr";
            }
            case 10: {
                return "Noyabr";
            }
            case 11: {
                return "Dekabr";
            }
        }
        return null;
    }

    public String getMonthK(int n) {
        switch (n) {
            case 0: {
                return "Йанвар";
            }
            case 1: {
                return "Феврал";
            }
            case 2: {
                return "Март";
            }
            case 3: {
                return "Апрел";
            }
            case 4: {
                return "Май";
            }
            case 5: {
                return "Ийун";
            }
            case 6: {
                return "Ийул";
            }
            case 7: {
                return "Август";
            }
            case 8: {
                return "Сентабр";
            }
            case 9: {
                return "Октабр";
            }
            case 10: {
                return "Ноябр";
            }
            case 11: {
                return "Декабр";
            }
        }
        return null;
    }
}
