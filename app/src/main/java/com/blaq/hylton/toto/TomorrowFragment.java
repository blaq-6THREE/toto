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
import com.blaq.hylton.toto.data.UserList;
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

    private ListView mListView;
    private ArrayList<User> mArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bossView = inflater.inflate(R.layout.fragment_tomorrow_main, container, false);

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mListView = bossView.findViewById(R.id.morrow_list_item);

        mArrayList = new ArrayList<>();

        return bossView;
    }

    @Override
    public void onStart() {
        super.onStart();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mArrayList.clear();

                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    User user = ds.child(mFirebaseUser.getUid()).getValue(User.class);

                    mArrayList.add(user);
                }
                UserList userList = new UserList(getActivity(), mArrayList);
                mListView.setAdapter(userList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
