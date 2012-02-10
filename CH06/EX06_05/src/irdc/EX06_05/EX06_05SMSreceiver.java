package irdc.EX06_05;

/*���ݤޥ�BroadcastReceiver���O*/
import android.content.BroadcastReceiver; 
import android.content.Context; 
import android.content.Intent; 
import android.os.Bundle; 
/*���ݤޥ�telephoney.gsm.SmsMessage�Ӧ���²�T*/
import android.telephony.gsm.SmsMessage; 
/*���ݤޥ�Toast���O�ӧi���ϥΪ̦���²�T*/
import android.widget.Toast; 

/*�ۭq�~�Ӧ�BroadcastReceiver���O,��ť�t�ΪA�ȼs�����T�� */
public class EX06_05SMSreceiver extends BroadcastReceiver 
{ 
  /*�ŧi�R�A�r��,�èϥ�android.provider.Telephony.SMS_RECEIVED�@��Action��²�T���̾�*/
  private static final String mACTION = "android.provider.Telephony.SMS_RECEIVED"; 
  private String str_receive="����²�T!";
  
  @Override 
  public void onReceive(Context context, Intent intent) 
  {
    // TODO Auto-generated method stub 
	  Toast.makeText(context, str_receive.toString(), Toast.LENGTH_LONG).show(); 
    /*�P�_�Ǩ�Intent�O�_��²�T*/
    if (intent.getAction().equals(mACTION)) 
    { 
      /*�غc�@�r�궰�X�ܼ�sb*/
      StringBuilder sb = new StringBuilder(); 
      /*������Intent�ǨӪ����*/
      Bundle bundle = intent.getExtras(); 
      /*�P�_Intent�O�����*/
      if (bundle != null) 
      { 
        /* pdus�� android����²�T�Ѽ� identifier
         * �z�Lbundle.get("")�^�Ǥ@�]�tpdus������*/
        Object[] myOBJpdus = (Object[]) bundle.get("pdus"); 
        
        /*�غc²�T����array,�è̾ڦ��쪺������רӫإ�array���j�p*/
        SmsMessage[] messages = new SmsMessage[myOBJpdus.length];  
        
        for (int i = 0; i<myOBJpdus.length; i++) 
        {  
          messages[i] = SmsMessage.createFromPdu ((byte[]) myOBJpdus[i]);  
        } 
          
        /* �N�e�Ӫ�²�T�X�֦ۭq�T����StringBuilder�� */  
        for (SmsMessage currentMessage : messages) 
        {  
          sb.append("������Ӧ�:\n");  
          /* �ӰT�̪��q�ܸ��X */ 
          sb.append(currentMessage.getDisplayOriginatingAddress());  
          sb.append("\n------�ǨӪ�²�T------\n");  
          /* ���o�ǨӰT����BODY */  
          sb.append(currentMessage.getDisplayMessageBody()); 
          Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show(); 
        }  
      }       
      /* �HNotification(Toase)��ܨӰT�T��  */
      Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show(); 
       
      /* ��^�DActivity */ 
      Intent i = new Intent(context, EX06_05.class); 
      /*�ۭq�@Bundle*/
      Bundle mbundle = new Bundle(); 
      /*�N²�T�T���HputString()��k�s�J�ۭq��bundle��*/
      mbundle.putString("STR_INPUT",  sb.toString()); 
      /*�N�ۭqbundle�g�JIntent��*/
      i.putExtras(mbundle); 
      /*�]�wIntent��Flag�H�@�ӥ��s��task�Ӱ���*/
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
      context.startActivity(i); 
    } 
  } 
} 

