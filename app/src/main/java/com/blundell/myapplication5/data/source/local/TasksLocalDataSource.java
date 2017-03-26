package com.blundell.myapplication5.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blundell.myapplication5.data.Task;
import com.blundell.myapplication5.data.source.TasksDataSource;

/**
 * Created by tu on 2017/3/27.
 */

public class TasksLocalDataSource implements TasksDataSource {

    private static TasksLocalDataSource INSTANCE;

    private TasksLocalDataSource(@NonNull Context context){

    }

    public static TasksLocalDataSource getINSTANCE(@NonNull Context context){
        if(INSTANCE == null){
            INSTANCE = new TasksLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void refreshTasks() {

    }
}
