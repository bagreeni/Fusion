package com.callisto.fusion.db.entities;

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

    // fields

    @PrimaryKey(autoGenerate = true)
    private long categoryID;
    private String name;

    // getters and setters

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
