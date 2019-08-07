package com.shut.savify.service;

import com.shut.savify.entity.Text;
import com.shut.savify.repository.TextRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TextService {
    private final TextRepository textRepository;
    private final PasswordEncoder passwordEncoder;

    public TextService(TextRepository textRepository, PasswordEncoder passwordEncoder) {
        this.textRepository = textRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Text> getText(String key){
        return textRepository.findById(key);
    }

    public Text createText(Text text){
        text.setKey(passwordEncoder.encode(text.getKey()));
        return textRepository.save(text);
    }

}
