package com.example.youachieve;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youachieve.activity.PostDetailActivity;
import com.example.youachieve.utils.MyConfig;
import com.example.youachieve.utils.MyData;
import com.example.youachieve.utils.PostData;
import com.example.youachieve.utils.TypePost;
import com.example.youachieve.network.LoadImage;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    boolean isLoading_;

    public boolean isLoading() {
        return isLoading_;
    }

    public void setLoading(boolean loading) {
        isLoading_ = loading;
    }

    public PostAdapter() {
        isLoading_ = false;
    }

    @Override
    public int getItemCount() {
        return MyData.posts.size();
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new PostViewHolder(layoutInflater.inflate(R.layout.list_item_post, parent, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(
            @NonNull PostAdapter.PostViewHolder holder,
            int position)
    {
        PostData postData = MyData.posts.get(position);
        holder.postUserFullName.setText(postData.userOwner.firstName.concat(" " + postData.userOwner.lastName));
        holder.postDate.setText(postData.post.datetimeCreate);

        TypePost typePost = TypePost.valueOf(postData.post.typePostId);
        holder.postType.setText(typePost.toString());
        holder.postType.setTextColor(TypePost.getIdResColor(typePost));

        holder.postLikeCount.setText(String.valueOf(postData.post.likesCount));
        holder.postCommentCount.setText(String.valueOf(postData.post.commentsCount));
        holder.postViewCount.setText(String.valueOf(postData.post.viewsCount));

        if (postData.post.text.length() > MyConfig.POSTS_TEXT_SIZE_MAX)
            holder.postText.setText(postData.post.text.substring(0, MyConfig.POSTS_TEXT_SIZE_MAX).concat(" ..."));
        else
            holder.postText.setText(postData.post.text);

        // Вначале установим фотографию загрузки, чтобы пользователь понял, что пост загружается
        // Когда реальное фото заргузится, фото загрузки сменится на реальное
        if (postData.files.size() > 0) {
            holder.postImage.setVisibility(View.VISIBLE);
            holder.postImage.setImageResource(R.drawable.download);
            // Пока что пусть будет показываться только одно изображение, адже если их больше
            String url = postData.files.get(0).url;
            String name = postData.files.get(0).name;
            new LoadImage(url, name, holder.postImage, MyData.appContext).execute();
        }
        else {
            holder.postImage.setVisibility(View.GONE);
        }

        // Изображение пользователя
        if (postData.userOwnerImage != null) {
            holder.postUserAvatar.setImageResource(R.drawable.download_icon);

            String url = postData.userOwnerImage.url;
            String name = postData.userOwnerImage.name;
            new LoadImage(url, name, holder.postUserAvatar, MyData.appContext).execute();
        }
        else {
            holder.postUserAvatar.setImageResource(R.drawable.user_avatar_none);
        }

        // Нажатие на пост
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.itemView.setBackgroundResource(R.drawable.selection);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    holder.itemView.setBackgroundResource(R.drawable.post_background);

                    // Переход на страницу с детализацией поста
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, PostDetailActivity.class);
                    intent.putExtra("postId", postData.post.id);
                    context.startActivity(intent);
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
        ImageView postUserAvatar;
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
            this.postUserAvatar = itemView.findViewById(R.id.postUserAvatar);
            this.postImage = itemView.findViewById(R.id.postImage);

        }
    }

    public void updatePosts() {
        isLoading_ = true;
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                notifyDataSetChanged();
                isLoading_ = false;
            }
        }, 500);
    }
}