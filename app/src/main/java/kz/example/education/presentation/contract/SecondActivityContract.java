package kz.example.education.presentation.contract;

import android.view.animation.Animation;

public interface SecondActivityContract {

    public interface View{
        public void initializeListeners();

        public void initializeViews();

        public Animation initializeAnimation();

        public void startAnimation();

        public void runTransitionAnimation();
    }
}
