package dev.lewandowski.ordersapp.controller;

import dev.lewandowski.ordersapp.dto.ProductEntity;
import dev.lewandowski.ordersapp.model.Product;
import dev.lewandowski.ordersapp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("/addProduct")
  public ProductEntity addProduct(@RequestBody Product product){
    log.info("Received product: " + product.getName() + " " + product.getPrice());
    return productService.save(product);
  }

  @GetMapping("/getAllProducts")
  public List<Product> showProducts(){
   log.info("Showing products");
    return productService.findAll();
  }
}
