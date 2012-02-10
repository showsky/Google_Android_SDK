package irdc.ex08_13; 

/* import����class */
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes; 
import org.xml.sax.SAXException; 
import org.xml.sax.helpers.DefaultHandler; 

public class MyHandler extends DefaultHandler
{ 
  /* �ܼƫŧi */
  private boolean in_item = false;
  private boolean in_title = false;
  private boolean in_link = false;
  private boolean in_desc = false;
  private boolean in_date = false;
  private boolean in_mainTitle = false;
  private List<News> li;
  private News news;
  private String title="";
  private StringBuffer buf=new StringBuffer();

  /* �N�ഫ��List<News>��XML��Ʀ^�� */
  public List<News> getParsedData()
  { 
    return li; 
  }
  /* �N�ѪR�X��RSS title�^�� */
  public String getRssTitle()
  { 
    return title; 
  }
  /* XML���}�l�ѪR�ɩI�s��method */
  @Override 
  public void startDocument() throws SAXException
  { 
    li = new ArrayList<News>(); 
  } 
  /* XML��󵲧��ѪR�ɩI�s��method */
  @Override 
  public void endDocument() throws SAXException
  {
  } 
  /* �ѪR��Element���}�Y�ɩI�s��method */
  @Override 
  public void startElement(String namespaceURI, String localName, 
               String qName, Attributes atts) throws SAXException
  { 
    if (localName.equals("item"))
    { 
      this.in_item = true;
      /* �ѪR��item���}�Y��new�@��News���� */
      news=new News();
    }
    else if (localName.equals("title"))
    { 
      if(this.in_item)
      {
        this.in_title = true;
      }
      else
      {
        this.in_mainTitle = true;
      }
    }
    else if (localName.equals("link"))
    { 
      if(this.in_item)
      {
        this.in_link = true;
      }
    }
    else if (localName.equals("description"))
    { 
      if(this.in_item)
      {
        this.in_desc = true;
      }
    }
    else if (localName.equals("pubDate"))
    { 
      if(this.in_item)
      {
        this.in_date = true;
      }
    } 
  }
  /* �ѪR��Element�������ɩI�s��method */
  @Override 
  public void endElement(String namespaceURI, String localName,
                         String qName) throws SAXException
  { 
    if (localName.equals("item"))
    { 
      this.in_item = false;
      /* �ѪR��item�������ɱNNews����g�JList�� */
      li.add(news);
    }
    else if (localName.equals("title"))
    { 
      if(this.in_item)
      {
        /* �]�wNews����title */
        news.setTitle(buf.toString().trim());
        buf.setLength(0);
        this.in_title = false;
      }
      else
      {
        /* �]�wRSS��title */
        title=buf.toString().trim();
        buf.setLength(0);
        this.in_mainTitle = false;
      }
    }
    else if (localName.equals("link"))
    { 
      if(this.in_item)
      {
        /* �]�wNews����link */
        news.setLink(buf.toString().trim());
        buf.setLength(0);
        this.in_link = false;
      }
    }
    else if (localName.equals("description"))
    { 
      if(in_item)
      {
        /* �]�wNews����description */
        news.setDesc(buf.toString().trim());
        buf.setLength(0);
        this.in_desc = false;
      }
    }
    else if (localName.equals("pubDate"))
    { 
      if(in_item)
      {
        /* �]�wNews����pubDate */
        news.setDate(buf.toString().trim());
        buf.setLength(0);
        this.in_date = false;
      }
    } 
  } 
  /* ���oElement���}�Y�������������r�� */
  @Override 
  public void characters(char ch[], int start, int length)
  { 
    if(this.in_item||this.in_mainTitle)
    {
      /* �Nchar[]�[�JStringBuffer */
      buf.append(ch,start,length);
    }
  } 
}