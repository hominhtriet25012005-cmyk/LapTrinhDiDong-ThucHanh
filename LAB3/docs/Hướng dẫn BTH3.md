# BÀI THỰC HÀNH 03 – FULL CODE VÀ GIẢI THÍCH CHI TIẾT

> Tài liệu này được biên soạn lại từ file gốc theo hướng **giảng dạy từng bước**. Mỗi đoạn mã đều có chú thích giải thích mục đích, cách hoạt động và kết quả hiển thị dự kiến.

---

## 1. Kiến thức nền cần nhớ

### 1.1. XML trong Android là gì?

XML là ngôn ngữ đánh dấu được Android sử dụng để mô tả giao diện. Mỗi thẻ XML thường tương ứng với một `View` hoặc một `ViewGroup`.

- `View`: phần tử giao diện đơn lẻ, ví dụ `TextView`, `Button`, `ImageView`, `EditText`.
- `ViewGroup`: phần tử chứa và sắp xếp các View khác, ví dụ `LinearLayout`, `ConstraintLayout`, `TableLayout`, `FrameLayout`, `GridLayout`.
- Thuộc tính bắt đầu bằng `android:` thuộc namespace chuẩn của Android.
- Thuộc tính bắt đầu bằng `app:` thường thuộc thư viện AndroidX hoặc Material Components.
- Thuộc tính bắt đầu bằng `tools:` chỉ hỗ trợ thiết kế/xem trước trong Android Studio, không ảnh hưởng trực tiếp khi ứng dụng chạy.

### 1.2. Đơn vị `dp` và `sp`

- `dp`, density-independent pixel: dùng cho kích thước, khoảng cách, padding và margin. Android tự quy đổi theo mật độ điểm ảnh của thiết bị.
- `sp`, scale-independent pixel: dùng cho kích thước chữ. Ngoài mật độ màn hình, `sp` còn tôn trọng cỡ chữ mà người dùng đặt trong hệ thống.

### 1.3. `match_parent`, `wrap_content` và `0dp`

- `match_parent`: chiếm toàn bộ kích thước khả dụng của View cha.
- `wrap_content`: chỉ chiếm đủ không gian để hiển thị nội dung.
- `0dp` trong `ConstraintLayout`: kích thước được xác định bởi các constraint. Nếu neo cả hai phía trái và phải, `0dp` giúp View giãn từ constraint trái đến constraint phải.
- `0dp` kết hợp `layout_weight` trong `LinearLayout`: phần kích thước còn lại được chia theo trọng số.

### 1.4. Padding và margin

- `padding`: khoảng cách từ biên của View vào nội dung bên trong.
- `margin`: khoảng cách từ View đến các View hoặc thành phần xung quanh.

### 1.5. ID và tài nguyên

- `@+id/...`: tạo một ID mới.
- `@id/...`: tham chiếu ID đã tồn tại.
- `@drawable/...`: tham chiếu ảnh hoặc drawable XML trong `res/drawable`.
- `@style/...`: áp dụng style trong `res/values`.
- `@android:color/...`: sử dụng tài nguyên màu có sẵn của Android.

---

# CÂU 1: MÀN HÌNH ĐĂNG KÝ CƠ BẢN BẰNG LINEARLAYOUT

## 2. File nền tròn cho Avatar

**Đường dẫn:** `app/src/main/res/drawable/circle_background.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<!--
    Dòng khai báo XML:
    - version="1.0": sử dụng chuẩn XML 1.0.
    - encoding="utf-8": file có thể lưu tiếng Việt và các ký tự Unicode.
    - Dòng này không tạo ra thành phần hiển thị.
-->

<!--
    shape là drawable được tạo bằng XML thay vì dùng ảnh PNG/JPG.
    xmlns:android khai báo namespace để sử dụng các thuộc tính android:...
    android:shape="oval" yêu cầu Android vẽ hình oval.
    Nếu chiều rộng và chiều cao của View bằng nhau, oval sẽ trở thành hình tròn.
-->
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">

    <!--
        solid tô toàn bộ phần bên trong shape bằng một màu duy nhất.
        #D3D3D3 là màu xám nhạt.
        Output: một nền oval/tròn màu xám nhạt.
    -->
    <solid android:color="#D3D3D3" />

<!-- Kết thúc drawable shape. -->
</shape>
```

