package kz.example.education.activities;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import kz.example.education.R;
import kz.example.education.adapters.UsersAdapter;
import kz.example.education.contract.UsersActivityContract.View;
import kz.example.education.entities.UserEntity;

public class UsersListActivity extends AppCompatActivity implements View, android.view.View.OnClickListener{

    RecyclerView mRecyclerViewUsers;
    LinearLayoutManager mLinearLayoutManagerVerticalUsers;
    UsersAdapter mUsersAdapterUsers;

    FrameLayout mButtonAnim;
    TextView mTextViewText;
    ProgressBar mProgressBarLoader;

    ArrayList<UserEntity> mArrayListUsers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        initializeViews();
        initializeArray();
        initializeAdapter();
    }

    @Override
    public void initializeViews() {
        mButtonAnim = (FrameLayout) findViewById(R.id.framelayout_activity_users_layout);
        mTextViewText = (TextView) findViewById(R.id.textview_activity_users_list_animated);
        mProgressBarLoader = (ProgressBar) findViewById(R.id.progressbar_activity_users_loader);

        mRecyclerViewUsers = (RecyclerView)findViewById(R.id.recyclerview_activity_user_list_users);
        mButtonAnim.setOnClickListener(this);
    }

    @Override
    public void initializeAdapter() {
        mUsersAdapterUsers = new UsersAdapter(this, mArrayListUsers);
        mLinearLayoutManagerVerticalUsers = new LinearLayoutManager(this);
        mRecyclerViewUsers.setLayoutManager(mLinearLayoutManagerVerticalUsers);
        mRecyclerViewUsers.setAdapter(mUsersAdapterUsers);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this,
                R.anim.recyclerview_layout_animation_fall);

        mRecyclerViewUsers.setLayoutAnimation(animation);
    }

    @Override
    public void initializeListeners() {
        mButtonAnim.setOnClickListener(this);
    }

    @Override
    public void startFadeTextAnimator() {
        final ValueAnimator fadeAnimator = new ValueAnimator();
        fadeAnimator.setFloatValues(1.0f, 0.0f);
        fadeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float newAlpha = (Float)fadeAnimator.getAnimatedValue();
                mTextViewText.setAlpha(newAlpha);
            }
        });
        fadeAnimator.setDuration(250);
        fadeAnimator.start();
    }

    @Override
    public void startValueAnimator(){
        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(mButtonAnim.getWidth(), getFabWidth());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int newSize = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mButtonAnim.getLayoutParams();
                layoutParams.width = newSize;
                mButtonAnim.setLayoutParams(layoutParams);
                mButtonAnim.requestLayout();
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressBarLoader.setVisibility(android.view.View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(500);
        animator.start();
    }

    private int getFabWidth() {
        return (int) getResources().getDimension(R.dimen.fab_size);
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

            if(i%5 == 0){
                userEntity.setName("");
                userEntity.setmBannerImage("https://images.pexels.com/photos/302769/pexels-photo-302769.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                userEntity.setmBannerTitle("Услуги строительной компании!");
            }

            mArrayListUsers.add(userEntity);
        }
    }

    @Override
    public void onClick(android.view.View v) {
        startFadeTextAnimator();
        startValueAnimator();
    }
}
