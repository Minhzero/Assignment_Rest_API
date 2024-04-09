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
import retrofit2.Call;
import retrofit2.Callback;

public class UpdateProfileActivity extends AppCompatActivity {
    TextInputEditText pr_name, pr_email, pr_sdt, pr_birthday;
    Button pr_btn,pr_back;
    ImageView pr_avatar;

    HttpRequest httpRequest;
    File file;
    Uri imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        pr_name = findViewById(R.id.pr_name);
        pr_email = findViewById(R.id.pr_email);
        pr_sdt = findViewById(R.id.pr_sdt);
        pr_birthday = findViewById(R.id.pr_birthday);
        pr_btn = findViewById(R.id.pr_btn);
        pr_avatar = findViewById(R.id.pr_avatar);
        pr_back= findViewById(R.id.pr_back);
        httpRequest = new HttpRequest();
        UserDTO user = (UserDTO) getIntent().getSerializableExtra("user");

        pr_name.setText(user.getName());
        pr_email.setText(user.getEmail());
        pr_sdt.setText(user.getPhone());
        pr_birthday.setText(user.getBirthday());
        String id = user.get_id();
        Log.d("idddd", "ddd" + id);
        pr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDTO userDTO = new UserDTO();
                userDTO.setName(pr_name.getText().toString());
                userDTO.setEmail(pr_email.getText().toString());
                userDTO.setPhone(pr_sdt.getText().toString());
                userDTO.setBirthday(pr_birthday.getText().toString());
                userDTO.setImage(imagePath.toString());

                httpRequest.callAPI().updateUserAPIById(id,userDTO).enqueue(responUser);


            }
        });
        pr_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        pr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateProfileActivity.this, ProfileActivity.class));
            }
        });

    }
    Callback<Response<UserDTO>> responUser = new Callback<Response<UserDTO>>() {
        @Override
        public void onResponse(Call<Response<UserDTO>> call, retrofit2.Response<Response<UserDTO>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    Toast.makeText(UpdateProfileActivity.this, "Update thanh cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateProfileActivity.this, ProfileActivity.class));
                }
            }
        }

        @Override
        public void onFailure(Call<Response<UserDTO>> call, Throwable t) {
Log.d("errr","ee  " +t.getMessage());
        }
    };

    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        getImage.launch(intent);
    }

    ActivityResultLauncher<Intent> getImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if (o.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = o.getData();
                         imagePath = intent.getData();
                        Log.d("anhh", "ddd" + imagePath);
//                        file= createFileFromUri(imagePath,"avatar");
                        // Glide để load ảnh
                        Glide.with(UpdateProfileActivity.this)
                                .load(imagePath)
                                .thumbnail(Glide.with(UpdateProfileActivity.this).load(R.mipmap.ic_launcher))
                                .centerCrop()
                                .circleCrop()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .into(pr_avatar);

                    }
                }
            });

    private File createFileFromUri(Uri path, String name) {
        File _file = new File(UpdateProfileActivity.this.getFilesDir(), name + ".png");
        try {
            InputStream in = UpdateProfileActivity.this.getContentResolver().openInputStream(path);
            OutputStream out = new FileOutputStream(_file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
            return _file;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}