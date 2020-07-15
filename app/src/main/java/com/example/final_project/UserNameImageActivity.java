package com.example.final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class UserNameImageActivity extends AppCompatActivity {

    /**
     * @Variable uname ,Is use to store the value of user name enter by user*/
    private EditText uname;
    static final int REQUEST_IMAGE_CAPTURE=1;
    private CardView cardView;
    private ImageView imageView;
    static final int SELECT_FILE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_image);

        uname = findViewById(R.id.text_name);
        imageView=findViewById(R.id.imageView);
        cardView=findViewById(R.id.image_card_view);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(getApplicationContext(),cardView);
                popupMenu.getMenuInflater().inflate(R.menu.choose_image,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.camera:
                                Intent camera =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if (camera.resolveActivity(getPackageManager())!=null){
                                    startActivityForResult(Intent.createChooser(camera,"Select Source"),REQUEST_IMAGE_CAPTURE);
                            }
                                break;
                            case R.id.gallary:
                                Intent gallary =new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                gallary.setType("image/*");
                                if (gallary.resolveActivity(getPackageManager())!=null){
                                startActivityForResult(Intent.createChooser(gallary,"Select Source"),SELECT_FILE);
                                break;}
                        }
                        return true;
                    }
                });
                popupMenu.show();

            }
        });
        //TODO : Add photo picker in on clicking on Image id(imageView)Gallery and Camera both
        //check if the name is empty then can't go to next Activity
        if(!uname.getText().toString().isEmpty()){
            findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(UserNameImageActivity.this,MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            });
        }else
            Snackbar.make(findViewById(R.id.ll),"Name Can't be empty",Snackbar.LENGTH_LONG)
                    .show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap bitmap= (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);

        }
        else if(requestCode==SELECT_FILE){
            Uri selectedImage=data.getData();
            imageView.setImageURI(selectedImage);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}