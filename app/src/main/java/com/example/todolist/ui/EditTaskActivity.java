package com.example.todolist.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.R;
import com.example.todolist.data.Task;
import com.example.todolist.viewmodel.TaskViewModel;

public class EditTaskActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task); // reuse the same layout

        EditText editTitle = findViewById(R.id.edit_title);
        Button buttonSave = findViewById(R.id.button_save);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        taskId = getIntent().getIntExtra("task_id", -1);
        String title = getIntent().getStringExtra("task_title");

        editTitle.setText(title);

        buttonSave.setText("Update Task");

        buttonSave.setOnClickListener(v -> {
            String newTitle = editTitle.getText().toString().trim();

            if (!newTitle.isEmpty()) {
                Task updatedTask = new Task(newTitle, false);
                updatedTask.setId(taskId); // IMPORTANT
                taskViewModel.update(updatedTask);
                finish();
            }
        });
    }
}
