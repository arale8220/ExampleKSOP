package com.example.cgv.ex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mFirstNumber, mSecondNumber, mThirdNumber, mFourthNumber, mFifthNumber, mPowerBall;
    private BottomNavigationView navigationView;
    private Button button;
    private String[] powerBalls = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
            "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35"  };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //onCreate함수 안에서, 이 액티비티가 시작될 때 어떠한 xml파일을 보이도록 할지 설정해줍니다.
        //어떤 이름의 layout을 설정해주어야 하나요?
        setContentView(R.layout.activity_game);

        //Navigation 에서 각 원소를 클릭하면 이동하게 만드는 함수입니다.
        // 클릭을 하면, 맨 아래의 onNavigationItemSelected 함수가 자동으로 실행됩니다.
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        //시작할 때, xml원소들을 찾아서 우리가 사용할 이름의 변수에 넣어주어야 합니다.
        //여기 파일에서 위에 TextView와 Button을 정의한 것들과, xml파일에서의 원소들을 비교하여, 어떤 id의 객체를 넣어야 할지 생각해보세요!
        button = (Button) findViewById(R.id.button); //예시
        mFirstNumber = (TextView) findViewById(R.id.firstNumber);
        mSecondNumber = (TextView) findViewById(R.id.secondNumber);
        mThirdNumber = (TextView) findViewById(R.id.thirdNumber);
        mFourthNumber = (TextView) findViewById(R.id.fourthNumber);
        mFifthNumber = (TextView) findViewById(R.id.fifthNumber);
        mPowerBall = (TextView) findViewById(R.id.powerBall);


        //버튼을 클릭하면 자동으로 generateRandomNumbers()라는 함수가 실행됩니다.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomNumbers();
            }
        });
    }

    //자동으로 실행되는 generateRandomNumbers가 어떤 기능을 하도록 만들어야 할까요?
    //goorm에서 예제로 사용해보았던 shuffle함수를 기억하세요!!
    public void generateRandomNumbers(){
        Collections.shuffle(Arrays.asList(powerBalls));
        mFirstNumber.setText(powerBalls[0]);
        mSecondNumber.setText(powerBalls[1]);
        mThirdNumber.setText(powerBalls[2]);
        mFourthNumber.setText(powerBalls[3]);
        mFifthNumber.setText(powerBalls[4]);
        mPowerBall.setText(powerBalls[5]);
    }


    //맨 아래의 네비게이션바의 원소가 클릭되었습니다. 각각의 원소가 클릭되었을 때에,
    //어떤 액티비티로 이동해야 할까요?
    //배웠던 조건문을 기억하면서, 선택된 아이템의 itemId가 R.id.~~가 같아야 어떤 액티비티를 실행되도록 해야할지, 고민해보세요!!
    //너무 어렵다면, 한줄만 선생님께 힌트를 달라고 요청해봅시다!
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