package com.blaq.hylton.toto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.blaq.hylton.toto.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TomorrowFragment extends Fragment {

    private View bossView;

    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;

    private TextView email;
    private TextView userID;
    private TextView name;

    private ListView mListView;
    private ArrayList<String> mArrayList;
    private ArrayAdapter<String> mArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bossView = inflater.inflate(R.layout.fragment_tomorrow_main, container, false);

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mListView = bossView.findViewById(R.id.morrow_list_item);

        mArrayList = new ArrayList<>();
        mArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mArrayList);



        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return bossView;
    }

//    private void showData(DataSnapshot dataSnapshot)
//    {
//        for (DataSnapshot ds: dataSnapshot.getChildren())
//        {
//            User user = new User();
//            user.setEmail(ds.child(mFirebaseUser.getUid()).getValue(User.class).getName());
//            user.setName(ds.child(mFirebaseUser.getUid()).getValue(User.class).getEmail());
//
//            mArrayList.add(user.getEmail());
//            mArrayList.add(user.getName());
//
//
//            mListView.setAdapter(mArrayAdapter);
//        }
//    }
}
