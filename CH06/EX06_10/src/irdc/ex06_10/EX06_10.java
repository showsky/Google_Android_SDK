package irdc.ex06_10;

/* import����class */
import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EX06_10 extends Activity
{
  /* �ŧi�����ܼ� */
  TextView setTime1;
  TextView setTime2;
  Button mButton1;
  Button mButton2;
  Button mButton3;
  Button mButton4;
  Calendar c=Calendar.getInstance();
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    
    /* �H�U���u�T�@�����x�����]�w */         
    setTime1=(TextView) findViewById(R.id.setTime1);
    /* �u�T�@�����x�����]�wButton */
    mButton1=(Button)findViewById(R.id.mButton1);
    mButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /* ���o���U���s�ɪ��ɶ�����TimePickerDialog���w�]�� */
    	  c.setTimeInMillis(System.currentTimeMillis());
        int mHour=c.get(Calendar.HOUR_OF_DAY);
        int mMinute=c.get(Calendar.MINUTE);
        
        /* ���XTimePickerDialog�ӳ]�w�ɶ� */
        new TimePickerDialog(EX06_10.this,
          new TimePickerDialog.OnTimeSetListener()
          {                
            public void onTimeSet(TimePicker view,int hourOfDay,int minute)
            {
              /* ���o�]�w�᪺�ɶ��A���@��]��0 */
              c.setTimeInMillis(System.currentTimeMillis());
              c.set(Calendar.HOUR_OF_DAY,hourOfDay);
              c.set(Calendar.MINUTE,minute);
              c.set(Calendar.SECOND,0);
              c.set(Calendar.MILLISECOND,0);
              
              /* ���w�x���]�w�ɶ���ɭn����CallAlarm.class */
              Intent intent = new Intent(EX06_10.this, CallAlarm.class);
              /* �إ�PendingIntent */
              PendingIntent sender=PendingIntent.getBroadcast(EX06_10.this,
                            0, intent, 0);
              /* AlarmManager.RTC_WAKEUP�]�w�A�Ȧb�t�Υ�v�ɦP�˷|����
               * �Hset()�]�w��PendingIntent�u�|����@��
               * */
              AlarmManager am;
              am = (AlarmManager)getSystemService(ALARM_SERVICE);
              am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
              /* ��s��ܪ��]�w�x���ɶ� */
              String tmpS=format(hourOfDay)+"�G"+format(minute);
              setTime1.setText(tmpS);
              /* �HToast���ܳ]�w�w���� */
              Toast.makeText(EX06_10.this,"�]�w�x���ɶ���"+tmpS,Toast.LENGTH_SHORT)
                .show();
            }          
          },mHour,mMinute,true).show();
      }
    });
        
    /* �u�T�@�����x��������Button */
    mButton2=(Button) findViewById(R.id.mButton2);
    mButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        Intent intent = new Intent(EX06_10.this, CallAlarm.class);
        PendingIntent sender=PendingIntent.getBroadcast(EX06_10.this,
                      0, intent, 0);
        /* ��AlarmManager������ */
        AlarmManager am;
        am =(AlarmManager)getSystemService(ALARM_SERVICE);
        am.cancel(sender);
        /* �HToast���ܤw�R���]�w�A�ç�s��ܪ��x���ɶ� */
        Toast.makeText(EX06_10.this,"�x���ɶ��Ѱ�", Toast.LENGTH_SHORT).show();
        setTime1.setText("�ثe�L�]�w");
      }
    });
        
    /* �H�U�������T�_���x�����]�w */         
    setTime2=(TextView) findViewById(R.id.setTime2);
    /* create�����T�_���x�����]�w�e�� */  
    /* �ޥ�timeset.xml��Layout */
    LayoutInflater factory = LayoutInflater.from(this);
    final View setView = factory.inflate(R.layout.timeset,null);
    final TimePicker tPicker=(TimePicker)setView.findViewById(R.id.tPicker);
    tPicker.setIs24HourView(true);
    
    /* create�����T�_�x�����]�wDialog */   
    final AlertDialog di=new AlertDialog.Builder(EX06_10.this)
          .setIcon(R.drawable.clock)
          .setTitle("�]�w")
          .setView(setView)
          .setPositiveButton("�T�w",new DialogInterface.OnClickListener()
           {
             public void onClick(DialogInterface dialog, int which)
             {
               /* ���o�]�w�����j��� */
               EditText ed=(EditText)setView.findViewById(R.id.mEdit);
               int times=Integer.parseInt(ed.getText().toString())*1000;
               /* ���o�]�w���}�l�ɶ��A��β@��]��0 */
               c.setTimeInMillis(System.currentTimeMillis());
               c.set(Calendar.HOUR_OF_DAY,tPicker.getCurrentHour());
               c.set(Calendar.MINUTE,tPicker.getCurrentMinute());
               c.set(Calendar.SECOND,0);
               c.set(Calendar.MILLISECOND,0);
               
               /* ���w�x���]�w�ɶ���ɭn����CallAlarm.class */
               Intent intent = new Intent(EX06_10.this, CallAlarm.class);
               PendingIntent sender = PendingIntent.getBroadcast(EX06_10.this,
                             1, intent, 0);
               /* setRepeating()�i���x�����а��� */
               AlarmManager am;
               am = (AlarmManager)getSystemService(ALARM_SERVICE);
               am.setRepeating(AlarmManager.RTC_WAKEUP,
                         c.getTimeInMillis(),times,sender);
               /* ��s��ܪ��]�w�x���ɶ� */    
               String tmpS=format(tPicker.getCurrentHour())+"�G"+
                           format(tPicker.getCurrentMinute());
               setTime2.setText("�]�w�x���ɶ���"+tmpS+"�}�l�A���ж��j��"+times/1000+"��");
               /* �HToast���ܳ]�w�w���� */
               Toast.makeText(EX06_10.this,"�]�w�x���ɶ���"+tmpS+"�}�l�A���ж��j��"+times/1000+"��",
            		   Toast.LENGTH_SHORT).show();
             }
           })
          .setNegativeButton("����",new DialogInterface.OnClickListener()
           {
             public void onClick(DialogInterface dialog, int which)
             {
             }
           }).create();
    
    /* �����T�_���x�����]�wButton */
    mButton3=(Button) findViewById(R.id.mButton3);
    mButton3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /* ���o���U���s�ɪ��ɶ�����tPicker���w�]�� */
        c.setTimeInMillis(System.currentTimeMillis());
        tPicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        tPicker.setCurrentMinute(c.get(Calendar.MINUTE));
        /* ���X�]�w�e��di */
        di.show();
      }
    });
        
    /* �����T�_���x��������Button */
    mButton4=(Button) findViewById(R.id.mButton4);
    mButton4.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        Intent intent = new Intent(EX06_10.this, CallAlarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(EX06_10.this,
                      1, intent, 0);
        /* ��AlarmManager������ */
        AlarmManager am;
        am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.cancel(sender);
        /* �HToast���ܤw�R���]�w�A�ç�s��ܪ��x���ɶ� */
        Toast.makeText(EX06_10.this,"�x���ɶ��Ѱ�",Toast.LENGTH_SHORT).show();
        setTime2.setText("�ثe�L�]�w");
      }
    });
  }
  
  /* ����ɶ���ܨ��ƪ�method */
  private String format(int x)
  {
    String s=""+x;
    if(s.length()==1) s="0"+s;
    return s;
  }
}