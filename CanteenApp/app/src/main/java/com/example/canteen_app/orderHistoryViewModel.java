package com.example.canteen_app;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.canteen_app.model.DatabaseHandlerForCheckout;
import com.example.canteen_app.model.DatabaseHandlerForOrderHistory;

import java.util.Map;

public class orderHistoryViewModel extends ViewModel {


    private MutableLiveData<Map<String, Object>> orderHistory;
    public MutableLiveData<Map<String, Object>> getCurrentOrder() {
        if (orderHistory == null) {
            orderHistory = new MutableLiveData<Map<String, Object>>();
        }
        return orderHistory;
    }

    public orderHistoryViewModel()
    {
        DatabaseHandlerForOrderHistory.initialize();
    }
}
