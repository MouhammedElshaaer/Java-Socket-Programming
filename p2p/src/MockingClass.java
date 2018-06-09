import java.io.*;
import java.util.Vector;

/*
* This Mocking class wil be replaced with the Blockchain class.
* Hint: you can use class refactor to rename the class to be
* "Blockchain" and save time and effort for changing the name
* in the other classes, this will leave only the constructors
* to be modified. :")
*/



public class MockingClass implements Serializable{

    public String message;
    public String displayName;

    MockingClass(){
        this.message="This data is from";
        this.displayName="Elsaaer";
    }
    MockingClass(String _message, String _displayName){
        this.message=_message;
        this.displayName=_displayName;
    }
}
