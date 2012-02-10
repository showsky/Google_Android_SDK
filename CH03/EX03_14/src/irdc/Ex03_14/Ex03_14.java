package irdc.Ex03_14;

import irdc.Ex03_14.R;
import android.app.Activity;
/*���ݤޥ�graphics.Typeface�~��ϥ�creatFromAsset()�ӧ��ܦr��*/
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ex03_14 extends Activity 
{
  /** Called when the activity is first created. */
  private TextView mText;
  private Button sizeButton;
  private Button fontButton;
  @Override
 
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mText=(TextView)findViewById(R.id.mytextview);
    sizeButton=(Button) findViewById(R.id.sizebutton);
    fontButton=(Button) findViewById(R.id.fontbutton);
    /*�]�wonClickListener�P���s����s��*/
    sizeButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /*�ϥ�setTextSize()�ӧ��ܦr��j�p */
        mText.setTextSize(20);
      }       
    }
    );
    fontButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /*�����ƥ��bassets���U�إߤ@fonts�ɮק��é�J�n�ϥΪ��r���ɮ�(.ttf)
         * �ô��Ѭ۹���|��creatFromAsset()�ӫإ�Typeface����*/
        mText.setTypeface
        (Typeface.createFromAsset(getAssets(),"fonts/HandmadeTypewriter.ttf"));
      }
    }
    );
  }  
}