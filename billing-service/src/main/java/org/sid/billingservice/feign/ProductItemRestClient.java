package org.sid.billingservice.feign;

import org.sid.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductItemRestClient {
    @GetMapping("/products/{id}?projection=fullProduct")
    Product getProductById(@PathVariable("id") Long id);
    @GetMapping("/products?projection=fullProduct")
    PagedModel<Product> findAll(/*@RequestParam(value = "page") int page, @RequestParam(value = "size") int size*/);
}
