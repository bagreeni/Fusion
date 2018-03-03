package com.callisto.fusion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Entity(foreignKeys = @ForeignKey(  entity = Task.class,
                                    parentColumns = "taskID",
                                    childColumns = "taskID" ))
public class TaskText {

    @PrimaryKey(autoGenerate = true)
    public long taskTextID;

    public long taskID;

    public String data;

}
