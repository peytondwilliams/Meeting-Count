# Meeting-Count
A java application for counting the number of meetings between two dates on a specific day of the week

## Simple.java:
Implements basic functionality. 

* Takes user input from console on question by question basis for csv file path, start date, end date, and day of the week. 

## Complex.java:
Modifies Simple.java's implementation. Adds support for counting multiple meetings on the same day. Adds input assertions. 

* Users can pass input as arguments in the following order: <csv_meetings_file_path> <start_date> <end_date> <day_of_week>. 
* Alternatively pass in the following arguments: <csv_meetings_file_path>, <csv_user_input_file_path>. Outputs to Output.csv

Example run: 
java Complex test3.csv user_input3.csv


File output to Output.csv

## Notes
* Holidays are stored in text file holidays.txt, containing all federal holidays 2018-2021
* files test[1-3].csv and user_input[1-3].csv provided to demonstrate basic functionality
* 3 hours spent on research and Simple.java implementation, 1 hour spent on Complex.java additions, 1 hour spent on provided inputs and debugging

## Sources
* https://docs.oracle.com
* https://www.thebalancecareers.com/holidays-paid-holidays-and-holiday-pay-2060447
