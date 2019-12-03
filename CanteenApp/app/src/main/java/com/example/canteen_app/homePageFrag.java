package com.example.canteen_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.canteen_app.MainActivity.username;


public class homePageFrag extends Fragment
{
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
        return view;
    }
}
