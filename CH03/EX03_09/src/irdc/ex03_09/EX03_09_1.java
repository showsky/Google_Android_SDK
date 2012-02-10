package irdc.ex03_09;

/* import����class */
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class EX03_09_1 extends Activity
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ���Jmylayout.xml Layout */
    setContentView(R.layout.mylayout);
    
    /* �HfindViewById()���oButton����A�å[�JonClickListener */
    Button b2 = (Button) findViewById(R.id.button2);
    b2.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        /* new�@��Intent����A�ë��w�n�Ұʪ�class */
        Intent intent = new Intent();
    	  intent.setClass(EX03_09_1.this, EX03_09.class);
    	  
    	  /* �I�s�@�ӷs��Activity */
        startActivity(intent);
        /* �����쥻��Activity */
        EX03_09_1.this.finish();
      }
    });
    
  }
}