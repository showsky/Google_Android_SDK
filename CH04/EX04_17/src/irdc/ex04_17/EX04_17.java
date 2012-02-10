package irdc.ex04_17;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EX04_17 extends Activity
{
  private TextView mTextView01;
  private Button mButton01;
  private ProgressBar mProgressBar01;
  public int intCounter=0;
  
  /* �ۭqHandler�T���N�X�A�ΥH�@���ѧO�ƥ�B�z */
  protected static final int GUI_STOP_NOTIFIER = 0x108;
  protected static final int GUI_THREADING_NOTIFIER = 0x109;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mButton01 = (Button)findViewById(R.id.myButton1); 
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    
    /* �]�wProgressBar widget���� */
    mProgressBar01 = (ProgressBar)findViewById(R.id.myProgressBar1);
    
    /* �I�ssetIndeterminate��k����indeterminate�Ҧ���false */
    mProgressBar01.setIndeterminate(false);
    
    /* ����U���s��A�}�l������u�@ */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        
        /* ���U���s��ProgressBar��� */
        mTextView01.setText(R.string.str_progress_start);
        
        /* �N���ê�ProgressBar��ܥX�� */
        mProgressBar01.setVisibility(View.VISIBLE);
        
        /* ���wProgress���̦h100 */
        mProgressBar01.setMax(100);
        
        /* ��lProgress��0 */
        mProgressBar01.setProgress(0);
        
        /* �_�l�@�Ӱ���� */
        new Thread(new Runnable()
        {
          public void run()
          {
            /* �w�]0��9�A�@����10�����j��ԭz */
            for (int i=0;i<10;i++)
            {
              try
              {
                /* �����ܼơA�ΥH�ѧO���J�i�� */
                intCounter = (i+1)*20;
                /* �C����@���j��A�Y�Ȱ�1�� */
                Thread.sleep(1000);
                
                /* ��Thread����5�����ܰ��浲�� */
                if(i==4)
                {
                  /* �HMessage����A�ǻ��ѼƵ�Handler */
                  Message m = new Message();
                  
                  /* �Hwhat�ݩʫ��wUser�ۭq */
                  m.what = EX04_17.GUI_STOP_NOTIFIER;
                  EX04_17.this.myMessageHandler.sendMessage(m);
                  break;
                }
                else
                {
                  Message m = new Message();
                  m.what = EX04_17.GUI_THREADING_NOTIFIER;
                  EX04_17.this.myMessageHandler.sendMessage(m); 
                }
              }
              catch(Exception e)
              {
                e.printStackTrace();
              }
            }
          }
        }).start();
      }
    });
  }
  
  /* Handler�غc����A�|��ť�ǨӪ��T���N�X */
  Handler myMessageHandler = new Handler()
  {
    // @Override 
    public void handleMessage(Message msg)
    { 
      switch (msg.what)
      { 
        /* ����o�ѧO�� ���}������ɩҨ��o���T�� */
        case EX04_17.GUI_STOP_NOTIFIER:
          
          /* ��ܰ���פF */
          mTextView01.setText(R.string.str_progress_done);
          
          /* �]�wProgressBar Widget������ */
          mProgressBar01.setVisibility(View.GONE);
          Thread.currentThread().interrupt();
          break;
          
        /* ����o�ѧO�� ����b��������ɩҨ��o���T�� */
        case EX04_17.GUI_THREADING_NOTIFIER:
          if(!Thread.currentThread().isInterrupted())
          {
            mProgressBar01.setProgress(intCounter);
            /* �N��ܶi����ܩ�TextView�� */
            mTextView01.setText
            (
              getResources().getText(R.string.str_progress_start)+"("+Integer.toString(intCounter)+"%)\n"+"Progress:"+Integer.toString(mProgressBar01.getProgress())+"\n"+"Indeterminate:"+Boolean.toString(mProgressBar01.isIndeterminate())
            );
          }
          break;
      } 
      super.handleMessage(msg); 
    }
  };
}