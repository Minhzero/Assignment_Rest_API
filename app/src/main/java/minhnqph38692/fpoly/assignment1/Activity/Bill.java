package minhnqph38692.fpoly.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import minhnqph38692.fpoly.assignment1.Adapter.BillListAdapter;
import minhnqph38692.fpoly.assignment1.DTO.BillDetailsDTO;
import minhnqph38692.fpoly.assignment1.DTO.Response;
import minhnqph38692.fpoly.assignment1.Interface.HttpRequest;
import minhnqph38692.fpoly.assignment1.Interface.Item_Bill_Handle;
import minhnqph38692.fpoly.assignment1.R;
import retrofit2.Call;
import retrofit2.Callback;

public class Bill extends AppCompatActivity {
    HttpRequest httpRequest;
    BillListAdapter billListAdapter;
    RecyclerView recyclerView;

    Item_Bill_Handle handle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        recyclerView = findViewById(R.id.recycview);
        httpRequest = new HttpRequest();
        httpRequest.callAPI().getListBillDetails().enqueue(getBillAPI);
        bottomNavigation();
    }
    private void bottomNavigation() {
        LinearLayout home = findViewById(R.id.homeBtnCart);
        LinearLayout cart = findViewById(R.id.CartBtnCart);
        LinearLayout bill = findViewById(R.id.BillBtnCart);
        LinearLayout profile = findViewById(R.id.profileBtnCart);
        LinearLayout setting = findViewById(R.id.settingBtnCart);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bill.this, Home.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bill.this, CartListActivity.class));
            }
        });
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bill.this, Bill.class));

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bill.this, ProfileActivity.class));

            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bill.this, SettingActivity.class));

            }
        });

    }

    private  void getData(ArrayList<BillDetailsDTO> list){
        billListAdapter = new BillListAdapter(this,list,handle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(billListAdapter);
    }
    Callback<Response<ArrayList<BillDetailsDTO>>> getBillAPI = new Callback<Response<ArrayList<BillDetailsDTO>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<BillDetailsDTO>>> call, retrofit2.Response<Response<ArrayList<BillDetailsDTO>>> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus() == 200){
                    ArrayList<BillDetailsDTO> list = response.body().getData();
                    getData(list);
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<BillDetailsDTO>>> call, Throwable t) {
            Log.d(">>> GetListBill", " onFailure"+t.getMessage());

        }
    };
}