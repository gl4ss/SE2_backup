package project.ecom.se2_backup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.ecom.se2_backup.common.ApiResponse;
import project.ecom.se2_backup.dto.CartDto;
import project.ecom.se2_backup.service.CartService;
import project.ecom.se2_backup.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    public ResponseEntity<ApiResponse> addToCart(@RequestBody CartDto cartDto ){

    }
}
