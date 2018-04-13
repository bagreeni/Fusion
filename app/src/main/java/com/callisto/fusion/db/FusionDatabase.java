package com.callisto.fusion.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.callisto.fusion.db.daos.CategoryDAO;
import com.callisto.fusion.db.daos.TaskCategoryDAO;
import com.callisto.fusion.db.daos.TaskDAO;
import com.callisto.fusion.db.daos.TextTaskDAO;
import com.callisto.fusion.db.entities.Category;
import com.callisto.fusion.db.entities.Task;
import com.callisto.fusion.db.entities.TaskCategory;
import com.callisto.fusion.db.entities.TextTask;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Database(version = 5, entities = {Task.class, TextTask.class, Category.class, TaskCategory.class})
@TypeConverters({Converters.class})
public abstract class FusionDatabase extends RoomDatabase {

    public abstract TaskDAO taskDAO();

    public abstract TextTaskDAO textTaskDAO();

    public abstract CategoryDAO categoryDAO();

    public abstract TaskCategoryDAO taskCategoryDAO();

}
