package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.common.Category;
import gr.aueb.supermarket.common.Gender;
import gr.aueb.supermarket.common.PaymentMethod;
import gr.aueb.supermarket.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Deprecated
public class Initializer {

    private EntityManager em;

    public Initializer() {
        em = JPAUtil.getCurrentEntityManager();
    }

    private void eraseData() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Erase Date From Database
        em.createNativeQuery("delete from OrderLine").executeUpdate();
        em.createNativeQuery("delete from pickup").executeUpdate();
        em.createNativeQuery("delete from Orders").executeUpdate();
        em.createNativeQuery("delete from Person").executeUpdate();
        em.createNativeQuery("delete from productPositions").executeUpdate();
        em.createNativeQuery("delete from Products").executeUpdate();
        em.createNativeQuery("delete from shelves").executeUpdate();
        em.createNativeQuery("delete from shelfContainers").executeUpdate();


        tx.commit();
    }

    public void prepareData() {

        //Calling Erase Date Method
        eraseData();

        //Begin Transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //Dates And Addresses
            LocalDateTime orderdate = LocalDateTime.now().plusMinutes(15);
            LocalDateTime pickupdate = LocalDateTime.now().plusMinutes(45);
            LocalDate date = LocalDate.now().plusDays(1);
            LocalDate today = LocalDate.now();
            LocalDate todayOrder = LocalDate.now();
            Address address = new Address("Evrou", 5, "Megara", "19100");
            UserInfo userInfo = new UserInfo("nck", "0000");

            //Shelf Containers
            ShelfContainer SC1 = new ShelfContainer(1, 1);
            ShelfContainer SC2 = new ShelfContainer(1, 1);
            ShelfContainer SC3 = new ShelfContainer(1, 1);
            ShelfContainer SC4 = new ShelfContainer(1, 2);
            ShelfContainer SC5 = new ShelfContainer(1, 2);
            ShelfContainer SC6 = new ShelfContainer(1, 2);
            ShelfContainer SC7 = new ShelfContainer(2, 1);
            ShelfContainer SC8 = new ShelfContainer(2, 1);
            ShelfContainer SC9 = new ShelfContainer(2, 2);
            ShelfContainer SC10 = new ShelfContainer(2, 2);

            //Shelves
            Shelf S1 = SC1.addShelf(1,100);
            Shelf S2 = SC1.addShelf(2,100);
            Shelf S3 = SC1.addShelf(3,100);
            Shelf S4 = SC1.addShelf(4, 100);
            Shelf S5 = SC2.addShelf(1, 100);
            Shelf S6 = SC2.addShelf(2, 100);
            Shelf S7 = SC2.addShelf(3, 100);
            Shelf S8 = SC2.addShelf(4, 100);
            Shelf S9 = SC3.addShelf(1, 100);
            Shelf S10 = SC3.addShelf(2, 100);
            Shelf S11 = SC1.addShelf(3, 100);
            Shelf S12 = SC3.addShelf(4, 100);
            Shelf S13 = SC4.addShelf(1, 100);
            Shelf S14 = SC4.addShelf(2, 100);
            Shelf S15 = SC4.addShelf(3, 100);
            Shelf S16 = SC4.addShelf(4, 100);
            Shelf S17 = SC5.addShelf(1, 100);
            Shelf S18 = SC5.addShelf(2, 100);
            Shelf S19 = SC5.addShelf(3, 100);
            Shelf S20 = SC5.addShelf(4, 100);
            Shelf S21 = SC6.addShelf(1, 100);
            Shelf S22 = SC6.addShelf(2, 100);
            Shelf S23 = SC6.addShelf(3, 100);
            Shelf S24 = SC6.addShelf(4, 100);
            Shelf S25 = SC7.addShelf(1, 100);
            Shelf S26 = SC7.addShelf(2, 100);
            Shelf S27 = SC7.addShelf(3, 100);
            Shelf S28 = SC7.addShelf(4, 100);
            Shelf S29 = SC8.addShelf(1, 100);
            Shelf S30 = SC8.addShelf(2, 100);
            Shelf S31 = SC8.addShelf(3, 100);
            Shelf S32 = SC8.addShelf(4, 100);
            Shelf S33 = SC9.addShelf(1, 100);
            Shelf S34 = SC9.addShelf(2, 100);
            Shelf S35 = SC9.addShelf(3, 100);
            Shelf S36 = SC9.addShelf(4, 100);
            Shelf S37 = SC10.addShelf(1, 100);
            Shelf S38 = SC10.addShelf(2, 100);
            Shelf S39 = SC10.addShelf(3, 100);
            Shelf S40 = SC10.addShelf(4, 100);

            // Products
            Product P1 = new Product(1.5, "Lays", Category.SNACKS);
            Product P2 = new Product(1, "Cheetos", Category.SNACKS);
            Product P3 = new Product(0.9, "Delta Milk", Category.MILK);
            Product P4 = new Product(1.5, "Pavlidis", Category.CHOCOLATE);
            Product P5 = new Product(0.7, "Molto", Category.SNACKS);
            Product P6 = new Product(8.0, "Chicken", Category.MEAT);
            Product P7 = new Product(11.5, "Ifantis Turkey", Category.MEAT);
            Product P8 = new Product(21.6, "Blue Cheese", Category.CHEESE);
            Product P9 = new Product(0.5, "Lettuce", Category.VEGETABLES);
            Product P10 = new Product(1.2, "Apples", Category.FRUIT);
            Product P11 = new Product(1, "Bread", Category.SNACKS);
            Product P12 = new Product(1.2, "CheesePie", Category.SNACKS);
            Product P13 = new Product(1.2, "Chicken Nuggets", Category.FROZEN_FOOD);
            Product P14 = new Product(12.2, "Nikas Turkey", Category.MEAT);
            Product P15 = new Product(1.1, "Amita", Category.JUICE);
            Product P16 = new Product(1.4, "Mustard", Category.PREPACKAGED);
            Product P17 = new Product(1.2, "Mayonnaise", Category.PREPACKAGED);
            Product P18 = new Product(1.2, "Oranges", Category.FRUIT);
            Product P19 = new Product(1.7, "Bananas", Category.FRUIT);
            Product P20 = new Product(10.2, "Beef", Category.MEAT);

            //Product Positions
            ProductPosition PP1 = P1.setProductPosition(20, S1);
            ProductPosition PP2 = P2.setProductPosition(30, S1);
            ProductPosition PP3 = P3.setProductPosition(30, S1);
            ProductPosition PP4 = P4.setProductPosition(10, S2);
            ProductPosition PP5 = P5.setProductPosition(50, S3);
            ProductPosition PP6 = P6.setProductPosition(80, S7);
            ProductPosition PP7 = P7.setProductPosition(10, S4);
            ProductPosition PP8 = P8.setProductPosition(90, S6);
            ProductPosition PP9 = P9.setProductPosition(60, S9);
            ProductPosition PP10 = P10.setProductPosition(10, S1);
            ProductPosition PP11 = P11.setProductPosition(30, S25);
            ProductPosition PP12 = P12.setProductPosition(30, S35);
            ProductPosition PP13 = P13.setProductPosition(30, S38);
            ProductPosition PP14 = P14.setProductPosition(30, S40);
            ProductPosition PP15 = P15.setProductPosition(30, S12);
            ProductPosition PP16 = P16.setProductPosition(30, S29);
            ProductPosition PP17 = P17.setProductPosition(30, S27);
            ProductPosition PP18 = P18.setProductPosition(30, S31);
            ProductPosition PP19 = P19.setProductPosition(30, S39);
            ProductPosition PP20 = P20.setProductPosition(30, S24);


            // Persons
            Customer C1 = new Customer("Manolis", "Partsinevelos", "partsinevelosm@gmail.com", "6978600994", date, Gender.MALE, userInfo);
            Customer C2 = new Customer("Nikolaos", "Stentoumis", "stentoumisn@gmail.com", "6979900994", date, Gender.MALE, userInfo);
            Customer C3 = new Customer("Xristos", "Koutsogianopoulos", "kchris.com", "6978440994", date, Gender.MALE, userInfo);
            Customer C4 = new Customer("Antonis", "Papadopoulos", "anp@gmail.com", "6978110994", date, Gender.MALE, userInfo);
            Customer C5 = new Customer("Panagiota", "Antoniou", "pana@gmail.com", "6978770994", date, Gender.MALE, userInfo);

            Employee E1 = new Employee("Nick", "Mitoglou", "nmitoglou@gmail.com", "6971313221", date, Gender.MALE, address, "Sales", "Employee", today, userInfo);
            Employee E2 = new Employee("George", "Stentoumis", "gstm@gmail.com", "6971313441", date, Gender.MALE, address, "Sales", "Director", today, userInfo);
            Employee E3 = new Employee("Dimitris", "Sloukas", "dsloukas@gmail.com", "6971315531", date, Gender.MALE, address, "Sales", "Employee", today, userInfo);

            //Orders
            Order O1 = new Order(todayOrder, orderdate, C1);
            Order O2 = new Order(todayOrder, orderdate, C2);
            Order O3 = new Order(todayOrder, orderdate, C3);
            Order O4 = new Order(todayOrder, orderdate, C1);
            Order O5 = new Order(todayOrder, orderdate, C1);
            Order O6 = new Order(todayOrder, orderdate, C5);
            Order O7 = new Order(todayOrder, orderdate, C4);
            Order O8 = new Order(todayOrder, orderdate, C2);
            Order O9 = new Order(todayOrder, orderdate, C1);
            Order O10 = new Order(todayOrder, orderdate, C4);

            //Order Line
            OrderLine OL1 = O1.addOrderLine(P1, 10);
            OrderLine OL2 = O1.addOrderLine(P2, 4);
            OrderLine OL3 = O2.addOrderLine(P5, 3);
            OrderLine OL4 = O2.addOrderLine(P7, 2);
            OrderLine OL5 = O3.addOrderLine(P9, 1);
            OrderLine OL6 = O4.addOrderLine(P10, 4);
            OrderLine OL7 = O4.addOrderLine(P20, 10);
            OrderLine OL8 = O5.addOrderLine(P17, 11);
            OrderLine OL9 = O5.addOrderLine(P16, 3);
            OrderLine OL10 = O6.addOrderLine(P12, 14);
            OrderLine OL11 = O7.addOrderLine(P11, 2);
            OrderLine OL12 = O7.addOrderLine(P13, 7);
            OrderLine OL13 = O8.addOrderLine(P14, 8);
            OrderLine OL14 = O8.addOrderLine(P4, 3);
            OrderLine OL15 = O8.addOrderLine(P3, 1);
            OrderLine OL16 = O9.addOrderLine(P6, 7);
            OrderLine OL17 = O10.addOrderLine(P19, 12);
            OrderLine OL18 = O10.addOrderLine(P18, 13);

            O1.setEmployee(E1);
            O2.setEmployee(E2);
            O3.setEmployee(E3);
            O4.setEmployee(E3);
            O5.setEmployee(E1);
            O6.setEmployee(E2);
            O7.setEmployee(E1);
            O8.setEmployee(E3);
            O9.setEmployee(E2);
            O10.setEmployee(E2);

            //Pickup
            Pickup PUP1 = O1.setPickup(pickupdate, PaymentMethod.CARD);
            Pickup PUP2 = O2.setPickup(pickupdate, PaymentMethod.CARD);
            Pickup PUP3 = O3.setPickup(pickupdate, PaymentMethod.CARD);
            Pickup PUP4 = O4.setPickup(pickupdate, PaymentMethod.CARD);
            Pickup PUP5 = O5.setPickup(pickupdate, PaymentMethod.CARD);
            Pickup PUP6 = O6.setPickup(pickupdate, PaymentMethod.CARD);
            Pickup PUP7 = O7.setPickup(pickupdate, PaymentMethod.CARD);
            Pickup PUP8 = O8.setPickup(pickupdate, PaymentMethod.CARD);
            Pickup PUP9 = O9.setPickup(pickupdate, PaymentMethod.CARD);
            Pickup PUP10 = O10.setPickup(pickupdate, PaymentMethod.CARD);


            //Objects To Database
            em.persist(C1);
            em.persist(C2);
            em.persist(C3);
            em.persist(C4);
            em.persist(C5);
            em.persist(E1);
            em.persist(E2);
            em.persist(E3);
            em.persist(SC1);
            em.persist(SC2);
            em.persist(SC3);
            em.persist(SC4);
            em.persist(SC5);
            em.persist(SC6);
            em.persist(SC7);
            em.persist(SC8);
            em.persist(SC9);
            em.persist(SC10);
            em.persist(S2);
            em.persist(S3);
            em.persist(S4);
            em.persist(S5);
            em.persist(S6);
            em.persist(S7);
            em.persist(S8);
            em.persist(S9);
            em.persist(S10);
            em.persist(S11);
            em.persist(S12);
            em.persist(S13);
            em.persist(S14);
            em.persist(S15);
            em.persist(S16);
            em.persist(S17);
            em.persist(S18);
            em.persist(S19);
            em.persist(S20);
            em.persist(S21);
            em.persist(S22);
            em.persist(S23);
            em.persist(S24);
            em.persist(S25);
            em.persist(S26);
            em.persist(S27);
            em.persist(S28);
            em.persist(S29);
            em.persist(S30);
            em.persist(S31);
            em.persist(S32);
            em.persist(S33);
            em.persist(S34);
            em.persist(S35);
            em.persist(S36);
            em.persist(S37);
            em.persist(S38);
            em.persist(S39);
            em.persist(S40);
            em.persist(S1);
            em.persist(P1);
            em.persist(P2);
            em.persist(P3);
            em.persist(P4);
            em.persist(P5);
            em.persist(P6);
            em.persist(P7);
            em.persist(P8);
            em.persist(P9);
            em.persist(P10);
            em.persist(P11);
            em.persist(P12);
            em.persist(P13);
            em.persist(P14);
            em.persist(P15);
            em.persist(P16);
            em.persist(P17);
            em.persist(P18);
            em.persist(P19);
            em.persist(P20);
            em.persist(PP1);
            em.persist(PP2);
            em.persist(PP3);
            em.persist(PP4);
            em.persist(PP5);
            em.persist(PP6);
            em.persist(PP7);
            em.persist(PP8);
            em.persist(PP9);
            em.persist(PP10);
            em.persist(PP11);
            em.persist(PP12);
            em.persist(PP13);
            em.persist(PP14);
            em.persist(PP15);
            em.persist(PP16);
            em.persist(PP17);
            em.persist(PP18);
            em.persist(PP19);
            em.persist(PP20);
            em.persist(O1);
            em.persist(O2);
            em.persist(O2);
            em.persist(O3);
            em.persist(O4);
            em.persist(O5);
            em.persist(O6);
            em.persist(O7);
            em.persist(O8);
            em.persist(O9);
            em.persist(O10);
            em.persist(OL1);
            em.persist(OL2);
            em.persist(OL3);
            em.persist(OL4);
            em.persist(OL5);
            em.persist(OL6);
            em.persist(OL7);
            em.persist(OL8);
            em.persist(OL9);
            em.persist(OL10);
            em.persist(OL11);
            em.persist(OL12);
            em.persist(OL13);
            em.persist(OL14);
            em.persist(OL15);
            em.persist(OL16);
            em.persist(OL17);
            em.persist(OL18);
            em.persist(PUP1);
            em.persist(PUP2);
            em.persist(PUP3);
            em.persist(PUP4);
            em.persist(PUP5);
            em.persist(PUP6);
            em.persist(PUP7);
            em.persist(PUP8);
            em.persist(PUP9);
            em.persist(PUP10);

            //Committing
            tx.commit();

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {

            //Closing Entity Manager
            if(em != null){
                em.close();
            }
        }
        //Closing Entity Manager
        em.close();
    }
}
