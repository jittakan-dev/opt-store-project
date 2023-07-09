package dev.jittakan.models;

import dev.jittakan.configs.ShoppingCart;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ShoppingCart
public class Cart implements Serializable {
    private List<CartItem> items;

    @Inject
    private transient Logger log;

    @PostConstruct
    public void initialize(){
        this.items = new ArrayList<>();
        log.info("Initializing the shopping cart!");
    }

    @PreDestroy
    public void destroy(){
        log.info("Destroying the shopping cart!");
    }

    public void addItemCart(CartItem cartItem){
        if(items.contains(cartItem)){
            Optional<CartItem> optionalItemCart = items.stream()
                    .filter(i -> i.equals(cartItem))
                    .findAny();
            if(optionalItemCart.isPresent()){
                CartItem i = optionalItemCart.get();
                i.setQuantity(i.getQuantity()+1);
            }
        } else {
            this.items.add(cartItem);
        }
    }

    public List<CartItem> getItems(){
        return items;
    }

    public int getTotal(){
        return items.stream().mapToInt(CartItem::getImport).sum();
    }

    public void removeProducts(List<String> productIds) {
        if (productIds != null) {
            productIds.forEach(this::removeProduct);
        }
    }

    public void removeProduct(String productId) {
        Optional<CartItem> product = findProduct(productId);
        product.ifPresent(cartItem -> items.remove(cartItem));
    }

    public void updateCantidad(String productId, int amount) {
        Optional<CartItem> product = findProduct(productId);
        product.ifPresent(cartItem -> cartItem.setQuantity(amount));
    }

    private Optional<CartItem> findProduct(String productId) {
        return  items.stream()
                .filter(cartItem -> productId.equals(Long.toString(cartItem.getProduct().getId())))
                .findAny();
    }
}
