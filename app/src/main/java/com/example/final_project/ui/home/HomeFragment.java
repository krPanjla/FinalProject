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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    Borrowers_Dd borrowers_dd;
    RecyclerView recyclerView;
    ArrayList<String> id,date,ammount;
    FloatingActionButton floatingActionButton;
    Context context;
    CustomAdapter customAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
                    }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_fragment, container, false);
        recyclerView=view.findViewById(R.id.recyclerlayout);
        floatingActionButton=view.findViewById(R.id.add_person);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), add_borrower.class);
                startActivity(intent);

            }
        floatingActionButton = view.findViewById(R.id.add_person);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), add_borrower.class);
            startActivity(intent);
        });
        return view;
        borrowers_dd=new Borrowers_Dd(HomeFragment.this);
        id=new ArrayList<>();
        date=new ArrayList<>();
        ammount=new ArrayList<>();
        storeDataInArrays();
        customAdapter=new CustomAdapter(HomeFragment.this),id,date,ammount;
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeFragment.this));


    }
    void  storeDataInArrays(){
        Cursor cursor=borrowers_dd.readAllData();
    if(cursor.getCount()==0){
        Toast.makeText(, "", Toast.LENGTH_SHORT).show();
    }else{
        while ((cursor.moveToNext())) {

            id.add(cursor.getString(0));
            date.add(cursor.getString(1));
            ammount.add(cursor.getString(2));

        }
    }
    }
}