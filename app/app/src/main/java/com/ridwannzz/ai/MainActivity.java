package com.ridwannzz.ai;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.content.Intent;
import android.provider.Settings;
import android.net.Uri;
import android.view.Gravity;
import android.widget.*;

public class MainActivity extends Activity {

    LinearLayout layout;
    EditText input;
    TextView chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 30, 24, 24);
        layout.setBackgroundColor(Color.rgb(18, 18, 30));

        TextView title = new TextView(this);
        title.setText("🤖 Ridwannzz AI");
        title.setTextSize(26);
        title.setTextColor(Color.CYAN);
        layout.addView(title);

        chat = new TextView(this);
        chat.setText(
            "\nHalo bro! Gue Ridwannzz AI.\n" +
            "Tanya apa aja tentang pelajaran!"
        );
        chat.setTextColor(Color.WHITE);
        chat.setTextSize(17);
        chat.setPadding(0, 24, 0, 24);
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
                chat.append(
                    "\nAI: Halo bro! Fitur AI online " +
                    "akan disambungkan berikutnya."
                );
                input.setText("");
            }
        });

        floating.setOnClickListener(v -> {
            if (Settings.canDrawOverlays(this)) {
                Intent intent =
                    new Intent(this, FloatingService.class);

                try {
                    startService(intent);
                } catch (Exception e) {
                    Toast.makeText(
                        this,
                        "Gagal membuka layar mengambang",
                        Toast.LENGTH_LONG
                    ).show();
                }
            } else {
                Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())
                );
                startActivity(intent);
            }
        });

        setContentView(layout);
    }
}
