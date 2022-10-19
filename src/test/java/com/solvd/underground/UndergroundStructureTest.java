package com.solvd.underground;

import com.solvd.underground.domain.structure.*;
import com.solvd.underground.service.*;
import com.solvd.underground.service.impl.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class UndergroundStructureTest {

    private static final LineService lineService = new LineServiceImpl();
    private static final DepotService depotService = new DepotServiceImpl();

    @BeforeTest
    public void startTest() {
        System.out.println("This message goes before a test");
    }

    @BeforeMethod
    public void startMethod() {
        System.out.println("This message goes before a structure method");
    }

    @AfterMethod
    public void endMethod() {
        System.out.println("This message goes after a structure method");
    }

    @AfterTest
    public void endTest() {
        System.out.println("This message goes after a test");
    }

    @BeforeClass
    public void startClass() {
        System.out.println("Start the first class");
    }

    @AfterClass
    public void endClass() {
        System.out.println("End the first class");
    }

    @Test(testName = "Checks if each line has a unique name")
    public void checkLineNameIsNotEqualTest() {
        List<Line> lines = lineService.getAll();

        Assert.assertNotEquals(lines.get(0).getName(), lines.get(1).getName());
    }

    @Test(testName = "Checks if a line has more than one station")
    public void checkLineHasStationsTest() {
        List<Station> stations = lineService.read(1L).getStations();

        Assert.assertTrue(stations.size() > 1);
    }

    @Test(testName = "Checks if station's name is not null")
    public void checkStationNameIsNotNullTest() {
        List<Station> stations = lineService.read(1L).getStations();
        SoftAssert sa = new SoftAssert();

        for (Station station : stations) {
            sa.assertNotNull(station);
        }

        sa.assertAll("Station's name is not null");
    }

    @Test(testName = "Checks if each station has a unique name")
    public void checkStationNameIsUniqueTest() {
        List<Station> stations = lineService.read(1L).getStations();

        for (int i = 0; i < stations.size(); i++) {
            SoftAssert sa = new SoftAssert();
            for (int j = i + 1; j < stations.size(); j++) {
                sa.assertNotEquals(stations.get(i).getName(), stations.get(j).getName());
            }
            sa.assertAll("Station name is unique");
        }
    }

    @Test(testName = "Checks whether depot addresses are not equal")
    public void checkDepotAddressIsNotEqualTest() {
        Depot depotOne = depotService.findDepot(1L);
        Depot depotTwo = depotService.findDepot(2L);

        Assert.assertNotEquals(depotOne.getAddress(), depotTwo.getAddress());
    }

    @Test(testName = "Checks whether station has any platforms")
    public void checkPlatformIsNotNullTest() {
        Station station = lineService.read(1L).getStations().get(1);
        Platform platform = new Platform();
        station.setPlatforms(List.of(platform));

        Assert.assertNotNull(station.getPlatforms());
    }
}
