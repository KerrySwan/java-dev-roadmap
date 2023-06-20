package set;

import commonObject.EnumDummy;

import java.util.EnumSet;
import java.util.Set;

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


    }

}
