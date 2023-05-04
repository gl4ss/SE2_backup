package project.ecom.se2_backup.dto;

import java.util.List;

public class CartDto {

    private List<CartItemDto> cartItemDtos;
    private double totals;

    public CartDto() {
    }


    public List<CartItemDto> getCartItemDtos() {
        return cartItemDtos;
    }

    public void setCartItemDtos(List<CartItemDto> cartItemDtos) {
        this.cartItemDtos = cartItemDtos;
    }

    public double getTotal() {
        return totals;
    }

    public void setTotal(double totals) {
        this.totals = totals;
    }
}
