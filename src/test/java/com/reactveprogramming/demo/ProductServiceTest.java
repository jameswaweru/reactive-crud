package com.reactveprogramming.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(Flux.just(new Product(), new Product()));

        StepVerifier.create(productService.getAllProducts())
                .expectNextCount(2)
                .verifyComplete();

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Mono.just(product));

        StepVerifier.create(productService.getProductById(1L))
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void saveProduct() {
        Product product = new Product();
        product.setName("Test");

        when(productRepository.save(product)).thenReturn(Mono.just(product));

        StepVerifier.create(productService.saveProduct(product))
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteProduct() {
        when(productRepository.deleteById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(productService.deleteProduct(1L))
                .verifyComplete();

        verify(productRepository, times(1)).deleteById(1L);
    }
}
