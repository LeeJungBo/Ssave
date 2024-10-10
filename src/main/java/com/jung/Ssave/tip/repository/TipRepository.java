package com.jung.Ssave.tip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Ssave.tip.domain.Tip;

public interface TipRepository extends JpaRepository<Tip, Integer>{

}
