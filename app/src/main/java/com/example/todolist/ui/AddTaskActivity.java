package com.example.todolist.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.R;
import com.example.todolist.data.Task;
import com.example.todolist.viewmodel.TaskViewModel;

public class AddTaskActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_add_task);

        EditText editTitle = findViewById(R.id.edit_title);
        Button buttonSave = findViewById(R.id.button_save);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        buttonSave.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();

            if (!title.isEmpty()) {
                Task task = new Task(title, false);
                taskViewModel.insert(task);
                finish();
            }
        });
    }
}
