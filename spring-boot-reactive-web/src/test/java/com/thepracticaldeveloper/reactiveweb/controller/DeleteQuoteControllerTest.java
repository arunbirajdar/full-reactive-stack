package com.thepracticaldeveloper.reactiveweb.controller;

import com.thepracticaldeveloper.reactiveweb.domain.Quote;
import com.thepracticaldeveloper.reactiveweb.repository.QuoteMongoBlockingRepository;

import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class DeleteQuoteControllerTest {

    private final QuoteMongoBlockingRepository quoteMongoBlockingRepository = mock(QuoteMongoBlockingRepository.class);

    private final DeleteQuoteController deleteQuoteController = new DeleteQuoteController(quoteMongoBlockingRepository);

    @Test
    public void shouldDeleteQuoteWhenQuoteExistInDb() {
        // given
        String quoteId = "1";
        Quote quote = new Quote(quoteId, "mock-book", "Quote 1");

        // when
        when(quoteMongoBlockingRepository.findById(quoteId)).thenReturn(Optional.of(quote));

        deleteQuoteController.deleteQuote(quoteId);

        // then
        verify(quoteMongoBlockingRepository).delete(quote);

    }

    @Test
    public void shouldNotCallRepositoryToDeleteQuoteWhenQuoteNotExistInDb() {
        // given
        String quoteId = "1";

        // when
        when(quoteMongoBlockingRepository.findById(quoteId)).thenReturn(Optional.empty());

        deleteQuoteController.deleteQuote(quoteId);

        // then
        verify(quoteMongoBlockingRepository).findById(quoteId);
        verifyNoMoreInteractions(quoteMongoBlockingRepository);

    }

}
