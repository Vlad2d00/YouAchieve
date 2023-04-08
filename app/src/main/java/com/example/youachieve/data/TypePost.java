package com.example.youachieve.data;

import androidx.annotation.NonNull;

import com.example.youachieve.R;

public enum TypePost {
    NONE {
        @NonNull
        @Override
        public String toString() {
            return "Неизвестный";
        }
    },
    CUSTOM {
        @NonNull
        @Override
        public String toString() {
            return "Обычный";
        }
    },
    ACHIEVEMENT {
        @NonNull
        @Override
        public String toString() {
            return "Достижение";
        }
    },
    SYSTEM {
        @NonNull
        @Override
        public String toString() {
            return "Системный";
        }
    };

    public static int getIdResColor(TypePost typePost) {
        switch (typePost) {
            case CUSTOM:        return R.color.type_post_custom;
            case ACHIEVEMENT:   return R.color.type_post_achievement;
            case SYSTEM:        return R.color.type_post_system;
            case NONE:          return R.color.text_primary;
            default:            return R.color.text_primary;
        }
    }

    public static TypePost valueOf(int value) {
        switch (value) {
            case 0:     return CUSTOM;
            case 1:     return ACHIEVEMENT;
            case 2:     return SYSTEM;
            default:    return NONE;
        }
    }
}
