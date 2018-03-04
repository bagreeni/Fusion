package com.callisto.fusion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Dao
public interface TaskTextDAO {

    @Query("SELECT * FROM TaskText")
    LiveData<List<TaskText>> getAll();

    @Insert
    long insert(TaskText textTask);

    @Delete
    int delete(TaskText testTask);

}
