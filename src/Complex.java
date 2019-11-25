import java.io.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.util.HashMap;

/**
 * Complex meeting count implementation
 *
 * @author Peyton D Williams will1892@purdue.edu
 * @version 11/24/2019
 *
 */

public class Complex implements Count_Interface {

    public Complex() {
        count_calender = new HashMap<>();
    }

    private HashMap<String, Integer> count_calender;


    public boolean read_csv(String file_path) {
        assert file_path != null;
        assert file_path.endsWith(".csv");

        BufferedReader reader;
        BufferedReader holiday_reader;

        try {
            reader = new BufferedReader(new FileReader(file_path));
            try {
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] line = row.split(", ");
                    add(line[0], line[1], line[2]);
                }
            }
            catch (IOException err) {
                System.out.println("Error on file read.");
                return false;
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("File at: " + file_path + " was not found.");
            return false;
        }

        try {
            holiday_reader = new BufferedReader(new FileReader("holidays.txt"));
        }
        catch (FileNotFoundException err) {
            System.out.println("Holidays file not found");
            return false;
        }


        try {
            String row;
            while ((row = holiday_reader.readLine()) != null) {
                if (count_calender.get(row) != null) {
                    count_calender.remove(row);
                }
            }
        }
        catch (IOException err) {
            System.out.println("Error while parsing holiday text file");
            return false;
        }

        return true;
    }


    /**
     * reads the passed user csv file and outputs an Output.csv with meeting counts
     *
     * @param file_path a filepath to user input file
     *
     * @return if read was successful
     */
    public boolean read_input_csv(String file_path) {
        assert file_path != null;
        assert file_path.endsWith(".csv");

        BufferedReader reader;
        FileWriter writer;

        try {
            reader = new BufferedReader(new FileReader(file_path));
        }
        catch (FileNotFoundException err) {
            System.out.println("User input file not found");
            return false;
        }

        try {
            writer = new FileWriter("Output.csv");
        }
        catch (IOException err) {
            System.out.println("error creating Output.csv");
            return false;
        }

        try { //reads the user input file and writes to Output.txt
            String row;
            while ((row = reader.readLine()) != null) {
                String[] line = row.split(", ");
                int new_count = count(line[0], line[1], line[2]);
                writer.append(line[0]);
                writer.append(", ");
                writer.append(line[1]);
                writer.append(", ");
                writer.append(line[2]);
                writer.append(", ");
                writer.append(Integer.toString(new_count));
                writer.append("\n");
            }
        }
        catch (IOException err) {
            System.out.println("Error reading user input file");
            return false;
        }

        try {
            writer.flush();
            writer.close();
        }
        catch (IOException err) {
            System.out.println("Output.csv did not write and close correctly");
            return false;
        }

        return true;
    }


    public void add(String start_date, String end_date, String day_of_week) {
        assert start_date != null;
        assert end_date != null;
        assert day_of_week != null;

        LocalDate start = LocalDate.parse(start_date, date_format);
        LocalDate end = LocalDate.parse(end_date, date_format);
        DayOfWeek weekday = DayOfWeek.valueOf(day_of_week.toUpperCase());

        //formats date to start on the given day of week
        if (start.getDayOfWeek().getValue() < weekday.getValue()) {
            start = start.plusDays(weekday.getValue() - start.getDayOfWeek().getValue());
        }
        else if (start.getDayOfWeek().getValue() > weekday.getValue()) {
            start = start.plusDays(7 - weekday.getValue() - start.getDayOfWeek().getValue());
        }

        //adds dates to count_calender, support added for multiple meetings on the same day
        while (start.isBefore(end)) {
            String curr_date = start.toString();
            if (count_calender.get(curr_date) == null) {
                count_calender.put(curr_date, 1);
            }
            else {
                count_calender.put(curr_date, count_calender.get(curr_date) + 1);
            }

            start = start.plusDays(7);
        }
    }


    public int count(String start_date, String end_date, String day_of_week) {
        assert start_date != null;
        assert  end_date != null;
        assert  day_of_week != null;

        LocalDate start =  LocalDate.parse(start_date, date_format);
        LocalDate end = LocalDate.parse(end_date, date_format);
        DayOfWeek weekday = DayOfWeek.valueOf(day_of_week.toUpperCase());


        //formats date to start on the given day of week
        if (start.getDayOfWeek().getValue() < weekday.getValue()) {
            start = start.plusDays(weekday.getValue() - start.getDayOfWeek().getValue());
        }
        else if (start.getDayOfWeek().getValue() > weekday.getValue()) {
            start = start.plusDays(7 - weekday.getValue() - start.getDayOfWeek().getValue());
        }

        int count = 0;

        //counts days in count_calender
        while(start.isBefore(end)) {
            String curr_date = start.toString();
            if (count_calender.get(curr_date) != null) {
                count += count_calender.get(curr_date);
            }
            start = start.plusDays(7);
        }

        return count;
    }


    public static void main(String[] args) {
        Complex counter = new Complex();

        if (args.length == 2) {
            boolean read_correct = counter.read_csv(args[0]);
            if (!read_correct) {
                return;
            }
            boolean user_read_correct = counter.read_input_csv(args[2]);
            if (!user_read_correct) {
                return;
            }

        }
        else if (args.length == 4) {
            boolean read_correct = counter.read_csv(args[0]);
            if (!read_correct) {
                return;
            }
            int count = counter.count(args[1], args[2], args[3]);
            System.out.println("The number of meetings between " + args[1] + " and " + args[2] + " is: " + count);
        }
        else {
            System.out.println("Incorrect number of arguments, please try again");
        }
    }
}