### Output dự kiến

File này không tự tạo thành một màn hình. Khi được gán làm background cho một View vuông, nó tạo nền tròn màu xám nhạt phía sau View đó.

---

## 3. File giao diện đăng ký

**Đường dẫn:** `app/src/main/res/layout/activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- Khai báo tài liệu XML sử dụng XML 1.0 và bảng mã UTF-8. -->

<!--
    LinearLayout là ViewGroup sắp xếp các View con tuần tự theo một hướng.
    Trong giao diện này, các View con được xếp từ trên xuống dưới.
-->
<LinearLayout
    <!-- Namespace chứa các thuộc tính Android chuẩn như layout_width, text, padding. -->
    xmlns:android="http://schemas.android.com/apk/res/android"

    <!-- Namespace cho thuộc tính của thư viện như cornerRadius, constraint. -->
    xmlns:app="http://schemas.android.com/apk/res-auto"

    <!-- Namespace dùng cho công cụ thiết kế của Android Studio. -->
    xmlns:tools="http://schemas.android.com/tools"

    <!-- Tạo ID main cho layout gốc để Kotlin/Java có thể tìm và thao tác. -->
    android:id="@+id/main"

    <!--
        orientation="vertical" xếp các View con theo chiều dọc.
        Thứ tự hiển thị chính là thứ tự các thẻ: avatar, tiêu đề, họ tên, email,
        mật khẩu và nút đăng ký.
    -->
    android:orientation="vertical"

    <!-- Đặt màu nền toàn màn hình thành xám rất nhạt. -->
    android:background="#F5F5F5"

    <!--
        gravity điều khiển cách các View con nằm trong LinearLayout.
        center_horizontal căn giữa các View con theo chiều ngang.
        Nó không căn giữa theo chiều dọc.
    -->
    android:gravity="center_horizontal"

    <!-- Layout gốc rộng bằng toàn bộ View cha, thường là toàn màn hình. -->
    android:layout_width="match_parent"

    <!-- Layout gốc cao bằng toàn bộ View cha. -->
    android:layout_height="match_parent"

    <!-- Tạo khoảng đệm 24dp ở cả bốn cạnh bên trong layout. -->
    android:padding="24dp"

    <!-- Cho Android Studio biết layout này gắn với MainActivity khi xem Preview. -->
    tools:context=".MainActivity">

    <!-- ImageView dùng để hiển thị ảnh đại diện. -->
    <ImageView
        <!-- Tạo ID avatarImage. -->
        android:id="@+id/avatarImage"

        <!-- Đặt chiều rộng ảnh là 100dp. -->
        android:layout_width="100dp"

        <!-- Đặt chiều cao ảnh là 100dp; width bằng height giúp nền oval thành tròn. -->
        android:layout_height="100dp"

        <!-- Cách mép trên của vị trí hiện tại 32dp. -->
        android:layout_marginTop="32dp"

        <!-- Lấy ảnh ic_user_avatar trong thư mục res/drawable. -->
        android:src="@drawable/ic_user_avatar"

        <!--
            centerCrop phóng ảnh sao cho lấp đầy toàn bộ vùng 100x100dp.
            Ảnh giữ tỉ lệ, phần thừa có thể bị cắt ở các cạnh.
        -->
        android:scaleType="centerCrop"

        <!-- Dùng drawable hình tròn màu xám làm nền phía sau ảnh. -->
        android:background="@drawable/circle_background" />

    <!-- TextView hiển thị lời chào. -->
    <TextView
        <!-- Tạo ID welcomeText. -->
        android:id="@+id/welcomeText"

        <!-- Chiều rộng vừa đủ chứa chữ Welcome!. -->
        android:layout_width="wrap_content"

        <!-- Chiều cao vừa đủ chứa nội dung chữ. -->
        android:layout_height="wrap_content"

        <!-- Nội dung được hiển thị. -->
        android:text="Welcome!"

        <!-- Cỡ chữ 24sp, có hỗ trợ cài đặt cỡ chữ của người dùng. -->
        android:textSize="24sp"

        <!-- Làm chữ đậm. -->
        android:textStyle="bold"

        <!-- Tạo khoảng cách 16dp với avatar phía trên. -->
        android:layout_marginTop="16dp" />

    <!--
        TextInputLayout là lớp bao ngoài cung cấp giao diện Material cho ô nhập,
        chẳng hạn nhãn nổi, thông báo lỗi và icon hỗ trợ.
    -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/fullNameLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"

        <!-- Nhãn Full Name. Khi người dùng nhập, nhãn có thể nổi lên phía trên. -->
        android:hint="Full Name"

        <!-- Cách tiêu đề Welcome 24dp. -->
        android:layout_marginTop="24dp">

        <!-- TextInputEditText là ô nhập liệu được tối ưu để dùng trong TextInputLayout. -->
        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/fullNameEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    <!-- Kết thúc vùng nhập họ tên. -->
    </com.google.android.material.textfield.TextInputLayout>

    <!-- Vùng nhập email. -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/emailLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email"
        android:layout_marginTop="12dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/emailEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"

            <!--
                textEmailAddress báo đây là dữ liệu email.
                Output thường thấy: bàn phím hiển thị phím @ và gợi ý phù hợp với email.
                Thuộc tính này không tự kiểm tra email hợp lệ hoàn toàn.
            -->
            android:inputType="textEmailAddress" />
    </com.google.android.material.textfield.TextInputLayout>

    <!-- Vùng nhập mật khẩu. -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/passwordLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Password"

        <!-- Bật biểu tượng con mắt cho phép ẩn hoặc hiện mật khẩu. -->
        app:passwordToggleEnabled="true"

        android:layout_marginTop="12dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/passwordEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"

            <!-- Ẩn ký tự mật khẩu và mở bàn phím phù hợp. -->
            android:inputType="textPassword" />
    </com.google.android.material.textfield.TextInputLayout>

    <!-- Nút gửi biểu mẫu đăng ký. XML chỉ tạo giao diện, chưa xử lý sự kiện nhấn. -->
    <Button
        android:id="@+id/signupButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Sign Up"
        android:layout_marginTop="24dp"

        <!-- Màu nền xanh hoàng gia. -->
        android:backgroundTint="#4169E1"

        <!-- Màu chữ trắng. -->
        android:textColor="#FFFFFF"

        <!-- Bo góc nút 16dp. Mức độ hỗ trợ phụ thuộc theme/component đang dùng. -->
        app:cornerRadius="16dp" />

<!-- Kết thúc layout gốc. -->
</LinearLayout>
```

