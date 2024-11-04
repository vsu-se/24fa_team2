import javafx.beans.property.*;

public class TestModel{
    
    private final StringProperty number = new SimpleStringProperty("0");

    String getNumber(){
        return number.get();
    }

    StringProperty numberProperty(){
        return number;
    }

    void setNumber(String number){
        this.number.set(number);
    }
}
