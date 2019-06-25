package kz.example.education.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kz.example.education.R;
import kz.example.education.entities.UserEntity;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<UserEntity> mArrayListUsers;

    public UsersAdapter(Context context, ArrayList<UserEntity> mArrayListUsers){
        this.context = context;
        this.mArrayListUsers = mArrayListUsers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_recyclerview_user, parent);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UsersHolder extends RecyclerView.ViewHolder{

        public UsersHolder(View itemView) {
            super(itemView);
        }
    }
}
