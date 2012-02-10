package irdc.EX04_02;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
/*�ϥ�OnClickListener�POnFocusChangeListener�ӰϹj���s�����A*/
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class EX04_02 extends Activity 
{
  /*�ŧi�T�Ӫ����ܼ�(�Ϥ����s,���s,�PTextView)*/
  private ImageButton mImageButton1;
  private Button mButton1;
  private TextView mTextView1;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*�z�LfindViewById�غc�T�Ӫ���*/
    mImageButton1 =(ImageButton) findViewById(R.id.myImageButton1);
    mButton1=(Button)findViewById(R.id.myButton1);
    mTextView1 = (TextView) findViewById(R.id.myTextView1);
    
    /*�z�LOnFocusChangeListener�Ӧ^��ImageButton��onFous�ƥ�*/
    mImageButton1.setOnFocusChangeListener(new OnFocusChangeListener()
    {
      public void onFocusChange(View arg0, boolean isFocused)
      {
        // TODO Auto-generated method stub
        
        /*�YImageButton���A��onFocus����ImageButton���Ϥ�
         * �ç���textView����r*/
        if (isFocused==true)
        {
          mTextView1.setText("�Ϥ����s���A��:Got Focus");
          mImageButton1.setImageResource(R.drawable.iconfull);
        }
        /*�YImageButton���A��offFocus����ImageButton���Ϥ�
         *�ç���textView����r*/
        else 
        {
          mTextView1.setText("�Ϥ����s���A��:Lost Focus");
          mImageButton1.setImageResource(R.drawable.iconempty);
        }
      }
    });
       
      /*�z�LonClickListener�Ӧ^��ImageButton��onClick�ƥ�*/
      mImageButton1.setOnClickListener(new OnClickListener()
      {
        public void onClick(View v)
        {
          // TODO Auto-generated method stub
          /*�YImageButton���A��onClick����ImageButton���Ϥ�
           * �ç���textView����r*/
          mTextView1.setText("�Ϥ����s���A��:Got Click");
          mImageButton1.setImageResource(R.drawable.iconfull);
        }   
      }
      );
      
      /*�z�LonClickListener�Ӧ^��Button��onClick�ƥ�*/
      mButton1.setOnClickListener(new OnClickListener()
      {
        public void onClick(View v)
        {
          // TODO Auto-generated method stub
          /*�YButton���A��onClick����ImageButton���Ϥ�
           * �ç���textView����r*/
          mTextView1.setText("�Ϥ����s���A��:Lost Focus");
          mImageButton1.setImageResource(R.drawable.iconempty);
        } 
      }
      ); 
  }
}