
package com.ccf.dtsma_coding_krewe.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.ccf.dtsma_coding_krewe.R;


public abstract class SingleFragmentActivity extends BaseActivity {
    public static final String TAG = "SingleFragmentActivity";


    protected abstract Fragment createFragment();

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @IdRes
    protected int getFragmentResId() {
        return R.id.fragmentContainer;
    }

    protected String getFragmentTag() {
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(getFragmentResId());

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(getFragmentResId(), fragment, getFragmentTag())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
