package com.example.pckosek.statusmonitor;


import android.util.Log;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LedAction {

    public int id;
    public String stamp;
    public String op;
    public String user_name;

    private DateFormat sdf;
    private SimpleDateFormat sdfAmerica;

    /* ------------------- */
    /*  CONSTRUCTOR
    /* ------------------- */

    public LedAction() {
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        sdfAmerica = new SimpleDateFormat("E MMM dd yyyy, hh:mm:ss a");
        sdfAmerica.setTimeZone(TimeZone.getTimeZone("America/New_York"));
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    public String getStampString() {

        String out = "";

        try {
            Date date = sdf.parse(stamp);
            out = sdfAmerica.format(date);
        } catch (Exception e) {
            Log.e("ERROR", "DATEFORMATTING");
        }
        return out;

    }

    public String getOpString() {
        return op;
    }

    public String getUser_nameString() {
        return user_name;
    }
}
