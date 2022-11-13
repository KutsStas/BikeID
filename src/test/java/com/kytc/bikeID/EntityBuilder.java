package com.kytc.bikeID;

import com.kytc.bikeID.entity.Bike;
import com.kytc.bikeID.entity.User;
import com.kytc.bikeID.entity.Workshop;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class EntityBuilder {

    public static User buildUser() {

        User user = new User();
        user.setId(RandomUtils.nextInt());
        user.setPassword(RandomStringUtils.random(7));
        user.setEmail(RandomStringUtils.random(5) + "@mail.com");
        return user;
    }

    public static Bike buildBike() {

        Bike bike = new Bike();
        bike.setId(RandomUtils.nextInt());
        bike.setBikeBrand(RandomStringUtils.random(5));
        bike.setBikeModel(RandomStringUtils.random(6));
        bike.setBikeColor(RandomStringUtils.random(7));
        bike.setBikeType(RandomStringUtils.random(8));
        return bike;

    }

    public static Workshop buildWorkshop() {

        Workshop workshop = new Workshop();
        workshop.setId(RandomUtils.nextInt());
        workshop.setWorkshopPhoneNumber(RandomUtils.nextInt());
        workshop.setWorkshopName(RandomStringUtils.random(5));

        return workshop;
    }

    public static List<User> buildUserList() {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            users.add(buildUser());
        }
        return users;
    }

    public static List<Bike> buildBikeList() {

        List<Bike> bikes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bikes.add(buildBike());
        }
        return bikes;
    }
    public static List<Workshop> buildWorkshopList(){
        List<Workshop> workshops = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            workshops.add(buildWorkshop());
        }
        return workshops;
    }


}