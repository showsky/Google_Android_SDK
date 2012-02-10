package irdc.ex04_24;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EX04_24 extends Activity
{
  private Button mButton1;
  private Button mButton2;
  private TextView mTextView1;
  private ImageView mImageView1;
  private int ScaleTimes;
  private int ScaleAngle;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mButton1 =(Button) findViewById(R.id.myButton1);
    mButton2 =(Button) findViewById(R.id.myButton2);
    mTextView1 = (TextView) findViewById(R.id.myTextView1);
    mImageView1 = (ImageView) findViewById(R.id.myImageView1);
    ScaleTimes = 1;
    ScaleAngle = 1;
    
    final Bitmap mySourceBmp = BitmapFactory.decodeResource(getResources(), R.drawable.hippo);
    
    final int widthOrig = mySourceBmp.getWidth(); 
    final int heightOrig = mySourceBmp.getHeight();
    
    /* �{�������A���J�w�]��Drawable */
    mImageView1.setImageBitmap(mySourceBmp);
    
    /* �V��������s */
    mButton1.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        ScaleAngle--;
        if(ScaleAngle<-5)
        {
          ScaleAngle = -5;
        }
        
        /* ScaleTimes=1�A����1:1���e�����*/
        int newWidth = widthOrig * ScaleTimes;
        int newHeight = heightOrig * ScaleTimes;
        
        float scaleWidth = ((float) newWidth) / widthOrig; 
        float scaleHeight = ((float) newHeight) / heightOrig; 
        
        Matrix matrix = new Matrix(); 
        /* �ϥ�Matrix.postScale�]�w���� */
        matrix.postScale(scaleWidth, scaleHeight);
        
        /* �ϥ�Matrix.postRotate��k����Bitmap*/
        //matrix.postRotate(5*ScaleAngle);
        matrix.setRotate(5*ScaleAngle);
        
        /* �إ߷s��Bitmap���� */ 
        Bitmap resizedBitmap = Bitmap.createBitmap(mySourceBmp, 0, 0, widthOrig, heightOrig, matrix, true);
        /**/
        BitmapDrawable myNewBitmapDrawable = new BitmapDrawable(resizedBitmap); 
        mImageView1.setImageDrawable(myNewBitmapDrawable);
        mTextView1.setText(Integer.toString(5*ScaleAngle)); 
      }      
    });
    
    /* �V�k������s */
    mButton2.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        ScaleAngle++;
        if(ScaleAngle>5)
        {
          ScaleAngle = 5;
        }
        
        /* ScaleTimes=1�A����1:1���e�����*/
        int newWidth = widthOrig * ScaleTimes;
        int newHeight = heightOrig * ScaleTimes;
        
        /* �p����઺Matrix��� */ 
        float scaleWidth = ((float) newWidth) / widthOrig; 
        float scaleHeight = ((float) newHeight) / heightOrig; 
        
        Matrix matrix = new Matrix(); 
        /* �ϥ�Matrix.postScale�]�w���� */ 
        matrix.postScale(scaleWidth, scaleHeight);
        
        /* �ϥ�Matrix.postRotate��k����Bitmap*/
        //matrix.postRotate(5*ScaleAngle);
        matrix.setRotate(5*ScaleAngle);

        /* �إ߷s��Bitmap���� */ 
        Bitmap resizedBitmap = Bitmap.createBitmap(mySourceBmp, 0, 0, widthOrig, heightOrig, matrix, true);
        /**/
        BitmapDrawable myNewBitmapDrawable = new BitmapDrawable(resizedBitmap); 
        mImageView1.setImageDrawable(myNewBitmapDrawable);
        mTextView1.setText(Integer.toString(5*ScaleAngle));
      }
    });
  }
}