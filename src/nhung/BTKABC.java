package nhung;

import java.util.Arrays;

public class BTKABC {

    public static void main(String[] args) {
        btk(0);
    }

//    Độ dài của điều kiện dừng và kết quả phải bằng nhau
    static int N = 3;
    static String[] btkResult = new String[N];

//    Độ dài của isVisited và name phải bằng nhau
    static boolean[] isVisited = new boolean[3]; // vị trí đã chọn (Giả sử có 3 Cái ghế cạnh nhau lần lượt là ghế 0, ghế 1 và ghế 2)
    static String[] name = {"A", "B", "C"}; // Đại diện cho 3 người

    private static void btk(int index) {
        if (index == N) { // điều kiện dừng cần return (3 người đã ngồi vào 3 ghế)
            System.out.println(Arrays.toString(btkResult));
            return;
        }
        for (int j = 0; j < name.length; j++) { // Giá trị cho mảng kết quả (Số ghế)
            if (!isVisited[j]) { // Kiểm tra ghế số j đã có ai ngồi chưa
                isVisited[j] = true; // nếu chưa ai ngồi thì sẽ cho 1 ng ngồi vào nên đánh dấu luôn là true (đã ngồi)
                btkResult[index] = name[j]; // Cho người ngồi vào (3 người xếp thành 1 hàng -> lấy người thứ j trong hàng để cho ngồi)

                btk(index + 1); // đệ quy => gọi lại hàm để tiếp tục cho người chưa ngồi ngồi vào ghế
                isVisited[j] = false; /*bước đệ quy ở trên là liên tục gọi lại hàm nên dòng cuối này sẽ chưa chạy (
                bao giờ thỏa nãn điều kiện dừng (có return) thì mới hết phần đệ quy )
                Khi đó dòng isVisited[j] = false; mới được thực thi.
                dòng này chạy có nghĩa là đã có đủ 3 người ngồi vào 3 ghế. Bây giờ sẽ lần lượt cho từng người đứng lên để đổi chỗ -> tạo thành 1 thứ tự chỗ ngồi mới*/
            }
        }
    }

    /*tổng kết đại khái hàm này sẽ chạy lặp đi lặp lại giống như việc sắp xếp chỗ ngồi cho 3 người
    Lúc thì người A ngồi đầu lúc thì ngồi giữa lúc thì ngồi cuối
    Tương tự với B và C
    Khi đó mỗi lần đổi chỗ sẽ tạo thành 1 thứ tự chỗ ngồi khác với lúc trước.

    Và thứ tự đổi thì có hạn, nên nó sẽ đổi đến khi không đổi được nữa thì sẽ chạy hết hàm
    --> tham khảo "Chỉnh hợp trong toán"*/
}
