package irdc.EX09_01;

import android.app.Activity; 
import android.app.AlertDialog; 
import android.content.DialogInterface; 
import android.content.Intent; 
import android.os.Bundle; 
/*���ݤޥ�util.DisplayMetrics���O�Ө��o�ù��ѪR��*/
import android.util.DisplayMetrics; 
import android.util.Log; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.widget.AbsoluteLayout; 
import android.widget.EditText; 
import android.widget.TextView; 

public class EX09_01 extends Activity 
{ 
  /*�ŧi�ܼ�*/
  private TextView mTextView01; 
  private LayoutInflater mInflater01; 
  private View mView01; 
  private EditText mEditText01,mEditText02; 
  private String TAG = "HIPPO_DEBUG"; 
  /* ����r�����Z */ 
  private int intShiftPadding = 14; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
     
    /* �إ�DisplayMetrics����A���o�ù��ѪR�� */ 
    DisplayMetrics dm = new DisplayMetrics();  
    getWindowManager().getDefaultDisplay().getMetrics(dm); 
     
    mTextView01 = (TextView)findViewById(R.id.myTextView1); 
     
    /* �N��rLabel��b�ù��k�W�� */ 
    mTextView01.setLayoutParams 
    ( 
      new AbsoluteLayout.LayoutParams(intShiftPadding*mTextView01.getText().toString().length(),18,(dm.widthPixels-(intShiftPadding*mTextView01.getText().toString().length()))-10,0) 
    ); 
     
    /* �B�z�ϥΪ��I��TextView��r���ƥ�B�z -�n�J*/ 
    mTextView01.setOnClickListener(new TextView.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
         
        /* ��ܵn�J��ܤ�� */ 
        showLoginForm(); 
      } 
    }); 
  } 
   
  /* �ۭq�n�J��ܤ����� */ 
  private void showLoginForm() 
  { 
    try 
    { 
      /* �HLayoutInflater���o�DActivity��context */ 
      mInflater01 = LayoutInflater.from(EX09_01.this); 
      /* �]�w�إߪ�View�ҭn�ϥΪ�Layout Resource */ 
      mView01 = mInflater01.inflate(R.layout.login, null); 
       
      /* �b��EditText */ 
      mEditText01 = (EditText) mView01.findViewById(R.id.myEditText1); 
       
      /* �K�XEditText */ 
      mEditText02 = (EditText) mView01.findViewById(R.id.myEditText2); 
      
      /*�إ�AlertDialog�����Ө��o�ϥΪ̱b���K�X*/ 
      new AlertDialog.Builder(this) 
      .setView(mView01) 
      .setPositiveButton("OK", 
      new DialogInterface.OnClickListener() 
      { 
        /*�мgonClick()��Ĳ�o���oToken�ƥ�P�����n�J�ƥ�*/
        public void onClick(DialogInterface dialog, int whichButton) 
        { 
          if(processGoogleLogin(mEditText01.getText().toString(), mEditText02.getText().toString())) 
          { 
            Intent i = new Intent(); 
            /*�n�J��I�s�n�X�{��(EX09_01_02.java)*/
            i.setClass(EX09_01.this, EX09_01_02.class); 
            startActivity(i); 
            finish(); 
          } 
        } 
      }).show(); 
    } 
    catch(Exception e) 
    { 
      e.printStackTrace(); 
    } 
  } 
   /*�I�sGoogleAuthSub�Ө���Google�b����Authentication Token*/
  private boolean processGoogleLogin(String strUID, String strUPW) 
  { 
    try 
    { 
      /*�غc�ۭq��GoogtleAuthSub����*/
      GoogleAuthSub gas = new GoogleAuthSub(strUID, strUPW); 
      /*���oGoogle Token*/
      String strAuth =  gas.getAuthSubToken(); 
      /*�N���^��Google Token�g�Jlog��*/
      Log.i(TAG, strAuth); 
       
    } 
    catch (Exception e) 
    { 
      e.printStackTrace(); 
    } 
    return true; 
  } 
} 
