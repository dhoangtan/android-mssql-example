package com.example.dbconnection.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dbconnection.R;
import com.example.dbconnection.models.Todo;
import com.example.dbconnection.services.MsSqlConnector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addToDoButton;
    private ListView toDoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        addToDoButton = findViewById(R.id.main_btn_add);
        toDoListView = findViewById(R.id.main_lv_list_todo);



        addToDoButton.setOnClickListener(view -> navigateToAddActivity());

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Todo> listTodo = new ArrayList<>();
        try {
            ResultSet result = MsSqlConnector.getInstance().query("SELECT * FROM ToDo");
            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String description = result.getString("description");
                listTodo.add(new Todo(id, title, description));
            }
            toDoListView.setAdapter(new TodoAdapter(this, listTodo));
        }
        catch (Exception e) {

        }
    }

    private void navigateToAddActivity() {
        Intent intent = new Intent(MainActivity.this, AddTodo.class);
        startActivity(intent);
    }
}