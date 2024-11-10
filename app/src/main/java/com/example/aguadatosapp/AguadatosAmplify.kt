package com.example.aguadatosapp

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.kotlin.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin

/**
 * `AguadatosAmplify` is the main application class responsible for initializing and configuring
 * Amplify framework with DataStore and API plugins on app startup.
 */
class AguadatosAmplify : Application() {
    override fun onCreate() {
        super.onCreate()

        try {
            // Adds AWSAuthPlugin for authorization with AWS services.
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            // Adds AWSDataStorePlugin for local and cloud data storage.
            Amplify.addPlugin(AWSDataStorePlugin())
            // Adds AWSApiPlugin for API interactions with AWS services.
            Amplify.addPlugin(AWSApiPlugin())
            // Adds AWSCognitoAuthPlugin for user authentication.
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            // Configures Amplify with the current application context.
            Amplify.configure(applicationContext)

            Log.i("AguadatosAmplify", "Initialized Amplify successfully")
        } catch (error: AmplifyException) {
            Log.e("AguadatosAmplify", "Could not initialize Amplify", error)
        }
    }
}