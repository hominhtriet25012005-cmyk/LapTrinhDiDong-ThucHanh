# BÀI THỰC HÀNH 04 – CODE VÀ GIẢI THÍCH CHI TIẾT

> Tài liệu được biên soạn lại từ file `lab_04_full_code.md` theo hướng giảng dạy. Mã nguồn được khôi phục về cú pháp XML/Java bình thường, bổ sung chú thích sát từng dòng, giải thích đầu vào, cách xử lý, output dự kiến, cơ sở lý thuyết, lệnh chạy và lỗi thường gặp.

---

# 1. KIẾN THỨC NỀN

## 1.1. Quan hệ giữa XML và Java trong Android

- File XML trong `res/layout` mô tả giao diện: vị trí, kích thước, màu sắc và thành phần hiển thị.
- Lớp Java kế thừa `AppCompatActivity` xử lý hành vi: đọc dữ liệu, bắt sự kiện và cập nhật giao diện.
- `setContentView(...)` gắn Activity với file layout.
- `findViewById(...)` tìm View dựa trên ID đã khai báo bằng `@+id/...` trong XML.
- `setOnClickListener(...)` đăng ký đoạn mã chạy khi người dùng nhấn một View.

## 1.2. Các đơn vị và giá trị kích thước

- `dp`: dùng cho kích thước và khoảng cách giao diện.
- `sp`: dùng cho kích thước chữ và tôn trọng cài đặt cỡ chữ hệ thống.
- `match_parent`: chiếm toàn bộ không gian khả dụng của View cha.
- `wrap_content`: chỉ chiếm đủ không gian để chứa nội dung.

## 1.3. Padding, margin, gravity và orientation

- `padding`: khoảng cách từ biên View vào nội dung bên trong.
- `layout_margin...`: khoảng cách bên ngoài giữa View hiện tại với thành phần xung quanh.
- `gravity`: căn nội dung hoặc các View con bên trong View hiện tại.
- `orientation="vertical"`: xếp View con từ trên xuống dưới.
- `orientation="horizontal"`: xếp View con trên cùng một hàng.

---

# 2. CÂU 1 – KHẢO SÁT NGƯỜI DÙNG

## 2.1. Giao diện `activity_main.xml`

