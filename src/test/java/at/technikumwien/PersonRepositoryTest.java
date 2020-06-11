package at.technikumwien;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

// integration test

@SpringBootTest
@Transactional   // rollbacks transaction after each test method
@ActiveProfiles("test")
@Tag("extended")
public class PersonRepositoryTest {
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void testSave() {
		var person = personRepository.save(
			new Person(Sex.MALE, "Maxi", "Musterkind", LocalDate.of(2010, 12, 24), true)
		);
		
		assertNotNull(person.getId());
		assertEquals(3, personRepository.count());
	}
	
	@Test
	public void testFindAllByActiveTrue() {
		var persons = personRepository.findAllByActiveTrue();
		
		assertEquals(1, persons.size());
	}
	
	// TODO add more tests here ;-)
}