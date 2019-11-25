# Meeting-Count
A java application for counting the number of meetings between two dates on a specific day of the week

## Simple.java:
Implements basic functionality. 

* Takes user input from console on question by question basis for csv file path, start date, end date, and day of the week. 

## Complex.java:
Modifies Simple.java's implementation. Adds support for counting multiple meetings on the same day. Added input assertions. 

* Users can pass input as arguments in the following order: csv_meetings_file_path start_date end_date day_of_week. 
* Alternatively pass in the following arguments: csv_meetings_file_path, csv_user_input_file_path. Output's to output.csv
