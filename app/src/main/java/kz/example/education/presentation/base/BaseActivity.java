package kz.example.education.presentation.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import kz.example.education.R;
import kz.example.education.presentation.activities.CameraActivity;
import kz.example.education.presentation.activities.PlayerActivity;
import kz.example.education.presentation.activities.ProfileActivity;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private Toolbar mToolbarBase;
    private TextView mTextViewToolbarTitle;
    private BottomNavigationView mBottomNavigationView;

    public Toolbar getBaseToolbar(){
        return mToolbarBase;
    }

    public TextView getBaseTextViewTitle(){
        return mTextViewToolbarTitle;
    }

    public int getLayout(){
        return R.layout.activity_base;
    }

    public BaseActivity getMainActivity(){
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        initializeSupportToolbar();
        initializeFragment();

        initializeBottomNavigationView();
        initializeBottomNavigationListener();
    }

    public abstract BaseFragment onInitFragment();

    public void initializeBottomNavigationView(){
        mBottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomnavigation_activity_base_navigation);
    }

    public void initializeBottomNavigationListener(){
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    public void initializeSupportToolbar(){
        mToolbarBase = (Toolbar)findViewById(R.id.activity_base_toolbar);
        mTextViewToolbarTitle = (TextView)findViewById(R.id.textview_activity_base_toolbar_title);

        mToolbarBase.setTitle("");
        setSupportActionBar(mToolbarBase);
    }

    public void initializeFragment(){
        if(getCurrentFragment() == null){
            displayFragment(onInitFragment());
        }
    }

    public void displayFragment(BaseFragment baseFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(fragmentManager.getBackStackEntryCount() > 0){
            fragmentTransaction.hide(getCurrentFragment());
        }

        fragmentTransaction.replace(R.id.framelayout_activity_base_fragment_container, baseFragment, baseFragment.getFragmentTag());
        fragmentTransaction.commit();
    }

    private BaseFragment getCurrentFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        int entryCount = fragmentManager.getBackStackEntryCount();
        if (entryCount > 0) {
            String fragmentTag = fragmentManager.getBackStackEntryAt(entryCount - 1).getName();
            return (BaseFragment) fragmentManager.findFragmentByTag(fragmentTag);
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
        }else{
            finish();
        }
    }

    public void navigate(Class activity){
        Intent navigator = new Intent(this, activity);
        startActivity(navigator);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list:
                mBottomNavigationView.getMenu().findItem(R.id.action_list).setChecked(true);
                break;

            case R.id.action_player:
                navigate(PlayerActivity.class);
                mBottomNavigationView.getMenu().findItem(R.id.action_player).setChecked(true);
                break;

            case R.id.action_camera:
                navigate(CameraActivity.class);
                mBottomNavigationView.getMenu().findItem(R.id.action_camera).setChecked(true);
                break;

            case R.id.action_profile:
                navigate(ProfileActivity.class);
                mBottomNavigationView.getMenu().findItem(R.id.action_profile).setChecked(true);
                break;

            case R.id.action_splash:
                mBottomNavigationView.getMenu().findItem(R.id.action_splash).setChecked(true);
                break;

            case R.id.action_alarm_add:
                Date dat  = new Date();//initializes to now
                Calendar cal_alarm = Calendar.getInstance();
                Calendar cal_now = Calendar.getInstance();
                cal_now.setTime(dat);
                cal_alarm.setTime(dat);
                cal_alarm.set(Calendar.HOUR_OF_DAY,5);//set the alarm time
                cal_alarm.set(Calendar.MINUTE, 59);
                cal_alarm.set(Calendar.SECOND,0);
                break;
        }
        return false;
    }
}
