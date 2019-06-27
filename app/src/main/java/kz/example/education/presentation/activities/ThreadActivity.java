package kz.example.education.presentation.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import kz.example.education.R;
import kz.example.education.presentation.contract.ThreadActivityContract;

public class ThreadActivity extends Activity implements
        ThreadActivityContract.View,
        View.OnClickListener{

    Button mButtonThreadLaunch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        initializeViews();
        initializeListeners();
    }

    @Override
    public void initializeViews() {
        mButtonThreadLaunch = (Button)findViewById(R.id.button_activity_thread_launch_task);
    }

    @Override
    public void initializeListeners() {
        mButtonThreadLaunch.setOnClickListener(this);
    }

    @Override
    public void launchThreadTask() {
        Compute computeThread = new Compute();
        computeThread.start();
    }

    @Override
    public void launchAsyncTaskThread() {
        CustomAsyncTask customAsyncTask = new CustomAsyncTask(3);
        customAsyncTask.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_activity_thread_launch_task:
                //launchThreadTask();
                //launchAsyncTaskThread();
                delayedTask();
                break;
        }
    }

    @Override
    public void delayedTask() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "MESSAGE", Toast.LENGTH_LONG).show();
            }
        }, 2000);
    }

    class Compute extends Thread{
        @Override
        public void run() {
            super.run();

            long endTime = System.currentTimeMillis() + 5 * 1000;

            while (System.currentTimeMillis() < endTime){
                synchronized (this){
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d(ThreadActivity.class.getSimpleName(), "RESULT");

            Bundle data = new Bundle();
            data.putString("result", "DONE");
            Message message = new Message();
            message.setData(data);

            handler.sendMessage(message);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mButtonThreadLaunch.setText(msg.getData().getString("result"));
        }
    };

    class CustomAsyncTask extends AsyncTask<ArrayList<Integer>, Void, String>{

        String resultString = "";

        public CustomAsyncTask(int numberSeconds){
            this.seconds = numberSeconds;
        }

        int seconds = 0;

        @Override
        protected String doInBackground(ArrayList<Integer>... arrayLists) {
            long endTime = System.currentTimeMillis() + seconds * 1000;

            while (System.currentTimeMillis() < endTime){
                synchronized (this){
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            resultString = "Text";
            return resultString;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mButtonThreadLaunch.setText("AsyncTask запущен");
        }

        @Override
        protected void onPostExecute(String text) {
            super.onPostExecute(text);
            mButtonThreadLaunch.setText(resultString);
        }
    }
}
