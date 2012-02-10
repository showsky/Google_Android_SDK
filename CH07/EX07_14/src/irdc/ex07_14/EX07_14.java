package irdc.ex07_14;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/* Activity��@SurfaceHolder.Callback */
public class EX07_14 extends Activity implements SurfaceHolder.Callback
{
  private TextView mTextView01;
  private static final String TAG = "HIPPO_MediaPlayer";
  
  /* �إ�MediaPlayer���� */
  private MediaPlayer mMediaPlayer01;
  
  /* SurfaceView����@��Layout�W����X���� */
  private SurfaceView mSurfaceView01;
  
  /* �HSurfaceHolder�ӱ���SurfaceView */
  private SurfaceHolder mSurfaceHolder01;
  
  /* �|��ImageButton����MediaPlayer�ƥ� */
  private ImageButton mPlay;
  private ImageButton mPause;
  private ImageButton mReset;
  private ImageButton mStop;
  
  /* MediaPlayer�Ȱ�flag */
  private boolean bIsPaused = false;
  
  /* MediaPlayer�Q����flag */
  private boolean bIsReleased = false;
  
  /* �N.3gp�v���ɦs��bSD�d���ڥؿ��� */
  private String strVideoPath = "";
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    if(!checkSDCard())
    {
      /* ����User���w��SD�O�Хd */
      mMakeTextToast
      (
        getResources().getText(R.string.str_err_nosd).toString(),
        true
      );
    }
    
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    
    /* �]�w�t��PixelFormae��UNKNOW */
    getWindow().setFormat(PixelFormat.UNKNOWN);
    
    /* ô��Layout�W��SurfaceView */
    mSurfaceView01 = (SurfaceView) findViewById(R.id.mSurfaceView1);
    
    /* �]�wSurfaceHolder��Layout SurfaceView���ù� */ 
    mSurfaceHolder01 = mSurfaceView01.getHolder();
    mSurfaceHolder01.addCallback(this);
    
    /* ��v����Size��176x144 */
    mSurfaceHolder01.setFixedSize(176,144);
    mSurfaceHolder01.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    
    /* 4��ImageButton */
    mPlay = (ImageButton) findViewById(R.id.play); 
    mPause = (ImageButton) findViewById(R.id.pause); 
    mReset = (ImageButton) findViewById(R.id.reset); 
    mStop = (ImageButton) findViewById(R.id.stop);
    
    /* �]�w���եμv����m */
    strVideoPath = "/sdcard/test.3gp";
    
    /* ������s */
    mPlay.setOnClickListener(new ImageButton.OnClickListener()
    { 
      public void onClick(View view)
      {
        /* �I�s����v��Function */
        if(checkSDCard())
        {
          playVideo(strVideoPath);
        }
      }
    });
    
    /* �Ȱ����s */
    mPause.setOnClickListener(new ImageButton.OnClickListener()
    {
      public void onClick(View view)
      {
        if(checkSDCard())
        {
          if (mMediaPlayer01 != null)
          {
            if(bIsReleased == false)
            {
              if(bIsPaused==false)
              {
                mMediaPlayer01.pause();
                bIsPaused = true;
                mTextView01.setText(R.string.str_pause);
              }
              else if(bIsPaused==true)
              {
                mMediaPlayer01.start();
                bIsPaused = false;
                mTextView01.setText(R.string.str_play);
              }
            }
          }
        }
      }
    });
    
    /* ���s������s */
    mReset.setOnClickListener(new ImageButton.OnClickListener()
    { 
      public void onClick(View view)
      {
        if(checkSDCard())
        {
          if(bIsReleased == false)
          {
            if (mMediaPlayer01 != null)
            { 
              mMediaPlayer01.seekTo(0); 
            }
          }
        }
      } 
    });
    
    /* �פ���s */
    mStop.setOnClickListener(new ImageButton.OnClickListener()
    { 
      public void onClick(View view)
      {
        if(checkSDCard())
        {
          if (mMediaPlayer01 != null)
          {
            if(bIsReleased==false)
            {
              mMediaPlayer01.stop();
              mMediaPlayer01.release();
              bIsReleased = true;
              mTextView01.setText(R.string.str_stop);
            }          
          }
        }
      }
    });
  }
  
  /* �ۭq����v����� */
  private void playVideo(String strPath)
  { 
    mMediaPlayer01 = new MediaPlayer();
    mMediaPlayer01.setAudioStreamType(AudioManager.STREAM_MUSIC);
    
    /* �]�wVideo�v���HSurfaceHolder���� */
    mMediaPlayer01.setDisplay(mSurfaceHolder01);  
    
    try
    { 
      mMediaPlayer01.setDataSource(strPath); 
    }
    catch (Exception e)
    { 
      // TODO Auto-generated catch block
      mTextView01.setText("setDataSource Exceeption:"+e.toString());
      e.printStackTrace();
    }
    
    try
    { 
      mMediaPlayer01.prepare();
    }
    catch (Exception e)
    { 
      // TODO Auto-generated catch block
      mTextView01.setText("prepare Exceeption:"+e.toString());
      e.printStackTrace(); 
    }
    mMediaPlayer01.start();
    bIsReleased = false;
    mTextView01.setText(R.string.str_play);
    
    mMediaPlayer01.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
    {
      @Override
      public void onCompletion(MediaPlayer arg0)
      {
        // TODO Auto-generated method stub
        mTextView01.setText(R.string.str_stop);
      }
    });
  }
  
  private boolean checkSDCard()
  {
    /* �P�_�O�Хd�O�_�s�b */
    if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(EX07_14.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(EX07_14.this, str, Toast.LENGTH_SHORT).show();
    }
  }
  
  @Override
  public void surfaceChanged(SurfaceHolder surfaceholder, int format, int w, int h)
  {
    // TODO Auto-generated method stub
    Log.i(TAG, "Surface Changed");
  }
  
  @Override
  public void surfaceCreated(SurfaceHolder surfaceholder)
  {
    // TODO Auto-generated method stub
    Log.i(TAG, "Surface Changed");
  }
  
  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceholder)
  {
    // TODO Auto-generated method stub
    Log.i(TAG, "Surface Destroyed");
  }
}