package com.callisto.fusion.db.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.callisto.fusion.db.entities.Category;

import java.util.List;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT categoryID FROM Category WHERE name = :catName")
    long getCategoryID(String catName);

    @Query("SELECT count(*) FROM Category WHERE name = :catName")
    long getCategoryMatchCount(String catName);

    @Insert
    long insertCategory(Category category);

    @Insert
    long[] insertCatagories(Category... category);

}
