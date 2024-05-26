package com.test.bookService.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateBookCommand {
	@TargetAggregateIdentifier
	private String BookId;
	private String name;
	private String author;
	private Boolean isReady;
	public String getBookId() {
		return BookId;
	}
	
	public CreateBookCommand(String BookId, String name, String author, Boolean isReady) {
		this.BookId = BookId;
		this.name = name;
		this.author = author;
		this.isReady = isReady;
	}
	public void setBookId(String bookId) {
		this.BookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Boolean getIsReady() {
		return isReady;
	}
	public void setIsReady(Boolean isReady) {
		this.isReady = isReady;
	}
}
