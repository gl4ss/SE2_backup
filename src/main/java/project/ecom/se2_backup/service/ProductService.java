package project.ecom.se2_backup.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.ecom.se2_backup.dto.DiscountDto;
import project.ecom.se2_backup.dto.ProductDto;
import project.ecom.se2_backup.model.Category;
import project.ecom.se2_backup.model.Discount;
import project.ecom.se2_backup.model.Product;
import project.ecom.se2_backup.repository.DiscountRepository;
import project.ecom.se2_backup.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DiscountRepository discountRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImgUrl(productDto.getImgUrl());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);
    }
    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImgUrl(product.getImgUrl());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());

        return productDto;
    }

    public List<ProductDto> getProducts() {
        List<Product> allProduct = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : allProduct){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public List<ProductDto> sortProducts(String sortBy) {
        List<Product> sortedProducts;
        if (sortBy.equals("category")) {
            sortedProducts = productRepository.sortProductsByCategory(sortBy);
        } else {
            sortedProducts = productRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
        }

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : sortedProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Long productId) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(!optionalProduct.isPresent()){
            throw new Exception("No product presents");
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImgUrl(productDto.getImgUrl());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }
    public void deleteProduct(ProductDto productDto, Long productId) throws Exception{
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
            throw new Exception("No product presents");
        }
        Product product = optionalProduct.get();
        productRepository.delete(product);
    }

    public List<ProductDto> getProductById(Long productId) throws Exception {
        Optional<Product> optionalProductById = productRepository.findById(productId);
        if(!optionalProductById.isPresent()){
            throw new Exception("No product presents");
        }
        List<Product> allProductById = (List<Product>) optionalProductById.get();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : allProductById){
            productDtos.add(getProductDto(product));
        }
        return productDtos;

    }

    public void addDiscount(Long productId, Discount discount) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(!optionalProduct.isPresent()){
            throw new Exception("No product presents");
        }
        Product product = optionalProduct.get();
        product.setDiscount(discount);
        productRepository.save(product);

    }
}
