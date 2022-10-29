package com.example.calculator;

import android.os.Build;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.Math;

import static java.lang.Math.floor;
import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void zeroButton(View view) {
        updateText("0");
    }

    public void oneButton(View view) {
        updateText("1");
    }

    public void twoButton(View view) {
        updateText("2");
    }

    public void threeButton(View view) {
        updateText("3");
    }

    public void fourButton(View view) {
        updateText("4");
    }

    public void fiveButton(View view) {
        updateText("5");
    }

    public void sixButton(View view) {
        updateText("6");
    }

    public void sevenButton(View view) {
        updateText("7");
    }

    public void eightButton(View view) {
        updateText("8");
    }

    public void nineButton(View view) {
        updateText("9");
    }

    public void clearButton(View view) {
        display.setText("0");
    }

    public void exponentButton(View view) {
        updateText("^");
    }

    public void parenthesesButton(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().charAt(i) == '(') {
                openPar += 1;
            }
            if (display.getText().toString().charAt(i) == ')') {
                closedPar += 1;
            }
        }

        if (openPar == closedPar || display.getText().toString().charAt(textLen - 1) == '(') {
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if (closedPar < openPar && display.getText().toString().charAt(textLen - 1) != '(') {
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }

    public void divideButton(View view) {
        updateText("÷");
    }

    public void multiplyButton(View view) {
        updateText("×");
    }

    public void addButton(View view) {
        updateText("+");
    }

    public void subtractButton(View view) {
        updateText("-");
    }

    public void plusMinusButton(View view) {
        updateText("-");
    }

    public void pointButton(View view) {
        updateText(".");
    }

    public void percentButton(View view) {
        String line = display.getText().toString();
        int pointOp = line.indexOf(".");

        if (pointOp != -1) {
            float number = Float.parseFloat(line);
            float percentResult = number * 100;
            display.setText(Float.toString(percentResult));
        }
        else {
            display.setText("ERROR");
        }
    }

    public void squaredButton(View view) {
        String line = display.getText().toString();
        int pointOp = line.indexOf(".");

        if (pointOp == -1) {
            int number = Integer.parseInt(line);
            int squareResult = number * number;
            display.setText(Integer.toString(squareResult));
        }
        else {
            float number = Float.parseFloat(line);
            float squareResult = number * number;
            display.setText(Float.toString(squareResult));
        }
    }

    public void squareRootButton(View view) {
        String line = display.getText().toString();
        int pointOp = line.indexOf(".");

        if (pointOp == -1) {
            int number = Integer.parseInt(line);
            int rootResult = (int) Math.sqrt(number);
            display.setText(Integer.toString(rootResult));
        }
        else {
            float number = Float.parseFloat(line);
            float rootResult = (float) Math.sqrt(number);
            display.setText(Float.toString(rootResult));
        }
    }

    public void naturalLogButton(View view) {
        String line = display.getText().toString();
        float number = Float.parseFloat(line);
        float lnResult = (float) Math.log(number);
        display.setText(Float.toString(lnResult));
    }

    public void equalsButton(View view) {
        //Find the operator
        String line = display.getText().toString(); //9-1
        int plusOp = line.indexOf("+"); //-1
        int subOp = line.indexOf("-"); //1
        int multOp = line.indexOf("×"); //-1
        int divOp = line.indexOf("÷"); //ALT+246
        int expOp = line.indexOf("^");
        int pointOp = line.indexOf("."); //-1
        String result = "";
        int leftSide = 0, rightSide = 0, addResult, subResult, multResult;
        float divResult, fleftSide, frightSide;

        int leftPar = line.indexOf("(");
        int rightPar = line.indexOf(")");
        boolean pars = true;

        if (leftPar == -1 && rightPar == -1) {
            pars = false;
        }
        else if (leftPar == -1) {
            if (rightPar > 0) {
                result = "ERROR";
                pars = false;
            }
        }
        else if (rightPar == -1) {
            if (leftPar > 0) {
                result = "ERROR";
                pars = false;
            }
        }
        else {
            pars = true;
        }

        if (pars) { //(2+2)+2
            String parenLine = line.substring(leftPar+1, rightPar);
            int parenLinePoint = parenLine.indexOf(".");
            int newplusOp = parenLine.indexOf("+");
            int newsubOp = parenLine.indexOf("-");
            int newmultOp = parenLine.indexOf("×");
            int newdivOp = parenLine.indexOf("÷");
            int newexpOp = parenLine.indexOf("^");
            int newpointOp = parenLine.indexOf(".");
            if (newplusOp != -1) {
                if (parenLinePoint == -1){
                    leftSide = Integer.parseInt(parenLine.substring(0, newplusOp));
                    rightSide = Integer.parseInt(parenLine.substring(newplusOp+1, display.getText().length()));
                    addResult = leftSide + rightSide;
                    result = Integer.toString(addResult);
                }
                else {
                    fleftSide = Float.parseFloat(parenLine.substring(0, newplusOp));
                    frightSide = Float.parseFloat(parenLine.substring(newplusOp, display.getText().length()));
                    float faddResult = fleftSide + frightSide;
                    result = Float.toString(faddResult);
                }
            }
            //Subtracting
            else if (newsubOp != -1) {
                if (parenLinePoint == -1) {
                    leftSide = Integer.parseInt(parenLine.substring(0, newsubOp));
                    rightSide = Integer.parseInt(parenLine.substring(newsubOp+1, display.getText().length()));
                    subResult = leftSide - rightSide;
                    result = Integer.toString(subResult);
                }
                else {
                    fleftSide = Float.parseFloat(parenLine.substring(0, newsubOp));
                    frightSide = Float.parseFloat(parenLine.substring(newsubOp+1, display.getText().length()));
                    float fsubResult = fleftSide - frightSide;
                    result = Float.toString(fsubResult);
                }
            }
            else if (newmultOp != -1) {
                if (parenLinePoint == -1) {
                    leftSide = Integer.parseInt(parenLine.substring(0, newmultOp));
                    rightSide = Integer.parseInt(parenLine.substring(newmultOp+1, display.getText().length()));
                    multResult = leftSide * rightSide;
                    result = Integer.toString(multResult);
                }
                else {
                    fleftSide = Float.parseFloat(parenLine.substring(0, newmultOp));
                    frightSide = Float.parseFloat(parenLine.substring(newmultOp+1, display.getText().length()));
                    float fmultResult = fleftSide * frightSide;
                    result = Float.toString(fmultResult);
                }
            }
            else if (newdivOp != -1) {
                if (parenLinePoint == -1) {
                    leftSide = Integer.parseInt(parenLine.substring(0, newdivOp));
                    rightSide = Integer.parseInt(parenLine.substring(newdivOp+1, display.getText().length()));
                    divResult = (float) leftSide / rightSide;
                    if (divResult == (int) divResult) {
                        int rounded = round(divResult);
                        result = Integer.toString(rounded);
                    }
                    else {
                        result = Float.toString(divResult);
                    }
                    if (rightSide == 0) {
                        result = "ERROR";
                    }
                }
                else {
                    fleftSide = Float.parseFloat(parenLine.substring(0, newdivOp));
                    frightSide = Float.parseFloat(parenLine.substring(newdivOp+1, display.getText().length()));
                    float fdivResult = fleftSide / frightSide;
                    result = Float.toString(fdivResult);
                    if (frightSide == 0 || frightSide == 0.0) {
                        result = "ERROR";
                    }
                }
            }
            else if (newexpOp != -1) { //Exponent
                if (parenLinePoint == -1) { //If there is a float
                    leftSide = Integer.parseInt(parenLine.substring(0, newexpOp));
                    rightSide = Integer.parseInt(parenLine.substring(newexpOp+1, display.getText().length()));
                    int integExpResult = (int) Math.pow(leftSide, rightSide);
                    result = Integer.toString(integExpResult);
                }
                else { //If there isn't a float
                    fleftSide = Float.parseFloat(parenLine.substring(0, newexpOp));
                    frightSide = Float.parseFloat(parenLine.substring(newexpOp+1, display.getText().length()));
                    float floExpResult = (float) Math.pow(fleftSide, frightSide);
                    result = Float.toString(floExpResult);
                }
            }

            display.setText(result);
        }
        else {
            if (plusOp != -1) {
                if (pointOp == -1){
                    leftSide = Integer.parseInt(line.substring(0, plusOp));
                    rightSide = Integer.parseInt(line.substring(plusOp, display.getText().length()));
                    addResult = leftSide + rightSide;
                    result = Integer.toString(addResult);
                }
                else {
                    fleftSide = Float.parseFloat(line.substring(0, plusOp));
                    frightSide = Float.parseFloat(line.substring(plusOp, display.getText().length()));
                    float faddResult = fleftSide + frightSide;
                    result = Float.toString(faddResult);
                }
            }
            //Subtracting
            else if (subOp != -1) {
                if (pointOp == -1) {
                    leftSide = Integer.parseInt(line.substring(0, subOp));
                    rightSide = Integer.parseInt(line.substring(subOp+1, display.getText().length()));
                    subResult = leftSide - rightSide;
                    result = Integer.toString(subResult);
                }
                else {
                    fleftSide = Float.parseFloat(line.substring(0, subOp));
                    frightSide = Float.parseFloat(line.substring(subOp+1, display.getText().length()));
                    float fsubResult = fleftSide - frightSide;
                    result = Float.toString(fsubResult);
                }
            }
            else if (multOp != -1) {
                if (pointOp == -1) {
                    leftSide = Integer.parseInt(line.substring(0, multOp));
                    rightSide = Integer.parseInt(line.substring(multOp+1, display.getText().length()));
                    multResult = leftSide * rightSide;
                    result = Integer.toString(multResult);
                }
                else {
                    fleftSide = Float.parseFloat(line.substring(0, multOp));
                    frightSide = Float.parseFloat(line.substring(multOp+1, display.getText().length()));
                    float fmultResult = fleftSide * frightSide;
                    result = Float.toString(fmultResult);
                }
            }
            else if (divOp != -1) {
                if (pointOp == -1) {
                    leftSide = Integer.parseInt(line.substring(0, divOp));
                    rightSide = Integer.parseInt(line.substring(divOp+1, display.getText().length()));
                    divResult = (float) leftSide / rightSide;
                    if (divResult == (int) divResult) {
                        int rounded = round(divResult);
                        result = Integer.toString(rounded);
                    }
                    else {
                        result = Float.toString(divResult);
                    }
                    if (rightSide == 0) {
                        result = "ERROR";
                    }
                }
                else {
                    fleftSide = Float.parseFloat(line.substring(0, divOp));
                    frightSide = Float.parseFloat(line.substring(divOp+1, display.getText().length()));
                    float fdivResult = fleftSide / frightSide;
                    result = Float.toString(fdivResult);
                    if (frightSide == 0 || frightSide == 0.0) {
                        result = "ERROR";
                    }
                }
            }
            else if (expOp != -1) { //Exponent
                if (pointOp == -1) { //If there is a float
                    leftSide = Integer.parseInt(line.substring(0, expOp));
                    rightSide = Integer.parseInt(line.substring(expOp+1, display.getText().length()));
                    int integExpResult = (int) Math.pow(leftSide, rightSide);
                    result = Integer.toString(integExpResult);
                }
                else { //If there isn't a float
                    fleftSide = Float.parseFloat(line.substring(0, expOp));
                    frightSide = Float.parseFloat(line.substring(expOp+1, display.getText().length()));
                    float floExpResult = (float) Math.pow(fleftSide, frightSide);
                    result = Float.toString(floExpResult);
                }
            }

            display.setText(result);
        }
    }


    public void backspaceButton(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}