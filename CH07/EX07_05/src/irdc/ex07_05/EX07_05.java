package irdc.ex07_05;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class EX07_05 extends Activity 
{
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);   
    Gallery g = (Gallery) findViewById(R.id.mygallery);
            
    /*�s�W�@ImageAdapter�ó]�w��Gallery����*/
    g.setAdapter(new ImageAdapter(this,getSD()));

    /*�]�w�@��itemclickListener�ƥ�*/
    g.setOnItemClickListener(new OnItemClickListener() 
    {
      public void onItemClick(AdapterView<?> parent, 
                       View v, int position, long id) 
      { 
        
      }
    });
  }
  
  private List<String> getSD()
  {
    /* �]�w�ثe�Ҧb���| */
    List<String> it=new ArrayList<String>();      
    File f=new File("/sdcard/");  
    File[] files=f.listFiles();
 
    /* �N�Ҧ��ɮץ[�JArrayList�� */
    for(int i=0;i<files.length;i++)
    {
      File file=files[i];
      if(getImageFile(file.getPath()))
        it.add(file.getPath());
    }
    return it;
  }
    
  private boolean getImageFile(String fName)
  {
    boolean re;
    
    /* ���o���ɦW */
    String end=fName.substring(fName.lastIndexOf(".")+1,
                  fName.length()).toLowerCase(); 
    
    /* �̪��ɦW�������M�wMimeType */
    if(end.equals("jpg")||end.equals("gif")||end.equals("png")
            ||end.equals("jpeg")||end.equals("bmp"))
    {
      re=true;
    }
    else
    {
      re=false;
    }
    return re; 
  }

  /*��gBaseAdapter�ۭq�@ImageAdapter class*/
  public class ImageAdapter extends BaseAdapter 
  {
    /*�ŧi�ܼ�*/
    int mGalleryItemBackground;
    private Context mContext;
    private List<String> lis;
    
    /*ImageAdapter���غc�l*/
    public ImageAdapter(Context c,List<String> li) 
    {
      mContext = c;
      lis=li;
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
      return lis.size();
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
    public View getView(int position, View convertView, 
                          ViewGroup parent) 
    {
      /*����ImageView����*/
      ImageView i = new ImageView(mContext);
      /*�]�w�Ϥ���imageView����*/
      Bitmap bm = BitmapFactory.decodeFile(lis.
                            get(position).toString());
      i.setImageBitmap(bm);
      /*���s�]�w�Ϥ����e��*/
      i.setScaleType(ImageView.ScaleType.FIT_XY);
      /*���s�]�wLayout���e��*/
      i.setLayoutParams(new Gallery.LayoutParams(136, 88));
      /*�]�wGallery�I����*/
      i.setBackgroundResource(mGalleryItemBackground);
      /*�Ǧ^imageView����*/
      return i;
    }     
  } 
}