package irdc.ex03_21;

import android.app.Activity;
import android.os.Bundle;

public class EX03_21 extends Activity
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    /*
     * �M�γz���I�����D�D
     * setTheme(R.style.Theme_Transparent);
    */
    
    /*
     * �M�Υ����D�D1
     * setTheme(R.style.Theme_Translucent); 
    */
    
    /*
     * �M�Υ����D�D2
    */
    setTheme(R.style.Theme_Translucent2);
    
    setContentView(R.layout.main);
  }
}