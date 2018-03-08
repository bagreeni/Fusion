package com.callisto.fusion.db.entities;

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
    public String categoryIDList;

    public String categoryList;

    // for debugging
    @Override
    public String toString() {
        return  "taskID: " + taskID +
                " | dueDate: " + dueDate +
                " | workDate: " + workDate +
                " | textTaskID: " + textTaskID +
                " | data: " + data +
                " | taskCategoryID: " + taskCategoryID +
                " | categoryIDList: " + categoryIDList +
                " | categoryList: " + categoryList;
    }

}
