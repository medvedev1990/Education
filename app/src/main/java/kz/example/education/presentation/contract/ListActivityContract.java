package kz.example.education.presentation.contract;

public interface ListActivityContract {
    interface View{
        public void initializeViews();

        public void initializeListeners();

        public void populateList();

        public void initializeAdapter();
    }
}
