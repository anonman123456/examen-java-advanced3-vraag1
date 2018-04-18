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

		// zit vraag al in database? geef bestaande antwoord terug
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
            //System.out.println("Nieuwe vraag: ");
            // random vraag selecteren
            answer = selectRandomAndDistributedAnswer(questions);
            questionRepository.save(new Question(question, answer));
        }
        return answer;
	}

	private String selectRandomAndDistributedAnswer(Iterable<Question> questions){
        ArrayList<String> foundAnswers = new ArrayList<>();
        ArrayList<String> possibleAnswers = new ArrayList<>();

        // welke antwoorden komen voor in database????
        for (Question question: questions) {
            foundAnswers.add(question.getAnswer());
        }
        //System.out.println("--------------------");
        //System.out.println("Antwoorden in database: ");
        //for(String foundAnswer : foundAnswers){
        //    System.out.println(foundAnswer);
        //}
        // welke antwoorden schieten nog over om uit te kiezen
        for(String possibleAnswer : getAnswers()){
            if(!foundAnswers.contains(possibleAnswer)){
                possibleAnswers.add(possibleAnswer);
            }
        }
        //System.out.println("--------------------");
        //System.out.println("Antwoorden om uit te kiezen: ");
        //for(String possibleAnswer : possibleAnswers){
        //    System.out.println(possibleAnswer);
        //}
        // kies random antwoord uit overblijvenden
        // indien geen overblijven kies uit oorspronkelijke lijst
        // indien wel overblijven enkel uit overblijvenden
        if(possibleAnswers.size() == 0){
            return getAnswers()[(generator.nextInt(getAnswers().length))];
        } else {
            return possibleAnswers.get(generator.nextInt(possibleAnswers.size()));
        }
    }

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
