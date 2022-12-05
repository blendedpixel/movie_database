import java.sql.*;
import java.util.ArrayList;

class App {

    // Method that fetches customer accounts and returns the response in an array
    static void retrieveCustomerAccounts() {

        try {

            // Create connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bighitvideo", "root", "mysqlpassword");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer");

            // Store in ArrayList

            // Arraylist to store customer accounts
            ArrayList<String> customerAccounts = new ArrayList<String>();

            // Add to ArrayList
            if (rs.next()) {

                do {
                    String singleRecord = rs.getString("accountId");

                    customerAccounts.add(singleRecord);
                } while (rs.next());

            } else {

                System.out.println("No customer records exist");

            }

            // Close connection
            con.close();

            System.out.println("<< Customer Accounts >>");
            System.out.println(customerAccounts);

        } catch (Exception e) {

            System.out.println(e);

        }

    }

    // Method that fetches customer accounts and returns the response in an array
    static void accountsAndVideos() {

        try {

            // Create connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bighitvideo", "root", "mysqlpassword");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select accountId, videoId from rental union select accountId, videoId from previousrental order by accountId");

            System.out.println("<< Customer Accounts and Videos currently and previously rented >>");
            while (rs.next()) {
                System.out
                        .println("Account Id: " + rs.getString("accountId") + ", videoId: " + rs.getString("videoId"));
            }
            ;

        } catch (Exception e) {

            System.out.println(e);

        }
        ;

    };

    public static void main(String args[]) {

        // Retrieve customer accounts
        retrieveCustomerAccounts();

        System.out.println(" ");

        // Get accounts and rentals
        accountsAndVideos();

    }
}