package com.solvd.underground;

import com.solvd.underground.domain.employee.*;
import com.solvd.underground.domain.payment.Token;
import com.solvd.underground.domain.payment.Wallet;
import com.solvd.underground.domain.rollingstock.*;
import com.solvd.underground.domain.structure.*;

import java.time.LocalDate;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        /*
        * Factory pattern is used to employ the new staff members.
        */
        Employee driver = EmploymentFactory.employ(EmployeeType.DRIVER);
        driver.setFirstName("Anton");
        driver.setLastName("Barsky");
        driver.setDob(LocalDate.of(1996, 1, 1));

        Carriage carriageOne = new Carriage();
        carriageOne.setSeatCapacity(100);
        carriageOne.setManufacturer("Stadler");
        carriageOne.setNumber(6654);
        Carriage carriageTwo = new Carriage();
        carriageTwo.setSeatCapacity(100);
        carriageTwo.setManufacturer("Stadler");
        carriageTwo.setNumber(6655);
        Carriage carriageThree = new Carriage();
        carriageThree.setSeatCapacity(100);
        carriageThree.setManufacturer("Stadler");
        carriageThree.setNumber(6656);

        Station stationOne = new Station();
        stationOne.setName("Malinovka");
        Station stationTwo = new Station();
        stationTwo.setName("Kastrychnitskaia");

        Train trainOne = new Train();
        trainOne.setNumber(5566);
        trainOne.setCarriages(List.of(carriageOne, carriageTwo));
        trainOne.setDrivers(List.of((Driver) driver));

        Train trainTwo = new Train();
        trainTwo.setNumber(5567);
        trainTwo.setCarriages(List.of(carriageThree));

        Depot depot = new Depot();
        depot.setAddress("Fabriciusa, 24");
        depot.setTrains(List.of(trainOne, trainTwo));

        /*
        * The builder pattern is used to create an immutable line.
        * We will still be able to add new stations or change the depot, if necessary.
        */
        Line line = Line.builder()
                .id(1L)
                .name("Autozavodskaya")
                .depot(depot)
                .stations(List.of(stationOne, stationTwo))
                .build();

        /*
        * We have to pay to get into the underground station, thus turnstiles support payment with a token,
        * a bank card or a travel card among others ways of payment via strategy pattern.
        */
        Turnstile turnstile = new Turnstile();
        turnstile.setChoiceOfPayment(new Token());
        turnstile.getChoiceOfPayment().pay();
        /*
        * We may have all three payment options in our wallet, so we can choose any of these using decorator pattern.
        */
        Wallet myWallet = new Wallet();
        myWallet.payWithTravelCard();
        /*
        * Train arrives to the station, and each of its carriages has an announcement upon both arrival and departure
        * using Observer, Facade, Decorator patterns.
        */
        EventHolder.arrive(trainOne, EventType.ARRIVAL);
        EventHolder.arrive(trainTwo, EventType.ARRIVAL);
        EventHolder.announce(EventType.ARRIVAL);
        EventHolder.depart(trainOne, EventType.DEPARTURE);
        EventHolder.announce(EventType.DEPARTURE);
    }
}