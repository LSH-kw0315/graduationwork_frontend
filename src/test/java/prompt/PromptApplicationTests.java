package prompt;

import com.prompt.PromptApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(classes = PromptApplication.class)
class PromptApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	@DisplayName("RESTful API 테스트")
	void RestApiTest(){
		WebClient webClient = WebClient.create("http://121.174.90.142:18649");

		Answer answer = webClient.get()
			.uri("/restApi/autogenerator/developer")
			.retrieve()
			.bodyToMono(Answer.class)
			.block();

		System.out.println(answer.query);
		System.out.println(answer.answer);
	}

	static class Answer {
		String query;
		String answer;
	}

}
