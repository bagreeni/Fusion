package com.callisto.fusion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by voxaelfox on 3/1/18.
 */

@Entity
public class Task {

    @PrimaryKey
    public int taskID;

}