### Output dự kiến của Câu 1

Màn hình nền xám nhạt, các thành phần được xếp dọc và căn giữa theo chiều ngang. Phía trên là avatar tròn, tiếp theo là chữ **Welcome!**, ba ô nhập họ tên, email, mật khẩu và nút **Sign Up** màu xanh.

### Lưu ý vận hành

- XML mới chỉ tạo giao diện, chưa có logic đăng ký.
- Muốn nút hoạt động, cần viết Kotlin/Java để đọc dữ liệu và xử lý `setOnClickListener`.
- Nếu `ic_user_avatar` chưa tồn tại, Android Studio báo lỗi không tìm thấy resource.
- `TextInputLayout` yêu cầu dự án có Material Components dependency và theme phù hợp.

---

# CÂU 2: LAYOUT PHÂN VÙNG BẰNG CONSTRAINTLAYOUT

## 4. File `activity_main2.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- Khai báo file XML. -->

<!--
    ConstraintLayout là layout gốc.
    Mỗi View con được xác định vị trí bằng các mối ràng buộc gọi là constraint.
-->
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity2">

    <!-- Header nằm ở trên cùng màn hình. -->
    <LinearLayout
        android:id="@+id/headerLayout"

        <!--
            0dp trong ConstraintLayout được gọi là match constraints.
            Vì header được neo vào cạnh trái và phải của parent nên nó giãn toàn chiều ngang.
        -->
        android:layout_width="0dp"
        android:layout_height="wrap_content"

        <!-- horizontal xếp icon và tiêu đề cạnh nhau từ trái sang phải. -->
        android:orientation="horizontal"
        android:padding="16dp"
        android:background="#FFE0B2"

        <!-- Neo cạnh trên của header vào cạnh trên của parent. -->
        app:layout_constraintTop_toTopOf="parent"

        <!-- Neo cạnh bắt đầu của header vào cạnh bắt đầu của parent. -->
        app:layout_constraintStart_toStartOf="parent"

        <!-- Neo cạnh kết thúc của header vào cạnh kết thúc của parent. -->
        app:layout_constraintEnd_toEndOf="parent">

        <ImageView
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/ic_user_avatar"

            <!-- Mô tả ảnh cho công cụ hỗ trợ tiếp cận như TalkBack. -->
            android:contentDescription="User Icon"

            <!-- Căn ImageView giữa theo chiều dọc trong LinearLayout ngang. -->
            android:layout_gravity="center_vertical" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Đăng ký tài khoản"
            android:textStyle="bold"
            android:textSize="18sp"

            <!-- Tạo khoảng cách 12dp giữa chữ và icon nằm trước nó. -->
            android:layout_marginStart="12dp"

            <!-- Căn chữ giữa theo chiều dọc của header. -->
            android:layout_gravity="center_vertical" />
    </LinearLayout>

    <!-- TableLayout tổ chức biểu mẫu theo các hàng. -->
    <TableLayout
        android:id="@+id/formLayout"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"

        <!-- Neo cạnh trên của form vào cạnh dưới header. -->
        app:layout_constraintTop_toBottomOf="@id/headerLayout"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <!-- Mỗi TableRow tương ứng với một hàng của bảng. -->
        <TableRow android:padding="8dp">
            <!-- Nhãn của ô họ tên. -->
            <TextView
                android:text="Họ tên:"
                android:layout_marginEnd="16dp" />

            <!--
                layout_weight="1" yêu cầu EditText nhận phần chiều rộng còn lại của hàng.
                hint là chữ gợi ý, biến mất hoặc di chuyển khi người dùng nhập tùy component.
            -->
            <EditText
                android:hint="Nhập họ tên"
                android:layout_weight="1" />
        </TableRow>

        <TableRow android:padding="8dp">
            <TextView
                android:text="Email:"
                android:layout_marginEnd="16dp" />
            <EditText
                android:hint="Nhập email"
                android:inputType="textEmailAddress"
                android:layout_weight="1" />
        </TableRow>

        <TableRow android:padding="8dp">
            <TextView
                android:text="Mật khẩu:"
                android:layout_marginEnd="16dp" />
            <EditText
                android:hint="Nhập mật khẩu"
                android:inputType="textPassword"
                android:layout_weight="1" />
        </TableRow>
    </TableLayout>

    <!-- FrameLayout tạo một vùng chứa nút. Các View con có thể chồng lên nhau. -->
    <FrameLayout
        android:id="@+id/buttonLayout"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        app:layout_constraintTop_toBottomOf="@id/formLayout"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <Button
            android:id="@+id/registerButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Tạo tài khoản"
            android:textColor="#FFFFFF"
            android:backgroundTint="#1A237E"

            <!-- Căn nút vào giữa FrameLayout. -->
            android:layout_gravity="center" />
    </FrameLayout>

    <!-- Nhóm văn bản chuyển sang màn hình đăng nhập. -->
    <LinearLayout
        android:id="@+id/switchLayout"
        android:layout_width="0dp"
        android:layout_height="wrap_content"

        <!-- Hai TextView con được xếp theo chiều dọc. -->
        android:orientation="vertical"

        <!-- Căn toàn bộ View con vào giữa theo các hướng phù hợp. -->
        android:gravity="center"
        android:layout_marginTop="24dp"
        app:layout_constraintTop_toBottomOf="@id/buttonLayout"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Đã có tài khoản?" />

        <TextView
            android:id="@+id/loginLink"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Đăng nhập"
            android:textStyle="bold"
            android:textColor="@android:color/holo_blue_dark"

            <!-- Tạo khoảng đệm 4dp phía trên bên trong TextView. -->
            android:paddingTop="4dp" />
    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Output dự kiến của Câu 2

