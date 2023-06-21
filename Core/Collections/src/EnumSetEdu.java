import commonObject.ComparableDummy;
import commonObject.EnumDummy;
import commonObject.HashDummy;

import java.util.*;

public class EnumSetEdu {

    public static void main(String[] args) {

        //EnumSet
            //Объявление EnumSet
            Set<EnumDummy> enumSet =
                    EnumSet.of(
                            EnumDummy.ONE,
                            EnumDummy.TWO,
                            EnumDummy.THREE,
                            EnumDummy.ONE
                    );

            enumSet.forEach(
                    System.out::println
            );


        //HashSet
        Set<HashDummy> hashSet = new HashSet<>();

        //LinkedHashSet
        Set<HashDummy> linkedHashSet = new LinkedHashSet<>();

        //TreeSet
        NavigableSet<ComparableDummy> treeSet = new TreeSet<>();

        //todo: Дополнить операциями с наборами. Дополнить многопоточными реализациями.

    }

}
