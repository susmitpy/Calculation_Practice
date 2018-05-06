package e.susmit.maths;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        GetInput();
    }

    private void GetInput() {
        Button Add = (Button) findViewById(R.id.add);
        Button Sub = (Button) findViewById(R.id.sub);
        Button Mul = (Button) findViewById(R.id.mul);
        Button Htu = (Button) findViewById(R.id.HTU);
        List<Button> l = Arrays.asList(Add, Sub, Mul, Htu);
        for (Button x: l) x.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.sub:
                intent = new Intent(HomeActivity.this, SubActivity.class);
                startActivity(intent);
                break;
            case R.id.mul:
                intent = new Intent(HomeActivity.this, MulActivity.class);
                startActivity(intent);
                break;
            case R.id.HTU:
                intent = new Intent(HomeActivity.this, HowActivity.class);
                startActivity(intent);
                break;

        }
    }
}
