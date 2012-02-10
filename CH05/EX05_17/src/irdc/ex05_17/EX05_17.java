package irdc.ex05_17;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class EX05_17 extends Activity
{
  private TextView mTextView01;
  private CheckBox mCheckBox01;
  
  /* �إ�WiFiManager���� */
  private WifiManager mWiFiManager01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mTextView01 = (TextView) findViewById(R.id.myTextView1);
    mCheckBox01 = (CheckBox) findViewById(R.id.myCheckBox1);
    
    /* �HgetSystemService���oWIFI_SERVICE */
    mWiFiManager01 = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
    
    /* �P�_����{���᪺WiFi���A�O�_�}�ҩζ}�Ҥ� */
    if(mWiFiManager01.isWifiEnabled())
    {
      /* �P�_WiFi���A�O�_�u�w�}�ҡv */
      if(mWiFiManager01.getWifiState()==WifiManager.WIFI_STATE_ENABLED)
      {
        /* �YWiFi�w�}�ҡA�N�֨������� */
        mCheckBox01.setChecked(true);
        /* �ܧ�֨�����r������WiFi*/
        mCheckBox01.setText(R.string.str_uncheck);
      }
      else
      {
        /* �YWiFi���}�ҡA�N�֨����Ŀ���� */
        mCheckBox01.setChecked(false);
        /* �ܧ�֨�����r���}��WiFi*/
        mCheckBox01.setText(R.string.str_checked);
      }
    }
    else
    {
      mCheckBox01.setChecked(false);
      mCheckBox01.setText(R.string.str_checked);
    }
    
    /* ����CheckBox���I���ƥ� */
    mCheckBox01.setOnClickListener(new CheckBox.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        
        /* ��֨����������֨����A */
        if(mCheckBox01.isChecked()==false)
        {
          /* ��������Wi-Fi�A�� */
          try
          {
            /* �P�_WiFi���A�O�_���w�}�� */
            if(mWiFiManager01.isWifiEnabled() )
            {
              /* ����WiFi */
              if(mWiFiManager01.setWifiEnabled(false))
              {
                mTextView01.setText(R.string.str_stop_wifi_done);
              }
              else
              {
                mTextView01.setText(R.string.str_stop_wifi_failed);
              }
            }
            else
            {
              /* WiFi���A�����w�}�Ҫ��A�� */
              switch(mWiFiManager01.getWifiState())
              {
                /* WiFi���b�}�ҹL�{���A�ɭP�L�k����... */
                case WifiManager.WIFI_STATE_ENABLING:
                  mTextView01.setText
                  (
                    getResources().getText(R.string.str_stop_wifi_failed)+":"+
                    getResources().getText(R.string.str_wifi_enabling)
                  );
                  break;
                /* WiFi���b�����L�{���A�ɭP�L�k����... */
                case WifiManager.WIFI_STATE_DISABLING:
                  mTextView01.setText
                  (
                    getResources().getText(R.string.str_stop_wifi_failed)+":"+
                    getResources().getText(R.string.str_wifi_disabling)
                  );
                  break;
                /* WiFi�w�g���� */
                case WifiManager.WIFI_STATE_DISABLED:
                  mTextView01.setText
                  (
                    getResources().getText(R.string.str_stop_wifi_failed)+":"+
                    getResources().getText(R.string.str_wifi_disabled)
                  );
                  break;
                /* �L�k���o�ο���WiFi���A */
                case WifiManager.WIFI_STATE_UNKNOWN:
                default:
                  mTextView01.setText
                  (
                    getResources().getText(R.string.str_stop_wifi_failed)+":"+
                    getResources().getText(R.string.str_wifi_unknow)
                  );
                  break;
              }
              mCheckBox01.setText(R.string.str_checked);
            }
          }
          catch (Exception e)
          {
            Log.i("HIPPO", e.toString());
            e.printStackTrace();
          }
        }
        else if(mCheckBox01.isChecked()==true)
        {
          /* ���ն}��Wi-Fi�A�� */
          try
          {
            /* �T�{WiFi�A�ȬO�����B���b�}�ҧ@�~�� */
            if(!mWiFiManager01.isWifiEnabled() && mWiFiManager01.getWifiState()!=WifiManager.WIFI_STATE_ENABLING )
            {
              if(mWiFiManager01.setWifiEnabled(true))
              {
                switch(mWiFiManager01.getWifiState())
                {
                  /* WiFi���b�}�ҹL�{���A�ɭP�L�k�}��... */
                  case WifiManager.WIFI_STATE_ENABLING:
                    mTextView01.setText
                    (
                      getResources().getText(R.string.str_wifi_enabling)
                    );
                    break;
                  /* WiFi�w�g���}�ҡA�L�k�A���}��... */
                  case WifiManager.WIFI_STATE_ENABLED:
                    mTextView01.setText
                    (
                      getResources().getText(R.string.str_start_wifi_done)
                    );
                    break;
                  /* ��L���������~ */
                  default:
                    mTextView01.setText
                    (
                      getResources().getText(R.string.str_start_wifi_failed)+":"+
                      getResources().getText(R.string.str_wifi_unknow)
                    );
                    break;
                }
              }
              else
              {
                mTextView01.setText(R.string.str_start_wifi_failed);
              }
            }
            else
            {
              switch(mWiFiManager01.getWifiState())
              {
                /* WiFi���b�}�ҹL�{���A�ɭP�L�k�}��... */
                case WifiManager.WIFI_STATE_ENABLING:
                  mTextView01.setText
                  (
                    getResources().getText(R.string.str_start_wifi_failed)+":"+
                    getResources().getText(R.string.str_wifi_enabling)
                  );
                  break;
                /* WiFi���b�����L�{���A�ɭP�L�k�}��... */
                case WifiManager.WIFI_STATE_DISABLING:
                  mTextView01.setText
                  (
                    getResources().getText(R.string.str_start_wifi_failed)+":"+
                    getResources().getText(R.string.str_wifi_disabling)
                  );
                  break;
                /* WiFi�w�g���� */
                case WifiManager.WIFI_STATE_DISABLED:
                  mTextView01.setText
                  (
                    getResources().getText(R.string.str_start_wifi_failed)+":"+
                    getResources().getText(R.string.str_wifi_disabled)
                  );
                  break;
                /* �L�k���o�ο���WiFi���A */
                case WifiManager.WIFI_STATE_UNKNOWN:
                default:
                  mTextView01.setText
                  (
                    getResources().getText(R.string.str_start_wifi_failed)+":"+
                    getResources().getText(R.string.str_wifi_unknow)
                  );
                  break;
              }
            }
            mCheckBox01.setText(R.string.str_uncheck);
          }
          catch (Exception e)
          {
            Log.i("HIPPO", e.toString());
            e.printStackTrace();
          }
        }
      }
    });
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(EX05_17.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(EX05_17.this, str, Toast.LENGTH_SHORT).show();
    }
  }
  
  @Override
  protected void onResume()
  {
    // TODO Auto-generated method stub
    
    /* ��onResume�мg�ƥ󬰨��o�}�ҵ{����UWiFi�����A */
    try
    {
      switch(mWiFiManager01.getWifiState())
      {
        /* WiFi�w�g�b�}�Ҫ��A... */
        case WifiManager.WIFI_STATE_ENABLED:
          mTextView01.setText
          (
            getResources().getText(R.string.str_wifi_enabling)
          );
          break;
        /* WiFi���b�}�ҹL�{�����A... */
        case WifiManager.WIFI_STATE_ENABLING:
          mTextView01.setText
          (
            getResources().getText(R.string.str_wifi_enabling)
          );
          break;
        /* WiFi���b�����L�{��... */
        case WifiManager.WIFI_STATE_DISABLING:
          mTextView01.setText
          (
            getResources().getText(R.string.str_wifi_disabling)
          );
          break;
        /* WiFi�w�g���� */
        case WifiManager.WIFI_STATE_DISABLED:
          mTextView01.setText
          (
            getResources().getText(R.string.str_wifi_disabled)
          );
          break;
        /* �L�k���o�ο���WiFi���A */
        case WifiManager.WIFI_STATE_UNKNOWN:
        default:
          mTextView01.setText
          (
            getResources().getText(R.string.str_wifi_unknow)
          );
          break;
      }
    }
    catch(Exception e)
    {
      mTextView01.setText(e.toString());
      e.getStackTrace();
    }
    super.onResume();
  }
  
  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    super.onPause();
  }
}