package com.solvd.underground;

import com.solvd.underground.domain.rollingstock.*;
import com.solvd.underground.service.*;
import com.solvd.underground.service.impl.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class UndergroundRollingStockTest {

    private static final TrainService trainService = new TrainServiceImpl();

    @BeforeMethod
    public void startMethod() {
        System.out.println("Start rolling stock method execution");
    }

    @AfterMethod
    public void endMethod() {
        System.out.println("Rolling stock method has been executed");
    }

    @BeforeClass
    public void startClass() {
        System.out.println("Start the second class!");
    }

    @AfterClass
    public void endClass() {
        System.out.println("End the second class!");
    }

    @Test(testName = "Checks if a train has carriages")
    public void checkTrainCarriagesNotEmptyTest() {
        Train trainOne = trainService.findTrain(1L);
        Train trainTwo = trainService.findTrain(2L);

        Assert.assertNotSame(trainOne, trainTwo);
    }

    @Test(testName = "Checks if train instances are not the same object")
    public void checkTrainIsNotSameTest() {
        Train trainOne = trainService.findTrain(1L);
        Train trainTwo = trainService.findTrain(2L);

        Assert.assertEquals(trainOne.getCarriages().size(), trainTwo.getCarriages().size());
    }

    @Test(testName = "Checks if manufacturer's name in a carriage is not empty")
    public void checkCarriageManufacturerIsEqualTest() {
        List<Carriage> carriages = trainService.findTrain(2L).getCarriages();
        SoftAssert sa = new SoftAssert();

        for (Carriage carriage : carriages) {
            sa.assertEquals(carriage.getManufacturer(), "Stadler");
        }

        sa.assertAll("Each carriage of the train has the same manufacturer's name");
    }

    @Test(testName = "Checks whether each carriage has a unique number")
    public void checkCarriageNumberIsUniqueTest() {
        List<Carriage> carriages = trainService.findTrain(1L).getCarriages();

        for (int i = 0; i < carriages.size(); i++) {
            SoftAssert sa = new SoftAssert();
            for (int j = i + 1; j < carriages.size(); j++) {
                sa.assertNotEquals(carriages.get(i).getNumber(), carriages.get(j).getNumber());
            }
            sa.assertAll("Carriage number is unique");
        }
    }
}