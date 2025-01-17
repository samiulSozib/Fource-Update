package com.example.fourceupdatefirst;


import android.app.Application;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);


        final FirebaseRemoteConfig remoteConfig=FirebaseRemoteConfig.getInstance();

        Map<String,Object> defaultValue=new HashMap<>();

        defaultValue.put(UpdateHelper.KEY_UPDATE_ENABLE,"false");
        defaultValue.put(UpdateHelper.KEY_UPDATE_VERSION,"1.0");
        defaultValue.put(UpdateHelper.KEY_UPDATE_URL,"https://play.google.com/store/apps/details?id=com.miniclip.carrom");
        //defaultValue.put(UpdateHelper.KEY_UPDATE_URL,"https://play.google.com/store/apps/details?id="+getPackageName());


        remoteConfig.setDefaults(defaultValue);
        remoteConfig.fetch(5)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            remoteConfig.activateFetched();

                        }
                    }
                });
    }
}
