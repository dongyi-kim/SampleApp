package cdmst.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView text;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;


    /**
     * onCreate  : 화면이 생성됬을때 동장
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //xml 파일로 화면을 그려주기
        setContentView(R.layout.activity_main);

        //text변수에 my_textview view를 연동
        text = (TextView)findViewById(R.id.my_textview);
        button1 = (Button)findViewById(R.id.my_button1);
        button2 = (Button)findViewById(R.id.my_button2);
        button3 = (Button)findViewById(R.id.my_button3);
        button4 = (Button)findViewById(R.id.my_button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v)
    {
        if(v == button1)
        {
            text.setText("1 Clicked!");
            Button b = (Button)v;
            b.setText("click click");
        }else if( v == button2 )
        {
            text.setText("2 Clicked!");
        }


    }
}
