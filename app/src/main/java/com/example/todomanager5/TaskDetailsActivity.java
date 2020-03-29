package com.example.todomanager5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskDetailsActivity extends AppCompatActivity {

    TextView title, description, startDate, deadline;
    CheckBox checkBox;
    Button btnEdit, btnSave;
    MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        btnSave = findViewById(R.id.btn_save);
        btnEdit = findViewById(R.id.btn_edit);
        mainAdapter = new MainAdapter();

        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            title = findViewById(R.id.details_title);
            description = findViewById(R.id.details_description);
            startDate = findViewById(R.id.details_start_date);
            deadline = findViewById(R.id.details_deadline);
            checkBox = findViewById(R.id.detailed_checkbox);


            Task task = (Task) intent.getSerializableExtra("task");

            title.setText(task.title);
            description.setText(task.description);
            checkBox.setChecked(task.isDone);


            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            startDate.setText(format.format(task.startDate));
            deadline.setText(format.format(task.deadline));

        }


    }

    public void onEdit(View view) {
        Intent intent = new Intent(this, EditTaskActivity.class);
        startActivityForResult(intent, 44);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 44) {
            if (requestCode == RESULT_OK) {
                EditTask editTask = (EditTask) data.getSerializableExtra("task");
                mainAdapter.editTask(editTask);
            } else if (requestCode == RESULT_CANCELED) {
                Toast.makeText(this, "Task is not editted", Toast.LENGTH_LONG).show();
            }
        }

    }
}
