package com.callisto.fusion.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.db.Category;
import com.callisto.fusion.db.TextTask;

import java.util.List;

/**
 * Created by voxaelfox on 3/3/18.
 */

public class TextTaskViewModel extends ViewModel {
    private LiveData<List<TextTask>> textTasks;
    private LiveData<List<Category>> categories;

    public LiveData<List<TextTask>> getTextTasks() {
        if (textTasks == null) {
            textTasks = DataRepository.getInstance().getAllTextTasks();
        }
        return textTasks;
    }

    public LiveData<List<Category>> getCategories() {
        if (categories == null) {
            categories = DataRepository.getInstance().getAllCategories();
        }
        return categories;
    }

}