**Đường dẫn:** `app/src/main/res/layout/activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- Khai báo tài liệu XML dùng chuẩn 1.0 và bảng mã UTF-8 để hỗ trợ tiếng Việt. -->

<LinearLayout
    <!-- Namespace chuẩn, cho phép sử dụng các thuộc tính android:... -->
    xmlns:android="http://schemas.android.com/apk/res/android"

    <!-- Layout gốc rộng bằng toàn bộ màn hình. -->
    android:layout_width="match_parent"

    <!-- Layout gốc cao bằng toàn bộ màn hình. -->
    android:layout_height="match_parent"

    <!-- Đặt nền giao diện thành màu trắng. -->
    android:background="#FFFFFF"

    <!-- Căn các View con vào giữa theo chiều ngang. -->
    android:gravity="center_horizontal"

    <!-- Xếp các View con lần lượt từ trên xuống dưới. -->
    android:orientation="vertical"

    <!-- Tạo khoảng đệm 24dp ở bên trong cả bốn cạnh. -->
    android:padding="24dp">

    <!-- TextView dùng làm tiêu đề màn hình. -->
    <TextView
        <!-- Chiều rộng vừa đủ chứa dòng chữ tiêu đề. -->
        android:layout_width="wrap_content"

        <!-- Chiều cao vừa đủ chứa nội dung. -->
        android:layout_height="wrap_content"

        <!-- Tạo khoảng cách 24dp với ô nhập tên nằm phía dưới. -->
        android:layout_marginBottom="24dp"

        <!-- Nội dung chữ được hiển thị. -->
        android:text="Khảo sát người dùng"

        <!-- Cỡ chữ 24sp. -->
        android:textSize="24sp"

        <!-- Hiển thị chữ đậm. -->
        android:textStyle="bold" />

    <!-- EditText để người dùng nhập tên. -->
    <EditText
        <!-- Tạo ID etName để Java có thể tìm thấy View này. -->
        android:id="@+id/etName"

        <!-- Ô nhập kéo dài theo chiều ngang của vùng chứa. -->
        android:layout_width="match_parent"

        <!-- Chiều cao phụ thuộc nội dung và kiểu hiển thị mặc định. -->
        android:layout_height="wrap_content"

        <!-- Cách nhóm chọn giới tính phía dưới 16dp. -->
        android:layout_marginBottom="16dp"

        <!-- Dùng drawable edittext_bg làm nền. File này phải tồn tại trong res/drawable. -->
        android:background="@drawable/edittext_bg"

        <!-- Chữ gợi ý xuất hiện khi ô nhập chưa có dữ liệu. -->
        android:hint="Nhập tên"

        <!-- Tạo khoảng đệm 12dp giữa chữ và viền ô nhập. -->
        android:padding="12dp" />

    <!-- RadioGroup quản lý một nhóm RadioButton chỉ được chọn một phương án. -->
    <RadioGroup
        android:id="@+id/rgGender"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="16dp"

        <!-- Căn hai lựa chọn vào giữa theo chiều ngang. -->
        android:gravity="center_horizontal"

        <!-- Xếp Nam và Nữ trên cùng một hàng. -->
        android:orientation="horizontal">

        <RadioButton
            android:id="@+id/rbMale"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"

            <!-- Tạo khoảng cách 32dp sau nút Nam. -->
            android:layout_marginEnd="32dp"

            <!-- Nam được chọn sẵn khi màn hình mở. -->
            android:checked="true"
            android:text="Nam" />

        <RadioButton
            android:id="@+id/rbFemale"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Nữ" />
    </RadioGroup>

    <!-- LinearLayout chứa các sở thích. -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="24dp"

        <!-- Xếp ba CheckBox thành một cột. -->
        android:orientation="vertical">

        <!-- CheckBox cho phép chọn hoặc bỏ chọn độc lập với các CheckBox khác. -->
        <CheckBox
            android:id="@+id/cbMusic"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Nghe nhạc" />

        <CheckBox
            android:id="@+id/cbMovie"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Xem phim" />

        <CheckBox
            android:id="@+id/cbBook"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Đọc sách" />
    </LinearLayout>

    <!-- Nút gửi dữ liệu khảo sát. -->
    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        <!-- Tô màu nền nút bằng màu xanh. -->
        android:backgroundTint="#3366CC"

        <!-- Tăng khoảng trống bên trái và phải nội dung nút. -->
        android:paddingHorizontal="32dp"

        <!-- Tăng khoảng trống phía trên và dưới nội dung nút. -->
        android:paddingVertical="12dp"

        android:text="Gửi khảo sát"

        <!-- Không tự chuyển toàn bộ chữ trên nút thành chữ in hoa. -->
        android:textAllCaps="false"

        <!-- Màu chữ trắng. -->
        android:textColor="#FFFFFF" />
</LinearLayout>
```

### Output giao diện

Màn hình hiển thị tiêu đề **Khảo sát người dùng**, ô nhập tên, hai lựa chọn giới tính, ba sở thích có thể chọn độc lập và nút **Gửi khảo sát**.

---

## 2.2. Xử lý sự kiện trong `MainActivity.java`

