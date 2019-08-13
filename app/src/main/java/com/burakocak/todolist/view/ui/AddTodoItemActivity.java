package com.burakocak.todolist.view.ui;


import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.burakocak.todolist.R;
import com.burakocak.todolist.databinding.ActivityAddTodoItemBinding;
import com.burakocak.todolist.model.EventbusObject;
import com.burakocak.todolist.view.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.burakocak.todolist.utils.Constants.TODO_ITEM_DATE;
import static com.burakocak.todolist.utils.Constants.TODO_ITEM_DESC;
import static com.burakocak.todolist.utils.Constants.TODO_ITEM_TITLE;

public class AddTodoItemActivity extends BaseActivity {

    private ActivityAddTodoItemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(AddTodoItemActivity.this, R.layout.activity_add_todo_item);

        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Todo Item");
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
            saveTodoItem();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void saveTodoItem() {

        String todoItemName = Objects.requireNonNull(binding.etTodoItemName.getText()).toString();
        String todoItemDesc = Objects.requireNonNull(binding.etTodoItemDesc.getText()).toString();
        Calendar calendar = Calendar.getInstance();
        calendar.set(binding.itemCompleteDate.getYear(), binding.itemCompleteDate.getMonth(), binding.itemCompleteDate.getDayOfMonth());
        Date date = calendar.getTime();
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String todoItemCompleteDate = f.format(date);

        if (todoItemName.trim().isEmpty() || todoItemDesc.trim().isEmpty() || todoItemCompleteDate.isEmpty()) {
            showErrorSneaker("Error!!", "Todo name is empty!");
            return;
        }

        Intent data = new Intent();
        data.putExtra(TODO_ITEM_TITLE, todoItemName);
        data.putExtra(TODO_ITEM_DESC, todoItemDesc);
        data.putExtra(TODO_ITEM_DATE, todoItemCompleteDate);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onCustomEvent(EventbusObject eventbusObject) {

    }
}
