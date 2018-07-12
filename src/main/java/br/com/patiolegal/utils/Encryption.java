package br.com.patiolegal.utils;

import java.security.MessageDigest;

import org.jboss.logging.Logger;

import br.com.patiolegal.exception.PasswordEncryptException;

public class Encryption {

    private static final Logger LOG = Logger.getLogger(Encryption.class);
    private static final String ALGORITHM = "SHA-256";
    private static final String CHARSET = "UTF-8";

    public String toEncrypt(String pass) {

        StringBuilder hexPass = new StringBuilder();

        try {

            MessageDigest algorithm = MessageDigest.getInstance(ALGORITHM);
            byte passDigest[] = algorithm.digest(pass.getBytes(CHARSET));

            for (byte b : passDigest) {
                hexPass.append(String.format("%02X", 0xFF & b));
            }

            return String.valueOf(hexPass);
        } catch (Exception e) {
            LOG.error("Erro ao encriptar a senha",  e);
            throw new PasswordEncryptException();
        }

    }

}
