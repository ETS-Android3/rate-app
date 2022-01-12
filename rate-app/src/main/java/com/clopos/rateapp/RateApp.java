package com.clopos.rateapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

public class RateApp implements View.OnTouchListener, View.OnClickListener {

    private static final String SHOW_COUNT = "show_count";
    private static final String TAG = "RateApp";
    private static RateApp instance;
    private final Context context;
    private final String appId;
    private final String color;
    private final int showAt;
    private ImageView star_1, star_2, star_3, star_4, star_5;
    private MaterialDialog dialog;
    private final SharedPreferences pref;

    public static RateApp initialize(Context context, String appId, String color, int showAt) {
        if (instance == null) {
            instance = new RateApp(context, appId, color, showAt);
        }

        return instance;
    }

    private RateApp(final Context context, String appId, String color, int showAt) {
        this.context = context;
        this.appId = appId;
        this.color = color;
        this.showAt = showAt;

        pref = PreferenceManager.getDefaultSharedPreferences(context);
        int showCount = pref.getInt(SHOW_COUNT, 0);

        pref.edit().putInt(SHOW_COUNT, showCount + 1).apply();

        new Handler().postDelayed(() -> {
            if (showCount == showAt) {
                showRateDialog();
            }
        }, 2000);

    }

    public void showRateDialog() {
        dialog = new MaterialDialog.Builder(context).cancelable(false).title(R.string.txt_rate_app).customView(R.layout.rate_layout, false).build();

        LinearLayout layout = (LinearLayout) dialog.getCustomView();

        if (layout != null) {
            star_1 = layout.findViewById(R.id.star_1);
            star_2 = layout.findViewById(R.id.star_2);
            star_3 = layout.findViewById(R.id.star_3);
            star_4 = layout.findViewById(R.id.star_4);
            star_5 = layout.findViewById(R.id.star_5);

            Button btnAskMeLater = layout.findViewById(R.id.btn_later);
            Button btnCancel = layout.findViewById(R.id.btn_cancel);

            btnAskMeLater.setTextColor(Color.parseColor(color));
            btnCancel.setTextColor(Color.parseColor(color));

            setOnTouchListeners(star_1, star_2, star_3, star_4, star_5);

            setOnClickListeners(btnAskMeLater, btnCancel);
        }

        dialog.show();
        Log.i(TAG, "Rate dialog showed");
    }

    private void setOnClickListeners(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    private void setOnTouchListeners(View... views) {
        for (View view : views) {
            view.setOnTouchListener(this);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int i = v.getId();
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled}
        };

        int[] colors = new int[]{
                Color.parseColor(color)
        };

        ColorStateList colorStateList = new ColorStateList(states, colors);
        if (i == R.id.star_1) {
            star_1.setImageTintList(colorStateList);
            star_2.setImageResource(R.drawable.star_false);
            star_3.setImageResource(R.drawable.star_false);
            star_4.setImageResource(R.drawable.star_false);
            star_5.setImageResource(R.drawable.star_false);

            new Handler().postDelayed(() -> {
                Toast.makeText(context, R.string.txt_successful, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Log.i(TAG, "Rate dialog dismissed with 1 star");
            }, 200);

        } else if (i == R.id.star_2) {
            star_1.setImageTintList(colorStateList);
            star_2.setImageTintList(colorStateList);
            star_3.setImageResource(R.drawable.star_false);
            star_4.setImageResource(R.drawable.star_false);
            star_5.setImageResource(R.drawable.star_false);

            new Handler().postDelayed(() -> {
                Toast.makeText(context, R.string.txt_successful, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Log.i(TAG, "Rate dialog dismissed with 2 star");
            }, 200);

        } else if (i == R.id.star_3) {
            star_1.setImageTintList(colorStateList);
            star_2.setImageTintList(colorStateList);
            star_3.setImageTintList(colorStateList);
            star_4.setImageResource(R.drawable.star_false);
            star_5.setImageResource(R.drawable.star_false);

            new Handler().postDelayed(() -> {
                Toast.makeText(context, R.string.txt_successful, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Log.i(TAG, "Rate dialog dismissed with 3 star");
            }, 200);

        } else if (i == R.id.star_4) {
            star_1.setImageTintList(colorStateList);
            star_2.setImageTintList(colorStateList);
            star_3.setImageTintList(colorStateList);
            star_4.setImageTintList(colorStateList);
            star_5.setImageResource(R.drawable.star_false);

            new Handler().postDelayed(() -> {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appId)));
                dialog.dismiss();
                Log.i(TAG, "Rate dialog dismissed with 4 star");
            }, 200);

        } else if (i == R.id.star_5) {
            star_1.setImageTintList(colorStateList);
            star_2.setImageTintList(colorStateList);
            star_3.setImageTintList(colorStateList);
            star_4.setImageTintList(colorStateList);
            star_5.setImageTintList(colorStateList);

            new Handler().postDelayed(() -> {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appId)));
                dialog.dismiss();
                Log.i(TAG, "Rate dialog dismissed with 5 star");
            }, 200);
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_later) {
            pref.edit().putInt(SHOW_COUNT, 3).apply();
            dialog.dismiss();
            Log.i(TAG, "Rate dialog dismissed by btn ask later");
        } else if (i == R.id.btn_cancel) {
            pref.edit().putInt(SHOW_COUNT, 5).apply();
            dialog.dismiss();
            Log.i(TAG, "Rate dialog dismissed by btn cancel");
        }
    }
}