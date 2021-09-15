package nl.han.ica.oose.dea.resources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCheckResourceTest {

    @Test
    public void testHealthy() {
        // Arrange
        var su = new HealthCheckResource();

        // Act
        var output = su.healthy();

        // Assert
        assertEquals("Up & Running", output);
    }

}