package com.ridwannzz.ai;

import android.app.*;
import android.content.*;
import android.graphics.PixelFormat;
import android.os.*;
import android.view.*;
import android.widget.*;

public class FloatingService extends Service {

    WindowManager wm;
    View box;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        LinearLayout panel = new LinearLayout(this);
        panel.setOrientation(1);
        panel.setPadding(20, 15, 20, 15);
        panel.setBackgroundColor(0xFF17172B);

        TextView title = new TextView(this);
        title.setText("Ridwannzz AI  ✕");
        title.setTextColor(0xFF00FFFF);
        title.setTextSize(18);
        panel.addView(title);

        EditText input = new EditText(this);
        input.setHint("Tanya AI...");
        input.setTextColor(0xFFFFFFFF);
        panel.addView(input);

        TextView answer = new TextView(this);
        answer.setText("Halo bro! Gue siap bantu belajar.");
        answer.setTextColor(0xFFFFFFFF);
        panel.addView(answer);

        Button send = new Button(this);
        send.setText("KIRIM");
        panel.addView(send);

        send.setOnClickListener(v -> {
            String q = input.getText().toString().trim();
            if (!q.isEmpty()) {
                answer.setText("Pertanyaan: " + q +
                    "\nAI online akan disambungkan berikutnya.");
                input.setText("");
            }
        });

        title.setOnClickListener(v -> stopSelf());

        box = panel;

        WindowManager.LayoutParams params =
            new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                Build.VERSION.SDK_INT >= 26
                    ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                    : WindowManager.LayoutParams.TYPE_PHONE,
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
        super.onDestroy();
        if (wm != null && box != null) {
            try {
                wm.removeView(box);
            } catch (Exception ignored) {}
        }
    }
}
