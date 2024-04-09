package minhnqph38692.fpoly.assignment1.Interface;

import java.util.ArrayList;

import minhnqph38692.fpoly.assignment1.DTO.BillDetailsDTO;
import minhnqph38692.fpoly.assignment1.DTO.FoodDTO;
import minhnqph38692.fpoly.assignment1.DTO.ProductDTOAPI;
import minhnqph38692.fpoly.assignment1.DTO.Response;
import minhnqph38692.fpoly.assignment1.DTO.UserDTO;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiServices {

    public static String BASE_URL="http://10.0.2.2:3000/api/";

    @POST("add-user")
    Call<Response<UserDTO>> addUser(@Body UserDTO userDTO);
    @GET("get-user/{id}")
    Call<Response<UserDTO>> getListUser(@Path("id") String id);


    @POST("login")
    Call<Response<UserDTO>> login(@Body UserDTO user);

    @GET("get-list-product")
    Call<Response<ArrayList<FoodDTO>>> getListFood(@Header("Authorization") String token);

    @POST("add-cart")
    Call<Response<ProductDTOAPI>> addProductAPI(@Body ProductDTOAPI productDTOAPI);

    @PUT("update-cart/{id}")
    Call<Response<ProductDTOAPI>> updateProductAPIById(@Path("id") String id,@Body ProductDTOAPI productDTOAPI);

    @GET("get-list-cart")
    Call<Response<ArrayList<ProductDTOAPI>>> getListProductCart();

    @DELETE("delete-cart-by-id/{id}")
    Call<Response<ProductDTOAPI>> deleteProductCart(@Path("id") String id);

    @GET("get-list-billdetails")
    Call<Response<ArrayList<BillDetailsDTO>>> getListBillDetails();
    @POST("add-billdetails")
    Call<Response<BillDetailsDTO>> addBillDetailsAPI(@Body BillDetailsDTO billDetailsDTO);

    @PUT("update-user/{id}")
    Call<Response<UserDTO>> updateUserAPIById(@Path("id") String id,@Body UserDTO userDTO);



}
