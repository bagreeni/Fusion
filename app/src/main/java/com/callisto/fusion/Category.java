package com.callisto.fusion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    public int categoryID;

}
