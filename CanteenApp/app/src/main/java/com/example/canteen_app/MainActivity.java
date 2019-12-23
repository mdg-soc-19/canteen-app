package com.example.canteen_app;



import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity  {
    static final int RC_SIGN_IN = 9001;
    static final String LAST_FRAGMENT = "userFragment";
    static int mCurrentFragment;
    static int mDefaultFragment = 1;
    static GoogleSignInClient mGoogleSignInClient;
    public static String uid;
    public static String Bhawan;
    public static String Name;


    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    public void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setTheme(R.style.CanteenAppTheme);
        //authcode
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        //authcode

        if (savedInstanceState != null) {
            mCurrentFragment = savedInstanceState.getInt(LAST_FRAGMENT);
        }
        else
            { mCurrentFragment = mDefaultFragment; }
    }

    @Override
    protected void onStart() {
        getSupportActionBar().hide();
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                System.out.println("BOOOOOOOOM");
                if(user!=null)
                {
                    // Sign in logic here.
                    uid = "PLACEHOLDER";
                    for (UserInfo profile : user.getProviderData())
                    {
                        uid = profile.getUid();
                        Name = profile.getDisplayName();
                        for(int i=0; i<10; i++)
                        {
                            System.out.println(Name);
                        }
                    }
                }


            }
        });
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(LAST_FRAGMENT, mCurrentFragment);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void updateUI(FirebaseUser account)
    {
        if(account==null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            ft.replace(R.id.your_placeholder, new LoginFragment());
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            ft.commit();
        }
        else
        {
            switch(mCurrentFragment)
            {
                case 1:
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.your_placeholder, new homePageFrag());
                    // or ft.add(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above
                    ft.commit();
                    break;
                case 2:
                    ft = getSupportFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.your_placeholder, new MenuPage());
                    // or ft.add(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above
                    ft.commit();
                    break;
                case 3:
                    ft = getSupportFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.your_placeholder, new CheckoutFrag());
                    // or ft.add(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above
                    ft.commit();
                    break;
                case 4:
                    ft = getSupportFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.your_placeholder, new OrderHistoryFrag());
                    // or ft.add(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above
                    ft.commit();


            }

        }
    }
}