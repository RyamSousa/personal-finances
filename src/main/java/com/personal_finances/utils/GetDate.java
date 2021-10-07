package com.personal_finances.utils;

import java.time.LocalDateTime;


public class GetDate {

    public static LocalDateTime getDateSystem() {
        return LocalDateTime.now();
    }

    public static String extractMonthAndYear(String date){
        String[] elements = date.split("-");

        return "/"+elements[1]+"/"+elements[2];
    }
}
