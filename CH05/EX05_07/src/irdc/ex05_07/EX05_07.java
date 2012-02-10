package irdc.ex05_07;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EX05_07 extends Activity
{
  private Button mButton01;
     
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    mButton01 = (Button)findViewById(R.id.myButton1); 
    
    /*�]�wButton��OnClickListener�Ұʨƥ�*/
    mButton01.setOnClickListener(new Button.OnClickListener() 
    {
      
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        
        ImageView mView01 = new ImageView(EX05_07.this);
        TextView mTextView = new TextView(EX05_07.this);
        LinearLayout lay = new LinearLayout(EX05_07.this);   
        
        /*�]�wmTextView�h���string��*/
        mTextView.setText(R.string.app_url);        
        /*�P�_mTextView�����e����A�ûP�t�ΰ��s��*/
        Linkify.addLinks(mTextView,Linkify.WEB_URLS|
            Linkify.EMAIL_ADDRESSES|Linkify.PHONE_NUMBERS);  
        /*��Toast�覡���*/ 
        Toast toast = Toast.makeText(EX05_07.this, mTextView
            .getText(), Toast.LENGTH_LONG);        
        View textView = toast.getView();         
        lay.setOrientation(LinearLayout.HORIZONTAL);
        /*�bToast�̥[�W�Ϥ�*/
        mView01.setImageResource(R.drawable.icon);
        /*�bToast����ܹϤ�*/
        lay.addView(mView01);
        /*�bToast����ܤ�r*/
        lay.addView(textView);    
        toast.setView(lay);        
        toast.show(); 
      }      
    }); 
  }
}