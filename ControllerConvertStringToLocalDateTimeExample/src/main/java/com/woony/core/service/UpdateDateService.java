package com.woony.core.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woony.core.domain.date.DateEntity;
import com.woony.core.domain.date.DateEntityRepository;
import com.woony.core.domain.exception.DateResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UpdateDateService {
    private final Logger logger = LoggerFactory.getLogger(UpdateDateService.class);
    private final DateEntityRepository dateEntityRepository;
    private static final String DATETIME_PATTERN_BS_FETCH_DATE = "dd-MMM-yyyy hh:mm:ss a";

    private static final DateTimeFormatter df = new DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendPattern(DATETIME_PATTERN_BS_FETCH_DATE)
        .toFormatter(Locale.ENGLISH);

    public UpdateDateService(DateEntityRepository dateEntityRepository) {
        this.dateEntityRepository = dateEntityRepository;
    }

    @Transactional
    public void updateCurrentDate(long id, String dateAsString) {

        LocalDateTime ldt =  LocalDateTime.from(df.parse(dateAsString));
        LocalDateTime nowDate = LocalDateTime.now();

        if (ldt.isAfter(nowDate)) {
            logger.error("The fetched date {} is later than current date: {}", ldt, nowDate);
            throw new IllegalArgumentException("fetchDate is later than current Date.");
        }

        DateEntity dateEntity = dateEntityRepository.findById(id).orElseThrow(() -> new DateResourceNotFoundException("There's not entity."));
        dateEntity.setDateTime(ldt);
    }


}
