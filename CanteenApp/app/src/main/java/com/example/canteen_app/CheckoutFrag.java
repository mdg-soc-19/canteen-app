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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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

    public static Fragment frag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        final Button Place = view.findViewById(R.id.placebu);
        //placebu - placebo!!! Geddit? No? Okay...
        final ProgressBar pb = view.findViewById(R.id.pBar);
        pb.setVisibility(View.VISIBLE);
        final GridLayout gl = view.findViewById(R.id.GridCheckout);

        frag = this;


        CheckoutViewModel mViewModel = ViewModelProviders.of(this).get(CheckoutViewModel.class);
        // Create the observer which updates the UI.
        final Observer<Map<String, Object>> orderObserver = new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable final Map<String, Object> data) {

                TextView tvsa = view.findViewById(R.id.tvcsa);
                TextView tvre = view.findViewById(R.id.tvcre);
                TextView tvga = view.findViewById(R.id.tvcga);
                if(gl.getChildCount() > 0)
                    gl.removeAllViews();
                gl.addView(tvsa);
                gl.addView(tvre);
                gl.addView(tvga);
                pb.setVisibility(View.GONE);

                Map<String, Object> itemlist = (Map)(data.get("Item"));


                int i = 1;
                int total = 0;
                for (Map.Entry<String, Object> entry : itemlist.entrySet()) {

                    System.out.println("Value is " + entry.getValue());
                    Map<String, Map<String, Object>> orderItem = new HashMap<>();
                    orderItem.put(entry.getKey(), (Map) entry.getValue());
                    String Name = (orderItem.get(entry.getKey()).get("Name")).toString();
                    String P = (orderItem.get(entry.getKey()).get("Price")).toString();
                    int Price = Integer.parseInt(P);
                    String Q = (orderItem.get(entry.getKey()).get("Quantity")).toString();
                    int Quantity = Integer.parseInt(Q);

                    if (Quantity != 0) {
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
                TextView Total = view.findViewById(R.id.TotalAmount);
                String tot = new String(total + "");
                Total.setText(tot);






            }};

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.getCurrentOrder().observe(this, orderObserver);

        Place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> OrderData = new HashMap<>();
                OrderData.put("OrderState", "Placed");
                db.collection(Bhawan + "-orders").document(uid).set(OrderData, SetOptions.merge());
                Place.setText("Order Placed");
                Place.setBackgroundColor(Color.parseColor("#63EC27"));
                db.collection(Bhawan + "-orders").document(uid)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful())
                                {
                                    DocumentSnapshot document = task.getResult();


                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                    LocalDateTime now = LocalDateTime.now();
                                    Map<String, Object> tempDoc = document.getData();
                                    tempDoc.put("Date", dtf.format(now));
                                    db.collection(Bhawan + "-orders")
                                            .add(tempDoc);
                                    //Bhawan orders doesnt need bhawan name.
                                    tempDoc.put("Bhawan", Bhawan);
                                    db.collection("users").document("usersdoc").collection(uid)
                                            .add(tempDoc);
                                    db.collection(Bhawan + "-orders").document(uid).delete();

                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    // Replace the contents of the container with the new fragment
                                    ft.replace(R.id.your_placeholder, new OrderHistoryFrag());
                                    // or ft.add(R.id.your_placeholder, new FooFragment());
                                    // Complete the changes added above
                                    ft.commit();


                                }
                                else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        });

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        MainActivity.mCurrentFragment = resumer;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CheckoutViewModel mViewModel = ViewModelProviders.of(this).get(CheckoutViewModel.class);
    }


}