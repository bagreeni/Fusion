package com.callisto.fusion.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.callisto.fusion.db.entities.TaskCategory;

/**
 * Created by voxaelfox on 3/1/18.
 */

@Dao
public interface TaskCategoryDAO {

    @Insert
    long insertTaskCatagory(TaskCategory taskCategory);

    @Insert
    long[] insertTaskCategories(TaskCategory... taskCategory);

}
