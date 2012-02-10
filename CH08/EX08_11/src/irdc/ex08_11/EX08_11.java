package irdc.ex08_11;

/* import����class */
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EX08_11 extends Activity
{
  /* �ܼƫŧi
   * newName�G�W�ǫ�b���A���W���ɮצW��
   * uploadFile�G�n�W�Ǫ��ɮ׸��|
   * actionUrl�G���A���W�������{�����| */
  private String newName="image.jpg";
  private String uploadFile="/data/data/irdc.ex08_11/image.jpg";
  private String actionUrl="http://10.10.100.33/upload/upload.jsp";
  private TextView mText1;
  private TextView mText2;
  private Button mButton;
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mText1 = (TextView) findViewById(R.id.myText2);
    mText1.setText("�ɮ׸��|�G\n"+uploadFile);
    mText2 = (TextView) findViewById(R.id.myText3);
    mText2.setText("�W�Ǻ��}�G\n"+actionUrl);
    /* �]�wmButton��onClick�ƥ�B�z */    
    mButton = (Button) findViewById(R.id.myButton);
    mButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        uploadFile();
      }
    });
  }
  
  /* �W���ɮצ�Server��method */
  private void uploadFile()
  {
    String end = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";
    try
    {
      URL url =new URL(actionUrl);
      HttpURLConnection con=(HttpURLConnection)url.openConnection();
      /* ���\Input�BOutput�A���ϥ�Cache */
      con.setDoInput(true);
      con.setDoOutput(true);
      con.setUseCaches(false);
      /* �]�w�ǰe��method=POST */
      con.setRequestMethod("POST");
      /* setRequestProperty */
      con.setRequestProperty("Connection", "Keep-Alive");
      con.setRequestProperty("Charset", "UTF-8");
      con.setRequestProperty("Content-Type",
                         "multipart/form-data;boundary="+boundary);
      /* �]�wDataOutputStream */
      DataOutputStream ds = 
        new DataOutputStream(con.getOutputStream());
      ds.writeBytes(twoHyphens + boundary + end);
      ds.writeBytes("Content-Disposition: form-data; " +
                    "name=\"file1\";filename=\"" +
                    newName +"\"" + end);
      ds.writeBytes(end);   

      /* ���o�ɮת�FileInputStream */
      FileInputStream fStream = new FileInputStream(uploadFile);
      /* �]�w�C���g�J1024bytes */
      int bufferSize = 1024;
      byte[] buffer = new byte[bufferSize];

      int length = -1;
      /* �q�ɮ�Ū����Ʀܽw�İ� */
      while((length = fStream.read(buffer)) != -1)
      {
        /* �N��Ƽg�JDataOutputStream�� */
        ds.write(buffer, 0, length);
      }
      ds.writeBytes(end);
      ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

      /* close streams */
      fStream.close();
      ds.flush();
      
      /* ���oResponse���e */
      InputStream is = con.getInputStream();
      int ch;
      StringBuffer b =new StringBuffer();
      while( ( ch = is.read() ) != -1 )
      {
        b.append( (char)ch );
      }
      /* �NResponse��ܩ�Dialog */
      showDialog(b.toString().trim());
      /* ����DataOutputStream */
      ds.close();
    }
    catch(Exception e)
    {
      showDialog(""+e);
    }
  }
  
  /* ���Dialog��method */
  private void showDialog(String mess)
  {
    new AlertDialog.Builder(EX08_11.this).setTitle("Message")
     .setMessage(mess)
     .setNegativeButton("�T�w",new DialogInterface.OnClickListener()
     {
       public void onClick(DialogInterface dialog, int which)
       {          
       }
     })
     .show();
  }
}