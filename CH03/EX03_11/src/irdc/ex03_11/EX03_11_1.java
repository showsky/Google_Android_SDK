package irdc.ex03_11;

/* import����class */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EX03_11_1 extends Activity 
{
  Bundle bunde;
  Intent intent;
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ���Jmylayout.xml Layout */
    setContentView(R.layout.myalyout);
    
    /* ���oIntent����Bundle���� */
    intent=this.getIntent();
    bunde = intent.getExtras();
    
    /* ���oBundle���󤤪���� */
    String sex = bunde.getString("sex");
    double height = bunde.getDouble("height");
    
    /* �P�_�ʧO */
    String sexText="";
    if(sex.equals("M"))
    {
      sexText="�k��";
    }else
    {
      sexText="�k��";
    }
    
    /* ���o�з��魫 */
    String weight=this.getWeight(sex, height);
    
    /* �]�w��X��r */
    TextView tv1=(TextView) findViewById(R.id.text1);
    tv1.setText("�A�O�@��"+sexText+"\n�A�������O"+height+"����\n�A���з��魫�O"+weight+"����");
    
    
    /* �HfindViewById()���oButton����A�å[�JonClickListener */
    Button b1 = (Button) findViewById(R.id.button1);
    b1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {          
    	  /* �^��result�^�W�@��activity */
    	  EX03_11_1.this.setResult(RESULT_OK, intent);
    	  
    	  /* ����activity */
    	  EX03_11_1.this.finish();
      }
    });
  }
  
  /* �|�ˤ��J��method */
  private String format(double num)
  {
    NumberFormat formatter = new DecimalFormat("0.00");
	String s=formatter.format(num);
	return s;
  }

  /* �HfindViewById()���oButton����A�å[�JonClickListener */	
  private String getWeight(String sex,double height)
  {
    String weight="";
	if(sex.equals("M"))
	{
	  weight=format((height-80)*0.7);
    }else
	{
	  weight=format((height-70)*0.6);
	}	
	return weight;
  }
}
