package entities;

import java.util.Objects;

public class Book {
    private String productName;
    private String author;
    private String price;
    private boolean bestsellerStatus;

    public Book(String productName, String author, String price, boolean bestsellerStatus){
        this.productName = productName;
        this.author = author;
        this.price = price;
        this.bestsellerStatus = bestsellerStatus;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return bestsellerStatus == that.bestsellerStatus && Objects.equals(productName, that.productName) && Objects.equals(author, that.author) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, author, price, bestsellerStatus);
    }

    @Override
    public String toString() {
        return String.format("|"+productName+"|"+author+"|"+price+"|"+bestsellerStatus+"|"+"\n");
    }
}
