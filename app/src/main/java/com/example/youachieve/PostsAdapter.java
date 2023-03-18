package com.example.youachieve;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private final ArrayList<Post> postList;

    public PostsAdapter(ArrayList<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @NonNull
    @Override
    public PostsAdapter.PostViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new PostViewHolder(layoutInflater.inflate(R.layout.list_item_post, parent, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(
            @NonNull PostsAdapter.PostViewHolder holder,
            int position)
    {
        Post post = postList.get(position);
        Log.w("YouAchieve", "PostsAdapter " + post.getDate().toString());

        String userName = post.getUser().getName();
        String userSurname = post.getUser().getSurname();
        holder.postUserFullName.setText(userName.concat(" ").concat(userSurname));

        holder.postDate.setText(new MyDate((post.getDate().getTime())).toString());
        holder.postType.setText(post.getType().toString());
        holder.postType.setTextColor(TypePost.getIdResColor(post.getType()));

        if (post.getText().length() > 250)
            holder.postText.setText(post.getText().substring(0, 200).concat(" ..."));
        else
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

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.itemView.setBackgroundResource(R.drawable.selection);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    holder.itemView.setBackgroundResource(R.drawable.post_background);
                    Log.w("YouAchieve", "Post click!");
//                    PostDetailFragment postDetailFragment = new PostDetailFragment(post);
//                    FragmentTransaction trans = getFragmentManager().beginTransaction();
//                    trans.add(R.id.postDetailList, postDetailFragment);
//                    trans.commit();
                }
                else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    holder.itemView.setBackgroundResource(R.drawable.post_background);
                }
                return true;
            }
        };
        holder.postText.setOnTouchListener(touchListener);
        holder.postImage.setOnTouchListener(touchListener);
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView postUserFullName;
        TextView postDate;
        TextView postType;
        TextView postText;
        TextView postLikeCount;
        TextView postCommentCount;
        TextView postViewCount;
        ImageView postAvatar;
        ImageView postImage;

        public PostViewHolder(@NonNull View itemView) {
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
        }
    }
}