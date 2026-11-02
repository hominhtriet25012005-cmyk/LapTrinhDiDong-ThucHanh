package com.example.laptrinhdidong;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private Spinner spinnerCountry;
    private ImageView ivFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spinnerCountry = findViewById(R.id.spinnerCountry);
        ivFlag = findViewById(R.id.ivFlag);
        String[] countries = getResources().getStringArray(R.array.countries);
        int[] flags = {
                R.drawable.flag_vn,
                R.drawable.flag_us,
                R.drawable.flag_jp,
                R.drawable.flag_fr
        };

        // Đổ dữ liệu chữ vào Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countries);
        spinnerCountry.setAdapter(adapter);
        ivFlag.setImageResource(flags[0]);

        // Lắng nghe sự kiện khi chọn 1 dòng trong Spinner
        final boolean[] isInitialSelection = {true};
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Đổi hình ảnh cờ tương ứng
                ivFlag.setImageResource(flags[position]);

                // Spinner tự chọn phần tử đầu tiên khi mở màn hình; chỉ hiện Dialog
                // sau khi người dùng chủ động chọn một quốc gia.
                if (isInitialSelection[0]) {
                    isInitialSelection[0] = false;
                    return;
                }

                // Hiển thị Dialog thông báo
                new AlertDialog.Builder(MainActivity2.this)
                        .setTitle(R.string.dialog_title)
                        .setMessage(getString(R.string.country_selected, countries[position]))
                        .setPositiveButton(R.string.dialog_ok, null)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
