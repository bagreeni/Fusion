package com.callisto.fusion.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by voxaelfox on 3/1/18.
 */

@Entity(foreignKeys = { @ForeignKey(entity = Task.class,
                                    parentColumns = "taskID",
                                    childColumns = "taskID"),
                        @ForeignKey(entity = Category.class,
                                    parentColumns = "categoryID",
                                    childColumns = "categoryID")
                        })
public class TaskCategory {

    // fields

    @PrimaryKey(autoGenerate = true)
    public long taskCategoryID;
    public long taskID;
    public long categoryID;

    // getters and setters

    public long getTaskCategoryID() {
        return taskCategoryID;
    }

    public void setTaskCategoryID(long taskCategoryID) {
        this.taskCategoryID = taskCategoryID;
    }

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }
}
