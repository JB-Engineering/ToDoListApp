package com.example.todolist.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.data.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasks = new ArrayList<>();
    private OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onTaskClicked(Task task);
        void onTaskLongPressed(Task task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setOnTaskClickListener(OnTaskClickListener listener) {
        this.listener = listener;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.titleText.setText(currentTask.getTitle());
        holder.completedCheck.setChecked(currentTask.isCompleted());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleText;
        private final CheckBox completedCheck;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.text_title);
            completedCheck = itemView.findViewById(R.id.checkbox_completed);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTaskClicked(tasks.get(getBindingAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    listener.onTaskLongPressed(tasks.get(getBindingAdapterPosition()));
                }
                return true;
            });
        }
    }
}
