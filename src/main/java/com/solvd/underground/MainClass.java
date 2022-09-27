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
        * Factory pattern is used for the employment of the new staff members.
        */
        Employee driver = EmploymentFactory.employ(EmployeeType.DRIVER);
        driver.setFirstName("Anton");
        driver.setLastName("Barsky");
        driver.setDob(LocalDate.of(1996, 1, 1));

        Carriage carriageOne = new Carriage();
        carriageOne.setSeatCapacity(100);
        carriageOne.setManufacturer("Stadler");
        carriageOne.setNumber(6655);
        Carriage carriageTwo = new Carriage();
        carriageTwo.setSeatCapacity(100);
        carriageTwo.setManufacturer("Stadler");
        carriageTwo.setNumber(6655);

        Station stationOne = new Station();
        stationOne.setName("Malinovka");
        Station stationTwo = new Station();
        stationTwo.setName("Kastrychnitskaia");

        Train train = new Train();
        train.setNumber(5566);
        train.setCarriages(List.of(carriageOne, carriageTwo));
        train.setDrivers(List.of((Driver) driver));

        Depot depot = new Depot();
        depot.setAddress("Fabriciusa, 24");
        depot.setTrains(List.of(train));

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

        EventHolder.arrive(carriageOne, EventType.ARRIVAL);
        EventHolder.arrive(carriageTwo, EventType.ARRIVAL);
//        EventHolder.depart(carriageOne, EventType.DEPARTURE);
        EventHolder.announce(EventType.ARRIVAL);
    }
}