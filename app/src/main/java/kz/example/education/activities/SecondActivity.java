package kz.example.education.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import kz.example.education.R;
import kz.example.education.contract.SecondActivityContract;

public class SecondActivity  extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        SecondActivityContract.View{

    Switch mSwitchVisibilityOption;
    CheckBox mCheckBoxTextViewVisibility;
    RadioGroup mRadioGroupTextOptions;
    TextView mTextViewTextRepresentation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        initializeViews();
        initializeListeners();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(SecondActivity.class.getName(), "OnResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_second_activity_hide_views:
                mRadioGroupTextOptions.setVisibility(View.INVISIBLE);
                break;

            case R.id.checkbox_second_activity_deny_hide:
                break;
        }
    }

    @Override
    public void initializeListeners() {
        mCheckBoxTextViewVisibility.setOnCheckedChangeListener(this);
        mSwitchVisibilityOption.setOnCheckedChangeListener(this);
    }

    @Override
    public void initializeViews() {
        mTextViewTextRepresentation = (TextView)findViewById(R.id.textview_activity_second_text_example);
        mRadioGroupTextOptions = (RadioGroup)findViewById(R.id.radiogroup_activity_second_text_chooser);
        mCheckBoxTextViewVisibility = (CheckBox)findViewById(R.id.checkbox_second_activity_deny_hide);
        mSwitchVisibilityOption = (Switch)findViewById(R.id.switch_second_activity_hide_views);
    }
}
