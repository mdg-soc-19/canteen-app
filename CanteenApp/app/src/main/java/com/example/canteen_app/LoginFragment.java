package com.example.canteen_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.canteen_app.MainActivity.username;


public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ImageView i = view.findViewById(R.id.ChaiIcon);
        i.setImageResource(R.drawable.iconfinal);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!authenticate())
                {
                    passwordTextInput.setError("Please Enter Valid Details.");

                }
                else
                {
                    username = usernameEditText.getText().toString();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.your_placeholder, new homePageFrag());
                    // or ft.add(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above
                    ft.commit();
                }
            }
        });
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(authenticate())
                {
                    passwordTextInput.setError(null);
                }
                return false;
            }
        });

        return view;
    }

    private boolean authenticate()
    {
        return true;
    }


}
