package com.example.cgv.ex;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, OnDateSelectedListener, OnMonthChangedListener{

    protected BottomNavigationView navigationView;
    private String[] happy = new String[]{"2018/12/15" , "2018/12/17" , "2018/12/20"};
    private String[] ksop = new String[]{"2018/12/5" , "2018/11/10" , "2018/11/17"};
    //위와 같은 형식으로, 자신이 달력에 추가할 날짜들을 적으세요.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_calendar) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (itemId == R.id.navigation_other) {
                startActivity(new Intent(this, OtherActivity.class));
            } else if (itemId == R.id.navigation_game) {
                startActivity(new Intent(this, GameActivity.class));
            }
            finish();
        }, 300);
        return true;
    }

}