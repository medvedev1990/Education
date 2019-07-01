package kz.example.education.presentation.contract;

public interface UsersActivityContract  {
    public interface  View {
        public void initializeViews();

        public void initializeAdapter();

        public void initializeListeners();

        public void startValueAnimator();

        public void startFadeTextAnimator();

        public void restoreButton();

        public void animateButton();

        public void initializeUseCases();
    }
}
