package irdc.ex04_25;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EX04_25 extends Activity
{
  protected Button mButton1,mButton2;
  protected TextView mTextView1;
  protected RadioGroup mRadioGroup1;
  protected boolean mUserChoice = false;
  protected int mMyChoice = -2;
  protected int intTimes = 0;
  protected RadioButton mRadio1,mRadio2,mRadio3;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mButton1 =(Button) findViewById(R.id.myButton1);
    mButton2 =(Button) findViewById(R.id.myButton2);
    mTextView1 = (TextView) findViewById(R.id.myTextView1);
    
    /* RadioGroup Widget */
    mRadioGroup1 = (RadioGroup) findViewById(R.id.myRadioGroup1);
    
    mRadio1 = (RadioButton) findViewById(R.id.myOption1); 
    mRadio2 = (RadioButton) findViewById(R.id.myOption2);
    mRadio3 = (RadioButton) findViewById(R.id.myOption3);
    
    /* ���o�T��RadioButton��ID�A�æs��m��ư}�C�� */
    int[] aryChoose = {mRadio1.getId(), mRadio2.getId(), mRadio3.getId()};
    
    /* �H�üƪ��覡���w���@�Ӭ��t�βq�������� */
    int intRandom = (int) (Math.random() * 3);
    mMyChoice = aryChoose[intRandom];
    
    /* �^�����s�ƥ� */
    mButton1.setOnClickListener(mChoose);
    
    /* �M�ū��s�ƥ� */
    mButton2.setOnClickListener(mClear);
    
    /* RadioGruop��User�ܧ�ﶵ�᪺�ƥ�B�z */
    mRadioGroup1.setOnCheckedChangeListener(mChangeRadio);
  }
  
  /* RadioGruop��User�ܧ�ﶵ�᪺�ƥ�B�z */
  private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener()
  {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
      // TODO Auto-generated method stub
      if(checkedId==mMyChoice)
      {
        /* User�q��F*/
        mUserChoice = true;
      }
      else
      {
        /* User�q���F*/
        mUserChoice = false;
      }
    }
  };
  
  /* �^�����s�ƥ� */
  private Button.OnClickListener mChoose = new Button.OnClickListener()
  {
    @Override
    public void onClick(View v)
    {
      // TODO Auto-generated method stub
      if(mUserChoice)
      {
        mTextView1.setText(R.string.str_guess_correct);
        mUserChoice = false;
        intTimes = 0;
        mRadio1 = (RadioButton) findViewById(R.id.myOption1); 
        mRadio2 = (RadioButton) findViewById(R.id.myOption2);
        mRadio3 = (RadioButton) findViewById(R.id.myOption3);
        int[] aryChoose = {mRadio1.getId(), mRadio2.getId(), mRadio3.getId()};
        int intRandom = (int) (Math.random() * 3);
        mMyChoice = aryChoose[intRandom];
        mRadioGroup1.clearCheck();
      }
      else
      {
        intTimes++;
        mTextView1.setText(getResources().getString(R.string.str_guess_error)+"("+Integer.toString(intTimes)+")");
        Animation shake = AnimationUtils.loadAnimation(EX04_25.this, R.anim.shake);
        v.startAnimation(shake);
      }
    }
  };
  
  /* �M�ū��s�ƥ� */
  private Button.OnClickListener mClear = new Button.OnClickListener()
  {
    @Override
    public void onClick(View v)
    {
      // TODO Auto-generated method stub
      mUserChoice = false;
      intTimes = 0;
      mRadio1 = (RadioButton) findViewById(R.id.myOption1); 
      mRadio2 = (RadioButton) findViewById(R.id.myOption2);
      mRadio3 = (RadioButton) findViewById(R.id.myOption3);
      int[] aryChoose = {mRadio1.getId(), mRadio2.getId(), mRadio3.getId()};
      int intRandom = (int) (Math.random() * 3);
      mMyChoice = aryChoose[intRandom];
      
      mTextView1.setText(R.string.hello);
      mRadioGroup1.clearCheck();
    }
  };
}