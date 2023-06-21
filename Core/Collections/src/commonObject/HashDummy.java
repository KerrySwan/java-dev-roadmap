package commonObject;

import java.util.Objects;

public class HashDummy {

    int a;
    int b;

    public HashDummy(int a, int b) {
        this.a = a;
        this.b = b;
    }

    //автогенерация
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashDummy hashDummy = (HashDummy) o;
        return a == hashDummy.a && b == hashDummy.b;
    }

    //автогенерация
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
