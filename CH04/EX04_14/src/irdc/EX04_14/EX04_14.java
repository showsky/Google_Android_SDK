package irdc.EX04_14;

import android.app.Activity;
import android.os.Bundle;
/*�o�̧ڭ̻ݭn�ϥ�Handler���O�PMessage���O�ӳB�z�����*/
import android.os.Handler;
import android.os.Message;
import android.widget.AnalogClock;
import android.widget.TextView;
/*�ݭn�ϥ�Java��Calendar�PThread���O�Ө��o�t�ήɶ�*/
import java.util.Calendar;
import java.lang.Thread;

public class EX04_14 extends Activity 
{
  /*�ŧi�@�`�Ƨ@���P�O�T����*/
  protected static final int GUINOTIFIER = 0x1234;
  /*�ŧi���widget�����ܼ�*/
  private TextView mTextView;
  public AnalogClock mAnalogClock;
  /*�ŧi�P�ɶ��������ܼ�*/
  public Calendar mCalendar;
  public int mMinutes;
  public int mHour;
  /*�ŧi����Handler�PThread�ܼ�*/
  public Handler mHandler;
  private Thread mClockThread;

	/** Called when the activity is first created. */
  
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*�z�LfindViewById���o���widget����*/
    mTextView=(TextView)findViewById(R.id.myTextView);
    mAnalogClock=(AnalogClock)findViewById(R.id.myAnalogClock);
    
    /*�z�LHandler�ӱ���������Ҷǻ����T���ç�sTextView*/
    mHandler = new Handler()
    {
      public void handleMessage(Message msg) 
      {
        /*�o�̬O�B�z�T������k*/
      	 switch (msg.what)
      	  { 
      	    case EX04_14.GUINOTIFIER:
      	    /* �b�o�B�z�nTextView����Show�ɶ����ƥ� */   	 		 
      	  	  mTextView.setText(mHour+" : "+mMinutes);
      	  	  break; 
      	  } 
      	  super.handleMessage(msg); 
      	  }
    };
    
    /*�z�L������ӫ�����o�t�ήɶ�*/
     mClockThread=new LooperThread();
     mClockThread.start();   
  }
    
    /*��g�@��Thread Class�Ψӫ�����o�t�ήɶ�*/ 	  
  	class LooperThread extends Thread
  	{
      public void run() 
      {
      	super.run();
        try
        {
        	do
        	{
        	/*���o�t�ήɶ�*/
        	long time = System.currentTimeMillis();
        	/*�z�LCalendar����Ө��o�p�ɻP����*/ 
    	  	  	final Calendar mCalendar = Calendar.getInstance();
    	  	  	mCalendar.setTimeInMillis(time);
    	  	  	mHour = mCalendar.get(Calendar.HOUR);
    	  	  	mMinutes = mCalendar.get(Calendar.MINUTE);
    	  	  	/*��������𮧤@��*/
        	Thread.sleep(1000); 
        	 /*���n����{��:���o�ɶ���o�X�T����Handler*/
        	 Message m = new Message();
           m.what = EX04_14.GUINOTIFIER;
           EX04_14.this.mHandler.sendMessage(m);       
        	}while(EX04_14.LooperThread.interrupted()==false);
        	/*��t�εo�X���_�T���ɰ���^��*/
        }
        catch(Exception e)
        {
         e.printStackTrace();
        }
      }      
    }
}