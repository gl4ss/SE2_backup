package project.ecom.se2_backup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ecom.se2_backup.common.ApiResponse;
import project.ecom.se2_backup.dto.AddToCartDto;
import project.ecom.se2_backup.dto.CartDto;
import project.ecom.se2_backup.model.User;
import project.ecom.se2_backup.service.CartService;
import project.ecom.se2_backup.service.ProductService;
import project.ecom.se2_backup.service.UserService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;


    @GetMapping("/addtocart")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam(value = "userId") Long userId ) throws Exception {

        User user = userService.findUserById(userId);


        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added"), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<CartDto> getCartItem(@RequestParam(value = "userId") Long userId) throws Exception {
        User user = userService.findUserById(userId);

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);

    }

    @DeleteMapping(value = "delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable(value = "cartItemId") Long cartItemId,
                                                      @RequestParam(value = "userId") Long userId) throws Exception {
        User user = userService.findUserById(userId);

        cartService.deleteCartItem(cartItemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "Deleted"), HttpStatus.OK);
    }
}
