package kz.example.education;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permission;

import kz.example.education.activities.SecondActivity;
import kz.example.education.contract.MainActivityContract;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        MainActivityContract.View {

    Button mButtonActionLogin;
    Button mButtonActionShare;
    EditText mEditTextLoginField;
    EditText mEditTextPasswordField;

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
    public void shareAction() {
        String number = "87078382671";
        Intent intent = new Intent(Intent.ACTION_CAMERA_BUTTON);
        intent.setData(Uri.parse("tel:" +number));
        startActivity(intent);
        //intent.setType("text/plain");
        //intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"TITLE");
        //intent.putExtra(android.content.Intent.EXTRA_TEXT, "Я сообщение");

        //startActivity(Intent.createChooser(intent, "Поделиться"));
    }
}
