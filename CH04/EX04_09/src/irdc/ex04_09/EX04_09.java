package irdc.ex04_09;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EX04_09 extends Activity
{
  private static final String[] countriesStr =
  { "�x�_��", "�x�_��", "�x����", "������" };
  private TextView myTextView;
  private EditText myEditText;
  private Button myButton_add;
  private Button myButton_remove;
  private Spinner mySpinner;
  private ArrayAdapter<String> adapter;
  private List<String> allCountries;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);

    allCountries = new ArrayList<String>();
    for (int i = 0; i < countriesStr.length; i++)
    {
      allCountries.add(countriesStr[i]);
    }

    /* new ArrayAdapter����ñNallCountries�ǤJ */
    adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, allCountries);
    adapter
        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    /* �HfindViewById()���o���� */
    myTextView = (TextView) findViewById(R.id.myTextView);
    myEditText = (EditText) findViewById(R.id.myEditText);
    myButton_add = (Button) findViewById(R.id.myButton_add);
    myButton_remove = (Button) findViewById(R.id.myButton_remove);
    mySpinner = (Spinner) findViewById(R.id.mySpinner);

    /* �NArrayAdapter�[�JSpinner���� */
    mySpinner.setAdapter(adapter);

    /* �NmyButton_add�[�JOnClickListener */
    myButton_add.setOnClickListener(new Button.OnClickListener()
    {

      @Override
      public void onClick(View arg0)
      {
        String newCountry = myEditText.getText().toString();

        /* �����s�W���ȬO�_�w�s�b�A���s�b�~�i�s�W */
        for (int i = 0; i < adapter.getCount(); i++)
        {
          if (newCountry.equals(adapter.getItem(i)))
          {
            return;
          }
        }

        if (!newCountry.equals(""))
        {
          /* �N�ȷs�W��adapter */
          adapter.add(newCountry);
          /* ���o�s�W���Ȫ���m */
          int position = adapter.getPosition(newCountry);
          /* �NSpinner����b�s�W���Ȫ���m */
          mySpinner.setSelection(position);
          /* �NmyEditText�M�� */
          myEditText.setText("");
        }

      }
    });

    /* �NmyButton_remove�[�JOnClickListener */
    myButton_remove.setOnClickListener(new Button.OnClickListener()
    {

      @Override
      public void onClick(View arg0)
      {

        if (mySpinner.getSelectedItem() != null)
        {
          /* ����mySpinner���� */
          adapter.remove(mySpinner.getSelectedItem().toString());
          /* �NmyEditText�M�� */
          myEditText.setText("");
          if (adapter.getCount() == 0)
          {
            /* �NmyTextView�M�� */
            myTextView.setText("");
          }
        }
      }
    });

    /* �NmySpinner�[�JOnItemSelectedListener */
    mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
    {

      @Override
      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
          long arg3)
      {
        /* �N�ҿ�mySpinner���ȱa�JmyTextView�� */
        myTextView.setText(arg0.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0)
      {

      }
    });

  }
}