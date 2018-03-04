package com.callisto.fusion;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

/**
 * Created by voxaelfox on 3/3/18.
 */

public class TextTaskViewModel extends ViewModel {
    private LiveData<List<TaskText>> textTasks;

    public LiveData<List<TaskText>> getTextTasks(DataRepository dr) {
        if (textTasks == null) {
            textTasks = dr.getTextTasks();
        }
        return textTasks;
    }

}
