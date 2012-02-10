package irdc.ex08_10; 

/* import����class */
import java.net.URL; 
import java.net.URLConnection; 
import android.app.Activity; 
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap; 
import android.graphics.BitmapFactory; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText;
import android.widget.ImageView;  

public class EX08_10 extends Activity 
{ 
  /* �ܼƫŧi */
  private Button mButton1;
  private Button mButton2;
  private EditText mEditText;
  private ImageView mImageView; 
  private Bitmap bm;
  
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 

    /* ��l�ƪ��� */
    mButton1 =(Button) findViewById(R.id.myButton1);
    mButton2 =(Button) findViewById(R.id.myButton2);
    mEditText = (EditText) findViewById(R.id.myEdit);
    mImageView = (ImageView) findViewById(R.id.myImage);
    mButton2.setEnabled(false);
    
    /* �w�����ɪ�Button */
    mButton1.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        String path=mEditText.getText().toString();
        if(path.equals(""))
        {
          showDialog("���}���i���ť�!");
        }
        else
        {
          /* �ǤJtype=1���w������ */
          setImage(path,1);
        }
      } 
    });
    
    /* �N���ɳ]���ୱ��Button */
    mButton2.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        try
        {
          String path=mEditText.getText().toString();
          if(path.equals(""))
          {
            showDialog("���}���i���ť�!");
          }
          else
          {
            /* �ǤJtype=2���]�w�ୱ */
            setImage(path,2);
          }
        }
        catch (Exception e)
        {
          showDialog("Ū�����~!���}�i�ण�O���ɩκ��}���~!");
          bm = null;
          mImageView.setImageBitmap(bm);
          mButton2.setEnabled(false);
          e.printStackTrace();
        }
      } 
    }); 
  }
  
  /* �N���ɧ�U�ӹw���Ψó]�w���ୱ��method */
  private void setImage(String path,int type)
  {
    try 
    {  
      URL url = new URL(path); 
      URLConnection conn = url.openConnection(); 
      conn.connect();  
      if(type==1)
      {
        /* �w������ */
        bm = BitmapFactory.decodeStream(conn.getInputStream());
        mImageView.setImageBitmap(bm);
        mButton2.setEnabled(true);
      }
      else if(type==2)
      {
        /* �]�w���ୱ */
        EX08_10.this.setWallpaper(conn.getInputStream());        
        bm = null;
        mImageView.setImageBitmap(bm);
        mButton2.setEnabled(false);
        showDialog("�ୱ�I���]�w����!");
      }   
    } 
    catch (Exception e) 
    { 
      showDialog("Ū�����~!���}�i�ण�O���ɩκ��}���~!");
      bm = null;
      mImageView.setImageBitmap(bm);
      mButton2.setEnabled(false);
      e.printStackTrace(); 
    } 
  }
  
  /* ���XDialog��method */
  private void showDialog(String mess){
    new AlertDialog.Builder(EX08_10.this).setTitle("Message")
    .setMessage(mess)
    .setNegativeButton("�T�w", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int which)
      {          
      }
    })
    .show();
  }  
}