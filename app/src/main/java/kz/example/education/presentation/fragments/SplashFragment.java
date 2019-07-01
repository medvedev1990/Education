package kz.example.education.presentation.fragments;

import android.graphics.Color;
import android.os.Bundle;
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

public class SplashFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        System.out.println("CALLED");

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
}
