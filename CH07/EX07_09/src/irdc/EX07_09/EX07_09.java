package irdc.EX07_09;

import android.app.Activity; 
import android.media.MediaPlayer; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.ImageButton;
import android.widget.TextView; 

public class EX07_09 extends Activity 
{ 
  /*�ŧi�@�� ImageButton,TextView,MediaPlayer�ܼ�*/
  private ImageButton mButton01, mButton02, mButton03;
  private TextView mTextView01; 
  private MediaPlayer mMediaPlayer01; 
  /*�ŧi�@��Flag�@���T�{���֬O�_�Ȱ����ܼƨùw�]��false*/
  private boolean bIsPaused = false; 

   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    
    /*�z�LfindViewById�غc�l�إ�TextView�PImageView����*/ 
    mButton01 =(ImageButton) findViewById(R.id.myButton1); 
    mButton02 =(ImageButton) findViewById(R.id.myButton2); 
    mButton03 =(ImageButton) findViewById(R.id.myButton3);
    mTextView01 = (TextView) findViewById(R.id.myTextView1); 
     
    /* onCreate�ɫإ�MediaPlayer���� */ 
    mMediaPlayer01 = new MediaPlayer();  
    /* �N���֥HImport���覡�x�s�bres/raw/always.mp3 */ 
    mMediaPlayer01 = MediaPlayer.create(EX07_09.this, R.raw.big); 

    /* ���漽�񭵼֪����s */ 
    mButton01.setOnClickListener(new ImageButton.OnClickListener() 
    { 
      @Override 
      /*�мgOnClick�ƥ�*/
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub         
        try 
        { 
          
          if (mMediaPlayer01 != null) 
          { 
            mMediaPlayer01.stop(); 
          } 
          /*�bMediaPlayer���o����귽�Pstop()����
           * �n�ǳ�Playback�����A�e�@�w�n�ϥ�MediaPlayer.prepare()*/
          mMediaPlayer01.prepare(); 
          /*�}�l�Φ^�м���*/
          mMediaPlayer01.start(); 
          /*����TextView���}�l���񪬺A*/
          mTextView01.setText(R.string.str_start);  
        } 
        catch (Exception e) 
        { 
          // TODO Auto-generated catch block 
          mTextView01.setText(e.toString()); 
          e.printStackTrace(); 
        } 
      } 
    }); 
     
    /* ����� */ 
    mButton02.setOnClickListener(new ImageButton.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      { 
        // TODO Auto-generated method stub 
        try 
        { 
          if (mMediaPlayer01 != null) 
          { 
            /*�����*/
            mMediaPlayer01.stop(); 
            /*����TextView������񪬺A*/
            mTextView01.setText(R.string.str_close);
          } 
            
        } 
        catch (Exception e) 
        { 
          // TODO Auto-generated catch block 
          mTextView01.setText(e.toString()); 
          e.printStackTrace(); 
        } 
      } 
    }); 
    
    /* �Ȱ����� */ 
    mButton03.setOnClickListener(new ImageButton.OnClickListener() 
    { 
      @Override 
      public void onClick(View arg0) 
      { 
        // TODO Auto-generated method stub 
        try 
        { 
          if (mMediaPlayer01 != null) 
          { 
            /*�O�_���Ȱ����A=�_*/
            if(bIsPaused==false) 
            { 
              /*�Ȱ�����*/
              mMediaPlayer01.pause(); 
              /*�]�wFlag��treu��� Player���A���Ȱ�*/
              bIsPaused = true; 
              /*����TextView���Ȱ�����*/
              mTextView01.setText(R.string.str_pause); 
            } 
            /*�O�_���Ȱ����A=�O*/
            else if(bIsPaused==true) 
            { 
              /*�^�_���X���A*/
              mMediaPlayer01.start(); 
              /*�]�wFlag��false��� Player���A���D�Ȱ����A*/
              bIsPaused = false; 
              /*����TextView���}�l����*/
              mTextView01.setText(R.string.str_start); 
            } 
          }          
        } 
        catch (Exception e) 
        { 
          // TODO Auto-generated catch block 
          mTextView01.setText(e.toString()); 
          e.printStackTrace(); 
        } 
      } 
    }); 
     
    /* ��MediaPlayer.OnCompletionLister�|���檺Listener */  
    mMediaPlayer01.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
    { 
      // @Override 
      /*�мg�ɮ׼��X�����ƥ�*/
      public void onCompletion(MediaPlayer arg0) 
      { 
        try 
        { 
          /*�Ѱ��귽�PMediaPlayer���������Y
           * ���귽�i�H����L�{���Q��*/
          mMediaPlayer01.release(); 
          /*����TextView�����񵲧�*/
          mTextView01.setText(R.string.str_OnCompletionListener); 
        } 
        catch (Exception e) 
        { 
          mTextView01.setText(e.toString()); 
          e.printStackTrace(); 
        } 
      } 
    }); 
     
    /* ��MediaPlayer.OnErrorListener�|���檺Listener */ 
    mMediaPlayer01.setOnErrorListener(new MediaPlayer.OnErrorListener() 
    { 
      @Override 
      /*�мg���~�B�z�ƥ�*/
      public boolean onError(MediaPlayer arg0, int arg1, int arg2) 
      { 
        // TODO Auto-generated method stub 
        try 
        { 
          /*�o�Ϳ��~�ɤ]�Ѱ��귽�PMediaPlayer������*/
          mMediaPlayer01.release(); 
          mTextView01.setText(R.string.str_OnErrorListener); 
        } 
        catch (Exception e) 
        { 
          mTextView01.setText(e.toString()); 
          e.printStackTrace(); 
        } 
        return false; 
      } 
    }); 
  } 

  @Override 
  /*�мg�D�{���Ȱ����A�ƥ�*/
  protected void onPause() 
  { 
    // TODO Auto-generated method stub 
    try 
    { 
      /*�A�D�{���Ȱ��ɸѰ��귽�PMediaPlayer���������Y*/
      mMediaPlayer01.release(); 
    } 
    catch (Exception e) 
    { 
      mTextView01.setText(e.toString()); 
      e.printStackTrace(); 
    } 
    super.onPause(); 
  } 
} 