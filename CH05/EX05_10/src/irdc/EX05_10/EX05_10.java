package irdc.EX05_10;

import android.app.Activity; 
import android.content.Intent; 
import android.os.Bundle; 
/*���ݤޥ�database.Cursor,Contacts.People�P net.uri�����O�Өϥ��p���H���*/
import android.database.Cursor; 
import android.net.Uri; 
import android.provider.Contacts.People; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText;
import android.widget.TextView; 

public class EX05_10 extends Activity 
{ 
  /*�ŧi�|��UI�ܼƻP�@�ӱ`�Ƨ@��Activity�����^�ǭȥ�*/
  private TextView mTextView01; 
  private Button mButton01;
  private EditText mEditText01;
  private EditText mEditText02;
  private static final int PICK_CONTACT_SUBACTIVITY = 2; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    
    /*�z�LfindViewById�غc�l�ӫغc�@��TextView,���EditText,�@��Button����**/
    mTextView01 = (TextView)findViewById(R.id.myTextView1); 
    mEditText01 = (EditText)findViewById(R.id.myEditText01);
    mEditText02 = (EditText)findViewById(R.id.myEditText02);
    mButton01 = (Button)findViewById(R.id.myButton1); 
    
    /*�]�wonClickListener ���ϥΪ��I��Button�ɷj�M�p���H*/
    mButton01.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        /*�غcUri�Ө��o�p���H���귽��m*/
        Uri uri = Uri.parse("content://contacts/people"); 
        /*�z�LIntent�Ө��o�p���H��ƨæ^�ǩҿ諸��*/
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        /*�}�ҷs��Activity�ô����Activity�^�ǭ�*/
        startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY); 
      } 
    }); 
  } 
  
  @Override 
  protected void onActivityResult 
(int requestCode, int resultCode, Intent data) 
  { 
    // TODO Auto-generated method stub 
    switch (requestCode) 
    {  
      case PICK_CONTACT_SUBACTIVITY: 
        final Uri uriRet = data.getData(); 
        if(uriRet != null) 
        { 
          try 
          { 
            /* �����n��android.permission.READ_CONTACTS�v�� */ 
            Cursor c = managedQuery(uriRet, null, null, null, null); 
            /*�NCursor�����Ƴ̫e��*/
            c.moveToFirst(); 
            /*���o�p���H���m�W*/
            String strName =  
            c.getString(c.getColumnIndexOrThrow(People.NAME)); 
            /*���o�p���H���q��*/
            String strPhone =  
            c.getString(c.getColumnIndexOrThrow(People.NUMBER)); 
            /*�N�m�W�P�q�ܼg�JEditText01,EditText02��*/
            mEditText01.setText(strName); 
            mEditText02.setText(strPhone);
          } 
          catch(Exception e) 
          { 
            /*�N���~��T�bTextView�����*/
            mTextView01.setText(e.toString()); 
            e.printStackTrace(); 
          } 
        } 
        break; 
    } 
    super.onActivityResult(requestCode, resultCode, data); 
  } 
}