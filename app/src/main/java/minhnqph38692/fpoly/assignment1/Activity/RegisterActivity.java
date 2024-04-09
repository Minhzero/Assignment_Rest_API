package minhnqph38692.fpoly.assignment1.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import minhnqph38692.fpoly.assignment1.DTO.Response;
import minhnqph38692.fpoly.assignment1.DTO.UserDTO;
import minhnqph38692.fpoly.assignment1.Interface.HttpRequest;
import minhnqph38692.fpoly.assignment1.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText sg_tenDN,sg_tenND,sg_email,sg_Mk,sg_Mk1,sg_phone,sg_year;

    Button sg_btn;
    ImageView avatar;
    HttpRequest httpRequest;
    TextView sg_Login;
    File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sg_tenDN = findViewById(R.id.sg_tenDN);
        sg_tenND = findViewById(R.id.sg_tenND);
        sg_email = findViewById(R.id.sg_email);
        sg_phone = findViewById(R.id.sg_phone);
        sg_Mk = findViewById(R.id.sg_Mk);
        sg_Mk1 = findViewById(R.id.sg_Mk1);
        sg_btn= findViewById(R.id.sg_btn);
        sg_Login= findViewById(R.id.sg_Login);

        httpRequest = new HttpRequest();


        sg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = sg_tenDN.getText().toString();
                String name = sg_tenND.getText().toString();
                String email = sg_email.getText().toString();
                String phone = sg_phone.getText().toString();
                String password = sg_Mk.getText().toString();
                String repass = sg_Mk1.getText().toString();
                if(username.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || repass.isEmpty() ) {
                    Toast.makeText(RegisterActivity.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                }else if (!password.matches(repass)) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }else {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUsername(username);
                    userDTO.setPassword(password);
                    userDTO.setPhone(phone);
                    userDTO.setName(name);
                    userDTO.setEmail(email);
                    userDTO.setBirthday("");
                    httpRequest.callAPI().addUser(userDTO).enqueue(responseUser);
                }

            }
        });

        sg_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    Callback<Response<UserDTO>> responseUser = new Callback<Response<UserDTO>>() {
        @Override
        public void onResponse(Call<Response<UserDTO>> call, retrofit2.Response<Response<UserDTO>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus()==200){
                    Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                }else {
                    Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                }
            }
        }

        @Override
        public void onFailure(Call<Response<UserDTO>> call, Throwable t) {
            Log.d(">>> getListDistributor","onFailur" + t.getMessage());

        }
    };

}