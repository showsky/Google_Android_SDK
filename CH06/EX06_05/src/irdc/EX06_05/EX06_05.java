package irdc.EX06_05;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle; 
import android.widget.TextView; 

public class EX06_05 extends Activity 
{ 
  /*�ŧi�@��TextView,String�}�C�P��Ӥ�r�r���ܼ�*/
  private TextView mTextView1; 
  public String[] strEmailReciver;
  public String strEmailSubject;
  public String strEmailBody;
  
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    
    /*�z�LfindViewById�غc�l�إ�TextView����*/ 
    mTextView1 = (TextView) findViewById(R.id.myTextView1); 
    mTextView1.setText("���ݱ���²�T..."); 
    
    try{
    /*���o²�T�ǨӪ�bundle*/
    Bundle bunde = this.getIntent().getExtras(); 
    if (bunde!= null)
    {
    	/*�Nbunde�����r����X*/
    	String sb = bunde.getString("STR_INPUT");
        /*�ۭq�@Intent�Ӱ���H�eE-mail���u�@*/
        Intent mEmailIntent = new Intent(android.content.Intent.ACTION_SEND);  
        /*�]�w�l��榡��"plain/text"*/
        mEmailIntent.setType("plain/text");
        
        /*���oEditText01,02,03,04���ȧ@������H�a�},����,�D��,����*/
        strEmailReciver =new String[]{"jay.mingchieh@gmail.com"};
        strEmailSubject = "�A���@��²�T!!";
        strEmailBody = sb.toString();
        
        /*�N���o���r���JmEmailIntent��*/
        mEmailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, strEmailReciver); 
        mEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, strEmailSubject);
        mEmailIntent.putExtra(android.content.Intent.EXTRA_TEXT, strEmailBody);
        startActivity(Intent.createChooser(mEmailIntent, getResources().getString(R.string.str_message))); 	
    }
    else
    {
    finish();
    }
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
  }
}


