package com.countdowntimer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*This class is used for show countdown timer months,days,weeks,hours,minutes and seconds */

public class CountDownTimerActivity extends AppCompatActivity {

    /*Views declaration*/
    private TextView months_left,weeks_left,daysLeft,hrsLeft,minLeft,secLeft,endDate;
    /*Handler Declaration*/
    private Handler handler;
    /*set End Time for timer */
    private String endDateTime="2019-04-12 10:15:00";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*inflate layout for activity*/
        setContentView(R.layout.activity_count_down_timer);
        /*invoke initView method  for views*/
        initView();
    }
    /*initView method for findviews by id*/
    private void initView() {
        months_left = findViewById(R.id.months_left);
        weeks_left = findViewById(R.id.weeks_left);
        daysLeft = findViewById(R.id.days_left);
        hrsLeft = findViewById(R.id.hrs_left);
        minLeft = findViewById(R.id.min_left);
        secLeft = findViewById(R.id.sec_left);
        endDate = findViewById(R.id.end_date);
        endDate.setText(endDateTime);
        /*invoke countDownStart() method for start count down*/
        countDownStart();
    }

    /*countDownStart() method for start count down*/
    /*countDownStart() method for start count down*/
    public void countDownStart() {
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    // Please set date in  YYYY-MM-DD hh:mm:ss format
                    /*parse endDateTime in future date*/
                    Date futureDate = dateFormat.parse(endDateTime);
                    Date currentDate = new Date();
                    /*if current date is not comes after future date*/
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();

                        long days = diff / (24 * 60 * 60 * 1000);
//                        Log.e("days--",""+days);
                        diff -= days *(24  *60 * 60  *1000);
                        long hours = diff / (60 * 60*  1000);
                        diff -= hours * (60*  60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60  *1000);
                        long seconds = diff / 1000;
                        Calendar mycal = Calendar.getInstance();
                        mycal.setTime(currentDate);
                        Calendar cal1 = Calendar.getInstance();
                        Calendar cal2 = Calendar.getInstance();
                        cal1.setTime(currentDate);
                        cal2.setTime(futureDate);
                        long years;
                        if (((cal1.get(Calendar.YEAR) % 4) == 0) || ((cal1.get(Calendar.YEAR) % 4) == 0)) {
                            years = days / 366;
                        } else {
                            years = days / 365;
                        }
                        long weeks = days / 7;
                        if (weeks % 5 == 0) {
                            weeks = weeks / 5;
                        } else {
                            weeks = weeks % 5;
                        }
                        long diffMonth = 0;
                        if (cal2.get(Calendar.MONTH) > cal1.get(Calendar.MONTH)) {
                            if ((cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH) < 12) && ((cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH)) > -1)) {
                                diffMonth = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
                            } else {
                                diffMonth = 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
                            }
                        }
                        long day = 0;
                        if (days % 7 == 0) {
                            day = days % 7;
                        } else {
                            day = days % 7;
                        }
                        @SuppressLint("DefaultLocale") String yearsLeft = "" + String.format("%02d", years);
                        @SuppressLint("DefaultLocale") String monthsLeft = "" + String.format("%02d", diffMonth);
                        @SuppressLint("DefaultLocale") String weeksLeft = "" + String.format("%02d", weeks);
                        @SuppressLint("DefaultLocale") String dayLeft = "" + String.format("%02d", day);
                        @SuppressLint("DefaultLocale") String hrLeft = "" + String.format("%02d", hours);
                        @SuppressLint("DefaultLocale") String minsLeft = "" + String.format("%02d", minutes);
                        @SuppressLint("DefaultLocale") String secondLeft = "" + String.format("%02d", seconds);


                        if (monthsLeft.equals("00") && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (!hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D ");
                            hrsLeft.setText(hrLeft + "H");
                        } else if (monthsLeft.equals("00") && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D ");
                            hrsLeft.setText(hrLeft + "H");
                        } else if (!monthsLeft.equals("00") && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (!hrLeft.equals("00"))) {
                            months_left.setText(monthsLeft + "M: ");
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D");
                            hrsLeft.setVisibility(View.GONE);
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                        } else if (!monthsLeft.equals("00") && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (hrLeft.equals("00"))) {
                            months_left.setText(monthsLeft + "M: ");
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D");
                            hrsLeft.setVisibility(View.GONE);
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                        } else if (!monthsLeft.equals("00") && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (!hrLeft.equals("00"))) {
                            months_left.setText(monthsLeft + "M: ");
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D");
                            hrsLeft.setVisibility(View.GONE);
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                        } else if (!monthsLeft.equals("00") && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (hrLeft.equals("00"))) {
                            months_left.setText(monthsLeft + "M: ");
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D");
                            hrsLeft.setVisibility(View.GONE);
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                        } else if ((!monthsLeft.equals("00")) && (weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (!hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D: ");
                            hrsLeft.setText(hrLeft + "H:");
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);

                        } else if ((!monthsLeft.equals("00")) && (weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D: ");
                            hrsLeft.setText(hrLeft + "H:");
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);

                        } else if ((monthsLeft.equals("00")) && (weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (!hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            weeks_left.setVisibility(View.GONE);
                            daysLeft.setText(dayLeft + "D: ");
                            hrsLeft.setText(hrLeft + "H: ");
                            minLeft.setText(minsLeft + "M");
                            secLeft.setVisibility(View.GONE);

                        } else if ((monthsLeft.equals("00")) && (weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            weeks_left.setVisibility(View.GONE);
                            daysLeft.setText(dayLeft + "D: ");
                            hrsLeft.setText(hrLeft + "H: ");
                            minLeft.setText(minsLeft + "M");
                            secLeft.setVisibility(View.GONE);

                        } else if ((!monthsLeft.equals("00")) && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (!hrLeft.equals("00"))) {
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D: ");
                            hrsLeft.setText(hrLeft + "H");
                            months_left.setVisibility(View.GONE);
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                        } else if ((!monthsLeft.equals("00")) && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (hrLeft.equals("00"))) {
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D: ");
                            hrsLeft.setText(hrLeft + "H");
                            months_left.setVisibility(View.GONE);
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                        } else if ((monthsLeft.equals("00")) && (weeksLeft.equals("00")) && (dayLeft.equals("00")) && (!hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            weeks_left.setVisibility(View.GONE);
                            daysLeft.setVisibility(View.GONE);
                            hrsLeft.setText(hrLeft + "H: ");
                            minLeft.setText(minsLeft + "M: ");
                            secLeft.setText(secondLeft + "S");
                        } else if ((monthsLeft.equals("00")) && (weeksLeft.equals("00")) && (dayLeft.equals("00")) && (hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            weeks_left.setVisibility(View.GONE);
                            daysLeft.setVisibility(View.GONE);
                            hrsLeft.setText(hrLeft + "H: ");
                            minLeft.setText(minsLeft + "M: ");
                            secLeft.setText(secondLeft + "S");
                        } else if ((!monthsLeft.equals("00")) && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (!hrLeft.equals("00"))) {
                            daysLeft.setVisibility(View.VISIBLE);
                            months_left.setVisibility(View.GONE);
                            weeks_left.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                            daysLeft.setText(dayLeft + "D: ");
                            hrsLeft.setText(hrLeft + "H: ");
                            minLeft.setText(minsLeft + "M");
                        } else if ((!monthsLeft.equals("00")) && (!weeksLeft.equals("00")) && (!dayLeft.equals("00")) && (hrLeft.equals("00"))) {
                            daysLeft.setVisibility(View.VISIBLE);
                            months_left.setVisibility(View.GONE);
                            weeks_left.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                            daysLeft.setText(dayLeft + "D: ");
                            hrsLeft.setText(hrLeft + "H: ");
                            minLeft.setText(minsLeft + "M");
                        } else if ((monthsLeft.equals("00")) && (weeksLeft.equals("00")) && (dayLeft.equals("00")) && (!hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            weeks_left.setVisibility(View.GONE);
                            daysLeft.setVisibility(View.GONE);
                            hrsLeft.setText(hrLeft + "H: ");
                            minLeft.setText(minsLeft + "M: ");
                            secLeft.setText(secondLeft + "S");
                        } else if ((monthsLeft.equals("00")) && (weeksLeft.equals("00")) && (dayLeft.equals("00")) && (hrLeft.equals("00"))) {
                            months_left.setVisibility(View.GONE);
                            weeks_left.setVisibility(View.GONE);
                            daysLeft.setVisibility(View.GONE);
                            hrsLeft.setText(hrLeft + "H: ");
                            minLeft.setText(minsLeft + "M: ");
                            secLeft.setText(secondLeft + "S");
                        } else {
                            months_left.setVisibility(View.GONE);
                            weeks_left.setText(weeksLeft + "W: ");
                            daysLeft.setText(dayLeft + "D: ");
                            hrsLeft.setText(hrLeft + "H");
                            minLeft.setVisibility(View.GONE);
                            secLeft.setVisibility(View.GONE);
                        }
                    } else {
                        textViewGone();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }
    private void textViewGone() {
        months_left.setVisibility(View.GONE);
        weeks_left.setVisibility(View.GONE);
        daysLeft.setVisibility(View.GONE);
        hrsLeft.setVisibility(View.GONE);
        minLeft.setVisibility(View.GONE);
        secLeft.setVisibility(View.GONE);
    }
}
