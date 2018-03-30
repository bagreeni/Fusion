package com.callisto.fusion.db.daos;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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

    @Query("SELECT * FROM TextTask WHERE data = :s")
    TextTask getTextTaskFromData(String s);

    @Query("SELECT textTaskID FROM TextTask WHERE data = :s")
    long getTextTaskID(String s);

    @Query("SELECT taskID FROM TextTask WHERE data = :s")
    long getTaskIDFromData(String s);

    @Query( "SELECT taskID, dueDate, workDate, textTaskID, data, taskCategoryID, GROUP_CONCAT(categoryID) AS categoryIDList, GROUP_CONCAT(name) AS categoryList " +
            "FROM Task " +
            "NATURAL JOIN TextTask " +
            "NATURAL JOIN TaskCategory " +
            "NATURAL JOIN Category " +
            "WHERE taskID IN (" +
                "SELECT tc.taskID " +
                "FROM TaskCategory AS tc " +
                "NATURAL JOIN Category AS cat " +
                "WHERE cat.name = :c" +
            ") " +
            "GROUP BY taskID")
    LiveData<List<FullTextTask>> getAllFullTextTasksLD(String c);

    @Query( "SELECT taskID, dueDate, workDate, textTaskID, data, taskCategoryID, GROUP_CONCAT(categoryID) AS categoryIDList, GROUP_CONCAT(name) AS categoryList " +
            "FROM Task " +
            "NATURAL JOIN TextTask " +
            "NATURAL JOIN TaskCategory " +
            "NATURAL JOIN Category " +
            "WHERE taskID IN (" +
                "SELECT tc.taskID " +
                "FROM TaskCategory AS tc " +
                "NATURAL JOIN Category AS cat " +
                "WHERE cat.name = :c" +
            ") " +
            "GROUP BY taskID")
    List<FullTextTask> getAllFullTextTasks(String c);

    @Insert
    long insertTextTask(TextTask textTask);

    @Delete
    int deleteTextTask(TextTask testTask);

}
