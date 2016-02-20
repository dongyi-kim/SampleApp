package cdmst.sampleapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{ //이 Activity 객체에 클릭 리스너 객체를 등록합시다

    public static final int REQUEST_CODE_LOTTO_CHOICE = 1;

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
        {   //btnChoice가 클릭되어서 onClick()이 호출되었다면 이 아래를 수행합니다

            //액티비티를 호출하는 인탠트는 다음과 같이 선언합니다.
            // Intent intent = new Intent( {호출하는 객체}, {호출 하려는 대상의 클래스} );
            Intent intent = new Intent(this, LottoChoiceActivity.class);

            //startActivity(Intent intent) : 메시지를 Application으로 보내 해당 액티비티를 열어주길 요청합니다.
            //startActivityForResult(Intent intent, int requestCode) : 메시지를 보내 해당 액티비티 열기를 요청합니다. 해당 액티비티가 종료시 setResult를 통해 등록된 result결과를 받아옵니다.
            startActivityForResult(intent, REQUEST_CODE_LOTTO_CHOICE);


        }
    }


    /**
     *  onActivityResult는 startActivityForResult()를 통해 호출된 액티비티가 종료되었을 때 호출되어 그 결과를 알려줍니다
     * @param requestCode   //startActivityForResult()를 호출할 때 넣어준 requestCode를 그대로 가져옵니다. 이를 사용해 호출된 액티비티나 그 용도를 구분할 수 있습니다
     * @param resultCode    //불려진 액티비티 내부에서 setResult를 통해 등록한 resultCode를 가져옵니다. 결과 코드에 따라 적당한 분기를 해주면 됩니다.
     * @param data          //불려진 액티비티가 종료를 알리며 보낸 Intent를 받아옵니다 이 때 Intent에 Extra를 통해 데이터를 꺼내올 수 있습니다.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){
            case REQUEST_CODE_LOTTO_CHOICE:
                    if(resultCode == Activity.RESULT_OK) {
                        int[] num = data.getIntArrayExtra( LottoChoiceActivity.EXTRA_NAME_NUMBERS );


                    }


                break;

            default:

        }


    }
}
