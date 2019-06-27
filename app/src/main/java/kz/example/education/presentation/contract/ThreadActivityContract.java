package kz.example.education.presentation.contract;

public interface ThreadActivityContract {
    interface View{
        public void initializeViews();

        public void initializeListeners();

        public void launchThreadTask();

        public void launchAsyncTaskThread();

        public void delayedTask();
    }
}
