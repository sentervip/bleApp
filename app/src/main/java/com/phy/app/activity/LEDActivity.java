package com.phy.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.phy.app.R;
import com.phy.app.app.PHYApplication;
import com.phy.app.ble.bean.LightColor;
import com.warkiz.widget.IndicatorSeekBar;

/**
 * LEDActivity
 *
 * @author:zhoululu
 * @date:2018/4/15
 */

public class LEDActivity extends EventBusBaseActivity implements IndicatorSeekBar.OnSeekBarChangeListener{

    private IndicatorSeekBar rBar,gBar,bBar;
    private int rData,gData,bData;
    private boolean isRRunning,isGRunning,isBRunning;

    private static final int RTAG = 1;
    private static final int GTAG = 2;
    private static final int BTAG = 3;
    private static final int ColorTag = 40;
    private static final int ModelTag = 60;
    private static final int INTERVAL = 300;
    private static int ModeType = 0;
    private Button btnRed,btnOrange;
    private Button btnM1,btnM2;



    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
			switch(msg.what){
				case RTAG:
					PHYApplication.getBandUtil().ledSetting(rData,LightColor.RED);
					if(isRRunning){
						handler.sendEmptyMessageDelayed(RTAG,INTERVAL);
					}
					break;
				case GTAG:
					PHYApplication.getBandUtil().ledSetting(gData,LightColor.GREE);
					if(isGRunning){
						handler.sendEmptyMessageDelayed(GTAG,INTERVAL);
					}
				case BTAG:
					PHYApplication.getBandUtil().ledSetting(bData,LightColor.BLUE);
					if(isBRunning){
						handler.sendEmptyMessageDelayed(BTAG,INTERVAL);
					}
					break;
				case ColorTag:
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Color1); // by aizj add
					break;
				case (ColorTag+1):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Color2); break;
				case ( ColorTag+2):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Color3);break;
                case (ColorTag+3):
                    PHYApplication.getBandUtil().ledSetting(0,LightColor.Color4); // by aizj add
                    break;
                case (ColorTag+4):
                    PHYApplication.getBandUtil().ledSetting(0,LightColor.Color5); break;
                case ( ColorTag+5):
                    PHYApplication.getBandUtil().ledSetting(0,LightColor.Color6);break;
					
					
				case (ModelTag):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Model1);break;
				case ( ModelTag+1):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Model2);break;
				case (ModelTag+2):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Model3);break;
                case (ModelTag+3):
                    PHYApplication.getBandUtil().ledSetting(0,LightColor.Model4);break;
                case ( ModelTag+4):
                    PHYApplication.getBandUtil().ledSetting(0,LightColor.Model5);break;
                case (ModelTag+5):
                    PHYApplication.getBandUtil().ledSetting(0,LightColor.Model6);break;
                case (ModelTag+6):
                    PHYApplication.getBandUtil().ledSetting(0,LightColor.ModelOff);break;
                default: break;
              }//switch
		}
    };

    @Override
    public void initComponent() {
        setTitle(R.string.label_console);

        rBar = findViewById(R.id.r_seekbar);
        gBar = findViewById(R.id.g_seekbar);
        bBar = findViewById(R.id.b_seekbar);

        rBar.setOnSeekChangeListener(this);
        bBar.setOnSeekChangeListener(this);
        gBar.setOnSeekChangeListener(this);
        

        //btnRed = (Button) findViewById(R.id.btnRed);
        btnM1 = (Button) findViewById(R.id.btnM5);
        //btnM1.setBackground(getResources().getDrawable());

        //corlor btn 6
        findViewById(R.id.BtnColor0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ColorTag0 ");
                handler.sendEmptyMessageDelayed(ColorTag,INTERVAL);
            }
        });
        findViewById(R.id.BtnColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ColorTag1 ");
                handler.sendEmptyMessageDelayed(ColorTag+1,INTERVAL);
            }
        });
        findViewById(R.id.BtnColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ColorTag2 ");
                handler.sendEmptyMessageDelayed(ColorTag+2,INTERVAL);
            }
        });
        findViewById(R.id.BtnColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ColorTag3 ");
                handler.sendEmptyMessageDelayed(ColorTag+3,INTERVAL);
            }
        });
        findViewById(R.id.BtnColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ColorTag4 ");
                handler.sendEmptyMessageDelayed(ColorTag+4,INTERVAL);
            }
        });
        findViewById(R.id.BtnColor5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ColorTag5 ");
                handler.sendEmptyMessageDelayed(ColorTag+5,INTERVAL);
            }
        });


        //model btn 7
        findViewById(R.id.btnM0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ModelTag0 ");
                handler.sendEmptyMessageDelayed(ModelTag,INTERVAL);
            }
        });
//            android:background="@style/btn_select_tyle"
        findViewById(R.id.btnM1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ModelTag1 ");
                handler.sendEmptyMessageDelayed(ModelTag+1,INTERVAL);
            }
        });
        findViewById(R.id.btnM2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ModelTag2 ");
                handler.sendEmptyMessageDelayed(ModelTag+2,INTERVAL);
            }
        });
        findViewById(R.id.btnM3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ModelTag3 ");
                handler.sendEmptyMessageDelayed(ModelTag+3,INTERVAL);
            }
        });
        findViewById(R.id.btnM4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ModelTag4 ");
                handler.sendEmptyMessageDelayed(ModelTag+4,INTERVAL);
            }
        });
        findViewById(R.id.btnM5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ModelTag5 ");
                handler.sendEmptyMessageDelayed(ModelTag+5,INTERVAL);
            }
        });
        findViewById(R.id.btnOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ModelOFF ");
                handler.sendEmptyMessageDelayed(ModelTag+6,INTERVAL);
            }
        });
    }




    @Override
    public int getContentLayout() {
        return R.layout.activity_led;
    }

    @Override
    public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
        if(seekBar.getId() == R.id.r_seekbar){
            rData = progress;
        }else if(seekBar.getId() == R.id.g_seekbar){
            gData = progress;
        }else if(seekBar.getId() == R.id.b_seekbar){
            bData = progress;
        }
    }

    @Override
    public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {
    }

    @Override
    public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
        if(seekBar.getId() == R.id.r_seekbar){
            isRRunning = true;
            handler.sendEmptyMessageDelayed(RTAG,INTERVAL);
        }else if(seekBar.getId() == R.id.g_seekbar){
            isGRunning = true;
            handler.sendEmptyMessageDelayed(GTAG,INTERVAL);
        }else if(seekBar.getId() == R.id.b_seekbar){
            isBRunning = true;
            handler.sendEmptyMessageDelayed(BTAG,INTERVAL);
        }
    }

    @Override
    public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

        if(seekBar.getId() == R.id.r_seekbar){
            isRRunning = false;
            handler.sendEmptyMessage(RTAG);
        }else if(seekBar.getId() == R.id.g_seekbar){
            isGRunning = false;
            handler.sendEmptyMessage(GTAG);
        }else if(seekBar.getId() == R.id.b_seekbar){
            isBRunning = false;
            handler.sendEmptyMessage(BTAG);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

       handler.removeCallbacksAndMessages(null);
    }
}
