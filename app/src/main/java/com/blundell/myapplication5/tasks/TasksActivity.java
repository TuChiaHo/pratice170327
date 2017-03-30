package com.blundell.myapplication5.tasks;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blundell.myapplication5.Injection;
import com.blundell.myapplication5.R;
import com.blundell.myapplication5.data.Task;
import com.github.clans.fab.FloatingActionButton;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.menu_item_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Date date = new Date();
                Task task = new Task();
                task.setmId(UUID.randomUUID().toString());
                task.setmTitle("title");
                task.setmDescription(date.toString());
                mTasksPresenter.saveTask(task);
            }
        });

        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.menu_item_delete);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                mTasksPresenter.deleteAllTasks();
            }
        });
    }

    @Override
    public void setPresenter(TasksPresenter presenter) {

    }

    @Override
    public void showTasks(List<Task> tasks) {
        StringBuffer result = new StringBuffer();
        for(Task task:tasks){
            result.append(task.getmDescription()+"\n");
        }
        ((TextView)findViewById(R.id.text_tv)).setText(result);
    }

    @Override
    public void showNoTasks() {
        ((TextView)findViewById(R.id.text_tv)).setText("");
    }
}
