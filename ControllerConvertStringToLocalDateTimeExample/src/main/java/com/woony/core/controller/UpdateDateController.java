package com.woony.core.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woony.core.domain.request.UpdateDateRequestWithString;
import com.woony.core.service.UpdateDateService;

@RestController
@RequestMapping("/core")
public class UpdateDateController {

    private UpdateDateService updateDateService;

    public UpdateDateController(UpdateDateService updateDateService) {
        this.updateDateService = updateDateService;
    }
    // Case 1: String -> Date 변환 로직 포함한 기능 구현
    @PutMapping("/updateDate/{dateId}")
    public String updateDateFromString(@PathVariable long dateId, @RequestBody UpdateDateRequestWithString request) {
        String fetchDate = request.getDateAsString();
        updateDateService.updateCurrentDate(dateId, fetchDate);
        return "Success";
    }
}
