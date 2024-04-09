package minhnqph38692.fpoly.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import minhnqph38692.fpoly.assignment1.DTO.Response;
import minhnqph38692.fpoly.assignment1.DTO.UserDTO;
import minhnqph38692.fpoly.assignment1.Interface.HttpRequest;
import minhnqph38692.fpoly.assignment1.R;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText lg_username, lg_password;
    Button lg_btn;
    HttpRequest httpRequest;
    TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lg_username = findViewById(R.id.lg_username);
        lg_password = findViewById(R.id.lg_password);
        lg_btn = findViewById(R.id.lg_btn);
        signup = findViewById(R.id.lgSingup);
        httpRequest= new HttpRequest();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        lg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDTO user = new UserDTO();
                String _username = lg_username.getText().toString();
                String _password = lg_password.getText().toString();
                if (_username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (_password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                user.setUsername(_username);
                user.setPassword(_password);
                httpRequest.callAPI().login(user).enqueue(responseUser);



            }
        });
    }

    Callback<Response<UserDTO>> responseUser = new Callback<Response<UserDTO>>() {
        @Override
        public void onResponse(Call<Response<UserDTO>> call, retrofit2.Response<Response<UserDTO>> response) {

                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("INFO",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", response.body().getToken());
                    editor.putString("refreshToken", response.body().getRefreshToken());
                    editor.putString("id", response.body().getData().get_id());
                    Log.d("id", "ff"+response.body().getData().get_id());
                    editor.apply();

                    startActivity(new Intent(LoginActivity.this, Home.class));
                }else {
                    Toast.makeText(LoginActivity.this, "đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }

        }

        @Override
        public void onFailure(Call<Response<UserDTO>> call, Throwable t) {

            Log.d(">>> GetlistDistributor", "onFailure" + t.getMessage());
        }
    };
}