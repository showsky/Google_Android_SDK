package irdc.ex08_13;

/* import����class */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class EX08_13_2 extends Activity
{
  /* �ܼƫŧi */
  private TextView mTitle;
  private TextView mDesc;
  private TextView mLink;
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* �]�wlayout��newscontent.xml */
    setContentView(R.layout.newscontent);
    /* ��l�ƪ��� */
    mTitle=(TextView) findViewById(R.id.myTitle);
    mDesc=(TextView) findViewById(R.id.myDesc);
    mLink=(TextView) findViewById(R.id.myLink);

    /* ���oIntent����Bundle���� */
    Intent intent=this.getIntent();
    Bundle bunde = intent.getExtras();
    /* ���oBundle���󤤪���� */
    mTitle.setText(bunde.getString("title"));
    mDesc.setText(bunde.getString("desc")+"....");
    mLink.setText(bunde.getString("link"));
    /* �]�wmLink�������s�� */
    Linkify.addLinks(mLink,Linkify.WEB_URLS);
  }
}