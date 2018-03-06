package com.callisto.fusion.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

/**
 * Created by voxaelfox on 3/1/18.
 */

@Dao
public interface TaskCategoryDAO {

    @Insert
    public long insertTaskCatagory(TaskCategory taskCategory);

    @Insert
    public long[] insertTaskCategories(TaskCategory... taskCategory);

}
