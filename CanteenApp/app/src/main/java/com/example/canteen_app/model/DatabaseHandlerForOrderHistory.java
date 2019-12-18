package com.example.canteen_app.model;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.canteen_app.CheckoutFrag;
import com.example.canteen_app.CheckoutViewModel;
import com.example.canteen_app.OrderHistoryFrag;
import com.example.canteen_app.R;
import com.example.canteen_app.homePageFrag;
import com.example.canteen_app.orderHistoryViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.canteen_app.MainActivity.uid;

public class DatabaseHandlerForOrderHistory {

    public static void initialize()
    {
        final Map<String, Object> data = new HashMap<>();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document("usersdoc").collection(uid).orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            data.put("document", task.getResult());
                            orderHistoryViewModel mViewModel = ViewModelProviders.of(OrderHistoryFrag.frag).get(orderHistoryViewModel.class);
                            mViewModel.getCurrentOrder().setValue(data);
                        }
                        else
                        {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }
                });



    }


}
