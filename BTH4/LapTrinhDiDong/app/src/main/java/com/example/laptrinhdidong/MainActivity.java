package com.example.laptrinhdidong;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private RadioGroup rgGender;
    private CheckBox cbMusic;
    private CheckBox cbMovie;
    private CheckBox cbBook;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        etName = findViewById(R.id.etName);
        rgGender = findViewById(R.id.rgGender);
        cbMusic = findViewById(R.id.cbMusic);
        cbMovie = findViewById(R.id.cbMovie);
        cbBook = findViewById(R.id.cbBook);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Xử lý khi nhấn nút Gửi
        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                // Cảnh báo nếu chưa nhập tên
                Toast.makeText(
                        MainActivity.this,
                        R.string.survey_name_required,
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int selectedGenderId = rgGender.getCheckedRadioButtonId();
            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender.getText().toString();

            // Lấy sở thích
            StringBuilder hobbies = new StringBuilder();
            if (cbMusic.isChecked()) hobbies.append(getString(R.string.hobby_music)).append(", ");
            if (cbMovie.isChecked()) hobbies.append(getString(R.string.hobby_movies)).append(", ");
            if (cbBook.isChecked()) hobbies.append(getString(R.string.hobby_reading)).append(", ");

            // Xóa dấu phẩy thừa ở cuối nếu có
            if (hobbies.length() > 0) {
                hobbies.setLength(hobbies.length() - 2);
            } else {
                hobbies.append(getString(R.string.no_hobbies));
            }

            // Hiển thị kết quả
            String message = getString(R.string.survey_summary, name, gender, hobbies.toString());
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exercise_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_country) {
            startActivity(new Intent(this, MainActivity2.class));
            return true;
        }
        if (itemId == R.id.action_download) {
            startActivity(new Intent(this, MainActivity3.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
