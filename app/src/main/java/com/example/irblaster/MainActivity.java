package com.example.irblaster;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button Short_BTN = (Button) findViewById(R.id.Short_BTN);
        final int shortpatern[] = {1242,9087,8908,1234,1234,1234,12347,1234,1243};//define patterns
        final int medpatern[] = {1242,9087,1000,1234,1234,1234,12347,1234,1000};
        final int longpatern[] = {1242,9087,2000,1234,1234,1234,12347,2234,2000};
        final int modechangepatern[] = {1982,2087,2000,1234,5234,1234,7534,1234,1210};
        final int cancerchangepatern[] = {1982,7287,7200,1234,5234,1234,1534,1234,1210};
        final int mode = 1;
        Button Change_BTN = (Button) findViewById(R.id.SwitchBTN);
        Button Long_BTN = (Button) findViewById(R.id.Long_BTN);
        Button Medium_BTN = (Button) findViewById(R.id.Medium_BTN);
        TextView mode1 = (TextView) findViewById(R.id.Mode_LBL);
        Button cancel_BTN = (Button) findViewById(R.id.cancel_BTN);
        mode1.setText("Mode: Toilet");

        final ConsumerIrManager mCIR = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);

        Short_BTN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCIR.transmit(38400,shortpatern);
        }
        });

        Medium_BTN.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){mCIR.transmit(38400,medpatern);
            }
        });

        Long_BTN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCIR.transmit(38400,longpatern);
            }
        });

        Change_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCIR.transmit(38400,modechangepatern);
                TextView mode1 = (TextView) findViewById(R.id.Mode_LBL);
                CharSequence lable = mode1.getText();
                if (lable == "Mode: Toilet"){
                    mode1.setText("Mode: Metting Room");
                }else{
                    mode1.setText("Mode: Toilet");
                }

            }
        });
        cancel_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 100; i++) {
                    mCIR.transmit(38400,cancerchangepatern);
                }

            }
        });








    }
}
