package com.callisto.fusion;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Database(version = 1, entities = {Task.class, TaskText.class, Category.class, TaskCategory.class})
public abstract class FusionDatabase extends RoomDatabase {

    public abstract TaskDAO taskDAO();

    public abstract TaskTextDAO taskTextDAO();

    public abstract CategoryDAO categoryDAO();

}
