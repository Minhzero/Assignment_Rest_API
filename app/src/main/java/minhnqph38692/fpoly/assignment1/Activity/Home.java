package minhnqph38692.fpoly.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import minhnqph38692.fpoly.assignment1.Adapter.CategoryAdapter;
import minhnqph38692.fpoly.assignment1.Adapter.FoodAdapter;
import minhnqph38692.fpoly.assignment1.DTO.CategoryDTO;
import minhnqph38692.fpoly.assignment1.DTO.FoodDTO;
import minhnqph38692.fpoly.assignment1.DTO.Response;
import minhnqph38692.fpoly.assignment1.Interface.HttpRequest;
import minhnqph38692.fpoly.assignment1.Interface.Item_Food_Handle;
import minhnqph38692.fpoly.assignment1.R;
import retrofit2.Call;
import retrofit2.Callback;

public class Home extends AppCompatActivity {
    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewCatergoryList,recyclerViewPopularList;
    HttpRequest httpRequest;
    SharedPreferences sharedPreferences;
    String token;

    FoodAdapter foodAdapter;
    ArrayList<FoodDTO> list = new ArrayList<>();
    Item_Food_Handle handle;
    ImageView headerImageView;
    private int[] imageResources = {R.drawable.fruit, R.drawable.fruitbasket, R.drawable.fruitgroup,R.drawable.chanhleo};
    private int currentImageIndex = 0;

    private Handler handler = new Handler();
    private Runnable imageSwitcher = new Runnable() {
        @Override
        public void run() {
            currentImageIndex = (currentImageIndex + 1) % imageResources.length;
            headerImageView.setImageResource(imageResources[currentImageIndex]);

            handler.postDelayed(this, 3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        headerImageView = findViewById(R.id.headerImageView);
        handler.post(imageSwitcher);

        httpRequest = new HttpRequest();
        recyclerViewPopularList = findViewById(R.id.recyclerViewPopular);
        sharedPreferences = getSharedPreferences("INFO",MODE_PRIVATE);
        token = sharedPreferences.getString("token","");
        Log.d("okok",token);

        httpRequest.callAPI().getListFood("Bearer "+token).enqueue(getListFoodAPI);


//        recyclerViewCatergory();
//        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation() {
        LinearLayout home = findViewById(R.id.homeBtn);
        LinearLayout cart = findViewById(R.id.CartBtn);
        LinearLayout bill = findViewById(R.id.BillBtn);
        LinearLayout profile = findViewById(R.id.profileBtn);
        LinearLayout setting = findViewById(R.id.settingBtn);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Home.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, CartListActivity.class));
            }
        });
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Bill.class));

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ProfileActivity.class));

            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, SettingActivity.class));

            }
        });
    }

//    private void recyclerViewCatergory() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewCatergoryList = findViewById(R.id.recyclerViewCatergory);
//        recyclerViewCatergoryList.setLayoutManager(linearLayoutManager);
//
//        ArrayList<CategoryDTO> catergory = new ArrayList<>();
//        catergory.add(new CategoryDTO("Pizaa", "cat_1"));
//        catergory.add(new CategoryDTO("Burger", "cat_2"));
//        catergory.add(new CategoryDTO("Hotdog", "cat_3"));
//        catergory.add(new CategoryDTO("Drink", "cat_4"));
//        catergory.add(new CategoryDTO("Donus", "cat_5"));
//
//
//        adapter = new CategoryAdapter(catergory);
//        recyclerViewCatergoryList.setAdapter(adapter);
//    }

    private void dodulieu(ArrayList<FoodDTO> list){
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


//        recyclerViewPopularList.setLayoutManager(new GridLayoutManager(Home.this,2));
//
//        recyclerViewPopularList.setAdapter(adapter2);
        foodAdapter = new FoodAdapter(this,list,handle);
        recyclerViewPopularList.setLayoutManager(new GridLayoutManager(Home.this,2));
        recyclerViewPopularList.setAdapter(foodAdapter);
    }

    Callback<Response<ArrayList<FoodDTO>>> getListFoodAPI = new Callback<Response<ArrayList<FoodDTO>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<FoodDTO>>> call, retrofit2.Response<Response<ArrayList<FoodDTO>>> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus()==200){
                    list=response.body().getData();
Log.d("okok",list.toString());
                    //  ArrayList<Fruit> listfr=response.body().getData();
                    dodulieu(list);
                    Toast.makeText(Home.this, "succ", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<FoodDTO>>> call, Throwable t) {
            Toast.makeText(Home.this, "Lỗi"+ t.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("ddd",t.getMessage());

        }
    };
    Callback<Response<FoodDTO>> responseFruitAPI = new Callback<Response<FoodDTO>>() {
        @Override
        public void onResponse(Call<Response<FoodDTO>> call, retrofit2.Response<Response<FoodDTO>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    //
                    // gọi lại API để load lại dữ liệu sau khi thêm thành công.
                    httpRequest.callAPI().getListFood("Bearer "+token).enqueue(getListFoodAPI);//
                }
            }
        }

        @Override
        public void onFailure(Call<Response<FoodDTO>> call, Throwable t) {
            Log.d(">>>> Fruit", "onFailure: " + t.getMessage());

        }
    };
//    public void onDestroyView() {
//        handler.removeCallbacks(imageSwitcher);
//        super.onDestroyView();
//    }
}