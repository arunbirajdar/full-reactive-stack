package com.thepracticaldeveloper.reactiveweb.controller;

import com.thepracticaldeveloper.reactiveweb.domain.Quote;
import com.thepracticaldeveloper.reactiveweb.repository.QuoteMongoBlockingRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DeleteQuoteController {

    private static final String QUOTE_ID = "quoteId";

    private QuoteMongoBlockingRepository quoteMongoBlockingRepository;

    public DeleteQuoteController(QuoteMongoBlockingRepository quoteMongoBlockingRepository) {
        this.quoteMongoBlockingRepository = quoteMongoBlockingRepository;
    }

    @DeleteMapping("/quotes-delete/{quoteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuote(@PathVariable(name = QUOTE_ID) String quoteId) {
        Optional<Quote> quote = quoteMongoBlockingRepository.findById(quoteId);
        quote.ifPresent(quoteMongoBlockingRepository::delete);
    }
}
