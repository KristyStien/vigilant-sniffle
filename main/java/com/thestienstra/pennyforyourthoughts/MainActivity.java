package com.thestienstra.pennyforyourthoughts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.thestienstra.pennyforyourthoughts.R.id.donateButton;

public class MainActivity extends AppCompatActivity {

    private double money;
    private String output;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //load
        SharedPreferences mPref = this.getSharedPreferences("theMoneyValue", Context.MODE_PRIVATE);
        money = Double.parseDouble(mPref.getString("savedMoney", "0.00"));
        output = String.format("%.2f",this.money);
        displayMoney();
        displayButton();

    }

    public void displayMoney(){
        TextView moneyView = (TextView) findViewById(R.id.moneyText);
        moneyView.setText("$ " + output);


    }

    public void addPenny (View view){

            money = money + 0.01;
            output = String.format("%.2f",this.money);
            displayMoney();
            displayButton();

        //save
        SharedPreferences mPref = getSharedPreferences("theMoneyValue", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("savedMoney", output);
        editor.commit();

    }

    public void reset (View view){
        money = 0.0;
        output = "0.00";
        displayMoney();
        displayButton();
    }

    public void testButton (View view){
        money = 19.98;
        output = "19.98";
        displayMoney();
        displayButton();
    }

    public void donate (View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://interland3.donorperfect.net/weblink/weblink.aspx?name=vision&id=1"));
        startActivity(intent);
    }

    public void displayButton(){
        Button theButton = (Button) findViewById(donateButton);
        if (this.money > 20.00) {

            theButton.setVisibility(View.VISIBLE);
        } else {

            theButton.setVisibility(View.GONE);
        }

    }


}
