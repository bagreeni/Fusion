package com.callisto.fusion.db;

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
public interface TextTaskDAO {

    @Query("SELECT * FROM TextTask")
    LiveData<List<TextTask>> getAll();

    @Insert
    long insert(TextTask textTask);

    @Delete
    int delete(TextTask testTask);

}
