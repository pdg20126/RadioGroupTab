package com.smyhvae.radiogrouptabdemo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class MainActivity extends FatherBarActivity {
    ToggleButton tb;
    EditText et;
    RelativeLayout re;
    @Override
    public void child(Bundle savedInstanceState) {
        setContentView(R.layout.layout_login);
        re= (RelativeLayout) findViewById(R.id.back);
        re.setBackgroundResource(R.drawable.login7);
        tb= (ToggleButton) findViewById(R.id.tgbtn_show_psw);
        et= (EditText) findViewById(R.id.edit_psw);
        et.setInputType(0x81);
        Toolbar t = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(t);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    et.setInputType(0x90);
                }else {
                    et.setInputType(0x81);
                }
            }
        });
    }

}
