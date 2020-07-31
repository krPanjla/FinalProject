package com.example.final_project.ui.settings;

import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.final_project.Database.DatabaseHelper;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.R;
import com.example.final_project.UserNameImageActivity;
import com.example.final_project.firebaseConnection.ConnectionFireBase;
import com.firebase.ui.auth.AuthUI;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingFragment";
    private AppCompatActivity This;
    private LinearLayout sign_out;
    private ImageView imageView;
    private ConnectionFireBase connect;
    private Bitmap bitmap;
    private TextView name;
    private ProgressBar progressBar;
    static final int REQUEST_IMAGE_CAPTURE=1;
    static final int SELECT_FILE=0;
    private UserDatadbProvider provider;
    public SettingsFragment(AppCompatActivity activity){
        This = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        provider = new UserDatadbProvider(view.getContext());
        connect = new ConnectionFireBase();
        name = view.findViewById(R.id.name);
        imageView = view.findViewById(R.id.prof_image);
        progressBar = view.findViewById(R.id.progressbar2);

        try{
            name.setText(provider.getName());
        }catch (CursorIndexOutOfBoundsException e){
            startActivity(new Intent(This, UserNameImageActivity.class));
            This.finish();
        }

        connect.downloadProfileImage("prof_image",provider.getEmail(),imageView,view.getContext(),progressBar);

        imageView.setOnClickListener(v -> {

            PopupMenu popupMenu=new PopupMenu(view.getContext(),imageView);
            popupMenu.getMenuInflater().inflate(R.menu.choose_image,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.camera:
                        Intent camera =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (camera.resolveActivity(view.getContext().getPackageManager())!=null){
                            startActivityForResult(Intent.createChooser(camera,"Select Source"),REQUEST_IMAGE_CAPTURE);
                        }
                        break;
                    case R.id.gallery:
                        Intent gallery =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        gallery.setType("image/*");
                        if (gallery.resolveActivity(view.getContext().getPackageManager())!=null){
                            startActivityForResult(Intent.createChooser(gallery,"Select Source"),SELECT_FILE);
                            break;}
                }
                return true;
            });
            popupMenu.show();

        });

        sign_out = view.findViewById(R.id.signout);
        sign_out.setOnClickListener(v -> new AlertDialog.Builder(requireActivity())
                .setPositiveButton("Yes", (dialog, which) -> AuthUI.getInstance()
                        .signOut(container.getContext())
                        .addOnCompleteListener(task -> {
                            SQLiteDatabase sqLiteDatabase = new DatabaseHelper(getContext()).getWritableDatabase();
                            sqLiteDatabase.execSQL(DatabaseHelper.DROP_TABLE_BORROWER);
                            sqLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_BORROWER);
                            sqLiteDatabase.execSQL(DatabaseHelper.DROP_TABLE_USER);
                            sqLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_USER);
                            This.finish();
                        })).setNegativeButton("No",(dialog, which) -> {

        }).setTitle("Alert")
                .setMessage("Do you want to sign out?").show());
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ){
            Bundle extras=data.getExtras();
            assert extras != null;
            bitmap= (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);
            bitmap = imageView.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();
            connect.uploadImageToStorage("prof_image",provider.getEmail(),imageData);
        }
        else if(requestCode==SELECT_FILE && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri selectedImage = data.getData();
            connect.uploadImageToStorage("prof_image",provider.getEmail(),selectedImage,getActivity(),imageView);
            //TODO Update ImageUrl to Firebase
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}