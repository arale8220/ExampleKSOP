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
    //Main3에서는 이 부분을 알아서 추가 하셨겠죠?
    private String[] happy = new String[]{"2018/12/15" , "2018/12/17" , "2018/12/20"};
    private String[] ksop = new String[]{"2018/12/5" , "2018/11/10" , "2018/11/17"};
    //위와 같은 형식으로, 자신이 달력에 추가할 날짜들을 적으세요.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //onCreate함수 안에서, 이 액티비티가 시작될 때 어떠한 xml파일을 보이도록 할지 설정해줍니다.
        //어떤 이름의 layout을 설정해주어야 하나요?
        setContentView(R.layout.activity_main);

        //Navigation 에서 각 원소를 클릭하면 이동하게 만드는 함수입니다.
        // 클릭을 하면, 맨 아래의 onNavigationItemSelected 함수가 자동으로 실행됩니다.
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);


        MaterialCalendarView calendar = findViewById(R.id.calendarView);
        calendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)//어떤 요일을 한주의 시작으로 하고 싶은지 정할 수 있습니다.
                .setMinimumDate(CalendarDay.from(2017, 0, 1))//어떤 요일부터 띄워줄지, 결정할 수 있습니다.
                .setMaximumDate(CalendarDay.from(2030, 11, 31))//어떤 요일까지 띄워줄지, 결정할 수 있습니다.
                .setCalendarDisplayMode(CalendarMode.MONTHS)//달력을 한달만 띄울지, 한 주씩 띄울지 등을 선택할 수 있습니다.
                .commit();


        calendar.addDecorators( new SundayDecorator(), new SaturdayDecorator()); //각각의 날짜의 색깔, 크기, 글꼴 등을 선택할 수 있습니다.
        //아래의 SundayDecorator 등의 함수를 살펴보면서 확인해봅시다.

        //날짜를 선택하면 어떠한 기능을 할 지 구현하는 부분입니다.
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String fullDay = Integer.toString(date.getYear())+"/"+Integer.toString(date.getMonth()+1)+"/"+Integer.toString(date.getDay());

                //만약 날짜가 위의 List에 포함되어 있다면, 특정한 기능을 하도록 만들어줄 것입니다.
                //Toast는 문자형 알림을 잠시 띄어주는 역할을 합니다.

                //만약 특정 날짜가 ksop 리스트 안에 있다면, 그 날짜와 그 날짜에 케이솝 수업이 있다는 정보를 Toast를 이용해 띄워보세요.
                //예시는 앞에서 확인할 수 있습니다.
                if (Arrays.asList(ksop).contains(fullDay)) {Toast.makeText(MainActivity.this, fullDay+" is KSOP Day!", Toast.LENGTH_SHORT).show();}
                //위와 비슷하게, 날짜가 happy 리스트에 있을때, 그리고 아무런 리스트에도 없을 때의 정보를 띄워보세요.
                else if (Arrays.asList(happy).contains(fullDay)) {Toast.makeText(MainActivity.this, fullDay+" is happy day!", Toast.LENGTH_SHORT).show();}
                else  {Toast.makeText(MainActivity.this, "No Planned on this day", Toast.LENGTH_SHORT).show();}
            }
        });
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


    //자동으로 생성되는 함수
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

    }

    //자동으로 생성되는 함수
    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

    }




    //SundayDecorator는 달력을 확인하면서 Sunday에서만 특수한 성질을 부여합니다.
    private class SundayDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();

        public SundayDecorator() {
        }

        //어떤 날들만 선택하여 데코레이터를 사용할지 결정해줍니다.
        //return값의 데이타 형태는 무엇일까요?
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SUNDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));//일요일의 색을 빨간 색으로 바꿉니다.
        }
    }





    private class SaturdayDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();

        public SaturdayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SATURDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }
    }
}