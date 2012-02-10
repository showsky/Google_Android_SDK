package irdc.ex10_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class EX10_06_04 extends MapActivity
{
  private TextView mTextView01;
  /* �W�@�L�G��menu�ﶵidentifier�A�ΥH�ѧO�ƥ� */
  static final private int MENU_ADD = Menu.FIRST;
  static final private int MENU_EDIT = Menu.FIRST+1;
  static final private int MENU_DRAW = Menu.FIRST+2;
  
  /* Google�a�ϩһݦ����ܼ� */
  private MapView mMapView01;
  private int intZoomLevel=20;
  
  /* ��Ʈw�һݦ����ܼ� */
  private MySQLiteOpenHelper dbHelper=null;
  private int version = 1;
  private List<String> allRestaurantID;
  private List<String> allRestaurantName;
  private List<String> allRestaurantAddress;
  private List<String> allRestaurantCal;
  
  /* ��Ʈw��ƪ� */
  private String tables[] = { "t_restaurant" };
  
  /* ��Ʈw���W�� */
  private String fieldNames[][] =
  {
    { "f_id", "f_name", "f_address", "f_cal" }
  };
  
  /* ��Ʈw����ƫ��A */
  private String fieldTypes[][] =
  {
    { "INTEGER PRIMARY KEY AUTOINCREMENT", "text" , "text", "text"}
  };
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_draw);
    
    mTextView01 = (TextView)findViewById(R.id.myTextView7);
    /* �إ�MapView���� */
    mMapView01 = (MapView)findViewById(R.id.myMapView1);
    
    /* ��Ʈw�s�u */
    dbHelper = new MySQLiteOpenHelper(this, "mydb", null, version, tables, fieldNames, fieldTypes);
    
    /* �t�ο���\�] */
    drawRestaurant();
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // TODO Auto-generated method stub
    /* menu�s��ID */
    int idGroup1 = 0;
    
    /* The order position of the item */
    int orderItem1 = Menu.NONE;
    int orderItem2 = Menu.NONE+1;
    int orderItem3 = Menu.NONE+2;
    
    /* �إ�3��Menu��� */
    menu.add(idGroup1, MENU_ADD, orderItem1, R.string.str_manu1).setIcon(android.R.drawable.ic_menu_add);
    menu.add(idGroup1, MENU_EDIT, orderItem2, R.string.str_manu2).setIcon(android.R.drawable.ic_dialog_info);
    menu.add(idGroup1, MENU_DRAW, orderItem3, R.string.str_manu3).setIcon(R.drawable.hipposmall);
    return super.onCreateOptionsMenu(menu);
  }
  
  @Override
  public boolean onMenuItemSelected(int featureId, MenuItem item)
  {
    // TODO Auto-generated method stub
    Intent intent = new Intent();
    switch(item.getItemId())
    {
      case (MENU_ADD):
        /* �s���\�U��� */
        intent.setClass(EX10_06_04.this, EX10_06_02.class);
        startActivity(intent);
        finish();
        break;
      case (MENU_EDIT):
        /* �s���\�U��� */
        intent.setClass(EX10_06_04.this, EX10_06_03.class);
        startActivity(intent);
        finish();
        break;
      case (MENU_DRAW):
        /* ���ѦY����H */
        drawRestaurant();
        break;
    }
    return super.onMenuItemSelected(featureId, item);
  }
  
  /* �ۭq�üƨ��o�\�U��ƨ�� */
  private void drawRestaurant()
  {
    String f[] = { "f_id", "f_name", "f_address", "f_cal"};
    /* SELECT f[] FROM tables[0] */
    Cursor c = dbHelper.select(tables[0], f, "", null, null, null, null);
    allRestaurantID = new ArrayList<String>();
    allRestaurantName = new ArrayList<String>();
    allRestaurantAddress = new ArrayList<String>();
    allRestaurantCal = new ArrayList<String>();
    
    /* �N�Ҧ��\�U��Ʃ�JList<String>���� */
    while (c.moveToNext())
    {
      allRestaurantID.add(c.getString(0));
      allRestaurantName.add(c.getString(1));
      allRestaurantAddress.add(c.getString(2));
      allRestaurantCal.add(c.getString(3));
    }
    
    if(allRestaurantID.size()>0)
    {
      Random generator = new Random();
      int intThrowIndex = generator.nextInt(allRestaurantID.size());
      mTextView01.setText
      (
        allRestaurantName.get(intThrowIndex)+"\n"+
        allRestaurantAddress.get(intThrowIndex)+"\n"+
        allRestaurantCal.get(intThrowIndex)+
        getResources().getText(R.string.str_cal)
      );
      
      /* �H�a�}�d�ߦa�z�y�� */
      GeoPoint gp = getGeoByAddress(allRestaurantAddress.get(intThrowIndex));
      if(gp==null)
      {
        /* �a�}�L�k�Ϭd��GeoPoint�� */
        mMapView01.setVisibility(MapView.GONE);
      }
      else
      {
        /* ��sMapView�a�� */
        mMapView01.setVisibility(MapView.VISIBLE);
        showImageOverlay(gp);
        refreshMapViewByGeoPoint(getGeoByAddress(allRestaurantAddress.get(intThrowIndex)), mMapView01, intZoomLevel, true);
      }
    }
    else
    {
      /* ��Ʈw�L���� */
      
    }
  }
  
  /**
   * �d�ߦa�}���a�z�y�� 
   * @param strSearchAddress �a�}�r��
   * @return GeoPoint �a�z�y�Ъ���
   */
  private GeoPoint getGeoByAddress(String strSearchAddress)
  {
    GeoPoint gp = null;
    try
    {
      if(strSearchAddress!="")
      {
        Geocoder mGeocoder01 = new Geocoder(EX10_06_04.this, Locale.getDefault());
        List<Address> lstAddress = mGeocoder01.getFromLocationName(strSearchAddress, 1);
        if (!lstAddress.isEmpty())
        {
          Address adsLocation = lstAddress.get(0);
          /* 1E6 = 1000000*/
          double geoLatitude = adsLocation.getLatitude()*1E6;
          double geoLongitude = adsLocation.getLongitude()*1E6;
          gp = new GeoPoint((int) geoLatitude, (int) geoLongitude);
        }
      }
    }
    catch (Exception e)
    { 
      e.printStackTrace(); 
    }
    return gp;
  }
  
  /**
   * ��sMapView�a��
   * @param gp GeoPoint�a�z�y�Ъ���
   * @param mv �d�ߪ���ƪ����W��
   * @param zoomLevel ��j�h��
   * @param setSatellite �O�_��ܽìP�a��
   */
  public static void refreshMapViewByGeoPoint(GeoPoint gp, MapView mv, int zoomLevel, boolean setSatellite)
  {
    try
    {
      mv.displayZoomControls(true);
      MapController mc = mv.getController();
      mc.animateTo(gp);
      mc.setZoom(zoomLevel);
      mv.setSatellite(setSatellite);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  
  /**
   * �b�a�ϤW���Overlay�Ϥ�
   * @param gp GeoPoint�a�z�y�Ъ���
   */
  private void showImageOverlay(GeoPoint gp)
  {
    /* �]�wOverlay */
    GeoPointImageOverlay mLocationOverlay01;
    mLocationOverlay01 = new GeoPointImageOverlay
    (
      gp,
      R.drawable.hipposmall
    );
    List<Overlay> overlays = mMapView01.getOverlays();
    overlays.add(mLocationOverlay01);
  }
  
  @Override
  protected boolean isRouteDisplayed()
  {
    // TODO Auto-generated method stub
    return false;
  }
}
