package e.susmit.maths;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class
SubActivity extends AppCompatActivity {
    EditText GA;
    Button btn;
    ImageView img;
    TextView ques;
    String question;
    int n1, n2, UserAns;
    InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_mul);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        GA = (EditText) findViewById(R.id.a);
        CreateQuestion();
        Listen();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("N1", n1);
        outState.putInt("N2", n2);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        n1 = savedInstanceState.getInt("N1");
        n2 = savedInstanceState.getInt("N2");
        question = String.valueOf(n1) + " x " + String.valueOf(n2);
        ques.setText(question);
    }

    private void Listen() {
        btn = (Button) findViewById(R.id.go);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                } catch (Exception e) {

                }
                CheckAnswer();
            }
        });

    }

    private void CreateQuestion() {
        GA.getText().clear();
        ques = (TextView) findViewById(R.id.q);
        Random rand = new Random();
        n1 = rand.nextInt(100);
        n2 = rand.nextInt(100);
        if (n1 < n2){
            int temp = n1;
            n1 = n2;
            n2 = temp;

        }
        question = String.valueOf(n1) + " - " + String.valueOf(n2);
        ques.setText(question);
        GA.requestFocus();
        imm.showSoftInput(GA, InputMethodManager.SHOW_FORCED);
    }

    private void CheckAnswer() {
        img = (ImageView) findViewById(R.id.img);

        boolean correct = false;
        if (TextUtils.isEmpty(GA.getText())) GA.setError("Please Type Answer Before Checking");
        else{
            UserAns = Integer.parseInt(GA.getText().toString());
            if (n1 - n2 == UserAns) {
                AnsRight();
                CreateQuestion();
            } else {
                AnsWrong();
            }
        }
    }


    private void AnsRight() {
        img.setImageResource(R.mipmap.green_on);

    }

    private void AnsWrong() {
        img.setImageResource(R.mipmap.red_on);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ShowAnswer = "%d - %d = %d";
                ques.setText(String.format(ShowAnswer, n1, n2, n1-n2));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CreateQuestion();
                    }
                },Long.parseLong(getString(R.string.delay)));
            }
        });
        GA.getText().clear();
        imm.showSoftInput(GA, InputMethodManager.SHOW_FORCED);
    }
}
