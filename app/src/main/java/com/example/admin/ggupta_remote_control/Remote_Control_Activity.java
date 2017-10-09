package com.example.admin.ggupta_remote_control;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Remote_Control_Activity extends AppCompatActivity {

    String channelNumber = "";
    boolean isRemoteOff = true;

    HashMap<String,String> hsBtnNum = new HashMap<String,String>();    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote__control_);

        EnableDisableRemote(!isRemoteOff);

        // filling values in hashmap for number buttons
        hsBtnNum.put("btnnum0","0");
        hsBtnNum.put("btnnum1","1");
        hsBtnNum.put("btnnum2","2");
        hsBtnNum.put("btnnum3","3");
        hsBtnNum.put("btnnum4","4");
        hsBtnNum.put("btnnum5","5");
        hsBtnNum.put("btnnum6","6");
        hsBtnNum.put("btnnum7","7");
        hsBtnNum.put("btnnum8","8");
        hsBtnNum.put("btnnum9","9");
        hsBtnNum.put("btnfav1","222");
        hsBtnNum.put("btnfav2","333");
        hsBtnNum.put("btnfav3","444");
        hsBtnNum.put("btnincChannel","++");
        hsBtnNum.put("btndecChannel","--");

        final Switch swtPower = (Switch) findViewById(R.id.swtchPower);
        final SeekBar seekbarVolume = (SeekBar) findViewById(R.id.seekbarVolume);

        final TextView tvPowerVal = (TextView) findViewById(R.id.powerVal);
        final TextView tvvolumeVal = (TextView) findViewById(R.id.volumeVal);
        final TextView tvChannelVal = (TextView) findViewById(R.id.channelVal);

        final Button btnnum0 = (Button) findViewById(R.id.btnnum0);
        final Button btnnum1 = (Button) findViewById(R.id.btnnum1);
        final Button btnnum2 = (Button) findViewById(R.id.btnnum2);
        final Button btnnum3 = (Button) findViewById(R.id.btnnum3);
        final Button btnnum4 = (Button) findViewById(R.id.btnnum4);
        final Button btnnum5 = (Button) findViewById(R.id.btnnum5);
        final Button btnnum6 = (Button) findViewById(R.id.btnnum6);
        final Button btnnum7 = (Button) findViewById(R.id.btnnum7);
        final Button btnnum8 = (Button) findViewById(R.id.btnnum8);
        final Button btnnum9 = (Button) findViewById(R.id.btnnum9);
        final Button btnfav1 = (Button) findViewById(R.id.btnfav1);
        final Button btnfav2 = (Button) findViewById(R.id.btnfav2);
        final Button btnfav3 = (Button) findViewById(R.id.btnfav3);
        final Button btnincChannel = (Button) findViewById(R.id.btnincChannel);
        final Button btndecChannel = (Button) findViewById(R.id.btndecChannel);
                
        // handle power switch on-off event
        swtPower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String val = swtPower.isChecked() ? "ON" : "OFF";
                tvPowerVal.setText(val);
                channelNumber = "";
                if(swtPower.isChecked()){
                    EnableDisableRemote(true);
                    tvChannelVal.setText("187");
                }
                else{
                    EnableDisableRemote(false);
                    tvChannelVal.setText("0");
                }
            }
        });
        
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                int val = 0;
                TextView tvChannelVal = (TextView) findViewById(R.id.channelVal);
                int num = Integer.parseInt(tvChannelVal.getText().toString());

                if(!(btn.getTag().toString().contains("fav")) && !(btn.getTag().toString().contains("++"))
                        && !(btn.getTag().toString().contains("--"))) {
                    val = Integer.parseInt(btn.getText().toString());
                }
                SetChannelNumber(btn,val,tvChannelVal,num);
            }
        };
        
        btnnum0.setOnClickListener(listener);
        btnnum1.setOnClickListener(listener);
        btnnum2.setOnClickListener(listener);
        btnnum3.setOnClickListener(listener);
        btnnum4.setOnClickListener(listener);
        btnnum5.setOnClickListener(listener);
        btnnum6.setOnClickListener(listener);
        btnnum7.setOnClickListener(listener);
        btnnum8.setOnClickListener(listener);
        btnnum9.setOnClickListener(listener);
        btnfav1.setOnClickListener(listener);
        btnfav2.setOnClickListener(listener);
        btnfav3.setOnClickListener(listener);
        btnincChannel.setOnClickListener(listener);
        btndecChannel.setOnClickListener(listener);

       seekbarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvvolumeVal.setText(Integer.toString(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        seekbarVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void SetChannelNumber(Button btn,int val, TextView tvChannelVal, int num ){

            switch (btn.getTag().toString()){
                case "btn0":
                case "btn1":
                case "btn2":
                case "btn3":
                case "btn4":
                case "btn5":
                case "btn6":
                case "btn7":
                case "btn8":
                case "btn9":
                    //channelNumber += val;
                    if(channelNumber.length() == 2) {
                        channelNumber += val;

                        // handling "001"
                        if(channelNumber.charAt(0) == '0' && channelNumber.charAt(1) == '0'){
                            channelNumber = channelNumber.substring(2);
                            tvChannelVal.setText(channelNumber);
                            channelNumber = "";
                            break;
                        }
                        else if(channelNumber.charAt(0) == '0'){ // handling "025"
                            channelNumber = channelNumber.substring(1);
                            tvChannelVal.setText(channelNumber);
                            channelNumber = "";
                            break;
                        }
                        tvChannelVal.setText(channelNumber);
                    }
                    else{
                        channelNumber += val;

                    }
                    if(channelNumber.length() == 3) {
                        channelNumber = "";
                    }
                    break;
                case "btnfav1":
                        tvChannelVal.setText("222");
                        break;
                case "btnfav2":
                        tvChannelVal.setText("333");
                        break;
                case "btnfav3":
                        tvChannelVal.setText("444");
                        break;
                case "btn++":
                    num = num == 999 ? 0 : num +1;
                    tvChannelVal.setText(String.format("%d",num));
                    break;
                case "btn--":
                    num = Integer.parseInt(tvChannelVal.getText().toString());
                    num = num == 0 ? 999 : num - 1;
                    tvChannelVal.setText(String.format("%d",num));
                    break;
                default:
                    tvChannelVal.setText("0");
            }
    }


    protected void EnableDisableRemote(boolean condition){

         SeekBar seekbarVolume = (SeekBar) findViewById(R.id.seekbarVolume);

         Button btnnum0 = (Button) findViewById(R.id.btnnum0);
         Button btnnum1 = (Button) findViewById(R.id.btnnum1);
         Button btnnum2 = (Button) findViewById(R.id.btnnum2);
         Button btnnum3 = (Button) findViewById(R.id.btnnum3);
         Button btnnum4 = (Button) findViewById(R.id.btnnum4);
         Button btnnum5 = (Button) findViewById(R.id.btnnum5);
         Button btnnum6 = (Button) findViewById(R.id.btnnum6);
         Button btnnum7 = (Button) findViewById(R.id.btnnum7);
         Button btnnum8 = (Button) findViewById(R.id.btnnum8);
         Button btnnum9 = (Button) findViewById(R.id.btnnum9);
         Button btnfav1 = (Button) findViewById(R.id.btnfav1);
         Button btnfav2 = (Button) findViewById(R.id.btnfav2);
         Button btnfav3 = (Button) findViewById(R.id.btnfav3);
         Button btnincChannel = (Button) findViewById(R.id.btnincChannel);
         Button btndecChannel = (Button) findViewById(R.id.btndecChannel);
        
        seekbarVolume.setEnabled(condition);
        btnnum0.setEnabled(condition);
        btnnum1.setEnabled(condition);
        btnnum2.setEnabled(condition);
        btnnum3.setEnabled(condition);
        btnnum4.setEnabled(condition);
        btnnum5.setEnabled(condition);
        btnnum6.setEnabled(condition);
        btnnum7.setEnabled(condition);
        btnnum8.setEnabled(condition);
        btnnum9.setEnabled(condition);
        btnfav1.setEnabled(condition);
        btnfav2.setEnabled(condition);
        btnfav3.setEnabled(condition);
        btnincChannel.setEnabled(condition);
        btndecChannel.setEnabled(condition);
    }

}