Giao diện được chia thành bốn vùng liên tiếp: header màu cam nhạt, form dạng bảng, nút **Tạo tài khoản** ở giữa và liên kết **Đăng nhập** phía dưới.

### Ý nghĩa của các loại căn chỉnh dễ nhầm

- `orientation`: hướng xếp các View con trong `LinearLayout`.
- `gravity`: căn các View con hoặc nội dung bên trong View hiện tại.
- `layout_gravity`: xác định chính View đó nằm ở đâu trong View cha.
- `layout_constraint...`: tạo mối neo giữa các cạnh trong `ConstraintLayout`.
- `Start/End` nên được ưu tiên hơn `Left/Right` vì hỗ trợ cả ngôn ngữ viết từ trái sang phải và phải sang trái.

---

# CÂU 3: MÁY TÍNH CẦM TAY BẰNG GRIDLAYOUT

## 5. Khai báo style cho nút

**Đường dẫn:** `app/src/main/res/values/styles.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- File XML chứa các tài nguyên style. -->

<resources>
    <!-- Style gốc dùng chung cho các nút máy tính. -->
    <style name="CalcButton">
        <!-- Chiều rộng 0dp để GridLayout phân bổ theo columnWeight. -->
        <item name="android:layout_width">0dp</item>

        <!-- Chiều cao vừa đủ nội dung, sau đó rowWeight có thể tham gia phân bổ không gian. -->
        <item name="android:layout_height">wrap_content</item>

        <!-- Khoảng cách bên ngoài mỗi nút là 4dp. -->
        <item name="android:layout_margin">4dp</item>

        <!-- Trọng số cột bằng 1: các nút thường được chia đều chiều rộng trong hàng. -->
        <item name="android:layout_columnWeight">1</item>

        <!-- Trọng số hàng bằng 1: các nút có thể chia không gian hàng theo tỉ lệ bằng nhau. -->
        <item name="android:layout_rowWeight">1</item>

        <!-- Cỡ chữ trên nút là 20sp. -->
        <item name="android:textSize">20sp</item>

        <!-- Màu nền mặc định của nút là xám nhạt. -->
        <item name="android:backgroundTint">#EEEEEE</item>

        <!-- Màu chữ gần đen. -->
        <item name="android:textColor">#0F0F0F</item>
    </style>

    <!--
        CalcButton.Red kế thừa toàn bộ thuộc tính từ CalcButton,
        sau đó ghi đè màu nền và màu chữ.
    -->
    <style name="CalcButton.Red" parent="CalcButton">
        <item name="android:backgroundTint">#E57373</item>
        <item name="android:textColor">#FFFFFF</item>
    </style>

    <!-- Style màu xanh, dùng cho nút bằng. -->
    <style name="CalcButton.Blue" parent="CalcButton">
        <item name="android:backgroundTint">#90CAF9</item>
        <item name="android:textColor">#FFFFFF</item>
    </style>

    <!-- Style màu vàng, dùng cho nút xóa một ký tự. -->
    <style name="CalcButton.Yellow" parent="CalcButton">
        <item name="android:backgroundTint">#F9EF90</item>
    </style>

    <!-- Style màu xanh lá, dùng cho các phép toán. -->
    <style name="CalcButton.Green" parent="CalcButton">
        <item name="android:backgroundTint">#C6F990</item>
    </style>
</resources>
```

