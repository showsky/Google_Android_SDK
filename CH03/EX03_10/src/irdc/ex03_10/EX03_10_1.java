package irdc.ex03_10;

/* import����class */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EX03_10_1 extends Activity 
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.myalyout);
    
    /* ���oIntent����Bundle���� */
    Bundle bunde = this.getIntent().getExtras();
    
    /* ���oBundle���󤤪���� */
    String sex = bunde.getString("sex");
    double height = bunde.getDouble("height");
    
    /* �P�_�ʧO */
    String sexText="";
    if(sex.equals("M")){
    	sexText="�k��";
    }else{
    	sexText="�k��";
    }
    
    /* ���o�з��魫 */
    String weight=this.getWeight(sex, height);
    
    /* �]�w��X��r */
    TextView tv1=(TextView) findViewById(R.id.text1);
    tv1.setText("�A�O�@��"+sexText+"\n�A�������O"+height+"����\n�A���з��魫�O"+weight+"����");
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
