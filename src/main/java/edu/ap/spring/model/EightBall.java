package edu.ap.spring.model;
import edu.ap.spring.jpa.Question;
import edu.ap.spring.jpa.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class EightBall {

	@Autowired
	QuestionRepository questionRepository;
	private Random generator = new Random();
	private String[] answers = {"It is certain", 
								"Yes definitely", 
								"Most likely",
								"Outlook good",
								"Better not tell you now",
								"Cannot predict now",
								"Don't count on it", 
								"Outlook not so good"};
	
	public String getRandomAnswer(String question) {
		String answer = "";

		// if question exists geef bestaande antwoord terugµ
        boolean exists = false;
        Iterable<Question> questions = questionRepository.findAll();
		for(Question q : questions){
			if(q.getQuestion().equals(question)){
				exists = true;
				answer = q.getAnswer();
			}
		}

		// vraag bestaat al of niet
        if(!exists){
            System.out.println("Doesn't exists");
            // random vraag selecteren
            answer = selectRandomAndDistributedAnswer(questions);
            questionRepository.save(new Question(question, answer));
        } else {
            System.out.println("Exists");
        }
        return answer;
	}

	private String selectRandomAndDistributedAnswer(Iterable<Question> questions){
        String answer = answers[generator.nextInt(answers.length)];
        ArrayList<String> foundAnswers = new ArrayList<>();
        // welke vragen komen niet voor in database????
        for (Question question: questions) {
            foundAnswers.add(question.getAnswer());
        }
    }

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
