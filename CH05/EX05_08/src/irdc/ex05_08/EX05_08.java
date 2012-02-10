package irdc.ex05_08;

/* import����class */
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EX05_08 extends Activity
{
  /*�ŧi�����ܼ�*/
  private NotificationManager myNotiManager;
  private Spinner mySpinner;
  private ArrayAdapter<String> myAdapter;
  private static final String[] status =
  { "�u�W","���}","���L��","���W�^��","���u" };
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    
    /* ��l�ƪ��� */
    myNotiManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    mySpinner=(Spinner)findViewById(R.id.mySpinner);
    myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,status);
    /* �M��myspinner_dropdown�ۭq�U�Կ��˦� */
    myAdapter.setDropDownViewResource(R.layout.myspinner_dropdown);
    /* �NArrayAdapter�[�JSpinner���� */
    mySpinner.setAdapter(myAdapter);

    /* �NmySpinner�[�JOnItemSelectedListener */
    mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
    {
      @Override
      public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3)
      {
        /* �̷ӿ�ܪ�item�ӧP�_�n�o���@��notification */
        if(status[arg2].equals("�u�W"))
        {
          setNotiType(R.drawable.msn,"�u�W");
        }
        else if(status[arg2].equals("���}"))
        {
          setNotiType(R.drawable.away,"���}");
        }
        else if(status[arg2].equals("���L��"))
        {
          setNotiType(R.drawable.busy,"���L��");
        }
        else if(status[arg2].equals("���W�^��"))
        {
          setNotiType(R.drawable.min,"���W�^��");
        }
        else
        {
          setNotiType(R.drawable.offine,"���u");
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0)
      {
      }
    });
  }
  
  /* �o�XNotification��method */
  private void setNotiType(int iconId, String text)
  {
    /* �إ߷s��Intent�A�@���I��Notification�d�����ɡA
     * �|���檺Activity */ 
    Intent notifyIntent=new Intent(this,EX05_08_1.class);  
    notifyIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
    /* �إ�PendingIntent�@���]�w�������檺Activity */ 
    PendingIntent appIntent=PendingIntent.getActivity(EX05_08.this,0,notifyIntent,0); 
       
    /* �إ�Notication�A�ó]�w�����Ѽ� */ 
    Notification myNoti=new Notification();
    /* �]�wstatusbar��ܪ�icon */
    myNoti.icon=iconId;
    /* �]�wstatusbar��ܪ���r�T�� */
    myNoti.tickerText=text;
    /* �]�wnotification�o�ͮɦP�ɵo�X�w�]�n�� */
    myNoti.defaults=Notification.DEFAULT_SOUND;
    /* �]�wNotification�d�������Ѽ� */
    myNoti.setLatestEventInfo(EX05_08.this,"MSN�n�J���A",text,appIntent);  
    /* �e�XNotification */
    myNotiManager.notify(0,myNoti);
  } 
}