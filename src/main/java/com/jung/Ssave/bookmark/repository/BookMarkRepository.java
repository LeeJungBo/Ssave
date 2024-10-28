package com.jung.Ssave.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Ssave.bookmark.domain.BookMark;

public interface BookMarkRepository extends JpaRepository<BookMark, Integer> {

}
