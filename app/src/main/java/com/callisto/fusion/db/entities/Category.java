package com.callisto.fusion.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Entity(indices = {
            @Index(value = "name", unique = true)
                    })
public class Category {

    @PrimaryKey(autoGenerate = true)
    public long categoryID;

    public String name;

}