```java
// Khai báo package chứa lớp. Phải đổi thành package thực tế của dự án.
package com.example.laptrinhdidong;

// Bundle chứa trạng thái được truyền vào Activity khi tạo hoặc khôi phục.
import android.os.Bundle;

// Các lớp View cần sử dụng trong chương trình.
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

// Activity tương thích với hệ sinh thái AndroidX/AppCompat.
import androidx.appcompat.app.AppCompatActivity;

// Khai báo MainActivity kế thừa AppCompatActivity.
public class MainActivity extends AppCompatActivity {

    // Biến tham chiếu đến ô nhập tên.
    EditText etName;

    // Biến tham chiếu đến nhóm giới tính.
    RadioGroup rgGender;

    // Hai RadioButton tương ứng Nam và Nữ.
    RadioButton rbMale, rbFemale;

    // Ba CheckBox sở thích.
    CheckBox cbMusic, cbMovie, cbBook;

    // Nút gửi khảo sát.
    Button btnSubmit;

    // Ghi đè hàm được gọi khi Activity bắt đầu được tạo.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Gọi onCreate của lớp cha để Android khởi tạo Activity đúng vòng đời.
        super.onCreate(savedInstanceState);

        // Nạp activity_main.xml làm giao diện cho Activity.
        setContentView(R.layout.activity_main);

        // Ánh xạ từng biến Java tới View có ID tương ứng trong XML.
        etName = findViewById(R.id.etName);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        cbMusic = findViewById(R.id.cbMusic);
        cbMovie = findViewById(R.id.cbMovie);
        cbBook = findViewById(R.id.cbBook);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Đăng ký listener. Khối lệnh bên trong chạy mỗi lần btnSubmit được nhấn.
        btnSubmit.setOnClickListener(v -> {

            // Lấy chữ từ EditText, chuyển thành String và xóa khoảng trắng hai đầu.
            String name = etName.getText().toString().trim();

            // Kiểm tra chuỗi tên có rỗng hay không.
            if (name.isEmpty()) {

                // Tạo Toast ngắn để cảnh báo người dùng.
                Toast.makeText(
                        MainActivity.this,
                        "Vui lòng nhập tên!",
                        Toast.LENGTH_SHORT
                ).show();

                // Dừng listener, không chạy các bước tổng hợp dữ liệu phía dưới.
                return;
            }

            // Toán tử ba ngôi: nếu rbMale được chọn thì trả về Nam, ngược lại trả về Nữ.
            String gender = rbMale.isChecked() ? "Nam" : "Nữ";

            // StringBuilder giúp ghép nhiều chuỗi hiệu quả hơn việc cộng String liên tục.
            StringBuilder hobbies = new StringBuilder();

            // Nếu CheckBox được chọn, thêm tên sở thích và dấu phân cách.
            if (cbMusic.isChecked()) hobbies.append("Nghe nhạc, ");
            if (cbMovie.isChecked()) hobbies.append("Xem phim, ");
            if (cbBook.isChecked()) hobbies.append("Đọc sách, ");

            // Nếu có ít nhất một sở thích thì chuỗi đang kết thúc bằng dấu phẩy và khoảng trắng.
            if (hobbies.length() > 0) {

                // Cắt hai ký tự cuối: dấu phẩy và khoảng trắng.
                hobbies.setLength(hobbies.length() - 2);
            } else {

                // Nếu không có CheckBox nào được chọn, ghi nội dung mặc định.
                hobbies.append("Không có");
            }

            // Ghép tên, giới tính và sở thích. \n tạo ký tự xuống dòng trong chuỗi.
            String message = "Chào " + name
                    + "\nGiới tính: " + gender
                    + "\nSở thích: " + hobbies.toString();

            // Hiển thị kết quả bằng Toast dài.
            Toast.makeText(
                    MainActivity.this,
                    message,
                    Toast.LENGTH_LONG
            ).show();
        });
    }
}
```

### Luồng xử lý và output

1. Người dùng nhập tên.
2. Chọn một giới tính. Mặc định là **Nam**.
3. Chọn không, một hoặc nhiều sở thích.
4. Nhấn **Gửi khảo sát**.
5. Nếu tên rỗng, Toast hiển thị `Vui lòng nhập tên!`.
6. Nếu hợp lệ, ví dụ tên `Thanh`, giới tính `Nam`, chọn nghe nhạc và đọc sách, Toast hiển thị:

```text
Chào Thanh
Giới tính: Nam
Sở thích: Nghe nhạc, Đọc sách
```

### Điểm lý thuyết quan trọng

- `RadioGroup` bảo đảm chỉ một `RadioButton` con được chọn tại một thời điểm.
- Các `CheckBox` hoạt động độc lập nên có thể chọn nhiều sở thích.
- `trim()` loại bỏ khoảng trắng thừa ở đầu và cuối tên.
- `return` dừng xử lý ngay khi dữ liệu không hợp lệ.
- `Toast` là thông báo tạm thời, không phù hợp để lưu kết quả lâu dài.

---

# 3. CÂU 2 – CHỌN QUỐC GIA BẰNG SPINNER

