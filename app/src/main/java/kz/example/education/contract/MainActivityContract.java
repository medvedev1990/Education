package kz.example.education.contract;

public interface MainActivityContract {

    public interface View{
        public Boolean checkAuthorization();

        public void initializationViews();

        public void initializationListeners();

        public void displayToast(String withText);

        public void transitionSecondActiviy();

        public void shareAction();
    }
}
