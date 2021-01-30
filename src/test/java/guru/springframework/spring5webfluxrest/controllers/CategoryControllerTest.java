package guru.springframework.spring5webfluxrest.controllers;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static guru.springframework.spring5webfluxrest.controllers.CategoryController.CATEGORY_BASE_URL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

public class CategoryControllerTest {

    WebTestClient webTestClient;
    @Mock
    CategoryRepository categoryRepository;
    CategoryController categoryController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryController = new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void listAllCategories() {
        given(categoryRepository.findAll()).willReturn(Flux.just(new Category(), new Category(), new Category()));
        webTestClient
                .get()
                .uri(CATEGORY_BASE_URL)
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(3);
    }

    @Test
    public void getCategoryByID() {
        given(categoryRepository.findById(anyString())).willReturn(Mono.just(Category.builder().description("Foo").id("123").build()));

        webTestClient
                .get()
                .uri(CATEGORY_BASE_URL + "/123")
                .exchange()
                .expectBody(Category.class);
    }

    @Test
    public void createCategory() {

        given(categoryRepository.saveAll(any(Publisher.class))).willReturn(Flux.just(new Category()));

        Mono<Category> categoryMono = Mono.just(new Category());

        webTestClient
                .post()
                .uri(CATEGORY_BASE_URL)
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void updateCategory() {

        given(categoryRepository.save(any(Category.class))).willReturn(Mono.just(new Category()));

        Mono<Category> categoryMono = Mono.just(new Category());

        webTestClient
                .put()
                .uri(CATEGORY_BASE_URL + "/123")
                .body(categoryMono, Category.class)
                .exchange()
                .expectStatus()
                .isOk();





    }
}