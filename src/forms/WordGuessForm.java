package forms;

public class WordGuessForm {

    private String guessedWord;

    public WordGuessForm(String guessedWord) {
        this.guessedWord = guessedWord;
    }

    // Отримати слово, введене користувачем
    public String getGuessedWord() {
        return guessedWord;
    }

    // Встановити слово, введене користувачем
    public void setGuessedWord(String guessedWord) {
        this.guessedWord = guessedWord;
    }
}
