/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnrxjava.examples;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 *
 * @author Home
 */
public class Parse {

    public void doIt() {
        InputStream ins = null; // raw byte-stream
        Reader r = null; // cooked reader
        BufferedReader br = null; // buffered for readLine()
        try {
            String s = "";

            ins = new FileInputStream("E:\\acquiring_docs\\logs\\lognew\\pegarules.log");

            r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
            br = new BufferedReader(r);
            while ((s = br.readLine()) != null) {

                if (s.trim() != null && !s.trim().isEmpty() ) {
                    String str = s.substring(0, 19);
                   // System.out.println("###################gag " + str);
                    if (str != null && !s.startsWith("<")) {
                        if (isValidDate(s.substring(0, 19))) {
                            System.out.println("Date " + s.substring(0, 19)
                                    + " || " + " Action " + s.substring(84, 114) + 
                                    " logLevel " + s.substring(116, 121) +
                                    " Message " + s.substring(122, s.length() - 1));
                        

                        }
                    }
                }

           //     if(!s.trim().isEmpty() && !s.trim().startsWith("<?xml") &&
                //   !s.trim().startsWith("javax") && !s.trim().startsWith("at") && 
                //   !s.trim().startsWith("com")
                //  && !s.trim().startsWith("From:") && !s.trim().startsWith("Caused") || !s.trim().startsWith("") ){
                //System.out.println(s);
               // System.out.println("Maxpri " + s.substring(25, 44));
                // System.out.println("Status " + s.substring(50, 58));
                // System.out.println("Component " + s.substring(61, 81));
                //System.out.println("Action " + s.substring(84, 114));
                //System.out.println("logLevel " + s.substring(116, 121));
                // System.out.println("get last index " +s.length());
                //System.out.println(Arrays.toString(s.split("\\s+")));
                //    }
            }
        } catch (Exception e) {

            System.err.println(e.getMessage());
            // handle exception
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Throwable t) { /* ensure close happens */ }
            }
            if (r != null) {
                try {
                    r.close();
                } catch (Throwable t) { /* ensure close happens */ }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (Throwable t) { /* ensure close happens */ }
            }
        }
    }

    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static void main(String args[]) {
        Parse parse = new Parse();
        parse.doIt();
    }
}
