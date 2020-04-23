package com.thepracticaldeveloper.reactiveweb.controller;

import com.thepracticaldeveloper.reactiveweb.domain.Quote;
import com.thepracticaldeveloper.reactiveweb.repository.QuoteMongoBlockingRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteQuoteControllerIntegrationTest {

    @MockBean
    private QuoteMongoBlockingRepository quoteMongoBlockingRepository;

    @LocalServerPort
    private int serverPort;

    private RestTemplate restTemplate;

    private String endpoint;

    @Before
    public void setUp() {
        endpoint = "http://localhost:" + serverPort + "/quotes-delete/{quoteId}";
        restTemplate = new RestTemplate();
    }

    @Test
    public void shouldDeleteQuote() {
        String quoteId = "1";
        Quote quote = new Quote(quoteId, "mock-book", "Quote 1");

        // given
        given(quoteMongoBlockingRepository.findById(quoteId)).willReturn(Optional.of(quote));

        // when
        ResponseEntity<Void> responseEntity = restTemplate.exchange(endpoint,
                                                                    HttpMethod.DELETE,
                                                                    null,
                                                                    Void.class,
                                                                    quoteId);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
