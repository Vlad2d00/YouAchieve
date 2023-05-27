package com.example.youachieve.utils;

import android.content.Context;

import java.util.ArrayList;

public class MyData {
    public static Context appContext = null;

    public static int posts_load_count = 0;
    public static boolean is_end_posts = false;
    public static boolean is_init_posts = false;

    public static ArrayList<PostData> posts = new ArrayList<PostData>();
}
