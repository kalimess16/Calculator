package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtNumber1, txtCal, txtResult, txtNumber2;
    private String mFirst = "", mSecond = "", mShowR = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
    }

    private void anhXa() {
        txtNumber1 = findViewById(R.id.text_Numbers);
        txtNumber2 = findViewById(R.id.text_temp);
        txtCal = findViewById(R.id.text_cal);
        txtResult = findViewById(R.id.text_Result);
    }


    private void myCalculator() {
        switch (txtCal.getText().toString()) {
            case "":
                mShowR = mFirst;
                break;
            case "+":
                mShowR =
                        String.valueOf(Double.parseDouble(mSecond) + Double.parseDouble(mFirst));
                break;
            case "-":
                mShowR = String.valueOf(Double.parseDouble(mSecond) - Double.parseDouble(mFirst));
                break;
            case "X":
                mShowR = String.valueOf(Double.parseDouble(mSecond) * Double.parseDouble(mFirst));
                break;
            case "/":
                if (mFirst.equals("0"))
                    Toast.makeText(this, "Error by 0", Toast.LENGTH_SHORT).show();
                else mShowR =
                        String.valueOf(Double.parseDouble(mSecond) / Double.parseDouble(mFirst));
                break;
            case "%":
                mShowR = String.valueOf(Double.parseDouble(mSecond) % Double.parseDouble(mFirst));
                break;
        }
    }

    /**
     * truyền data vào màng hình
     */
    private void setNumber(String numbers) {
        if (!mFirst.equals("0")) mFirst += numbers; // loại bỏ số 0 ở đầu
        if (!numbers.equals("0") && mFirst.equals("0")) {
            mFirst = numbers;
            Log.d("TAG", mFirst);
        }
        txtNumber1.setText(mFirst);
        myCalculator();
        txtResult.setText(mShowR);
    }

    private void setCal(String cal) {
        switch (cal) {
            case "+":
                mSecond = mShowR;
                txtNumber2.setText(mSecond);
                mFirst = "";
                txtNumber1.setText(mFirst);
                txtCal.setText("+");
                break;
            case "-":
                mSecond = mShowR;
                txtNumber2.setText(mSecond);
                mFirst = "";
                txtNumber1.setText(mFirst);
                txtCal.setText("-");
                break;
            case "X":
                mSecond = mShowR;
                txtNumber2.setText(mSecond);
                mFirst = "";
                txtNumber1.setText(mFirst);
                txtCal.setText("x");
                break;
            case "/":
                mSecond = mShowR;
                txtNumber2.setText(mSecond);
                mFirst = "";
                txtNumber1.setText(mFirst);
                txtCal.setText("/");
                break;
            case "%":
                mSecond = mShowR;
                txtNumber2.setText(mSecond);
                mFirst = "";
                txtNumber1.setText(mFirst);
                txtCal.setText("%");
                break;
        }
    }

    /**
     * bắt dự kiện onclick
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_0:
                setNumber("0");
                break;
            case R.id.button_1:
                setNumber("1");
                break;
            case R.id.button_2:
                setNumber("2");
                break;
            case R.id.button_3:
                setNumber("3");
                break;
            case R.id.button_4:
                setNumber("4");
                break;
            case R.id.button_5:
                setNumber("5");
                break;
            case R.id.button_6:
                setNumber("6");
                break;
            case R.id.button_7:
                setNumber("7");
                break;
            case R.id.button_8:
                setNumber("8");
                break;
            case R.id.button_9:
                setNumber("9");
                break;
            case R.id.button_add:
                setCal("+");
                break;
            case R.id.button_sub:
                setCal("-");
                break;
            case R.id.button_mul:
                setCal("X");
                break;
            case R.id.button_div:
                setCal("/");
                break;
            case R.id.button_mod:
                setCal("%");
                break;
            case R.id.button_Result:
                cTotal();
                break;
            case R.id.button_clear:
                cClear();
                break;
            case R.id.image_delete:
                cDelete();
                break;
            case R.id.button_swap:
                cSwap();
                break;
            case R.id.button_dot:
                cDot();
                break;
        }
    }

    /**
     * button .
     */
    private void cDot() {
        if (!mFirst.isEmpty()) {
            mFirst = mFirst + ".";
            txtNumber1.setText(mFirst);
            myCalculator();
            txtResult.setText(mShowR);
        } else {
            mFirst = "0.";
            txtNumber1.setText(mFirst);
            myCalculator();
            txtResult.setText(mShowR);
        }
    }

    /**
     * button chuyển đổi dấu +/-
     */
    private void cSwap() {
        if (!mFirst.isEmpty()) {
            if (Double.parseDouble(mFirst) > 0) {
                mFirst = "-" + mFirst;
                txtNumber1.setText(mFirst);
                myCalculator();
                txtResult.setText(mShowR);
            } else if (Double.parseDouble(mFirst) == 0) {
                mFirst = "0";
                txtNumber1.setText(mFirst);
                myCalculator();
                txtResult.setText(mShowR);
            } else {
                mFirst = mFirst.substring(1, mFirst.length());
                txtNumber1.setText(mFirst);
                myCalculator();
                txtResult.setText(mShowR);
            }
        } else Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
    }

    /**
     * Button Delete
     */
    private void cDelete() {
        if (!mFirst.isEmpty()) {
            // kiểm ra nếu nFirst về rỗng hay đó là số âm
            if (mFirst.length() == 1 || mFirst.length() == 2 && Double.parseDouble(mFirst) < 0) {
                mFirst = "";
                if ("".equals(mSecond)) { // kiểm tra trong lúc tính toán
                    mShowR = "0";
                    txtResult.setText(mShowR);
                } else {
                    mShowR = mSecond;
                    txtResult.setText(mShowR);
                }
            } else {
                mFirst = mFirst.substring(0, mFirst.length() - 1);
                myCalculator();
                txtResult.setText(mShowR);
            }
            txtNumber1.setText(mFirst);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Button =
     */
    private void cTotal() {
        if (mSecond.equals("") && !mFirst.equals("")) {
            mShowR = mFirst;
            mFirst = "";
            txtNumber1.setText(mFirst);
            txtResult.setText(mShowR);
        } else if (mFirst.equals("") && !mSecond.equals("")) {
            mShowR = mSecond;
            mSecond = "";
            txtNumber2.setText(mSecond);
            txtResult.setText(mFirst);
        } else {
            mFirst = "";
            mSecond = "";
            txtCal.setText("");
            txtNumber1.setText(mFirst);
            txtNumber2.setText(mSecond);
            txtResult.setText(mShowR);
        }
    }

    /**
     * Button AC
     */
    private void cClear() {
        mFirst = "";
        txtNumber1.setText(mFirst);
        mSecond = "";
        txtNumber2.setText(mSecond);
        txtCal.setText("");
        mShowR = "0";
        txtResult.setText(mShowR);
    }

}
