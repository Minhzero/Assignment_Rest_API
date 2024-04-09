package minhnqph38692.fpoly.assignment1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import minhnqph38692.fpoly.assignment1.DTO.BillDetailsDTO;
import minhnqph38692.fpoly.assignment1.DTO.FoodDTO;
import minhnqph38692.fpoly.assignment1.R;

public class BillDetailsActivity extends AppCompatActivity {
    TextView ct_Title,ct_price,ct_stock,textBillDate,textBillTotal;
 ImageView ct_anh;
 LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        ct_Title = findViewById(R.id.ct_Title);
        ct_price = findViewById(R.id.ct_price);
        ct_stock = findViewById(R.id.ct_stock);
        textBillDate = findViewById(R.id.textBillDate);
        textBillTotal = findViewById(R.id.textBillTotal);
        ct_anh = findViewById(R.id.ct_anh);
        back = findViewById(R.id.back);
        Intent intent = getIntent();
        String billDetailsJson = intent.getStringExtra("billDetails");

        // Chuyển đổi chuỗi JSON thành đối tượng BillDetailsDTO bằng Gson
        Gson gson = new Gson();
        BillDetailsDTO billDetails = gson.fromJson(billDetailsJson, BillDetailsDTO.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(BillDetailsActivity.this, Bill.class));
            }
        });
        ct_Title.setText(billDetails.getTitle());
        ct_price.setText("Giá: "+String.valueOf(billDetails.getPrice()));
        ct_stock.setText("Số lượng: "+billDetails.getStock());
        textBillDate.setText("Thời gian: "+billDetails.getDate());
        textBillTotal.setText("Tổng tiền"+String.valueOf(billDetails.getTotal()));

//        Glide.with(this)
//                .load(billDetails.getImage()) // Đặt URL của hình ảnh ở đây
//                .into(ct_anh);
        Glide.with(this)
                .load(billDetails.getImage())
                .thumbnail(Glide.with(this).load(billDetails.getImage().get(0)))
                .into(ct_anh);
        Log.d("ii", "onCreate: "+billDetails.getImage());

    }
}