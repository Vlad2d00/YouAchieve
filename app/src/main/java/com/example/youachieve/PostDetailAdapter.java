package com.example.youachieve;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youachieve.utils.MyConfig;
import com.example.youachieve.utils.PostData;
import com.example.youachieve.utils.TypePost;
import com.example.youachieve.network.LoadImage;

import java.util.ArrayList;

public class PostDetailAdapter extends RecyclerView.Adapter<PostDetailAdapter.PostDetailViewHolder> {
    private ArrayList<PostData> posts_;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public PostDetailAdapter(ArrayList<PostData> posts) {
        this.posts_ = posts;
    }

    @Override
    public int getItemCount() {
        return posts_.size();
    }

    @NonNull
    @Override
    public PostDetailAdapter.PostDetailViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new PostDetailViewHolder(layoutInflater.inflate(R.layout.list_item_post_detail, parent, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(
            @NonNull PostDetailAdapter.PostDetailViewHolder holder,
            int position)
    {
        PostData postData = posts_.get(position);
        holder.postUserFullName.setText(postData.userOwner.firstName.concat(" " + postData.userOwner.lastName));
        holder.postDate.setText(postData.post.datetimeCreate);

        TypePost typePost = TypePost.valueOf(postData.post.typePostId);
        holder.postType.setText(typePost.toString());
        holder.postType.setTextColor(TypePost.getIdResColor(typePost));
        holder.postText.setText(postData.post.text);

        holder.postLikeCount.setText(String.valueOf(postData.post.likesCount));
        holder.postCommentCount.setText(String.valueOf(postData.post.commentsCount));
        holder.postViewCount.setText(String.valueOf(postData.post.viewsCount));

        // Вначале установим фотографию загрузки, чтобы пользователь понял, что пост загружается
        // Когда реальное фото заргузится, фото загрузки сменится на реальное
        if (postData.files.size() > 0) {
            holder.postImage.setImageResource(R.drawable.download);
            // Пока что пусть будет показываться только одно изображение, адже если их больше
            new LoadImage(postData.files.get(0).url, holder.postImage).execute();
        }

        // Изображение пользователя
        if (postData.userOwner.imageId != 0) {
            holder.postUserAvatar.setImageResource(R.drawable.download_icon);
            new LoadImage(postData.userOwnerImage.url, holder.postUserAvatar).execute();
        }
        else {
            holder.postUserAvatar.setImageResource(R.drawable.user_avatar_none);
        }

        // Список комментариев
//        PostCommentDao postCommentDao = db.postCommentDao();
//        List<PostComment> postComments = postCommentDao.filterByPostId(post.id);
//
//        RecyclerView recyclerView = holder.commentList.findViewById(R.id.commentList);
//        recyclerView.setHasFixedSize(false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(holder.commentList.getContext()));
//        recyclerView.setAdapter(new PostCommentAdapter((ArrayList<PostComment>) postComments));
    }

    static class PostDetailViewHolder extends RecyclerView.ViewHolder {
        TextView postUserFullName;
        TextView postDate;
        TextView postType;
        TextView postText;
        TextView postLikeCount;
        TextView postCommentCount;
        TextView postViewCount;
        ImageView postUserAvatar;
        ImageView postImage;
        RecyclerView commentList;

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
            this.postUserAvatar = itemView.findViewById(R.id.postUserAvatar);
            this.postImage = itemView.findViewById(R.id.postImage);
            this.commentList = itemView.findViewById(R.id.commentList);
        }
    }
}