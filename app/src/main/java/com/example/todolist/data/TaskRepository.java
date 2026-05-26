package com.example.todolist.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {

    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    // Executor for background database operations
    private static final ExecutorService databaseExecutor =
            Executors.newFixedThreadPool(2);

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        databaseExecutor.execute(() -> taskDao.insert(task));
    }

    public void update(Task task) {
        databaseExecutor.execute(() -> taskDao.update(task));
    }

    public void delete(Task task) {
        databaseExecutor.execute(() -> taskDao.delete(task));
    }
}
