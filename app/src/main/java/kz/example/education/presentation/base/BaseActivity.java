package kz.example.education.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import kz.example.education.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbarBase;
    private TextView mTextViewToolbarTitle;

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
    }

    public abstract BaseFragment onInitFragment();

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
}
