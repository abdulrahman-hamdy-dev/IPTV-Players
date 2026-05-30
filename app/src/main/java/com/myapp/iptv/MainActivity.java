package com.myapp.iptv;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.content.Intent; // السطر ده اللي كان ناقص وحل المشكلة بالملي
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView btnLiveTv, btnMovies, btnSeries, btnSettings, btnCatchup, btnReload;
    private TextView textDateTime, textDeviceId;
    private Handler timeHandler;
    private Runnable timeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // تفعيل الشاشة الكاملة
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        setContentView(R.layout.activity_main);

        // ربط نصوص البيانات الديناميكية
        textDateTime = findViewById(R.id.text_date_time);
        textDeviceId = findViewById(R.id.text_device_id);

        // 1. جلب معرف الجهاز الحقيقي الفعلي للهاتف
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        if (androidId != null && !androidId.isEmpty()) {
            textDeviceId.setText("معرف الجهاز: " + androidId.toUpperCase());
        } else {
            textDeviceId.setText("معرف الجهاز: Unknown");
        }

        // 2. تشغيل الوقت والتاريخ بشكل حي وثانية بثانية
        timeHandler = new Handler(Looper.getMainLooper());
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a\ndd/MM/yyyy", new Locale("ar"));
                String currentDateTime = sdf.format(new Date());
                textDateTime.setText(currentDateTime);
                timeHandler.postDelayed(this, 1000);
            }
        };
        timeHandler.post(timeRunnable);

        // ربط الأزرار المترتبة من اليسار لليمين
        btnLiveTv = findViewById(R.id.btn_live_tv);
        btnMovies = findViewById(R.id.btn_movies);
        btnSeries = findViewById(R.id.btn_series);
        btnSettings = findViewById(R.id.btn_settings);
        btnCatchup = findViewById(R.id.btn_catchup);
        btnReload = findViewById(R.id.btn_reload);

        // برمجة أحداث الضغط والانتقال للشاشة الجديدة
        btnLiveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LiveTvActivity.class);
                startActivity(intent);
            }
        });

        btnMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "جاري فتح قسم الأفلام...", Toast.LENGTH_SHORT).show();
            }
        });

        btnSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "جاري فتح قسم المسلسلات...", Toast.LENGTH_SHORT).show();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "جاري فتح الإعدادات...", Toast.LENGTH_SHORT).show();
            }
        });

        btnCatchup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "جاري فتح قسم القنوات المسجلة...", Toast.LENGTH_SHORT).show();
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "جاري تحديث البيانات...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timeHandler != null && timeRunnable != null) {
            timeHandler.removeCallbacks(timeRunnable);
        }
    }
}

