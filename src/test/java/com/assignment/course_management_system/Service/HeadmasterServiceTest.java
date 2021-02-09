package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.Entity.Headmaster;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeadmasterServiceTest {

    @Test
    void getAllHeadmasters() {

        List<Headmaster> testHeadmasters = Arrays.asList(
                new Headmaster(1, "Steven", "Stone", "Lavender", "0776767676", 8),
                new Headmaster(2, "Brock", "Peters", "Pewter", "0777345678", 3));

        Assertions.assertThat(testHeadmasters).contains(testHeadmasters.get(0), testHeadmasters.get(1));
    }

    @Test
    void registerHeadmaster() {
        Headmaster headmaster = new Headmaster();
        headmaster.setHeadmasterID(1);
        headmaster.setHeadmasterFirstName("Steven");
        headmaster.setHeadmasterLastName("Stone");
        headmaster.setHeadmasterAddress("Lavender");
        headmaster.setHeadmasterPhoneNumber("0776767676");
        headmaster.setUserID(3);

        assertEquals(1, headmaster.getHeadmasterID());
        assertEquals("Steven", headmaster.getHeadmasterFirstName());
        assertEquals("Stone", headmaster.getHeadmasterLastName());
        assertEquals("Lavender", headmaster.getHeadmasterAddress());
        assertEquals("0776767676", headmaster.getHeadmasterPhoneNumber());
        assertEquals(3, headmaster.getUserID());
    }
}