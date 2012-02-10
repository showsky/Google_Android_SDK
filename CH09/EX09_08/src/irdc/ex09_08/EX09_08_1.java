package irdc.ex09_08;

/* import����class */
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class EX09_08_1 extends Activity
{
  private GridView gView;
  private String userId="";
  private List<String[]> li=new ArrayList<String[]>();
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* �]�wlayout��albumlist.xml */
    setContentView(R.layout.albumlist);
    
    /* ���oBundle����userId */
    Intent intent=this.getIntent();
    Bundle bunde = intent.getExtras();
    userId = bunde.getString("userId");

    /* �I�sgetAlbumList()���o��ï��T */
    li=this.getAlbumList(userId);
    /* �]�wgView��Adapter���۩w�q��AlbumAdapter */
    gView=(GridView) findViewById(R.id.myGrid);
    gView.setAdapter(new AlbumAdapter(this,li));
    
    /* �]�wGridView��onItemClick�ƥ� */
    gView.setOnItemClickListener(
        new AdapterView.OnItemClickListener() 
    { 
      @Override
      public void onItemClick(AdapterView<?> arg0,View arg1,
                              int arg2,long arg3)
      {
        /* ��b���B��ïID�B��ï�W�٩�JBundle�ǵ��U�@��Activity */
        Intent intent = new Intent();
        intent.setClass(EX09_08_1.this,EX09_08_2.class);
        Bundle bundle = new Bundle();
        bundle.putString("userId",userId);
        bundle.putString("albumId",li.get(arg2)[0]);
        bundle.putString("title",li.get(arg2)[2]);
        intent.putExtras(bundle);
        startActivityForResult(intent,1);
      } 
    }); 
  }
  
  /* ��RXML���o��ï��T��method */
  private List<String[]> getAlbumList(String id)
  {
    List<String[]> data=new ArrayList<String[]>();
    URL url = null;
    String path="http://picasaweb.google.com/data/feed/api/user/"
                +id.trim();
    try
    {
      url = new URL(path);
      /* �H�ۭq��AlbumHandler�@���ѪRXML��Handler */
      AlbumHandler alHandler = new AlbumHandler(); 
      Xml.parse(url.openConnection().getInputStream(),
                Xml.Encoding.UTF_8,alHandler);
      
      /* ���o��ï��T */
      data =alHandler.getParsedData(); 
    }
    catch (Exception e)
    { 
      /* �o�Ϳ��~�ɦ^��result�^�W�@��activity */
      Intent intent=new Intent();
      Bundle bundle = new Bundle();
      bundle.putString("error",""+e);
      intent.putExtras(bundle);
      /* ���~���^�ǭȳ]�w��99 */
      EX09_08_1.this.setResult(99, intent);
      EX09_08_1.this.finish();
    }
    return data;
  }
  
  /* �мg onActivityResult()*/
  @Override
  protected void onActivityResult(int requestCode,int resultCode,
                                  Intent data)
  {
    switch (resultCode)
    { 
      case 99:
        /* �^�ǿ��~�ɥHDialog��� */
        Bundle bunde = data.getExtras();
        String error = bunde.getString("error");
        showDialog(error);
        break;     
      default: 
        break; 
    } 
  } 
  
  /* ���Dialog��method */
  private void showDialog(String mess){
    new AlertDialog.Builder(EX09_08_1.this).setTitle("Message")
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