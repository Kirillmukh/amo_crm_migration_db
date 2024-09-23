package com.example.dbeaver.controller;

import com.example.dbeaver.dto.response.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("handle")
    public ResponseDTO handle() {
        return null;
    }
}
