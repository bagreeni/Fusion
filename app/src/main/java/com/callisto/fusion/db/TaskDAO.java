package com.callisto.fusion.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

/**
 * Created by voxaelfox on 3/1/18.
 */

@Dao
public interface TaskDAO {

    @Insert
    long insert(Task task);

    @Delete
    int delete(Task task);

}
