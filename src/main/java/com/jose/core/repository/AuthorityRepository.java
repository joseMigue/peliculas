package com.jose.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jose.core.model.Authority;
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer>{

	public Authority findByAuthority(String authority);
}
