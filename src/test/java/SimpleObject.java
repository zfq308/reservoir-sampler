/**
 * Created by Engin Yoeyen on 21/02/15.
 */
public class SimpleObject {
    
    private String magicString;
    private Integer magicNumber;
    
    public SimpleObject(String magicString, int magicNumber){
        this.magicString = magicString;
        this.magicNumber = magicNumber;
    }
    
    public String getMagicString() {
        return magicString;
    }

    public void setMagicString(String magicString) {
        this.magicString = magicString;
    }

    public Integer getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public String toString() {
        return "SimpleObject{" +
                "magicString='" + magicString + '\'' +
                ", magicNumber=" + magicNumber +
                '}';
    }
}
