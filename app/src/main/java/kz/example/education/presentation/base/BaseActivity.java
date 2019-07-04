package kz.example.education.presentation.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbarBase;
    private TextView mTextViewToolbarTitle;
    private BottomNavigationView mBottomNavigationView;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

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
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "мой будильник")
                        .putExtra(AlarmClock.EXTRA_HOUR, 13)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 15);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                mBottomNavigationView.getMenu().findItem(R.id.action_splash).setChecked(true);
                break;

        }
        return false;
    }
    public void initializaNavigationDrawer(){
        mDrawerLayout = new DrawerLayout(this);
    }
    public void initializaNavigationview(){
        mNavigationView = (NavigationView)findViewById(R.id.navigationview_activity_start_navigator);
    }

    public void initializedDrawer(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbarBase,R.string.Drawable_view_open,R.string.Drawable_view_close){

            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
            }
        @Override
            public void onDrawerClosed(View drawerView){
            super.onDrawerClosed(drawerView);}
        };
    }
    public void InitializedListeneer(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return false;
            }
        });
    }
}
