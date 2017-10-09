package com.example.admin.ggupta_remote_control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Admin on 10/8/2017.
 */

public class RemoteControl_Activity extends Activity{

    String channelNumber = "";

    HashMap<String,String> hsBtnNum = new HashMap<String,String>();


    final Switch swtPower = (Switch) findViewById(R.id.swtchPower);
    final SeekBar seekbarVolume = (SeekBar) findViewById(R.id.seekbarVolume);

    final TextView tvPower = (TextView) findViewById(R.id.tvPower);
    final TextView tvVolume = (TextView) findViewById(R.id.speakerVolume);
    final TextView tvcurrentChannel = (TextView) findViewById(R.id.currentChannel);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote__control_);

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


        // handle power switch on-off event
        swtPower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String val = swtPower.isChecked() ? "ON" : "OFF";
                tvPower.setText(val);
                if(swtPower.isChecked()){
                    EnableDisableRemote(true);
                }
                else{
                    EnableDisableRemote(false);
                }
            }
        });


        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SetChannelNumber((Button) v);
            }
        };

        seekbarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvVolume.setText(progress);
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

    public void SetChannelNumber(Button btn){
        String val = hsBtnNum.get(btn.getText().toString());
        int num = Integer.parseInt(tvcurrentChannel.getText().toString());

        if( val != null){
            switch (val){
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    if(channelNumber.length()<3) {
                        channelNumber += val;
                    }
                    else{
                        channelNumber = val;
                    }
                    if(channelNumber.charAt(0) == '0' && channelNumber.charAt(1) == '0'){
                        channelNumber = channelNumber.substring(2);
                    }
                    else if(channelNumber.charAt(0) == '0'){
                        channelNumber = channelNumber.substring(1);
                    }
                    tvcurrentChannel.setText(channelNumber);
                    break;
                case "222":
                case "333":
                case "444":
                    channelNumber = val;
                    tvcurrentChannel.setText(channelNumber);
                    break;
                case "++":
                    num = num == 999 ? 0 : num +1;
                    tvcurrentChannel.setText(String.format("%d",num));
                    break;
                case "--":
                    num = Integer.parseInt(tvcurrentChannel.getText().toString());
                    num = num == 0 ? 999 : num - 1;
                    tvcurrentChannel.setText(String.format("%d",num));
                    break;
            }
        }
        else{
            tvcurrentChannel.setText("0");
        }
    }


    protected void EnableDisableRemote(boolean condition){

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
