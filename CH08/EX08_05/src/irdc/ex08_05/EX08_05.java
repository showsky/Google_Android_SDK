package irdc.ex08_05;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class EX08_05 extends Activity
{
  private Gallery myGallery01;
  /* ���}�C�r�� */
  private String[] myImageURL = new String[]
  {
      "http://lh4.ggpht.com/_2N-HvtdpHZY/Sac5ahGHGeE/AAAAAAAABRc/"
          + "3txi_fNEe3U/s144-c/20090226.jpg",
      "http://lh3.ggpht.com/_2N-HvtdpHZY/Sac43BcwNWE/AAAAAAAABP0/"
          + "apDTAIoyHSE/s144-c/20090225.jpg",
      "http://lh5.ggpht.com/_2N-HvtdpHZY/SZ35ddDLtbE/AAAAAAAABNA/"
          + "Ze_TpD3FFfE/s144-c/20090215.jpg",
      "http://lh6.ggpht.com/_2N-HvtdpHZY/SZ357lAfZNE/AAAAAAAABOE/"
          + "dfxBtdINgPA/s144-c/20090220.jpg",
      "http://lh5.ggpht.com/_2N-HvtdpHZY/SYjwSZO8emE/AAAAAAAABGI/"
          + "EHe7N52mywg/s144-c/20090129.jpg" };

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    myGallery01 = (Gallery) findViewById(R.id.myGallery01);
    myGallery01.setAdapter(new myInternetGalleryAdapter(this));
  }

  /* �갵BaseAdapter */
  public class myInternetGalleryAdapter extends BaseAdapter
  {
    /* ���O���� myContext��Context���� */
    private Context myContext;
    private int mGalleryItemBackground;

    /* �غc�l�u���@�ӰѼơA�Y�n�x�s��Context */
    public myInternetGalleryAdapter(Context c)
    {
      this.myContext = c;
      TypedArray a = myContext
          .obtainStyledAttributes(R.styleable.Gallery);

      /* ���oGallery�ݩʪ�Index id */
      mGalleryItemBackground = a.getResourceId(
          R.styleable.Gallery_android_galleryItemBackground, 0);

      /* ������styleable�ݩʯ�����Шϥ� */
      a.recycle();
    }

    /* �^�ǩҦ��w�w�q���Ϥ��`�ƶq */
    public int getCount()
    {
      return myImageURL.length;
    }

    /* �Q��getItem��k�A���o�ثe�e�����v�����}�CID */
    public Object getItem(int position)
    {
      return position;
    }

    public long getItemId(int position)
    {
      return position;
    }

    /* �̾ڶZ���������첾�q �Q��getScale�^��views���j�p(0.0f to 1.0f) */
    public float getScale(boolean focused, int offset)
    {
      /* Formula: 1 / (2 ^ offset) */
      return Math.max(0, 1.0f / (float) Math.pow(2, Math
          .abs(offset)));
    }

    /* ���o�ثe����ܪ��v��View�A�ǤJ�}�CID�ȨϤ�Ū���P���� */
    @Override
    public View getView(int position, View convertView,
        ViewGroup parent)
    {
      // TODO Auto-generated method stub
      /* �إߤ@��ImageView���� */

      ImageView imageView = new ImageView(this.myContext);
      try
      {
        /* new URL����N���}�ǤJ */
        URL aryURI = new URL(myImageURL[position]);
        /* ���o�s�u */
        URLConnection conn = aryURI.openConnection();
        conn.connect();
        /* ���o�^�Ǫ�InputStream */
        InputStream is = conn.getInputStream();
        /* �NInputStream�ܦ�Bitmap */
        Bitmap bm = BitmapFactory.decodeStream(is);
        /* ����InputStream */
        is.close();
        /* �]�wBitmap��ImageView�� */
        imageView.setImageBitmap(bm);
      } catch (IOException e)
      {
        e.printStackTrace();
      }

      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      /* �]�w�o��ImageView���󪺼e���A��쬰dip */
      imageView.setLayoutParams(new Gallery.LayoutParams(200, 150));
      /* �]�wGallery�I���� */
      imageView.setBackgroundResource(mGalleryItemBackground);
      return imageView;
    }
  }
}