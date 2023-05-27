package com.example.youachieve;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youachieve.db.entity.User;
import com.example.youachieve.network.LoadImage;

import java.util.ArrayList;

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.UserViewHolder> {
    private ArrayList<User> users_;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public UserDetailAdapter(ArrayList<User> userList) {
        this.users_ = userList;
    }

    @Override
    public int getItemCount() {
        return users_.size();
    }

    @NonNull
    @Override
    public UserDetailAdapter.UserViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new UserViewHolder(layoutInflater.inflate(R.layout.list_item_post_detail, parent, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(
            @NonNull UserDetailAdapter.UserViewHolder holder,
            int position)
    {
        User user = users_.get(position);

        holder.userNickname.setText(user.username);
        holder.userFullName.setText(user.firstName.concat(" " + user.lastName));
        holder.userDescription.setText(user.description);

        // Изображение пользователя
//        if (user.imageUrl != null) {
//            holder.userAvatar.setImageResource(R.drawable.download_icon);
//            new LoadImage(user.imageUrl, holder.userAvatar).execute();
//        }
//        else {
//            holder.userAvatar.setImageResource(R.drawable.user_avatar_none);
//        }

    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userNickname;
        TextView userFullName;
        TextView userDescription;
        ImageView userAvatar;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.userNickname = itemView.findViewById(R.id.userNickname);
            this.userFullName = itemView.findViewById(R.id.userFullName);
            this.userDescription = itemView.findViewById(R.id.userDescription);
            this.userAvatar = itemView.findViewById(R.id.userAvatar);
        }
    }
}