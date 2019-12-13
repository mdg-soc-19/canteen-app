package com.example.canteen_app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.firestore.SetOptions;
=======
>>>>>>> parent of cd98f18... Commit - chenges in auth

import java.util.HashMap;
import java.util.Map;



import static android.content.ContentValues.TAG;
import static com.example.canteen_app.MainActivity.Bhawan;
import static com.example.canteen_app.MainActivity.uid;


public class RJBMenu extends Fragment implements View.OnClickListener {
    public int k;
    private static int resumer = 2;
<<<<<<< HEAD
    FirebaseUser user;

=======
>>>>>>> parent of cd98f18... Commit - chenges in auth
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rjbmenu, container, false);
        // Inflate the layout for this fragment


        final GridLayout gl = view.findViewById(R.id.grid);
        final Context thiscontext = this.getActivity();
        //query code
        final ProgressBar pb = view.findViewById(R.id.pBar);
        pb.setVisibility(View.VISIBLE);
<<<<<<< HEAD
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        System.out.println("REACHED");
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
                    }
                    Toast toast = Toast.makeText(getActivity(),"UID is " + uid, Toast.LENGTH_LONG);
                    toast.show();
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("OrderState", "notCheckedOut");
                    docData.put("uid", uid);


                    db.collection(Bhawan + "-orders").document(uid)
                            .set(docData);


                }


            }
        });








        db.collection(Bhawan + "-items")
=======
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("RJB-items")
>>>>>>> parent of cd98f18... Commit - chenges in auth
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 1;
                            pb.setVisibility(View.GONE);
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Item item = document.toObject(Item.class);
                                final String name = item.Name;
                                final int price = item.Price;

                                final boolean aty = item.Availablity;

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
                                LinearLayout ll = new LinearLayout(getContext());
                                ll.setOrientation(LinearLayout.HORIZONTAL);
                                ll.setPadding(0,0,2,0);

                                Button pb = new Button(getActivity().getApplicationContext());
                                pb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                if(aty)
                                    pb.setBackgroundColor(Color.parseColor("#D81B60"));
                                else
                                    pb.setBackgroundColor(Color.parseColor("#696969"));
                                pb.setText("ADD");



                                Button mb = new Button(getActivity().getApplicationContext());
                                mb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                if(aty)
                                    mb.setBackgroundColor(Color.parseColor("#D81B60"));
                                else
                                    mb.setBackgroundColor(Color.parseColor("#696969"));
<<<<<<< HEAD
                                mb.setText("REMOVE");
                                ll.addView(pb);
                                ll.addView(mb);
                                gl.addView(ll);
=======
                                mb.setText("ADD");
                                mb.setId(i + 10*4);
>>>>>>> parent of cd98f18... Commit - chenges in auth

                                mb.setText("ADD");
                                mb.setId(i + 10*4);

<<<<<<< HEAD


                                final Item_Quant iq = new Item_Quant();
                                //ORDERING
                                if(aty) {
                                    pb.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            iq.quant_incrementer();
                                            int id = v.getId();
                                            Toast toast = Toast.makeText(getActivity(), "No. of " + name + " in cart:" + iq.quant, Toast.LENGTH_LONG);
                                            toast.show();
                                            Map<String, Object> item = new HashMap<>();
                                            item.put("Name", name);
                                            item.put("Price", price);
                                            item.put("Quantity", iq.quant);
                                            Map<String, Object> itemmap = new HashMap<>();
                                            itemmap.put(name, item);
                                            Map<String, Object> toplayer = new HashMap<>();
                                            toplayer.put("Item", itemmap);

                                            db.collection(Bhawan + "-orders").document(uid)
                                                    .set(toplayer, SetOptions.merge())
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast toast = Toast.makeText(getActivity(), "Adding to cart failed", Toast.LENGTH_LONG);
                                                            toast.show();
                                                        }
                                                    });


                                        }
                                    });


                                    mb.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if(iq.quant!=0)
                                            iq.quant_decrementer();
                                            int id = v.getId();
                                            Toast toast = Toast.makeText(getActivity(), "No. of " + name + " in cart:" + iq.quant, Toast.LENGTH_LONG);
                                            toast.show();
                                            Map<String, Object> item = new HashMap<>();
                                            item.put("Name", name);
                                            item.put("Price", price);
                                            item.put("Quantity", iq.quant);
                                            Map<String, Object> itemmap = new HashMap<>();
                                            itemmap.put(name, item);
                                            Map<String, Object> toplayer = new HashMap<>();
                                            toplayer.put("Item", itemmap);

                                            db.collection(Bhawan + "-orders").document(uid)
                                                    .set(toplayer, SetOptions.merge())
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast toast = Toast.makeText(getActivity(), "Adding to cart failed", Toast.LENGTH_LONG);
                                                            toast.show();
                                                        }
                                                    });


                                        }
                                    });





                                }
                                //ENDOF ORDERING

=======
                                gl.addView(mb);
                                mb.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int id = v.getId();
                                        Toast toast = Toast.makeText(getActivity(), "id is " + id, Toast.LENGTH_LONG);
                                        toast.show();
                                    }
                                });
>>>>>>> parent of cd98f18... Commit - chenges in auth
                                //end of adding
                            }
                            k = i;
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        Button back = view.findViewById(R.id.backb);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.your_placeholder, new homePageFrag());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
            }
        });
        Button checko = view.findViewById(R.id.checkb);
        checko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.your_placeholder, new CheckoutFrag());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
            }
        });



        return view;
    }


    public void onClick(View v)
    {


    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.mCurrentFragment = resumer;
    }
}

class Item
{
    public String Name;
    public int Price, Quantity;
    public Boolean Availablity;

}
<<<<<<< HEAD
class Item_Quant
{
    int quant;
    public void quant_incrementer()
    {
        quant++;
    }
    public void quant_decrementer() { quant--;}
}

class order
{
    private String OrderState = "notCheckedOut";
    class Items
    {

    }
}
=======
>>>>>>> parent of cd98f18... Commit - chenges in auth