### Vì sao nên dùng style?

Style giúp tránh lặp lại cùng thuộc tính trên nhiều nút. Khi muốn đổi cỡ chữ hoặc margin của toàn bộ bàn phím máy tính, chỉ cần sửa `CalcButton` một lần.

---

## 6. File giao diện máy tính

**Đường dẫn:** `app/src/main/res/layout/activity_main3.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>

<!-- Layout gốc xếp vùng màn hình và bàn phím theo chiều dọc. -->
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp"
    android:background="#FFFFFF">

    <!-- Vùng hiển thị biểu thức và kết quả. -->
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:background="#F5F5F5"
        android:padding="12dp">

        <!-- TextView hiển thị phép tính hiện tại. -->
        <TextView
            android:id="@+id/inputText"

            <!-- 0dp để chiều rộng được xác định bởi constraint Start và End. -->
            android:layout_width="0dp"
            android:layout_height="wrap_content"

            <!-- Dữ liệu mẫu khi mở màn hình; chưa phải kết quả tính động. -->
            android:text="9+7"
            android:textSize="30sp"

            <!-- Neo vào phía trên của vùng hiển thị. -->
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"

            <!-- Căn nội dung chữ về phía đầu dòng, thường là bên trái. -->
            android:gravity="start" />

        <!-- TextView hiển thị kết quả. -->
        <TextView
            android:id="@+id/resultText"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="16"
            android:textSize="40sp"
            android:textStyle="bold"

            <!-- Neo kết quả vào góc dưới và cuối của parent, thường là góc dưới phải. -->
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent" />
    </androidx.constraintlayout.widget.ConstraintLayout>

    <!-- GridLayout xếp các nút theo lưới. -->
    <GridLayout
        android:id="@+id/gridLayout"
        android:layout_width="match_parent"

        <!--
            Chiều cao 0dp kết hợp layout_weight="1" trong LinearLayout dọc.
            GridLayout sẽ lấy toàn bộ chiều cao còn lại sau vùng hiển thị và margin.
        -->
        android:layout_height="0dp"
        android:layout_weight="1"

        <!-- Căn các ô dựa theo margin của chúng. -->
        android:alignmentMode="alignMargins"

        <!-- Lưới có 4 cột. -->
        android:columnCount="4"

        <!-- Lưới có 5 hàng. -->
        android:rowCount="5"

        <!-- Cách vùng hiển thị phía trên 16dp. -->
        android:layout_marginTop="16dp"

        <!-- Cho phép GridLayout linh hoạt sắp xếp hàng khi có phần tử span nhiều hàng. -->
        android:rowOrderPreserved="false">

        <!-- Hàng 1: CE, Del, nhân, chia. -->
        <Button android:text="CE" style="@style/CalcButton.Red" />
        <Button android:text="Del" style="@style/CalcButton.Yellow" />
        <Button android:text="×" style="@style/CalcButton.Green" />
        <Button android:text="÷" style="@style/CalcButton.Green" />

        <!-- Hàng 2: 1, 2, 3, cộng. -->
        <Button android:text="1" style="@style/CalcButton" />
        <Button android:text="2" style="@style/CalcButton" />
        <Button android:text="3" style="@style/CalcButton" />
        <Button android:text="+" style="@style/CalcButton.Green" />

        <!-- Hàng 3: 4, 5, 6, trừ. -->
        <Button android:text="4" style="@style/CalcButton" />
        <Button android:text="5" style="@style/CalcButton" />
        <Button android:text="6" style="@style/CalcButton" />
        <Button android:text="-" style="@style/CalcButton.Green" />

        <!-- Hàng 4: 7, 8, 9 và nút bằng bắt đầu chiếm hai hàng. -->
        <Button android:text="7" style="@style/CalcButton" />
        <Button android:text="8" style="@style/CalcButton" />
        <Button android:text="9" style="@style/CalcButton" />

        <!--
            layout_rowSpan="2" làm nút = chiếm chiều cao của hai hàng.
            Nó nằm ở cột cuối và kéo dài từ hàng 4 xuống hàng 5.
        -->
        <Button
            android:text="="
            style="@style/CalcButton.Blue"
            android:layout_rowSpan="2" />

        <!--
            layout_columnSpan="2" làm nút 0 chiếm hai cột liên tiếp ở hàng cuối.
        -->
        <Button
            android:text="0"
            style="@style/CalcButton"
            android:layout_columnSpan="2" />

        <!-- Nút dấu chấm chiếm một ô còn lại trước cột của nút bằng. -->
        <Button android:text="." style="@style/CalcButton" />
    </GridLayout>
</LinearLayout>
```

