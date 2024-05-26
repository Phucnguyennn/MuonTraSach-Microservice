package com.test.bookService.command.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReponsitory extends JpaRepository<Book, String>{

}
