package com.woony.core.controller;

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

    public String updateDateFromString(@RequestBody UpdateDateRequestWithString request) {
        String fetchDate = request.getDateAsString();
        long dateId = request.getId();
        updateDateService.updateCurrentDate(dateId, fetchDate);
        return "Success";
    }
}
