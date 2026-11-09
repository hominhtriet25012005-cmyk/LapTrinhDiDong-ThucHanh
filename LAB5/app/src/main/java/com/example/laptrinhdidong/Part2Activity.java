package com.example.laptrinhdidong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Part2Activity extends AppCompatActivity {
    // Khai báo mảng chứa ID (mã định danh) của 6 hình ảnh đã lưu trong thư mục res/drawable
    private final int[] imageIds = {
            R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
            R.drawable.img_4, R.drawable.img_5, R.drawable.img_6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Gọi hàm của lớp cha để thiết lập hệ thống cơ bản
        setContentView(R.layout.activity_part2); // Liên kết và hiển thị giao diện chính chứa GridView

        GridView gridView = findViewById(R.id.gridView); // Tìm GridView trên giao diện thông qua ID

        // Lắp bộ chuyển đổi (Adapter) do chúng ta tự định nghĩa (ImageAdapter) vào lưới GridView
        gridView.setAdapter(new ImageAdapter());

        // Cài đặt sự kiện: Lắng nghe và chạy code bên trong khi người dùng bấm vào một ô ảnh bất kỳ
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            int thuTu = position + 1; // position đếm từ 0, nên cộng 1 để ra số thứ tự thực tế (1, 2, 3...)
            Toast.makeText(Part2Activity.this, "Bạn đã chọn ảnh thứ " + thuTu, Toast.LENGTH_SHORT).show();
        });
    }

    // Giữ lại ImageView trong từng ô để không phải tìm lại khi GridView tái sử dụng ô.
    private static class ImageViewHolder {
        final ImageView imageView;

        ImageViewHolder(View itemView) {
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    // Tự tạo một lớp Adapter kế thừa từ BaseAdapter.
    // Lý do: ArrayAdapter thường chỉ dùng cho Text, để xử lý hình ảnh phức tạp ta dùng BaseAdapter để tự "vẽ" lại từng ô.
    private class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imageIds.length; // Báo cho GridView biết có tổng cộng bao nhiêu ô cần hiển thị
        }

        @Override
        public Object getItem(int position) {
            return imageIds[position]; // Lấy ra dữ liệu của một phần tử tại vị trí cụ thể
        }

        @Override
        public long getItemId(int position) {
            return position; // Trả về ID của phần tử (ở đây ta dùng luôn vị trí position làm ID)
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Hàm getView là nơi quan trọng nhất: Nó "vẽ" và nhét dữ liệu vào từng ô lưới.
            ImageViewHolder holder;

            // Nếu convertView bị null (tức là ô này chưa từng được tạo ra trên màn hình)
            if (convertView == null) {
                // LayoutInflater có nhiệm vụ "bơm" (inflate) file giao diện grid_item.xml thành một đối tượng View trong Java
                convertView = LayoutInflater.from(Part2Activity.this).inflate(R.layout.grid_item, parent, false);
                holder = new ImageViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ImageViewHolder) convertView.getTag();
            }

            // Lấy ID ảnh từ mảng (dựa vào position) và nhét ảnh đó vào ImageView
            holder.imageView.setImageResource(imageIds[position]);

            return convertView; // Trả lại cái ô đã có ảnh hoàn chỉnh để GridView hiển thị lên màn hình
        }
    }
}
