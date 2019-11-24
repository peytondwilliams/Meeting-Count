import java.util.Calendar;
import java.util.HashMap;

public interface Count_Interface {

    //Will include a data structure to store meeting dates

    int add(Calendar start, Calendar end);
    int count(Calendar start, Calendar end);
}
