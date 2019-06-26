package kz.example.education.contract;

import android.view.View;

public interface UsersActivityContract  {
    public interface  View {
        public void initializeViews();

        public void initializeAdapter();

        public void initializeArray();

        public void initializeListeners();

        public void startValueAnimator();

        public void startFadeTextAnimator();
    }
}
