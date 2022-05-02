package ru.stqa.pft.soap;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServeiceTests {

  @Test
  public void testMyIp(){
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("192.168.1.1");
    assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>77</State></GeoIP>");
  }

  @Test
  public void testInvalidIp(){
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("192.168.1.xxx");
    assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>77</State></GeoIP>");
  }
}
