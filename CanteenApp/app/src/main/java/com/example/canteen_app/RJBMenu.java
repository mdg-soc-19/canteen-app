package com.example.canteen_app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class RJBMenu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rjbmenu, container, false);
        // Inflate the layout for this fragment
        GridLayout gl = view.findViewById(R.id.grid);

        //query code

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name


        DocumentReference docRef = db.collection("RJB-items").document("RYQsMe2PzsyLoxZbobfj");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Item item = documentSnapshot.toObject(Item.class);

                Toast toast = Toast.makeText(getActivity(), item.Name, Toast.LENGTH_LONG);
                toast.show();
        }
        });



        int length =10; //he thik karne
        for (int i = 0; i < length; i++)
        {
            //column1
            TextView tv = new TextView(getActivity().getApplicationContext());
            tv.setWidth(50);
            tv.setText("1");
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setBackgroundColor(Color.parseColor("#ffffff"));
            gl.addView(tv);

            //column2
            TextView tv2 = new TextView(getActivity().getApplicationContext());
            tv2.setWidth(200);
            tv2.setText("Maggi");
            tv2.setTextColor(Color.parseColor("#000000"));
            tv2.setBackgroundColor(Color.parseColor("#ffffff"));
            gl.addView(tv2);

            //column3
            TextView tv3 = new TextView(getActivity().getApplicationContext());
            tv3.setWidth(100);
            tv3.setText("Rs. " + "20");
            tv3.setTextColor(Color.parseColor("#000000"));
            tv3.setBackgroundColor(Color.parseColor("#ffffff"));
            gl.addView(tv3);

            //column4

            Button mb = new Button(getActivity().getApplicationContext());
            mb.setWidth(100);
            mb.setBackgroundColor(Color.parseColor("#D81B60"));
            mb.setText("ADD");

            gl.addView(mb);


        }




        return view;
    }







}

class Item
{
    public String Name;
    public int Price, Quantity;
    public Boolean Availablity;

}
