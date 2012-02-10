package idrc.ex06_07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class EX06_07 extends Activity
{
  private TextView mTextView1;
  private String mEditText01 ="IRDC@gmail.com";
  private String strEmailSubject = "You have phone!!"; 
    
    /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mPhoneCallListener phoneListener=new mPhoneCallListener();  
    /*�]�wTelephonyManager�h����t��TELEPHONY_SERVICE*/
    TelephonyManager telMgr = (TelephonyManager)getSystemService
                 (TELEPHONY_SERVICE); 
    telMgr.listen(phoneListener, mPhoneCallListener.
                 LISTEN_CALL_STATE); 
     
    mTextView1 = (TextView)findViewById(R.id.myTextView1);
  }
  
  /*�ϥ�PhoneCallListener�ӱҰʨƥ�*/
  public class mPhoneCallListener extends PhoneStateListener 
  { 
    
    @Override 
    public void onCallStateChanged(int state, String incomingNumber) 
    { 
      // TODO Auto-generated method stub 
      switch(state)  
      {  
        /*���o�q�ܫݾ����A*/
        case TelephonyManager.CALL_STATE_IDLE: 
          mTextView1.setText(R.string.str_CALL_STATE_IDLE); 
          break;
        /*���o�q�ܳq�ܪ��A*/  
        case TelephonyManager.CALL_STATE_OFFHOOK: 
          mTextView1.setText(R.string.str_CALL_STATE_OFFHOOK); 
          break;
        /*���o�ӹq���A*/ 
        case TelephonyManager.CALL_STATE_RINGING: 
          mTextView1.setText                  
          ( 
            /*��ܨӹq���X*/  
            getResources().getText(R.string.str_CALL_STATE_RINGING)+ 
            incomingNumber 
          ); 
          
          /*�]�w���q�ܨӹq�ɡA�o�eE-mail*/
          Intent mEmailIntent = new Intent(android.content.Intent
                    .ACTION_SEND);  
          mEmailIntent.setType("plain/text");
          /*�]�wE-mail����H���H�c*/
          mEmailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                    new String[]{mEditText01.toString()});
          /*�]�wE-mail�����D*/
          mEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                    strEmailSubject); 
          /*�]�wE-mail�����e*/
          mEmailIntent.putExtra(android.content.Intent.EXTRA_TEXT, 
                    R.string.str_EmailBody+incomingNumber);
          /*��ܵo�H��*/
          startActivity(Intent.createChooser(mEmailIntent, 
                    getResources().getString(R.string.str_message)));
          
          break;  
        default: 
          break; 
      }  
      super.onCallStateChanged(state, incomingNumber);
    }    
  }
}