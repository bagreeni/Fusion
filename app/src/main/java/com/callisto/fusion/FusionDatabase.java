package com.callisto.fusion;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.callisto.fusion.db.Category;
import com.callisto.fusion.db.CategoryDAO;
import com.callisto.fusion.db.Task;
import com.callisto.fusion.db.TaskCategory;
import com.callisto.fusion.db.TaskCategoryDAO;
import com.callisto.fusion.db.TaskDAO;
import com.callisto.fusion.db.TextTask;
import com.callisto.fusion.db.TextTaskDAO;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Database(version = 2, entities = {Task.class, TextTask.class, Category.class, TaskCategory.class})
public abstract class FusionDatabase extends RoomDatabase {

    public abstract TaskDAO taskDAO();

    public abstract TextTaskDAO textTaskDAO();

    public abstract CategoryDAO categoryDAO();

    public abstract TaskCategoryDAO taskCategoryDAO();

}
