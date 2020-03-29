package com.example.todomanager5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TaskClickListener {

    RecyclerView recyclerView;
    MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        mainAdapter = new MainAdapter();
        mainAdapter.listener = this;
        recyclerView.setAdapter(mainAdapter);

    }

    public void onCreateTask(View view) {
        Intent intent = new Intent(this, com.example.todomanager.CreateTaskActivity.class);
        startActivityForResult(intent, 42);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42) {
            if (resultCode == RESULT_OK) {
                Task task = (Task) data.getSerializableExtra("task");
                mainAdapter.addTask(task);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Task creation canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onTaskClick(Task task) {
        Intent intent = new Intent(this, TaskDetailsActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);

    }

    @Override
    public void onEditTaskClick(EditTask editTask) {
        Intent intent = new Intent(this, EditTaskActivity.class);
        intent.putExtra("task", editTask);
        startActivity(intent);
    }

    }