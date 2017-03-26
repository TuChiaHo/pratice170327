package com.blundell.myapplication5.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

/**
 * Created by tu on 2017/3/26.
 */

public class Task {

    @NonNull
    private final String mId;

    @Nullable
    private final String mTitle;

    @Nullable
    private final String mDescription;

    private final boolean mCompleted;

    public Task(@Nullable String title, @Nullable String description) {
        this(title, description, UUID.randomUUID().toString(), false);
    }

    public Task(@Nullable String title, @Nullable String description,
                @NonNull String id, boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Override
    public String toString() {
        return "Task with title " + mTitle;
    }
}
