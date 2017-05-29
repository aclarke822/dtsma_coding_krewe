package com.ccf.dtsma_coding_krewe.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ccf.dtsma_coding_krewe.R;
import com.ccf.dtsma_coding_krewe.activity.MapsActivity;


public class MainFragment extends BaseFragment {
    public static final String TAG = "FaqFragment";


    private View mLoadingView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    @LayoutRes
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);

        mLoadingView = view.findViewById(R.id.loading_view);

        final Button mapButton = (Button) view.findViewById(R.id.map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingView.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);

            }
        });

        final TextView callPhone = (TextView) view.findViewById(R.id.call_button);

        callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getActivity().getPackageManager();
                if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
                    showRestaurantPausedPopup();
                } else {
                    // Cannot place phone call
                    Toast.makeText(getActivity(),
                            "Your device is unable to place a telephone call.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        mLoadingView.setVisibility(View.GONE);
    }

    private void showRestaurantPausedPopup() {
        if (isAdded()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.modal_call_911, null);
            dialogBuilder.setView(dialogView);

            final AlertDialog alert = dialogBuilder.create();


            final Button modalCancel = (Button) dialogView
                    .findViewById(R.id.call_no);

            final Button modalContinue = (Button) dialogView
                    .findViewById(R.id.call_yes);

            modalCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (isAdded()) {
                        modalCancel.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
//                        mLoadingView.setVisibility(View.GONE);
                        alert.dismiss();
                    }
                }
            });

            modalContinue.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (isAdded()) {
                        String url = "tel:911";
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                        startActivity(intent);
                    }
                }
            });

            alert.show();

            if (alert.getWindow() != null) {
                int width = (int) (getResources().getDisplayMetrics().widthPixels * .85);
                alert.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        }
    }
}
