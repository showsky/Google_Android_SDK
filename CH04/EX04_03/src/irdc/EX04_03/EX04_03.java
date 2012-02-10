package irdc.EX04_03;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EX04_03 extends Activity 
{
  /** Called when the activity is first created. */
  /*�ŧi��Ӫ����ܼ�(���s�P�s���r)*/
  private Button mButton;
  private EditText mEditText;
  
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*�z�LfindViewById()���o���� */
    mButton=(Button)findViewById(R.id.myButton);
    mEditText=(EditText)findViewById(R.id.myEditText);
    
    /*�]�wonClickListener��Button�����ťonClick�ƥ�*/
    mButton.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
      // TODO Auto-generated method stub
    
      /*�ŧi�r���ܼƨè��o�ϥΪ̿�J��EditText�r��*/
      Editable Str;
      Str=mEditText.getText();
      
      /*�ϥΨt�μзǪ� makeText()�覡�Ӳ���Toast�T��*/
      Toast.makeText(
        EX04_03.this,
        "�A���@��  "+Str.toString()+"�w�e�F�C�ϦѤH�H�c",
        Toast.LENGTH_LONG).show();
      
      /*�M��EditText*/
      mEditText.setText("");
      }   
     }
    );
     }
}
