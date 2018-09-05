package accenture.com.coffeetek.Model;

import android.graphics.Bitmap;

import java.util.List;

public class Product {

    private String Title;
    private Bitmap Image;
    private int Size;
    private int Sugar;
    private int Amount = 0;
    private int Additional_Cream;
    private int Additional_Chocolate;
    private int Additional_Cinnamon;
    private int Additional_Coffee;
    private int Additional_Milk;

    public Product() {
    }

    public Product(String title, Bitmap image, int size, int sugar, int amount, int additional_Cream, int additional_Chocolate, int additional_Cinnamon, int additional_Coffee, int additional_Milk) {
        Title = title;
        Image = image;
        Size = size;
        Sugar = sugar;
        Amount = amount;
        Additional_Cream = additional_Cream;
        Additional_Chocolate = additional_Chocolate;
        Additional_Cinnamon = additional_Cinnamon;
        Additional_Coffee = additional_Coffee;
        Additional_Milk = additional_Milk;
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

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getAdditional_Cream() {
        return Additional_Cream;
    }

    public void setAdditional_Cream(int additional_Cream) {
        Additional_Cream = additional_Cream;
    }

    public int getAdditional_Chocolate() {
        return Additional_Chocolate;
    }

    public void setAdditional_Chocolate(int additional_Chocolate) {
        Additional_Chocolate = additional_Chocolate;
    }

    public int getAdditional_Cinnamon() {
        return Additional_Cinnamon;
    }

    public void setAdditional_Cinnamon(int additional_Cinnamon) {
        Additional_Cinnamon = additional_Cinnamon;
    }

    public int getAdditional_Coffee() {
        return Additional_Coffee;
    }

    public void setAdditional_Coffee(int additional_Coffee) {
        Additional_Coffee = additional_Coffee;
    }

    public int getAdditional_Milk() {
        return Additional_Milk;
    }

    public void setAdditional_Milk(int additional_Milk) {
        Additional_Milk = additional_Milk;
    }
}
