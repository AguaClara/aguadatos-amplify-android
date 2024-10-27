package com.example.aguadatosapp

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin


class
AguaDatosAmplifyApp : Application(){
    override fun onCreate() {
        super.onCreate()

        try {
            //Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)
            Log.i("AguaDatosAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("AguaDatosAmplifyApp", "Could not initialize Amplify", error)
        }
    }
}