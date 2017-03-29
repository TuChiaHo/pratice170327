package com.blundell.myapplication5.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tu on 2017/3/26.
 */

public class Task  extends RealmObject {

    @NonNull
    @PrimaryKey
    private String mId;

    @Nullable
    private String mTitle;

    @Nullable
    private String mDescription;

    private boolean mCompleted;

//    public Task(@Nullable String title, @Nullable String description) {
//        this(title, description, UUID.randomUUID().toString(), false);
//    }
//
//    public Task(@Nullable String title, @Nullable String description,
//                @NonNull String id, boolean completed) {
//        mId = id;
//        mTitle = title;
//        mDescription = description;
//        mCompleted = completed;
//    }

    @NonNull
    public String getmId() {
        return mId;
    }

    public void setmId(@NonNull String mId) {
        this.mId =  UUID.randomUUID().toString();
    }

    @Nullable
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(@Nullable String mTitle) {
        this.mTitle = mTitle;
    }

    @Nullable
    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(@Nullable String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean ismCompleted() {
        return mCompleted;
    }

    public void setmCompleted(boolean mCompleted) {
        this.mCompleted = mCompleted;
    }
}
