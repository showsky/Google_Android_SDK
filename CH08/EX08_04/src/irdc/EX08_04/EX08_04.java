package irdc.EX08_04;

import android.app.Activity; 
import android.content.Intent; 
import android.net.Uri; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView; 

public class EX08_04 extends Activity 
{
  /*�ŧi�@��ListView,TextView�����ܼ�
   * �@��String array�ܼ��x�s�ڪ��̷R�M��
   * �PString�ܼƨ��x�s���}*/
  private ListView mListView1; 
  private TextView mTextView1; 
  private String[] myFavor;
  private String  myUrl;
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
     
    /*�z�LfindViewById�غc�l�إ�ListView�PTextView����*/ 
    mListView1 =(ListView) findViewById(R.id.myListView1); 
    mTextView1 = (TextView) findViewById(R.id.myTextView1); 
    mTextView1.setText(getResources().getString(R.string.hello));
    /*�N�ڪ��̷R�M���string.xml���פJ*/
    myFavor = new String[] { 
                               getResources().getString(R.string.str_list_url1), 
                               getResources().getString(R.string.str_list_url2), 
                               getResources().getString(R.string.str_list_url3), 
                               getResources().getString(R.string.str_list_url4) 
                             }; 
    /*�ۭq�@ArrayAdapter�ǳƶǤJListView��,�ñNmyFavor�M��H�ѼƶǤJ*/ 
    ArrayAdapter<String> adapter = new  
    ArrayAdapter<String> 
    (EX08_04.this, android.R.layout.simple_list_item_1, myFavor); 
    
    /*�N�ۭq������ArrayAdapter�ǤJ�ۭq��ListView��*/
    mListView1.setAdapter(adapter);
    /*�NListAdapter���i��(Focusable)���ﶵ���}*/
    mListView1.setItemsCanFocus(true);  
    /*�]�wListView���ﶵ�]���C���u���@�ﶵ*/ 
    mListView1.setChoiceMode 
    (ListView.CHOICE_MODE_SINGLE); 
    /*�]�wListView�ﶵ��nItemClickListener*/
    mListView1.setOnItemClickListener(new ListView.OnItemClickListener()
    { 

      @Override
      /*�мgOnItemClick��k*/
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
          long arg3)
      {
        // TODO Auto-generated method stub
        /*�Y�ҿ��檺��r�PmyFavor�r��}�C�Ĥ@�Ӥ�r�ۦP*/ 
        if(arg0.getAdapter().getItem(arg2).toString()==myFavor[0].toString())
        {
          /*���o���}�éI�sgoToUrl()��k*/
          myUrl=getResources().getString(R.string.str_url1);
          goToUrl(myUrl);
        }
        /*�Y�ҿ��檺��r�PmyFavor�r��}�C�ĤG�Ӥ�r�ۦP*/ 
        else if (arg0.getAdapter().getItem(arg2).toString()==myFavor[1].toString())
        {
          /*���o���}�éI�sgoToUrl()��k*/
          myUrl=getResources().getString(R.string.str_url2);
          goToUrl(myUrl);
        } 
        /*�Y�ҿ��檺��r�PmyFavor�r��}�C�ĤT�Ӥ�r�ۦP*/ 
        else if (arg0.getAdapter().getItem(arg2).toString()==myFavor[2].toString())
        {
          /*���o���}�éI�sgoToUrl()��k*/
          myUrl=getResources().getString(R.string.str_url3);
          goToUrl(myUrl);
        } 
        /*�Y�ҿ��檺��r�PmyFavor�r��}�C�ĥ|�Ӥ�r�ۦP*/ 
        else if (arg0.getAdapter().getItem(arg2).toString()==myFavor[3].toString())
        {
          /*���o���}�éI�sgoToUrl()��k*/
          myUrl=getResources().getString(R.string.str_url4);
          goToUrl(myUrl);
        } 
        /*�H�W�ҫD*/
        else
        {
          /*��ܿ��~�T��*/
          mTextView1.setText("Ooops!!�X���F");
        } 
      }
    }); 
  } 
    /*�}�Һ�������k*/
    private void goToUrl(String url)
    {
      Uri uri = Uri.parse(url); 
      Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
      startActivity(intent); 
    }
} 
