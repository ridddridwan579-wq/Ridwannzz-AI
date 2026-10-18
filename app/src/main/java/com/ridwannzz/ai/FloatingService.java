package com.ridwannzz.ai;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class FloatingService extends Service {

    private WindowManager wm;
    private View box;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        LinearLayout panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(24, 20, 24, 20);
        panel.setBackgroundColor(0xFF17172B);

        TextView title = new TextView(this);
        title.setText("Ridwannzz AI  ✕");
        title.setTextColor(0xFF00FFFF);
        title.setTextSize(18);
        panel.addView(title);

        EditText input = new EditText(this);
        input.setHint("Tanya AI...");
        input.setTextColor(Color.WHITE);
        panel.addView(input);

        TextView answer = new TextView(this);
        answer.setText("Halo bro! Gue siap bantu belajar.");
        answer.setTextColor(Color.WHITE);
        panel.addView(answer);

        Button send = new Button(this);
        send.setText("KIRIM");
        panel.addView(send);

        send.setOnClickListener(v -> {
            String q = input.getText().toString().trim();

            if (!q.isEmpty()) {
                answer.setText(
                    "Pertanyaan: " + q
                    + "\nAI online akan disambungkan berikutnya."
                );
                input.setText("");
            }
        });

        title.setOnClickListener(v -> stopSelf());

        box = panel;

        int type = Build.VERSION.SDK_INT >= 26
            ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            : WindowManager.LayoutParams.TYPE_PHONE;

        WindowManager.LayoutParams params =
            new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                type,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            );

        params.gravity = Gravity.TOP | Gravity.END;
        params.x = 20;
        params.y = 150;

        try {
            wm.addView(box, params);
        } catch (Exception e) {
            stopSelf();
        }
    }

    @Override
    public void onDestroy() {
        if (wm != null && box != null) {
            try {
                wm.removeView(box);
            } catch (Exception ignored) {
            }
        }

        super.onDestroy();
    }
}
