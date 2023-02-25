package com.example.dbconnection.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbconnection.R;
import com.example.dbconnection.models.Todo;
import com.example.dbconnection.services.MsSqlConnector;

import java.util.List;

public class TodoAdapter extends BaseAdapter {

    private List<Todo> listTodo;
    private LayoutInflater inflater;
    private Context context;

    public TodoAdapter(Context context, List<Todo> listTodo) {
        this.listTodo = listTodo;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listTodo.size();
    }

    @Override
    public Object getItem(int i) {
        return listTodo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listTodo.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.todo_fragment, null);
            viewHolder = new ViewHolder();
            viewHolder.todoTitle = view.findViewById(R.id.todo_fragment_title);
            viewHolder.todoDescription = view.findViewById(R.id.todo_fragment_description);
            viewHolder.clearButton = view.findViewById(R.id.todo_fragment_clear);
            viewHolder.doneButton = view.findViewById(R.id.todo_fragment_done);

            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Todo todo = (Todo) getItem(i);
        viewHolder.todoTitle.setText(todo.getTitle());
        viewHolder.todoDescription.setText(todo.getDescription());
        viewHolder.doneButton.setOnClickListener(v -> {
            MsSqlConnector.getInstance().execute("UPDATE ToDo SET state = 1 where id = " + todo.getId());
            listTodo.remove(todo);
            notifyDataSetChanged();
        });
        viewHolder.clearButton.setOnClickListener(v -> {
            MsSqlConnector.getInstance().execute("DELETE FROM ToDo WHERE id = " + todo.getId());
            listTodo.remove(todo);
            notifyDataSetChanged();
        });
        return view;
    }

    static class ViewHolder {
        TextView todoTitle;
        TextView todoDescription;
        Button doneButton;
        Button clearButton;
    }


}
