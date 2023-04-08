package com.example.youachieve;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youachieve.data.MyConfig;
import com.example.youachieve.data.MyDate;
import com.example.youachieve.data.Post;
import com.example.youachieve.data.TypePost;
import com.example.youachieve.network.LoadImage;

import java.util.ArrayList;

public class PostDetailAdapter extends RecyclerView.Adapter<PostDetailAdapter.PostDetailViewHolder> {
    private final ArrayList<Post> postList;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public PostDetailAdapter(ArrayList<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int getItemCount() {
        return postList.size();
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
        Post post = postList.get(position);
        Log.w("YouAchieve", "PostDetailAdapter " + post.getDate().toString());

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

        // Если было загружено имя изоражения, то загрузить его и установить
        if (post.getUser().getImageName().length() > 0) {
            new LoadImage(MyConfig.URL_GET_IMAGE + post.getUser().getImageName(), holder.postAvatar).execute();
            new LoadImage(MyConfig.URL_GET_IMAGE + post.getUser().getImageName(), holder.postImage).execute();
        }
        else {
            holder.postAvatar.setImageResource(R.drawable.avatar_default);
            holder.postImage.setImageResource(R.drawable.avatar_default);
        }

        RecyclerView recyclerView = holder.commentList.findViewById(R.id.commentList);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(holder.commentList.getContext()));
        recyclerView.setAdapter(new CommentAdapter(post.getCommentList()));
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
            this.postAvatar = itemView.findViewById(R.id.postAvatar);
            this.postImage = itemView.findViewById(R.id.postImage);
            this.commentList = itemView.findViewById(R.id.commentList);
        }
    }
}