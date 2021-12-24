package com.example.duvallstipcalculatorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
        implements TextWatcher, SeekBar.OnSeekBarChangeListener {

    //declare your variables for the widgets
    private EditText editTextBillAmount;
    private TextView textViewPercent;
    private TextView textViewBillAmount;
    private TextView totalTextView;
    private TextView tipTextView;


    //declare the variables for the calculations
    private double billAmount = 0.0;
    private double percent = .15;
    private double total = 0.0;
    //set the number formats to be used for the $ amounts , and % amounts
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add Listeners to Widgets
        tipTextView.addTextChangedListener(this);

        tipTextView = findViewById(R.id.tipTextView);

        totalTextView.addTextChangedListener(this);

        totalTextView = findViewById(R.id.totalTextView);


        editTextBillAmount = findViewById(R.id.editText_BillAmount);
 /*  Note: each View that will be retrieved using findViewById needs to map to a View with the matching id
Example: editTextBillAmount
Needs to map to a View with the following: android:id="@+id/editText_BillAmount
*/
        editTextBillAmount.addTextChangedListener(this);

        textViewBillAmount = findViewById(R.id.textView_BillAmount);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("MainActivity", "inside beforeTextChanged method: charSequence= " + charSequence);


    /*
    Note:   int i, int i1, and int i2
            represent start, before, count respectively
            The charSequence is converted to a String and parsed to a double for you
     */
        double d = new Double.valueOf(i.ToString());
        double d1 = new Double.valueOf(i1.ToString());
        double d2 = new Double.valueOf(i2.ToString());
        return (d,d1 ,d2);




    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("MainActivity", "inside onTextChanged method: charSequence= " + charSequence);
        //surround risky calculations with try catch (what if billAmount is 0 ?
        //charSequence is converted to a String and parsed to a double for you
        try {
            billAmount = Double.parseDouble(charSequence.toString()) / 100;
            Log.d("MainActivity", "Bill Amount = " + billAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setText on the textView
        textViewBillAmount.setText(currencyFormat.format(billAmount));
        //perform tip and total calculation and update UI by calling calculate
        calculate();//uncomment this line
    }


    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        percent = progress / 100; //calculate percent based on seeker value
        calculate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    // calculate and display tip and total amounts
    // calculate and display tip and total amounts
    private void calculate() {
        Log.d("MainActivity", "inside calculate method");
        //uncomment below

        // format percent and display in percentTextView
        textViewPercent.setText(percentFormat.format(percent));
        // calculate the tip and total
        double tip = billAmount * percent;
        //use the tip example to do the same for the Total
        // display tip and total formatted as currency
        //user currencyFormat instead of percentFormat to set the textViewTip
        tipTextView.setText(currencyFormat.format(tip));
        //use the tip example to do the same for the total
        totalTextView.setText(currencyFormat.format(total));
    }

}



}