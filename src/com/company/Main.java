package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static String filePath = "";
    List Failed = Arrays.asList(
            "CLE1541800041590",
            "CLE1541800042090",
            "CLE1541800041420",
            "CLE1541800041670",
            "CLE1541800039790",
            "CLE1541800036230",
            "CLE1541800036720",
            "CLE1541800033910",
            "CLE1541800047950",
            "CLE1541800039950",
            "CLE3541800086550",
            "CLE1541800041340",
            "CLE1541800041180",
            "CLE3541800086220",
            "CLE1541800028070",
            "CLE1541800032010",
            "CLE3541800086140",
            "CLE1541800033340",
            "CLE1541800027990",
            "CLE1541800041830",
            "CLE1541800047870",
            "CLE1541800031100"
    );

    public static void main(String[] args) {
	// write your code here
    }

    private static List<String> getMeters(){
        try {
            File file = new File(filePath);
            BufferedReader meterReader = new BufferedReader(new FileReader(file));
            String line;
            List<String> meterList = new ArrayList<>();
            while ((line = meterReader.readLine()) != null) {
                meterList.add(line);
            }
            return meterList;
        }catch (Exception ignored){

        }
        return null;
    }

    private static void checkMeter(List<String> serial) {
        try {
            for (String s:serial) {
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
                int count  = 0;

                while ((text = reader.readLine()) != null) {
                    if ((x = text.indexOf(query1)) != -1) {
                        //System.out.println("\""+text.split(" ")[2] +"\",");
                        //monthly.append(" ").append(text.substring(x).split(" ")[0]);
                    } else if ((x = text.indexOf(query2)) != -1) {
                        System.out.println("\""+text.split(" ")[2] +"\",");
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
                    }else if ((x = text.indexOf("No data founded")) != -1) {
                        System.out.println("Serial :"+ s + " No data founded" );

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
        }catch (Exception ex){

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
                    if ((text.indexOf("S:" + s)) != -1) {
                        if( text.contains("T:SMS") && !meterThreads.contains(text.split(" ")[2]))
                            meterThreads.add(text.split(" ")[2]);
                    }
                }
                meterThreadInformation(meterThreads,s);
            }
        } catch (Exception ignored) {

        }
    }

    private static void meterConnectionCount(List<String> serial) {
        try {
            int alertCount, smsCount;
            for (String s : serial) {
                alertCount = 0; smsCount = 0;
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                while ((text = reader.readLine()) != null) {
                    if ((text.indexOf("S:" + s)) != -1) {
                        if (text.contains("T:ALERT")){
                            alertCount ++;
                        }else {
                            smsCount ++;
                        }
                    }
                }
                System.out.printf("%s %s:Alert %s:Sms \n", s, alertCount, smsCount);
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
                            threadINfo.append("LOG"+ " ");
                        }
                        if(val.contains(String.format("READING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "MONTHLY"))){
                            threadINfo.append("READ:MON"+ "-");
                        }else if(val.contains(String.format("READING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "DAILY"))){
                            threadINfo.append("READ:DAY"+ "-");
                        }else if(val.contains(String.format("READING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "HOURLY"))){
                            threadINfo.append("READ:HOUR"+ "-");
                        }else if(val.contains(String.format("SAVING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "MONTHLY"))){
                            threadINfo.append("SAVE:MON"+ " ");
                        }else if(val.contains(String.format("SAVING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "DAILY"))){
                            threadINfo.append("SAVE:DAY"+ " ");
                        }else if(val.contains(String.format("SAVING DATA FROM  Meter: %s ACTION TYPE: DATA:%s", serial, "HOURLY"))){
                            threadINfo.append("SAVE:HOUR"+ " ");
                        }else if((val.indexOf("ActionLog log size:")) != -1){
                            int z = text.indexOf("Thread");
                            String y = text.substring(0,z-1);
                            y = y.split(" ")[1];
                            String value =  y.split(",")[0]+"-->"+"[" +val.split(":")[1].substring(1) + "]";
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
            System.out.println("====================================== "+threat+" ================================");
            try {
                File file = new File(filePath);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String text;
                int x =  0;
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
