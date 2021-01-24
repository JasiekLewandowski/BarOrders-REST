package dev.lewandowski.ordersapp.service;

import dev.lewandowski.ordersapp.dto.ProductEntity;
import dev.lewandowski.ordersapp.dto.converter.ProductConverter;
import dev.lewandowski.ordersapp.model.Product;
import dev.lewandowski.ordersapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductConverter productConverter;

  public ProductEntity save(Product product) {
    return productRepository.save(productConverter.convert(product));
  }

  public List<Product> findAll(){
    List<ProductEntity> productEntities = productRepository.findAll();
    return productConverter.convert(productEntities);
  }
}