### Output dự kiến của Câu 3

Phía trên là vùng hiển thị màu xám nhạt cao 200dp. Biểu thức `9+7` nằm ở góc trên, kết quả `16` nằm ở góc dưới bên phải. Phía dưới là bàn phím 4 cột, 5 hàng. Nút `=` cao bằng hai hàng và nút `0` rộng bằng hai cột.

### Quan trọng: giao diện này chưa phải máy tính hoàn chỉnh

Các nút hiện chỉ hiển thị trên màn hình. Để tính toán thật, cần viết code Kotlin/Java để:

1. Bắt sự kiện nhấn từng nút.
2. Ghép các chữ số và toán tử thành biểu thức.
3. Xử lý `CE`, `Del`, dấu chấm và các phép toán.
4. Kiểm tra chia cho 0 và biểu thức sai.
5. Hiển thị kết quả vào `resultText`.

---

# 7. TỔNG HỢP CƠ SỞ LÝ THUYẾT

## 7.1. So sánh các layout

### LinearLayout

- Sắp xếp các View theo đúng một trục.
- `vertical`: từ trên xuống dưới.
- `horizontal`: từ trái sang phải hoặc theo hướng ngôn ngữ.
- Phù hợp cho form đơn giản, thanh công cụ, nhóm nút.

