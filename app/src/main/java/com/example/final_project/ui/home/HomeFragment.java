package com.example.final_project.ui.home;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.final_project.Database.BorrowersDB.Home_DataContact;
import com.example.final_project.Database.DatabaseHelper;
import com.example.final_project.add_borrower;
import com.example.final_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    DatabaseHelper borrowers_dd;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.home_list);
        floatingActionButton=view.findViewById(R.id.add_person);
        Home_DataContact contact  = new Home_DataContact(this.getContext());
        ArrayList<Home_DataContact> list = Home_DataContact.createContactsList(this.getContext());
        Log.e(TAG,"count Value"+ Home_DataContact.getCount(view.getContext()));
        if(!(Home_DataContact.getCount(view.getContext())<=0)) {
            Log.e(TAG,"IN");
            HomeAdapter adapter = new HomeAdapter(list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }
        floatingActionButton.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), add_borrower.class);
            intent.putExtra("this",(Serializable)this);
            startActivity(intent);

        });
    return view;
    }

}