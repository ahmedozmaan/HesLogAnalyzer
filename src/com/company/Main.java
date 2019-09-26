package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    //2019-04-15 03:46:16,366 [pool-1-thread-1] INFO  c.s.amisms.jobs.HourlyJob - RUNNING HOURLY JOB
    //2019-04-15 09:46:37,832 [pool-1-thread-1] INFO  c.s.amisms.jobs.HourlyJob - RUNNING HOURLY JOB
    //2019-04-15 15:46:59,420 [pool-1-thread-1] INFO  c.s.amisms.jobs.HourlyJob - RUNNING HOURLY JOB
    //2019-04-15 21:47:20,334 [pool-1-thread-1] INFO  c.s.amisms.jobs.HourlyJob - RUNNING HOURLY JOB

    static String filePath = "C:\\Users\\User\\Documents\\hes\\hes.log";
    //static String filePath = "C:\\Users\\User\\Documents\\hes.2019-04-24.log";


    public static void main(String[] args) {
        //getMeterThread();
        //2019-03-19 07:18:34,606 [Thread-173529] INFO  c.s.a.dlms.ExecutorThread - Alert: C:102 D:Tue Mar 19 07:17:18 EAT 2019 S:CLE1541800068240 T:ALERT
        //2019-03-19 07:20:53,597 [pool-1-thread-1] INFO  c.s.amisms.dlms.Listener - Adding ActionLog: DATA:DAILY for: CLE1541800068240

        //getMeterThreads("[pool-1-thread-1] INFO  c.s.amisms.dlms.Listener - Adding ActionLog: DATA:HOURLY for","2019-04-16 09:48");
        //getMeterThreads("CLE1541800020540");
        //getMetersThreads("CLE1541800027990");
        //getMetersThreads("CLE1541800004880");
        /*for(String x : getMeters()){
            getMetersThreads(x);
            System.out.println();
        }*/
        //getMetersThreads("CLE1541800027990");
        //getString("ERROR c.s.a.e.DataExecutor - No data found");
        //getThreadByString("CLE1541800104120");

        //meterActionTrack("CLE3541800091580");
        //meterActionTrack("CLE1541800048520");
        // meterActionTrack("CLE1541800020540");
        //meterActionTrack("CLE1541800027990");

        //meterActionTrack("CLE1541800107500");
        // CLE3541800091660    CLE1541800124340    CLE1541800134160
        //getThreadByString("S:CLE1541800124340");
        getThreadByString("Exception StackTrace:gurux.dlms.GXDLMSException: Access Error : Other Reason.");
        //getString("Prev APN");


        //getString("Meter Calender Serial: CLE3541800091580 Calender Date:");
        //getString("Meter Calender Serial: CLE1541800048520 Calender Date:");
        //getString("Meter Calender Serial: CLE1541800020540 Calender Date:");
        //getString("Meter Calender Serial: CLE1541800027990 Calender Date:");
        //getThreadByString("READING DATA FROM  Meter: CLE3541800090750 ACTION TYPE: DATA:MONTHLY");
        //getString("ACTION TYPE: DATA:MONTHLY");

        //getMetersThreads("CLE1541800149270");
        //getMeterThreads("CLE1541800106510");
        //getMeterThreads("CLE1541800106020");
        //getMeterThreads("S:CLE1541800149270 T:SMS");
        //getMeterThreads("S:CLE1541800075580 T:SMS");
        // getMetersThreads("Prev APN:");
        //meterConnectionCount(getMeters());
        //meterSentSMS(getMeters());
        //getMeterThreads(getMeters());
        // getMeterThread();
    }

    private static List<String> getMeters() {
        List<String> meterList = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\User\\IdeaProjects\\HesLogAnalyzer\\src\\com\\company\\meters.txt");
            BufferedReader meterReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = meterReader.readLine()) != null) {
                meterList.add(line);
            }
            return meterList;
        } catch (Exception ignored) {

        }
        return meterList;
    }

    private static void checkMeter(List<String> serial) {
        try {
            for (String s : serial) {
                StringBuilder monthly = new StringBuilder("MONTHLY");
                StringBuilder daily = new StringBuilder("DAILY");
                StringBuilder hourly = new StringBuilder("HOURLY");

                String reading = "READING DATA FROM  Meter: %s ACTION TYPE: DATA:%s";
                String saved = "SAVING DATA FROM  Meter: %s ACTION TYPE: DATA:%s";
                File file = new File(filePath);

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                int x;
                String query1 = String.format(reading, s, "MONTHLY");
                String query2 = String.format(reading, s, "DAILY");
                String query3 = String.format(reading, s, "HOURLY");
                String query4 = String.format(saved, s, "MONTHLY");
                String query5 = String.format(saved, s, "DAILY");
                String query6 = String.format(saved, s, "HOURLY");
                int count = 0;

                while ((text = reader.readLine()) != null) {
                    if ((x = text.indexOf(query1)) != -1) {
                        //System.out.println("\""+text.split(" ")[2] +"\",");
                        //monthly.append(" ").append(text.substring(x).split(" ")[0]);
                    } else if ((x = text.indexOf(query2)) != -1) {
                        System.out.println("\"" + text.split(" ")[2] + "\",");
                        daily.append(" ").append(text.substring(x).split(" ")[0]);
                    } else if ((x = text.indexOf(query3)) != -1) {
                        hourly.append(" ").append(text.substring(x).split(" ")[0]);
                    } else if ((x = text.indexOf(query4)) != -1) {
                        monthly.append(" ").append(text.substring(x).split(" ")[0]);
                    } else if ((x = text.indexOf(query5)) != -1) {
                        daily.append(" ").append(text.substring(x).split(" ")[0]);
                    } else if ((x = text.indexOf(query6)) != -1) {
                        hourly.append(" ").append(text.substring(x).split(" ")[0]);
                    } else if ((x = text.indexOf("S:" + s)) != -1) {
                        count++;
                        //System.out.println(text.substring(x)); No data founded
                    } else if ((x = text.indexOf("No data founded")) != -1) {
                        System.out.println("Serial :" + s + " No data founded");

                    }
                }
                /*if(count > 0 && (monthly.length() > 7 || daily.length() > 7 || hourly.length() > 7)){
                    System.out.println("Serial :"+ s);
                    System.out.println(monthly);
                    System.out.println(daily);
                    System.out.println(hourly);
                    System.out.println("==========================================");
                }*/

            }
        } catch (Exception ex) {

        }
    }


    private static void getThreadByString(String s) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            ArrayList<String> meterThreads = new ArrayList<>();

            while ((text = reader.readLine()) != null) {
                if (text.contains(s)) {
                    meterThreads.add(text.split(" ")[2]);
                }
            }
            printThread(meterThreads);
        } catch (Exception ignored) {

        }
    }

    private static void meterActionTrack(String s) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                if (text.contains(s)) {
                    if (text.contains("SMS sent to:") ||
                            text.contains("Adding ActionLog") ||
                            text.contains("S:" + s) ||
                            text.contains("ActionLog Remove")) {
                        System.out.println(text);
                    }
                }
            }
        } catch (Exception ignored) {

        }
    }


    private static void getMeterThreads(String s) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            ArrayList<String> meterThreads = new ArrayList<>();

            while ((text = reader.readLine()) != null) {
                if ((text.indexOf(s)) != -1) {
                    if ((text.indexOf("S:" + s)) != -1)
                        meterThreads.add(text.split(" ")[2]);
                }
            }
            printThread(meterThreads);
        } catch (Exception ignored) {

        }
    }

    private static void getString(String s) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            ArrayList<String> meterThreads = new ArrayList<>();

            while ((text = reader.readLine()) != null) {
                if (text.contains(s)) {
                    System.out.println(text);
                }
            }
        } catch (Exception ignored) {

        }
    }

    private static void getMetersThreads(String... serial) {
        try {
            for (String s : serial) {
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                ArrayList<String> meterThreads = new ArrayList<>();

                while ((text = reader.readLine()) != null) {
                    if ((text.indexOf(s)) != -1) {
                        //if ((text.indexOf("S:" + s)) != -1) {
                        System.out.println(text);
                        //if (text.contains("T:SMS") && !meterThreads.contains(text.split(" ")[2]))
                        // meterThreads.add(text.split(" ")[2]);
                    }
                }
                printThread(meterThreads);
            }
        } catch (Exception ignored) {

        }
    }

    private static void getMeterThreads(String q1, String q2) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            int count = 0;
            while ((text = reader.readLine()) != null) {
                if ((text.contains(q1) && text.contains(q2))) {
                    //if ((text.indexOf("S:" + s)) != -1) {
                    System.out.println(++count + ":" + text);
                    //if (text.contains("T:SMS") && !meterThreads.contains(text.split(" ")[2]))
                    // meterThreads.add(text.split(" ")[2]);
                }
            }
        } catch (Exception ignored) {

        }
    }

    private static void getMeterThreads(List<String> serial) {
        try {
            for (String s : serial) {
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                ArrayList<String> meterThreads = new ArrayList<>();

                while ((text = reader.readLine()) != null) {
                    if ((text.indexOf(s)) != -1) {
                        //if ((text.indexOf("S:" + s)) != -1) {
                        System.out.println(text.substring(text.indexOf("INFO")));
                        //if (text.contains("T:SMS") && !meterThreads.contains(text.split(" ")[2]))
                        // meterThreads.add(text.split(" ")[2]);
                    }
                }
                printThread(meterThreads);
            }
        } catch (Exception ignored) {

        }
    }

    private static void getMeterThread() {
        try {

            FileWriter fw = new FileWriter("out.txt");
            for (String serial : getMeters()) {
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                ArrayList<String> meterThreads = new ArrayList<>();

                while ((text = reader.readLine()) != null) {
                    if (text.contains(serial + " ActionLog Remove: DATA:HOURLY") ||
                            text.contains("Adding ActionLog: DATA:HOURLY for: " + serial) ||
                            text.contains("MONITOR: SMS sent to: " + serial)) {
                        //if (text.contains("2019-03-09 04:27")) {
                        //if (text.contains("RUNNING HOURLY JOB")) {
                        String[] data = text.split(" ");
                        String val = data[0] + " " + data[1] + " " + data[6] + " " + data[7] + " " + data[8] + " " + data[9] + " " + data[10] + " " + data[11];
                        System.out.print(" - " + val);
                        //System.out.println(data[0] +" "+ data[1] +" "+data[5] +" "+data[6] +" "+data[7] +" "+data[8] +" "+data[9] +" "+data[10]);
                        fw.write(" - " + val);
                    }
                }
                fw.write("\n");
                System.out.println();
            }
            fw.close();
        } catch (Exception ignored) {

        }
    }

    private static void getMeterActonInfo() {
        try {

            FileWriter fw = new FileWriter("out.txt");
            for (String serial : getMeters()) {
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                ArrayList<String> meterThreads = new ArrayList<>();

                while ((text = reader.readLine()) != null) {
                    if (text.contains(serial + " ActionLog Remove: DATA:HOURLY") ||
                            text.contains("Adding ActionLog: DATA:HOURLY for: " + serial)) {
                        //if (text.contains("2019-03-09 04:27")) {
                        //if (text.contains("RUNNING HOURLY JOB")) {
                        String[] data = text.split(" ");
                        String val = data[0] + " " + data[1] + " " + data[6] + " " + data[7] + " " + data[8] + " " + data[9] + " " + data[10] + " " + data[11];
                        System.out.print(" - " + val);
                        //System.out.println(data[0] +" "+ data[1] +" "+data[5] +" "+data[6] +" "+data[7] +" "+data[8] +" "+data[9] +" "+data[10]);
                        fw.write(" - " + val);
                    }
                }
                fw.write("\n");
                System.out.println();
            }
            fw.close();
        } catch (Exception ignored) {

        }
    }

    private static void meterConnectionCount(List<String> serial) {
        try {
            int alertCount, smsCount;
            for (String s : serial) {
                System.out.println();
                alertCount = 0;
                smsCount = 0;
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                while ((text = reader.readLine()) != null) {
                    if ((text.indexOf("S:" + s)) != -1) {


                        if (text.contains("T:ALERT")) {
                            alertCount++;
                        } else {
                            smsCount++;
                        }
                    } else if (text.contains(s)) {
                        if (text.contains("SMS sent to:") ||
                                text.contains("Adding ActionLog") ||
                                text.contains("ActionLog Remove")) {
                            System.out.println(text);
                        }
                    }
                }
                System.out.printf("%s %s:Alert %s:Sms \n", s, alertCount, smsCount);
            }
        } catch (Exception ignored) {

        }
    }


    private static void meterSentSMS(List<String> serial) {
        try {
            for (String s : serial) {
                System.out.println();
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                while ((text = reader.readLine()) != null) {
                    if (text.contains(s)) {
                        if (text.contains("SMS sent to:") ||
                                text.contains("Adding ActionLog") ||
                                text.contains("ActionLog Remove")) {
                            System.out.println(text);
                        }
                    }
                }
            }
        } catch (Exception ignored) {

        }
    }


    private static void meterThreadInformation(ArrayList<String> threat, String serial) {
        StringBuilder threadINfo = new StringBuilder("");
        for (String th : threat) {
            threadINfo.append(th);
            try {
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                int x = 0;
                while ((text = reader.readLine()) != null) {
                    if ((x = text.indexOf(th)) != -1) {
                        if (text.substring(x).contains("- <-") || text.substring(x).contains("- ->")) {
                            continue;
                        }
                        String val = text.substring(x);
                        if ((x = val.indexOf(String.format("LOGIN TO Meter: %s", serial))) != -1) {
                            threadINfo.append("LOG" + " ");
                        }
                        if (val.contains(String.format("READING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "MONTHLY"))) {
                            threadINfo.append("READ:MON" + "-");
                        } else if (val.contains(String.format("READING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "DAILY"))) {
                            threadINfo.append("READ:DAY" + "-");
                        } else if (val.contains(String.format("READING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "HOURLY"))) {
                            threadINfo.append("READ:HOUR" + "-");
                        } else if (val.contains(String.format("SAVING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "MONTHLY"))) {
                            threadINfo.append("SAVE:MON" + " ");
                        } else if (val.contains(String.format("SAVING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "DAILY"))) {
                            threadINfo.append("SAVE:DAY" + " ");
                        } else if (val.contains(String.format("SAVING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "HOURLY"))) {
                            threadINfo.append("SAVE:HOUR" + " ");
                        } else if ((val.indexOf("ActionLog log size:")) != -1) {
                            int z = text.indexOf("Thread");
                            String y = text.substring(0, z - 1);
                            y = y.split(" ")[1];
                            String value = y.split(",")[0] + "-->" + "[" + val.split(":")[1].substring(1) + "]";
                            threadINfo.append(value);
                        }
                    }

                }

            } catch (Exception ignored) {

            }
        }
        System.out.println(threadINfo);
    }

    private static void printThread(ArrayList<String> threat) {
        if (threat.size() == 0) return;
        for (String th : threat) {
            System.out.println("====================================== " + th + " ================================");
            try {
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                int x = 0;
                while ((text = reader.readLine()) != null) {
                    if ((x = text.indexOf(th)) != -1) {
                        if (text.substring(x).contains("- <-") || text.substring(x).contains("- ->")) {
                            continue;
                        }
                        System.out.println(text);
                    }

                }

            } catch (Exception ignored) {

            }
        }
    }
}