## 3.1. Giao diện `activity_main2.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFFFFF"
    android:gravity="center_horizontal"
    android:orientation="vertical"
    android:padding="24dp">

    <!-- Tiêu đề màn hình. -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="16dp"
        android:text="Chọn quốc gia"
        android:textSize="24sp"
        android:textStyle="bold" />

    <!-- Spinner là danh sách chọn dạng thả xuống. -->
    <Spinner
        android:id="@+id/spinnerCountry"

        <!-- Chiều rộng cố định 200dp. -->
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="24dp"
        android:background="@drawable/edittext_bg"
        android:padding="12dp" />

    <!-- ImageView hiển thị cờ của quốc gia đang được chọn. -->
    <ImageView
        android:id="@+id/ivFlag"
        android:layout_width="200dp"
        android:layout_height="130dp"

        <!-- fitXY ép ảnh khớp cả chiều rộng và chiều cao; ảnh có thể bị méo. -->
        android:scaleType="fitXY" />
</LinearLayout>
```

### Output giao diện

Màn hình có tiêu đề, một Spinner rộng 200dp và vùng ảnh cờ 200 × 130dp. Ban đầu ImageView chưa có ảnh trong XML; Java sẽ gán ảnh khi Spinner chọn một phần tử.

---

## 3.2. Xử lý Spinner trong `MainActivity2.java`

```java
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

    // Spinner hiển thị danh sách quốc gia.
    Spinner spinnerCountry;

    // ImageView hiển thị cờ.
    ImageView ivFlag;

    // Mảng dữ liệu chữ. position 0 là Việt Nam, 1 là Mỹ, v.v.
    String[] countries = {"Việt Nam", "Mỹ", "Nhật Bản", "Pháp"};

    // Mảng ID drawable. Thứ tự phải trùng với mảng countries.
    int[] flags = {
            R.drawable.flag_vn,
            R.drawable.flag_us,
            R.drawable.flag_jp,
            R.drawable.flag_fr
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gắn Activity với layout câu 2.
        setContentView(R.layout.activity_main2);

        // Ánh xạ hai View từ XML.
        spinnerCountry = findViewById(R.id.spinnerCountry);
        ivFlag = findViewById(R.id.ivFlag);

        // Tạo adapter kiểu String.
        // this: Context hiện tại.
        // simple_spinner_dropdown_item: layout dòng có sẵn của Android.
        // countries: nguồn dữ liệu cần hiển thị.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                countries
        );

        // Gắn adapter vào Spinner để Spinner có dữ liệu.
        spinnerCountry.setAdapter(adapter);

        // Đăng ký listener xử lý khi một phần tử được chọn.
        spinnerCountry.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id
                    ) {
                        // position là vị trí phần tử được chọn.
                        // Dùng cùng position để lấy đúng cờ trong mảng flags.
                        ivFlag.setImageResource(flags[position]);

                        // Tạo và hiển thị hộp thoại thông báo.
                        new AlertDialog.Builder(MainActivity2.this)
                                .setTitle("Thông báo")
                                .setMessage("Bạn đã chọn: " + countries[position])

                                // null nghĩa là khi nhấn OK, Dialog chỉ đóng lại.
                                .setPositiveButton("OK", null)
                                .show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Hàm bắt buộc của interface.
                        // Bài này không cần xử lý khi không có phần tử nào được chọn.
                    }
                }
        );
    }
}
```

### Giải thích Adapter

Spinner không tự biết dữ liệu cần hiển thị. `ArrayAdapter<String>` đóng vai trò cầu nối:

```text
Mảng countries → ArrayAdapter → Spinner
```

Khi người dùng chọn phần tử tại `position = 2`:

```java
countries[2] // "Nhật Bản"
flags[2]     // R.drawable.flag_jp
```

Vì vậy hai mảng phải có cùng số phần tử và cùng thứ tự.

### Output dự kiến

Nếu chọn **Pháp**:

- `position` bằng `3`.
- `ivFlag` hiển thị `flag_fr`.
- Dialog xuất hiện với nội dung `Bạn đã chọn: Pháp`.

### Lưu ý hành vi ban đầu

Sau khi gọi `setAdapter`, Spinner thường tự chọn phần tử đầu tiên. Vì listener đã được gắn, Dialog có thể xuất hiện ngay lần đầu mở màn hình. Nếu chỉ muốn hiện Dialog khi người dùng thật sự thao tác, cần thêm biến đánh dấu để bỏ qua lần callback đầu tiên.

---

# 4. CÂU 3 – GIẢ LẬP TẢI ẢNH BẰNG PROGRESSBAR

## 4.1. Giao diện `activity_main3.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFFFFF"
    android:gravity="center_horizontal"
    android:orientation="vertical"
    android:padding="24dp">

    <!-- Nút bắt đầu quá trình giả lập tải. -->
    <Button
        android:id="@+id/btnDownload"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="24dp"
        android:backgroundTint="#E0E0E0"
        android:paddingHorizontal="32dp"
        android:text="Tải ảnh"
        android:textColor="#000000" />

    <!-- ProgressBar dạng vòng xoay, biểu thị đang xử lý nhưng không cho biết phần trăm. -->
    <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="24dp"

        <!-- Không nhìn thấy nhưng vẫn giữ chỗ trong bố cục. -->
        android:visibility="invisible" />

    <!-- ImageView hiển thị ảnh sau khi hết thời gian chờ. -->
    <ImageView
        android:id="@+id/ivDownloaded"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:background="@drawable/edittext_bg"
        android:padding="12dp"

        <!-- Phóng ảnh để lấp đầy View, giữ tỉ lệ và có thể cắt phần thừa. -->
        android:scaleType="centerCrop"

        <!-- Ban đầu ẩn ảnh nhưng vẫn giữ vùng 300 × 300dp. -->
        android:visibility="invisible" />
