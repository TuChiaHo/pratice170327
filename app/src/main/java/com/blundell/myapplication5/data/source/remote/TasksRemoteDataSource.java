package com.blundell.myapplication5.data.source.remote;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.test.espresso.core.deps.guava.collect.Lists;

import com.blundell.myapplication5.data.Task;
import com.blundell.myapplication5.data.source.TasksDataSource;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tu on 2017/3/26.
 */

public class TasksRemoteDataSource implements TasksDataSource{

    private static TasksRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLIS = 1000;

    private final static Map<String, Task> TASKS_SERVICE_DATA;

    static {
        TASKS_SERVICE_DATA = new LinkedHashMap<>(2);
        addTask("Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!");
    }

    public static TasksRemoteDataSource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new TasksRemoteDataSource();
        }
        return INSTANCE;
    }

    private static void addTask(String title, String description){
        Task newTask = new Task(title, description);
        TASKS_SERVICE_DATA.put(newTask.getId(), newTask);
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTasksLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void saveTask(@NonNull Task task) {
        TASKS_SERVICE_DATA.put(task.getId(), task);
    }

    @Override
    public void deleteAllTasks() {
        TASKS_SERVICE_DATA.clear();
    }

    @Override
    public void refreshTasks() {

    }
}
