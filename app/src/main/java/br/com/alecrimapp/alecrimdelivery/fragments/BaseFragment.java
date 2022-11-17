package br.com.alecrimapp.alecrimdelivery.fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import br.com.alecrimapp.alecrimdelivery.activities.MainActivity;

/**
 * Created by ricardomiranda on 08/01/16.
 */
public class BaseFragment extends Fragment {

    public MainActivity getMainActivity(){
        // Local variables
        MainActivity activity = null;

        activity = (MainActivity) getActivity();

        return activity;
    }

    public void addFragment(@NonNull Fragment fragment) {
        getMainActivity().addFragment(fragment);
    }

    public void replaceFragment(@NonNull Fragment fragment,
                                @Nullable String backStackStateName) {
        getMainActivity().replaceFragment(fragment, backStackStateName);
    }
}
