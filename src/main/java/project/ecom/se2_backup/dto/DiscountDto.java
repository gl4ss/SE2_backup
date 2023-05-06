package project.ecom.se2_backup.dto;

import jakarta.validation.constraints.NotNull;

public class DiscountDto {
    private Long id;
    private @NotNull int discount;
    private @NotNull boolean isDiscount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isDiscount() {
        if(this.discount != 0){
            return true;
        }
        return false;
    }

}
