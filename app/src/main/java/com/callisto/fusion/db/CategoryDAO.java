package com.callisto.fusion.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Dao
public interface CategoryDAO {

    @Query("SELECT categoryID FROM Category WHERE name = :catName")
    public long getCategoryID(String catName);

    @Query("SELECT count(*) FROM Category WHERE name = :catName")
    public long getCategoryMatchCount(String catName);

    @Insert
    public long insertCategory(Category category);

    @Insert
    public long[] insertCatagories(Category... category);

}
