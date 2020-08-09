package com.example.final_project.ui.payments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.final_project.CheckService.Formate;
import com.example.final_project.Database.useradate.UserDatadbProvider;
import com.example.final_project.R;
import com.example.final_project.ui.customNotificationBox.NotificationAdapter;
import com.example.final_project.ui.customNotificationBox.NotificationData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class PaymentsFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private ListView listView;
    private ArrayList<NotificationData> list = new ArrayList<>();
    private String TAG = "PaymentsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payments, container, false);
        listView =  view.findViewById(R.id.pay_list);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Member/"+ Formate.toUsername(new UserDatadbProvider(getContext()).getEmail())+"/DueBill");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e(TAG,snapshot.toString());
                NotificationData post = snapshot.getValue(NotificationData.class);
                if(snapshot.getChildrenCount()!=0){
                    assert post != null;
                    Log.e(TAG,post.isPayed()+"");
                    list.add(post);

//                    getChildFragmentManager().beginTransaction().detach(PaymentsFragment.this).attach(PaymentsFragment.this).commit();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        PaymentAdapter arrayAdapter = new PaymentAdapter(requireActivity().getApplicationContext(),list);

        if(list != null){
            listView.setAdapter(arrayAdapter);
            list.clear();
        }
        else {
            ArrayAdapter arrayAdapter1 = new ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,new String[]{"No Bill to pay"});
            listView.setAdapter(arrayAdapter1);
            list.clear();
        }
        return view;
    }
}