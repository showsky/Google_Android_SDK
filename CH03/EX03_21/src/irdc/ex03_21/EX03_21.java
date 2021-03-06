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
     * 套用透明背景的主題
     * setTheme(R.style.Theme_Transparent);
    */
    
    /*
     * 套用布景主題1
     * setTheme(R.style.Theme_Translucent); 
    */
    
    /*
     * 套用布景主題2
    */
    setTheme(R.style.Theme_Translucent2);
    
    setContentView(R.layout.main);
  }
}