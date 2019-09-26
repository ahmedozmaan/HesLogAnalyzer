package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Sample {
    static double x;
    private int c = 0;
    //INSERT INTO `ami`.`action_log` (`hes_id`, `serial_no`, `phone_number`, `status`, `created_at`, `obis`, `type`, `start`, `end`, `retry`, `delay`) VALUES
    //        ('1', 'CLE3541900594580', '00252682901996', 'new', '2019-05-25 11:06:00', '1.0.98.1.0.255', 'DATA:MONTHLY', '2019-05-28 00:00:00', '2019-05-24 00:00:00', '1', '0');


    public static void main(String[] args) {

        try {
            FileWriter fw=new FileWriter("C:\\Users\\User\\IdeaProjects\\HesLogAnalyzer\\src\\com\\company\\query.txt");
            File file = new File("C:\\Users\\User\\IdeaProjects\\HesLogAnalyzer\\src\\com\\company\\meters.txt");
            BufferedReader meterReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = meterReader.readLine()) != null) {
                fw.write("('1',"+line+", 'new', '2019-05-25 03:29:28', '1.0.98.2.0.255', 'DATA:DAILY', '2019-05-25 00:15:00', '2019-05-25 00:00:00', '1', '0'),\n");
            }
            fw.close();
        } catch (Exception ignored) {

        }finally {
        }

/*try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/water", "root", "asad_root");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from emp");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }*/
    }
}
