## Android life cycle aware components

Components
Architecture components are a set of Android libraries
that help you structure your app in a way that is robust, testable, and maintainable

#### INPUT - EXAMPLE OF VIEW MODEL - how does data persist using this view model after configuration changes
The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
The ViewModel class allows data to survive configuration changes such as screen rotations.

Two examples has been shown
1. Data saved in activity
2. Data saved across fragments

#### OUTPUT
view modle data persist after the config changes as well ie. data is not lost after screen rotation

#### 1. Data saved in activity
1. Create MyViewModel which is data source, after changing in data source however activity get rotates but data is available on activity
```
public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
```
2. Get this data in activity

```
// Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        // BELOW FOR JUST FOR DEMO PURPOSE
        MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);

        // THIS KIND OF OBSERVER CAN BE ADDED ANY WHERE LIKE IN ACTIVITY OR FRAGMENT
        // ADD OBSERVER IN ON CHANGE
       /*
        model.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {

            }
        });*/
        // SEE LAMDA VARIATIONS OF SAME AS ABOVE
        model.getUsers().observe(this, users -> {
            // update UI
            Log.i(TAG, "onChanged.......users...." + users);
        });
        
   ```
 
 #### 2. Data saved across fragments
 pass data between fragments
 1. Get data in master ie main fragment
 ```
 // BELOW TWO ARE SAME LABDA FLAVORS HAS BEEN SHOWN
             MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);
            /*
             model.getUsers().observe(getActivity(), new Observer<List<User>>() {
                 @Override
                 public void onChanged(@Nullable List<User> users) {
 
                 }
             });*/
 
             model.getUsers().observe(this, users -> {
                 // update UI
                 Log.i(TAG, "onChanged users...." + users);
                 recyclerView.setAdapter(new MyFragmenItemRecyclerViewAdapter(users, mListener));
             });
 ```
 2. Update data in shared view model after item being selected from master/ main frament
  ```
 //SET SELECTED VALUE IN SHARED VIEW MODEL
        // THIS DATA IS PERSIST DURING CONFIGURATION CHANGES AS WELL
        SharedViewModel model = ViewModelProviders.of(this).get(SharedViewModel.class);
        model.select(item);
```
 3. Shared data is available after activity recreated on config changes
 in this case data has been shown on details framgnet UI
 ``` Data is received in details fragment after clicking on items
    SharedViewModel model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        model.getSelected().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User dummyItem) {
                Log.i(TAG, "onChanged.... " + dummyItem.toString());
                textView.setText(dummyItem.toString());
            }
        });
        
```

#### UI

![device-2018-02-17-203945](https://user-images.githubusercontent.com/28217318/36342368-9ca97442-1423-11e8-8044-bdd83452e424.png)

![device-2018-02-17-203835](https://user-images.githubusercontent.com/28217318/36342367-9c74e1b4-1423-11e8-9a3a-92e491e70fe9.png)


##### References
https://developer.android.com/topic/libraries/architecture/viewmodel.html
https://codelabs.developers.google.com/codelabs/android-lifecycles/#0
