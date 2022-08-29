package com.woony.core.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UpdateDateService {
    private final Logger logger = LoggerFactory.getLogger(UpdateDateService.class);
    private static final String DATETIME_PATTERN_BS_FETCH_DATE = "dd-MMM-yyyy hh:mm:ss a";

    private static final DateTimeFormatter df = new DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendPattern(DATETIME_PATTERN_BS_FETCH_DATE)
        .toFormatter(Locale.ENGLISH);

    public void updateCurrentDate(long id, String dateAsString) {

        LocalDateTime ldt =  LocalDateTime.from(df.parse(dateAsString));
        LocalDateTime nowDate = LocalDateTime.now();

        if (ldt.isAfter(nowDate)) {
            logger.error("The fetched date {} is later than current date: {}", ldt, nowDate);
            throw new IllegalArgumentException("fetchDate is later than current Date.");
        }

    }


}
