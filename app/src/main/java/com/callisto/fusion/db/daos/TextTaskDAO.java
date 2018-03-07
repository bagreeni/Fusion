package com.callisto.fusion.db.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.callisto.fusion.db.entities.FullTextTask;
import com.callisto.fusion.db.entities.TextTask;

import java.util.List;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Dao
public interface TextTaskDAO {

    @Query("SELECT * FROM TextTask")
    LiveData<List<TextTask>> getAllTextTasks();

    @Query("SELECT * FROM Task NATURAL JOIN TextTask NATURAL JOIN TaskCategory NATURAL JOIN Category where taskID = taskID")
    LiveData<List<FullTextTask>> getAllFullTextTasks();

    @Insert
    long insertTextTask(TextTask textTask);

    @Delete
    int deleteTextTask(TextTask testTask);

}