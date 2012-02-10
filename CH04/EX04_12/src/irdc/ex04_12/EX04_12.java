package irdc.ex04_12;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class EX04_12 extends Activity
{
  TextView myTextView;
  ImageButton myImageButton_1;
  ImageButton myImageButton_2;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);

    /* �HfindViewById()���oTextView��ImageButton���� */
    myTextView = (TextView) findViewById(R.id.myTextView);
    myImageButton_1 = (ImageButton) findViewById(R.id.myImageButton_1);
    myImageButton_2 = (ImageButton) findViewById(R.id.myImageButton_2);

    /* myImageButton_1�[�JOnClickListener */
    myImageButton_1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        myTextView.setText("�A���U���OmyImageButton_1");
        /* ���UmyImageButton_1�ɱNmyImageButton_1�Ϥ��m����p3�Ϥ� */
        myImageButton_1.setImageDrawable(getResources().getDrawable(
            R.drawable.p3));
        /* ���UmyImageButton_1�ɱNmyImageButton_2�Ϥ��m����p2�Ϥ� */
        myImageButton_2.setImageDrawable(getResources().getDrawable(
            R.drawable.p2));
      }
    });

    /* myImageButton_2�[�JOnClickListener */
    myImageButton_2.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        myTextView.setText("�A���U���OmyImageButton_2");
        /* ���UmyImageButton_2�ɱNmyImageButton_1�Ϥ��m����p1�Ϥ� */
        myImageButton_1.setImageDrawable(getResources().getDrawable(
            R.drawable.p1));
        /* ���UmyImageButton_2�ɱNmyImageButton_2�Ϥ��m����p3�Ϥ� */
        myImageButton_2.setImageDrawable(getResources().getDrawable(
            R.drawable.p3));
      }
    });
  }
}