package accenture.com.coffeetek.Model;

import android.graphics.Bitmap;

import java.util.List;

public class Product {

    private String Title;
    private Bitmap Image;
    private int Size;
    private int Sugar;
    private List<String> Additional;
    private int Quantity = 0;

    public Product() {
    }

    public Product(String title, Bitmap image, int size, int sugar, List<String> additional, int quantity) {
        Title = title;
        Image = image;
        Size = size;
        Sugar = sugar;
        Additional = additional;
        Quantity = quantity;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public int getSugar() {
        return Sugar;
    }

    public void setSugar(int sugar) {
        Sugar = sugar;
    }

    public List<String> getAdditional() {
        return Additional;
    }

    public void setAdditional(List<String> additional) {
        Additional = additional;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
