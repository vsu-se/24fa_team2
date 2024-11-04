

public class TestInteractor extends Thread {

    TestModel model;

    TestInteractor(TestModel model){
        this.model = model;
        this.start();
    }

    void addFive(){
        try{
            model.setNumber(Integer.toString(Integer.parseInt(model.getNumber()) + 5));
        } catch (NumberFormatException e){
            model.setNumber("5");
        }
    }

    @Override
    public void run(){
        while (true) { 
            addFive();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
