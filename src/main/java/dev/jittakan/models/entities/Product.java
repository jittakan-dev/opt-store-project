package dev.jittakan.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private int price;
    private String sku;

    @Column(name = "registration_date")
    private LocalDate dateRegistration;

    public Product() {
    }

    public Product(Long id, String name, String type, int price) {
        this.id = id;
        this.name = name;
        Category category = new Category();
        category.setName(type);
        this.category = category;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getRegistrationDate() {
        return dateRegistration;
    }

    public void setRegistrationDate(LocalDate dateRegistration) {
        this.dateRegistration = dateRegistration;
    }
}
