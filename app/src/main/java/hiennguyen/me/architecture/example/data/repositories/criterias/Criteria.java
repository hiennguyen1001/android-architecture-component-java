package hiennguyen.me.architecture.example.data.repositories.criterias;

public class Criteria {
    private int greaterThan;
    private int lessThan;
    private int equal;
    private int min;
    private int max;

    public int getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(int greaterThan) {
        this.greaterThan = greaterThan;
    }

    public int getLessThan() {
        return lessThan;
    }

    public void setLessThan(int lessThan) {
        this.lessThan = lessThan;
    }

    public int getEqual() {
        return equal;
    }

    public void setEqual(int equal) {
        this.equal = equal;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
