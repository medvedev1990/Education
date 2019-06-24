package kz.example.education.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import kz.example.education.R;
import kz.example.education.receiver.WifiStateReceiver;
import kz.example.education.contract.MainActivityContract;
import kz.example.education.utils.Constants;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        MainActivityContract.View,
        MultiplePermissionsListener{

    Button mButtonActionLogin;
    Button mButtonActionShare;
    EditText mEditTextLoginField;
    EditText mEditTextPasswordField;

    WifiStateReceiver wifiStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializationViews();
        initializationListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_activity_main_transition:
                if (checkAuthorization()) {
                    displayToast(getResources()
                            .getString(R.string.activity_main_toast_message_login_success));
                    transitionSecondActiviy();
                } else {
                    displayToast(getResources()
                            .getString(R.string.activity_main_toast_message_login_failure));
                }
                break;

            case R.id.button_activity_main_share:
                shareAction();
                //callReceiver();
                break;
        }
    }

    @Override
    public Boolean checkAuthorization() {
        if (mEditTextLoginField.getText().toString().length() <= 0 ||
                mEditTextPasswordField.getText().toString().length() <= 0) {
            return false;
        } else {
            if (mEditTextPasswordField.getText().toString().equals("qwerty123") &&
                    mEditTextLoginField.getText().toString().equals("icarus")) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void initializationViews() {
        mEditTextLoginField = (EditText) findViewById(R.id.edittext_activity_main_login);
        mEditTextPasswordField = (EditText) findViewById(R.id.edittext_activity_main_password);
        mButtonActionLogin = (Button) findViewById(R.id.button_activity_main_transition);
        mButtonActionShare = (Button) findViewById(R.id.button_activity_main_share);
    }

    @Override
    public void initializationListeners() {
        mButtonActionLogin.setOnClickListener(this);
        mButtonActionShare.setOnClickListener(this);
    }

    @Override
    public void displayToast(String withText) {
        Toast.makeText(this, withText, Toast.LENGTH_LONG).show();
    }

    @Override
    public void transitionSecondActiviy() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Constants.REQUEST_CODE_CAMERA_PERMISSION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }else{
                Toast.makeText(this, "You denied CAMERA permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void shareAction() {
        //String number = "87078382671";
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA}, Constants.REQUEST_CODE_CAMERA_PERMISSION);
            /*Dexter.withActivity(this).withPermissions(
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.CAMERA).
                    withListener(this);*/
        }else{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        }
        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //intent.setData(Uri.parse("tel:" +number));
        //startActivity(intent);
        //intent.setType("text/plain");
        //intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"TITLE");
        //intent.putExtra(android.content.Intent.EXTRA_TEXT, "Я сообщение");

        //startActivity(Intent.createChooser(intent, "Поделиться"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        wifiStateReceiver = new WifiStateReceiver();
        //registerReceiver(wifiStateReceiver, new IntentFilter("ACTION"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(wifiStateReceiver);
    }

    @Override
    public void callReceiver() {
        Intent intent = new Intent("ACTION");
        sendBroadcast(intent);
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

    }
}
