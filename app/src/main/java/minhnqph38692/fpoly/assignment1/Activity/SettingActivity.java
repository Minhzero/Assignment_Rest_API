package minhnqph38692.fpoly.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;

import minhnqph38692.fpoly.assignment1.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

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
                startActivity(new Intent(SettingActivity.this, Home.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, CartListActivity.class));
            }
        });
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, Bill.class));

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, ProfileActivity.class));

            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, SettingActivity.class));

            }
        });

    }
}