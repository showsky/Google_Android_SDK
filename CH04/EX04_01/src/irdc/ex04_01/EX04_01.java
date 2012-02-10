package irdc.ex04_01;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EX04_01 extends Activity
{
  /*�ŧi TextView�BEditText����*/
  private TextView mTextView01; 
  private EditText mEditText01;   
  
  /** Called when the activity is first created. */
    @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*���oTextView�BEditText*/   
    mTextView01 = (TextView)findViewById(R.id.myTextView);
    mEditText01 = (EditText)findViewById(R.id.myEditText);
    
    /*�]�wEditText��OnKeyListener�ƥ�ӱҰ�*/
    mEditText01.setOnKeyListener(new EditText.OnKeyListener()
    {

      @Override
      public boolean onKey(View arg0, int arg1, KeyEvent arg2)
      {
        // TODO Auto-generated method stub
        /*�]�wTextView���EditText�ҿ�J�����e*/
        mTextView01.setText(mEditText01.getText()); 
        return false;
      }
    });
}
}