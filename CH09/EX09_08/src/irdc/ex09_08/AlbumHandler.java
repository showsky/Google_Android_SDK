package irdc.ex09_08; 

/* import����class */
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes; 
import org.xml.sax.SAXException; 
import org.xml.sax.helpers.DefaultHandler; 

public class AlbumHandler extends DefaultHandler
{
  private String gphotoURI="";
  private String mediaURI="";
  private boolean in_entry = false;
  private boolean in_title = false;
  private boolean in_id = false;
  private List<String[]> li;
  private String[] s;
  private StringBuffer buf=new StringBuffer();

  /* �N�ഫ��List��XML��Ʀ^�� */
  public List<String[]> getParsedData()
  { 
    return li; 
  }

  /* XML���}�l�ѪR�ɩI�s��method */
  @Override 
  public void startDocument() throws SAXException
  { 
    li = new ArrayList<String[]>(); 
  }
  
  /* XML��󵲧��ѪR�ɩI�s��method */
  @Override 
  public void endDocument() throws SAXException
  {
  }
  
  /* ���oprefix��method */
  @Override 
  public void startPrefixMapping(String prefix,String uri)
  {
    if(prefix.equals("gphoto"))
    {
      gphotoURI=uri;
    }
    else if(prefix.equals("media"))
    {
      mediaURI=uri;
    }
  }
  
  /* �ѪR��Element���}�Y�ɩI�s��method */
  @Override 
  public void startElement(String namespaceURI, String localName, 
               String qName, Attributes atts) throws SAXException
  { 
    if (localName.equals("entry"))
    { 
      this.in_entry = true;
      /* �ѪR��entry���}�Y��new�@��String[] */
      s=new String[3];
    }
    else if (localName.equals("title"))
    { 
      if(this.in_entry)
      {
        this.in_title = true;
      }
    }
    else if (localName.equals("id"))
    { 
      if(gphotoURI.equals(namespaceURI))
      {
        this.in_id = true;  
      }
    }
    else if (localName.equals("thumbnail"))
    { 
      if(mediaURI.equals(namespaceURI))
      {
        /* ��ï���} */
        s[1]=atts.getValue("url");
      }
    }
  }
  
  /* �ѪR��Element�������ɩI�s��method */
  @Override 
  public void endElement(String namespaceURI, String localName,
                         String qName) throws SAXException
  { 
    if (localName.equals("entry"))
    { 
      this.in_entry = false;
      /* �ѪR��item�������ɱNString[]�g�JList */
      li.add(s);
    }
    else if (localName.equals("title"))
    { 
      if(this.in_entry)
      {
        /* ��ï�W�� */
        s[2]=buf.toString().trim();
        buf.setLength(0);
        this.in_title = false;
      }
    }
    else if (localName.equals("id"))
    { 
      if(gphotoURI.equals(namespaceURI))
      {
        /* ��ïID */
        s[0]=buf.toString().trim();
        buf.setLength(0);
        this.in_id = false;
      }
    }
  }
  
  /* ���oElement���}�Y�������������r�� */
  @Override 
  public void characters(char ch[], int start, int length)
  { 
    if(this.in_title||this.in_id)
    {
      /* �Nchar[]�[�JStringBuffer */
      buf.append(ch,start,length);
    }
  } 
}