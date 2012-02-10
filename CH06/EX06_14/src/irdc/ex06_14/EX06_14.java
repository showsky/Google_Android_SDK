package irdc.ex06_14;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EX06_14 extends Activity
{
  /* �إߨ��mServiceReceiver����A�@�����O�����ܼ� */
  private mServiceReceiver mReceiver01, mReceiver02;
  private Button mButton1;
  private TextView mTextView01;
  private EditText mEditText1, mEditText2;
  
  /* �ۭqACTION�`�ơA�@���s����Intent Filter�ѧO�`�� */
  private static String SMS_SEND_ACTIOIN = "SMS_SEND_ACTIOIN";
  private static String SMS_DELIVERED_ACTION = "SMS_DELIVERED_ACTION";
    
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    
    /* �q�ܸ��X */
    mEditText1 = (EditText) findViewById(R.id.myEditText1);
    
    /* ²�T���e */
    mEditText2 = (EditText) findViewById(R.id.myEditText2);
    mButton1 = (Button) findViewById(R.id.myButton1);
    
    //mEditText1.setText("+886935123456");
    /* �]�w�w�]��5556��ܲĤG�Ӽ�������Port */
    mEditText1.setText("5556");
    mEditText2.setText("Hello DavidLanz!");
    
    /* �o�eSMS²�T���s�ƥ�B�z */
    mButton1.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        
        /* ���o�e���q�ܸ��X */
        String strDestAddress = mEditText1.getText().toString();
        
        /* ���o�e��²�T���e */
        String strMessage = mEditText2.getText().toString();
        
        /* �إ�SmsManager���� */
        SmsManager smsManager = SmsManager.getDefault();
        
        // TODO Auto-generated method stub
        try
        {
          /* �إߦۭqAction�`�ƪ�Intent(��PendingIntent�ѼƤ���) */
          Intent itSend = new Intent(SMS_SEND_ACTIOIN);
          Intent itDeliver = new Intent(SMS_DELIVERED_ACTION);
          
          /* sentIntent�ѼƬ��ǰe�ᱵ�����s���T��PendingIntent */
          PendingIntent mSendPI = PendingIntent.getBroadcast(getApplicationContext(), 0, itSend, 0);
          
          /* deliveryIntent�ѼƬ��e�F�ᱵ�����s���T��PendingIntent */
          PendingIntent mDeliverPI = PendingIntent.getBroadcast(getApplicationContext(), 0, itDeliver, 0);
          
          /* �o�eSMS²�T�A�`�N�˼ƪ����PendingIntent�Ѽ� */
          smsManager.sendTextMessage(strDestAddress, null, strMessage, mSendPI, mDeliverPI);
          mTextView01.setText(R.string.str_sms_sending);
        }
        catch(Exception e)
        {
          mTextView01.setText(e.toString());
          e.printStackTrace();
        }
      }
    });
  }
  
  /* �ۭqmServiceReceiver�мgBroadcastReceiver��ť²�T���A�T�� */
  public class mServiceReceiver extends BroadcastReceiver
  {
    @Override
    public void onReceive(Context context, Intent intent)
    {
      // TODO Auto-generated method stub
      
      try
      {
        /* android.content.BroadcastReceiver.getResultCode()��k */
        switch(getResultCode())
        {
          case Activity.RESULT_OK:
            /* �o�e²�T���\ */
            //mTextView01.setText(R.string.str_sms_sent_success);
            mMakeTextToast
            (
              getResources().getText(R.string.str_sms_sent_success).toString(),
              true
            );
            break;
          case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
            /* �o�e²�T���� */
            //mTextView01.setText(R.string.str_sms_sent_failed);
            mMakeTextToast
            (
              getResources().getText(R.string.str_sms_sent_failed).toString(),
              true
            );
            break;
          case SmsManager.RESULT_ERROR_RADIO_OFF:
            break;
          case SmsManager.RESULT_ERROR_NULL_PDU:
            break;
        }        
      }
      catch(Exception e)
      {
        mTextView01.setText(e.toString());
        e.getStackTrace();
      }
    }
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(EX06_14.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(EX06_14.this, str, Toast.LENGTH_SHORT).show();
    }
  }
  
  @Override
  protected void onResume()
  {
    // TODO Auto-generated method stub
    
    /* �ۭqIntentFilter��SENT_SMS_ACTIOIN Receiver */
    IntentFilter mFilter01;
    mFilter01 = new IntentFilter(SMS_SEND_ACTIOIN);
    mReceiver01 = new mServiceReceiver();
    registerReceiver(mReceiver01, mFilter01);
    
    /* �ۭqIntentFilter��DELIVERED_SMS_ACTION Receiver */
    mFilter01 = new IntentFilter(SMS_DELIVERED_ACTION);
    mReceiver02 = new mServiceReceiver();
    registerReceiver(mReceiver02, mFilter01);
    
    super.onResume();
  }
  
  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    
    /* �������U�ۭqReceiver */
    unregisterReceiver(mReceiver01);
    unregisterReceiver(mReceiver02);
    
    super.onPause();
  }
}