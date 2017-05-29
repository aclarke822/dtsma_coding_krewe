package com.ccf.dtsma_coding_krewe.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ccf.dtsma_coding_krewe.R;



public abstract class BaseActivity extends AppCompatActivity {

    protected ProgressDialog mProgressDialog;
    protected Handler mHandler;

    private Toolbar mActionBarToolbar;
    private DrawerLayout mDrawerLayout;
    private boolean mUseToolbar;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUseToolbar = true;
        mHandler = new Handler();

    }


    protected void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mActionBarToolbar != null) {
                if (mUseToolbar) {
                    setSupportActionBar(mActionBarToolbar);
                } else {
                    mActionBarToolbar.setVisibility(View.GONE);
                }
            }
        }
        return mActionBarToolbar;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
