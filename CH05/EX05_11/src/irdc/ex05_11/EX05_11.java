package irdc.ex05_11;

/* import����class */
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class EX05_11 extends ListActivity
{
  /* �ܼƫŧi 
     items�G�s����ܪ��W��
     paths�G�s���ɮ׸��|
     rootPath�G�_�l�ؿ�         */
  private List<String> items=null;
  private List<String> paths=null;
  private String rootPath="/";
  private TextView mPath;
  
  @Override
  protected void onCreate(Bundle icicle)
  {
    super.onCreate(icicle);
    
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    /* ��l��mPath�A�ΥH��ܥثe���| */
    mPath=(TextView)findViewById(R.id.mPath);
    getFileDir(rootPath);
  }

  /* ���o�ɮ׬[�c��method */
  private void getFileDir(String filePath)
  {
    /* �]�w�ثe�Ҧb���| */
    mPath.setText(filePath);
    items=new ArrayList<String>();
    paths=new ArrayList<String>();
    File f=new File(filePath);  
    File[] files=f.listFiles();
    
    if(!filePath.equals(rootPath))
    {
      /* �Ĥ@���]�w��[�^��ڥؿ�] */
      items.add("b1");
      paths.add(rootPath);
      /* �ĤG���]�w��[�^��W�@�h] */
      items.add("b2");
      paths.add(f.getParent());
    }
    /* �N�Ҧ��ɮץ[�JArrayList�� */
    for(int i=0;i<files.length;i++)
    {
      File file=files[i];
      items.add(file.getName());
      paths.add(file.getPath());
    }
    
    /* �ϥΦ۩w�q��MyAdapter�ӱN��ƶǤJListActivity */
    setListAdapter(new MyAdapter(this,items,paths));
  }
  
  /* �]�wListItem�Q���U�ɭn�����ʧ@ */
  @Override
  protected void onListItemClick(ListView l,View v,int position,long id)
  {
    File file=new File(paths.get(position));
    if (file.isDirectory())
    {
      /* �p�G�O��Ƨ��N�A����getFileDir() */
      getFileDir(paths.get(position));
    }
    else
    {
      /* �p�G�O�ɮ״N����openFile() */
      openFile(file);
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
    else
    {
      type="*";
    }
    /* �p�G�L�k�����}�ҡA�N���X�n��M�浹�ϥΪ̿�� */
    type += "/*"; 
    return type; 
  }
}