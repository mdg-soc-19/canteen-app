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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


import static android.content.ContentValues.TAG;


public class RJBMenu extends Fragment implements View.OnClickListener {
    public int k;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rjbmenu, container, false);
        // Inflate the layout for this fragment
        final GridLayout gl = view.findViewById(R.id.grid);
        final Context thiscontext = this.getActivity();
        //query code

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("RJB-items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 1;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Item item = document.toObject(Item.class);
                                String name = item.Name;
                                int price = item.Price;
                                int qty = item.Quantity;
                                boolean aty = item.Availablity;

                                //adding to frag
                                //column1
                                TextView tv = new TextView(getActivity().getApplicationContext());
                                tv.setWidth(50);
                                tv.setText(Integer.toString(i++));
                                tv.setTextColor(Color.parseColor("#000000"));
                                tv.setBackgroundColor(Color.parseColor("#ffffff"));
                                tv.setId(i + 10*1);
                                gl.addView(tv);


                                //column2
                                TextView tv2 = new TextView(getActivity().getApplicationContext());
                                tv2.setWidth(200);
                                tv2.setText(name);
                                tv2.setTextColor(Color.parseColor("#000000"));
                                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                                tv2.setId(i + 10*2);
                                gl.addView(tv2);

                                //column3
                                TextView tv3 = new TextView(getActivity().getApplicationContext());
                                tv3.setWidth(100);
                                tv3.setText("Rs. " + price);
                                tv3.setTextColor(Color.parseColor("#000000"));
                                tv3.setBackgroundColor(Color.parseColor("#ffffff"));
                                tv3.setId(i + 10*3);
                                gl.addView(tv3);

                                //column4
                                Button mb = new Button(getActivity().getApplicationContext());
                                mb.setWidth(100);
                                if(aty)
                                    mb.setBackgroundColor(Color.parseColor("#D81B60"));
                                else
                                    mb.setBackgroundColor(Color.parseColor("#696969"));
                                mb.setText("ADD");
                                mb.setId(i + 10*4);

                                mb.setText("ADD");
                                mb.setId(i + 10*4);

                                gl.addView(mb);
                                mb.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        Toast toast = Toast.makeText(getActivity(), "id is " + id, Toast.LENGTH_LONG);
                                        toast.show();
                                    }
                                });
                                //end of adding
                            }
                            k = i;
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        return view;
    }


    public void onClick(View v)
    {


    }





}

class Item
{
    public String Name;
    public int Price, Quantity;
    public Boolean Availablity;

}
