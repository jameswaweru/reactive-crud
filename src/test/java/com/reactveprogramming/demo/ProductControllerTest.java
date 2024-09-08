package com.reactveprogramming.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@WebFluxTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setId(1L);
        sampleProduct.setName("Sample Product");
        sampleProduct.setPrice(100.0);
    }

    @Test
    void getAllProducts() {
        when(productService.getAllProducts()).thenReturn(Flux.just(sampleProduct));

        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .hasSize(1)
                .contains(sampleProduct);

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getProductById() {
        when(productService.getProductById(1L)).thenReturn(Mono.just(sampleProduct));

        webTestClient.get()
                .uri("/products/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(sampleProduct);

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void createProduct() {
        when(productService.saveProduct(any(Product.class))).thenReturn(Mono.just(sampleProduct));

        webTestClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sampleProduct)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(sampleProduct);

        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void updateProduct() {
        when(productService.saveProduct(any(Product.class))).thenReturn(Mono.just(sampleProduct));

        webTestClient.put()
                .uri("/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sampleProduct)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(sampleProduct);

        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void deleteProduct() {
        when(productService.deleteProduct(1L)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/products/{id}", 1L)
                .exchange()
                .expectStatus().isOk();

        verify(productService, times(1)).deleteProduct(1L);
    }
}
