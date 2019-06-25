package kz.example.education.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import kz.example.education.R;
import kz.example.education.contract.UsersActivityContract;
import kz.example.education.contract.UsersActivityContract.View;
import kz.example.education.entities.UserEntity;

public class UsersListActivity extends AppCompatActivity implements View{

    RecyclerView mRecyclerViewUsers;
    LinearLayoutManager mLinearLayoutManagerVerticalUsers;
    ArrayList<UserEntity> mArrayListUsers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
    }

    @Override
    public void initializeViews() {
        mRecyclerViewUsers = (RecyclerView)findViewById(R.id.recyclerview_activity_user_list_users);
    }

    @Override
    public void initializeAdapter() {
        mLinearLayoutManagerVerticalUsers = new LinearLayoutManager(this);
        mRecyclerViewUsers.setLayoutManager(mLinearLayoutManagerVerticalUsers);
    }

    @Override
    public void initializeArray() {
        for(int i = 0; i < 20; i++){
            UserEntity userEntity = new UserEntity();

            userEntity.setName("John");
            userEntity.setSurname("Wayne");
            userEntity.setAddress("Address");
            userEntity.setCountry("Great Britain");
            userEntity.setFaculty("Engineering");
            userEntity.setGPA(3.3f);
            userEntity.setMark(95);
            userEntity.setUniversity("Royal University");
        }
    }
}
