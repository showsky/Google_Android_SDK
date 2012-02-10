package irdc.EX05_03;

import android.app.Activity; 
/*���ݤޥ�PendingIntent���O�~��ϥ�getBrocast()*/
import android.app.PendingIntent; 
import android.content.Intent; 
import android.os.Bundle; 
/*���ݤޥ�telephony.gsm.SmsManager���O�~��ϥ�sendTextMessage()*/
import android.telephony.gsm.SmsManager; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EX05_03 extends Activity  
{ 
  /*�ŧi�ܼƤ@��Button�P���EditText*/
  private Button mButton1; 
  private EditText mEditText1; 
  private EditText mEditText2; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    
    /*�z�LfindViewById�غc�l�ӫغcEditText1,EditText2�PButton����*/ 
    mEditText1 = (EditText) findViewById(R.id.myEditText1); 
    mEditText2 = (EditText) findViewById(R.id.myEditText2); 
    mButton1 = (Button) findViewById(R.id.myButton1); 
    
    /*�N�w�]��r���JEditText��*/
    mEditText1.setText("�п�J�q�ܸ��X"); 
    mEditText2.setText("�п�J²�T���e!!"); 
    
    /*�]�wonClickListener ���ϥΪ��I��EditText�ɰ��X����*/
    mEditText1.setOnClickListener(new EditText.OnClickListener()
    {
      public void onClick(View v)
      {
        /*�I��EditText�ɲM�Ť���*/
        mEditText1.setText("");
      }
    }
    );
    
    /*�]�wonClickListener ���ϥΪ��I��EditText�ɰ��X����*/
    mEditText2.setOnClickListener(new EditText.OnClickListener()
    {
      public void onClick(View v)
      {
        /*�I��EditText�ɲM�Ť���*/
        mEditText2.setText("");
      }
    }
    );
     
    /*�]�wonClickListener ���ϥΪ��I��Button�ɰ��X����*/
    mButton1.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        /*��EditText1���o²�T����H�q��*/
        String strDestAddress = mEditText1.getText().toString(); 
        /*��EditText2���o²�T��r���e*/
        String strMessage = mEditText2.getText().toString(); 
        /*�غc�@���odefault instance�� SmsManager���� */
        SmsManager smsManager = SmsManager.getDefault(); 
      
        // TODO Auto-generated method stub 
        /*�ˬd����H�q�ܮ榡�P²�T�r�ƬO�_�W�L70�r��*/
        if(isPhoneNumberValid(strDestAddress)==true && iswithin70(strMessage)==true)
        {
        try 
          { 
          /*��ӱ����ˬd�q�L�����p�U,�o�e²�T
           * ���غc�@PendingIntent����èϥ�getBroadcast()��k�i��Broadcast
           * �NPendingIntent,�q��,²�T��r���ѼƶǤJsendTextMessage()��k�o�e²�T*/
            PendingIntent mPI = PendingIntent.getBroadcast(EX05_03.this, 0, new Intent(), 0); 
            smsManager.sendTextMessage(strDestAddress, null, strMessage, mPI, null); 
          } 
        catch(Exception e) 
          { 
          e.printStackTrace(); 
          } 
          Toast.makeText(EX05_03.this,
            "�e�X���\!!" , 
            Toast.LENGTH_SHORT).show();
          mEditText1.setText("");
          mEditText2.setText("");
        
        }
        /*�q�ܮ榡�P²�T��r���ŦX�����,�ϥ�Toast�i���ϥΪ��ˬd*/
        else 
        { /*�q�ܮ榡����*/
          if (isPhoneNumberValid(strDestAddress)==false)
          { /*�B�r�ƶW�L70�r��*/
            if(iswithin70(strMessage)==false)
            {
              Toast.makeText(EX05_03.this, 
                  "�q�ܸ��X�榡���~+²�T���e�W�L70�r,���ˬd!!", 
                  Toast.LENGTH_SHORT).show();
            }
            else
            {
            Toast.makeText(EX05_03.this,
                "�q�ܸ��X�榡���~,���ˬd!!" , 
                Toast.LENGTH_SHORT).show();
            }
          }
          /*�r�ƶW�L70�r��*/
          else if (iswithin70(strMessage)==false)
          {
            Toast.makeText(EX05_03.this, 
                "²�T���e�W�L70�r,�ЧR���������e!!", 
                Toast.LENGTH_SHORT).show();
          }
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
     /* �i�������q�ܮ榡��:
      * ^\\(? : �i�H�ϥ� "(" �@���}�Y
      * (\\d{2}): �򱵵ۨ�ӼƦr
      * \\)? : �i�H�ϥ�")"����
      * [- ]? : �b�W�z�榡��i�H�ϥΨ��ܩʪ� "-".
      * (\\d{4}) : �A�򱵵ۥ|�ӼƦr
      * [- ]? : �i�H�ϥΨ��ܩʪ� "-" ����.
      * (\\d{4})$: �H�|�ӼƦr����.
      * �i�H���U�C�Ʀr�榡:
      * (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890  
     */
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
  public static boolean iswithin70(String text)
  {
    if (text.length()<= 70)
      return true;
    else
      return false;
  }
} 
