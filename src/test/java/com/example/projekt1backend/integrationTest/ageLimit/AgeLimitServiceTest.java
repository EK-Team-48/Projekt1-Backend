package com.example.projekt1backend.integrationTest.ageLimit;

import com.example.projekt1backend.ageLimit.entity.AgeLimit;
import com.example.projekt1backend.ageLimit.service.AgeLimitService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:data.sql"}
)
@Transactional
@Rollback(true)
class AgeLimitServiceTest {

    @Autowired
    private AgeLimitService ageLimitService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllTest() {
        List<AgeLimit> allAgeLimits = ageLimitService.findAll();
        assertNotNull(allAgeLimits);

        // Der er 4 ageLimits i h2-databasen.
        assertTrue(allAgeLimits.size() >= 4);

        // Undersøger om der er en ageLimit der er 15 i h2-databasen.
        assertTrue(allAgeLimits.stream().anyMatch(al -> al.getAgeRating() != null && al.getAgeRating() == 15));
    }

    @Test
    void findByIdTest() {
        Optional<AgeLimit> al1 = ageLimitService.findById(1);
        assertTrue(al1.isPresent());
        assertEquals(0, al1.get().getAgeRating());
    }

    @Test
    void postAgeLimitTest() {
        int before = ageLimitService.findAll().size();

        AgeLimit newAgeLimit = new AgeLimit(25);

        AgeLimit saveTest = ageLimitService.save(newAgeLimit);
        assertNotNull(saveTest.getAgeRating());

        Optional<AgeLimit> found = ageLimitService.findById(saveTest.getAgeLimitId());
        assertTrue(found.isPresent());
        assertEquals(25, found.get().getAgeRating());

        List<AgeLimit> after = ageLimitService.findAll();
        assertEquals(before + 1, after.size());
    }

    @Test
    void deleteAgeLimitTest() {
        AgeLimit deleteTest = new AgeLimit(25);
        ageLimitService.save(deleteTest);

        Integer id = deleteTest.getAgeLimitId();
        ageLimitService.deleteById(id);

        Optional<AgeLimit> after = ageLimitService.findById(id);
        assertTrue(after.isEmpty());
    }
}