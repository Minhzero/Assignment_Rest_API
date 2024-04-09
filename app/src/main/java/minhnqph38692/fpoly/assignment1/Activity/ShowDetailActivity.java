package minhnqph38692.fpoly.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import minhnqph38692.fpoly.assignment1.DTO.FoodDTO;
import minhnqph38692.fpoly.assignment1.DTO.ProductDTOAPI;
import minhnqph38692.fpoly.assignment1.DTO.Response;
import minhnqph38692.fpoly.assignment1.Helper.ManagementCart;
import minhnqph38692.fpoly.assignment1.Interface.HttpRequest;
import minhnqph38692.fpoly.assignment1.R;
import retrofit2.Call;
import retrofit2.Callback;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, description, numberOrderTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDTO object;
    int numberOrder = 1;
    private ManagementCart managementCart;
//add cart
    HttpRequest httpRequest;
    LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDetailActivity.this, Home.class));
            }
        });
httpRequest = new HttpRequest();
        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDTO) getIntent().getSerializableExtra("object");

        Glide.with(this)
                .load(object.getImage().get(0))
                .thumbnail(Glide.with(this).load(object.getImage().get(0)))
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getPrice());
        description.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
                addCart();

                Log.d("fff", "onClick: " + object);
            }
        });

    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        description = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picFood);


    }
    private  void addCart(){
        ProductDTOAPI productDTOAPI = new ProductDTOAPI();
        object = (FoodDTO) getIntent().getSerializableExtra("object");
        productDTOAPI.setImage(object.getImage());
        productDTOAPI.setTitle(object.getTitle());
        productDTOAPI.setPrice(object.getPrice());
        productDTOAPI.setStock(numberOrder);

        httpRequest.callAPI().addProductAPI(productDTOAPI).enqueue(responProductDTOAPI);
    }
    Callback<Response<ProductDTOAPI>> responProductDTOAPI = new Callback<Response<ProductDTOAPI>>() {
        @Override
        public void onResponse(Call<Response<ProductDTOAPI>> call, retrofit2.Response<Response<ProductDTOAPI>> response) {
            if (response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    Toast.makeText(ShowDetailActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ProductDTOAPI>> call, Throwable t) {
            Log.d(">>> GetListCart", " onFailure"+t.getMessage());

        }
    };
}