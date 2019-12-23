package com.example.canteen_app;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.canteen_app.MainActivity.Bhawan;
import static com.example.canteen_app.MainActivity.uid;


public class OrderHistoryFrag extends Fragment {

    private static int resumer = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public static Fragment frag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        frag = this;
        final ProgressBar pb = view.findViewById(R.id.pBar);
        pb.setVisibility(View.VISIBLE);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final LinearLayout ll = view.findViewById(R.id.OrderHistoryLL);

        orderHistoryViewModel mViewModel = ViewModelProviders.of(this).get(orderHistoryViewModel.class);
        // Create the observer which updates the UI.
        final Observer<Map<String, Object>> orderObserver = new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable final Map<String, Object> data) {
                if(ll.getChildCount() > 0)
                    ll.removeAllViews();
                pb.setVisibility(View.GONE);
                for (QueryDocumentSnapshot document : (QuerySnapshot)data.get("document")) {

                    MaterialCardView materialCardView = new MaterialCardView(getActivity());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
                    params.setMargins(10, 50, 10, 10);
                    materialCardView.setLayoutParams(params);
                    materialCardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(-2, -2);
                    param.setMargins(10, 10, 10, 10);
                    LinearLayout cardlinear = new LinearLayout(getActivity());
                    cardlinear.setLayoutParams(param);
                    cardlinear.setOrientation(LinearLayout.VERTICAL);
                    TextView orderState = new TextView(getActivity());
                    if(document.get("OrderState").equals("Placed"))
                    {
                        orderState.setText("ACTIVE ORDER");
                        orderState.setTextColor(Color.parseColor("#63EC27"));
                    }
                    if(document.get("OrderState").equals("Delivered"))
                    {
                        orderState.setText("DELIVERED");
                        orderState.setTextColor(Color.parseColor("#000000"));
                    }
                    orderState.setGravity(Gravity.CENTER_HORIZONTAL);
                    cardlinear.addView(orderState);


                    TextView bhawan = new TextView(getActivity());
                    bhawan.setText("Bhawan : " + document.get("Bhawan") );
                    bhawan.setTextColor(Color.parseColor("#000000"));
                    bhawan.setLayoutParams(param);
                    cardlinear.addView(bhawan);

                    TextView date = new TextView(getActivity());
                    date.setText("Date and Time : \n" + document.get("Date") );
                    date.setTextColor(Color.parseColor("#000000"));
                    date.setLayoutParams(param);
                    cardlinear.addView(date);

                    GridLayout gl = new GridLayout(getActivity());
                    gl.setColumnCount(3);
                    gl.setLayoutParams(param);
                    gl.setBackgroundColor(Color.parseColor("#e3e2de"));



                    Map<String, Object> Items =  new HashMap<>();
                    Items.put("Item", document.get("Item"));

                    Map<String, Object> itemlist = (Map)(Items.get("Item"));
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
                            tv.setBackgroundColor(Color.parseColor("#e3e2de"));
                            tv.setId(1 + 10 * i);
                            gl.addView(tv);

                            //column2
                            TextView tv2 = new TextView(getActivity().getApplicationContext());
                            tv2.setWidth(200);
                            tv2.setText(Name);
                            tv2.setTextColor(Color.parseColor("#000000"));
                            tv2.setBackgroundColor(Color.parseColor("#e3e2de"));
                            tv2.setId(2 + 10 * i);
                            gl.addView(tv2);

                            //column3
                            TextView tv3 = new TextView(getActivity().getApplicationContext());
                            tv3.setWidth(200);
                            tv3.setText("Rs. " + Price + " X" + Quantity);
                            tv3.setTextColor(Color.parseColor("#000000"));
                            tv3.setBackgroundColor(Color.parseColor("#e3e2de"));
                            tv3.setId(3 + 10 * i);
                            gl.addView(tv3);
                            total += Price * Quantity;
                        }

                    }
                    cardlinear.addView(gl);
                    TextView tot = new TextView(getActivity());
                    tot.setText("Total = " + total);
                    tot.setTextColor(Color.parseColor("#000000"));
                    tot.setBackgroundColor(Color.parseColor("#e3e2de"));

                    tot.setLayoutParams(param);
                    cardlinear.addView(tot);

                    final String Date = (String)document.get("Date");
                    Button del = new Button(getActivity());
                    del.setText("Delete");
                    del.setBackgroundColor(Color.parseColor("#F60034"));
                    del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            db.collection("users").document("usersdoc").collection(uid)
                                    .whereEqualTo("Date", Date)
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    document.getReference().delete();

                                                }

                                            } else {
                                                Log.d(TAG, "Error getting documents: ", task.getException());
                                            }
                                        }
                                    });
                        }
                    });
                    cardlinear.addView(del);


                    materialCardView.addView(cardlinear);


                    ll.addView(materialCardView);
                }



            }};

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.getCurrentOrder().observe(this, orderObserver);









        MaterialButton home = view.findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentTransaction ft = getFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.your_placeholder, new homePageFrag());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();

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
