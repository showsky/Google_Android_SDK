package irdc.ex05_08;

/* import����class */
import android.app.Activity; 
import android.os.Bundle; 
import android.widget.Toast;

/* ��user�I��Notification�d�����ɡA�|���檺Activity */ 
public class EX05_08_1 extends Activity 
{ 
  @Override 
  protected void onCreate(Bundle savedInstanceState)
  {  
    super.onCreate(savedInstanceState); 
    
    /* �o�XToast */
    Toast.makeText(EX05_08_1.this,
                   "�o�O����MSN�����n�J���A���{��",
                   Toast.LENGTH_SHORT
                  ).show();
    finish();
  } 
} 