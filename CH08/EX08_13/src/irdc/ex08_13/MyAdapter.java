package irdc.ex08_13;

/* import����class */
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/* �۩w�q��Adapter�A�~��android.widget.BaseAdapter */
public class MyAdapter extends BaseAdapter
{
  /* �ܼƫŧi  */
  private LayoutInflater mInflater;
  private List<News> items;

  /* MyAdapter���غc�l�A�ǤJ��ӰѼ�  */  
  public MyAdapter(Context context,List<News> it)
  {
    /* �Ѽƪ�l�� */
    mInflater = LayoutInflater.from(context);
    items = it;
  }
  
  /* �]�~��BaseAdapter�A���мg�H�Umethod */
  @Override
  public int getCount()
  {
    return items.size();
  }

  @Override
  public Object getItem(int position)
  {
    return items.get(position);
  }
  
  @Override
  public long getItemId(int position)
  {
    return position;
  }
  
  @Override
  public View getView(int position,View convertView,ViewGroup par)
  {
    ViewHolder holder;
    
    if(convertView == null)
    {
      /* �ϥΦ۩w�q��news_row�@��Layout */
      convertView = mInflater.inflate(R.layout.news_row, null);
      /* ��l��holder��text�Picon */
      holder = new ViewHolder();
      holder.text = (TextView) convertView.findViewById(R.id.text);      
      convertView.setTag(holder);
    }
    else
    {
      holder = (ViewHolder) convertView.getTag();
    }
    News tmpN=(News)items.get(position);
    holder.text.setText(tmpN.getTitle());
    
    return convertView;
  }
  
  /* class ViewHolder */
  private class ViewHolder
  {
    TextView text;
  }
}