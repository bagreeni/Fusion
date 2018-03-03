package com.callisto.fusion;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

/**
 * Created by voxaelfox on 3/3/18.
 */

public class TaskModel {
    private LiveData<List<Task>> tasks;

    public LiveData<List<Task>> getTasks() {
        if (tasks == null) {
            tasks = new MutableLiveData<List<Task>>();
            loadUsers();
        }
        return tasks;
    }

    private void loadUsers() {
        // Do an asyncronous operation to fetch users.
    }
}
