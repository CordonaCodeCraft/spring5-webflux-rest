package guru.springframework.spring5webfluxrest.services.impl;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import guru.springframework.spring5webfluxrest.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }


    @Override
    public Mono<Vendor> addVendor(Vendor vendor) {
        return this.vendorRepository.save(vendor);
    }

    @Override
    public Mono<Long> vendorsCount() {
        return vendorRepository.count();
    }
}
