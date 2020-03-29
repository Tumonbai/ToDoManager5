package com.example.todomanager5;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    CheckBox checkBox;
    TaskClickListener listener;
    Task task;
    EditTask editTask;


    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text_view);
        checkBox = itemView.findViewById(R.id.checkbox);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditTaskClick(editTask);
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTaskClick(task);


            }
        });
    }
}
