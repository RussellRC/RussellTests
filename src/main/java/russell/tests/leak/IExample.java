package russell.tests.leak;

public interface IExample {

    int counter();

    ILeak leak();

    IExample copy(IExample example);

    int plusPlus();

    String message();

    
    
}