</LinearLayout>
```

### Phân biệt `visible`, `invisible` và `gone`

- `View.VISIBLE`: View hiển thị và chiếm chỗ.
- `View.INVISIBLE`: View không hiển thị nhưng vẫn chiếm chỗ.
- `View.GONE`: View không hiển thị và không chiếm chỗ.

---

## 4.2. Giả lập tải trong `MainActivity3.java`

```java
package com.example.laptrinhdidong;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    // Nút bắt đầu tải.
    Button btnDownload;

    // Vòng xoay báo đang tải.
    ProgressBar progressBar;

    // Vùng hiển thị ảnh kết quả.
    ImageView ivDownloaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Nạp layout của câu 3.
        setContentView(R.layout.activity_main3);

        // Ánh xạ View.
        btnDownload = findViewById(R.id.btnDownload);
        progressBar = findViewById(R.id.progressBar);
        ivDownloaded = findViewById(R.id.ivDownloaded);

        // Chạy khối lệnh khi người dùng nhấn nút Tải ảnh.
        btnDownload.setOnClickListener(v -> {

            // Ẩn ảnh cũ nếu người dùng nhấn tải lại.
            ivDownloaded.setVisibility(View.INVISIBLE);

            // Hiện vòng xoay báo đang xử lý.
            progressBar.setVisibility(View.VISIBLE);

            // postDelayed lên lịch chạy một khối lệnh sau một khoảng thời gian.
            new Handler().postDelayed(() -> {

                // Sau 3000ms, ẩn ProgressBar.
                progressBar.setVisibility(View.INVISIBLE);

                // Gắn sample_image từ res/drawable vào ImageView.
                ivDownloaded.setImageResource(R.drawable.sample_image);

                // Hiện ImageView sau khi đã gắn ảnh.
                ivDownloaded.setVisibility(View.VISIBLE);

            // 3000 mili giây tương đương 3 giây.
            }, 3000);
        });
    }
}
```

### Luồng hoạt động

```text
Nhấn Tải ảnh
    ↓
Ẩn ImageView
    ↓
Hiện ProgressBar
    ↓
Chờ 3000 ms
    ↓
Ẩn ProgressBar
    ↓
Gắn sample_image
    ↓
