package com.blundell.myapplication5;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blundell.myapplication5.data.source.TasksRepository;
import com.blundell.myapplication5.data.source.local.TasksLocalDataSource;
import com.blundell.myapplication5.data.source.remote.TasksRemoteDataSource;

/**
 * Created by tu on 2017/3/26.
 */

public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context){
        return TasksRepository.getINSTANCE(TasksRemoteDataSource.getInstance(), TasksLocalDataSource.getINSTANCE(context));
    }
}
