package com.example.tmnt.bottomwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WindowManager manager;

    private RelativeLayout rePayWay;
    private LinearLayout LinPayWay;
    private RelativeLayout rePayDetail;
    private ListView lv;
    private RelativeLayout reBalance;
    private Button btnPay;
    private EditText gridPasswordView;
    private LinearLayout linPass;
    View view;
    boolean isActive;
    private static final String TAG = "MainActivity";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView= (TextView) findViewById(R.id.text);

  Window window=getWindow();


        manager= (WindowManager) getSystemService(WINDOW_SERVICE);

        final WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height=manager.getDefaultDisplay().getHeight()*3/5;

        layoutParams.gravity= Gravity.BOTTOM;

        layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        layoutParams.dimAmount=1.5f;

        layoutParams.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                isActive=false;
//                manager.addView(getBottomView(),layoutParams);
                isActive=true;
                PayDetailFragment payDetailFragment=new PayDetailFragment();
                payDetailFragment.show(getSupportFragmentManager(),"payDetailFragment");
            }
        });


    }

    public View getBottomView(){
       view = LayoutInflater.from(MainActivity.this).inflate(R.layout.bottom_lay,null,false);
            rePayWay = (RelativeLayout) view.findViewById(R.id.re_pay_way);
            rePayDetail = (RelativeLayout) view.findViewById(R.id.re_pay_detail);//付款详情
            LinPayWay = (LinearLayout) view.findViewById(R.id.lin_pay_way);//付款方式
            lv = (ListView) view.findViewById(R.id.lv_bank);//付款方式（银行卡列表）
            reBalance = (RelativeLayout) view.findViewById(R.id.re_balance);//付款方式（余额）
            btnPay = (Button) view.findViewById(R.id.btn_confirm_pay);
            gridPasswordView = (EditText) view.findViewById(R.id.pass_view);
            linPass = (LinearLayout)view.findViewById(R.id.lin_pass);
       final Animation slide_left_to_left = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_left_to_left);
        final  Animation slide_right_to_left = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_right_to_left);
        final  Animation slide_left_to_right = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_left_to_right);
        final  Animation slide_left_to_left_in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_left_to_left_in);
            rePayWay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rePayDetail.startAnimation(slide_left_to_left);
                    rePayDetail.setVisibility(View.GONE);
                    LinPayWay.startAnimation(slide_right_to_left);
                    LinPayWay.setVisibility(View.VISIBLE);
                }
            });
            reBalance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rePayDetail.startAnimation(slide_left_to_left_in);
                    rePayDetail.setVisibility(View.VISIBLE);
                    LinPayWay.startAnimation(slide_left_to_right);
                    LinPayWay.setVisibility(View.GONE);
                    Log.i(TAG, "onClick: re_balance");
                }
            });
            btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rePayDetail.startAnimation(slide_left_to_left);
                    rePayDetail.setVisibility(View.GONE);
                    linPass.startAnimation(slide_right_to_left);
                    linPass.setVisibility(View.VISIBLE);
                    Log.i(TAG, "onClick: btn_pay");
                }
            });
        return view;
    }


    @Override
    public void onBackPressed() {
         super.onBackPressed();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            if (event.getY()<=manager.getDefaultDisplay().getHeight()/3){
                if (!isActive){
                    manager.removeView(view);
                    isActive=true;
                }
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
                if (!isActive){
                    manager.removeView(view);
                    isActive=true;
                }
            else {
                finish();
                }
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
