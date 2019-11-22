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
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Color0); // by aizj add
					break;
				case (ColorTag+1):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Color1); break;
				case ( ColorTag+2):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Color2);break;
					
					
				case (ModelTag):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Model0);break;
				case ( ModelTag+1):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Model1);break;
				case (ModelTag+2):
					PHYApplication.getBandUtil().ledSetting(0,LightColor.Model2);break;
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
        //btnM1 = (Button) findViewById(R.id.btnM1);
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
        findViewById(R.id.btnM0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:ModelTag0 ");
                handler.sendEmptyMessageDelayed(ModelTag,INTERVAL);
            }
        });

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
