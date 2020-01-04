package com.example.canteen_app.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.example.canteen_app.CheckoutFrag;
import com.example.canteen_app.CheckoutViewModel;
import com.example.canteen_app.MenuPage;
import com.example.canteen_app.MenuPageViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.canteen_app.MainActivity.Bhawan;
import static com.example.canteen_app.MainActivity.uid;

public class DatabaseHandlerForCheckout {

    public static void initialize()
    {
        try{
            final Map<String, Object> orderData = new HashMap<>();
            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection(Bhawan + "-orders").document(uid)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                orderData.put("Item", document.get("Item"));
                                try
                                {
                                    CheckoutViewModel mViewModel = ViewModelProviders.of(CheckoutFrag.frag).get(CheckoutViewModel.class);
                                    mViewModel.getCurrentOrder().setValue(orderData);
                                }catch (Exception e)
                                {}

                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

        }catch (Exception e)
        {

        }

    }


}
