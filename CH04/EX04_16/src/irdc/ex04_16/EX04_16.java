package irdc.ex04_16;

/* import����class */
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

public class EX04_16 extends Activity
{
  /*�ŧi�����ܼ�*/
  private ImageView mImageView01;
  private ImageView mImageView02;
  private ImageView mImageView03;
  private Button mButton;
  private TextView mText;
  /*�ŧi���׬�3��int�}�C�A�ñN�T�i�P��id��J
    R.drawable.p01�G����A
    R.drawable.p02�G�®�2
    R.drawable.p03�G����3
    R.drawable.p04�G���J�P�I��*/
  private static int[] s1=new int[]{R.drawable.p01,R.drawable.p02,R.drawable.p03};
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* ���Jmain.xml Layout */
    setContentView(R.layout.main);
    /* ���o�������� */
    mText=(TextView)findViewById(R.id.mText);
    mImageView01=(ImageView)findViewById(R.id.mImage01);
    mImageView02=(ImageView)findViewById(R.id.mImage02);
    mImageView03=(ImageView)findViewById(R.id.mImage03);
    mButton=(Button)findViewById(R.id.mButton);
    /* ����~�P�{�� */
    randon();
    /* ��mImageView01�[�JonClickListener*/
    mImageView01.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /* �T�i�P�P��½���A�ñN����ܪ���i�P�ܳz�� */
        mImageView01.setImageDrawable(getResources().getDrawable(s1[0]));
        mImageView02.setImageDrawable(getResources().getDrawable(s1[1]));
        mImageView03.setImageDrawable(getResources().getDrawable(s1[2]));
        mImageView02.setAlpha(100);
        mImageView03.setAlpha(100);
        /* �̦��S���q��ӨM�wTextView�n��ܪ��T�� */
        if(s1[0]==R.drawable.p01){
          mText.setText("�z!�A�q��F��!!����!");
        }
        else
        {
          mText.setText("�A�q���F��!!�n���n�A�դ@��?");
        }
      }
    });
    
    /* ��mImageView02�[�JonClickListener*/
    mImageView02.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /* �T�i�P�P��½���A�ñN����ܪ���i�P�ܳz�� */
        mImageView01.setImageDrawable(getResources().getDrawable(s1[0]));
        mImageView02.setImageDrawable(getResources().getDrawable(s1[1]));
        mImageView03.setImageDrawable(getResources().getDrawable(s1[2]));
        mImageView01.setAlpha(100);
        mImageView03.setAlpha(100);
        /* �̦��S���q��ӨM�wTextView�n��ܪ��T�� */
        if(s1[1]==R.drawable.p01){
          mText.setText("�z!�A�q��F��!!����!");
        }
        else
        {
          mText.setText("�A�q���F��!!�n���n�A�դ@��?");
        }
      }
    });
    
    /* ��mImageView03�[�JonClickListener*/
    mImageView03.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /* �T�i�P�P��½���A�ñN����ܪ���i�P�ܳz�� */
        mImageView01.setImageDrawable(getResources().getDrawable(s1[0]));
        mImageView02.setImageDrawable(getResources().getDrawable(s1[1]));
        mImageView03.setImageDrawable(getResources().getDrawable(s1[2]));
        mImageView01.setAlpha(100);
        mImageView02.setAlpha(100);
        /* �̦��S���q��ӨM�wTextView�n��ܪ��T�� */
        if(s1[2]==R.drawable.p01){
          mText.setText("�z!�A�q��F��!!����!");
        }
        else
        {
          mText.setText("�A�q���F��!!�n���n�A�դ@��?");
        }
      }
    });
    
    /* ��mButton�[�JonClickListener�A�Ϩ���U��T�i�P��½���I���B���s�~�P*/   
    mButton.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        mText.setText("�q�q�ݬ���A�O���@�i?");
        mImageView01.setImageDrawable(getResources()
        		    .getDrawable(R.drawable.p04));
        mImageView02.setImageDrawable(getResources()
        		    .getDrawable(R.drawable.p04));
        mImageView03.setImageDrawable(getResources()
        		    .getDrawable(R.drawable.p04));
        mImageView01.setAlpha(255);
        mImageView02.setAlpha(255);
        mImageView03.setAlpha(255);
        randon();
      }
    });    
  }
  /*���s�~�P���{��*/
  private void randon()
  {
    for(int i=0;i<3;i++)
    {
      int tmp=s1[i];
      int s=(int)(Math.random()*2);
      s1[i]=s1[s];
      s1[s]=tmp;
    }        
  }
}