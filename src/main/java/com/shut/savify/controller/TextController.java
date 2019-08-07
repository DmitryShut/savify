package com.shut.savify.controller;

import com.shut.savify.entity.Text;
import com.shut.savify.service.TextService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/texts")
public class TextController {

    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping(path = "/{key}")
    public ResponseEntity<Text> getText(@PathVariable String key){
        Optional<Text> text = textService.getText(key);
        return text.isPresent() ? ResponseEntity.ok(text.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity createText(@RequestBody Text text){
        return ResponseEntity.ok(textService.createText(text));
    }
}
