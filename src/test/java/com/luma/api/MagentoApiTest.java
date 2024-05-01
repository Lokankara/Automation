package com.luma.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luma.model.User;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.IOException;

import static org.testng.Assert.*;

public class MagentoApiTest {

    @Ignore
    @Test
    public void testGetCustomerInfo() throws IOException {
        MagentoApi magentoApi = MagentoApi.getInstance();
        try {
            String response = magentoApi.getCustomerInfo();

            Assert.assertNotNull(response, "Response should not be null");

            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(response, User.class);

            assertEquals(user.getId(), 140620);
            assertEquals(user.getGroupId(), 1);
            assertEquals(user.getCreatedAt(), "2024-04-14 09:30:26");
            assertEquals(user.getCreatedIn(), "Default Store View");
            assertEquals(user.getEmail(), "tester1234@gmail.com");
            assertEquals(user.getFirstname(), "Tester");
            assertEquals(user.getLastname(), "Tester");
            assertEquals(user.getStoreId(), 1);
            assertEquals(user.getWebsiteId(), 1);
            assertTrue(user.getAddresses().isEmpty());
            assertEquals(user.getDisableAutoGroupChange(), 0);
            assertFalse(user.getExtensionAttributes().isSubscribed());
        } catch (IOException e) {
            Assert.fail("Failed to get customer info due to an exception: " + e.getMessage());
        }
    }
}
