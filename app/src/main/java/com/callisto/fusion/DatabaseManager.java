package com.callisto.fusion;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Database(version = 1, entities = {TaskText.class, Category.class})
public abstract class DatabaseManager extends RoomDatabase {

    abstract public TaskTextDAO taskTextDAO();

    abstract public CategoryDAO categoryDAO();

}
