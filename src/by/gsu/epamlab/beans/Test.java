package by.gsu.epamlab.beans;

public class Test {
    private int testId;
    private String test;

    public Test() {
    }

    public Test(int testId, String test) {
        this.testId = testId;
        this.test = test;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return test;
    }
}
