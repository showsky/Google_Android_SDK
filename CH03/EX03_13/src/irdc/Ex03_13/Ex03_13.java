package irdc.Ex03_13;

import android.app.Activity;
/*���ݤޥ�graphics.Color�~��ϥ�Color.*������*/
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;

/*���ݤޥ� widget.Button�~��ŧi�ϥ�Button����*/
import android.widget.Button;

/*���ݤޥ� widget.TextView�~��ŧi�ϥ�TestView����*/
import android.widget.TextView;
public class Ex03_13 extends Activity 
{
  private Button mButton;
  private TextView mText;
  private int[] mColors;
  private int colornum;

  /** Called when the activity is first created. */
  @Override

  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    /*�z�LfindViewById�غc�l�Өϥ�main.xml�Pstring.xml
    ��button�PtextView���Ѽ�*/
    mButton=(Button) findViewById(R.id.mybutton);
    mText= (TextView) findViewById(R.id.mytext);

    /*�ŧi�ëغc�@���array���x�s���ϥΪ���r�C��*/
    mColors = new int[] 
                      { 
    Color.BLACK, Color.RED, Color.BLUE,
    Color.GREEN, Color.MAGENTA, Color.YELLOW
                       };
    colornum=0;
    
/*�ϥ�setOnClickListener�����s��ť�ƥ�*/
    mButton.setOnClickListener(new View.OnClickListener() 
    {             
      /*�ϥ�onClick���ϥΪ��I�U���s���X���ܰʤ�r�C��*/
      public void onClick(View v)
      {        
        if (colornum < mColors.length)
        {
          mText.setTextColor(mColors[colornum]);
          colornum++;
        }
        else
          colornum=0;
       }  
    }
    );  
  }
}




  
