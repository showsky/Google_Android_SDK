package irdc.ex06_06;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Contacts;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class EX06_06 extends Activity
{
  private TextView myTextView1;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    myTextView1 = (TextView) findViewById(R.id.myTextView1);

    /* �s�W�ۤv�갵��PhoneStateListener */
    exPhoneCallListener myPhoneCallListener = new exPhoneCallListener();
    /* ���o�q�ܪA�� */
    TelephonyManager tm = (TelephonyManager) this
        .getSystemService(Context.TELEPHONY_SERVICE);
    /* ���UListener */
    tm.listen(myPhoneCallListener, PhoneStateListener.LISTEN_CALL_STATE);

  }

  /* ����class�~��PhoneStateListener */
  public class exPhoneCallListener extends PhoneStateListener
  {
    /* �мgonCallStateChanged���A���ܮɧ���myTextView1����r���C�� */
    public void onCallStateChanged(int state, String incomingNumber)
    {
      switch (state)
      {
        /* �L���󪬺A�� */
        case TelephonyManager.CALL_STATE_IDLE:
          myTextView1.setTextColor(getResources().getColor(R.drawable.red));
          myTextView1.setText("CALL_STATE_IDLE");
          break;
        /* ���_�q�ܮ� */
        case TelephonyManager.CALL_STATE_OFFHOOK:
          myTextView1.setTextColor(getResources().getColor(R.drawable.green));
          myTextView1.setText("CALL_STATE_OFFHOOK");
          break;
        /* �q�ܶi�Ӯ� */
        case TelephonyManager.CALL_STATE_RINGING:
          getContactPeople(incomingNumber);
          break;
        default:
          break;
      }
      super.onCallStateChanged(state, incomingNumber);
    }
  }

  private void getContactPeople(String incomingNumber)
  {
    myTextView1.setTextColor(Color.BLUE);
    ContentResolver contentResolver = getContentResolver();
    Cursor cursor = null;

    /* cursor�̭n�����W�� */
    String[] projection = new String[]
    { Contacts.People._ID, Contacts.People.NAME, Contacts.People.NUMBER };

    /* �Ψӹq�q�ܸ��X�h��ӳs���H */
    cursor = contentResolver.query(Contacts.People.CONTENT_URI, projection,
        Contacts.People.NUMBER + "=?", new String[]
        { incomingNumber }, Contacts.People.DEFAULT_SORT_ORDER);

    /* �䤣�˳s���H */
    if (cursor.getCount() == 0)
    {
      myTextView1.setText("unknown Number:" + incomingNumber);
    } else if (cursor.getCount() > 0)
    {
      cursor.moveToFirst();
      /* �bprojection�o�Ӱ}�C�̦W�r�O��b��1�Ӧ�m */
      String name = cursor.getString(1);
      myTextView1.setText(name + ":" + incomingNumber);
    }

  }
}