package com.jung.Ssave.bookmark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jung.Ssave.bookmark.domain.BookMark;
import com.jung.Ssave.bookmark.dto.MyBookMarkView;
import com.jung.Ssave.bookmark.repository.BookMarkRepository;
import com.jung.Ssave.ssave.service.AladdinItemService;

@Service
public class BookMarkService {

	private BookMarkRepository bookMarkRepository;
	private AladdinItemService aladdinItemService;
	
	public BookMarkService(BookMarkRepository bookMarkRepository, AladdinItemService aladdinItemService) {
		
		this.bookMarkRepository = bookMarkRepository;
		this.aladdinItemService = aladdinItemService;
		
	}
	
	public BookMark addBookMark(int userId
								, String isbn13) {
		
		String title = aladdinItemService.detailAladin(isbn13, userId).getItem().get(0).getTitle();
		String cover = aladdinItemService.detailAladin(isbn13, userId).getItem().get(0).getCover();
		int priceSales = aladdinItemService.detailAladin(isbn13, userId).getItem().get(0).getPriceSales();
		String link = aladdinItemService.detailAladin(isbn13, userId).getItem().get(0).getLink();
		String author = aladdinItemService.detailAladin(isbn13, userId).getItem().get(0).getAuthor();
		
		BookMark bookMark = BookMark.builder()
									.userId(userId)
									.isbn13(isbn13)
									.title(title)
									.cover(cover)
									.priceSales(priceSales)
									.link(link)
									.author(author)
									.build();
		
		BookMark result = bookMarkRepository.save(bookMark);
		
		return result;
		
	}
	
	public boolean removeBookMark(int userId, String isbn13) {
		
		Optional<BookMark> optionalBookMark =  bookMarkRepository.findByIsbn13AndUserId(isbn13, userId);
		BookMark bookMark = optionalBookMark.orElse(null);
		
		if(bookMark != null) {
			
			bookMarkRepository.delete(bookMark);
			return true;
		
		}else {
		
			return false;
		
		}
		
	}
	
	public List<MyBookMarkView> myBookMark(int userId){
		
		List<MyBookMarkView> myBookMarkList = new ArrayList<>();
		
		// dto를 만들어줄라면 bookmark리스트 전체를 쏵 훝어야하니
		List<BookMark> bookMarkList = bookMarkRepository.findAllByOrderByIdDesc();
		
		for(BookMark bookMark : bookMarkList) {
			
			MyBookMarkView myBookMarkView = MyBookMarkView.builder()
														  .isbn13(bookMark.getIsbn13())
														  .userId(bookMark.getUserId())
														  .title(bookMark.getTitle())
														  .cover(bookMark.getCover())
														  .priceSales(bookMark.getPriceSales())
														  .link(bookMark.getLink())
														  .author(bookMark.getAuthor())
														  .build();
		
			myBookMarkList.add(myBookMarkView);
		}
		
		return myBookMarkList;
		
	}
	
	
	
	public boolean isBookMarkByUserIdAndItemId(int userId, String isbn13) {
		
		int count = bookMarkRepository.countByUserIdAndIsbn13(userId, isbn13);
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
}
