package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final VendorRepository vendorRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(VendorRepository vendorRepository, CategoryRepository categoryRepository) {
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count().block() == 0) {

            log.info("-------- Loading data --------");

            loadCategories();

            loadVendors();
        }
    }

    private void loadCategories() {

        Category first = Category.builder().description("First").build();
        Category second = Category.builder().description("Second").build();
        Category third = Category.builder().description("Third").build();

        categoryRepository.save(first).block();
        categoryRepository.save(second).block();
        categoryRepository.save(third).block();

        log.info(String.format("Added %d categories in database", categoryRepository.count().block()));

    }

    private void loadVendors() {

        Vendor first = Vendor.builder().firstName("Endios").lastName("Deloro").build();
        Vendor second = Vendor.builder().firstName("John").lastName("Thompson").build();
        Vendor third = Vendor.builder().firstName("Uncle").lastName("Bob").build();

        vendorRepository.save(first).block();
        vendorRepository.save(second).block();
        vendorRepository.save(third).block();

        log.info(String.format("Added %d vendors in database", vendorRepository.count().block()));

    }
}
