package com.burakocak.todolist.view.ui;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.burakocak.todolist.R;
import com.burakocak.todolist.databinding.ActivityAddTodoBinding;
import com.burakocak.todolist.model.EventbusObject;
import com.burakocak.todolist.view.base.BaseActivity;

public class AddTodoActivity extends BaseActivity {

    private ActivityAddTodoBinding binding;

    public static final String TODO_TITLE = "com.burakocak.todolist.view.ui.TODO_NAME";

    @Override
    public void onCustomEvent(EventbusObject eventbusObject) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(AddTodoActivity.this,R.layout.activity_add_todo);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Todo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_todo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_todo:
                saveTodo();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void saveTodo() {
        String todoName = binding.etTodoName.getText().toString();
        if (todoName.trim().isEmpty()) {
            showErrorSneaker("Error!!","Todo name is empty!");
            return;
        }
        Intent data = new Intent();
        data.putExtra(TODO_TITLE,todoName);
        setResult(RESULT_OK,data);
        finish();
    }
}
