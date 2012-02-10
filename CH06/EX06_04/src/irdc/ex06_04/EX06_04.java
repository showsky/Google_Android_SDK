package irdc.ex06_04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EX06_04 extends Activity
{
  private Button mButton01,mButton02;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mButton01 = (Button)findViewById(R.id.myButton1);
    
    /* �}�l�Ұʨt�ΪA�ȫ��s�ƥ� */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        
        /* �غcIntent����A���w�}�ҹ�H��mService1�A�� */
        Intent i = new Intent( EX06_04.this, mService1.class );
        
        /* �]�w�sTASK���覡 */
        i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        
        /* �HstartService��k�Ұ�Intent */
        startService(i); 
      }
    });
    
    mButton02 = (Button)findViewById(R.id.myButton2);
    
    /* �����t�ΪA�ȫ��s�ƥ� */
    mButton02.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        
        /* �غcIntent����A���w����������H��mService1�A�� */
        Intent i = new Intent( EX06_04.this, mService1.class );
        
        /* �HstopService��k����Intent */
        stopService(i);
      }
    });
  }
}