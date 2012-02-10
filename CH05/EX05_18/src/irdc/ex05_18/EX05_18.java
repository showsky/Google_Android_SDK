package irdc.ex05_18;

/* import����class */
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.os.Bundle; 
import android.telephony.TelephonyManager;

public class EX05_18 extends ListActivity 
{ 
  private TelephonyManager telMgr;
  private List<String> item=new ArrayList<String>();
  private List<String> value=new ArrayList<String>();
   
  @SuppressWarnings("static-access")
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main); 
    telMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE); 
    
    /* �N���o����T�g�JList�� */
    /* ���oSIM�d���A */
    item.add(getResources().getText(R.string.str_list0).toString());
    if(telMgr.getSimState()==telMgr.SIM_STATE_READY)
    {
      value.add("�}�n");
    }
    else if(telMgr.getSimState()==telMgr.SIM_STATE_ABSENT)
    {
      value.add("�LSIM�d");
    }
    else
    {
      value.add("SIM�d�Q��w�Υ��������A");
    }
    
    /* ���oSIM�d�d�� */
    item.add(getResources().getText(R.string.str_list1).toString());
    if(telMgr.getSimSerialNumber()!=null)
    {
      value.add(telMgr.getSimSerialNumber());
    }
    else
    {
      value.add("�L�k���o");
    }
    
    /* ���oSIM�d�����ӥN�X */
    item.add(getResources().getText(R.string.str_list2).toString());
    if(telMgr.getSimOperator().equals(""))
    {
      value.add("�L�k���o");
    }
    else
    {
      value.add(telMgr.getSimOperator());
    }
    
    /* ���oSIM�d�����ӦW�� */
    item.add(getResources().getText(R.string.str_list3).toString());
    if(telMgr.getSimOperatorName().equals(""))
    {
      value.add("�L�k���o");
    }
    else
    {
      value.add(telMgr.getSimOperatorName());
    }
    
    /* ���oSIM�d��O */
    item.add(getResources().getText(R.string.str_list4).toString());
    if(telMgr.getSimCountryIso().equals(""))
    {
      value.add("�L�k���o");
    }
    else
    {
      value.add(telMgr.getSimCountryIso());
    }
    
    /* �ϥΦ۩w�q��MyAdapter�ӱN��ƶǤJListActivity */
    setListAdapter(new MyAdapter(this,item,value));
  } 
}