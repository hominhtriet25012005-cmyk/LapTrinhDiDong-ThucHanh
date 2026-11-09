package com.example.laptrinhdidong;

import android.content.Intent;
import android.os.Bundle; // Lưu trạng thái dữ liệu của màn hình
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter; // Gắn dữ liệu từ mảng lên giao diện
import android.widget.ListView; // Tạo danh sách cuộn theo chiều dọc
import android.widget.Toast; // Hiển thị thông báo nhỏ tạm thời (popup)
import androidx.appcompat.app.AppCompatActivity; // Lớp nền tảng hỗ trợ tạo màn hình


public class Part1Activity extends AppCompatActivity {
    // AppCompatActivity: Lớp nền tảng của Google giúp ứng dụng dùng được các tính năng giao diện mới (như Theme, Toolbar)
    // nhưng vẫn tương thích và chạy không bị lỗi trên các máy Android đời cũ.
    // Tạo class có tên là Part1Activity (trạng thái public), kế thừa AppCompatActivity để tạo màn hình giao diện
    @Override
    // Khai báo việc ghi đè phương thức của lớp cha (AppCompatActivity)
    protected void onCreate(Bundle savedInstanceState) {
        // protected: Giới hạn quyền truy cập, đảm bảo chỉ hệ thống Android và các lớp kế thừa mới được phép gọi hàm này để tạo màn hình.
        // onCreate: Là hàm đầu tiên được hệ thống tự động gọi ra khi màn hình (Activity) bắt đầu khởi tạo.
        // Mọi việc thiết lập giao diện và dữ liệu ban đầu đều phải viết trong hàm này.

        // Bundle savedInstanceState: Là một "gói" chứa dữ liệu trạng thái cũ của màn hình.
        // Tác dụng chính: Giúp khôi phục lại trạng thái giao diện
        // (ví dụ: giữ lại đoạn chữ bạn đang gõ dở mà chưa kịp lưu) khi cấu hình máy thay đổi,
        // chẳng hạn như khi bạn xoay ngang/dọc điện thoại.
        // Nếu bạn mở ứng dụng lần đầu, biến này sẽ mang giá trị rỗng (null)
        super.onCreate(savedInstanceState);
        //super từ khóa để tham chiếu lại các state, behavior, biến,.. của lớp cha
        // ở đây dùng để gọi lại hàm onCreate của lớp cha nhằm thiết lập nền tảng hệ thống
        // và lưu/khôi phục lại trạng thái giao diện (qua savedInstanceState)
        setContentView(R.layout.activity_part1);
        // Liên kết và nạp file thiết kế giao diện XML (activity_part1.xml) để hiển thị lên màn hình Activity này

        ListView listView = findViewById(R.id.listView);

        // Khai báo dữ liệu 10 tên sinh viên với kiểu dữ liệu là string (chuỗi)
        String[] students = {"Nguyễn An", "Trần Bình", "Lê Cường", "Phạm Dũng", "Hoàng Giang",
                "Võ Khánh", "Đặng Minh", "Bùi Nam", "Đỗ Quang", "Trịnh Sơn"};

        // Gắn dữ liệu bằng ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                // dùng từ khóa new để Khởi tạo một đối tượng Adapter (bộ chuyển đổi)
                // để xử lý và quản lý dữ liệu kiểu chuỗi (String).
                this,
                android.R.layout.simple_list_item_1,
                // Giao diện có sẵn của Android dùng để thiết kế cho từng dòng (chỉ gồm 1 TextView đơn giản).
                students // Nguồn dữ liệu: mảng chứa 10 tên sinh viên sẽ được đổ vào danh sách.
        );
        listView.setAdapter(adapter);
        // adapter đóng vai trò làm cầu nối trung gian: lấy dữ liệu thô (từ mảng String[] hoặc Database), định dạng lại rồi đắp vào từng ô giao diện để hiển thị lên danh sách. Nếu không có Adapter, ListView của bạn sẽ chỉ là một cái khung trắng tinh không có nội dung.
        // Tóm lại: ArrayAdapter làm cầu nối, lấy từng phần tử trong mảng 'students',
        // nhét vào giao diện 'simple_list_item_1' và chuẩn bị đưa lên màn hình.

        // Xử lý sự kiện chọn item
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Cài đặt sự kiện: Lắng nghe và thực thi code mỗi khi người dùng bấm vào một dòng bất kỳ trong danh sách.
            // Lắp bộ chuyển đổi (adapter) vào ListView để chính thức đổ dữ liệu và hiển thị danh sách lên màn hình.
            //parent: Chính là toàn bộ cái danh sách (ListView hoặc GridView) đang chứa các mục.
            //view: Là cái giao diện cụ thể của một dòng (item) mà bạn vừa bấm vào (ví dụ: chính là cái phần tử giao diện chứa chữ "Nguyễn An").
            //position: Vị trí (số thứ tự) của dòng bị bấm, tính theo mảng dữ liệu (luôn bắt đầu đếm từ 0, 1, 2...). Đây là tham số bạn dùng nhiều nhất để biết người dùng vừa bấm vào phần tử nào trong mảng.
            //id: Mã định danh (row ID) của dòng đó. Khi bạn dùng mảng bình thường (như trong Bài 5), giá trị id này thường bằng luôn với position
            String selectedName = students[position];
            // Truy xuất lấy ra tên sinh viên trong mảng 'students' dựa vào vị trí 'position' vừa được bấm.
            Toast.makeText(Part1Activity.this, "Xin chào " + selectedName + "!", Toast.LENGTH_SHORT).show();
            // Tạo một thông báo nhỏ (Toast) ghép chữ "Xin chào "
            // với tên vừa lấy được, hiển thị lên màn hình trong khoảng thời gian ngắn (LENGTH_SHORT) rồi tự tắt.
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_part1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_open_part2) {
            startActivity(new Intent(Part1Activity.this, Part2Activity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
