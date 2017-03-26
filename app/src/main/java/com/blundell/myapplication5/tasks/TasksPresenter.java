package com.blundell.myapplication5.tasks;

import android.support.annotation.NonNull;
import android.util.Log;

import com.blundell.myapplication5.data.Task;
import com.blundell.myapplication5.data.source.TasksDataSource;
import com.blundell.myapplication5.data.source.TasksRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tu on 2017/3/26.
 */

public class TasksPresenter implements TasksContract.Presenter{

    private final TasksRepository mTasksRepository;

    private final TasksContract.View mTasksView;

    public TasksPresenter(@NonNull TasksRepository tasksRepository, @NonNull TasksContract.View tasksView){
        mTasksRepository = tasksRepository;
        mTasksView = tasksView;

        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks(false);
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        if(forceUpdate){
            mTasksRepository.refreshTasks();
        }

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                List<Task> tasksToShow = new ArrayList<Task>();
                Log.e("test", "onTasksLoaded");
                for(Task task:tasks){
                    tasksToShow.add(task);
                    Log.e("test", ""+task);
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
