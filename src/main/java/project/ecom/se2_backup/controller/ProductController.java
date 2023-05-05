package project.ecom.se2_backup.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ecom.se2_backup.common.ApiResponse;
import project.ecom.se2_backup.dto.ProductDto;
import project.ecom.se2_backup.model.Category;
import project.ecom.se2_backup.repository.CategoryRepository;
import project.ecom.se2_backup.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/list/")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/list/{productId}")
    public ResponseEntity<List<ProductDto>> getProductById(@PathVariable Long productId) throws Exception {
        List<ProductDto> products = productService.getProductById(productId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> sortProducts(@RequestParam String sortBy) {
        return new ResponseEntity<>(
                productService.sortProducts(sortBy),
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "added"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable(value ="productId") Long productId, @RequestBody ProductDto productDto) throws Exception{
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "category doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(new ApiResponse(true, "added"), HttpStatus.OK);
    }

    @PostMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable(value = "productId") Long productId, @RequestBody ProductDto productDto) throws Exception{

        productService.deleteProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "deleted"), HttpStatus.OK);
    }

}
