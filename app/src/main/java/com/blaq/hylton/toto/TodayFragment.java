package com.blaq.hylton.toto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.blaq.hylton.toto.data.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TodayFragment extends Fragment {

    private View bossView;
    private Button mSignOutButton;
    private Button addToDatabaseButton;

    private String UID;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private DatabaseReference mDatabaseReference;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bossView = inflater.inflate(R.layout.fragment_today_main, container, false);

        mSignOutButton = bossView.findViewById(R.id.signout);
        addToDatabaseButton = bossView.findViewById(R.id.database_adder_button);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        addToDatabaseButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String userID = mFirebaseUser.getUid();
             String userDisplayName = mFirebaseUser.getDisplayName();
             String userEmail = mFirebaseUser.getEmail();

             writeNewUser(userID, userDisplayName, userEmail);
         }
        });

        return bossView;
    }


    // This is done!
    private void writeNewUser(String userId, String name, String email)
    {
        User user = new User(name, email);
        mDatabaseReference.child("users").child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getActivity(), "Added to Database", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // This is done!
    private void signOut()
    {
        mFirebaseAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(),
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(getActivity(), GoogleSignUp.class));
                    }
                });
    }

}
