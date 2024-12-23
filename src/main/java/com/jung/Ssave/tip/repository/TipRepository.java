package com.jung.Ssave.tip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Ssave.tip.domain.Tip;

public interface TipRepository extends JpaRepository<Tip, Integer>{

	public List<Tip> findByUserIdOrderByIdDesc(int userId);
	
	public Optional<Tip> findByUserIdAndId(int userId, int id);
	
}
