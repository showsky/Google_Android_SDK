package irdc.ex06_08;

import java.io.File;
import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EX06_08 extends Activity
{
  private Button myButton;
  private ProgressBar myProgressBar;
  private TextView myTextView;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    myButton = (Button) findViewById(R.id.myButton);
    myProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);
    myTextView = (TextView) findViewById(R.id.myTextView);

    myButton.setOnClickListener(new Button.OnClickListener()
    {

      @Override
      public void onClick(View arg0)
      {
        showSize();
      }

    });

  }

  private void showSize()
  {
    /* �NTextView��ProgressBar�]�w���ŭȤ�0 */
    myTextView.setText("");
    myProgressBar.setProgress(0);
    /* �P�_�O�Хd�O�_���J */
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
    {
      /* ���oSD CARD�ɮ׸��|�@��O/sdcard */
      File path = Environment.getExternalStorageDirectory();

      /* StatFs���ɮרt�ΪŶ��ϥΪ��p */
      StatFs statFs = new StatFs(path.getPath());
      /* Block��size */
      long blockSize = statFs.getBlockSize();
      /* �`Block�ƶq */
      long totalBlocks = statFs.getBlockCount();
      /* �w�ϥΪ�Block�ƶq */
      long availableBlocks = statFs.getAvailableBlocks();

      String[] total = fileSize(totalBlocks * blockSize);
      String[] available = fileSize(availableBlocks * blockSize);

      /* getMax���o�bmain.xml��ProgressBar�]�w���̤j�� */
      int ss = Integer.parseInt(available[0]) * myProgressBar.getMax()
          / Integer.parseInt(total[0]);

      myProgressBar.setProgress(ss);
      String text = "�`�@" + total[0] + total[1] + "\n";
      text += "�i��" + available[0] + available[1];
      myTextView.setText(text);

    } else if (Environment.getExternalStorageState().equals(
        Environment.MEDIA_REMOVED))
    {
      String text = "SD CARD�w����";
      myTextView.setText(text);
    }
  }

  /* �^�Ǭ��r��}�C[0]���j�p[1]�����KB��MB */
  private String[] fileSize(long size)
  {
    String str = "";
    if (size >= 1024)
    {
      str = "KB";
      size /= 1024;
      if (size >= 1024)
      {
        str = "MB";
        size /= 1024;
      }
    }

    DecimalFormat formatter = new DecimalFormat();
    /* �C3�ӼƦr��,���j�p�G1,000 */
    formatter.setGroupingSize(3);
    String result[] = new String[2];
    result[0] = formatter.format(size);
    result[1] = str;

    return result;
  }

}