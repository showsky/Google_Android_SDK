package irdc.ex05_15;

/* import����class */
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class EX05_15 extends ListActivity
{
  /* ����ŧi 
     items�G�s����ܪ��W��
     paths�G�s���ɮ׸��|
     rootPath�G�_�l�ؿ�
  */
  private List<String> items=null;
  private List<String> paths=null;
  private String rootPath="/";
  private TextView mPath;
  private View myView;
  private EditText myEditText;
  
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
      /* �ĤG���]�w��[�^�W�h] */
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
    File file = new File(paths.get(position));
  
    if(file.isDirectory())
    {
      /* �p�G�O��Ƨ��N�A����getFileDir() */
      getFileDir(paths.get(position));
    }
    else
    {
      /* �p�G�O�ɮשI�sfileHandle() */
      fileHandle(file);
    }
  }
  
  /* �B�z�ɮת�method */
  private void fileHandle(final File file){
    /* ���U�ɮ׮ɪ�OnClickListener */
    OnClickListener listener1=new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog,int which)
      {
        if(which==0)
        {
          /* ��ܪ�item���}���ɮ� */
          openFile(file);
        }
        else if(which==1)
        {
          /* ��ܪ�item������ɦW */
          LayoutInflater factory = LayoutInflater.from(EX05_15.this);
          /* ��l��myChoiceView�A�ϥ�rename_alert_dialog��layout */
          myView=factory.inflate(R.layout.rename_alert_dialog,null);
          myEditText=(EditText)myView.findViewById(R.id.mEdit);
          /* �N��l�ɦW����JEditText�� */
          myEditText.setText(file.getName());
            
          /* new�@�ӧ���ɦW��Dialog���T�w���s��listener */
          OnClickListener listener2=new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface dialog, int which)
            {
              /* ���o�ק�᪺�ɮ׸��| */
              String modName=myEditText.getText().toString();
              final String pFile=file.getParentFile().getPath()+"/";
              final String newPath=pFile+modName;
              
              /* �P�_�ɦW�O�_�w�s�b */
              if(new File(newPath).exists())
              {
                /* �ư��ק��ɦW�ɨS�ק諾���e�X�����p */
                if(!modName.equals(file.getName()))
                {
                  /* ���XAlertĵ�i�ɦW���СA�ýT�{�O�_�ק� */
                  new AlertDialog.Builder(EX05_15.this)
                      .setTitle("�`�N!")
                      .setMessage("�ɦW�w�g�s�b�A�O�_�n�л\?")
                      .setPositiveButton("�T�w",new DialogInterface.OnClickListener()
                      {
                        public void onClick(DialogInterface dialog,int which)
                        {          
                          /* �ɦW���Ф��M�ק�|�Чﱼ�w�s�b���ɮ� */
                          file.renameTo(new File(newPath));
                          /* ���s�����ɮצC��ListView */
                          getFileDir(pFile);
                        }
                      })
                      .setNegativeButton("����",new DialogInterface.OnClickListener()
                      {
                        public void onClick(DialogInterface dialog,int which)
                        {
                        }
                      }).show();
                }
              }
              else
              {
                /* �ɦW���s�b�A�������ק�ʧ@ */
                file.renameTo(new File(newPath));
                /* ���s�����ɮצC��ListView */
                getFileDir(pFile);
              }
            }
          };

          /* create����ɦW�ɸ��X��Dialog */
          AlertDialog renameDialog=new AlertDialog.Builder(EX05_15.this).create();
          renameDialog.setView(myView);
          
          /* �]�w����ɦW���U�T�{�᪺Listener */
          renameDialog.setButton("�T�w",listener2);
          renameDialog.setButton2("����",new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface dialog, int which)
            {
            }
          });
          renameDialog.show();
        }
        else
        {
          /* ��ܪ�item���R���ɮ� */
          new AlertDialog.Builder(EX05_15.this).setTitle("�`�N!")
              .setMessage("�T�w�n�R���ɮ׶�?")
              .setPositiveButton("�T�w", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface dialog, int which)
                {          
                  /* �R���ɮ� */
                  file.delete();
                  getFileDir(file.getParent());
                }
              })
              .setNegativeButton("����", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface dialog, int which)
                {
                }
              }).show();
        }
      }
    };
        
    /* ��ܤ@���ɮ׮ɡA���X�n�p��B�z�ɮת�ListDialog */
    String[] menu={"�}���ɮ�","����ɦW","�R���ɮ�"};
    new AlertDialog.Builder(EX05_15.this)
        .setTitle("�A�n���ƻ�?")
        .setItems(menu,listener1)
        .setPositiveButton("����", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int which)
          {
          }
        })
        .show();
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