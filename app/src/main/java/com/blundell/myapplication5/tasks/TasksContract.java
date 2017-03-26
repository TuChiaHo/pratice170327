package com.blundell.myapplication5.tasks;

/**
 * Created by tu on 2017/3/26.
 */

public interface TasksContract {

    interface View {
        void setPresenter(TasksPresenter presenter);
    }

    interface Presenter {

        void start();
        void loadTasks(boolean forceUpdate);
    }
}
