package irdc.ex05_22;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EX05_22 extends Activity
{
  private TextView mTextView01;
  private Button mButton01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /* ��k�G�ݭn�Ψ쪺Display���� */
    final Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
    
    mButton01 = (Button)findViewById(R.id.myButton1); 
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    
    if(getRequestedOrientation()==-1)
    {
      mTextView01.setText(getResources().getText(R.string.str_err_1001));
    }
    
    /* ����U���s����ù��e�� */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        /* ��k�@�G�мggetRequestedOrientation */
        
        /* �Y�L�k���oscreenOrientation�ݩ� */
        if(getRequestedOrientation()==-1)
        {
          /* ���ܵL�k�i��e������\��A�]�L�k�P�OOrientation */
          mTextView01.setText(getResources().getText(R.string.str_err_1001));
        }
        else
        {
          if(getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
          {
            /* �Y��U����A�h�ܧ󬰪����e�{ */
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
          }
          else if(getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
          {
            /* �Y��U�������A�h�ܧ󬰾�e�{ */
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
          }
        }
        
        /* ��k�G�G�P�_�ù��e����(�����ǲ�) */
        int h= defaultDisplay.getHeight();
        int w = defaultDisplay.getWidth();
        
        /* ���ѪR�׬����s���U��U���ѪR�� */
        mTextView01.setText(Integer.toString(h)+"x"+Integer.toString(w));
        
        //if(w > h)
        //{
          /* Landscape */
          /* �мgActivity�̪�setRequestedOrientation()��k */
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //}
        //else
        //{
          /* Portrait */
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //}
      }      
    });
  }

  @Override
  public void setRequestedOrientation(int requestedOrientation)
  {
    // TODO Auto-generated method stub
    
    /* �P�_�n�ܧ󪺤�V�A�HToast���� */
    switch(requestedOrientation)
    {
      case (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE):
        mMakeTextToast
        (
          getResources().getText(R.string.str_msg1).toString(),
          false
        );
        break;
      case (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT):
        mMakeTextToast
        (
          getResources().getText(R.string.str_msg2).toString(),
          false
        );
        break;
    }
    super.setRequestedOrientation(requestedOrientation);
  }

  @Override
  public int getRequestedOrientation()
  {
    // TODO Auto-generated method stub
    
    /* ���мggetRequestedOrientation��k�A�i���o��U�ù�����V */
    return super.getRequestedOrientation();
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(EX05_22.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(EX05_22.this, str, Toast.LENGTH_SHORT).show();
    }
  }
}