package com.shut.savify.service;

import com.shut.savify.entity.Text;
import com.shut.savify.repository.TextRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TextService {
    private final TextRepository textRepository;

    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public Optional<Text> getText(String key){
        return textRepository.findById(key);
    }

    public Text createText(Text text){
        return textRepository.save(text);
    }

}
