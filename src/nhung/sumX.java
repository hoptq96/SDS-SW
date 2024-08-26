package nhung;

import java.util.ArrayList;

public class sumX {
    public static void main(String[] args) {

        ArrayList[] arrList = new ArrayList[5];

//        arrList[0] = new ArrayList(); // Khởi tạo 1 araylist, nếu không có khởi tạo này thì ArrayList sẽ bị null
//                                    // và sẽ bị lỗi khi e dùng các hàm như add, get
//        arrList[1] = new ArrayList();
//        arrList[2] = new ArrayList();
//        arrList[3] = new ArrayList();
//        arrList[4] = new ArrayList();
//        khởi tạo ở trên để e dễ hình dung nhé, còn trong bài làm thì a sẽ viết theo cách xin hơn là trong vòng for

        for (int i = 0; i < 5; i++) {
            arrList[i] = new ArrayList(); // Khởi tạo 1 araylist
            for (int j = 1; j <= 5; j++) {
                arrList[i].add(j); // thêm giá trị vào arraylist
            }
        }
//        Sau khi hết vòng for thì ta được mảng 2 chiều với kiểu dữ liệu là mảng arraylist rồi nhé
//        {1,2,3,4,5},
//        {1,2,3,4,5},
//        {1,2,3,4,5},
//        {1,2,3,4,5},
//        {1,2,3,4,5}

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += (int) arrList[i].get(i);
            sum += (int) arrList[arrList.length - i - 1].get(i);
        }
        System.out.println(sum);
    }
}
