package irdc.ex09_03; 

import java.net.HttpURLConnection; 
import java.net.URL; 
import java.net.URLEncoder; 

import android.app.Activity; 
import android.os.Bundle; 
import android.view.KeyEvent; 
import android.view.View; 
import android.webkit.WebView; 
import android.widget.Button; 
import android.widget.EditText; 

public class EX09_03 extends Activity 
{ 
  private Button mButton01; 
  private EditText mEditText01; 
  private WebView mWebView01; 
  private boolean bInternetConnectivity=false; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
     
    /* ���դ���O�_�㦳�s�uGoogle API���s�u��O */ 
    if(checkInternetConnection 
       ("http://code.google.com/intl/zh-TW/apis/chart/","utf-8") 
      ) 
    { 
      bInternetConnectivity = true; 
    } 
     
    mWebView01 = (WebView)findViewById(R.id.myWebView1); 
    mButton01 = (Button)findViewById(R.id.myButton1); 
    mButton01.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        if(mEditText01.getText().toString()!="" &&  
           bInternetConnectivity==true) 
        { 
          mWebView01.loadData 
          ( 
            /* �I�s�ۭq���ݥͦ�QR Code��� */ 
            genGoogleQRChart 
            ( 
              mEditText01.getText().toString(),120 
            ),"text/html", "utf-8" 
          ); 
        } 
      } 
    }); 
     
    mEditText01 = (EditText)findViewById(R.id.myEditText1); 
    mEditText01.setText(R.string.str_text); 
    
    mEditText01.setOnKeyListener(new EditText.OnKeyListener() 
    { 
      @Override 
      public boolean onKey(View v, int keyCode, KeyEvent event) 
      { 
        // TODO Auto-generated method stub 
         
        if(mEditText01.getText().toString()!="" && 
           bInternetConnectivity==true) 
        { 
          mWebView01.loadData 
          ( 
            genGoogleQRChart 
            ( 
              mEditText01.getText().toString(),120 
            ),"text/html", "utf-8" 
          ); 
        } 
        return false; 
      } 
    }); 
  } 
   
  /* �I�sGoogle API�A����QR Code�G�����X */ 
  public String genGoogleQRChart(String strToQRCode, int strWidth) 
  { 
    String strReturn=""; 
    try 
    { 
      strReturn = new String(strToQRCode.getBytes("utf-8")); 
       
      /* �զ�Google API�ݭn���ǿ�ѼƦr�� */ 
      strReturn = "<html><body>"+ 
      "<img src=http://chart.apis.google.com/chart?chs="+ 
      strWidth+"x"+strWidth+"&chl="+ 
      URLEncoder.encode(strReturn, "utf-8")+ 
      "&choe=UTF-8&cht=qr></body></html>"; 
    } 
    catch (Exception e) 
    { 
      e.printStackTrace(); 
    } 
    return strReturn; 
  } 
   
  /* �ˬd�����s�u�O�_���` */ 
  public boolean checkInternetConnection 
  (String strURL, String strEncoding) 
  { 
    /* �̦h����n��Y�L�^���h��ܵL�k�s�u */ 
    int intTimeout = 5; 
    try 
    { 
      HttpURLConnection urlConnection= null; 
      URL url = new URL(strURL); 
      urlConnection=(HttpURLConnection)url.openConnection(); 
      urlConnection.setRequestMethod("GET"); 
      urlConnection.setDoOutput(true); 
      urlConnection.setDoInput(true); 
      urlConnection.setRequestProperty 
      ( 
        "User-Agent","Mozilla/4.0"+ 
        " (compatible; MSIE 6.0; Windows 2000)" 
      ); 
       
      urlConnection.setRequestProperty 
      ("Content-type","text/html; charset="+strEncoding);      
      urlConnection.setConnectTimeout(1000*intTimeout); 
      urlConnection.connect(); 
      if (urlConnection.getResponseCode() == 200) 
      { 
        return true; 
      } 
      else 
      { 
        return false; 
      } 
    } 
    catch (Exception e) 
    { 
      e.printStackTrace(); 
      return false; 
    } 
  } 
   
  /* �ۭqBIG5��UTF-8 */ 
  public String big52unicode(String strBIG5) 
  { 
    String strReturn=""; 
    try 
    { 
      strReturn = new String(strBIG5.getBytes("big5"), "UTF-8"); 
    } 
    catch (Exception e) 
    { 
      e.printStackTrace(); 
    } 
    return strReturn; 
  } 
   
  /* �ۭqUTF-8��BIG5 */ 
  public String unicode2big5(String strUTF8) 
  { 
    String strReturn=""; 
    try 
    { 
      strReturn = new String(strUTF8.getBytes("UTF-8"), "big5"); 
    } 
    catch (Exception e) 
    { 
      e.printStackTrace(); 
    } 
    return strReturn; 
  } 
}