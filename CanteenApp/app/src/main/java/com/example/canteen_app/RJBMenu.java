package com.example.canteen_app;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;



import static android.content.ContentValues.TAG;
import static com.example.canteen_app.MainActivity.Bhawan;
import static com.example.canteen_app.MainActivity.uid;

/*
public class RJBMenu extends Fragment implements View.OnClickListener, AuthStateListener {
    public int k;
    private static int resumer = 2;
    FirebaseUser user;
    boolean checkoutpossible = false;

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
                            .set(docData, SetOptions.merge());


                }


            }
        });








        db.collection(Bhawan + "-items")
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
                                LinearLayout.LayoutParams paramstv = new LinearLayout.LayoutParams(-2, -2);
                                paramstv.setMargins(0, 0, 20, 0);
                                //column1
                                TextView tv = new TextView(getActivity().getApplicationContext());

                                tv.setText(Integer.toString(i++));
                                tv.setTextColor(Color.parseColor("#000000"));
                                //tv.setBackgroundColor(Color.parseColor("#ffffff"));
                                tv.setId(1 + 10*i);
                                tv.setLayoutParams(paramstv);
                                gl.addView(tv);


                                //column2
                                TextView tv2 = new TextView(getActivity().getApplicationContext());

                                tv2.setText(name);
                                tv2.setTextColor(Color.parseColor("#000000"));
                                //tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                                tv2.setId(2 + 10*i);
                                tv2.setLayoutParams(paramstv);
                                tv2.setMaxWidth(150);
                                tv2.setSingleLine(false);
                                gl.addView(tv2);

                                //column3
                                TextView tv3 = new TextView(getActivity().getApplicationContext());

                                tv3.setText("Rs. " + price);
                                tv3.setTextColor(Color.parseColor("#000000"));
                                //tv3.setBackgroundColor(Color.parseColor("#ffffff"));
                                tv3.setId(3 + 10*i);
                                tv3.setLayoutParams(paramstv);
                                gl.addView(tv3);

                                //column4
                                LinearLayout ll = new LinearLayout(getContext());
                                ll.setOrientation(LinearLayout.HORIZONTAL);
                                ll.setPadding(0,0,2,0);

                                MaterialButton pb = new MaterialButton(getActivity(), null, R.attr.borderlessButtonStyle);

                                if(aty)
                                    pb.setBackgroundColor(Color.parseColor("#D81B60"));
                                else
                                    pb.setBackgroundColor(Color.parseColor("#696969"));
                                pb.setText("+");

                                pb.setTextColor(Color.parseColor("#000000"));



                                System.out.println("View grp layout is " + ViewGroup.LayoutParams.WRAP_CONTENT);

                                //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, 120);
                                //params.setMargins(0, 0, 10, 10);
                                pb.setMinWidth(80);
                                pb.setMinimumWidth(80);

                                pb.setLayoutParams(paramstv);




                                MaterialButton mb = new MaterialButton(getActivity(), null, R.attr.borderlessButtonStyle);
                                //Button mb = new Button(getActivity().getApplicationContext());

                                if(aty)
                                    mb.setBackgroundColor(Color.parseColor("#D81B60"));
                                else
                                    mb.setBackgroundColor(Color.parseColor("#696969"));
                                mb.setText("-");
                                mb.setTextColor(Color.parseColor("#000000"));
                                mb.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                                int t = ViewGroup.LayoutParams.WRAP_CONTENT;
                                mb.setMinWidth(80);
                                mb.setMinimumWidth(80);

                                mb.setLayoutParams(paramstv);



                                ll.addView(pb);
                                ll.addView(mb);
                                gl.addView(ll);




                                final Item_Quant iq = new Item_Quant();

                                db.collection(Bhawan + "-orders").document(uid)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful())
                                                {
                                                    DocumentSnapshot document = task.getResult();
                                                    Map<String, Object> tempMap= (Map)document.get("Item");
                                                    if(tempMap !=null)
                                                    {
                                                        Map<String, Object> tempMap1 = (Map)tempMap.get(name);
                                                        if(tempMap1!=null)
                                                        {
                                                            Long quant = (Long) tempMap1.get("Quantity");
                                                            iq.quant = quant.intValue();
                                                            if(quant!=0)
                                                            {
                                                                checkoutpossible = true;
                                                            }

                                                        }
                                                    }



                                                }
                                                else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });

                                //ORDERING
                                if(aty) {
                                    pb.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            iq.quant_incrementer();
                                            int id = v.getId();
                                            checkoutpossible = true;
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

                                            if(iq.quant==0)
                                                checkoutpossible=false;

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

                if(checkoutpossible)
                {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.your_placeholder, new CheckoutFrag());
                    // or ft.add(R.id.your_placeholder, new FooFragment());
                    // Complete the changes added above
                    ft.commit();
                }
                else
                {
                    Toast toast = Toast.makeText(getActivity(), "Please place something into the cart before checking out.", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });



        return view;
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        String uid = "PLACEHOLDER";
        for (UserInfo profile : user.getProviderData())
        {
            uid = profile.getUid();
        }
        Toast toast = Toast.makeText(getActivity(),"UID is " + uid, Toast.LENGTH_LONG);
        toast.show();
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
*/