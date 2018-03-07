package com.callisto.fusion.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.util.Date;

/**
 * Created by voxaelfox on 3/6/18.
 */

public class FullTextTask {

    // from Task
    public long taskID;

    public Date dueDate;

    public Date workDate;

    // from TextTask
    public long textTaskID;

    public String data;

    // from TaskCategory
    public long taskCategoryID;

    // from Category
    public long categoryID;

    @ColumnInfo(name = "name")
    public String categoryName;

    // for debugging
    @Override
    public String toString() {
        return  "taskID: " + taskID +
                " | dueDate: " + dueDate +
                " | workDate: " + workDate +
                " | textTaskID: " + textTaskID +
                " | data: " + data +
                " | taskCategoryID: " + taskCategoryID +
                " | categoryID: " + categoryID +
                " | categoryName: " + categoryName;
    }

}
