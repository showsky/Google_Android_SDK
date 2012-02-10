package irdc.EX04_10;

import android.app.Activity;
import android.os.Bundle;
/*���d�һݨϥΨ쪺class*/
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class EX04_10 extends Activity 
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    /*�z�LfindViewById���o*/
    Gallery g = (Gallery) findViewById(R.id.mygallery);
    /*�s�W�@ImageAdapter�ó]�w��Gallery����*/
    g.setAdapter(new ImageAdapter(this));
    
    /*�]�w�@��itemclickListener��Toast�Q�I��Ϥ�����m*/
    g.setOnItemClickListener(new OnItemClickListener() 
    {
      public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
      {
        Toast.makeText(EX04_10.this, getString(R.string.my_gallery_text_pre) 
            + position+ getString(R.string.my_gallery_text_post), 
            Toast.LENGTH_SHORT).show();
      }
    });
  }
  
  /*��gBaseAdapter�ۭq�@ImageAdapter class*/
  public class ImageAdapter extends BaseAdapter 
  {
    /*�ŧi�ܼ�*/
    int mGalleryItemBackground;
    private Context mContext;
    
    /*ImageAdapter���غc�l*/
    public ImageAdapter(Context c) 
    {
      mContext = c;
      /* �ϥΦbres/values/attrs.xml����<declare-styleable>�w�q
      * ��Gallery�ݩ�.*/
      TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
      /*���oGallery�ݩʪ�Index id*/
      mGalleryItemBackground = a.getResourceId(
          R.styleable.Gallery_android_galleryItemBackground, 0);
      /*������styleable�ݩʯ�����Шϥ�*/ 
      a.recycle();
    }
    
    /*�@�w�n�мg����kgetCount,�Ǧ^�Ϥ��ƥ�*/
    public int getCount() 
    {
      return myImageIds.length;
    }
    
    /*�@�w�n�мg����kgetItem,�Ǧ^position*/
    public Object getItem(int position) 
    {
      return position;
    }
    
    /*�@�w�n�мg����kgetItemId,�Ǧ^position*/
    public long getItemId(int position) 
    {
      return position;
    }
    
    /*�@�w�n�мg����kgetView,�Ǧ^�@View����*/
    public View getView(int position, View convertView, ViewGroup parent) 
    {
      /*����ImageView����*/
      ImageView i = new ImageView(mContext);
      /*�]�w�Ϥ���imageView����*/
      i.setImageResource(myImageIds[position]);
      /*���s�]�w�Ϥ����e��*/
      i.setScaleType(ImageView.ScaleType.FIT_XY);
      /*���s�]�wLayout���e��*/
      i.setLayoutParams(new Gallery.LayoutParams(136, 88));
      /*�]�wGallery�I����*/
      i.setBackgroundResource(mGalleryItemBackground);
      /*�Ǧ^imageView����*/
      return i;
    }
    
    /*�غc�@Integer array�è��o�w���JDrawable���Ϥ�id*/
    private Integer[] myImageIds = 
    {
        R.drawable.photo1,
        R.drawable.photo2,
        R.drawable.photo3,
        R.drawable.photo4,
        R.drawable.photo5,
        R.drawable.photo6,
    };   
  } 
}