package com.callisto.fusion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by voxaelfox on 2/28/18.
 */

@Entity(foreignKeys = @ForeignKey   (  entity = Category.class,
                                        parentColumns = "categoryID",
                                        childColumns = "taskID" )
                                    )
public class TaskText {

        @PrimaryKey
        public int taskID;

        public String data;

        public int categoryID;


}
