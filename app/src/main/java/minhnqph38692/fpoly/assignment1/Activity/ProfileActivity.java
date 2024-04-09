package minhnqph38692.fpoly.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import minhnqph38692.fpoly.assignment1.DTO.Response;
import minhnqph38692.fpoly.assignment1.DTO.UserDTO;
import minhnqph38692.fpoly.assignment1.Interface.HttpRequest;
import minhnqph38692.fpoly.assignment1.R;
import retrofit2.Call;
import retrofit2.Callback;

public class ProfileActivity extends AppCompatActivity {
    TextView tennguoidung,emailnguoidung,sdtnguoidung,namsinhnguoidung;
    HttpRequest httpRequest;
    ImageView avatar;
    SharedPreferences sharedPreferences;
    String id;
    Button btnSua;
    UserDTO user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tennguoidung=findViewById(R.id.tennguoidung);
        emailnguoidung =findViewById(R.id.emailnguoidung);
        sdtnguoidung=findViewById(R.id.sdtnguoidung);
        namsinhnguoidung=findViewById(R.id.namsinhnguoidung);
        btnSua =findViewById(R.id.btnSua);
        avatar = findViewById(R.id.avatar);
        sharedPreferences = getSharedPreferences("INFO",MODE_PRIVATE);
        httpRequest = new HttpRequest();
        id = sharedPreferences.getString("id","");
        Log.d("dd","dd"+id);
        httpRequest.callAPI().getListUser(id).enqueue(getUserAPI);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                intent.putExtra("user", user); // user là đối tượng UserDTO
                startActivity(intent);
            }
        });


        bottomNavigation();
    }
    Callback<Response<UserDTO>> getUserAPI = new Callback<Response<UserDTO>>() {
        @Override
        public void onResponse(Call<Response<UserDTO>> call, retrofit2.Response<Response<UserDTO>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200){
                     user = response.body().getData();

                        // Ví dụ: In ra tên người dùng
                        tennguoidung.setText(user.getName());
                        Log.d("ccc", "onResponse: "+user.getImage());
                        emailnguoidung.setText(user.getEmail());
                        sdtnguoidung.setText(user.getPassword());
                        namsinhnguoidung.setText(user.getBirthday());
                        sdtnguoidung.setText(user.getPhone());
                    Glide.with(ProfileActivity.this)
                            .load(user.getImage())
                            .thumbnail(Glide.with(ProfileActivity.this).load(user.getImage()))
                            .into(avatar);


                }
            }
        }

        @Override
        public void onFailure(Call<Response<UserDTO>> call, Throwable t) {

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
                startActivity(new Intent(ProfileActivity.this, Home.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, CartListActivity.class));
            }
        });
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, Bill.class));

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));

            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SettingActivity.class));

            }
        });

    }
}