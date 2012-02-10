package irdc.ex04_18;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class EX04_18 extends Activity
{
  private TextView mTextView01;
  private Button mButton01,mButton02;
  private GridView mGridView01;
  private String[] mGames1,mGames2;
  private ArrayAdapter<String> aryAdapter1;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /* 4�Ӧr��}�C */
    mGames1 = new String[]
                        {
                          getResources().getString(R.string.str_list1),
                          getResources().getString(R.string.str_list2),
                          getResources().getString(R.string.str_list3),
                          getResources().getString(R.string.str_list4)
                        };
    
    /* 9�Ӧr��}�C */
    mGames2 = new String[]
                        {
                          getResources().getString(R.string.str_list1),
                          getResources().getString(R.string.str_list2),
                          getResources().getString(R.string.str_list3),
                          getResources().getString(R.string.str_list4),
                          getResources().getString(R.string.str_list1),
                          getResources().getString(R.string.str_list2),
                          getResources().getString(R.string.str_list3),
                          getResources().getString(R.string.str_list4),
                          getResources().getString(R.string.str_list1)
                        };
    
    mButton01 = (Button)findViewById(R.id.myButton1);
    mButton02 = (Button)findViewById(R.id.myButton2);
    mGridView01 = (GridView)findViewById(R.id.myGridView1);
    
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        
        /* 4�Ӥ����A�H2��覡�e�{(2x2) */
        mGridView01.setNumColumns(2);
        aryAdapter1 = new ArrayAdapter<String>(EX04_18.this, R.layout.simple_list_item_1_small, mGames1);
        mGridView01.setAdapter(aryAdapter1);
        //mGridView01.setScrollBarStyle(DEFAULT_KEYS_DIALER);
        mGridView01.setSelection(2);
        mGridView01.refreshDrawableState();
      }
    });
    
    mButton02.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        /* 9�Ӥ����A�H3��覡�e�{(3x3) */
        mGridView01.setNumColumns(3);
        aryAdapter1 = new ArrayAdapter<String>(EX04_18.this, R.layout.simple_list_item_1_small, mGames2);
        mGridView01.setAdapter(aryAdapter1);
      }
    });
    
    mGridView01.setOnItemClickListener(new GridView.OnItemClickListener()
    {
      @Override
      public void onItemClick(AdapterView<?> parent, View v, int position, long arg3)
      {
        // TODO Auto-generated method stub
        
        /* �P�_Adapter�̪������ӼơA�P�_�Q�I�諸�O�ĴX�Ӥ����W�� */
        switch(aryAdapter1.getCount())
        {
          case 4:
            /* position��GridView�̪��������ޭ� */
            mTextView01.setText(mGames1[position]);
            break;
          case 9:
            mTextView01.setText(mGames2[position]);
            break;
        }
      }
    });
  }
}