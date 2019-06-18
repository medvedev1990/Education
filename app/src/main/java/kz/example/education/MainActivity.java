package kz.example.education;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kz.example.education.contract.MainActivityContract;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        MainActivityContract.View {

    Button mButtonActionLogin;
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
        switch (v.getId()){
            case R.id.button_activity_main_transition:
                if(checkAuthorization()){
                    displayToast(getResources()
                            .getString(R.string.activity_main_toast_message_login_success));
                }
                else {
                    displayToast(getResources()
                            .getString(R.string.activity_main_toast_message_login_failure));
                }
                break;
        }
    }

    @Override
    public Boolean checkAuthorization() {
        if(mEditTextLoginField.getText().toString().length() <= 0 ||
                mEditTextPasswordField.getText().toString().length() <= 0){
            return false;
        }else{
            if(mEditTextPasswordField.getText().toString().equals("qwerty123") &&
                    mEditTextLoginField.getText().toString().equals("icarus")){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public void initializationViews() {
        mEditTextLoginField = (EditText)findViewById(R.id.edittext_activity_main_login);
        mEditTextPasswordField = (EditText)findViewById(R.id.edittext_activity_main_password);
        mButtonActionLogin = (Button)findViewById(R.id.button_activity_main_transition);
    }

    @Override
    public void initializationListeners() {
        mButtonActionLogin.setOnClickListener(this);
    }

    @Override
    public void displayToast(String withText) {
        Toast.makeText(this, withText, Toast.LENGTH_LONG).show();
    }
}
