package irdc.ex07_08;

/* import����class */
import android.app.Activity; 
import android.content.Context; 
import android.media.AudioManager; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class EX07_08 extends Activity 
{ 
  /* �ܼƫŧi */
  private ImageView myImage;
  private ImageButton downButton;
  private ImageButton upButton;
  private ImageButton normalButton;
  private ImageButton muteButton;
  private ImageButton vibrateButton;
  private ProgressBar myProgress;
  private AudioManager audioMa;
  private int volume=0;
  
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main);
    
    /* �����l�� */
    audioMa = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    myImage = (ImageView)findViewById(R.id.myImage); 
    myProgress = (ProgressBar)findViewById(R.id.myProgress); 
    downButton = (ImageButton)findViewById(R.id.downButton); 
    upButton = (ImageButton)findViewById(R.id.upButton); 
    normalButton = (ImageButton)findViewById(R.id.normalButton); 
    muteButton = (ImageButton)findViewById(R.id.muteButton); 
    vibrateButton = (ImageButton)findViewById(R.id.vibrateButton); 
    
    /* �]�w��l��������q */
    volume=audioMa.getStreamVolume(AudioManager.STREAM_RING); 
    myProgress.setProgress(volume);
    /* �]�w��l���n���Ҧ� */
    int mode=audioMa.getRingerMode();
    if(mode==AudioManager.RINGER_MODE_NORMAL)
    {
      myImage.setImageDrawable(getResources()
                               .getDrawable(R.drawable.normal));
    }
    else if(mode==AudioManager.RINGER_MODE_SILENT)
    {
      myImage.setImageDrawable(getResources()
                               .getDrawable(R.drawable.mute));
    }
    else if(mode==AudioManager.RINGER_MODE_VIBRATE)
    {
      myImage.setImageDrawable(getResources()
                               .getDrawable(R.drawable.vibrate));
    }
    
    /* ���q�դp�n��Button */
    downButton.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      {
        /* �]�w���q�դp�n�@�� */
        audioMa.adjustVolume(AudioManager.ADJUST_LOWER, 0); 
        volume=audioMa.getStreamVolume(AudioManager.STREAM_RING);
        myProgress.setProgress(volume);
        /* �]�w�վ���n���Ҧ� */
        int mode=audioMa.getRingerMode();
        if(mode==AudioManager.RINGER_MODE_NORMAL)
        {
          myImage.setImageDrawable(getResources()
                                  .getDrawable(R.drawable.normal));
        }
        else if(mode==AudioManager.RINGER_MODE_SILENT)
        {
          myImage.setImageDrawable(getResources()
                                   .getDrawable(R.drawable.mute));
        }
        else if(mode==AudioManager.RINGER_MODE_VIBRATE)
        {
          myImage.setImageDrawable(getResources()
                                  .getDrawable(R.drawable.vibrate));
        }
      } 
    }); 
       
    /* ���q�դj�n��Button */
    upButton.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      { 
        /* �]�w���q�դj�n�@�� */
        audioMa.adjustVolume(AudioManager.ADJUST_RAISE, 0);
        volume=audioMa.getStreamVolume(AudioManager.STREAM_RING);
        myProgress.setProgress(volume);
        /* �]�w�վ�᪺�n���Ҧ� */
        int mode=audioMa.getRingerMode();
        if(mode==AudioManager.RINGER_MODE_NORMAL)
        {
          myImage.setImageDrawable(getResources()
                                   .getDrawable(R.drawable.normal));
        }
        else if(mode==AudioManager.RINGER_MODE_SILENT)
        {
          myImage.setImageDrawable(getResources()
                                   .getDrawable(R.drawable.mute));
        }
        else if(mode==AudioManager.RINGER_MODE_VIBRATE)
        {
          myImage.setImageDrawable(getResources()
                                  .getDrawable(R.drawable.vibrate));
        }
      } 
    }); 
    
    /* �վ�a�n�Ҧ������`�Ҧ���Button */ 
    normalButton.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      {
        /* �]�w�a�n�Ҧ���NORMAL */
        audioMa.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        /* �]�w���q�P�n���Ҧ� */
        volume=audioMa.getStreamVolume(AudioManager.STREAM_RING); 
        myProgress.setProgress(volume);
        myImage.setImageDrawable(getResources()
                                 .getDrawable(R.drawable.normal));
      } 
    });
    
    /* �վ�a�n�Ҧ����R���Ҧ���Button */ 
    muteButton.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      { 
        /* �]�w�a�n�Ҧ���SILENT */
        audioMa.setRingerMode(AudioManager.RINGER_MODE_SILENT); 
        /* �]�w���q�P�n�����A */
        volume=audioMa.getStreamVolume(AudioManager.STREAM_RING);
        myProgress.setProgress(volume);
        myImage.setImageDrawable(getResources()
                                 .getDrawable(R.drawable.mute)); 
      } 
    }); 
 
    /* �վ�a�n�Ҧ����_�ʼҦ���Button */ 
    vibrateButton.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      { 
        /* �]�w�a�n�Ҧ���VIBRATE */
        audioMa.setRingerMode(AudioManager.RINGER_MODE_VIBRATE); 
        /* �]�w���q�P�n�����A */
        volume=audioMa.getStreamVolume(AudioManager.STREAM_RING); 
        myProgress.setProgress(volume);
        myImage.setImageDrawable(getResources()
                                 .getDrawable(R.drawable.vibrate)); 
      } 
    }); 
  }
}