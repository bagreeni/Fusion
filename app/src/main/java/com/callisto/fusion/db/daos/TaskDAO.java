package com.callisto.fusion.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.callisto.fusion.db.entities.Task;

/**
 * Created by voxaelfox on 3/1/18.
 */

@Dao
public interface TaskDAO {

    @Query("SELECT * FROM Task WHERE taskID = :findTaskID")
    Task getTaskFromID(long findTaskID);

    @Insert
    long insertTask(Task task);

    @Delete
    int deleteTask(Task task);

}
