package irdc.ex10_01; 

/* import����class */
import android.app.Activity; 
import android.app.AlertDialog;
import android.content.Context; 
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle; 
import android.os.PowerManager; 
import android.os.ServiceManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem; 
import android.view.Window; 
import android.view.WindowManager; 
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.IHardwareService;

public class EX10_01 extends Activity 
{
  private boolean ifLocked = false;
  private PowerManager.WakeLock mWakeLock; 
  private PowerManager mPowerManager; 
  private LinearLayout mLinearLayout;
  /* �x�s�{���Ұʫe������G�� */
  private int mUserBrightness=0; 
  /* �W�@�L�G��menu�ﶵidentifier�A�ΥH�ѧO�ƥ� */ 
  static final private int M_CHOOSE = Menu.FIRST; 
  static final private int M_EXIT = Menu.FIRST+1;
  /* �C���檺�C��P��r�}�C */
  private int[] color={R.drawable.white,R.drawable.blue,
                       R.drawable.pink,R.drawable.green,
                       R.drawable.orange,R.drawable.yellow};
  private int[] text={R.string.str_white,R.string.str_blue,
                      R.string.str_pink,R.string.str_green,
                      R.string.str_orange,R.string.str_yellow};
  
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
     
    /* �����bsetContentView���e�I�s���ù���� */ 
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags 
    ( 
      WindowManager.LayoutParams.FLAG_FULLSCREEN, 
      WindowManager.LayoutParams.FLAG_FULLSCREEN 
    );
    
    setContentView(R.layout.main);
    /* ��l��mLinearLayout */
    mLinearLayout=(LinearLayout)findViewById(R.id.myLinearLayout1);         
    /* ���oPowerManager */ 
    mPowerManager = (PowerManager)
                     getSystemService(Context.POWER_SERVICE); 
    /* ���oWakeLock */
    mWakeLock = mPowerManager.newWakeLock 
    ( 
      PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "BackLight" 
    );
    
    try
    {
      /* ���o�{���Ұʮɪ�����]�w�G�� */
      mUserBrightness = Settings.System.getInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS);
    }
    catch (Exception e)
    {
      Toast.makeText(EX10_01.this,""+e,Toast.LENGTH_LONG).show();
      e.printStackTrace();
    }
  } 

  @Override 
  public boolean onCreateOptionsMenu(Menu menu) 
  { 
    /* menu�s��ID */ 
    int idGroup1 = 0;    
    /* menuItemID */ 
    int orderMenuItem1 = Menu.NONE; 
    int orderMenuItem2 = Menu.NONE+1; 
    /* �إ�menu */ 
    menu.add(idGroup1,M_CHOOSE,orderMenuItem1,R.string.str_title);
    menu.add(idGroup1,M_EXIT,orderMenuItem2,R.string.str_exit); 
    menu.setGroupCheckable(idGroup1, true, true);
 
    return super.onCreateOptionsMenu(menu); 
  } 
   
  @Override 
  public boolean onOptionsItemSelected(MenuItem item) 
  {  
    switch(item.getItemId()) 
    { 
      case (M_CHOOSE):
        /* ���X��ܭI���C�⪺AlertDialog */
        new AlertDialog.Builder(EX10_01.this)
          .setTitle(getResources().getString(R.string.str_title))
          .setAdapter(new MyAdapter(this,color,text),listener1)
          .setPositiveButton("����",
              new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface dialog, int which)
            {
            }
          })
          .show();
        break; 
      case (M_EXIT): 
        /* ���}�{�� */ 
        this.finish(); 
        break; 
    }
    return super.onOptionsItemSelected(item); 
  }
  
  /* ��ܭI���C�⪺AlertDialog��OnClickListener */
  OnClickListener listener1=new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface dialog,int which)
    {
      /* ���I���C�� */
      mLinearLayout.setBackgroundResource(color[which]);
      /* �HToast��ܳ]�w���C�� */
      Toast.makeText(EX10_01.this,
                     getResources().getString(text[which]),
                     Toast.LENGTH_LONG).show();
    }
  };
   
  @Override 
  protected void onResume() 
  {  
    /* onResume()�ɩI�swakeLock() */
    wakeLock(); 
    super.onResume(); 
  } 
   
  @Override 
  protected void onPause() 
  {
    /* onPause()�ɩI�swakeUnlock() */
    wakeUnlock(); 
    super.onPause();
  } 
  
  /* ��_WakeLock��method */
  private void wakeLock()
  { 
    if (!ifLocked) 
    { 
      setBrightness(255);
      ifLocked = true;
      mWakeLock.acquire();
    }
    setBrightness(255);
  } 

  /* ����WakeLock��method */
  private void wakeUnlock() 
  { 
    if (ifLocked) 
    { 
      mWakeLock.release(); 
      ifLocked = false;
      setBrightness(mUserBrightness);
    }
  }
  
  /* �]�w����G�ת�method */
  private void setBrightness(int brightness)
  {
    /* ���oIHardwareService */
    IHardwareService hardware = IHardwareService.Stub.asInterface(  
        ServiceManager.getService("hardware"));  
    if (hardware != null)
    {  
      try
      {
        /* �]�w�G�� */
        Settings.System.putInt(getContentResolver(),
            Settings.System.SCREEN_BRIGHTNESS,
            brightness);
        hardware.setScreenBacklight(brightness);
      }
      catch (Exception e)
      {
        Toast.makeText(EX10_01.this,""+e,Toast.LENGTH_LONG).show(); 
        e.printStackTrace();
      }  
    }
  }
}