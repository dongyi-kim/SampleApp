package cdmst.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{ //이 Activity 객체에 클릭 리스너 객체를 등록합시다

    public static final int RESULT_CODE_LOTTO_CHOICE = 1;

    private TextView text;
    private Button btnChoice;


    /**
     * onCreate  : 화면이 생성됬을때 동장
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //xml 파일로 화면을 그려주기
        //layout폴더에 만든 xml파일은 자동으로 리소스 id가 등록되므로 R.layoyt.XXX 로 접근이 가능해집니다
        setContentView(R.layout.activity_main);

        //text변수에 my_textview view를 연동
        //XML파일 내부에 id를 부여해준 View들도 리소스 id로 접근이 가능합니다
        //setContentView로 불러온 Layout의 View들은 Activity에서 관리하고 있습니다
        //그러므로 findViewById()를 사용하여 액티비티로부터 그 View들의 객체를 받아올 수 있습니다
        text = (TextView)findViewById(R.id.my_textview);
        btnChoice = (Button)findViewById(R.id.my_button1);

        //View들에 setOnClickListener메소드를 통해 OnClickListener 객체를 등록할 수 있습니다
        //해당 View가 Click되었을 때에 해당 OnClickListener객체의 onClick(View v)메소드가 실행됩니다
        btnChoice.setOnClickListener(this);

    }

    /**
     * setOnClickListener()를 통해 클릭 리스너가 등록된 객체는 클릭되었을 때 해당 리스너 객체의 onClick() 메소드를 호출해줍니다
     * @param v //클릭된 대상 View가 파라미터로 주어집니다 비교 연산자를 통해서 어떤 View가 클릭된건지 검사할 수 있습니다
     */
    @Override
    public void onClick(View v)
    {
        if( v == btnChoice)
        {
            Intent intent = new Intent(this, LottoChoiceActivity.class);
            startActivityForResult(intent, RESULT_CODE_LOTTO_CHOICE);
            //
            //

            ///
        }

    }


}
