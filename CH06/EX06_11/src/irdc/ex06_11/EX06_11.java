package irdc.ex06_11;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EX06_11 extends Activity 
{
  private TextView mTextView01;
  private TextView mTextView03;
  private EditText mEditText1;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*�]�wPhoneCallListener*/
    mPhoneCallListener phoneListener=new mPhoneCallListener(); 
    /*�]�wTelephonyManager�h���Telephony Severice*/
    TelephonyManager telMgr = (TelephonyManager)getSystemService(
        TELEPHONY_SERVICE);
    /*�]�wListen Call*/
    telMgr.listen(phoneListener, mPhoneCallListener.
        LISTEN_CALL_STATE); 
    
    /*�]�w��MTextView�BEditText*/ 
    mTextView01 = (TextView)findViewById(R.id.myTextView1); 
    mTextView03 = (TextView)findViewById(R.id.myTextView3);
    mEditText1 = (EditText)findViewById(R.id.myEditText1);
    
  } 
  /*�P�_PhoneStateListener�{�b�����A*/
  public class mPhoneCallListener extends PhoneStateListener 
  { 
    @Override 
    public void onCallStateChanged(int state, String incomingNumber) 
    { 
      // TODO Auto-generated method stub 
      switch(state)  
      {  
        /*�P�_����ثe�O�ݾ����A*/ 
        case TelephonyManager.CALL_STATE_IDLE: 
          mTextView01.setText(R.string.str_CALL_STATE_IDLE); 
          
          try
          { 
            AudioManager audioManager = (AudioManager)
                           getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) 
            { 
              /*�]�w������ݾ��ɡA�T�a�Ҧ������`*/ 
              audioManager.setRingerMode(AudioManager.
                  RINGER_MODE_NORMAL);               
              audioManager.getStreamVolume(
                  AudioManager.STREAM_RING);
            } 
          }         
          catch(Exception e)
          { 
           mTextView01.setText(e.toString()); 
           e.printStackTrace(); 
          }
          break;
          
        /*�P�_���A���q�ܤ�*/  
        case TelephonyManager.CALL_STATE_OFFHOOK: 
          mTextView01.setText(R.string.str_CALL_STATE_OFFHOOK); 
          break;
          
        /*�P�_���A���ӹq*/   
        case TelephonyManager.CALL_STATE_RINGING: 
          /*��ܲ{�b�ӹq���T��*/
          mTextView01.setText( 
              getResources().getText(R.string.str_CALL_STATE_RINGING)+ 
            incomingNumber);
         
          /*�P�_�P��J�q�ܬO�_���@�ˡA��@�ˮ��T�a�Ҧ��ର�R��*/ 
          if(incomingNumber.equals(mTextView03.getText().toString()))
          {                 
          try
          {             
            AudioManager audioManager = (AudioManager)
                            getSystemService(Context.AUDIO_SERVICE);
          if (audioManager != null)
            {
            /*�]�w�T�a�Ҧ����R��*/ 
            audioManager.setRingerMode(AudioManager.
                  RINGER_MODE_SILENT); 
            audioManager.getStreamVolume(
                  AudioManager.STREAM_RING);
            Toast.makeText(EX06_11.this, getString(R.string.str_msg) 
                ,Toast.LENGTH_SHORT).show(); 
            } 
          }
          catch(Exception e)
          { 
           mTextView01.setText(e.toString()); 
           e.printStackTrace();         
          break;
          }      
        } 
      }
      
      super.onCallStateChanged(state, incomingNumber);
      
      mEditText1.setOnKeyListener(new EditText.OnKeyListener()
      {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
          // TODO Auto-generated method stub
          /*�]�w�bEditText�̩ҿ�J����ƦP�B��ܦbTextView*/
          mTextView03.setText(mEditText1.getText());
          return false;
        }
      });
    }
  }
}