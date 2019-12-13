package com.example.canteen_app;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.canteen_app.MainActivity.Bhawan;
import static com.example.canteen_app.MainActivity.uid;


public class CheckoutFrag extends Fragment {

    private static int resumer = 3;
    public CheckoutFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        final ProgressBar pb = view.findViewById(R.id.pBar);
        pb.setVisibility(View.VISIBLE);
        final GridLayout gl = view.findViewById(R.id.GridCheckout);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Bhawan + "-orders").document(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            pb.setVisibility(View.GONE);
                            DocumentSnapshot document = task.getResult();
                            System.out.println(document.getData());
                            Map<String, Object> Items =  new HashMap<>();
                            Items.put("Item", document.get("Item"));
                            System.out.println("ITEMS : " + Items);
                            Map<String, Object> itemlist = (Map)(Items.get("Item"));

                            System.out.println("REACHED");
                            int i = 1;
                            int total = 0;
                            for (Map.Entry<String, Object> entry : itemlist.entrySet())
                            {
                                System.out.println("Value is " + entry.getValue());
                                Map<String, Map<String, Object>> orderItem = new HashMap<>();
                                orderItem.put(entry.getKey(), (Map)entry.getValue());
                                String Name = (orderItem.get(entry.getKey()).get("Name")).toString();
                                String P = (orderItem.get(entry.getKey()).get("Price")).toString();
                                int Price = Integer.parseInt(P);
                                String Q = (orderItem.get(entry.getKey()).get("Quantity")).toString();
                                int Quantity = Integer.parseInt(Q);

                                if(Quantity!=0) {
                                    //column1
                                    TextView tv = new TextView(getActivity().getApplicationContext());
                                    tv.setWidth(50);
                                    tv.setText(Integer.toString(i++));
                                    tv.setTextColor(Color.parseColor("#000000"));
                                    tv.setBackgroundColor(Color.parseColor("#ffffff"));
                                    tv.setId(1 + 10 * i);
                                    gl.addView(tv);

                                    //column2
                                    TextView tv2 = new TextView(getActivity().getApplicationContext());
                                    tv2.setWidth(200);
                                    tv2.setText(Name);
                                    tv2.setTextColor(Color.parseColor("#000000"));
                                    tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                                    tv2.setId(2 + 10 * i);
                                    gl.addView(tv2);

                                    //column3
                                    TextView tv3 = new TextView(getActivity().getApplicationContext());
                                    tv3.setWidth(200);
                                    tv3.setText("Rs. " + Price + " X" + Quantity);
                                    tv3.setTextColor(Color.parseColor("#000000"));
                                    tv3.setBackgroundColor(Color.parseColor("#ffffff"));
                                    tv3.setId(3 + 10 * i);
                                    gl.addView(tv3);
                                    total += Price * Quantity;
                                }

                            }
                            TextView Total = (TextView)view.findViewById(R.id.TotalAmount);
                            String tot = new String(total + "");
                            Total.setText(tot);
                            System.out.println("total is " + Total.getText());


                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        final Button Place = view.findViewById(R.id.placebu);
        //placebu - placebo!!! Geddit? No? Okay...

        Place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Map<String, Object> OrderData = new HashMap<>();
            OrderData.put("OrderState", "Placed");
            db.collection(Bhawan + "-orders").document(uid).set(OrderData, SetOptions.merge());
            Place.setText("Order Placed");
            Place.setBackgroundColor(Color.parseColor("#63EC27"));

            }
        });

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        MainActivity.mCurrentFragment = resumer;
    }


}






