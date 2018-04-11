package com.callisto.fusion.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.db.entities.Category;
import com.callisto.fusion.db.entities.FullTextTask;

import java.util.List;

/**
 *
 * Created by voxaelfox on 3/3/18.
 */

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<FullTextTask>> fullTextTasks;
    private LiveData<List<Category>> categories;


    public MutableLiveData<List<FullTextTask>> getFullTextTasks(String cat) {
        if (fullTextTasks == null) {
            fullTextTasks = new MutableLiveData<>();
            DataRepository.getInstance().updateFullTextTaskList(fullTextTasks, cat);
        }
        return fullTextTasks;
    }

    public void updateFullTextTasks(String cm) {
        DataRepository.getInstance().updateFullTextTaskList(fullTextTasks, cm);
    }

    public LiveData<List<Category>> getCategories() {
        if (categories == null) {
            categories = DataRepository.getInstance().getAllCategories();
        }
        return categories;
    }

}
