package irdc.ex04_26;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EX04_26 extends Activity 
{
  /*�ŧi Button����*/
  private Button mButton1;
  
    /** Called when the activity is first created. */
    @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*���o Button����*/
    mButton1 = (Button) findViewById(R.id.myButton1);
    mButton1.setOnClickListener(new Button.OnClickListener()
    {

    @Override
    public void onClick(View v)
    {
      // TODO Auto-generated method stub      
      new AlertDialog.Builder(EX04_26.this)
      /*�u�X�������̤W�Y��r*/
      .setTitle(R.string.app_about)
      /*�]�w�u�X�������Ϧ�*/
      .setIcon(R.drawable.hot) 
      /*�]�w�u�X�������T��*/
      .setMessage(R.string.app_about_msg)
      .setPositiveButton(R.string.str_ok,
      new DialogInterface.OnClickListener()
    {
     public void onClick(DialogInterface dialoginterface, int i)
     {           
      finish();/*��������*/
     }
     }
    )
      /*�]�w���X��������^�ƥ�*/
      .setNegativeButton(R.string.str_no,
       new DialogInterface.OnClickListener()
      {
     public void onClick(DialogInterface dialoginterface, int i)   
    {
    }
      })
    .show();
    }
    });
  }
  }
