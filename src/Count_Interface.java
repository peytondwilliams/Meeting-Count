import java.time.format.DateTimeFormatter;

/**
 * Interface for counting meetings
 *
 * @author Peyton D Williams will1892@purdue.edu
 * @version 11/24/2019
 *
 */

public interface Count_Interface {

    final DateTimeFormatter date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //Will include a data structure to store meeting dates


    /**
     * reads the passed csv file to fill a data structure
     *
     * @param file a filepath to csv file
     *
     * @return if read was successful
     */
    boolean read_csv(String file);

    /**
     * adds the given dates to a data structure
     *
     * @param start startdate in the read format
     * @param end enddate in the read format
     * @param day_of_week day of the week in read format
     */
    void add(String start, String end, String day_of_week);

    /**
     * counts the number of meetings based on passed paramaters
     *
     * @param start startdate in the read format
     * @param end enddate in the read format
     * @param day_of_week day of the week in read format
     *
     * @return the number of meetings between specified dates on day of week
     */
    int count(String start, String end, String day_of_week);
}
