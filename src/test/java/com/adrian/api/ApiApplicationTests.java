package com.adrian.api;

import com.adrian.api.controller.RepositoryController;
import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.service.impl.RepositoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class ApiApplicationTests {

	@InjectMocks
	private RepositoryController controller;
	@Mock
	private RepositoryServiceImpl service;
	WebTestClient client;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
		client = WebTestClient.bindToController(controller).build();
	}

	@Test
	public void should_accept_json_then_return_list_of_repository_DTOs_with_status_isOk_and_json_header() {
		client.get().uri("http://localhost:8080/repositories/adrian5235")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectAll(
						responseSpec -> responseSpec.expectStatus().isOk(),
						responseSpec -> responseSpec.expectHeader().contentType(MediaType.APPLICATION_JSON)
				)
				.expectBodyList(RepositoryDTO.class);
	}
}
