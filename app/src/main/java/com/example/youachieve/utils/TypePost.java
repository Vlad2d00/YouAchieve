package com.example.youachieve.utils;

import androidx.annotation.NonNull;

import com.example.youachieve.R;

public enum TypePost {
    NONE {
        @NonNull
        @Override
        public String toString() {
            return "None";
        }
    },
    SYSTEM {
        @NonNull
        @Override
        public String toString() {
            return "Системный";
        }
    },
    CUSTOM {
        @NonNull
        @Override
        public String toString() {
            return "Обычный";
        }
    },
    ADVERTISEMENT {
        @NonNull
        @Override
        public String toString() {
            return "Реклама";
        }
    },
    ACHIEVE {
        @NonNull
        @Override
        public String toString() {
            return "Достижение";
        }
    },
    PURPOSE {
        @NonNull
        @Override
        public String toString() {
            return "Цель";
        }
    };

    public static int getIdResColor(TypePost typePost) {
        switch (typePost) {
            case SYSTEM:            return R.color.type_post_system;
            case CUSTOM:            return R.color.type_post_custom;
            case ADVERTISEMENT:     return R.color.type_post_advertisement;
            case ACHIEVE:           return R.color.type_post_achieve;
            case PURPOSE:           return R.color.type_post_purpose;
            default:                return R.color.type_post_custom;
        }
    }

    public static TypePost valueOf(int value) {
        switch (value) {
            case 0:     return SYSTEM;
            case 1:     return CUSTOM;
            case 2:     return ADVERTISEMENT;
            case 3:     return ACHIEVE;
            case 4:     return PURPOSE;

            default:    return NONE;
        }
    }
}
