package com.example.canteen_app;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.canteen_app.MainActivity.Bhawan;
import static com.example.canteen_app.MainActivity.uid;
import com.example.canteen_app.model.DatabaseHandlerForRJBMenu;


public class MenuPageViewModel extends ViewModel {
    // TODO: Implement the ViewModel



    private MutableLiveData<Map<String, Object>> Bhawanitems;

    public MutableLiveData<Map<String, Object>> getCurrentMenu() {
        if (Bhawanitems == null) {
            Bhawanitems = new MutableLiveData<Map<String, Object>>();
        }
        return Bhawanitems;
    }


public MenuPageViewModel()
{



    DatabaseHandlerForRJBMenu.initialize();
}


}
