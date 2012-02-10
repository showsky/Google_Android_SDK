package irdc.ex03_11;

/* import����class */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EX03_11 extends Activity 
{
  private EditText et;
  private RadioButton rb1;
  private RadioButton rb2;
    
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
        et = (EditText) findViewById(R.id.height);
        double height=Double.parseDouble(et.getText().toString());
        /*���o��ܪ��ʧO*/
        String sex="";
        rb1 = (RadioButton) findViewById(R.id.sex1);
        rb2 = (RadioButton) findViewById(R.id.sex2);
        if(rb1.isChecked())
        {
        	sex="M";
        }else{
        	sex="F";
        }    
        /*new�@��Intent����A�ë��wclass*/
    	Intent intent = new Intent();
        intent.setClass(EX03_11.this,EX03_11_1.class);
        
        /*new�@��Bundle����A�ñN�n�ǻ�����ƶǤJ*/
    	Bundle bundle = new Bundle();
    	bundle.putDouble("height",height);
    	bundle.putString("sex",sex);
    	
    	/*�NBundle����assign��Intent*/
    	intent.putExtras(bundle);
    	
    	/*�I�sActivity EX03_11_1*/
    	startActivityForResult(intent,0);
      }
    });
  }
  
  /* �мg onActivityResult()*/
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    switch (resultCode)
    { 
      case RESULT_OK:
    	/* ���o��ơA����ܩ�e���W */  
        Bundle bunde = data.getExtras();
        String sex = bunde.getString("sex");
        double height = bunde.getDouble("height");
        
        et.setText(""+height);
        if(sex.equals("M"))
        {
          rb1.setChecked(true);
        }else
        {
          rb2.setChecked(true);
        }
        break;       
      default: 
        break; 
     } 
   } 
}