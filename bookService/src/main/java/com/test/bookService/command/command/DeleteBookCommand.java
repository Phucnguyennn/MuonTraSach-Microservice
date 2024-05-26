package com.test.bookService.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteBookCommand {
	@TargetAggregateIdentifier
	private String BookId;
	
	public String getBookId() {
		return BookId;
	}
	
	public void setBookId(String bookId) {
		this.BookId = bookId;
	}

	public DeleteBookCommand(String bookId) {
		super();
		BookId = bookId;
	}
	
}
