package com.pali.eventgo.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatter {

    public LocalDate createdDateFormatter(LocalDateTime createdOn) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        String text = createdOn.format(dateTimeFormatter);
        return LocalDate.parse(text, dateTimeFormatter);
    }
}
