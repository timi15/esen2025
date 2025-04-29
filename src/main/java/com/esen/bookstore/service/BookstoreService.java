package com.esen.bookstore.service;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.repository.BookstoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookstoreService {

    private final BookstoreRepository bookstoreRepository;

    public List<Bookstore> findAll(){
        return bookstoreRepository.findAll();
    }

    public void save(Bookstore bookstore){
        bookstoreRepository.save(bookstore);
    }

    public void delete(Long id){
        if(!bookstoreRepository.existsById(id)){
            throw new IllegalArgumentException("Cannot find book wiht id "+ id);
        }
        var bookstore = bookstoreRepository.findById(id).get();
        bookstoreRepository.delete(bookstore);
    }

    public void removeBookFromInventories(Book book) {
        bookstoreRepository.findAll()
                .forEach(bookstore -> {
                    bookstore.getInventory().remove(book);
                    bookstoreRepository.save(bookstore);
                });
    }

}
