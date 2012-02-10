package irdc.ex05_21;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class EX05_21 extends Activity
{
  private Button mButton01;
  private ListView mListView01;
  private ArrayAdapter<String> aryAdapter1;
  private ArrayList<String> arylistTask;
  
  /* ���O�����]�w���^�̦h�X����Task�ƶq */
  private int intGetTastCounter=30;
  
  /* ���O����ActivityManager���� */
  private ActivityManager mActivityManager;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mButton01 = (Button)findViewById(R.id.myButton1);
    mListView01 = (ListView)findViewById(R.id.myListView1);
    
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        try
        {
          /* ActivityManager����V�t�Ψ��oACTIVITY_SERVICE */
          mActivityManager = (ActivityManager)EX05_21.this.getSystemService(ACTIVITY_SERVICE);
          
          arylistTask = new ArrayList<String>();
          
          /* �HgetRunningTasks��k���^���b���椤���{��TaskInfo */
          List<ActivityManager.RunningTaskInfo> mRunningTasks = mActivityManager.getRunningTasks(intGetTastCounter);
          //List<ActivityManager.RunningServiceInfo> mRunningTasks = mActivityManager.getRunningServices(intGetTastCounter);
          
          int i = 1;
          /* �H�j���baseActivity�覡���o�u�@�W�ٻPID */ 
          for (ActivityManager.RunningTaskInfo amTask : mRunningTasks)
          //for (ActivityManager.RunningServiceInfo amTask : mRunningTasks)
          {
            /* baseActivity.getClassName���X����u�@�W�� */
            arylistTask.add("" + (i++) + ": "+ amTask.baseActivity.getClassName() + "(ID=" + amTask.id +")"); 
            //arylistTask.add("" + (i++) + ": "+ amTask.process + "(ID=" + amTask.pid +")");
          }
          aryAdapter1 = new ArrayAdapter<String>(EX05_21.this, R.layout.simple_list_item_1, arylistTask);
          if(aryAdapter1.getCount()==0)
          {
            /* ��S��������檺�u�@�A�h���ܰT�� */
            mMakeTextToast
            (
              getResources().getText(R.string.str_err_no_running_task).toString(),
              //getResources().getText(R.string.str_err_no_running_service).toString(),
              true
            );
          }
          else
          {
            /* �o�{�I�����檺�u�@�{���A�HListView Widget���C�e�{ */
            mListView01.setAdapter(aryAdapter1);
          }
        }
        catch(SecurityException e)
        {
          /* ��LGET_TASKS�v����(SecurityException�ҥ~)���ܰT�� */
          mMakeTextToast
          (
            getResources().getText(R.string.str_err_permission).toString(),
            true
          );
        }
      }
    });
    
    /* ��User�b����u�@��ܮɪ��ƥ�B�z */
    mListView01.setOnItemSelectedListener(new ListView.OnItemSelectedListener()
    {
      @Override
      public void onItemSelected(AdapterView<?> parent, View v, int id, long arg3)
      {
        // TODO Auto-generated method stub
        /* �ѩ�N����u�@�H�}�C�s��A�G�Hid���X�}�C�����W�� */
        mMakeTextToast(arylistTask.get(id).toString(),false);
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0)
      {
        // TODO Auto-generated method stub
        
      }
    });
    
    /* ��User�b����u�@�W�I���ɪ��ƥ�B�z */
    mListView01.setOnItemClickListener(new ListView.OnItemClickListener()
    {
      @Override
      public void onItemClick(AdapterView<?> parent, View v, int id,  long arg3)
      {
        // TODO Auto-generated method stub
        /* �ѩ�N����u�@�H�}�C�s��A�G�Hid���X�}�C�����W�� */
        mMakeTextToast(arylistTask.get(id).toString(), false);
      }
    });
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(EX05_21.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(EX05_21.this, str, Toast.LENGTH_SHORT).show();
    }
  }
}