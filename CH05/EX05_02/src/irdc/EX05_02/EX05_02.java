package irdc.EX05_02;

import android.app.Activity; 
import android.content.Intent; 
/*���ݤޥ�Uri���O�~��ϥ�Uri.parse()*/
import android.net.Uri; 
import android.os.Bundle; 
import android.view.View; 
/*���ݤޥ� widget.Button�~��ŧi�ϥ�Button����*/
import android.widget.Button; 
import android.widget.Toast;
/*���ݤޥ� widget.EditText�~��ŧi�ϥ�EditText����*/
import android.widget.EditText; 
/*���ݤޥ� java.util.regex�~��ϥ�Regular Expression*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EX05_02 extends Activity 
{ 
 /*�ŧiButton�PEditText����W��*/
  private Button mButton1; 
  private EditText mEditText1; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    /*�z�LfindViewById�غc�l�ӫغcEditText�PButton����*/ 
    mEditText1 = (EditText) findViewById(R.id.myEditText1); 
    mButton1 = (Button) findViewById(R.id.myButton1); 
    /*�]�wButton����OnClickListener�Ӳ�ťOnClick�ƥ�*/
    mButton1.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        try 
        { 
        	/*���oEditText���ϥΪ̿�J���r��*/
          String strInput = mEditText1.getText().toString(); 
          if (isPhoneNumberValid(strInput)==true)
          {
          /*�غc�@�ӷs��Intent�ð���action.CALL���`�ƻP�z�LUri�N�r��a�J*/
          Intent myIntentDial = new  
          Intent("android.intent.action.CALL",Uri.parse("tel:"+strInput)); 
          /*�bstartActivity()��k���a�J�ۭq��Intent����H���漷���q�ܪ��u�@*/
          startActivity(myIntentDial);
          mEditText1.setText("");
          }
          else
          {
        	mEditText1.setText("");
        	Toast.makeText(
        	EX05_02.this, "��J���q�ܮ榡����",
        	Toast.LENGTH_LONG).show();
          }
        } 
        catch(Exception e) 
        { 
          e.printStackTrace(); 
        } 
      } 
    }); 
  }
  /*�ˬd�r��O�_���q�ܸ��X����k,�æ^��true or false���P�_��*/
  public static boolean isPhoneNumberValid(String phoneNumber)
  {
	   boolean isValid = false;
	   /* �i�������q�ܮ榡��:
	      * ^\\(? : �i�H�ϥ� "(" �@���}�Y
	      * (\\d{3}): �򱵵ۤT�ӼƦr
	      * \\)? : �i�H�ϥ�")"����
	      * [- ]? : �b�W�z�榡��i�H�ϥΨ��ܩʪ� "-".
	      * (\\d{3}) : �A�򱵵ۤT�ӼƦr
	      * [- ]? : �i�H�ϥΨ��ܩʪ� "-" ����.
	      * (\\d{4})$: �H�|�ӼƦr����.
	      * �i�H���U�C�Ʀr�榡:
	      * (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890  
	     */
	   String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
	   String expression2 ="^\\(?(\\d{2})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
	   CharSequence inputStr = phoneNumber;
	   /*�إ�Pattern*/
	   Pattern pattern = Pattern.compile(expression);
	   /*�NPattern �H�ѼƶǤJMatcher�@Regular expression*/
	   Matcher matcher = pattern.matcher(inputStr);
	   /*�إ�Pattern2*/
	   Pattern pattern2 =Pattern.compile(expression2);
	   /*�NPattern2 �H�ѼƶǤJMatcher2�@Regular expression*/ 
	   Matcher matcher2= pattern2.matcher(inputStr);
	   if(matcher.matches()||matcher2.matches())
	   {
	   isValid = true;
	   }
	   return isValid; 
	 }
} 
