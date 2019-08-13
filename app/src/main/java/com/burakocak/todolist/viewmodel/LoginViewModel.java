package com.burakocak.todolist.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.burakocak.todolist.database.TodoDatabase;
import com.burakocak.todolist.database.dao.UsersDao;
import com.burakocak.todolist.model.EventbusObject;
import com.burakocak.todolist.model.Users;
import com.burakocak.todolist.utils.Constants;

import org.greenrobot.eventbus.EventBus;



public class LoginViewModel extends AndroidViewModel {

    private UsersDao usersDao;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        TodoDatabase todoDatabase = TodoDatabase.getDatabase(application);
        usersDao = todoDatabase.usersDao();
    }

    // Using AsyncTask
    public void registerUser(Users user) {
        new InsertAsyncTask(usersDao).execute(user);
    }

    /*
        Operation was performed using AsyncTask.
        Login results of transactions using EvenBus

     */
    public void getLogin(String u, String p) {
        EventBus.getDefault().post(new EventbusObject(Constants.SHOW_LOADING));
        new LoginAsyncTask(usersDao).execute(u,p);
    }


    private static class InsertAsyncTask extends AsyncTask <Users,Void,Void> {

        UsersDao usersDao;

        private InsertAsyncTask(UsersDao usersDao) {
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(final Users... users) {
            usersDao.insertUser(users[0]);
            return null;
        }
    }

    private static class LoginAsyncTask extends AsyncTask <String,String,Void > {
        UsersDao usersDao;

        private LoginAsyncTask(UsersDao usersDao) {
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            if (usersDao.userExists(strings[0],strings[1]) ==1){
                EventBus.getDefault().post(new EventbusObject(Constants.RESULT_OK,strings[0]));
            } else {
                EventBus.getDefault().post(new EventbusObject(Constants.RESULT_NO));
            }
            EventBus.getDefault().post(new EventbusObject(Constants.HIDE_LOADING));
            return null;

        }



    }
}
