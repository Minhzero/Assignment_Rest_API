package minhnqph38692.fpoly.assignment1.Helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import minhnqph38692.fpoly.assignment1.DTO.FoodDTO;
import minhnqph38692.fpoly.assignment1.DTO.ProductDTOAPI;
import minhnqph38692.fpoly.assignment1.DTO.Response;
import minhnqph38692.fpoly.assignment1.Interface.ChangeNumberItemsListener;
import minhnqph38692.fpoly.assignment1.Interface.HttpRequest;
import retrofit2.Call;
import retrofit2.Callback;

public class ManagementCart {
    private Context context;
    private  TinyDB tinyDB;

    HttpRequest httpRequest;
    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB =new TinyDB(context);
        httpRequest = new HttpRequest();
    }



    public void insertFood(FoodDTO item) {
        ArrayList<FoodDTO> listFood = getListCart();
        boolean existAlready = false;
        int n = -1; // Khởi tạo n với giá trị -1 để kiểm tra xem sản phẩm đã tồn tại hay chưa
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i; // Lưu vị trí của sản phẩm đã tồn tại trong danh sách
                break;
            }
        }
        if (existAlready) {
            // Nếu sản phẩm đã tồn tại, cập nhật số lượng của sản phẩm
            listFood.get(n).setNumberInCart(listFood.get(n).getNumberInCart() + item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Added To Cart", Toast.LENGTH_SHORT).show();
    }

    public  ArrayList<FoodDTO> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<FoodDTO> list, int position, ChangeNumberItemsListener changeNumberItemsListener){
        list.get(position).setNumberInCart(list.get(position).getNumberInCart() + 1);

        tinyDB.putListObject("CartList", list);

        changeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<FoodDTO> list, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(list.get(position).getNumberInCart() == 1){
            list.remove(position);
        }else {
            list.get(position).setNumberInCart(list.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", list);
        changeNumberItemsListener.changed();
    }
//    public Double getTotalFee(){
//        ArrayList<ProductDTOAPI> list = getListCart();
//        double fee = 0;
//        for(int i =0;i< list.size(); i++){
//            fee = fee+(list.get(i).getPrice() * list.get(i).getNumberInCart());
//        }
//        return fee;
//    }
    public  void  updateCart(String id,ProductDTOAPI productDTOAPI){
        httpRequest.callAPI()
                .updateProductAPIById(id,productDTOAPI)
                .enqueue(responDistributorAPI);

    }
    Callback<Response<ProductDTOAPI>> responDistributorAPI = new Callback<Response<ProductDTOAPI>>() {
        @Override
        public void onResponse(Call<Response<ProductDTOAPI>> call, retrofit2.Response<Response<ProductDTOAPI>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ProductDTOAPI>> call, Throwable t) {
            Log.d(">>> GetListDitributor", " onFailure"+t.getMessage());
        }
    };
}
