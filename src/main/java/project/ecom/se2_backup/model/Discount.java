package project.ecom.se2_backup.model;

import jakarta.persistence.*;

@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int discount;

    private boolean isDiscount;

    public Discount() {
    }

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
