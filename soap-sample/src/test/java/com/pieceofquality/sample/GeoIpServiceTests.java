package com.pieceofquality.sample;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("31.28.251.135");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("31.28.251.xxx");
        assertEquals(geoIP.getReturnCodeDetails(), "Invalid IP address");
    }
}
