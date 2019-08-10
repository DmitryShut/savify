package com.shut.savify.service;

import com.shut.savify.entity.Text;
import com.shut.savify.exception.NotFoundException;
import com.shut.savify.repository.TextRepository;
import com.shut.savify.util.CryptographyService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Optional;

@Service
public class TextService {
    private final TextRepository textRepository;

    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @SneakyThrows
    public String getText(String key) {
        PrivateKey pvt = CryptographyService.getPrivateKeyFromString(key.replace(' ','+'));
        PublicKey publicKey = CryptographyService.getPublicKey(pvt);
        String base64PublicKey = CryptographyService.hashPublicKey(publicKey);
        Optional<Text> base64EncodedText = textRepository.findById(base64PublicKey);
        if(base64EncodedText.isPresent()){
            return CryptographyService.decodeText(base64EncodedText.get().getText(), pvt);
        }else {
            throw new NotFoundException("Text with this key not found");
        }
    }

    @SneakyThrows
    public String createText(Text text) {
        KeyPair keyPair = CryptographyService.getKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = CryptographyService.getPublicKey(privateKey);
        String base64EncryptedTextString = CryptographyService.getEncryptedText(text.getText(), publicKey);
        String base64PublicKey = CryptographyService.hashPublicKey(publicKey);
        textRepository.save(new Text(base64PublicKey, base64EncryptedTextString));
        return CryptographyService.toStringPrivateKey(privateKey);
    }

}
