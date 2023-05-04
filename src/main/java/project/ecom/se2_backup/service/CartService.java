package project.ecom.se2_backup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ecom.se2_backup.dto.AddToCartDto;
import project.ecom.se2_backup.dto.CartDto;
import project.ecom.se2_backup.dto.CartItemDto;
import project.ecom.se2_backup.model.Cart;
import project.ecom.se2_backup.model.Product;
import project.ecom.se2_backup.model.User;
import project.ecom.se2_backup.repository.CartRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    public void addToCart(AddToCartDto addToCartDto, User user) throws Exception {
        Product product = (Product) productService.getProductById(addToCartDto.getProductId());

        if(product != null && user != null) {
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(addToCartDto.getQuantity());
            cart.setCreatedDate(new Date());

            cartRepository.save(cart);
        }


    }

    public CartDto listCartItems(User user) {
        List<Cart> carts = cartRepository.findAllByUserOrder(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        double totals =0;

        for(Cart cart : carts){
            CartItemDto cartItemDto = new CartItemDto(cart);
            totals += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemDto);
        }
        CartDto cartDto = new CartDto();
        cartDto.setTotal(totals);
        cartDto.setCartItemDtos(cartItems);
        return cartDto;
    }

    public void deleteCartItem(Long cartItemId, User user) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if(optionalCart.isEmpty()){
            throw new Exception("cart item is invalid");
        }
        Cart cart = optionalCart.get();
        if(cart.getUser() != user){
            throw new Exception("Cart item not found");
        }
        cartRepository.delete(cart);
    }
}
