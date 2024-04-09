package minhnqph38692.fpoly.assignment1.DTO;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable {
    String _id, username,password,email,phone,birthday,name;
    String createAt,updateAt;
//    ArrayList<String> image;
String image;
    public UserDTO() {
    }

//    public UserDTO(String _id, String username, String password, String email, String phone, String birthday, String name, String createAt, String updateAt, ArrayList<String> image) {
//        this._id = _id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.phone = phone;
//        this.birthday = birthday;
//        this.name = name;
//        this.createAt = createAt;
//        this.updateAt = updateAt;
//        this.image = image;
//    }


    public UserDTO(String _id, String username, String password, String email, String phone, String birthday, String name, String createAt, String updateAt, String image) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.image = image;
    }
//    public ArrayList<String> getImage() {
//        return image;
//    }
//
//    public void setImage(ArrayList<String> image) {
//        this.image = image;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
