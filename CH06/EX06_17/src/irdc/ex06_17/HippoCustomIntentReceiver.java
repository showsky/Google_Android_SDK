package irdc.ex06_17;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* �ۭq�~�Ӧ�BroadcastReceiver���O�A��ť�ۭq�t�ΪA�ȼs�����T�� */
public class HippoCustomIntentReceiver extends BroadcastReceiver
{
  /* �ۭq���@��Intent Filter��ACTION�T�� */
  public static final String HIPPO_SERVICE_IDENTIFIER = "HIPPO_ON_SERVICE_001";
  
  @Override
  public void onReceive(Context context, Intent intent)
  {
    // TODO Auto-generated method stub
    if(intent.getAction().toString().equals(HIPPO_SERVICE_IDENTIFIER))
    {
      /* �HBundle����Ѷ}�ǨӪ��Ѽ� */
      Bundle mBundle01 = intent.getExtras();
      String strParam1="";
      
      /* �YBundle���󤣬��ŭȡA���X�Ѽ� */
      if (mBundle01 != null)
      {
        /* �N���X��STR_PARAM01�ѼơA�s���strParam1�r�ꤤ */
        strParam1 = mBundle01.getString("STR_PARAM01");
      }
      
      /* �I�s����Activity�A�����D�{�� */
      Intent mRunPackageIntent = new Intent(context, EX06_17.class); 
      mRunPackageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      if(strParam1!="")
      {
        /* ���s�ʸ˰Ѽơ]SMS�T���^�^�� */
        mRunPackageIntent.putExtra("STR_PARAM01", strParam1);
      }
      else
      {
        mRunPackageIntent.putExtra("STR_PARAM01", "From Service notification...");
      }
      context.startActivity(mRunPackageIntent);
    }
  } 

}
