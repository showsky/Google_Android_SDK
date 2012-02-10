package irdc.ex09_07;
import java.util.List; 
import java.util.Locale; 

import android.content.Context; 
import android.content.Intent; 
import android.location.Address; 
import android.location.Criteria; 
import android.location.Geocoder; 
import android.location.Location; 
import android.location.LocationListener; 
import android.location.LocationManager; 
import android.net.Uri; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import android.widget.TextView; 

import com.google.android.maps.GeoPoint; 
import com.google.android.maps.MapActivity; 
import com.google.android.maps.MapController; 
import com.google.android.maps.MapView; 

public class EX09_07 extends MapActivity 
{ 
  private TextView mTextView01; 
  private LocationManager mLocationManager01; 
  private String strLocationProvider = ""; 
  private Location mLocation01; 
  private MapController mMapController01; 
  private MapView mMapView01; 
  private Button mButton01,mButton02,mButton03; 
  private EditText mEditText01; 
  private int intZoomLevel=0; 
  private GeoPoint fromGeoPoint, toGeoPoint;  
   
  @Override 
  protected void onCreate(Bundle icicle) 
  { 
    // TODO Auto-generated method stub 
    super.onCreate(icicle); 
    setContentView(R.layout.main); 
     
    mTextView01 = (TextView)findViewById(R.id.myTextView1); 
     
    mEditText01 = (EditText)findViewById(R.id.myEditText1); 
    mEditText01.setText 
    ( 
      getResources().getText 
      (R.string.str_default_address).toString() 
    ); 
     
    /* �إ�MapView���� */ 
    mMapView01 = (MapView)findViewById(R.id.myMapView1); 
    mMapController01 = mMapView01.getController(); 
     
    // �]�wMapView����ܿﶵ�]�ìP�B��D�^ 
    mMapView01.setSatellite(true); 
    mMapView01.setStreetView(true); 
     
    // ��j���h�� 
    intZoomLevel = 15; 
    mMapController01.setZoom(intZoomLevel); 
     
    /* �إ�LocationManager������o�t��LOCATION�A�� */ 
    mLocationManager01 =  
    (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
     
    /*  
    * �ۭq��ơA�s��Location Provider�A 
    * �ñN���x�s�bstrLocationProvider���� 
    */ 
    getLocationProvider(); 
     
    /* �ǤJLocation����A��ܩ�MapView */ 
    fromGeoPoint = getGeoByLocation(mLocation01); 
    refreshMapViewByGeoPoint(fromGeoPoint, 
                       mMapView01, intZoomLevel); 
     
    /* �إ�LocationManager����A��ťLocation�ܧ�ɨƥ�A��sMapView */ 
    mLocationManager01.requestLocationUpdates 
    (strLocationProvider, 2000, 10, mLocationListener01); 
     
    mButton01 = (Button)findViewById(R.id.myButton1); 
    mButton01.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        if(mEditText01.getText().toString()!="") 
        { 
          /* ���oUser�n�e���a�}��GeoPoint���� */ 
          toGeoPoint =  
          getGeoByAddress(mEditText01.getText().toString()); 
           
          /* ���|�W��Intent */ 
          Intent intent = new Intent();  
          intent.setAction(android.content.Intent.ACTION_VIEW); 
           
          /* �ǤJ���|�W���һݭn���a�Ц�} */ 
          intent.setData 
          ( 
            Uri.parse("http://maps.google.com/maps?f=d&saddr="+ 
            GeoPointToString(fromGeoPoint)+ 
            "&daddr="+GeoPointToString(toGeoPoint)+ 
            "&hl=tw") 
          ); 
          startActivity(intent); 
        } 
      } 
    }); 
     
    /* ��j�a�� */ 
    mButton02 = (Button)findViewById(R.id.myButton2); 
    mButton02.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        intZoomLevel++; 
        if(intZoomLevel>mMapView01.getMaxZoomLevel()) 
        { 
          intZoomLevel = mMapView01.getMaxZoomLevel(); 
        } 
        mMapController01.setZoom(intZoomLevel); 
      } 
    }); 
     
    /* �Y�p�a�� */ 
    mButton03 = (Button)findViewById(R.id.myButton3); 
    mButton03.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
        intZoomLevel--; 
        if(intZoomLevel<1) 
        { 
          intZoomLevel = 1; 
        } 
        mMapController01.setZoom(intZoomLevel); 
      } 
    }); 
  } 
   
  /* ���������GPS�y�Ч�s�ɪ��ƥ� */ 
  public final LocationListener mLocationListener01 =  
  new LocationListener() 
  { 
    @Override 
    public void onLocationChanged(Location location) 
    { 
      // TODO Auto-generated method stub 
       
      /* ����������m�ܧ�ɡA�Nlocation�ǤJgetMyLocation */ 
      mLocation01 = location; 
      fromGeoPoint = getGeoByLocation(location); 
      refreshMapViewByGeoPoint(fromGeoPoint, 
            mMapView01, intZoomLevel); 
    } 
     
    @Override 
    public void onProviderDisabled(String provider) 
    { 
      // TODO Auto-generated method stub 
      mLocation01 = null; 
    } 
     
    @Override 
    public void onProviderEnabled(String provider) 
    { 
      // TODO Auto-generated method stub 
       
    } 
     
    @Override 
    public void onStatusChanged(String provider, 
                int status, Bundle extras) 
    { 
      // TODO Auto-generated method stub 
       
    } 
  }; 
   
  /* �ǤJLocation����A���^��GeoPoint���� */ 
  private GeoPoint getGeoByLocation(Location location) 
  { 
    GeoPoint gp = null; 
    try 
    { 
      /* ��Location�s�b */ 
      if (location != null) 
      { 
        double geoLatitude = location.getLatitude()*1E6; 
        double geoLongitude = location.getLongitude()*1E6; 
        gp = new GeoPoint((int) geoLatitude, (int) geoLongitude); 
      } 
    } 
    catch(Exception e) 
    { 
      e.printStackTrace(); 
    } 
    return gp; 
  } 
   
  /* ��J�a�}�A���o��GeoPoint���� */ 
  private GeoPoint getGeoByAddress(String strSearchAddress) 
  { 
    GeoPoint gp = null; 
    try 
    { 
      if(strSearchAddress!="") 
      { 
        Geocoder mGeocoder01 = new Geocoder 
        (EX09_07.this, Locale.getDefault()); 
         
        List<Address> lstAddress = mGeocoder01.getFromLocationName
                           (strSearchAddress, 1);
        if (!lstAddress.isEmpty()) 
        { 
          Address adsLocation = lstAddress.get(0); 
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
   
  /* �ǤJgeoPoint��sMapView�̪�Google Map */ 
  public static void refreshMapViewByGeoPoint 
  (GeoPoint gp, MapView mapview, int zoomLevel) 
  { 
    try 
    { 
      mapview.displayZoomControls(true); 
      MapController myMC = mapview.getController(); 
      myMC.animateTo(gp); 
      myMC.setZoom(zoomLevel); 
      mapview.setSatellite(false); 
    } 
    catch(Exception e) 
    { 
      e.printStackTrace(); 
    } 
  } 
   
  /* �ǤJ�g�n�ק�sMapView�̪�Google Map */ 
  public static void refreshMapViewByCode 
  (double latitude, double longitude, 
      MapView mapview, int zoomLevel) 
  { 
    try 
    { 
      GeoPoint p = new GeoPoint((int) latitude, (int) longitude); 
      mapview.displayZoomControls(true); 
      MapController myMC = mapview.getController(); 
      myMC.animateTo(p); 
      myMC.setZoom(zoomLevel); 
      mapview.setSatellite(false); 
    } 
    catch(Exception e) 
    { 
      e.printStackTrace(); 
    } 
  } 
   
  /* �NGeoPoint�̪��g�n�ץHString,String�^�� */ 
  private String GeoPointToString(GeoPoint gp) 
  { 
    String strReturn=""; 
    try 
    { 
      /* ��Location�s�b */ 
      if (gp != null) 
      { 
        double geoLatitude = (int)gp.getLatitudeE6()/1E6; 
        double geoLongitude = (int)gp.getLongitudeE6()/1E6; 
        strReturn = String.valueOf(geoLatitude)+","+
          String.valueOf(geoLongitude); 
      } 
    } 
    catch(Exception e) 
    { 
      e.printStackTrace(); 
    } 
    return strReturn; 
  } 
   
  /* ���oLocationProvider */ 
  public void getLocationProvider() 
  { 
    try 
    { 
      Criteria mCriteria01 = new Criteria(); 
      mCriteria01.setAccuracy(Criteria.ACCURACY_FINE); 
      mCriteria01.setAltitudeRequired(false); 
      mCriteria01.setBearingRequired(false); 
      mCriteria01.setCostAllowed(true); 
      mCriteria01.setPowerRequirement(Criteria.POWER_LOW); 
      strLocationProvider =  
      mLocationManager01.getBestProvider(mCriteria01, true); 
       
      mLocation01 = mLocationManager01.getLastKnownLocation 
      (strLocationProvider); 
    } 
    catch(Exception e) 
    { 
      mTextView01.setText(e.toString()); 
      e.printStackTrace(); 
    } 
  } 
   
  @Override 
  protected boolean isRouteDisplayed() 
  { 
    // TODO Auto-generated method stub 
    return false; 
  } 
}