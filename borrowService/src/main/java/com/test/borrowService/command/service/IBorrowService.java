package com.test.borrowService.command.service;

import com.test.borrowService.command.model.Message;

public interface IBorrowService {
	void sendMessage(Message message);
	String findIdBorrowing(String employeeId,String bookId);
}