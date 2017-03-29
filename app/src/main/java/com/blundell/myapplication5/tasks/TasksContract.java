package com.blundell.myapplication5.tasks;

import com.blundell.myapplication5.data.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tu on 2017/3/26.
 */

public interface TasksContract {

    interface View {
        void setPresenter(TasksPresenter presenter);
        void showTasks(List<Task> tasks);
        void showNoTasks();
    }

    interface Presenter {

        void start();
        void loadTasks(boolean forceUpdate);
        void saveTask(Task task);
        void deleteAllTasks();
    }
}
