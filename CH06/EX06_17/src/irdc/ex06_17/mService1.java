package irdc.ex06_17;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.gsm.SmsMessage;
import android.util.Log;

/* �ۭq�t�ΪA�ȡA���ť��T���ƥ�ɡA�h�s���ۭq�T�� */
public class mService1 extends Service
{
  /* Handler���� */
  private Handler objHandler = new Handler();
  private int intCounter=0;
  
  /* �ۭq�s���ѧOACTIOIN�`�� */
  public static final String HIPPO_SERVICE_IDENTIFIER = "HIPPO_ON_SERVICE_001";
  
  /* �t�α���²�T���s��ACTION�`�� */
  private static final String HIPPO_SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
  
  /* �ۭq²�TmSMSReceiver���� */
  private mSMSReceiver mReceiver01;
  
  /* �O�_��debug�Ҧ��A�ݭn�bconsole�̿�XLog */
  private static boolean bIfDebug = true;
  
  private Runnable mTasks = new Runnable() 
  { 
    public void run() 
    { 
      intCounter++;
      Log.i("HIPPO", "Counter:"+Integer.toString(intCounter));
      objHandler.postDelayed(mTasks, 1000); 
    } 
  };
  
  @Override
  public void onStart(Intent intent, int startId)
  {
    // TODO Auto-generated method stub
    
    if(bIfDebug)
    {
      objHandler.postDelayed(mTasks, 1000);
    }
    super.onStart(intent, startId);
  }

  @Override
  public void onCreate()
  {
    // TODO Auto-generated method stub
    
    /* �V�t�ε��Ureceiver�A��ť�t��²�T�s���ƥ� */
    IntentFilter mFilter01;
    mFilter01 = new IntentFilter(HIPPO_SMS_ACTION);
    mReceiver01 = new mSMSReceiver();
    registerReceiver(mReceiver01, mFilter01);
    
    super.onCreate();
  }
  
  @Override
  public IBinder onBind(Intent intent)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void onDestroy()
  {
    // TODO Auto-generated method stub
    
    objHandler.removeCallbacks(mTasks);
    unregisterReceiver(mReceiver01);
    super.onDestroy();
  }
  
  /* ����t��²�T�s���ƥ�᪺�ƥ�B�z */
  public class mSMSReceiver extends BroadcastReceiver
  {
    @Override
    public void onReceive(Context context, Intent intent)
    {
      // TODO Auto-generated method stub
      
      /* �P�_�O�_���t�μs����²�TACTION */
      if (intent.getAction().equals(HIPPO_SMS_ACTION))
      {
        StringBuilder sb = new StringBuilder();
        Bundle bundle = intent.getExtras();
        
        if (bundle != null)
        {
          /* ��ѻP�ѧOSMS²�T */
          Object[] myOBJpdus = (Object[]) bundle.get("pdus");
          SmsMessage[] messages = new SmsMessage[myOBJpdus.length]; 
          for (int i = 0; i<myOBJpdus.length; i++)
          { 
            messages[i] = SmsMessage.createFromPdu ((byte[]) myOBJpdus[i]); 
          }
          //Log.i(LOG_TAG, "[SMSApp Bundle] " + bundle.toString()); 
           
          /* �N�e�Ӫ�²�T�X�֦ۭq�T����StringBuilder�� */ 
          for (SmsMessage currentMessage : messages)
          {
            sb.append(currentMessage.getDisplayOriginatingAddress());
            /* �b�q�ܻPSMS²�TBODY�����A�[�W���jTAG */
            sb.append(EX06_17.strDelimiter1);
            sb.append(currentMessage.getDisplayMessageBody());
          }
          
          /* �V�t�μs���ۭq�T�� */
          Intent i = new Intent(HIPPO_SERVICE_IDENTIFIER);
          i.putExtra("STR_PARAM01", sb.toString());
          
          /* �HsendBroadcast�e�X�s���T�� */
          sendBroadcast(i);
        }
      }
      else
      {
        Intent i = new Intent(HIPPO_SERVICE_IDENTIFIER);
        i.putExtra("STR_PARAM01", intent.getAction().toString());
        sendBroadcast(i);
      }
    }
  }
}