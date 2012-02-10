package irdc.EX04_05;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class EX04_05 extends Activity 
{
  /*�ŧi�����ܼ�*/
  private TextView mTextView1;
  private CheckBox mCheckBox1;
  private CheckBox mCheckBox2;
  private CheckBox mCheckBox3;
  
  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      
      /*�z�LfindViewById���oTextView����ýվ��r���e*/
      mTextView1 = (TextView) findViewById(R.id.myTextView1); 
      mTextView1.setText("�A�ҿ�ܪ����ئ�: "); 
      
      /*�z�LfindViewById���o�T��CheckBox����*/
      mCheckBox1=(CheckBox)findViewById(R.id.myCheckBox1);
      mCheckBox2=(CheckBox)findViewById(R.id.myCheckBox2);
      mCheckBox3=(CheckBox)findViewById(R.id.myCheckBox3);
      
      /*�]�wOnCheckedChangeListener���T��CheckBox����*/
      mCheckBox1.setOnCheckedChangeListener(mCheckBoxChanged);
      mCheckBox2.setOnCheckedChangeListener(mCheckBoxChanged);
      mCheckBox3.setOnCheckedChangeListener(mCheckBoxChanged);
      } 
      
      /*�ŧi�ëغconCheckedChangeListener����*/ 
      private CheckBox.OnCheckedChangeListener mCheckBoxChanged 
      = new CheckBox.OnCheckedChangeListener() 
      { 
        /*implement onCheckedChanged��k*/
        @Override 
        public void onCheckedChanged( CompoundButton buttonView,
            boolean isChecked) 
        { 
          // TODO Auto-generated method stub 
          /*�z�LgetString()���oCheckBox����r�r��*/
          String str0="�ҿ諸���ج�: ";
          String str1=getString(R.string.str_checkbox1);
          String str2=getString(R.string.str_checkbox2);
          String str3=getString(R.string.str_checkbox3);
          String plus=";";
          String result="���O�W�L�w���o!!";
          String result2="�٥i�H�A�h�R�X����!!";
          
          /*���@CheckBox�Q�Ŀ��,��CheckBox����r�|����TextView����r���e
           * �T�Ӫ����`�@�K�ر���*/
          if(mCheckBox1.isChecked()==true & mCheckBox2.isChecked()==true
              & mCheckBox3.isChecked()==true) 
          { 
            mTextView1.setText(str0+str1+plus+str2+plus+str3+result);
          } 
          else if(mCheckBox1.isChecked()==false & mCheckBox2.isChecked()==true
              & mCheckBox3.isChecked()==true) 
          { 
            mTextView1.setText(str0+str2+plus+str3+result);
          } 
          else if(mCheckBox1.isChecked()==true & mCheckBox2.isChecked()==false
              & mCheckBox3.isChecked()==true) 
          { 
            mTextView1.setText(str0+str1+plus+str3+result);
          } 
          else if(mCheckBox1.isChecked()==true & mCheckBox2.isChecked()==true
              & mCheckBox3.isChecked()==false) 
          { 
            mTextView1.setText(str0+str1+plus+str2+result);
          } 
          else if(mCheckBox1.isChecked()==false & mCheckBox2.isChecked()==false
              & mCheckBox3.isChecked()==true) 
          { 
            mTextView1.setText(str0+str3+plus+result2);
          } 
          else if(mCheckBox1.isChecked()==false & mCheckBox2.isChecked()==true
              & mCheckBox3.isChecked()==false) 
          { 
            mTextView1.setText(str0+str2);
          } 
          else if(mCheckBox1.isChecked()==true & mCheckBox2.isChecked()==false
              & mCheckBox3.isChecked()==false) 
          { 
            mTextView1.setText(str0+str1);
          } 
          else if(mCheckBox1.isChecked()==false & mCheckBox2.isChecked()==false
              & mCheckBox3.isChecked()==false) 
          { 
            mTextView1.setText(str0);
          } 
        }
       }; 
} 


