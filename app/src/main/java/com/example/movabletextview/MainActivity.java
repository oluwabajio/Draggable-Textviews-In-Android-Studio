package com.example.movabletextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int pressed_x, pressed_y;
    TextView tv1;
    String names[] = {"C", "L", "A", "S", "S"};
    String words[] = {"CLASS", "LONG", "ADEMOLA", "STUPID", "NICE"};

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      layout = (RelativeLayout) findViewById(R.id.linear);

        generateTv(5);

//        tv1 = (TextView) findViewById(R.id.tv1);
//        tv1.setOnTouchListener(mOnTouchListenerTv1);

//        String word = words[new Random().nextInt(words.length)];
//
//        char[] cArray = word.toCharArray();

//        for(int i=0;i<word.length();i++)
//        {
//            System.out.println(word.charAt(i));
//        }
    }

    private void generateTv(int namesLength) {

        //Get Screen width and height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        for (int i = 0; i < namesLength; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(width) + 0;
            int randomNumber2 = random.nextInt(height) + 0;


            TextView tv = new TextView(this);
            String name = names[i];
            tv.setText("" + names[i]);
            tv.setX(randomNumber);
            tv.setY(randomNumber2);
            tv.setTextSize(33);
            tv.setOnTouchListener(mOnTouchListenerTv2);
            layout.addView(tv);
            tv.setOnTouchListener(mOnTouchListenerTv2);
            Log.e("TTT", "name "+name);
        }
    }

    public final View.OnTouchListener mOnTouchListenerTv1 = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RelativeLayout.LayoutParams relativeLayoutParams = (RelativeLayout.LayoutParams) tv1.getLayoutParams();


            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("TAG", "@@@@ TV1 ACTION_UP");
                    // Where the user started the drag
                    pressed_x = (int) event.getRawX();
                    pressed_y = (int) event.getRawY();
                    Log.e("TTT", "down ");
                    break;

                case MotionEvent.ACTION_MOVE:
                    Log.d("TAG", "@@@@ TV1 ACTION_UP");
                    // Where the user's finger is during the drag
                    final int x = (int) event.getRawX();
                    final int y = (int) event.getRawY();

                    // Calculate change in x and change in y
                    int dx = x - pressed_x;
                    int dy = y - pressed_y;

                    // Update the margins
                    relativeLayoutParams.leftMargin += dx;
                    relativeLayoutParams.topMargin += dy;
                    v.setLayoutParams(relativeLayoutParams);

                    // Save where the user's finger was for the next ACTION_MOVE
                    pressed_x = x;
                    pressed_y = y;
                    Log.e("TTT", "move");
                    break;
                    
            }

            return true;
        }
    };

    public final View.OnTouchListener mOnTouchListenerTv2 = new View.OnTouchListener() {
        int prevX,prevY;
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final RelativeLayout.LayoutParams par=(RelativeLayout.LayoutParams)v.getLayoutParams();
            switch(event.getAction())
            {
                case MotionEvent.ACTION_MOVE:
                {
                    par.topMargin+=(int)event.getRawY()-prevY;
                    prevY=(int)event.getRawY();
                    par.leftMargin+=(int)event.getRawX()-prevX;
                    prevX=(int)event.getRawX();
                    v.setLayoutParams(par);
                    return true;
                }
                case MotionEvent.ACTION_UP:
                {
                    par.topMargin+=(int)event.getRawY()-prevY;
                    par.leftMargin+=(int)event.getRawX()-prevX;
                    v.setLayoutParams(par);
                    return true;
                }
                case MotionEvent.ACTION_DOWN:
                {
                    prevX=(int)event.getRawX();
                    prevY=(int)event.getRawY();
                    par.bottomMargin=-2*v.getHeight();
                    par.rightMargin=-2*v.getWidth();
                    v.setLayoutParams(par);
                    return true;
                }
            }
return false;
        }
    };
}
