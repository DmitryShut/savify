package com.shut.savify.util;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class CryptographyService {

    @SneakyThrows
    public static String hashPublicKey(PublicKey publicKey){
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digest = messageDigest.digest(publicKey.getEncoded());
        return new String(Base64.getEncoder().encode(digest), StandardCharsets.UTF_8);
    }

    public static String toStringPrivateKey(PrivateKey privateKey){
        return new String(Base64.getEncoder().encode(privateKey.getEncoded()), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static String getEncryptedText(String text, PublicKey publicKey){
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedText = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(encryptedText), StandardCharsets.UTF_8);
    }

    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    @SneakyThrows
    public static String decodeText(String base64EncodedText, PrivateKey pvt){
        byte[] decodedEncrypted = Base64.getDecoder().decode(base64EncodedText);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, pvt);
        byte[] text = cipher.doFinal(decodedEncrypted);
        return new String(text);
    }

    @SneakyThrows
    public static PrivateKey getPrivateKeyFromString(String privateKey){
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey.getBytes(StandardCharsets.UTF_8)));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(ks);
    }

    public static PublicKey getPublicKey(PrivateKey key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPrivateCrtKey privk = (RSAPrivateCrtKey) key;
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privk.getModulus(), privk.getPublicExponent());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicKeySpec);
    }
}
