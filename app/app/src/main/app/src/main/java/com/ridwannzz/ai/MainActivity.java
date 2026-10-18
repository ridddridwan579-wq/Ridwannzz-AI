package com.ridwannzz.ai;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.view.*;
import android.widget.*;
import android.content.Intent;
public class MainActivity extends Activity {

    LinearLayout layout;
    EditText input;
    TextView chat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(1);
        layout.setPadding(20, 30, 20, 20);
        layout.setBackgroundColor(Color.rgb(18, 18, 30));

        TextView title = new TextView(this);
        title.setText("🤖 Ridwannzz AI");
        title.setTextSize(26);
        title.setTextColor(Color.CYAN);
        layout.addView(title);

        chat = new TextView(this);
        chat.setText("\nHalo bro! Gue Ridwannzz AI.\nTanya apa aja tentang pelajaran!");
        chat.setTextColor(Color.WHITE);
        chat.setTextSize(17);
        layout.addView(chat);

        input = new EditText(this);
        input.setHint("Tulis pertanyaan...");
        input.setTextColor(Color.WHITE);
        input.setHintTextColor(Color.GRAY);
        layout.addView(input);

        Button send = new Button(this);
        send.setText("KIRIM");
        layout.addView(send);

        Button floating = new Button(this);
        floating.setText("BUKA LAYAR MENGAMBANG");
        layout.addView(floating);

        send.setOnClickListener(v -> {
            String text = input.getText().toString().trim();
            if (!text.isEmpty()) {
                chat.append("\n\nLu: " + text);
                chat.append("\nAI: Fitur AI online akan kita sambungkan berikutnya.");
                input.setText("");
            }
        });

        floating.setOnClickListener(v -> {
            if (android.provider.Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(this, FloatingService.class);
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(intent);
                } else {
                    startService(intent);
                }
            } else {
                Intent intent = new Intent(
                    android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    android.net.Uri.parse("package:" + getPackageName())
                );
                startActivity(intent);
            }
        });

        setContentView(layout);
    }
}
