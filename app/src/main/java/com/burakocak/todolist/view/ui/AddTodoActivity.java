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

import java.util.Objects;

import static com.burakocak.todolist.utils.Constants.TODO_TITLE;

public class AddTodoActivity extends BaseActivity {

    private ActivityAddTodoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(AddTodoActivity.this, R.layout.activity_add_todo);

        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Todo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_todo) {
            saveTodo();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void saveTodo() {
        String todoName = Objects.requireNonNull(binding.etTodoName.getText()).toString();
        if (todoName.trim().isEmpty()) {
            showErrorSneaker("Error!!", "Todo name is empty!");
            return;
        }
        Intent data = new Intent();
        data.putExtra(TODO_TITLE, todoName);
        setResult(RESULT_OK, data);
        finish();
    }


    @Override
    public void onCustomEvent(EventbusObject eventbusObject) {

    }
}
