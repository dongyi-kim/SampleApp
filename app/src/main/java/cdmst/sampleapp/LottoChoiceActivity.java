package cdmst.sampleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by waps12b on 16. 2. 20..
 */
public class LottoChoiceActivity extends Activity{

    private EditText[] numbers;
    private Button btnRandom;
    private Button btnOkay;

    public static final String EXTRA_NAME_NUMBERS = "12342334345234234234";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lotto_choice);


        numbers = new EditText[6];
        numbers[0] = (EditText)findViewById(R.id.editText1);
        numbers[1] = (EditText)findViewById(R.id.editText2);
        numbers[2] = (EditText)findViewById(R.id.editText3);
        numbers[3] = (EditText)findViewById(R.id.editText4);
        numbers[4] = (EditText)findViewById(R.id.editText5);
        numbers[5] = (EditText)findViewById(R.id.editText6);

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


        generateLottoNumbers();
    }

    private void sendResult() {

        int[] array = new int[6];
        for(int i = 0 ; i < 6;  i++){
            array[i] = Integer.parseInt( numbers[i].getEditableText().toString() );
        }

        Arrays.sort(array);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NAME_NUMBERS, array);

        setResult( Activity.RESULT_OK , intent);

        finish();
    }

    private boolean checkNumbers()
    {
        try{
            HashSet<Integer> sets = new HashSet<>();
            for(int i = 0 ; i< 6 ; i ++){
                int num = Integer.parseInt( numbers[i].getText().toString() );


                if( num < 1 || num > 45 )
                {   //각 숫자가 범위를 벗어났나
                    return false;
                }
                sets.add(num);
            }

            if(sets.size() != 6)
            {   //중복 숫자가 있는지
                return false;
            }

        }
        catch (Exception ex)
        {   //문자 입력한 경우 처리
            return false;
        }

        return true;
    }

    private void generateLottoNumbers() {
        HashSet<Integer> set = new HashSet<>();

        Random random = new Random();
        while(set.size() < 6 )
        {
            int rand = Math.abs(random.nextInt()) % 45 + 1;
            set.add(rand);
        }

        int index = 0;
        for(Integer i : set)
        {   // 6번 반복
            numbers[index].setText(String.valueOf(i));
            index ++; //0 ~ 5
        }
    }
}
