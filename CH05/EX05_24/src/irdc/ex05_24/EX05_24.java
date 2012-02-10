package irdc.ex05_24;

/* import����class */
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.os.Bundle; 
import android.telephony.TelephonyManager;

public class EX05_24 extends ListActivity 
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
    /* ���o����q�ܸ��X */
    item.add(getResources().getText(R.string.str_list0).toString());
    if(telMgr.getLine1Number()!=null)
    {
      value.add(telMgr.getLine1Number());
    }
    else
    {
      value.add("�L�k���o");
    }
    
    /* ���o�q�H������O */
    item.add(getResources().getText(R.string.str_list1).toString());
    if(telMgr.getNetworkCountryIso().equals(""))
    {
      value.add("�L�k���o");
    }
    else
    {
      value.add(""+telMgr.getNetworkCountryIso());
    }
    
    /* ���o�q�H���q�N�X */
    item.add(getResources().getText(R.string.str_list2).toString());
    if(telMgr.getNetworkOperator().equals(""))
    {
      value.add("�L�k���o");
    }
    else
    {
      value.add(telMgr.getNetworkOperator());
    }
    
    /* ���o�q�H���q�W�� */
    item.add(getResources().getText(R.string.str_list3).toString());
    if(telMgr.getNetworkOperatorName().equals(""))
    {
      value.add("�L�k���o");
    }
    else
    {
      value.add(telMgr.getNetworkOperatorName());
    }
    
    /* ���o��ʳq�T���� */
    item.add(getResources().getText(R.string.str_list4).toString());
    if(telMgr.getPhoneType()==telMgr.PHONE_TYPE_GSM)
    {
      value.add("GSM");
    }
    else
    {
      value.add("����");
    }
    
    /* ���o�������� */
    item.add(getResources().getText(R.string.str_list5).toString());
    if(telMgr.getNetworkType()==telMgr.NETWORK_TYPE_EDGE)
    {
      value.add("EDGE");
    }
    else if(telMgr.getNetworkType()==telMgr.NETWORK_TYPE_GPRS)
    {
      value.add("GPRS");
    }
    else if(telMgr.getNetworkType()==telMgr.NETWORK_TYPE_UMTS)
    {
      value.add("UMTS");
    }
    else if(telMgr.getNetworkType()==4)
    {
      value.add("HSDPA");
    }
    else
    {
      value.add("����");
    }
    
    /* ���o���C���A */
    item.add(getResources().getText(R.string.str_list6).toString());
    if(telMgr.isNetworkRoaming())
    {
      value.add("���C��");
    }
    else{
      value.add("�L���C");
    }
    
    /* ���o���IMEI */
    item.add(getResources().getText(R.string.str_list7).toString());
    value.add(telMgr.getDeviceId());
    
    /* ���oIMEI SV */
    item.add(getResources().getText(R.string.str_list8).toString());
    if(telMgr.getDeviceSoftwareVersion()!=null)
    {
      value.add(telMgr.getDeviceSoftwareVersion());
    }
    else
    {
      value.add("�L�k���o");
    }
    
    /* ���o���IMSI */
    item.add(getResources().getText(R.string.str_list9).toString());
    if(telMgr.getSubscriberId()!=null)
    {
      value.add(telMgr.getSubscriberId());
    }
    else
    {
      value.add("�L�k���o");
    }
    
    /* ���oContentResolver */
    ContentResolver cv = EX05_24.this.getContentResolver();
    String tmpS="";
    
    /* ���o�Ūު��A */
    item.add(getResources().getText(R.string.str_list10).toString());
    tmpS=android.provider.Settings.System.getString(cv,android.provider.Settings.System.BLUETOOTH_ON);
    if(tmpS.equals("1"))
    {
      value.add("�w�}��");
    }
    else{
      value.add("���}��");
    }
    
    /* ���oWIFI���A */
    item.add(getResources().getText(R.string.str_list11).toString());
    tmpS=android.provider.Settings.System.getString(cv,android.provider.Settings.System.WIFI_ON);
    if(tmpS.equals("1"))
    {
      value.add("�w�}��");
    }
    else{
      value.add("���}��");
    }
    
    /* ���o����Ҧ��O�_�}�� */
    item.add(getResources().getText(R.string.str_list12).toString());
    tmpS=android.provider.Settings.System.getString(cv,android.provider.Settings.System.AIRPLANE_MODE_ON);
    if(tmpS.equals("1"))
    {
      value.add("�}�Ҥ�");
    }
    else{
      value.add("���}��");
    }
    
    /* ���o�ƾں��C�O�_�}�� */
    item.add(getResources().getText(R.string.str_list13).toString());
    tmpS=android.provider.Settings.System.getString(cv,android.provider.Settings.System.DATA_ROAMING);
    if(tmpS.equals("1"))
    {
      value.add("�}�Ҥ�");
    }
    else{
      value.add("���}��");
    }
    
    /* �ϥΦ۩w�q��MyAdapter�ӱN��ƶǤJListActivity */
    setListAdapter(new MyAdapter(this,item,value));
  } 
} 
