package com.test.bookService.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.bookService.command.data.Book;
import com.test.bookService.command.data.BookReponsitory;


@Component
public class BookEventsHandler {
	
	@Autowired
	private BookReponsitory bookReponsitory;
	
	@EventHandler
	public void on(BookCreatedEvent event) {
		Book book = new Book();
		BeanUtils.copyProperties(event, book);
		bookReponsitory.save(book);
	}
	@EventHandler
    public void on(BookUpdateEvent event) {
		 Book book = bookReponsitory.getById(event.getBookId());
	       book.setAuthor(event.getAuthor());
	       book.setName(event.getName());
	       book.setIsReady(event.getIsReady());
	        bookReponsitory.save(book);
    }
	@EventHandler
    public void on(BookDeleteEvent event) {
		
	        bookReponsitory.deleteById(event.getBookId());;
    }
	

}	
