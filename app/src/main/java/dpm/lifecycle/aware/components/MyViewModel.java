package dpm.lifecycle.aware.components;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asyncronous operation to fetch users.

        //TODO add dynamic data
        MutableLiveData<List<User>> users = new MutableLiveData<>();
        List<User> usersList = new ArrayList<>();

        User user = new User();
        user.setEmail("mike@hotmail.com");
        user.setFirstName("Mike");
        usersList.add(user);

         user = new User();
        user.setEmail("sham@hotmail.com");
        user.setFirstName("Shame");
        usersList.add(user);

         user = new User();
        user.setEmail("kristen@hotmail.com");
        user.setFirstName("Kristen");
        usersList.add(user);


        user = new User();
        user.setEmail("kEristen@hotmail.com");
        user.setFirstName("KEristen");
        usersList.add(user);

        users.setValue(usersList);

        this.users = users;
    }
}