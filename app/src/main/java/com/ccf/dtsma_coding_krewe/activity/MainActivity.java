package com.ccf.dtsma_coding_krewe.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

import com.ccf.dtsma_coding_krewe.R;
import com.ccf.dtsma_coding_krewe.fragment.MainFragment;


public class MainActivity extends SingleFragmentActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }

    @Override
    protected String getFragmentTag() {
        return MainFragment.TAG;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentResId() {
        return R.id.container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("LEA");
        } else {
            Log.e(TAG, "android.support.v7.app.ActionBar getSupportActionBar() returned null.");
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
