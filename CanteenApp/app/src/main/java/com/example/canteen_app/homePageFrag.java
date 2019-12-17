package com.example.canteen_app;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;




public class homePageFrag extends Fragment
{
    private static int resumer = 1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        ImageView rajendra = view.findViewById(R.id.rajendrap);
        rajendra.setImageResource(R.drawable.rajendraliner);
        ImageView ravindra = view.findViewById(R.id.ravindrat);
        ravindra.setImageResource(R.drawable.ravindra);
        ImageView radhakrishna = view.findViewById(R.id.rkb);
        radhakrishna.setImageResource(R.drawable.radhakrishnan);
        ImageView cautley = view.findViewById(R.id.cautley);
        cautley.setImageResource(R.drawable.cautley);
        ImageView sarojini = view.findViewById(R.id.sarojinin);
        sarojini.setImageResource(R.drawable.sarojioniliner);
        ImageView rajiv = view.findViewById(R.id.rajivg);
        rajiv.setImageResource(R.drawable.rajivliner);






        //listener - list
        MaterialCardView RJBCard = view.findViewById(R.id.RJB);
        RJBCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.Bhawan = "RJB";
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.your_placeholder, new MenuPage());
                    // or ft.add(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above
                    ft.commit();

            }
        });
        MaterialCardView RavindraCard = view.findViewById(R.id.Ravindra);
        RavindraCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.Bhawan = "Ravindra";
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.your_placeholder, new RJBMenu());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();

            }
        });
        MaterialCardView RKBCard = view.findViewById(R.id.RKB);
        RKBCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.Bhawan = "RKB";
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.your_placeholder, new RJBMenu());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();

            }
        });
        MaterialCardView CautleyCard = view.findViewById(R.id.cautleycanteen);
        CautleyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.Bhawan = "cautley";
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.your_placeholder, new RJBMenu());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();

            }
        });
        MaterialCardView SarojiniCard = view.findViewById(R.id.sarojini);
        SarojiniCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.Bhawan = "sarojini";
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.your_placeholder, new RJBMenu());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();

            }
        });

        MaterialButton history = view.findViewById(R.id.Orderhist);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentTransaction ft = getFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.your_placeholder, new OrderHistoryFrag());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();

            }
        });
        //listener-end


        return view;

    }
}
