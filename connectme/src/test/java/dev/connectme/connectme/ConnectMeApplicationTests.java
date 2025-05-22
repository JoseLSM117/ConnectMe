package dev.connectme.connectme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@ActiveProfiles("test")
class ConnectMeApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void testMainMethod() {
		try (var mocked = mockStatic(SpringApplication.class)) {
			ConnectMeApplication.main(new String[]{});

			mocked.verify(() ->
					SpringApplication.run(ConnectMeApplication.class, new String[]{})
			);
		}
	}
}
