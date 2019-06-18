package kz.example.education.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import kz.example.education.R;

public class SecondActivity  extends AppCompatActivity{

    View view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Log.d(SecondActivity.class.getName(), "OnCreate");
        if (savedInstanceState != null){
            System.out.println("HERE: " + savedInstanceState.getString("SAVE"));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bundle data = new Bundle();
        data.putString("DATA", "ttexx");
        onSaveInstanceState(data);
        Log.d(SecondActivity.class.getName(), "OnPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(SecondActivity.class.getName(), "OnStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(SecondActivity.class.getName(), "OnStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(SecondActivity.class.getName(), "OnResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(SecondActivity.class.getName(), "OnRestart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            System.out.println("DATA: " + savedInstanceState);
        }
        Log.d(SecondActivity.class.getName(), "OnRestore");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("SAVE", "messsage");
        Log.d(SecondActivity.class.getName(), "OnSaved");
    }
}
