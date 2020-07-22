package com.example.final_project.ui.home;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.final_project.Database.BorrowersDB.Borrowers_Dd;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.add_borrower;
import com.example.final_project.R;
import com.example.final_project.add_borrower;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment<floatingActionButton> extends Fragment {
    private static final String TAG = "HomeFragment";
    Borrowers_Dd borrowers_dd;
    RecyclerView recyclerView;
    ArrayList<String> id,date,ammount;
    FloatingActionButton floatingActionButton;
    Context context;
    CustomAdapter customAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recyclerlayout);
        floatingActionButton=view.findViewById(R.id.add_person);

        borrowers_dd=new Borrowers_Dd(view.getContext());
        id=new ArrayList<>();
        date=new ArrayList<>();
        ammount=new ArrayList<>();
        storeDataInArrays();
        customAdapter=new CustomAdapter(view.getContext(),id,date,ammount);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        floatingActionButton.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), add_borrower.class);
            startActivity(intent);
        });
    return view;
    }
    private void storeDataInArrays(){
        Cursor cursor=borrowers_dd.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(getContext(),"",Toast.LENGTH_SHORT).show();
        }else{
            while((cursor.moveToNext())){

                id.add(cursor.getString(0));
                date.add(cursor.getString(1));
                ammount.add(cursor.getString(2));

            }
        }
    }

}