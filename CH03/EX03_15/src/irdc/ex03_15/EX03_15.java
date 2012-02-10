package irdc.ex03_15;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

/*���bLayout�̨ϥ�Gallery widget�A�����ޥγo�ǼҲ�*/
import android.content.Context;
import android.widget.Gallery;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class EX03_15 extends Activity
{
  private TextView mTextView01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mTextView01 = (TextView) findViewById(R.id.myTextView01);
    mTextView01.setText(getString(R.string.str_txt1));
    mTextView01.setTextColor(Color.BLUE);
    
    ((Gallery) findViewById(R.id.myGallery1))
               .setAdapter(new ImageAdapter(this)); 
  }
  
  public class ImageAdapter extends BaseAdapter
  { 
    /* ���O���� myContext��Context�����O */ 
    private Context myContext; 
    
    /*�ϥ�android.R.drawable�̪��Ϥ��@���Ϯw�ӷ��A���A����ư}�C*/
    private int[] myImageIds =
                  { 
                    android.R.drawable.btn_minus,
                    android.R.drawable.btn_radio,
                    android.R.drawable.ic_lock_idle_low_battery, 
                    android.R.drawable.ic_menu_camera
                  }; 
    /* �غc�l�u���@�ӰѼơA�Y�n�x�s��Context */ 
    public ImageAdapter(Context c) { this.myContext = c; } 

    /* �^�ǩҦ��w�w�q���Ϥ��`�ƶq */ 
    public int getCount() { return this.myImageIds.length; } 

    /* �Q��getItem��k�A���o�ثe�e�����v�����}�CID */ 
    public Object getItem(int position) { return position; } 
    public long getItemId(int position) { return position; }
    
    /* ���o�ثe����ܪ��v��View�A�ǤJ�}�CID�ȨϤ�Ū���P���� */ 
    public View getView(int position, View convertView, 
                        ViewGroup parent)
    { 
      /* �إߤ@��ImageView���� */
      ImageView i = new ImageView(this.myContext);
      
      i.setImageResource(this.myImageIds[position]);
      i.setScaleType(ImageView.ScaleType.FIT_XY); 
      
      /* �]�w�o��ImageView���󪺼e���A��쬰dip */ 
      i.setLayoutParams(new Gallery.LayoutParams(120, 120)); 
      return i; 
    } 

    /*�̾ڶZ���������첾�q �Q��getScale�^��views���j�p(0.0f to 1.0f)*/
    public float getScale(boolean focused, int offset)
    { 
      /* Formula: 1 / (2 ^ offset) */ 
      return Math.max(0,1.0f/(float)Math.pow(2,Math.abs(offset)));
     } 
   }
}