package com.example.final_project.ui.settings;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.final_project.CheckService.Formate;
import com.example.final_project.Database.DatabaseHelper;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.R;
import com.example.final_project.UserNameImageActivity;
import com.example.final_project.firebaseConnection.ConnectionFireBase;
import com.example.final_project.ui.profile.ProfileFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;
import static com.google.zxing.BarcodeFormat.QR_CODE;

public class SettingsFragment extends Fragment {

    private ProfileFragment profileFragment;
    CardView cardView;
    private static final String TAG = "SettingFragment";
    private AppCompatActivity This;
    private ImageView imageView,qrcode;
    private ConnectionFireBase connect;
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
        TextView name = view.findViewById(R.id.name);
        imageView = view.findViewById(R.id.prof_image);
        ProgressBar progressBar = view.findViewById(R.id.progressbar2);
        qrcode = view.findViewById(R.id.qrcode);

        profileFragment=new ProfileFragment();
        cardView=view.findViewById(R.id.edit_profile);
        cardView.setOnClickListener(v -> {

            FragmentManager fragmentManager=getParentFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_frame,profileFragment);
            fragmentTransaction.commit();

        });

        try{
            name.setText(provider.getName());
        }catch (CursorIndexOutOfBoundsException e){
            startActivity(new Intent(This, UserNameImageActivity.class));
            This.finish();
        }

        try{

            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(
                            Formate.toUsername(new UserDatadbProvider(getContext()).getEmail())
                            ,QR_CODE
                            ,qrcode.getWidth()
                            ,qrcode.getHeight()
                            ,null);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            int[] res = new int[width*height];
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    res[offset + x] = bitMatrix.get(x, y) ? BLACK : WHITE;
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Log.e(TAG,"bitmap created ");
            bitmap.setPixels(res, 0, width, 0, 0, width, height);
            qrcode.setImageBitmap(bitmap);
        }catch(WriterException e){
            Log.e(TAG,"Error : ");
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }

        qrcode.setOnLongClickListener(view1 -> {
            Dialog imageView2 = new Dialog(requireActivity());
            imageView2.requestWindowFeature(Window.FEATURE_NO_TITLE);
            imageView2.setContentView(R.layout.image_container);
            try{

                BitMatrix bitMatrix = new MultiFormatWriter()
                        .encode(
                                Formate.toUsername(new UserDatadbProvider(getContext()).getEmail())
                                ,QR_CODE
                                , Resources.getSystem().getDisplayMetrics().widthPixels-30
                                , Resources.getSystem().getDisplayMetrics().widthPixels-30
                                ,null);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                int[] res = new int[width*height];
                for (int y = 0; y < height; y++) {
                    int offset = y * width;
                    for (int x = 0; x < width; x++) {
                        res[offset + x] = bitMatrix.get(x, y) ? BLACK : WHITE;
                    }
                }
                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Log.e(TAG,"bitmap created2 ");
                bitmap.setPixels(res, 0, width, 0, 0, width, height);
                ImageView image = (ImageView) imageView2.findViewById(R.id.qr_image);
                image.setImageBitmap(bitmap);
                imageView2.show();
                }catch(WriterException e){
                Log.e(TAG,"Error : 2");
                Log.e(TAG, Objects.requireNonNull(e.getMessage()));
            }

            return false;
        });


        connect.downloadProfileImage("prof_image",provider.getEmail(),imageView,view.getContext(), progressBar);

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

        LinearLayout sign_out = view.findViewById(R.id.signout);
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
            Bitmap bitmap = (Bitmap) extras.get("data");
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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}