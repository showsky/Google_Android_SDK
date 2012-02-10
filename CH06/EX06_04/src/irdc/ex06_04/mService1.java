package irdc.ex06_04;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/* �ۭqmService���O�~��Service���O */
public class mService1 extends Service
{
  /* �إ�Handler����A�@��������ǻ� postDelayed���� */
  private Handler objHandler = new Handler();
  
  /* ���F�T�{�t�ΪA�Ȱ��污�p */
  private int intCounter=0;
  
  /* �����ܼ�mTasks��Runnable����A�@��Timer���� */
  private Runnable mTasks = new Runnable() 
  {
    /* ��������� */
    public void run()
    {
      /* ���Wcounter��ơA�@���I���A�Ȱ���ɶ��ѧO */
      intCounter++;
      
      /* �HLog����bLogCat�̿�Xlog�T���A�ʬݪA�Ȱ��污�p */
      Log.i("HIPPO", "Counter:"+Integer.toString(intCounter));
      
      /* �C1��I�sHandler.postDelayed��k���а��� */
      objHandler.postDelayed(mTasks, 1000);
    } 
  };
  
  @Override
  public void onStart(Intent intent, int startId)
  {
    // TODO Auto-generated method stub
    
    /* �A�ȶ}�l�A�Y�I�s�C1��I�smTasks����� */
    objHandler.postDelayed(mTasks, 1000);
    super.onStart(intent, startId);
  }

  @Override
  public void onCreate()
  {
    // TODO Auto-generated method stub
    super.onCreate();
  }
  
  @Override
  public IBinder onBind(Intent intent)
  {
    // TODO Auto-generated method stub
    
    /* IBinder��k��Service�غc�����мg����k */
    return null;
  }

  @Override
  public void onDestroy()
  {
    // TODO Auto-generated method stub
    
    /* ��A�ȵ����A����mTasks����� */
    objHandler.removeCallbacks(mTasks);
    super.onDestroy();
  }  
}