### ConstraintLayout

- Xác định vị trí View bằng các constraint.
- Giúp giảm số lượng layout lồng nhau.
- `0dp` thường mang nghĩa match constraints.
- Mỗi View nên có đủ constraint ngang và dọc để tránh vị trí không ổn định.

### TableLayout và TableRow

- Dùng cho giao diện dạng hàng/cột.
- Mỗi `TableRow` là một hàng.
- Các View con trong hàng tạo thành các cột.
- Hợp với biểu mẫu nhỏ, nhưng không nên dùng như bảng dữ liệu lớn có cuộn.

### FrameLayout

- Thường dùng để chứa một View hoặc chồng nhiều View lên nhau.
- `layout_gravity` điều khiển vị trí View con trong FrameLayout.

### GridLayout

- Sắp xếp phần tử theo lưới hàng và cột.
- `columnCount`, `rowCount`: số cột và hàng.
- `layout_columnSpan`: số cột một View chiếm.
- `layout_rowSpan`: số hàng một View chiếm.
- Phù hợp cho bàn phím máy tính, bảng nút, dashboard nhỏ.

## 7.2. Phân biệt `orientation`, `gravity`, `layout_gravity`

### `android:orientation`

Chỉ dùng với các ViewGroup hỗ trợ hướng sắp xếp, tiêu biểu là `LinearLayout`.

```xml
android:orientation="vertical"
```

Output: các View con xếp thành một cột.

```xml
android:orientation="horizontal"
```

Output: các View con xếp thành một hàng.

### `android:gravity`

Căn nội dung bên trong View hoặc căn nhóm View con bên trong ViewGroup.

```xml
android:gravity="center_horizontal"
```

Output: nội dung hoặc View con được căn giữa theo chiều ngang.

### `android:layout_gravity`

Căn chính View hiện tại so với View cha.

