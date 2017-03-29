package com.blundell.myapplication5.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.espresso.core.deps.guava.collect.Lists;

import com.blundell.myapplication5.data.Task;
import com.blundell.myapplication5.data.source.TasksDataSource;

import java.util.LinkedHashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by tu on 2017/3/27.
 */

public class TasksLocalDataSource implements TasksDataSource {

    private static TasksLocalDataSource INSTANCE;

    private Realm realm;

    private TasksLocalDataSource(@NonNull Context context){
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public static TasksLocalDataSource getINSTANCE(@NonNull Context context){
        if(INSTANCE == null){
            INSTANCE = new TasksLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        RealmQuery<Task> query = realm.where(Task.class);
        RealmResults<Task> result = query.findAll();
        callback.onTasksLoaded(result);
    }

    @Override
    public void saveTask(@NonNull final Task task, @NonNull final SaveTaskCallback callback) {
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Task realmObject = realm.createObject(Task.class);
//                realmObject.setmId(task.getmId());
//                realmObject.setmTitle(task.getmTitle());
//                realmObject.setmDescription(task.getmDescription());
//                callback.onSuccess();
//            }
//        });

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Task realmObject = realm.createObject(Task.class, task.getmId());
                realmObject.setmTitle(task.getmTitle());
                realmObject.setmDescription(task.getmDescription());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        });

    }

    @Override
    public void deleteAllTasks(@NonNull final DeleteAllTasksCallback callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final RealmResults<Task> results = realm.where(Task.class).findAll();
                results.deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        });
    }

    @Override
    public void refreshTasks() {

    }
}
