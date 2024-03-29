package kz.example.education.presentation.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import kz.example.education.R;
import kz.example.education.presentation.activities.SplashActivity;
import kz.example.education.presentation.base.BaseFragment;
import kz.example.education.presentation.contract.SplashFragmentContract;

public class SplashFragment extends BaseFragment implements SplashFragmentContract.View{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        System.out.println("CALLED");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPagerTransition();
            }
        }, 3000);

        return baseView;
    }

    @Override
    public String getFragmentTag() {
        return SplashFragment.class.getName();
    }

    @Override
    public void customizeActionBar() {
        ((SplashActivity)getActivity()).getBaseTextViewTitle().setText("ЭТО СПЛАШЬ");
        ((SplashActivity)getActivity()).getBaseTextViewTitle().setTextColor(Color.RED);
    }

    @Override
    public int onLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void viewPagerTransition() {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        getBaseActivity().displayFragment(viewPagerFragment);
    }
}
