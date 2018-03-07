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

    @PrimaryKey(autoGenerate = true)
    public long taskCategoryID;

    public long taskID;

    public long categoryID;

}
