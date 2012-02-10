package irdc.EX05_04;

import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import android.app.Activity; 
/*���ݤޥ�content.Intent���O�Ӷ}��email client*/
import android.content.Intent; 
import android.os.Bundle; 
import android.view.KeyEvent; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 

public class EX05_04 extends Activity 
{ 
  /*�ŧi�|��EditText�@��Button�H�Υ|��String�ܼ�*/
  private EditText mEditText01; 
  private EditText mEditText02; 
  private EditText mEditText03; 
  private EditText mEditText04;
  private Button mButton01; 
  private String[] strEmailReciver;
  private String strEmailSubject; 
  private String[] strEmailCc;
  private String strEmailBody ; 

  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    /*�z�LfindViewById�غc�l�ӫغcButton����*/ 
    mButton01 = (Button)findViewById(R.id.myButton1);
    /*�NButton�w�]�]��Disable�����A*/
    mButton01.setEnabled(false); 
    /*�z�LfindViewById�غc�l�ӫغc�Ҧ�EditText����*/ 
    mEditText01 = (EditText)findViewById(R.id.myEditText1); 
    mEditText02 = (EditText)findViewById(R.id.myEditText2);
    mEditText03 = (EditText)findViewById(R.id.myEditText3);
    mEditText04 = (EditText)findViewById(R.id.myEditText4);
    /*�]�wOnKeyListener,��key�ƥ�o�ͮɶi�����*/
    mEditText01.setOnKeyListener(new EditText.OnKeyListener() 
    { 
      @Override 
      public boolean onKey(View v, int keyCode, KeyEvent event) 
      { 
        // TODO Auto-generated method stub 
        /*�Y�ϥΪ���J�����Wemail��r,�henable ���s �Ϥ��hdisable ���s*/
        if(isEmail(mEditText01.getText().toString())) 
        { 
          mButton01.setEnabled(true); 
        } 
        else 
        { 
          mButton01.setEnabled(false); 
        } 
        return false; 
      } 
    }); 
    
    /*�]�wonClickListener ���ϥΪ��I��Button�ɰe�Xemail*/
    mButton01.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        /*�z�LIntent�ӵo�e�l��*/
        Intent mEmailIntent = new Intent(android.content.Intent.ACTION_SEND);  
        /*�]�w�l��榡��plain/text*/
        mEmailIntent.setType("plain/text");
        
        /*���oEditText01,02,03,04���ȧ@������H�a�},����,�D��,����*/
        strEmailReciver = new String[]{mEditText01.getText().toString()};
        strEmailCc = new String[]{mEditText02.getText().toString()};
        strEmailSubject = mEditText03.getText().toString();
        strEmailBody = mEditText04.getText().toString();
        
        /*�N���o���r���JmEmailIntent��*/
        mEmailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, strEmailReciver); 
        mEmailIntent.putExtra(android.content.Intent.EXTRA_CC, strEmailCc);
        mEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, strEmailSubject);
        mEmailIntent.putExtra(android.content.Intent.EXTRA_TEXT, strEmailBody);
        /*�}��Gmail �ñN�����ѼƶǤJ*/
        startActivity(Intent.createChooser(mEmailIntent, getResources().getString(R.string.str_message))); 
      } 
    }); 
  } 
   
  /*�T�{�r��O�_��email�榡�æ^��true or false*/
  public static boolean isEmail(String strEmail) 
  { 
    String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$"; 
    Pattern p = Pattern.compile(strPattern); 
    Matcher m = p.matcher(strEmail); 
    return m.matches(); 
  } 
} 
