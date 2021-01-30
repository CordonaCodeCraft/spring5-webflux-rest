package guru.springframework.spring5webfluxrest.controllers;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static guru.springframework.spring5webfluxrest.controllers.VendorController.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class VendorControllerTest {

    @Mock
    VendorRepository vendorRepository;
    VendorController vendorController;
    WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();

    }

    @Test
    public void getAllVendors() {
        given(vendorRepository.findAll()).willReturn(Flux.just(new Vendor(), new Vendor(), new Vendor()));

        webTestClient
                .get()
                .uri(VENDORS_BASE_URL)
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(3);
    }

    @Test
    public void getVendorById() {
        given(vendorRepository.findById(anyString())).willReturn(Mono.just(new Vendor()));

        webTestClient
                .get()
                .uri(VENDORS_BASE_URL + "/123")
                .exchange()
                .expectBody(Vendor.class);
    }

    @Test
    public void createNewVendor() {
        given(vendorRepository.saveAll(any(Publisher.class))).willReturn(Flux.just(new Vendor()));

        Mono<Vendor> vendorMono = Mono.just(new Vendor());

        webTestClient
                .post()
                .uri(VENDORS_BASE_URL)
                .body(vendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void updateVendor() {
        given(vendorRepository.save(any(Vendor.class))).willReturn(Mono.just(new Vendor()));

        Mono<Vendor> vendorMono = Mono.just(new Vendor());

        webTestClient
                .put()
                .uri(VENDORS_BASE_URL +"/123")
                .body(vendorMono,Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();


    }
}