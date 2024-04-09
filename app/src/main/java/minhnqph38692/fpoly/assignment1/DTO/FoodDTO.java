package minhnqph38692.fpoly.assignment1.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class FoodDTO implements Serializable {
    String _id;
    private  String title;
    private ArrayList<String> image;

    private Double price;
    private String description;

    private  String createdAt,updatedAt;
    private int NumberInCart;

    public FoodDTO(int numberInCart) {
        NumberInCart = numberInCart;
    }





    public FoodDTO() {
    }

    public FoodDTO(String _id, String title, ArrayList<String> image, Double price, String description, String createdAt, String updatedAt, int numberInCart) {
        this._id = _id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        NumberInCart = numberInCart;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberInCart() {
        return NumberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        NumberInCart = numberInCart;
    }
}
