package irdc.ex05_09;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class EX05_09 extends Activity
{
  private AutoCompleteTextView myAutoCompleteTextView;
  private TextView myTextView1;
  private Cursor contactCursor;
  private ContactsAdapter myContactsAdapter;
  /* �n���X�q�T������� */
  public static final String[] PEOPLE_PROJECTION = new String[]
  { Contacts.People._ID, Contacts.People.PRIMARY_PHONE_ID,
      Contacts.People.TYPE, Contacts.People.NUMBER, Contacts.People.LABEL,
      Contacts.People.NAME };

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    myAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.myAutoCompleteTextView);
    myTextView1 = (TextView) findViewById(R.id.myTextView1);

    /* ���oContentResolver */
    ContentResolver content = getContentResolver();

    /* ���o�q�T����Cursor */
    contactCursor = content.query(Contacts.People.CONTENT_URI,
        PEOPLE_PROJECTION, null, null, Contacts.People.DEFAULT_SORT_ORDER);

    /* �NCursor�ǤJ�ۤv�갵��ContactsAdapter */
    myContactsAdapter = new ContactsAdapter(this, contactCursor);

    myAutoCompleteTextView.setAdapter(myContactsAdapter);

    myAutoCompleteTextView
        .setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

          @Override
          public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
              long arg3)
          {
            /* ���oCursor */
            Cursor c = myContactsAdapter.getCursor();
            /* ������I�諸��m */
            c.moveToPosition(arg2);
            String number = c.getString(c
                .getColumnIndexOrThrow(Contacts.People.NUMBER));
            /* ��䤣��q�ܮ���ܵL��J�q�� */
            number = number == null ? "�L��J�q��" : number;
            myTextView1.setText(c.getString(c
                .getColumnIndexOrThrow(Contacts.People.NAME))
                + "���q�ܬO" + number);
          }

        });

  }
}