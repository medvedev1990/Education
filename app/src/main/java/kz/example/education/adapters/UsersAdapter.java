package kz.example.education.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kz.example.education.R;
import kz.example.education.entities.UserEntity;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<UserEntity> mArrayListUsers = new ArrayList<>();

    public UsersAdapter(Context context, ArrayList<UserEntity> mArrayListUsers){
        this.context = context;
        this.mArrayListUsers = mArrayListUsers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_recyclerview_user, parent, false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UsersHolder usersHolder = (UsersHolder)holder;

        Picasso.get().load(R.drawable.nature).into(usersHolder.mImageViewImage);

        usersHolder.mTextViewName.setText(mArrayListUsers.get(position).getName());
        usersHolder.mTextViewSurname.setText(mArrayListUsers.get(position).getSurname());
        usersHolder.mTextViewMark.setText(Integer.toString(mArrayListUsers.get(position).getMark()));
        usersHolder.mTextViewGpa.setText(Float.toString(mArrayListUsers.get(position).getGPA()));
        usersHolder.mTextViewUniversity.setText(mArrayListUsers.get(position).getUniversity());
        usersHolder.mTextViewFaculty.setText(mArrayListUsers.get(position).getFaculty());
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

        public UsersHolder(View itemView) {
            super(itemView);

             mImageViewImage = (ImageView)itemView.findViewById(R.id.imageview_item_recyclerview_user_image);
             mTextViewName = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_name);
             mTextViewSurname = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_surname);
             mTextViewMark = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_mark);;
             mTextViewGpa = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_gpa);;
             mTextViewUniversity = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_university);;
             mTextViewFaculty = (TextView)itemView.findViewById(R.id.textview_item_recyclerview_user_faculty);;
        }
    }
}