Hiện ImageView
```

### Output dự kiến

- Trước khi nhấn nút: ProgressBar và ảnh đều không nhìn thấy.
- Sau khi nhấn: vòng xoay xuất hiện.
- Sau khoảng 3 giây: vòng xoay biến mất và `sample_image` xuất hiện.

### Cảnh báo quan trọng

Đây chỉ là **giả lập độ trễ**, không tải ảnh từ Internet. `setImageResource(...)` đọc ảnh đã đóng gói sẵn trong ứng dụng. Tải ảnh mạng thực tế cần thư viện như Glide/Coil/Picasso hoặc cơ chế mạng khác, đồng thời xử lý lỗi, cache và vòng đời Activity.

`new Handler()` không chỉ rõ `Looper` là cách viết cũ và có thể bị cảnh báo ở Android mới. Cách rõ ràng hơn:

```java
new Handler(Looper.getMainLooper()).postDelayed(() -> {
    // Cập nhật giao diện
}, 3000);
```

Khi dùng cách trên, cần import:

```java
import android.os.Looper;
```

---

# 5. TÀI NGUYÊN BẮT BUỘC

Các file sau phải tồn tại, nếu không dự án sẽ báo lỗi resource:

```text
app/src/main/res/drawable/edittext_bg.xml
app/src/main/res/drawable/flag_vn.png
app/src/main/res/drawable/flag_us.png
app/src/main/res/drawable/flag_jp.png
app/src/main/res/drawable/flag_fr.png
app/src/main/res/drawable/sample_image.png
```

Tên tài nguyên Android:

- Chỉ nên gồm chữ thường, số và dấu gạch dưới.
- Không dùng dấu cách, dấu gạch ngang hoặc chữ hoa.
- Phần mở rộng ảnh không xuất hiện trong `R.drawable...`.

Ví dụ file `flag_vn.png` được tham chiếu bằng:

```java
R.drawable.flag_vn
```

## Drawable mẫu cho `edittext_bg.xml`

Nếu dự án chưa có file này, có thể tạo tại `res/drawable/edittext_bg.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <!-- Nền trắng. -->
    <solid android:color="#FFFFFF" />

    <!-- Viền xám rộng 1dp. -->
    <stroke
        android:width="1dp"
        android:color="#BDBDBD" />

    <!-- Bo tròn bốn góc 8dp. -->
    <corners android:radius="8dp" />
</shape>
```

---

# 6. KHAI BÁO ACTIVITY TRONG MANIFEST

Nếu tạo thêm `MainActivity2` và `MainActivity3`, cần bảo đảm chúng được khai báo trong `AndroidManifest.xml`:

```xml
<application
    ...>

    <activity android:name=".MainActivity3" />
    <activity android:name=".MainActivity2" />

    <activity
        android:name=".MainActivity"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
</application>
```

- Activity có `MAIN` và `LAUNCHER` là màn hình khởi động khi nhấn biểu tượng ứng dụng.
- Hai Activity còn lại có thể được mở bằng `Intent` hoặc đổi Activity launcher để chạy thử riêng.

Ví dụ mở `MainActivity2`:

```java
Intent intent = new Intent(MainActivity.this, MainActivity2.class);
startActivity(intent);
```

Cần import:

```java
import android.content.Intent;
```

---

# 7. LỆNH BUILD VÀ KIỂM TRA

Mở Terminal tại thư mục gốc có file `gradlew`.

## Windows

```bash
gradlew.bat clean assembleDebug
```

## macOS/Linux

```bash
./gradlew clean assembleDebug
```

Ý nghĩa:

- `clean`: xóa sản phẩm build cũ.
- `assembleDebug`: biên dịch phiên bản debug và tạo APK.

APK thường được tạo tại:

```text
app/build/outputs/apk/debug/app-debug.apk
```

Chạy Android Lint:

```bash
# Windows
gradlew.bat lint

