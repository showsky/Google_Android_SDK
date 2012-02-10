package irdc.ex06_16;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* ����android.intent.action.BOOT_COMPLETED��Receiver���O */
public class HippoStartupIntentReceiver extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {
    // TODO Auto-generated method stub
    
    /* ����Receiver�ɡA���w�}�Ҧ��{���]EX06_16.class�^ */
    Intent mBootIntent = new Intent(context, EX06_16.class);
    
    /* �]�wIntent�}�Ҭ�FLAG_ACTIVITY_NEW_TASK */ 
    mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    
    /* �NIntent�HstartActivity�ǰe���@�~�t�� */ 
    context.startActivity(mBootIntent);
  }
}
