package com.example.youachieve;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youachieve.data.Comment;
import com.example.youachieve.data.MyDate;

import java.util.ArrayList;

class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private final ArrayList<Comment> commentList;

    public CommentAdapter(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    @NonNull
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new CommentViewHolder(layoutInflater.inflate(R.layout.list_item_comment, parent, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(
            @NonNull CommentAdapter.CommentViewHolder holder,
            int position)
    {
        Comment comment = commentList.get(position);
        Log.w("YouAchieve", "CommentAdapter " + comment.getDate().toString());

        String userName = comment.getUser().getName();
        String userSurname = comment.getUser().getSurname();
        holder.commentUserFullName.setText(userName.concat(" ").concat(userSurname));
        holder.commentDate.setText(new MyDate((comment.getDate().getTime())).toString());
        holder.commentText.setText(comment.getText());

        if (comment.getUser().getIdResAvatar() != 0)
            holder.commentAvatar.setImageResource(comment.getUser().getIdResAvatar());
        else
            holder.commentAvatar.setImageResource(R.drawable.avatar_default);
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView commentUserFullName;
        TextView commentDate;
        TextView commentText;
        ImageView commentAvatar;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.commentUserFullName = itemView.findViewById(R.id.commentUserFullName);
            this.commentText = itemView.findViewById(R.id.commentText);
            this.commentDate = itemView.findViewById(R.id.commentDate);
            this.commentAvatar = itemView.findViewById(R.id.commentAvatar);
        }
    }
}