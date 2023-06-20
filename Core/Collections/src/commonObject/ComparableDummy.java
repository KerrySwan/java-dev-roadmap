package commonObject;

public class ComparableDummy implements Comparable<ComparableDummy> {

    int a;
    int b;

    public ComparableDummy(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(ComparableDummy o) {
        //для наглядности без Integer.compare()
        if ((this.a+this.b) < (o.a + o.b)) return -1;
        if ((this.a+this.b) == (o.a + o.b)) return 0;
        else return 1;
    }
}
