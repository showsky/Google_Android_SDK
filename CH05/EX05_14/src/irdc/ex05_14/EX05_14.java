package irdc.ex05_14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EX05_14 extends Activity
{
  private ImageView myImageView;
  private Button myButton;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    myImageView = (ImageView) findViewById(R.id.myImageView);
    myButton = (Button) findViewById(R.id.myButton);

    /* myButton�[�JOnClickListener */
    myButton.setOnClickListener(new Button.OnClickListener()
    {

      @Override
      public void onClick(View v)
      {
        /* ���o���s���r�� */
        String text = ((Button) v).getText().toString();

        /* �r�ꬰget�ɱNmyImageView�]���६���� */
        if (text.equals(getString(R.string.strButton1)))
        {
          ((Button) v).setText(R.string.strButton2);
          myImageView.setImageDrawable(getWallpaper());
        }
        /* �r�ꬰreset�ɱNmyImageView�]����l���� */
        else if (text.equals(getString(R.string.strButton2)))
        {
          ((Button) v).setText(R.string.strButton1);
          myImageView.setImageDrawable(getResources().getDrawable(
              R.drawable.icon));
        }
      }

    });

  }
}