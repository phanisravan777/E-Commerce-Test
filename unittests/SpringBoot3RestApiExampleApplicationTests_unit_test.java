import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SpringBoot3RestApiExampleApplicationTests {

	@Autowired
	private SpringBoot3RestApiExampleApplication app;

	@Test
	public void contextLoads() {
		assertThat(app).isNotNull();
	}

	@Test
	public void main() {
		SpringBoot3RestApiExampleApplication.main(new String[] {});
		assertThat(app).isNotNull();
	}
}