package com.example.youachieve;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youachieve.activity.DetailActivity;
import com.example.youachieve.activity.PostDetailActivity;
import com.example.youachieve.data.DataBase;
import com.example.youachieve.data.MyConfig;
import com.example.youachieve.data.MyDate;
import com.example.youachieve.data.Post;
import com.example.youachieve.data.TypePost;
import com.example.youachieve.data.User;
import com.example.youachieve.network.LoadImage;

import java.util.ArrayList;
import java.util.Date;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private final ArrayList<Post> postList;
    private final FragmentTransaction transaction;
    boolean isLoading = false;

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public PostAdapter(ArrayList<Post> postList, FragmentTransaction transaction) {
        this.postList = postList;
        this.transaction = transaction;
    }

    @Override
    public int getItemCount() {
        return postList.size();
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
        Post post = postList.get(position);
        Log.w("YouAchieve", "PostAdapter " + post.getDate().toString());

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

        if (post.getUser().getImageName().length() > 0)
            new LoadImage(MyConfig.URL_GET_IMAGE + post.getUser().getImageName(), holder.postAvatar).execute();
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

                    // Переход на страницу с детализацией поста
                    DataBase.selectedPost = post;
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    context.startActivity(intent);

//                    PostDetailFragment newFragment=new PostDetailFragment(post);
//                    transaction.replace(R.id.contentFragment, newFragment);
//                    transaction.commit();
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

    public void loadMorePosts() {
        isLoading = true;
        //DataBase.postList.add(new Post(new User("", ""), "", new Date(), TypePost.NONE));
        //notifyItemInserted(DataBase.postList.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                //DataBase.postList.remove(DataBase.postList.size() - 1);
                int scrollPosition = DataBase.postList.size();
                notifyItemRemoved(scrollPosition);

                //DataBase.loadPosts(MyConfig.COUNT_LOAD_POSTS);
                notifyDataSetChanged();
                isLoading = false;
            }
        }, 500);
    }
}