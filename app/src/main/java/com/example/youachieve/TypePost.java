package com.example.youachieve;

import androidx.annotation.NonNull;

public enum TypePost {
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
            default:            return R.color.text_primary;
        }
    }
}
