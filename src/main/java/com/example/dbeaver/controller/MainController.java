package com.example.dbeaver.controller;

import com.example.dbeaver.dto.response.ResponseDTO;
import com.example.dbeaver.facade.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final Facade facade;
    @GetMapping("handle")
    public ResponseDTO handle() {
        return facade.getResponse();
    }
}
