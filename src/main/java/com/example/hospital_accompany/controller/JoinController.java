package com.example.hospital_accompany.controller;

import com.example.hospital_accompany.dto.JoinDto;
import com.example.hospital_accompany.service.JoinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDto joinDto) {

        System.out.println(joinDto.getUsername());
        joinService.joinProcess(joinDto);

        return "ok";
    }
}