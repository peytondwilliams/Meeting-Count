public interface Count_Interface {

    //Will include a data structure to store meeting dates

    boolean read_csv(String file);
    void add(String start, String end, String day_of_week);
    int count(String start, String end, String day_of_week);
}
