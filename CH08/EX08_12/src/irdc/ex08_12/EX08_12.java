package irdc.ex08_12;

/* import����class */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class EX08_12 extends Activity
{
  /* �ܼƫŧi */
  Button mButton;
  EditText mEdit1;
  EditText mEdit2;
  EditText mEdit3;
  EditText mEdit4;
  EditText mEdit5;
  /* �֦h������XML-RPC���} */
  private String path=
    "http://xmlrpc.blog.roodo.com/cgi-bin/mt/mt-xmlrpc.cgi";
  /* XML-RPC�o�G�峹��method name */
  private String method="metaWeblog.newPost";
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    /* ��l�ƪ��� */
    mEdit1=(EditText)findViewById(R.id.blogId);
    mEdit2=(EditText)findViewById(R.id.blogAccount);
    mEdit3=(EditText)findViewById(R.id.blogPwd);
    mEdit4=(EditText)findViewById(R.id.artTitle);
    mEdit5=(EditText)findViewById(R.id.artContent);  
    mButton=(Button)findViewById(R.id.myButton);
    /* �]�w�o�G�峹��onClick�ƥ� */
    mButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /* ���o��J����T */
        String blogId=mEdit1.getText().toString();
        String account=mEdit2.getText().toString();
        String pwd=mEdit3.getText().toString();
        String title=mEdit4.getText().toString();
        String content=mEdit5.getText().toString();
        
        if(blogId.equals("")||account.equals("")||pwd.equals("")||
           title.equals("")||content.equals(""))
        {
          showDialog("����쥼��g��!!");
        }
        else
        {
          /* �o�eXML POST�����Response���e */
          String outS=getPostString(method,blogId,account,
                                    pwd,title,content);
          String re=sendPost(outS);
          showDialog(re);
        }
      }
    });
  }
  
  /* �o�erequest�ܳ����檺�������}��method */
  private String sendPost(String outString)
  {
    HttpURLConnection conn=null;
    String result="";
    URL url = null;   
    try
    {
      url = new URL(path);
      conn = (HttpURLConnection)url.openConnection();
      /* ���\Input�BOutput */
      conn.setDoInput(true);
      conn.setDoOutput(true);
      /* �]�w�ǰe��method=POST */
      conn.setRequestMethod("POST");
      /* setRequestProperty */
      conn.setRequestProperty("Content-Type", "text/xml");
      conn.setRequestProperty("Charset", "UTF-8");
      
      /* �e�Xrequest */
      OutputStreamWriter out = 
        new OutputStreamWriter(conn.getOutputStream(), "utf-8");
      out.write(outString);
      out.flush();
      out.close();
      /* �ѪR�^�Ǫ�XML���e */
      result=parseXML(conn.getInputStream());
      conn.disconnect();
    }
    catch(Exception e)
    {
      conn.disconnect();
      e.printStackTrace();
      showDialog(""+e);
    }
    return result;
  }
  
  /* �ѪRResponse��XML���e��method */
  private String parseXML(InputStream is)
  {
    String result="";
    Document doc = null;
    try
    {
      /* �NXML�ഫ��Document���� */
      DocumentBuilderFactory dbf=
        DocumentBuilderFactory.newInstance();
      DocumentBuilder db=dbf.newDocumentBuilder();
      doc = db.parse(is);
      doc.getDocumentElement().normalize();
      /* �ˬd�^�ǭȬO�_���]�tfault�o��tag�A���N�N��o�G���~ */
      int fault=doc.getElementsByTagName("fault").getLength();
      if(fault>0)
      {
        result+="�o�G���~!\n";
        /* ���ofaultCode(���~�N�X) */
        NodeList nList1=doc.getElementsByTagName("int");
        for (int i = 0; i < nList1.getLength(); ++i)
        {
          String errCode=nList1.item(i).getChildNodes().item(0)
                         .getNodeValue();
          result+="���~�N�X�G"+errCode+"\n";
        }
        /* ���ofaultString(���~�T��) */
        NodeList nList2=doc.getElementsByTagName("string");
        for (int i = 0; i < nList2.getLength(); ++i)
        {
          String errString=nList2.item(i).getChildNodes().item(0)
                           .getNodeValue();
          result+="���~�T���G"+errString+"\n";
        }
      }
      else
      {
        /* �o�G���\�A���o�峹�s�� */
        NodeList nList=doc.getElementsByTagName("string");
        for (int i = 0; i < nList.getLength(); ++i)
        {
          String artId=nList.item(i).getChildNodes().item(0)
                       .getNodeValue();
          result+="�o�G���\!!�峹�s���u"+artId+"�v";
        }
      }
    }
    catch (Exception ioe) 
    {
      showDialog(""+ioe);
    }
    return result;
  }
  
  /* �խn�o�e��XML���e��method */  
  private String getPostString(String method,String blogId,
      String account,String pwd,String title,String content)
  {
    String s="";
    s+="<methodCall>";
    s+="<methodName>"+method+"</methodName>";
    s+="<params>";
    s+="<param><value><string>"+blogId+"</string></value></param>";
    s+="<param><value><string>"+account+"</string></value></param>";
    s+="<param><value><string>"+pwd+"</string></value></param>";
    s+="<param><value><struct>";
    s+="<member><name>title</name>" +
       "<value><string>"+title+"</string></value></member>";
    s+="<member><name>description</name>" +
       "<value><string>"+content+"</string></value></member>";
    s+="</struct></value></param>";
    s+="<param><value><boolean>1</boolean></value></param>";
    s+="</params>";
    s+="</methodCall>";

    return s;
  }
  
  /* ���XDialog��method */
  private void showDialog(String mess)
  {
    new AlertDialog.Builder(EX08_12.this).setTitle("Message")
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