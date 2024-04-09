package minhnqph38692.fpoly.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import minhnqph38692.fpoly.assignment1.Adapter.CartListAdapter;
import minhnqph38692.fpoly.assignment1.DTO.BillDetailsDTO;
import minhnqph38692.fpoly.assignment1.DTO.ProductDTOAPI;
import minhnqph38692.fpoly.assignment1.DTO.Response;
import minhnqph38692.fpoly.assignment1.Helper.ManagementCart;
import minhnqph38692.fpoly.assignment1.Interface.ChangeNumberItemsListener;
import minhnqph38692.fpoly.assignment1.Interface.HttpRequest;
import minhnqph38692.fpoly.assignment1.Interface.Item_ProductCart_Handle;
import minhnqph38692.fpoly.assignment1.R;
import retrofit2.Call;
import retrofit2.Callback;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt, check;
    private double tax;
    ScrollView scrollView;

    HttpRequest httpRequest;
    ArrayList<ProductDTOAPI> list = new ArrayList<>();
    Item_ProductCart_Handle handle;
    Double total;
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        httpRequest = new HttpRequest();

        httpRequest.callAPI().getListProductCart().enqueue(getListProductAPI);


        handle = new Item_ProductCart_Handle() {
            @Override
            public void Delete(String id) {

            }

            @Override
            public void UpdateT(String id, ProductDTOAPI productDTOAPI) {
                productDTOAPI.setTitle(productDTOAPI.getTitle());
                productDTOAPI.setImage(productDTOAPI.getImage());
                productDTOAPI.setPrice(productDTOAPI.getPrice());
                productDTOAPI.setStock(productDTOAPI.getStock() + 1);
                httpRequest.callAPI().updateProductAPIById(id, productDTOAPI)
                        .enqueue(responProductAPI);
                updateTotalPrice();
            }

            @Override
            public void UpdateG(String id, ProductDTOAPI productDTOAPI) {

                int newStock = productDTOAPI.getStock() - 1;
                if (newStock > 0) { // Đảm bảo số lượng không âm
                    productDTOAPI.setTitle(productDTOAPI.getTitle());
                    productDTOAPI.setImage(productDTOAPI.getImage());
                    productDTOAPI.setPrice(productDTOAPI.getPrice());
                    productDTOAPI.setStock(newStock);
                    httpRequest.callAPI().updateProductAPIById(id, productDTOAPI)
                            .enqueue(responProductAPI);
                    updateTotalPrice();
                } else {
                    // Xử lý khi số lượng sản phẩm giảm xuống dưới 0 (ví dụ: hiển thị thông báo)
                    // Nếu có yêu cầu, bạn có thể hiển thị thông báo cho người dùng
                    // rằng họ không thể giảm số lượng sản phẩm dưới 0.
                    httpRequest.callAPI().deleteProductCart(id).enqueue(responProductAPI);
                    updateTotalPrice();
                }
            }
        };

        managementCart = new ManagementCart(this);
        initView();