```xml
android:layout_gravity="center"
```

Output: View được đặt ở giữa vùng chứa nếu View cha hỗ trợ thuộc tính này.

## 7.3. `inputType`

```xml
android:inputType="textEmailAddress"
```

Gợi ý hệ thống sử dụng bàn phím nhập email. Không thay thế bước kiểm tra định dạng email trong code.

```xml
android:inputType="textPassword"
```

Ẩn ký tự đã nhập và tối ưu bàn phím cho mật khẩu. Không tự mã hóa hay lưu mật khẩu an toàn.

## 7.4. Style và kế thừa

```xml
<style name="CalcButton.Blue" parent="CalcButton">
```

`CalcButton.Blue` nhận thuộc tính từ `CalcButton`, rồi ghi đè các thuộc tính khai báo bên trong. Cơ chế này giúp tái sử dụng và đồng bộ thiết kế.

---

# 8. CÁC LỆNH VÀ THAO TÁC THƯỜNG DÙNG

## 8.1. Chạy ứng dụng trong Android Studio

1. Mở dự án.
2. Chờ Gradle Sync hoàn tất.
3. Chọn emulator hoặc điện thoại thật.
4. Nhấn nút **Run** hoặc dùng phím tắt tương ứng của hệ điều hành.

## 8.2. Build bằng Terminal

Trên Windows, tại thư mục gốc dự án:

```bash
gradlew.bat assembleDebug
```

Trên macOS/Linux:

```bash
./gradlew assembleDebug
```

Output dự kiến: Gradle biên dịch project và tạo APK debug trong thư mục gần dạng:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## 8.3. Kiểm tra lỗi lint

Windows:

```bash
gradlew.bat lint
```

macOS/Linux:

```bash
./gradlew lint
```

Lint giúp phát hiện vấn đề như chuỗi viết trực tiếp trong layout, thiếu content description, khả năng tiếp cận kém hoặc thuộc tính không phù hợp.

## 8.4. Làm sạch và build lại

Windows:

```bash
gradlew.bat clean assembleDebug
```

macOS/Linux:

```bash
./gradlew clean assembleDebug
```

`clean` xóa sản phẩm build cũ. `assembleDebug` biên dịch lại APK debug từ đầu.

---

# 9. LỖI THƯỜNG GẶP VÀ CÁCH KIỂM TRA

## 9.1. Không tìm thấy `ic_user_avatar`

Nguyên nhân: chưa có file drawable đúng tên.

Cách xử lý: thêm ảnh/vector có tên `ic_user_avatar` vào `res/drawable` hoặc sửa `android:src` thành tài nguyên đang tồn tại.

## 9.2. Không nhận `TextInputLayout`

Kiểm tra dependency Material Components trong module `app` và đồng bộ Gradle. Đồng thời bảo đảm ứng dụng đang dùng theme Material phù hợp.

## 9.3. ConstraintLayout báo lỗi thiếu constraint

Mỗi View trực tiếp nằm trong `ConstraintLayout` nên có constraint xác định vị trí ngang và dọc. Nếu thiếu, Preview có thể hiển thị một vị trí nhưng khi chạy View có thể quay về vị trí khác.

## 9.4. Nút bấm hiển thị nhưng không hoạt động

XML chỉ mô tả UI. Cần gắn sự kiện trong Activity/Fragment bằng View Binding, Data Binding hoặc `findViewById`.

## 9.5. Hardcoded text

Các chuỗi như `Welcome!`, `Sign Up` hoặc `Đăng nhập` đang được viết trực tiếp trong XML. Trong dự án thực tế nên chuyển chúng vào `res/values/strings.xml` để dễ dịch và bảo trì.

Ví dụ:

```xml
<!-- res/values/strings.xml -->
<resources>
    <string name="welcome">Welcome!</string>
    <string name="sign_up">Sign Up</string>
</resources>
```

Sau đó tham chiếu trong layout:

```xml
android:text="@string/welcome"
```

---