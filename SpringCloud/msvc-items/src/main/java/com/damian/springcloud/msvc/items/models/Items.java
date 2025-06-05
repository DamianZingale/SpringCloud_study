package com.damian.springcloud.msvc.items.models;
// actuaria como DTO

public class Items {
    private Product product;
    private int quantity;

    public Items(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Items() {
    }
// Getters and Setters
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return product.getPrice() * quantity;
    }
}
