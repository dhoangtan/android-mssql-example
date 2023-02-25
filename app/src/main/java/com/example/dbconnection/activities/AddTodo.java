package com.example.dbconnection.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbconnection.R;
import com.example.dbconnection.services.MsSqlConnector;

public class AddTodo extends AppCompatActivity {
    private Button confirmButton;
    private Button cancelButton;
    private EditText titleEditText;
    private EditText descriptionEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        getSupportActionBar().hide();

        confirmButton = findViewById(R.id.add_todo_btn_confirm);
        cancelButton = findViewById(R.id.add_todo_btn_cancel);
        titleEditText = findViewById(R.id.add_todo_ed_title);
        descriptionEditText = findViewById(R.id.add_todo_ed_desc);

        confirmButton.setOnClickListener(view -> confirmEvent());
        cancelButton.setOnClickListener(view -> cancelEvent());
    }

    private void confirmEvent() {
        String title = titleEditText.getText().toString();
        String desc = descriptionEditText.getText().toString();

        if (title.isEmpty()) {
            Toast.makeText(this, "Todo title can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        MsSqlConnector.getInstance().execute("INSERT INTO ToDo VALUES ('" + title + "','" + desc +"', 0)");
        Toast.makeText(this, "Successfully add new Todo", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void cancelEvent() {
        finish();
    }

}