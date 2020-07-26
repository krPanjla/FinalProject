package com.example.final_project.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project.Database.BorrowersDB.Borrowers_Dd;

import com.example.final_project.Database.BorrowersDB.DataContact;
import com.example.final_project.add_borrower;
import com.example.final_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    Borrowers_Dd borrowers_dd;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.home_list);
        floatingActionButton=view.findViewById(R.id.add_person);
        ArrayList<DataContact> list = DataContact.createContactsList(this.getContext());
        HomeAdapter adapter = new HomeAdapter(list);
        if(list != null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }
        floatingActionButton.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), add_borrower.class);
            startActivity(intent);
        });
    return view;
    }

}