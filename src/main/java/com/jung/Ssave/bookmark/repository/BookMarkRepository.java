package com.jung.Ssave.bookmark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Ssave.bookmark.domain.BookMark;

public interface BookMarkRepository extends JpaRepository<BookMark, Integer> {

	public int countByUserIdAndIsbn13(int userId, String isbn13);
	
	public Optional<BookMark> findByIsbn13AndUserId(String isbn13, int userId);
	
	public List<BookMark> findAllByOrderByIdDesc();
}
