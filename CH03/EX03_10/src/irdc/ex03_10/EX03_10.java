package irdc.ex03_10;

/* import����class */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EX03_10 extends Activity 
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    
    /* �HfindViewById()���oButton����A�å[�JonClickListener */
    Button b1 = (Button) findViewById(R.id.button1);
    b1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        /*���o��J������*/
        EditText et = (EditText) findViewById(R.id.height);
        double height=Double.parseDouble(et.getText().toString());
        /*���o��ܪ��ʧO*/
        String sex="";
        RadioButton rb1 = (RadioButton) findViewById(R.id.sex1);
        if(rb1.isChecked())
        {
        	sex="M";
        }else{
        	sex="F";
        }    
        /*new�@��Intent����A�ë��wclass*/
    	Intent intent = new Intent();
        intent.setClass(EX03_10.this,EX03_10_1.class);
        
        /*new�@��Bundle����A�ñN�n�ǻ�����ƶǤJ*/
    	Bundle bundle = new Bundle();
    	bundle.putDouble("height",height);
    	bundle.putString("sex",sex);
    	
    	/*�NBundle����assign��Intent*/
    	intent.putExtras(bundle);
    	
    	/*�I�sActivity EX03_10_1*/
    	startActivity(intent);
      }
    });
  }
}