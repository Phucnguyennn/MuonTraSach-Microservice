package com.test.bookService.query.projection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.bookService.command.data.Book;
import com.test.bookService.command.data.BookReponsitory;
import com.test.bookService.query.model.BookReponseModel;
import com.test.bookService.query.queries.GetAllBooksQuery;
import com.test.bookService.query.queries.GetBookQuery;

import jakarta.persistence.EntityNotFoundException;

@Component
public class BookProjection {
	@Autowired
	private BookReponsitory bookRepository;
	

//	 @QueryHandler
//	    public BookReponseModel handle(GetBookQuery getBooksQuery) {
//	        BookReponseModel model = new BookReponseModel();
//	        Optional<Book> optionalBook = bookRepository.findById(getBooksQuery.getBookId());
//
//	        if (optionalBook.isPresent()) {
//	            Book book = optionalBook.get();
//	            BeanUtils.copyProperties(book, model);
//	        } else {
//	            // Handle the case where the book is not found
//	            throw new EntityNotFoundException("Book not found with id: " + getBooksQuery.getBookId());
//	        }
//
//	        return model;
//	    }
	
	 @QueryHandler
	    public BookReponseModel handle(GetBookQuery getBooksQuery) {
		 BookReponseModel model = new BookReponseModel();
		 Book book = bookRepository.getById(getBooksQuery.getBookId());
	      BeanUtils.copyProperties(book, model);

	        return model;
	    }
	 @QueryHandler List<BookReponseModel> handle(GetAllBooksQuery getAllBooksQuery){
		 List<Book> listEntity = bookRepository.findAll();
		 List<BookReponseModel> listbook = new ArrayList<>();
		 listEntity.forEach(s -> {
			 BookReponseModel model = new BookReponseModel();
			 BeanUtils.copyProperties(s, model);
			 listbook.add(model);
		 });
		 return listbook;
	 }
//	 @QueryHandler
//	    public BookReponseCommonModel handle(GetDetailsBookQuery getDetailsBookQuery) {
//		 BookReponseCommonModel model = new BookReponseCommonModel();
//		 Book book = bookRepository.getById(getDetailsBookQuery.getBookId());
//	      BeanUtils.copyProperties(book, model);
//
//	        return model;
//	    }
}
