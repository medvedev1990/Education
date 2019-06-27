package kz.example.education.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kz.example.education.R;
import kz.example.education.presentation.entities.UserEntity;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<UserEntity> mArrayListUsers = new ArrayList<>();

    int lastPosition = -1;

    public UsersAdapter(Context context, ArrayList<UserEntity> mArrayListUsers){
        this.context = context;
        this.mArrayListUsers = mArrayListUsers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1){
            View view = LayoutInflater.from(context).inflate(R.layout.item_user_recyclerview_user, parent, false);
            return new UsersHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_advertisement_users_list, parent, false);
            return new AdvertisementHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 1){
            ((UsersHolder)holder).bind(mArrayListUsers.get(position));
        }else{
            ((AdvertisementHolder)holder).bind(mArrayListUsers.get(position));
        }
        Animation animation = null;
        if (position > lastPosition) {
            animation = AnimationUtils.loadAnimation(context, R.anim.slide_from_bottom);
        }else{
            animation = AnimationUtils.loadAnimation(context, R.anim.slide_from_top);
        }
        lastPosition = position;
        holder.itemView.setAnimation(animation);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return mArrayListUsers.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mArrayListUsers.get(position).getName().length() > 0){
            return 1;
        }else{
            return 2;
        }
    }

    public class UsersHolder extends RecyclerView.ViewHolder{

        ImageView mImageViewImage;
        TextView mTextViewName;
        TextView mTextViewSurname;
        TextView mTextViewMark;
        TextView mTextViewGpa;
        TextView mTextViewUniversity;
        TextView mTextViewFaculty;
        SwitchCompat mSwitchCompatChecked;

        public UsersHolder(View itemView) {
            super(itemView);

             mImageViewImage = (ImageView)itemView.findViewById(R.id.imageview_item_recyclerview_user_image);
             mTextViewName = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_name);
             mTextViewSurname = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_surname);
             mTextViewMark = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_mark);;
             mTextViewGpa = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_gpa);;
             mTextViewUniversity = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_university);;
             mTextViewFaculty = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_faculty);;
             mSwitchCompatChecked = (SwitchCompat)itemView.findViewById(R.id.switch_item_user_recyclerview_switch);
        }

        public void bind(UserEntity entity){
            Picasso.get().load(R.drawable.nature).into(mImageViewImage);

            mTextViewName.setText(entity.getName());
            mTextViewSurname.setText(entity.getSurname());
            mTextViewMark.setText(Integer.toString(entity.getMark()));
            mTextViewGpa.setText(Float.toString(entity.getGPA()));
            mTextViewUniversity.setText(entity.getUniversity());
            mTextViewFaculty.setText(entity.getFaculty());

            if(entity.getChecked()){
                mSwitchCompatChecked.setChecked(true);
            }else{
                mSwitchCompatChecked.setChecked(false);
            }
        }

        public void clearAnimation(){
            itemView.clearAnimation();
        }
    }

    public class AdvertisementHolder extends RecyclerView.ViewHolder{

        ImageView mImageViewBanner;
        TextView mTextViewText;

        public AdvertisementHolder(View itemView) {
            super(itemView);

            mImageViewBanner = (ImageView) itemView.findViewById(R.id.item_advertisement_users_list_banner);
            mTextViewText = (TextView) itemView.findViewById(R.id.item_advertisement_users_list_title);
        }

        public void bind(UserEntity entity){
            Picasso.get().load(R.drawable.users_list_advertisement_icon).into(mImageViewBanner);
            mTextViewText.setText(entity.getmBannerTitle());
        }

        public void clearAnimation(){
            itemView.clearAnimation();
        }
    }
}
