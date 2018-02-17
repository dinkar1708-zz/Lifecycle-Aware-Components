package dpm.lifecycle.aware.components;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * THIS IS THE VIEW MODEL
 * ViewModel is a class that is responsible for preparing and managing the data for ACTIVITY AND FRAGMENT
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * THIS SHARED DATA IS USED BETWEEN ACTIVITY AND FRAGMENT
 *
 * It's very common that two or more fragments in an activity need to communicate with each other.
 * Imagine a common case of master-detail fragments, where you have a fragment in which the user selects an item
 * from a list and another fragment that displays the contents of the selected item
 */
public class SharedViewModel extends ViewModel {
    private static final String TAG = SharedViewModel.class.getSimpleName();

    private final MutableLiveData<User> selected = new MutableLiveData<>();

    /**
     * DEFINE OWN METHODS
     * @param item
     */
    public void select(User item) {
        selected.setValue(item);
    }

    /**
     * DEFINE OWN METHODS
     *
     * @return
     */
    public LiveData<User> getSelected() {
        return selected;
    }
}