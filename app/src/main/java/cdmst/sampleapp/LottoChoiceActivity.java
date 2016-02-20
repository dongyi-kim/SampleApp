package cdmst.sampleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by waps12b on 16. 2. 20..
 */
public class LottoChoiceActivity extends Activity{

    private EditText[] numbers;
    private Button btnRandom;
    private Button btnOkay;
    private LinearLayout linearLotto;

    //공통적으로 사용되는 상수는 항상 publix static final 로 선언합시다
    //Intent에 사용되는 Extra Name은 전역으로 사용되는 상수입니다
    public static final String EXTRA_NAME_NUMBERS = "12342334345234234234";
    public static final int LOTTO_SIZE = 6;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setContentView(R.layout.activity_lotto_choice);

        linearLotto = (LinearLayout)findViewById(R.id.linear_lotto);

        numbers = new EditText[LOTTO_SIZE];
        for(int i = 0 ; i < LOTTO_SIZE ; i++){
            numbers[i] = (EditText)linearLotto.getChildAt(i);
        }


        generateLottoNumbers();

        btnRandom = (Button)findViewById(R.id.btn_random);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateLottoNumbers();
            }
        });

        btnRandom = (Button)findViewById(R.id.btn_okay);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNumbers()) {//true
                    sendResult();
                } else {//false
                    Toast.makeText(LottoChoiceActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    /**
     *  이 액티비티에서 수행한 결과를 저장하고 종료합니다.
     */
    private void sendResult()
    {
        //선택된 6가지의 숫자를 정수 배열에 저장합니다
        int[] array = new int[LOTTO_SIZE];
        for(int i = 0 ; i < LOTTO_SIZE;  i++)
        {
            array[i] = Integer.parseInt( numbers[i].getText().toString() );
        }

        //선택된 숫자들을 정렬합니다
        Arrays.sort(array);

        //result를 액티비티에 보내기 위해서 인텐트를 보냅니다
        Intent intent = new Intent();
        //putExtra를 통해 intent에 추가데이터를 삽입합니다.
        intent.putExtra(EXTRA_NAME_NUMBERS, array);

        //setResult를 통해 이 액티비티의 결과 코드와 intent를 등록합니다.
        setResult(Activity.RESULT_OK, intent);

        //액티비티를 종료합니다.
        finish();
    }

    /**
     * 6개의 입력된 숫자가 로또 규칙에 위배되지 않는지 검사합니다
     * @return
     */
    private boolean checkNumbers()
    {
        try{
            //또 set을 이용합니다
            //6개의 숫자를 set에 넣었을 때 sets의 사이즈가 6개라면 중복된 숫자가 없음을 의미합니다
            HashSet<Integer> sets = new HashSet<>();
            for(int i = 0 ; i< LOTTO_SIZE; i ++){
                //EditText로 부터 입력된 문자열을 받아옵니다
                String text= numbers[i].getText().toString();

                //해당 문자열을 정수형으로 파싱하여 num에 저장합니다
                //이 때 문자가 섞여있다면 Exception이 발생하여 catch로 점프합니다
                int num = Integer.parseInt( text );

                //변환 결과인 숫자가 1~45범위를 벗어나는지 아닌지 채크합니다.
                if( num < 1 || num > 45 )
                {   //각 숫자가 범위를 벗어났나 검사합니다
                    return false;
                }

                //이 숫자를 set에 넣습니다
                sets.add(num);
            }

            //set에 존재하는 숫자가 입력한 개수와 일치하는지 검사합니다.
            if(sets.size() != LOTTO_SIZE)
            {   //중복 숫자가 있는지
                return false;
            }

        }
        catch (Exception ex)
        {   //숫자가 아니라 문자가 입력될었다면 parseInt에서 Exception이 발생해 이 부분으로 오게 됩니다.
            return false;
        }

        return true;
    }

    /**
     * 6개의 1~45사이 난수를 생성하여 화면에 보여줍니다
     *
     */
    private void generateLottoNumbers() {
        // [?] set을 쓰는 이유?
        // 기본적으로 set은 중복데이터를 허용하지 않습니다
        // 즉 set의 사이즈가 6이 될 때 까지 난수를 집어넣는다면, 중복되지 않은 6개의 데이터를 얻을 수 있게 됩니다
        HashSet<Integer> set = new HashSet<>();

        //Random 클래스는 난수를 만들어주는 클래스입니다. (난수생성기)
        Random random = new Random();
        while(set.size() < LOTTO_SIZE ) //set의 사이즈가 6개보다 작은 동안에 반복됩니다
        {
            //random.nextInt()를 통해 정수 난수를 가져옵니다 (음수일 수 도 있습니다)
            //이에 절대값을 취한 후 % 45 + 1을 하면 1 ~ 45사이의 수를 얻을 수 있습니다.
            int rand = Math.abs(random.nextInt()) % 45 + 1;
            set.add(rand);// set에 추가합니다
        }

        int index = 0;
        for(Integer i : set)
        {   // 6번 반복
            numbers[index].setText(String.valueOf(i));
            index ++; //0 ~ 5
        }
    }
}
