package com.callisto.fusion.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.db.TaskText;

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
