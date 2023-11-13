package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.loan_history.UserLoanHistory;
import com.group.libraryapp.domain.loan_history.UserLoanHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {

    private final BookRepository repository;

    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;


    public BookService(BookRepository repository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.repository = repository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(BookCreateRequest request){
        repository.save(new Book(request.getName()));

    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 1. 책 정보 가져옴
        Book book = repository.findBookByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        // 2. 대출기록 정보를 확인해서 대출중인지 확인한다
        // 3. 만약에 대출중이라면 예외 발생
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("진작 대출되어 있는 책입니다.");
        }
        // 4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        // 5. 유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장
        userLoanHistoryRepository.save(new UserLoanHistory(user.getId(), book.getName()));
    }
}
