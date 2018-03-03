package com.callisto.fusion;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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
