import java.util.Calendar;
import java.util.HashMap;

public class Simple implements Count_Interface{

    public Simple() {
        count_calender = new HashMap<>();
    }

    private HashMap<String, Integer> count_calender;
    public boolean read_csv(String file_name) {
        return false;
    }

    public void add(String start_date, String end_date, String day_of_week) {

    }

    public int count(String start_date, String end_date, String day_of_week) {
        return -1;
    }

    public static void main(String[] args) {

    }

}