# macOS/Linux
./gradlew lint
```

Lint có thể cảnh báo hardcoded text, thiếu `contentDescription`, API không phù hợp hoặc vấn đề khả năng tiếp cận.

---

# 8. LỖI THƯỜNG GẶP

## 8.1. `Cannot resolve symbol R`

Kiểm tra:

- XML có lỗi cú pháp hay không.
- Tên file resource có chữ hoa hoặc ký tự không hợp lệ hay không.
- Có thiếu tài nguyên được tham chiếu không.
- Thử **Sync Project with Gradle Files**, sau đó clean và build lại.

## 8.2. `Cannot resolve symbol flag_vn` hoặc `sample_image`

Ảnh chưa nằm trong `res/drawable`, tên bị sai hoặc chứa ký tự không hợp lệ.

## 8.3. `findViewById` trả về `null`

Activity có thể đang nạp sai layout, hoặc ID không tồn tại trong layout được truyền vào `setContentView`.

## 8.4. Nhấn nút bị crash

Mở Logcat và kiểm tra dòng có `FATAL EXCEPTION`. Các nguyên nhân hay gặp:

- View bị `null`.
- Resource không tồn tại.
- Activity chưa được khai báo trong Manifest.
- Package trong file Java không khớp cấu trúc dự án.

## 8.5. Dialog hiện ngay khi mở màn hình Spinner

Đây là hành vi callback chọn phần tử đầu tiên, không nhất thiết là lỗi. Có thể bỏ qua callback đầu bằng biến Boolean:

```java
boolean isFirstSelection = true;
```

Trong `onItemSelected`:

```java
if (isFirstSelection) {
    isFirstSelection = false;
    ivFlag.setImageResource(flags[position]);
    return;
}
```

## 8.6. Màn hình không cuộn trên thiết bị nhỏ

Các layout hiện dùng `LinearLayout` trực tiếp. Nếu nội dung vượt chiều cao màn hình, có thể bọc phần nội dung bằng `ScrollView`. `ScrollView` chỉ nên có một View con trực tiếp.

---

# 9. CẢI TIẾN NÊN ÁP DỤNG

## 9.1. Chuyển chuỗi sang `strings.xml`

Thay vì viết trực tiếp:

```xml
android:text="Gửi khảo sát"
```

Khai báo trong `res/values/strings.xml`:

```xml
<resources>
    <string name="submit_survey">Gửi khảo sát</string>
</resources>
```

Sau đó dùng:

```xml
android:text="@string/submit_survey"
```

Điều này giúp dễ dịch đa ngôn ngữ và giảm cảnh báo hardcoded text.

## 9.2. Dùng View Binding

`findViewById` hoạt động được nhưng có thể gõ sai ID hoặc ép kiểu sai. View Binding tạo lớp binding an toàn hơn theo từng layout.

## 9.3. Khóa nút khi đang tải

Trong câu 3, người dùng có thể nhấn nhiều lần và tạo nhiều tác vụ chờ. Có thể tạm khóa nút:

```java
btnDownload.setEnabled(false);
```

Sau khi tải xong:

```java
btnDownload.setEnabled(true);
```

---

# 10. CHECKLIST HOÀN THÀNH

- [ ] Package trong ba file Java khớp package của dự án.
- [ ] Có `activity_main.xml`, `activity_main2.xml`, `activity_main3.xml` trong `res/layout`.
- [ ] Có `edittext_bg.xml` trong `res/drawable`.
- [ ] Có đủ bốn ảnh cờ, đúng tên và đúng thứ tự mảng.
- [ ] Có `sample_image` trong `res/drawable`.
- [ ] Ba Activity được khai báo trong Manifest.
- [ ] ID trong XML khớp hoàn toàn với `R.id...` trong Java.
- [ ] Build không còn lỗi.
- [ ] Câu 1 kiểm tra được trường hợp tên rỗng và nhiều sở thích.
- [ ] Câu 2 đổi đúng cờ theo quốc gia.
- [ ] Câu 3 hiện ProgressBar rồi hiện ảnh sau khoảng 3 giây.

---

# 11. TỔNG KẾT

Bài thực hành 04 minh họa ba nhóm kỹ năng Android cơ bản:

1. **Thu thập dữ liệu biểu mẫu:** dùng `EditText`, `RadioGroup`, `RadioButton` và `CheckBox`.
2. **Hiển thị danh sách lựa chọn:** dùng `Spinner`, `ArrayAdapter`, mảng dữ liệu và `AlertDialog`.
3. **Cập nhật giao diện theo thời gian:** dùng `ProgressBar`, `Handler`, `postDelayed` và thay đổi `visibility`.

Cần phân biệt rõ rằng XML chịu trách nhiệm về giao diện, còn Java chịu trách nhiệm ánh xạ View, bắt sự kiện, kiểm tra dữ liệu và thay đổi trạng thái màn hình. Riêng câu 3 chỉ mô phỏng quá trình tải bằng thời gian chờ, chưa thực hiện tải dữ liệu qua mạng.
