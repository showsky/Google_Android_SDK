package irdc.EX08_14;

import android.app.Activity; 
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle; 
import android.util.Log; 
import android.view.View; 
import android.webkit.URLUtil; 
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*���ݤޥ�java.io�Pjava.net*/
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.InputStream; 
import java.net.URL; 
import java.net.URLConnection; 



public class EX08_14 extends Activity 
{ 
  private TextView mTextView01;
  private EditText mEditText01;
  private Button mButton01;
  private static final String TAG = "DOWNLOADAPK"; 
  private String currentFilePath = ""; 
  private String currentTempFilePath = ""; 
  private String strURL="";
  private String fileEx="";
  private String fileNa="";
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
     
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    mButton01 = (Button)findViewById(R.id.myButton1);
    mEditText01 =(EditText)findViewById(R.id.myEditText1);
 
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v) 
      {
        /* �ɮ׷|�U����local�� */ 
        mTextView01.setText("�U����...");
        strURL = mEditText01.getText().toString(); 
        /*���o���w�˵{�����ɮצW��*/
        fileEx = strURL.substring(strURL.lastIndexOf(".")+1,strURL.length()).toLowerCase();
        fileNa = strURL.substring(strURL.lastIndexOf("/")+1,strURL.lastIndexOf("."));
        getFile(strURL);
       }
     }
    );
    
    mEditText01.setOnClickListener(new EditText.OnClickListener()
    {

      @Override
      public void onClick(View arg0)
      {
        // TODO Auto-generated method stub
        mEditText01.setText("");
        mTextView01.setText("���ݦw�˵{��(�п�JURL)");
      }
      
    });
  }

  private void getFile(final String strPath) 
  { 
    try 
    { 
      if (strPath.equals(currentFilePath) ) 
      { 
          
       getDataSource(strPath);  
      }        
      currentFilePath = strPath;      
      Runnable r = new Runnable() 
      {   
        public void run() 
        {   
          try 
          { 
             getDataSource(strPath); 
          } 
          catch (Exception e) 
          { 
            Log.e(TAG, e.getMessage(), e); 
          } 
        } 
      };   
      new Thread(r).start(); 
    } 
    catch(Exception e) 
    { 
      e.printStackTrace(); 
    }
  } 
  
   /*���o�����ɮ�*/ 
  private void getDataSource(String strPath) throws Exception 
  { 
    if (!URLUtil.isNetworkUrl(strPath)) 
    { 
      mTextView01.setText("���~��URL"); 
    } 
    else 
    { 
        /*���oURL*/
        URL myURL = new URL(strPath); 
        /*�إ߳s�u*/
        URLConnection conn = myURL.openConnection();   
        conn.connect();   
        /*InputStream �U���ɮ�*/
        InputStream is = conn.getInputStream();   
        if (is == null) 
        { 
          throw new RuntimeException("stream is null"); 
        } 
        /*�إ߼Ȧs�ɮ�*/ 
        File myTempFile = File.createTempFile(fileNa, "."+fileEx); 
        /*���o���s�ɮ׸��|*/
        currentTempFilePath = myTempFile.getAbsolutePath(); 
        /*�N�ɮ׼g�J�Ȧs��*/ 
        FileOutputStream fos = new FileOutputStream(myTempFile); 
        byte buf[] = new byte[128];   
        do 
        {   
          int numread = is.read(buf);   
          if (numread <= 0) 
          { 
            break; 
          } 
          fos.write(buf, 0, numread);   
        }while (true);  
        
        /*�}���ɮ׶i��w��*/
        openFile(myTempFile);
        //openFile(c);
        try 
        { 
          is.close(); 
        } 
        catch (Exception ex) 
        { 
          Log.e(TAG, "error: " + ex.getMessage(), ex); 
        } 
      }
    }  
   
  /* �b����W�}���ɮת�method */
  private void openFile(File f) 
  {
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setAction(android.content.Intent.ACTION_VIEW);
    
    /* �I�sgetMIMEType()�Ө��oMimeType */
    String type = getMIMEType(f);
    /* �]�wintent��file�PMimeType */
    intent.setDataAndType(Uri.fromFile(f),type);
    startActivity(intent); 
  }

  /* �P�_�ɮ�MimeType��method */
  private String getMIMEType(File f) 
  { 
    String type="";
    String fName=f.getName();
    /* ���o���ɦW */
    String end=fName.substring(fName.lastIndexOf(".")+1,fName.length()).toLowerCase(); 
    
    /* �̪��ɦW�������M�wMimeType */
    if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")||end.equals("xmf")||end.equals("ogg")||end.equals("wav"))
    {
      type = "audio"; 
    }
    else if(end.equals("3gp")||end.equals("mp4"))
    {
      type = "video";
    }
    else if(end.equals("jpg")||end.equals("gif")||end.equals("png")||end.equals("jpeg")||end.equals("bmp"))
    {
      type = "image";
    }
    else if(end.equals("apk")) 
    { 
      /* android.permission.INSTALL_PACKAGES */ 
      type = "application/vnd.android.package-archive"; 
    } 
    else
    {
      type="*";
    }
    /*�p�G�L�k�����}�ҡA�N���X�n��M�浹�ϥΪ̿�� */
    if(end.equals("apk")) 
    { 
    } 
    else 
    { 
      type += "/*";  
    } 
    return type;  
  } 

  /*�ۭq�R���ɮפ�k*/
  private void delFile(String strFileName) 
  { 
    File myFile = new File(strFileName); 
    if(myFile.exists()) 
    { 
      myFile.delete(); 
    } 
  } 
  
  /*��Activity�B��onPause���A��,���TextView��r���A*/
  @Override 
  protected void onPause()
  {
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    mTextView01.setText("�U�����\");
    super.onPause();
  }

  /*��Activity�B��onResume���A��,�R���Ȧs�ɮ�*/
  @Override 
  protected void onResume() 
  { 
    // TODO Auto-generated method stub   
    /* �R���Ȧs�ɮ� */ 
    delFile(currentTempFilePath); 
    super.onResume(); 
  }
} 
