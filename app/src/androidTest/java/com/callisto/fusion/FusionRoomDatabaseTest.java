package com.callisto.fusion;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.callisto.fusion.db.Task;
import com.callisto.fusion.db.TaskDAO;
import com.callisto.fusion.db.TaskText;
import com.callisto.fusion.db.TaskTextDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by voxaelfox on 3/1/18.
 */
@RunWith(AndroidJUnit4.class)
public class FusionRoomDatabaseTest {
    private TaskDAO mTaskDao;
    private TaskTextDAO mTaskTextDao;
    private FusionDatabase mDb;

    @Before
    public void createDB() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, FusionDatabase.class).build();
        mTaskDao = mDb.taskDAO();
        mTaskTextDao = mDb.taskTextDAO();
    }

    @After
    public void closeDB() throws IOException {
        mDb.close();
    }

    @Test
    public void createTaskAndCheck() throws Exception {
        Task task = new Task();
        task.taskID = 0;

        TaskText textTask = new TaskText();
        textTask.taskTextID = 0;
        textTask.taskID = 0;
        textTask.data = "Hello Task!";

        mTaskDao.insert(task);
        mTaskTextDao.insert(textTask);

        List<TaskText> textTasks = mTaskTextDao.getAll();
        assertEquals(textTasks.get(0).data, "Hello Task!");
    }
}
