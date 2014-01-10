package br.com.ebt.figh.web.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component()
public class  PasswordCustomEncoder implements PasswordEncoder {

	@Override
	public String encodePassword(String rawPass, Object salt) throws DataAccessException {
		MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("md5");
		return encoder.encodePassword(rawPass, salt);
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
		MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("md5");
		String passVerify = encoder.encodePassword(rawPass, salt);
		return encPass.equals(passVerify) ? true : false;
	}

}
