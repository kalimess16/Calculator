package com.example.mycalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String SPACE = "", ADD = "+", SUB = "-", MUL = "X", DIV = "/", MOD = "%";

    private TextView mNumber1, mCal, mResult, mNumber2;
    private String mFirst = "", mSecond = "", mShowR = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
    }

    private void anhXa() {
        mNumber1 = findViewById(R.id.text_Numbers);
        mNumber2 = findViewById(R.id.text_temp);
        mCal = findViewById(R.id.text_cal);
        mResult = findViewById(R.id.text_Result);
    }

    private void myCalculator() {
        switch (mCal.getText().toString()) {
            case SPACE:
                mShowR = mFirst;
                break;
            case ADD:
                mShowR = String.valueOf(Double.parseDouble(mSecond) + Double.parseDouble(mFirst));
                break;
            case SUB:
                mShowR = String.valueOf(Double.parseDouble(mSecond) - Double.parseDouble(mFirst));
                break;
            case MUL:
                mShowR = String.valueOf(Double.parseDouble(mSecond) * Double.parseDouble(mFirst));
                break;
            case DIV:
                if (mFirst.equals("0")) {
                    Toast.makeText(this, "Error by 0", Toast.LENGTH_SHORT).show();
                } else {
                    mShowR = String.valueOf(
                            Double.parseDouble(mSecond) / Double.parseDouble(mFirst));
                }
                break;
            case MOD:
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
        show();
    }

    private void show() {
        mNumber1.setText(mFirst);
        myCalculator();
        mResult.setText(mShowR);
    }

    private void setCal(String cal) {
        switch (cal) {
            case ADD:
                mHand();
                mCal.setText(ADD);
                break;
            case SUB:
                mHand();
                mCal.setText(SUB);
                break;
            case MUL:
                mHand();
                mCal.setText(MUL);
                break;
            case DIV:
                mHand();
                mCal.setText(DIV);
                break;
            case MOD:
                mHand();
                mCal.setText(MOD);
                break;
        }
    }

    private void mHand() {
        mSecond = mShowR;
        mNumber2.setText(mSecond);
        mFirst = "";
        mNumber1.setText(mFirst);
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
                setCal(ADD);
                break;
            case R.id.button_sub:
                setCal(SUB);
                break;
            case R.id.button_mul:
                setCal(MUL);
                break;
            case R.id.button_div:
                setCal(DIV);
                break;
            case R.id.button_mod:
                setCal(MOD);
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
            show();
        } else {
            mFirst = "0.";
            show();
        }
    }

    /**
     * button chuyển đổi dấu +/-
     */
    private void cSwap() {
        if (!mFirst.isEmpty()) {
            if (Double.parseDouble(mFirst) > 0) {
                mFirst = SUB + mFirst;
                show();
            } else if (Double.parseDouble(mFirst) == 0) {
                mFirst = "0";
                show();
            } else {
                mFirst = mFirst.substring(1, mFirst.length());
                show();
            }
        } else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Button Delete
     */
    private void cDelete() {
        if (!mFirst.isEmpty()) {
            // kiểm ra nếu nFirst về rỗng hay đó là số âm
            if (mFirst.length() == 1 || mFirst.length() == 2 && Double.parseDouble(mFirst) < 0) {
                mFirst = SPACE;
                if (SPACE.equals(mSecond)) { // kiểm tra trong lúc tính toán
                    mShowR = "0";
                    mResult.setText(mShowR);
                } else {
                    mShowR = mSecond;
                    mResult.setText(mShowR);
                }
            } else {
                mFirst = mFirst.substring(0, mFirst.length() - 1);
                myCalculator();
                mResult.setText(mShowR);
            }
            mNumber1.setText(mFirst);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Button =
     */
    private void cTotal() {
        if (mSecond.equals(SPACE) && !mFirst.equals(SPACE)) {
            mShowR = mFirst;
            mFirst = SPACE;
            mNumber1.setText(mFirst);
            mResult.setText(mShowR);
        } else if (mFirst.equals(SPACE) && !mSecond.equals(SPACE)) {
            mShowR = mSecond;
            mSecond = SPACE;
            mNumber2.setText(mSecond);
            mResult.setText(mFirst);
        } else {
            mFirst = SPACE;
            mSecond = SPACE;
            mCal.setText(SPACE);
            mNumber1.setText(mFirst);
            mNumber2.setText(mSecond);
            mResult.setText(mShowR);
        }
    }

    /**
     * Button AC
     */
    private void cClear() {
        mFirst = "";
        mNumber1.setText(mFirst);
        mSecond = "";
        mNumber2.setText(mSecond);
        mCal.setText("");
        mShowR = "0";
        mResult.setText(mShowR);
    }
}