//        CalcuteCart();
        bottomNavigation();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CartListActivity.this)
                        .setTitle("Xác nhận")
                        .setMessage("Bạn có chắc chắn muốn thanh toán giỏ hàng?")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý dữ liệu sản phẩm khi người dùng chọn OK trong hộp thoại
                                ArrayList<ProductDTOAPI> productList = ((CartListAdapter) adapter).getList();
                                for (ProductDTOAPI product : productList) {
                                    BillDetailsDTO billDetailsDTO = new BillDetailsDTO();
                                    billDetailsDTO.setTitle(product.getTitle());
                                    billDetailsDTO.setImage(product.getImage());
                                    billDetailsDTO.setPrice(product.getPrice());
                                    billDetailsDTO.setStock(product.getStock());
                                    billDetailsDTO.setTotal(total);
                                    billDetailsDTO.setDate(getCurrentDateTime());
                                    httpRequest.callAPI().addBillDetailsAPI(billDetailsDTO).enqueue(responBillAPI);
                                    httpRequest.callAPI().deleteProductCart(product.get_id()).enqueue(responProductAPI);
                                    updateTotalPrice();
                                    Toast.makeText(CartListActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                                    Log.d("Product Info", "Title: " + product.getTitle() + ", Price: $" + product.getPrice() + ",gia" + total);
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
            }
        });

    }

    Callback<Response<BillDetailsDTO>> responBillAPI = new Callback<Response<BillDetailsDTO>>() {
        @Override
        public void onResponse(Call<Response<BillDetailsDTO>> call, retrofit2.Response<Response<BillDetailsDTO>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    Toast.makeText(CartListActivity.this, "okay", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<BillDetailsDTO>> call, Throwable t) {
            Log.d(">>> GetListbill", " onFailure" + t.getMessage());

        }
    };




    private void bottomNavigation() {
        LinearLayout home = findViewById(R.id.homeBtnCart);
        LinearLayout cart = findViewById(R.id.CartBtnCart);
        LinearLayout bill = findViewById(R.id.BillBtnCart);
        LinearLayout profile = findViewById(R.id.profileBtnCart);
        LinearLayout setting = findViewById(R.id.settingBtnCart);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, Home.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, Bill.class));

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, ProfileActivity.class));

            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, SettingActivity.class));

            }
        });

    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollViewCart);
        check = findViewById(R.id.check);

    }

    private void initList(ArrayList<ProductDTOAPI> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(list, this, handle, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
//               updateTotalPrice();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }

    Callback<Response<ArrayList<ProductDTOAPI>>> getListProductAPI = new Callback<Response<ArrayList<ProductDTOAPI>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<ProductDTOAPI>>> call, retrofit2.Response<Response<ArrayList<ProductDTOAPI>>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    list = response.body().getData();
                    Log.d("okok", list.toString());
                    //  ArrayList<Fruit> listfr=response.body().getData();
                    updateTotalPrice();

                    initList(list);
                    Toast.makeText(CartListActivity.this, "succ", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<ProductDTOAPI>>> call, Throwable t) {
            Toast.makeText(CartListActivity.this, "Lỗi" + t.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("ddd", t.getMessage());
        }
    };

    Callback<Response<ProductDTOAPI>> responProductAPI = new Callback<Response<ProductDTOAPI>>() {
        @Override
        public void onResponse(Call<Response<ProductDTOAPI>> call, retrofit2.Response<Response<ProductDTOAPI>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    httpRequest.callAPI().getListProductCart().enqueue(getListProductAPI);
                    Toast.makeText(CartListActivity.this, "okok", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ProductDTOAPI>> call, Throwable t) {
            Log.d(">>> GetListProductAPI", " onFailure" + t.getMessage());

        }
    };

    private void updateTotalPrice() {
        double percenTax = 0.02;
        double delivery = 10;

        double totalFee = calculateTotalPrice(list);
        double tax = Math.round((totalFee * percenTax) * 100) / 100;
        total = (double) (Math.round((totalFee + tax + delivery) * 100) / 100);

        totalFeeTxt.setText("$" + totalFee);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);
    }

    private double calculateTotalPrice(ArrayList<ProductDTOAPI> list) {
        double totalPrice = 0;
        for (ProductDTOAPI product : list) {
            totalPrice += product.getPrice() * product.getStock();
        }
        return totalPrice;
    }


//    private void CalcuteCart() {
//        double percenTax = 0.02;
//        double delivery = 10;
//
//        tax = Math.round((managementCart.getTotalFee() * percenTax) * 100) / 100;
//        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
//        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;
//
//        totalFeeTxt.setText("$" + itemTotal);
//        taxTxt.setText("$" + tax);
//        deliveryTxt.setText("$" + delivery);
//        totalTxt.setText("$" + total);
//    }
}