package com.blundell.myapplication5.data.source;

import android.support.annotation.NonNull;

import com.blundell.myapplication5.data.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tu on 2017/3/26.
 */

public class TasksRepository implements TasksDataSource{

    private static TasksRepository INSTANCE = null;

    private final TasksDataSource mTasksRemoteDataSource;

    private final TasksDataSource mTasksLocalDataSource;

    Map<String, Task> mCachedTasks;

    boolean mCacheIsDirty = false;

    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource, @NonNull TasksDataSource tasksLocalDataSource){

        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    public static TasksRepository getINSTANCE(TasksDataSource tasksRemoteDataSource, TasksDataSource tasksLocalDataSource){
        if(INSTANCE == null){
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {

        // Respond immediately with cache if available and not dirty
        if(mCachedTasks != null && !mCacheIsDirty){
            callback.onTasksLoaded(new ArrayList<Task>(mCachedTasks.values()));
            return;
        }

        if(mCacheIsDirty){
            // If the cache is dirty we need to fetch new data from the network.
            getTasksFromRemoteDataSource(callback);
        }else{
            // Query the local storage if available. If not, query the network.
            mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
                @Override
                public void onTasksLoaded(List<Task> tasks) {
                    refreshCache(tasks);
                    callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    getTasksFromRemoteDataSource(callback);
                }
            });
        }
    }

    @Override
    public void saveTask(@NonNull Task task) {
        mTasksRemoteDataSource.saveTask(task);
        mTasksLocalDataSource.saveTask(task);

        if(mCachedTasks == null){
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(task.getId(), task);
    }

    @Override
    public void refreshTasks() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAllTasks() {

    }

    private void getTasksFromRemoteDataSource(@NonNull final LoadTasksCallback callback){
        mTasksRemoteDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                refreshCache(tasks);
                refreshLocalDataSource(tasks);
                callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<Task> tasks){
        if(mCachedTasks == null){
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        for (Task task : tasks){
            mCachedTasks.put(task.getId(), task);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Task> tasks){
        mTasksLocalDataSource.deleteAllTasks();
        for (Task task : tasks){
            mTasksLocalDataSource.saveTask(task);
        }
    }
}
