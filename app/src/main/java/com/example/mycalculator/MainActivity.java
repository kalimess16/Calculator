package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String SPACE = "", ADD = "+", SUB = "-", MUL = "X", DIV = "/", MOD = "%";

    private TextView mFirstNumber, mCal, mResult, mSecondNumber;
    private String mValueOfFirst = "", mValueOfSecond = "", mShowResult = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
    }

    private void anhXa() {
        mFirstNumber = findViewById(R.id.text_Numbers);
        mSecondNumber = findViewById(R.id.text_temp);
        mCal = findViewById(R.id.text_cal);
        mResult = findViewById(R.id.text_Result);
    }

    private void myCalculator() {
        switch (mCal.getText().toString()) {
            case SPACE:
                mShowResult = mValueOfFirst;
                break;
            case ADD:
                mShowResult = String.valueOf(Double.parseDouble(mValueOfSecond) + Double.parseDouble(mValueOfFirst));
                break;
            case SUB:
                mShowResult = String.valueOf(Double.parseDouble(mValueOfSecond) - Double.parseDouble(mValueOfFirst));
                break;
            case MUL:
                mShowResult = String.valueOf(Double.parseDouble(mValueOfSecond) * Double.parseDouble(mValueOfFirst));
                break;
            case DIV:
                if (mValueOfFirst.equals("0")) {
                    Toast.makeText(this, "Error by 0", Toast.LENGTH_SHORT).show();
                } else {
                    mShowResult = String.valueOf(
                            Double.parseDouble(mValueOfSecond) / Double.parseDouble(mValueOfFirst));
                }
                break;
            case MOD:
                mShowResult = String.valueOf(Double.parseDouble(mValueOfSecond) % Double.parseDouble(mValueOfFirst));
                break;
        }
    }

    /**
     * truyền data vào màng hình
     */
    private void setNumber(String numbers) {
        if (!mValueOfFirst.equals("0")) mValueOfFirst += numbers; // loại bỏ số 0 ở đầu
        if (!numbers.equals("0") && mValueOfFirst.equals("0")) mValueOfFirst = numbers;
        show();
    }

    private void show() {
        mFirstNumber.setText(mValueOfFirst);
        myCalculator();
        mResult.setText(mShowResult);
    }

    private void setCal(String cal) {
        switch (cal) {
            case ADD:
                handle();
                mCal.setText(ADD);
                break;
            case SUB:
                handle();
                mCal.setText(SUB);
                break;
            case MUL:
                handle();
                mCal.setText(MUL);
                break;
            case DIV:
                handle();
                mCal.setText(DIV);
                break;
            case MOD:
                handle();
                mCal.setText(MOD);
                break;
        }
    }

    private void handle() {
        mValueOfSecond = mShowResult;
        mSecondNumber.setText(mValueOfSecond);
        mValueOfFirst = "";
        mFirstNumber.setText(mValueOfFirst);
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
                opposite();
                break;
            case R.id.button_dot:
                funBtnDot();
                break;
        }
    }

    /**
     * button .
     */
    private void funBtnDot() {
        if (!mValueOfFirst.isEmpty()) {
            mValueOfFirst = mValueOfFirst + ".";
            show();
        } else {
            mValueOfFirst = "0.";
            show();
        }
    }

    /**
     * button chuyển đổi dấu +/-
     */
    private void opposite() {
        if (!mValueOfFirst.isEmpty()) {
            if (Double.parseDouble(mValueOfFirst) > 0) {
                mValueOfFirst = SUB + mValueOfFirst;
                show();
            } else if (Double.parseDouble(mValueOfFirst) == 0) {
                mValueOfFirst = "0";
                show();
            } else {
                mValueOfFirst = mValueOfFirst.substring(1, mValueOfFirst.length());
                show();
            }
        } else {
            Toast.makeText(this, R.string.Error, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Button Delete
     */
    private void cDelete() {
        if (!mValueOfFirst.isEmpty()) {
            // kiểm ra nếu nFirst về rỗng hay đó là số âm
            if (mValueOfFirst.length() == 1 || mValueOfFirst.length() == 2 && Double.parseDouble(mValueOfFirst) < 0) {
                mValueOfFirst = SPACE;
                if (SPACE.equals(mValueOfSecond)) { // kiểm tra trong lúc tính toán
                    mShowResult = "0";
                    mResult.setText(mShowResult);
                } else {
                    mShowResult = mValueOfSecond;
                    mResult.setText(mShowResult);
                }
            } else {
                mValueOfFirst = mValueOfFirst.substring(0, mValueOfFirst.length() - 1);
                myCalculator();
                mResult.setText(mShowResult);
            }
            mFirstNumber.setText(mValueOfFirst);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Button =
     */
    private void cTotal() {
        if (mValueOfSecond.equals(SPACE) && !mValueOfFirst.equals(SPACE)) {
            mShowResult = mValueOfSecond;
            mValueOfFirst = SPACE;
            mFirstNumber.setText(mValueOfFirst);
            mResult.setText(mShowResult);
        } else if (mValueOfFirst.equals(SPACE) && !mValueOfSecond.equals(SPACE)) {
            mShowResult = mValueOfSecond;
            mValueOfSecond = SPACE;
            mSecondNumber.setText(mValueOfSecond);
            mResult.setText(mValueOfFirst);
        } else {
            mValueOfSecond = SPACE;
            mValueOfSecond = SPACE;
            mCal.setText(SPACE);
            mFirstNumber.setText(mValueOfFirst);
            mSecondNumber.setText(mValueOfSecond);
            mResult.setText(mShowResult);
        }
    }

    /**
     * Button AC
     */
    private void cClear() {
        mValueOfFirst = "";
        mFirstNumber.setText(mValueOfFirst);
        mValueOfSecond = "";
        mSecondNumber.setText(mValueOfSecond);
        mCal.setText("");
        mShowResult = "0";
        mResult.setText(mShowResult);
    }
}
