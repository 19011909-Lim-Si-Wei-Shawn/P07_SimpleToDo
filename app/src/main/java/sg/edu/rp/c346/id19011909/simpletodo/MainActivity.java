package sg.edu.rp.c346.id19011909.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Creating Variable,
    EditText etInput;
    Button btnA;
    Button btnC;
    Button btnD;
    ListView lvInfo;
    Spinner spnChoice;

    ArrayList<String> AllInfo;
    String InputData = "";
    Integer IntData = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking Variable,
        etInput = findViewById(R.id.ETUser);
        btnA = findViewById(R.id.buttonAdd);
        btnC = findViewById(R.id.buttonClear);
        btnD = findViewById(R.id.buttonDelete);
        lvInfo = findViewById(R.id.ListViewAll);
        spnChoice = findViewById(R.id.SpinOption);

        //Setting Action {ArrayList},
        AllInfo = new ArrayList<String>();
        AllInfo.add("Do Assignment");

        //Setting Action,
        spnChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if(spnChoice.getSelectedItem().equals("Add a New Task"))
                        {
                            etInput.setHint(R.string.ETAdd);
                            etInput.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                            btnD.setEnabled(false);
                            btnD.setTextColor(getResources().getColor(R.color.OrangeRed));
                            btnA.setEnabled(true);
                            btnA.setTextColor(getResources().getColor(R.color.white));
                        }
                        break;

                    case 1:
                        if(spnChoice.getSelectedItem().equals("Remove a Task"))
                        {
                            etInput.setHint(R.string.ETRemove);
                            etInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                            btnA.setEnabled(false);
                            btnA.setTextColor(getResources().getColor(R.color.OrangeRed));
                            btnD.setEnabled(true);
                            btnD.setTextColor(getResources().getColor(R.color.white));
                        }
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Linking ArrayAdapter,
        ArrayAdapter List = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, AllInfo);

        lvInfo.setAdapter(List);

        //Button Action {Adding},
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputData = etInput.getText().toString();
                AllInfo.add(InputData);
                List.notifyDataSetChanged();
                etInput.setText("");
            }
        });

        //Button Action {Remove},
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etInput.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    IntData = Integer.parseInt(etInput.getText().toString());

                    if(AllInfo.isEmpty() == true)
                    {
                        Toast.makeText(MainActivity.this, "You don't have any Task to Remove", Toast.LENGTH_SHORT).show();
                    }

                    else if(IntData > AllInfo.size())
                    {   Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();     }

                    else
                    {
                        AllInfo.remove(AllInfo.get(IntData));
                        List.notifyDataSetChanged();
                    }
                    etInput.setText("");
                }
            }
        });

        //Button Action {Remove},
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(AllInfo.isEmpty() == true)
                {
                    Toast.makeText(MainActivity.this, "You don't have any Task to Remove", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    AllInfo.clear();
                    List.notifyDataSetChanged();
                }
                etInput.setText("");
            }
        });
    }
}