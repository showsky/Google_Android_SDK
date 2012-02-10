package irdc.ex03_19;

import android.app.Activity; 
import android.app.ProgressDialog; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 

public class EX03_19 extends Activity
{ 
  /*�إߤ@�ӥ��쪺���O�����ܼơA���O��ProgressDialog����*/
  public ProgressDialog myDialog = null;
    
  @Override 
  public void onCreate(Bundle icicle)
  { 
    super.onCreate(icicle);
    
    /* �إߤ@�ӫ��s���� */ 
    Button btnButton1 = new Button(this); 
    this.setContentView(btnButton1); 
    btnButton1.setText(R.string.str_btn1); 
    
    /* ���إߦn�����s����A���wOnClicklistener 
     * ��Y���U���s�|���檺�ƥ� 
     * �æb�ƥ�B�z��Ƥ����ProgressBar */ 
    btnButton1.setOnClickListener(myShowProgressBar); 
  } 
    
  /** ���U���s���檺OnClickListener�ƥ��� */ 
  Button.OnClickListener myShowProgressBar = new Button.OnClickListener()
  {
    // @Override 
    public void onClick(View arg0)
    {
      CharSequence strDialogTitle = getString(R.string.str_dialog_title);
      CharSequence strDialogBody = getString(R.string.str_dialog_body);
      
      // ���Progress��ܤ��
      myDialog = ProgressDialog.show
                 (
                   EX03_19.this,    
                   strDialogTitle,
                   strDialogBody, 
                   true
                 ); 
       
      new Thread()
      { 
        public void run()
        { 
          try
          { 
            /* �b�o�̼g�W�n���檺�{�����q */
            /* ���F����ݨ��ĪG�A�H�Ȱ�3��@���ܽd */
            sleep(3000); 
          }
          catch (Exception e)
          {
          } 
          // �����ҫإߪ�myDialog����C
          myDialog.dismiss(); 
        }
      }.start(); /* �}�l�������� */
    } 
  }; 
}