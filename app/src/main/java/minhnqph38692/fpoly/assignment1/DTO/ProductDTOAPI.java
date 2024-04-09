package minhnqph38692.fpoly.assignment1.DTO;

import java.util.ArrayList;

public class ProductDTOAPI {
    String _id;
    private  String title;
    private ArrayList<String> image;

    private Double price;
    private int stock;

    private  String createdAt,updatedAt;

    public ProductDTOAPI(String _id, String title, ArrayList<String> image, Double price, int stock, String createdAt, String updatedAt) {
        this._id = _id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductDTOAPI() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
}
