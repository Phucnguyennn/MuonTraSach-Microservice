package com.test.bookService.query.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.bookService.BookServiceApplication;
import com.test.bookService.query.model.BookReponseModel;
import com.test.bookService.query.queries.GetAllBooksQuery;
import com.test.bookService.query.queries.GetBookQuery;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {
	

	@Autowired 
	private  QueryGateway queryGateway;

private Logger logger =org.slf4j.LoggerFactory.getLogger(BookServiceApplication.class);

	@GetMapping("/{bookId}")
    public BookReponseModel getBookDetail(@PathVariable String bookId) {
        GetBookQuery getBooksQuery = new GetBookQuery();
        getBooksQuery.setBookId(bookId);

        BookReponseModel bookResponseModel =
        queryGateway.query(getBooksQuery,
                ResponseTypes.instanceOf(BookReponseModel.class))
                .join();

        return bookResponseModel;
    }
	@GetMapping
	public List<BookReponseModel> getAllBooks(){
		GetAllBooksQuery getAllBooksQuery = new GetAllBooksQuery();
		List<BookReponseModel> list = queryGateway.query(getAllBooksQuery, ResponseTypes.multipleInstancesOf(BookReponseModel.class))
				.join();
		logger.info("Danh sach Book "+list.toString());
		return list;
	}

}
