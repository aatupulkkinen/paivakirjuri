package otp.model.encryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

public class EncryptionHandler {

    private final StandardPBEStringEncryptor encryptor;

    public EncryptionHandler(){
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setSaltGenerator(new ZeroSaltGenerator());
        encryptor.setPassword("auvonkurssi");
    }

    public String encrypt(String string){
        return encryptor.encrypt(string);
    }

    public String decrypt(String string){
        return encryptor.decrypt(string);
    }
}
