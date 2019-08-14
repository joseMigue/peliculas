package com.jose.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.core.model.Authority;
import com.jose.core.repository.AuthorityRepository;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;
	public Authority buscarAuthority(String nombreAuthority) {
		return authorityRepository.findByAuthority(nombreAuthority);
	}
}
