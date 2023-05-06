package project.ecom.se2_backup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name= "products")
public class Product {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private @NotNull String name;
     private @NotNull String imgUrl;
     private @NotNull String description;
     private @NotNull double price;

     @ManyToOne
     @JoinColumn(name="category_id")
     private Category category;

     @OneToOne
     @JoinColumn(name = "discount_id")
     private Discount discount;
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}

