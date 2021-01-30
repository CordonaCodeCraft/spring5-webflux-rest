package guru.springframework.spring5webfluxrest.services;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import reactor.core.publisher.Mono;

public interface VendorService {
    Mono<Vendor> addVendor(Vendor vendor);
    Mono<Long> vendorsCount();
}
