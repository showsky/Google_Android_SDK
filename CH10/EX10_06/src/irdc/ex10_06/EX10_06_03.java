package irdc.ex10_06;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/* �s���\�U���Activity */
public class EX10_06_03 extends Activity
{
  private ListView mListView01;
  static final private int MENU_ADD = Menu.FIRST;
  static final private int MENU_DRAW = Menu.FIRST+2;
  private MySQLiteOpenHelper dbHelper=null;
  /* version�����j�󵥩�1 */
  private int version = 1;
  private List<String> allRestaurantID;
  private List<String> allRestaurantName;
  private List<String> allRestaurantAddress;
  private List<String> allRestaurantCal;
  private List<String> lstRestaurant;
  private int intItemSelected=-1;
  
  /* ��Ʈw��ƪ�  */
  private String tables[] = { "t_restaurant" };
  
  /* ��Ʈw���W��  */
  private String fieldNames[][] =
  {
    { "f_id", "f_name", "f_address", "f_cal" }
  };
  
  /* ��Ʈw����ƫ��A  */
  private String fieldTypes[][] =
  {
    { "INTEGER PRIMARY KEY AUTOINCREMENT", "text" , "text", "text"}
  };
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_list);
    
    mListView01 = (ListView)findViewById(R.id.myListView1);
    dbHelper = new MySQLiteOpenHelper(this, "mydb", null, version, tables, fieldNames, fieldTypes);
    
    updateListView();
    
    mListView01.setOnItemClickListener(new ListView.OnItemClickListener()
    {
      @Override
      public void onItemClick(AdapterView<?> parent, View v, int id,  long arg3)
      {
        // TODO Auto-generated method stub
        /* f_id��� */
        intItemSelected = id;
        
        /* �u�X����洣�Ѩ�إ\��]�s��B�R���^ */
        String[] dlgMenu=
        {
          getResources().getText(R.string.str_edit_it).toString(),
          getResources().getText(R.string.str_del_it).toString()
        };
        new AlertDialog.Builder(EX10_06_03.this)
        .setTitle(R.string.str_whattodo)
        .setItems(dlgMenu, mListener1)
        .setPositiveButton(R.string.str_cancel,
        new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int which)
          {
          }
        }).show();
      }
    });
  }
  
  private OnClickListener mListener1=new DialogInterface.OnClickListener()
  {
    @Override
    public void onClick(DialogInterface dialog, int which)
    {
      // TODO Auto-generated method stub
      switch(which)
      {
        case 0:
          /* ��ƭק� */
          if(intItemSelected>=0)
          {
            /* �إ߭I���ҽk�ĪG���e������ */
            final Dialog d = new Dialog(EX10_06_03.this);
            Window window = d.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            d.setTitle(R.string.str_edit_it);
            d.setContentView(R.layout.layout_edit);
            
            /* �NUser�I�諸�\�U��Ʃ�J����Widget�� */
            final EditText mEditText01 = (EditText)d.findViewById(R.id.myEditText1);
            final EditText mEditText02 = (EditText)d.findViewById(R.id.myEditText2);
            final EditText mEditText03 = (EditText)d.findViewById(R.id.myEditText3);
            mEditText01.setText(allRestaurantName.get(intItemSelected));
            mEditText02.setText(allRestaurantAddress.get(intItemSelected));
            mEditText03.setText(allRestaurantCal.get(intItemSelected));
            
            /* ��s��ƫ��s�ƥ�B�z */
            Button mButton01 = (Button)d.findViewById(R.id.myButton2);
            mButton01.setOnClickListener(new Button.OnClickListener()
            {
              @Override
              public void onClick(View arg0)
              {
                // TODO Auto-generated method stub
                String[] updateFields = { "f_name", "f_address", "f_cal"};
                String[] updateValues =
                {
                  mEditText01.getText().toString(),
                  mEditText02.getText().toString(),
                  mEditText03.getText().toString()
                };
                String where = "f_id=?";
                String[] whereValue = { allRestaurantID.get(intItemSelected) };
                /* �I�supdate()��s��ƪ�̪����� */
                int intCol = dbHelper.update(tables[0], updateFields, updateValues, where, whereValue);
                /* �^�ǧ�s���\���� >0�� */
                if(intCol>0)
                {
                  updateListView();
                }
                d.dismiss();
              }
            });
            d.show();
          }
          break;
        case 1:
          /* �R����� */
          if(intItemSelected>=0)
          {
            String where = "f_id=?";
            String[] whereValue = { allRestaurantID.get(intItemSelected) };
            int intCol = dbHelper.delete(tables[0], where, whereValue);
            
            /* �^�ǧR�����\���� >0�� */
            if(intCol>0)
            {
              /* �R�����\ */
              updateListView();
            }
          }
          break;
      }
    }
  };
  
  /**
   * ��sListView���
   */
  private void updateListView()
  {
    String f[] = { "f_id", "f_name", "f_address", "f_cal"};
    /* SELECT f[] FROM tables[0] */
    Cursor c = dbHelper.select(tables[0], f, "", null, null, null, null);
    lstRestaurant = new ArrayList<String>();
    allRestaurantID = new ArrayList<String>();
    allRestaurantName = new ArrayList<String>();
    allRestaurantAddress = new ArrayList<String>();
    allRestaurantCal = new ArrayList<String>();
    
    while (c.moveToNext())
    {
      lstRestaurant.add(c.getString(1)+"("+c.getString(3)+getResources().getText(R.string.str_cal)+")");
      allRestaurantID.add(c.getString(0));
      allRestaurantName.add(c.getString(1));
      allRestaurantAddress.add(c.getString(2));
      allRestaurantCal.add(c.getString(3));
    }
    if(lstRestaurant.size()>0)
    {
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(EX10_06_03.this, R.layout.simple_list_item_single_choice, lstRestaurant);
      mListView01.setItemsCanFocus(true);
      mListView01.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
      mListView01.setAdapter(adapter);
    }
    else
    {
      /* ��Ʈw�L�����A�Nflag�]�w��-1 */
      intItemSelected = -1;
      
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(EX10_06_03.this, R.layout.simple_list_item_single_choice, lstRestaurant);
      mListView01.setItemsCanFocus(true);
      mListView01.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
      mListView01.setAdapter(adapter);
    }
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // TODO Auto-generated method stub
    /* menu�s��ID */
    int idGroup1 = 0;
    
    /* The order position of the item */
    int orderItem1 = Menu.NONE;
    int orderItem3 = Menu.NONE+2;
     
    menu.add(idGroup1, MENU_ADD, orderItem1, R.string.str_manu1).setIcon(android.R.drawable.ic_menu_add);
    menu.add(idGroup1, MENU_DRAW, orderItem3, R.string.str_manu3).setIcon(R.drawable.hipposmall);
    return super.onCreateOptionsMenu(menu);
  }
  
  @Override
  public boolean onMenuItemSelected(int featureId, MenuItem item)
  {
    // TODO Auto-generated method stub
    Intent intent = new Intent();
    switch(item.getItemId())
    {
      case (MENU_ADD):
        /* �e���s���\�U�\�� */
        if(dbHelper!=null && dbHelper.getReadableDatabase().isOpen())
        {
          dbHelper.close();
        }
        intent.setClass(EX10_06_03.this, EX10_06_02.class);
        startActivity(intent);
        finish();
        break;
      case (MENU_DRAW):
        /* �e���t�ζüƿ���\�U�\�� */
        if(dbHelper!=null && dbHelper.getReadableDatabase().isOpen())
        {
          dbHelper.close();
        }
        intent.setClass(EX10_06_03.this, EX10_06_04.class);
        startActivity(intent);
        finish();
        break;
    }
    return super.onMenuItemSelected(featureId, item);
  }
  
  @Override
  protected void onResume()
  {
    // TODO Auto-generated method stub
    super.onResume();
  }
  
  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    super.onPause();
  }
  
  @Override
  protected void onDestroy()
  {
    // TODO Auto-generated method stub
    if(dbHelper!=null && dbHelper.getReadableDatabase().isOpen())
    {
      dbHelper.close();
    }
    super.onDestroy();
  }  
}
