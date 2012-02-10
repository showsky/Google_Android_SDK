package irdc.ex04_23;

/* import����class */
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;

public class EX04_23 extends Activity
{
  /* �����ܼƫŧi */
  private ImageView mImageView;
  private Button mButton01;
  private Button mButton02;
  private AbsoluteLayout layout1;
  private Bitmap bmp;
  private int id=0;
  private int displayWidth;
  private int displayHeight;
  private float scaleWidth=1;
  private float scaleHeight=1;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    
    /* ���o�ù��ѪR�פj�p */
    DisplayMetrics dm=new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    displayWidth=dm.widthPixels;
    /* �ù����׶������U��Button���� */
    displayHeight=dm.heightPixels-80;
    /* ��l�Ƭ����ܼ� */
    bmp=BitmapFactory.decodeResource(getResources(),R.drawable.ex04_23);
    mImageView = (ImageView)findViewById(R.id.myImageView);
    layout1 = (AbsoluteLayout)findViewById(R.id.layout1); 
    mButton01 = (Button)findViewById(R.id.myButton1);
    mButton02 = (Button)findViewById(R.id.myButton2);
    
    /* ���Y�p���s�[�WonClickListener */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        small();
      }
    });
    
    /* ����j���s�[�WonClickListener */
    mButton02.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        big();
      }
    });    
  }
  
  /* �Ϥ��Y�p��method */
  private void small()
  {
    int bmpWidth=bmp.getWidth();
    int bmpHeight=bmp.getHeight();
    /* �]�w�Ϥ��Y�p����� */
	double scale=0.8;  
    /* �p��X�o���n�Y�p����� */
    scaleWidth=(float) (scaleWidth*scale);
    scaleHeight=(float) (scaleHeight*scale);
    
    /* ����reSize�᪺Bitmap���� */
    Matrix matrix = new Matrix();  
    matrix.postScale(scaleWidth, scaleHeight); 
    Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true); 
     
    if(id==0)
    {
      /* �p�G�O�Ĥ@�����A�N������ӳ]�w��ImageView */
      layout1.removeView(mImageView);
    }
    else
    {
      /* �p�G���O�Ĥ@�����A�N�����W�@����j�Y�p�Ҳ��ͪ�ImageView */
      layout1.removeView((ImageView)findViewById(id));
    }
    /* ���ͷs��ImageView�A��JreSize��Bitmap����A�A��JLayout�� */
    id++;
    ImageView imageView = new ImageView(EX04_23.this);  
    imageView.setId(id);
    imageView.setImageBitmap(resizeBmp);
    layout1.addView(imageView); 
    setContentView(layout1);
    
    /* �]���Ϥ����̤j�ɩ�j���s�|disable�A�ҥH�b�Y�p�ɧ�L���]��enable */
    mButton02.setEnabled(true);
  }
  
  /* �Ϥ���j��method */
  private void big()
  {   
    int bmpWidth=bmp.getWidth();
    int bmpHeight=bmp.getHeight();
    /* �]�w�Ϥ���j����� */
    double scale=1.25;  
    /* �p��o���n��j����� */
    scaleWidth=(float)(scaleWidth*scale);
    scaleHeight=(float)(scaleHeight*scale);
    
    /* ����reSize�᪺Bitmap���� */
    Matrix matrix = new Matrix();  
    matrix.postScale(scaleWidth, scaleHeight); 
    Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true); 
      
    if(id==0)
    {
      /* �p�G�O�Ĥ@�����A�N������ӳ]�w��ImageView */
      layout1.removeView(mImageView);
    }
    else
    {
      /* �p�G���O�Ĥ@�����A�N�����W�@����j�Y�p�Ҳ��ͪ�ImageView */
      layout1.removeView((ImageView)findViewById(id));
    }
    /* ���ͷs��ImageView�A��JreSize��Bitmap����A�A��JLayout�� */
    id++; 
    ImageView imageView = new ImageView(EX04_23.this);  
    imageView.setId(id);
    imageView.setImageBitmap(resizeBmp);
    layout1.addView(imageView); 
    setContentView(layout1); 
    
    /* �p�G�A��j�|�W�L�ù��j�p�A�N��Button disable */ 
    if(scaleWidth*scale*bmpWidth>displayWidth||scaleHeight*scale*bmpHeight>displayHeight)
    {
      mButton02.setEnabled(false);
    }
  }
}