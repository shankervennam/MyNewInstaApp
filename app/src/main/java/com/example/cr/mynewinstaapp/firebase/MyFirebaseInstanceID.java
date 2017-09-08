package com.example.cr.mynewinstaapp.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceID extends FirebaseInstanceIdService
{
    private static final String REG_TOKEN = "REG_TOKEN";
    @Override
    public void onTokenRefresh()
    {
        String RECENT_TOKEN = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, RECENT_TOKEN);
    }
}
