package com.callisto.fusion.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by voxaelfox on 3/1/18.
 */

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    public long taskID;

    public Date dueDate;

    public Date workDate;

}
