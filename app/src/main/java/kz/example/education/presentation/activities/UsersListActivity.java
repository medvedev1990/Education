package kz.example.education.presentation.activities;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import kz.example.education.R;
import kz.example.education.domain.usecase.GetUsersUseCase;
import kz.example.education.presentation.adapters.UsersAdapter;
import kz.example.education.presentation.contract.UsersActivityContract.View;
import kz.example.education.presentation.entities.UserEntity;

public class UsersListActivity extends AppCompatActivity implements View, android.view.View.OnClickListener{

    RecyclerView mRecyclerViewUsers;
    LinearLayoutManager mLinearLayoutManagerVerticalUsers;
    UsersAdapter mUsersAdapterUsers;

    FrameLayout mButtonAnim;
    TextView mTextViewText;
    ProgressBar mProgressBarLoader;

    GetUsersUseCase getUsersUseCase;

    ArrayList<UserEntity> mArrayListUsers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        initializeViews();
        initializeAdapter();
    }

    @Override
    public void initializeContract() {
        getUsersUseCase = new GetUsersUseCase();
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
        animator.setIntValues(getInitialWidth(), getFabWidth());
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

    private int getInitialWidth() { return (int) getResources().getDimension(R.dimen.initial_button_size); }

    @Override
    public void onClick(android.view.View v) {
        UsersListLoader loader = new UsersListLoader();
        loader.execute();
    }

    @Override
    public void animateButton() {
        startFadeTextAnimator();
        startValueAnimator();
    }

    @Override
    public void restoreButton() {
        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(getFabWidth(), getInitialWidth());
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
                mProgressBarLoader.setVisibility(android.view.View.GONE);
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

        final ValueAnimator fadeAnimator = new ValueAnimator();
        fadeAnimator.setFloatValues(0.0f, 1.0f);
        fadeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float newAlpha = (Float)fadeAnimator.getAnimatedValue();
                mTextViewText.setAlpha(newAlpha);
            }
        });
        fadeAnimator.setDuration(510);
        fadeAnimator.start();
    }

    class UsersListLoader extends AsyncTask<Integer, Void, ArrayList<UserEntity>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            animateButton();
            mArrayListUsers.clear();
            mUsersAdapterUsers.notifyDataSetChanged();
        }

        @Override
        protected ArrayList<UserEntity> doInBackground(Integer... integers) {

            ArrayList<UserEntity> mArrayListUsers = new ArrayList<>();

            mArrayListUsers = getUsersUseCase.initializeUsers();

            return mArrayListUsers;
        }

        @Override
        protected void onPostExecute(ArrayList<UserEntity> userEntities) {
            super.onPostExecute(userEntities);
            mArrayListUsers.addAll(userEntities);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mUsersAdapterUsers.notifyDataSetChanged();
                    restoreButton();
                }
            }, 1000);
        }
    }
}
