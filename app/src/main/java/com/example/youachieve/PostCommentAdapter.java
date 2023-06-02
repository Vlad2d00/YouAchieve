package com.example.youachieve;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youachieve.db.entity.PostComment;
import com.example.youachieve.network.LoadImage;
import com.example.youachieve.utils.MyData;
import com.example.youachieve.utils.MyDate;
import com.example.youachieve.utils.PostCommentData;

import java.util.ArrayList;

class PostCommentAdapter extends RecyclerView.Adapter<PostCommentAdapter.PostCommentViewHolder> {
    private final ArrayList<PostCommentData> comments_;

    public PostCommentAdapter(ArrayList<PostCommentData> comments) {
        this.comments_ = comments;
    }

    @Override
    public int getItemCount() {
        return comments_.size();
    }

    @NonNull
    @Override
    public PostCommentAdapter.PostCommentViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new PostCommentViewHolder(layoutInflater.inflate(R.layout.list_item_comment, parent, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(
            @NonNull PostCommentAdapter.PostCommentViewHolder holder,
            int position)
    {
        PostCommentData comment = comments_.get(position);

        holder.commentUserFullName.setText(comment.userFirstName.concat(" " + comment.userLastName));
        holder.commentDate.setText(new MyDate(comment.datetimeCreate).toString());
        holder.commentText.setText(comment.text);

        // Изображение пользователя
        if (comment.userImageUrl != null) {
            holder.commentAvatar.setImageResource(R.drawable.download_icon);

            String url = comment.userImageUrl;
            String name = comment.userImageName;
            new LoadImage(url, name, holder.commentAvatar, MyData.appContext).execute();
        }
        else {
            holder.commentAvatar.setImageResource(R.drawable.user_avatar_none);
        }

    }

    static class PostCommentViewHolder extends RecyclerView.ViewHolder {
        TextView commentUserFullName;
        TextView commentDate;
        TextView commentText;
        ImageView commentAvatar;

        public PostCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.commentUserFullName = itemView.findViewById(R.id.commentUserFullName);
            this.commentText = itemView.findViewById(R.id.commentText);
            this.commentDate = itemView.findViewById(R.id.commentDate);
            this.commentAvatar = itemView.findViewById(R.id.commentAvatar);
        }
    }
}