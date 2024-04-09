package minhnqph38692.fpoly.assignment1.DTO;

import java.util.ArrayList;

public class BillDetailsDTO {
    String _id;
    private  String title;
    private ArrayList<String> image;

    private Double price;
    private int stock;
    private  Double total;
    private  String date;

    private  String createdAt,updatedAt;

    public BillDetailsDTO(String _id, String title, ArrayList<String> image, Double price, int stock, Double total, String date, String createdAt, String updatedAt) {
        this._id = _id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.total = total;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BillDetailsDTO() {
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
