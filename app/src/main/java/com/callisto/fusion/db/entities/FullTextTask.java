package com.callisto.fusion.db.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by voxaelfox on 3/6/18.
 */

public class FullTextTask implements Serializable {

    // fields

    // from Task
    private long taskID;
    private Date dueDate;
    private Date workDate;

    // from TextTask
    private long textTaskID;
    private String data;

    // from TaskCategory
    private long taskCategoryID;

    // from Category
    private String categoryIDList;
    private String categoryList;

    // getters and setters

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public long getTextTaskID() {
        return textTaskID;
    }

    public void setTextTaskID(long textTaskID) {
        this.textTaskID = textTaskID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTaskCategoryID() {
        return taskCategoryID;
    }

    public void setTaskCategoryID(long taskCategoryID) {
        this.taskCategoryID = taskCategoryID;
    }

    public String getCategoryIDList() {
        return categoryIDList;
    }

    public void setCategoryIDList(String categoryIDList) {
        this.categoryIDList = categoryIDList;
    }

    public String getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(String categoryList) {
        this.categoryList = categoryList;
    }

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
