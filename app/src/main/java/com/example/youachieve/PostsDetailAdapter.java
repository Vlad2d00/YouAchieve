package com.example.youachieve;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PostsDetailAdapter extends RecyclerView.Adapter<PostsDetailAdapter.PostDetailViewHolder> {
    private final ArrayList<Post> postList;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public PostsDetailAdapter(ArrayList<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @NonNull
    @Override
    public PostsDetailAdapter.PostDetailViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new PostDetailViewHolder(layoutInflater.inflate(R.layout.list_item_post, parent, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(
            @NonNull PostsDetailAdapter.PostDetailViewHolder holder,
            int position)
    {
        Post post = postList.get(position);
        Log.w("YouAchieve", "PostsDetailAdapter " + post.getDate().toString());

        String userName = post.getUser().getName();
        String userSurname = post.getUser().getSurname();
        holder.postUserFullName.setText(userName.concat(" ").concat(userSurname));

        holder.postDate.setText(new MyDate((post.getDate().getTime())).toString());
        holder.postType.setText(post.getType().toString());
        holder.postType.setTextColor(TypePost.getIdResColor(post.getType()));
        holder.postText.setText(post.getText());

        holder.postLikeCount.setText(String.valueOf(post.getLikeCount()));
        holder.postCommentCount.setText(String.valueOf(post.getCommentList().size()));
        holder.postViewCount.setText(String.valueOf(post.getViewCount()));

        if (post.getUser().getIdResAvatar() != 0)
            holder.postAvatar.setImageResource(post.getUser().getIdResAvatar());
        else
            holder.postAvatar.setImageResource(R.drawable.avatar_default);

        if (post.getIdResImageList().size() > 0)
            holder.postImage.setImageResource(post.getIdResImageList().get(0));

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.commentlList.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(post.getCommentList().size());

        CommentAdapter commentAdapter = new CommentAdapter(post.getCommentList());
        holder.commentlList.setLayoutManager(layoutManager);
        holder.commentlList.setAdapter(commentAdapter);
        holder.commentlList.setRecycledViewPool(viewPool);
//        RecyclerView recyclerView = holder.commentlList.findViewById(R.id.commentlList);
//        recyclerView.setHasFixedSize(false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(holder.commentlList.getContext()));
//        recyclerView.setAdapter(new CommentAdapter(post.getCommentList()));
    }

    static class PostDetailViewHolder extends RecyclerView.ViewHolder {
        TextView postUserFullName;
        TextView postDate;
        TextView postType;
        TextView postText;
        TextView postLikeCount;
        TextView postCommentCount;
        TextView postViewCount;
        ImageView postAvatar;
        ImageView postImage;
        RecyclerView commentlList;

        public PostDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            this.postUserFullName = itemView.findViewById(R.id.postUserFullName);
            this.postDate = itemView.findViewById(R.id.postDate);
            this.postType = itemView.findViewById(R.id.postType);
            this.postText = itemView.findViewById(R.id.postText);
            this.postLikeCount = itemView.findViewById(R.id.postLikeCount);
            this.postCommentCount = itemView.findViewById(R.id.postCommentCount);
            this.postViewCount = itemView.findViewById(R.id.postViewCount);
            this.postUserFullName = itemView.findViewById(R.id.postUserFullName);
            this.postAvatar = itemView.findViewById(R.id.postAvatar);
            this.postImage = itemView.findViewById(R.id.postImage);
            this.commentlList = itemView.findViewById(R.id.commentlList);
        }
    }
}