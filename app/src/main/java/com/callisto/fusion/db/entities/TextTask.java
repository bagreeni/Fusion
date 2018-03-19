package com.callisto.fusion.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Entity(foreignKeys = @ForeignKey(  entity = Task.class,
                                    parentColumns = "taskID",
                                    childColumns = "taskID" ))
public class TextTask {

    // fields

    @PrimaryKey(autoGenerate = true)
    public long textTaskID;
    public long taskID;
    public String data;

    // getters and setters

    public long getTextTaskID() {
        return textTaskID;
    }

    public void setTextTaskID(long textTaskID) {
        this.textTaskID = textTaskID;
    }

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
