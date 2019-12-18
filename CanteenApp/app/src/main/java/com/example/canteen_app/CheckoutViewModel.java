package com.example.canteen_app;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.canteen_app.model.DatabaseHandlerForCheckout;
import com.example.canteen_app.model.DatabaseHandlerForRJBMenu;

import java.util.Map;

public class CheckoutViewModel extends ViewModel {

    private MutableLiveData<Map<String, Object>> orderData;
    public MutableLiveData<Map<String, Object>> getCurrentOrder() {
        if (orderData == null) {
            orderData = new MutableLiveData<Map<String, Object>>();
        }
        return orderData;
    }
    public CheckoutViewModel()
    {



        DatabaseHandlerForCheckout.initialize();
    }


}
