package com.pratamaycon.muxi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pratamaycon.muxi.domain.model.Terminal;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Integer> {

	Terminal findByLogic(Integer logic);
	
}
