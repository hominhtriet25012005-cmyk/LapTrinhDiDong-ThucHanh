package com.example.laptrinhdidong;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private Button btnDownload;
    private ProgressBar progressBar;
    private ImageView ivDownloaded;
    private Runnable pendingDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnDownload = findViewById(R.id.btnDownload);
        progressBar = findViewById(R.id.progressBar);
        ivDownloaded = findViewById(R.id.ivDownloaded);

        btnDownload.setOnClickListener(v -> {
            // Ẩn ảnh đi và hiện vòng xoay tải
            ivDownloaded.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            btnDownload.setEnabled(false);

            // Dùng Handler để giả lập độ trễ chờ tải trong 3 giây (3000 milliseconds)
            pendingDownload = () -> {
                // Sau 3 giây: Tắt vòng xoay
                progressBar.setVisibility(View.INVISIBLE);

                // Gắn ảnh vào và cho hiện ảnh lên
                ivDownloaded.setImageResource(R.drawable.sample_image); // Cần có file tên sample_image trong thư mục drawable
                ivDownloaded.setVisibility(View.VISIBLE);
                btnDownload.setEnabled(true);
                pendingDownload = null;

            };
            mainHandler.postDelayed(pendingDownload, 3000);
        });
    }

    @Override
    protected void onDestroy() {
        if (pendingDownload != null) {
            mainHandler.removeCallbacks(pendingDownload);
            pendingDownload = null;
        }
        super.onDestroy();
    }
}
