package scheduloe;

import java.util.Scanner;

public class Scheduloe {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String resp;

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            sc.nextLine();

            Scheduloe schedule = new Scheduloe(); 

            switch (action) {
                case 1:
                    schedule.addSchedule();
                    break;
                case 2:
                    schedule.viewSchedule();
                    break;
                case 3:
                    schedule.viewSchedule();
                    schedule.updateSchedule();
                    break;
                case 4:
                    schedule.viewSchedule();
                    schedule.deleteSchedule();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
            }

            System.out.print("Continue? (yes/no): ");
            resp = sc.next();

        } while (resp.equalsIgnoreCase("yes"));
        
        System.out.println("Thank You!");
        sc.close(); // Close the scanner
    }

    public void addSchedule() {
        Scanner sc = new Scanner(System.in);
        config conf = new config(); 

        System.out.print("Day of Week: ");
        String dayOfWeek = sc.nextLine();

        System.out.print("Start Time (HH:MM): ");
        String startTime = sc.nextLine();

        System.out.print("End Time (HH:MM): ");
        String endTime = sc.nextLine();

        String sql = "INSERT INTO tbl_schedule (day_of_week, start_time, end_time) VALUES (?, ?, ?)";
        conf.addRecord(sql, dayOfWeek, startTime, endTime); // Adjusted parameters for addRecord
    }

    private void viewSchedule() {
        String qry = "SELECT * FROM tbl_schedule"; // Correct query for schedule
        String[] hdrs = {"ID", "Day of Week", "Start Time", "End Time"};
        String[] clms = {"schedule_id", "day_of_week", "start_time", "end_time"}; // Corrected column names

        config conf = new config();
        conf.viewRecords(qry, hdrs, clms); // Adjusted method to view schedule
    }

    private void updateSchedule() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline character

        System.out.print("Enter new Day of Week: ");
        String newDayOfWeek = sc.nextLine();

        System.out.print("Enter new Start Time (HH:MM): ");
        String newStartTime = sc.nextLine();

        System.out.print("Enter new End Time (HH:MM): ");
        String newEndTime = sc.nextLine();

        String qry = "UPDATE tbl_schedule SET day_of_week = ?, start_time = ?, end_time = ? WHERE schedule_id = ?";
        config conf = new config();
        conf.updateRecord(qry, newDayOfWeek, newStartTime, newEndTime, id); // Pass correct parameters
    }

    private void deleteSchedule() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        String qry = "DELETE FROM tbl_schedule WHERE schedule_id = ?";
        config conf = new config();
        conf.deleteRecord(qry, id); // Corrected to use schedule_id
    }
}
