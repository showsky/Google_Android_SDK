package irdc.ex06_17;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EX06_17 extends Activity
{
  private Button mButton01,mButton02;
  private TextView mTextView01;
    
  /* ���V²�T�ѧO����r */
  private static String strSecretWord="IRDC";
  
  /* �s���T���[Delimiter�W�^���ѧOTAG */
  public static String strDelimiter1="<delimiter1>";
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    
    try
    {
      /* �إ�Bundle����A�P�_�O�_���ǤJ�ʸ˰Ѽ� */
      Bundle mBundle01 = this.getIntent().getExtras();
      if (mBundle01 != null)
      {
        /* ���o�ѼƦW��STR_PARAM01 */
        String strParam01 = mBundle01.getString("STR_PARAM01");     
        String[] aryTemp01 = null;
        
        /* �o�{���Ӧۼs�����T���ѼơA��ܨӦۦۤv���s���ƥ� */
        if(eregi(strDelimiter1,strParam01))
        {
          /* �P�_strDelimiter�A�åH�}�C�s�� */
          aryTemp01 = strParam01.split(strDelimiter1);
          
          /* �P�_�}�C����[0]�O�_���x�W���q�ܸ��X�H�ι�²�T����r�i���ˬd */
          if(isTWCellPhone(aryTemp01[0]) && eregi(strSecretWord,aryTemp01[1]) && aryTemp01.length==2)
          {
            /* ��ܤw���������V²�T����r */
            mMakeTextToast
            (
              getResources().getText(R.string.str_cmd_sms_catched).toString(), false
            );
            
            /* ��o�e²�TUser���q�ܡA��O�^��²�T���q�ܸ��X */
            String strDestAddress = aryTemp01[0];
            
            /* ���ռ����������O�_���Q�e�F��Port Number */
            //String strDestAddress = "5556";
            
            /* �n�^�Ǫ�SMS BODY���e */
            String strMessage = getResources().getText(R.string.str_cmd_sms_returned).toString();
            
            /* �إ�SmsManager���� */
            SmsManager smsManager = SmsManager.getDefault();
            
            // TODO Auto-generated method stub
            try
            {
              /* �إ�PendingIntent�@��sentIntent�Ѽ� */
              PendingIntent mPI = PendingIntent.getBroadcast(EX06_17.this, 0, new Intent(), 0);
              
              /* �����ǰe²�T */
              smsManager.sendTextMessage(strDestAddress, null, strMessage, mPI, null);
              
              /* �t�Φ۰ʦ^��²�T����A�HToast��ܵ��G */
              mMakeTextToast
              (
                getResources().getText(R.string.str_cmd_sms_sending).toString()+
                strDestAddress,
                true
              );
            }
            catch(Exception e)
            {
              e.printStackTrace();
            }
            finish();
          }
          else
          {
            /* �Y�S���o�{�i�ѧO���ӹq�q�ܸ��X */
            /* �P�_�O�_���Ӧۦۤv���ۭqSMS Receiver�s���T�� */
            if(eregi(strDelimiter1,strParam01))
            {
              aryTemp01 = strParam01.split(strDelimiter1);
              mTextView01.setText(aryTemp01[1].toString());
            }
            else
            {
              /* �S���ۤv���s���ƥ�A�º鬰�@��SMS²�T */
              mTextView01.setText(strParam01);
            }
          }
        }
        else
        {
          if(eregi(strDelimiter1,strParam01))
          {
            aryTemp01 = strParam01.split(strDelimiter1);
            mTextView01.setText(aryTemp01[1].toString());
          }
          else
          {
            /* �S���ۤv���s���ƥ�A�º鬰�@��SMS²�T */
            mTextView01.setText(strParam01);
          }
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    
    mButton01 = (Button)findViewById(R.id.myButton1);
    
    /* �}�l��ť���V²�T�A��(mService1)�Ұ� */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        Intent i = new Intent( EX06_17.this, mService1.class ); 
        i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK ); 
        startService(i);
        mMakeTextToast(getResources().getText(R.string.str_service_online).toString(),true);
        finish();
      }
    });
    
    mButton02 = (Button)findViewById(R.id.myButton2);
    
    /* �פ��ť���V²�T�A��(mService1) */
    mButton02.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        Intent i = new Intent( EX06_17.this, mService1.class );
        stopService(i);
        mMakeTextToast(getResources().getText(R.string.str_service_offline).toString(),true);
      }
    });
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(EX06_17.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(EX06_17.this, str, Toast.LENGTH_SHORT).show();
    }
  }
  
  /* �P�_������²�T�O�_��������r��²�T */
  public static boolean isCommandSMS(String strPat, String strSMS)
  {
    String strPattern = "(?i)"+strPat;
    Pattern p = Pattern.compile(strPattern);
    Matcher m = p.matcher(strSMS);
    return m.find();
  }
  
  /* �ۭq���W��F���A�L���j�p�g���r�� */
  public static boolean eregi(String strPat, String strUnknow)
  {
    /* ��k�@ */
    String strPattern = "(?i)"+strPat;
    Pattern p = Pattern.compile(strPattern);
    Matcher m = p.matcher(strUnknow);
    return m.find();
    
    /* ��k�G */
    /*
    if(strUnknow.toLowerCase().indexOf(strPat.toLowerCase())>=0)
    {
      return true;
    }
    else
    {
      return false;
    }
    */
  }
  
  /* �P�_²�T�o�e�̪��ӹq�A�O�_���x�W��ʹq�ܮ榡 */
  public static boolean isTWCellPhone(String strUnknow)
  {
    /*
     * (0935)456-789, 0935-456-789, 1234567890, (0935)-456-789
     * */
    String strPattern = "^\\(?(\\d{4})\\)?[-]?(\\d{3})[-]?(\\d{3})$";
    Pattern p = Pattern.compile(strPattern);
    Matcher m = p.matcher(strUnknow);
    return m.matches();
  }
  
  /* �P�_²�T�o�e�̪��ӹq�A�O�_�������ʹq�ܮ榡 */
  public static boolean isUSCellPhone(String strUnknow)
  {
    /*
     * (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890
     * */
    String strPattern = "^\\(?(\\d{3})\\)?[-]?(\\d{3})[-]?(\\d{4})$";
    Pattern p = Pattern.compile(strPattern);
    Matcher m = p.matcher(strUnknow);
    return m.matches();
  }
  
  @Override
  protected void onResume()
  {
    // TODO Auto-generated method stub
    super.onResume();
  }
  
  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    super.onPause();
  }
}