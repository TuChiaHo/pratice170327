package com.blundell.myapplication5.tasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blundell.myapplication5.Injection;
import com.blundell.myapplication5.R;

/**
 * Created by tu on 2017/3/26.
 */

public class TasksActivity extends AppCompatActivity implements TasksContract.View{

    private TasksPresenter mTasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the presenter
        mTasksPresenter = new TasksPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        mTasksPresenter.loadTasks(true);
    }

    @Override
    public void setPresenter(TasksPresenter presenter) {

    }
}
