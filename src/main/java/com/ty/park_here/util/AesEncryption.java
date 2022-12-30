package com.ty.park_here.util;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import lombok.SneakyThrows;

@Configuration
public class AesEncryption implements AttributeConverter<Object, String>{

	@Value("${aes.encryption.key}")
	private String encryptionKey="";
	private String encryptionCipher="AES";
	
	private Key key;
	private Cipher cipher;
	
	private Key getKey() {
		if(key==null) {
			key=new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
		}
		return key;
	}

	private Cipher getCipher() throws GeneralSecurityException {
		if(cipher==null) {
			cipher= Cipher.getInstance(encryptionCipher);
		}
		return cipher;
	}
	private void initCipher(int encryption) throws InvalidKeyException, GeneralSecurityException  {
		getCipher().init(encryption, getKey());
		
	}

	@SneakyThrows
	@Override
	public String convertToDatabaseColumn(Object attribute) {
		if(attribute==null)
			return null;
		initCipher(Cipher.ENCRYPT_MODE);
		byte[] bytes=SerializationUtils.serialize(attribute);
		return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
	}

	@SneakyThrows
	@Override
	public Object convertToEntityAttribute(String dbData) {
		if(dbData==null)
			return null;
		initCipher(Cipher.DECRYPT_MODE);
		byte[] bytes= getCipher().doFinal(Base64.getDecoder().decode(dbData));
		return SerializationUtils.deserialize(bytes);
	}

}
