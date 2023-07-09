package dev.jittakan.models;

import dev.jittakan.models.entities.Product;

import java.util.Objects;

public class CartItem {
    private int amount;
    private Product product;

    public CartItem(int amount, Product product) {
        this.amount = amount;
        this.product = product;
    }

    public int getQuantity() {
        return amount;
    }

    public void setQuantity(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getImport(){
        return amount * product.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product.getId(), cartItem.product.getId())
                && Objects.equals(product.getName(), cartItem.product.getName());
    }
}
