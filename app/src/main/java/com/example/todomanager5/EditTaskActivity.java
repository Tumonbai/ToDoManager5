package com.example.todomanager5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditTaskActivity extends AppCompatActivity {

    EditText tv_edit_title, tv_edit_description;
    TextView  tv_edit_start_date, tv_edit_deadline, tv_edit_checkbox_text;
    CheckBox edit_checkbox;
    Button btn_edit_start_date, btn_edit_deadline, btn_edit_save, btn_edit_cancel;
    Date editStartDate, editDeadline;
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog pickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Intent intent = getIntent();
        if (intent == null){
            Toast.makeText(this,"Task not found", Toast.LENGTH_LONG).show();
            finish();
        }else{
            tv_edit_title = findViewById(R.id.tv_edit_title);
            tv_edit_description = findViewById(R.id.tv_edit_description);
            tv_edit_start_date = findViewById(R.id.tv_edit_start_date);
            tv_edit_deadline = findViewById(R.id.tv_edit_deadline);
            tv_edit_checkbox_text = findViewById(R.id.tv_checkbox_text);
            edit_checkbox = findViewById(R.id.edit_checkbox);
            btn_edit_start_date = findViewById(R.id.btn_edit_start_date);
            btn_edit_deadline = findViewById(R.id.btn_edit_deadline);
            btn_edit_save = findViewById(R.id.btn_edit_save);
            btn_edit_cancel = findViewById(R.id.btn_edit_cancel);
            EditTask editTask = (EditTask)getIntent().getSerializableExtra("task");

            tv_edit_title.setText(editTask.editTitle);
            tv_edit_description.setText(editTask.editDescription);
            edit_checkbox.setChecked(editTask.isDone);



        }

    }

    public void onChooseStartDate(View view) {

        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tv_edit_start_date.setText(dayOfMonth +"." + (month+1)+"." + year);
                editStartDate = new GregorianCalendar(year,month,day).getTime();

            }
        },year,month, day);
        pickerDialog.show();
    }

    public void onChooseDeadline(View view) {

        int  day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tv_edit_deadline.setText(dayOfMonth +"."+(month +1)+ "."+ year);
                editDeadline = new GregorianCalendar(year,month,dayOfMonth).getTime();
            }
        }, year,month,day);
        pickerDialog.show();
    }

    public void onEditSave(View view) {

        String editTitle = tv_edit_title.getText().toString();
        String editDescription = tv_edit_description.getText().toString();

        EditTask  editTask = new EditTask();
        editTask.editStartDate = editStartDate;
        editTask.editDeadline = editDeadline;
        editTask.editTitle = editTitle;
        editTask.editDescription = editDescription;
        editTask.isDone = false;
        Intent intent = new Intent();
        intent.putExtra("task",editTask);
        setResult(RESULT_OK, intent);
        finish();


    }

    public void onEditCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();

    }
}
