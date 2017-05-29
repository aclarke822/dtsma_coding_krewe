

package com.ccf.dtsma_coding_krewe.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {
    public static final String TAG = "BaseFragment";

    private FragmentBackStackManager mBackStackManager;

    @LayoutRes
    public abstract int getLayoutResId();

    public interface FragmentBackStackManager {
        void pushFragment(Fragment fragment, String tag);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity) {
            a = (Activity) context;
            if (a instanceof FragmentBackStackManager) {
                mBackStackManager = (FragmentBackStackManager) a;
            }
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        mBackStackManager = null;
    }

    @Override
    public void onStop()
    {
        super.onStop();
//        getActivity().stopService(new Intent(getActivity(), LocationService.class));
    }

    public void onDisplay() {
        // This area intentionally left blank
    }

    public FragmentBackStackManager getBackStackManager() {
        return mBackStackManager;
    }

    public void setBackStackManager(@Nullable FragmentBackStackManager backStackManager) {
        mBackStackManager = backStackManager;
    }
}
