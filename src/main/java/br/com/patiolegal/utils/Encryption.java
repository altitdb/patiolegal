package br.com.patiolegal.utils;

import java.security.MessageDigest;

import br.com.patiolegal.exception.PasswordToEncryptException;

public class Encryption {

	private static final String ALGORITHM = "SHA-256";
	private static final String CHARSET = "UTF-8";

	public String toEncrypt(String pass) {

		MessageDigest algorithm;
		StringBuilder hexPass = new StringBuilder();
		String hashPass;

		try {

			algorithm = MessageDigest.getInstance(ALGORITHM);
			byte passDigest[] = algorithm.digest(pass.getBytes(CHARSET));

			for (byte b : passDigest) {
				hexPass.append(String.format("%02X", 0xFF & b));
			}

			hashPass = hexPass.toString();

			return hashPass;
		} catch (Exception e) {
			throw new PasswordToEncryptException(e);
		}

	}

}
