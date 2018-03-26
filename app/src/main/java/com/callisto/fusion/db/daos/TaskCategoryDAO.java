package com.callisto.fusion.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.callisto.fusion.db.entities.TaskCategory;

import java.util.List;

/**
 * Created by voxaelfox on 3/1/18.
 */

@Dao
public interface TaskCategoryDAO {

    @Insert
    long insertTaskCatagory(TaskCategory taskCategory);

    @Query("SELECT * FROM TaskCategory WHERE taskID = :tid")
    List<TaskCategory> getTaskCategoriesFromTaskID(long tid);

    @Delete
    int deleteTaskCategory(TaskCategory taskCategory);

    @Insert
    long[] insertTaskCategories(TaskCategory... taskCategory);

}
