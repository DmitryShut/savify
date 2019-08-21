package com.shut.savify.controller;

import com.shut.savify.entity.Text;
import com.shut.savify.service.TextService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/texts")
public class TextController {

    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping
    public String getText(@RequestParam(name = "key") String key) {
        return textService.getText(key);
    }

    @PostMapping
    public String createText(@RequestBody Text text){
        return textService.createText(text);
    }
}
