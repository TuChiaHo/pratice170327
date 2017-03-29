package com.blundell.myapplication5.data.source;

import android.support.annotation.NonNull;

import com.blundell.myapplication5.data.Task;

import java.util.List;

/**
 * Created by tu on 2017/3/26.
 */

public interface TasksDataSource {

    interface LoadTasksCallback{

        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

    interface SaveTaskCallback{
        void onSuccess();
    }

    interface DeleteAllTasksCallback{
        void onSuccess();
    }

    void getTasks(@NonNull LoadTasksCallback callback);

    void saveTask(@NonNull Task task, @NonNull SaveTaskCallback callback);

    void deleteAllTasks(@NonNull DeleteAllTasksCallback callback);

    void refreshTasks();
}
