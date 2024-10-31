package com.example.aguadatosapp

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin

/**
 * `AguadatosAmplify` is the main application class responsible for initializing and configuring
 * Amplify framework with DataStore and API plugins on app startup.
 */
class AguadatosAmplify : Application(){
    override fun onCreate() {
        super.onCreate()

        try {
            // Adds AWSDataStorePlugin for local and cloud data storage.
            Amplify.addPlugin(AWSDataStorePlugin())
            // Adds AWSApiPlugin for API interactions with AWS services.
            Amplify.addPlugin(AWSApiPlugin())
            // Configures Amplify with the current application context.
            Amplify.configure(applicationContext)

            Log.i("AguaDatosAmplify", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("AguaDatosAmplify", "Could not initialize Amplify", error)
        }
    }
}