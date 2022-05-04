package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServeiceTests {

  @Test
  public void testMyIp(){
    String geoIp = new GeoIPService().getGeoIPServiceSoap()
            .getIpLocation("192.168.1.1");
    assertEquals(geoIp, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
  }

  @Test(enabled = false)
  public void testInvalidIp(){
    String geoIp = new GeoIPService().getGeoIPServiceSoap()
            .getIpLocation("213.135.86.2xxx");
    assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>77</State></GeoIP>");
  }
}
