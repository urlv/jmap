package mapper;

import java.util.ArrayList;
import java.util.List;

public class HelperMapping {
    public long phoneMapping(String phone) {
        return Long.valueOf(phone);
    }

    public List<String> foodMapping(String food) {
       String[] arr = food.split(",");

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("hamburger");
        arrayList.add("pizza");
        arrayList.add(arr[0]);
        arrayList.add(arr[1]);

        return arrayList;
    }
}
