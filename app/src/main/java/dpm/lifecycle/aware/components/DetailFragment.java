package dpm.lifecycle.aware.components;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {

    private static final String TAG = DetailFragment.class.getSimpleName();

    private TextView textView;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        SharedViewModel model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        model.getSelected().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User dummyItem) {
                Log.i(TAG, "onChanged.... " + dummyItem.toString());
                textView.setText(dummyItem.toString());
            }
        });
      /*  model.getUsers().observe(this, users -> {
            // update UI
//            Log.i(TAG, "users...." + users);
        });*/

        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        textView = view.findViewById(R.id.textView);
        return view;
    }
}
