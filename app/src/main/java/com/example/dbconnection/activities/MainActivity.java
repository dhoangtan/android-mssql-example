package com.example.dbconnection.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dbconnection.R;
import com.example.dbconnection.models.Todo;
import com.example.dbconnection.services.MsSqlConnector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addToDoButton;
    private ListView todoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        addToDoButton = findViewById(R.id.main_btn_add);
        todoListView = findViewById(R.id.main_lv_list_todo);

        addToDoButton.setOnClickListener(view -> navigateToAddActivity());

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            ResultSet result = MsSqlConnector.getInstance().query("SELECT * FROM ToDo WHERE state = 0");
            ArrayList<Todo> listTodo = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String description = result.getString("description");
                boolean state = result.getBoolean("state");
                listTodo.add(new Todo(id, title, description, state));
            }
            todoListView.setAdapter(new TodoAdapter(this, listTodo));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navigateToAddActivity() {
        Intent intent = new Intent(MainActivity.this, AddTodo.class);
        startActivity(intent);
    }
}