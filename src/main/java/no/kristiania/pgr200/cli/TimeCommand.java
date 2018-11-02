package no.kristiania.pgr200.cli;

import no.kristiania.pgr200.utils.DateHandler;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TimeCommand extends Command<String> {

    public TimeCommand(String name, String description, String type, String mode, String table, String subQuestionValue, String[] subQuestionName) {
        super(name, description, type, mode, table, subQuestionValue, subQuestionName);
    }

    public TimeCommand() {
    }

    @Override
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(String value) throws DateTimeParseException {
        super.setValue(handleDate(value));
    }

    String handleDate(String time) throws DateTimeParseException {
        LocalTime.parse(time, DateHandler.TimeFormatter);
        return time;
    }

    @Override
    String handleValue(String value) throws IllegalArgumentException{
        if(value != null && value instanceof String && !value.isEmpty()){
            return value;
        }
        else{
            throw new IllegalArgumentException("Invalid Input");
        }
    }
}
