package com.example.coffee_lovers_mrcoffee.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.coffee_lovers_mrcoffee.Container;
import com.example.coffee_lovers_mrcoffee.R;
import com.example.coffee_lovers_mrcoffee.services.AuthService;
import com.example.coffee_lovers_mrcoffee.ui.customer.CustomerProfileActivity;

import io.reactivex.rxjava3.disposables.Disposable;

public class StartupActivity extends AppCompatActivity {

    // dependencies
    Container container = Container.instant();
    AuthService authService = container.authService;

    // disposables
    Disposable customerListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        // check if customer is already logged in
        customerListener = authService.currentUser.subscribe(customer -> {

            Intent intent;

            if (customer == null) {
                intent = new Intent(StartupActivity.this, SignInActivity.class);
            } else {
                intent = new Intent(StartupActivity.this, CustomerProfileActivity.class);
            }

            startActivity(intent);
            finish();

        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        customerListener.dispose();
    }
}