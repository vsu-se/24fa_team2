import javafx.scene.layout.Region;

public class TestController{
    
    private final Region view;

    public TestController(){
        TestModel testModel = new TestModel();
        TestInteractor interactor = new TestInteractor(testModel);
        view = new TestView(testModel, interactor::addFive);
    }

    public Region getView(){
        return view;
    }
}
