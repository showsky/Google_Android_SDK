package irdc.ex10_04;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EX10_04 extends Activity implements SurfaceHolder.Callback
{
  private Button mButton01;
  private TextView mTextView01;
  private EditText mEditText01;
  private String TAG = "HIPPO";
  private SurfaceView mSurfaceView01;
  private SurfaceHolder mSurfaceHolder01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    /* �����ε{�����ù�����A���ϥ�title bar */
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.main);
    
    /* ���o�ù��ѪR���� */
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    
    mTextView01 = (TextView) findViewById(R.id.myTextView1);
    mTextView01.setText(R.string.str_qr_gen);
    
    /* �HSurfaceView�@���۾�Preview���� */
    mSurfaceView01 = (SurfaceView) findViewById(R.id.mSurfaceView1);
    
    /* ô��SurfaceView�A���oSurfaceHolder���� */
    mSurfaceHolder01 = mSurfaceView01.getHolder();
    
    /* Activity������@SurfaceHolder.Callback */
    mSurfaceHolder01.addCallback(EX10_04.this);
    
    /* ����QRCode�����s�ƥ�B�z */
    mButton01 = (Button)findViewById(R.id.myButton1);
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        if(mEditText01.getText().toString()!="")
        {
          /* �ǤJsetQrcodeVersion��4�A�ȯ౵��62�Ӧr�� */
          AndroidQREncode(mEditText01.getText().toString(), 4);
        }
      }
    });
    
    mEditText01 = (EditText)findViewById(R.id.myEditText1);
    mEditText01.setText("DavidLanz");
    mEditText01.setOnKeyListener(new EditText.OnKeyListener()
    {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event)
      {
        // TODO Auto-generated method stub
        return false;
      }
    });
  }
  
  /* �ۭq����QR Code����� */
  public void AndroidQREncode(String strEncoding, int qrcodeVersion)
  {
    try
    {
      /* �غcQRCode�s�X���� */
      com.swetake.util.Qrcode testQrcode = new com.swetake.util.Qrcode();
      /* L','M','Q','H' */
      testQrcode.setQrcodeErrorCorrect('M');
      /* "N","A" or other */
      testQrcode.setQrcodeEncodeMode('B');
      /* 0-20 */
      testQrcode.setQrcodeVersion(qrcodeVersion);
      
      // getBytes
      byte[] bytesEncoding = strEncoding.getBytes("utf-8");
      
      if (bytesEncoding.length>0 && bytesEncoding.length <120)
      {
        /* �N�r��z�LcalQrcode����ഫ��boolean�}�C */
        boolean[][] bEncoding = testQrcode.calQrcode(bytesEncoding);
        /* �̾ڽs�X�᪺boolean�}�C�Aø�� */
        drawQRCode(bEncoding, getResources().getColor(R.drawable.black));
      }
    }
    catch (Exception e)
    {
      Log.i("HIPPO", Integer.toString(mEditText01.getText().length()) );
      e.printStackTrace();
    }
  }
  
  /* �bSurfaceView�Wø�sQRCode�Ϥ� */
  private void drawQRCode(boolean[][] bRect, int colorFill)
  {
    /* test Canvas*/
    int intPadding = 20;
    
    /* ���bSurfaceView�Wø�ϡA�ݥ�lock��wSurfaceHolder */
    Canvas mCanvas01 = mSurfaceHolder01.lockCanvas();
    
    /* �]�w�e��ø�s�C�� */
    mCanvas01.drawColor(getResources().getColor(R.drawable.white));
    
    /* �إߵe�� */
    Paint mPaint01 = new Paint();
    
    /* �]�w�e���C��μ˦� */
    mPaint01.setStyle(Paint.Style.FILL);
    mPaint01.setColor(colorFill);
    mPaint01.setStrokeWidth(1.0F);
    
    /* �v�@���J2��boolean�}�C */
    for (int i=0;i<bRect.length;i++)
    {
      for (int j=0;j<bRect.length;j++)
      {
        if (bRect[j][i])
        {
          /* �̾ڰ}�C�ȡAø�X���X��� */
          mCanvas01.drawRect(new Rect(intPadding+j*3+2, intPadding+i*3+2, intPadding+j*3+2+3, intPadding+i*3+2+3), mPaint01);
        }
      }
    }
    mSurfaceHolder01.unlockCanvasAndPost(mCanvas01);
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(EX10_04.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(EX10_04.this, str, Toast.LENGTH_SHORT).show();
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