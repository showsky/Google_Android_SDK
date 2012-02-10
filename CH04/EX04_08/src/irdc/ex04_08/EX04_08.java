package irdc.ex04_08;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Spinner;

public class EX04_08 extends Activity
{
  private static final String[] countriesStr =
  { "�x�_��", "�x�_��", "�x����", "������" };
  private TextView myTextView;
  private Spinner mySpinner;
  private ArrayAdapter<String> adapter;
  Animation myAnimation;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);

    /* �HfindViewById()���o���� */
    myTextView = (TextView) findViewById(R.id.myTextView);
    mySpinner = (Spinner) findViewById(R.id.mySpinner);

    adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, countriesStr);
    /* myspinner_dropdown���ۭq�U�Կ��˦��w�q�bres/layout�ؿ��U */
    adapter.setDropDownViewResource(R.layout.myspinner_dropdown);

    /* �NArrayAdapter�[�JSpinner���� */
    mySpinner.setAdapter(adapter);

    /* �NmySpinner�[�JOnItemSelectedListener */
    mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
    {

      @Override
      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
          long arg3)
      {
        /* �N�ҿ�mySpinner���ȱa�JmyTextView�� */
        myTextView.setText("��ܪ��O" + countriesStr[arg2]);
        /* �NmySpinner��� */
        arg0.setVisibility(View.VISIBLE);
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0)
      {
        // TODO Auto-generated method stub
      }

    });

    /* ���oAnimation�w�q�bres/anim�ؿ��U */
    myAnimation = AnimationUtils.loadAnimation(this, R.anim.my_anim);

    /* �NmySpinner�[�JOnTouchListener */
    mySpinner.setOnTouchListener(new Spinner.OnTouchListener()
    {

      @Override
      public boolean onTouch(View v, MotionEvent event)
      {
        /* �NmySpinner����Animation */
        v.startAnimation(myAnimation);
        /* �NmySpinner���� */
        v.setVisibility(View.INVISIBLE);
        return false;
      }

    });

    mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener()
    {

      @Override
      public void onFocusChange(View v, boolean hasFocus)
      {
        // TODO Auto-generated method stub

      }

    });

  }
}