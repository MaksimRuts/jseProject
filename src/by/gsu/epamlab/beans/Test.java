package by.gsu.epamlab.beans;

public class Test {
    private int testId;
    private String name;

    public Test() {
    }

    public Test(String name) {
        this(0, name);
    }


    public Test(int testId, String name) {
        this.testId = testId;
        this.name = name;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
