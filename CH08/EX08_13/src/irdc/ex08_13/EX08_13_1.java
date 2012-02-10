package irdc.ex08_13;

/* import����class */
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class EX08_13_1 extends ListActivity
{
  /* �ܼƫŧi */
  private TextView mText;
  private String title="";
  private List<News> li=new ArrayList<News>();
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    /* �]�wlayout��newslist.xml */
    setContentView(R.layout.newslist);
    
    mText=(TextView) findViewById(R.id.myText);
    /* ���oIntent����Bundle���� */
    Intent intent=this.getIntent();
    Bundle bunde = intent.getExtras();
    /* ���oBundle���󤤪���� */
    String path = bunde.getString("path");
    /* �I�sgetRss()���o�ѪR�᪺List */
    li=getRss(path);
    mText.setText(title);
    /* �]�w�۩w�q��MyAdapter */
    setListAdapter(new MyAdapter(this,li));
  }
  
  /* �]�wListItem�Q���U�ɭn�����ʧ@ */
  @Override
  protected void onListItemClick(ListView l,View v,int position,
                                 long id)
  {
    /* ���oNews���� */
    News ns=(News)li.get(position);
    /* new�@��Intent����A�ë��wclass */
    Intent intent = new Intent();
    intent.setClass(EX08_13_1.this,EX08_13_2.class);
    /* new�@��Bundle����A�ñN�n�ǻ�����ƶǤJ */
    Bundle bundle = new Bundle();
    bundle.putString("title",ns.getTitle());
    bundle.putString("desc",ns.getDesc());
    bundle.putString("link",ns.getLink());
    /* �NBundle����assign��Intent */
    intent.putExtras(bundle);
    /* �I�sActivity EX08_13_2 */
    startActivity(intent);
  }
  
  /* ��RXML��method */
  private List<News> getRss(String path)
  {
    List<News> data=new ArrayList<News>();
    URL url = null;     
    try
    {  
      url = new URL(path);
      /* ����SAXParser���� */ 
      SAXParserFactory spf = SAXParserFactory.newInstance(); 
      SAXParser sp = spf.newSAXParser(); 
      /* ����XMLReader���� */ 
      XMLReader xr = sp.getXMLReader(); 
      /* �]�w�۩w�q��MyHandler��XMLReader */ 
      MyHandler myExampleHandler = new MyHandler(); 
      xr.setContentHandler(myExampleHandler);     
      /* �ѪRXML */
      xr.parse(new InputSource(url.openStream()));
      /* ���oRSS���D�P���e�C�� */
      data =myExampleHandler.getParsedData(); 
      title=myExampleHandler.getRssTitle();
    }
    catch (Exception e)
    { 
      /* �o�Ϳ��~�ɦ^��result�^�W�@��activity */
      Intent intent=new Intent();
      Bundle bundle = new Bundle();
      bundle.putString("error",""+e);
      intent.putExtras(bundle);
      /* ���~���^�ǭȳ]�w��99 */
      EX08_13_1.this.setResult(99, intent);
      EX08_13_1.this.finish();
    }
    return data;
  }
}