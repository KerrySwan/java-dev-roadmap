# Collection Framework

## Обзор:
#### Зачем нужен Collection Framework:

1. *Сокращение объёма/усилия разработки* через имплементацию решений готовых структур и алгоритмов
2. *Улучшает производительность* через имплементацию готовых хай-перформанс алгоритмов и структур
3. *Улучает взаимодействие несвязанных API* через общие коллекции
4. *Сокращает усилия по разработке и внедрения АПИ* - пропадает необходимость создания структур для конкретного случая
5. *Способствует переиспользовыванию кода* через готовые интерфейсы структур данных

### Collection Framework состоит из:

1. *Интерфейсы коллекций*
2. *Имплементации общего порядка (основных)* - сеты, списки и т.д.
3. *Устаревшие имплементаций* - Vector и Hashtable
4. *Имплементации особого порядка* имплементации для особых ситуаций, со специфическими характеристиками
5. *Многопоточные имплементации*
6. *Имплементации-обёртки* обычно расширяют функциональность существующих имплементации
7. *Выгодные имплементации* маленькие высокопроизводительные имплементации
   (не уверен на счет перевода Convenience implementations)
8. *Абстрактные имплементации* частичные имплементации интерфейсов коллекций
9. *Алгоритмы* сортировки и прочее
10. *Инфраструктурные интерфейсы*
11. *Утиллиты массивов*

### Интерфейсы коллекций:
Находятся в java.util

1. Set - набор
2. SortedSet - сортированный набор, необходим Comparator или имплементация Comparable
3. NavigableSet - сортированный список дополненный методами навигации
4. Queue - очередь
5. concurrent.BlockingQueue - очередь расширенная методами *ожидания при получении элемента, если очередь пуста* и *ожидания свободного места при добавлении элемента.*
6. concurrent.TransferQueue - очередь в которой происходит ожидание получения-извлечения элемента. Одновременно вставляется и извлекается один элемент.
7. Deque - дэк
8. concurrent.BlockingDeque - Дэк расширенный методами *ожидания при получении элемента, если очередь пуста* и *ожидания свободного места при добавлении элемента.*
9. List - его кстати нет на оф страничке

### Интерфейсы _**НЕ**_ коллекций:

1. Map
2. SortedMap - сортирована таблица соотношений, необходим Comparator или имплементация Comparable
3. NavigableMap - расширение SortedMap с методами навигации
4. ConcurrentMap - получение и удаление элементов реализовано в соответствии с приоритетом потоков, обращающихся к таблице. Гарантируется атомарносить и потокобезопастность операций.
5. NavigableConcurrentMap - смесь пункта 2 и 3

### Дополнительные сведения:

1. Многие методы расширенных интерфейсов помечены опциональными.
2. Коллекции, которые не поддерживают операции модификации, помечены как *не модифицируемые* (не поддерживают remove и clear)
3. Коллекции, которые дополнительно гарантируют, что никакие изменения не будут видимы, помечены как *неизменяемые*
4. Списки, которые гарантируют, что их размер останется константой, даже если их элементы изменяется, называются *списками фиксированного размера*
5. Списки, которые гарантируют доступ к элементам по индексу за постоянное время, называются *списками случайного доступа.* Списки, которые не гарантируют такой доступ, называются 
*списками последовательного доступа.* Интерфейс RandomAccess помечает списки, которые поддерживают случайный доступ

Некоторые имплементации ограничивают какие элементы могут быть в них храниться.

#### Возможные ограничения:

1. Быть определенного типа
2. Не быть null
3. Подчиняться какому либо правилу/предикату.

#### Некоторые имплементации коллекций:


| Interface | Hash Table | Resizable Array | Balanced Tree | Linked List | Hash Table + Linked List |
|:----------| :--------: | :------------: |:-------------:| :--------: |-------------------------:|
| Set       |  Hash Set  |                |    TreeSet    |            |            LinkedHashSet |
| List      |            |    ArrayList   |               | LinkedList |                          |
| Deque     |            |   ArrayDeque   |               | LinkedList |                          |
| Map       |  Hash Map  |                |    TreeMap    |            |            LinkedHashMap |

Все имплементации общего порядка поддерживают все опциональные методы и не имеют ограничений на элементы, которые они могут содержать. 
Все они несинхронизированный, но все коллекции содержат статические фабрики называемые *синхронизированные обертки*, 
которые могут быть использованные, что бы добавить синхронизацию, ко многим несинхронизированным коллекциям. 
Все новые имплементации имеют fail-fast итераторы, которые обнаруживают одновременные/многопоточные изменения, и быстро прекращают свою работу.

### Многопоточные коллекции:
Не связанны с "синхронизированными обёртками"

#### Интерфейсы:

1. BlockingQueue
2. TransferQueue
3. BlockingDeque
4. ConcurrentMap
5. ConcurrentNavigableMap

#### Имплементации:

1. LinkedBlockingQueue
2. ArrayBlockingQueue
3. PriorityBlockingQueue
4. DelayQueue
5. SynchronousQueue
6. LinkedBlockingDeque
7. LinkedTransferQueue
8. CopyOnWriteArrayList
9. CopyOnWriteArraySet
10. ConcurrentSkipListSet
11. ConcurrentHashMap
12. ConcurrentSkipListMap

### Цели разработки:
Основной целью является создание API небольшого размера (дальше указан термин "conceptual weight", предполагаю, имелось ввиду размер и сложность кода).
Было критично то, что бы новый функционал не казался слишком сложным для уже практикующих программистов Java;
функционал должен был расширять текущие возможности, нежели заменять их. В то же время, новый API должен был быть достаточно мощным, что бы обеспечить все преимущества описанные ранее.
Для поддержания маленького количества интерфейсов, они не предполагали охватить такие мелкие отличия как изменяемость, модифицируем ость и изменение размера. 
Наоборот, такой функционал в основных интерфейсах помечен как опциональный, позволяющий имплементациям выбрасывать UnsupportedOperationException для индикации того, что объявленные операции не поддерживаются.

### Дополнительно:
Это должно было быть в начале, но на официальном ресурсе этого нет.
Все коллекции и не-коллекции являются имплементация ми двух интерфейсов -

1. Iterable
2. Map
   Подинтерфейсом Iterable является Collection.
   То есть имеем такую иерархию зависимостей, в соответствии с javadoc

```
   Iterable <--- Collection <--- List  <--- ...
                            <--- Set   <--- ...
                            <--- Queue <--- Deque <--- ...
                                       <--- ... 

        Map <--- SortedMap  <--- NavigableMap <--- ...
                            <--- ConcurentNavigableMap <--- ...
```

## Практика и javadoc:
Постараюсь здесь описать основные реализации классов в формате спуска по иерархии.

## Set:

Примеры в файле SetsEdu.java

#### Содержит два под интерфейса:

1. SortedSet
2. NavigableSet
   NavigableSet является потомком SortedSet

#### Реализации "чистого" set:

1. EnumSet
2. HashSet
3. LinkedHashSet

Реализации SortedSet и NavigableSet:

1. TreeSet
2. ConcurrentSkipListSet

## Set

#### EnumSet:
Документация описывает как набор, который позволяет сохранять Enum'ы.
Является типобезопасной альтернативой традиционным "битовым флагам".
Операции с множеством значений (removeAll, containsAll) выполняются за константное время.
Не поддерживает __вставку__ null.
Не синхронизирован. Для синхронизации должен быть обёрнут так
`Collections.synchronizedSet(EnumSet.noneOf(MyEnum.class))`

EnumSet абстрактен. Объявляется он через
`EnumSet.of(...)`

[Битовые флаги](https://ru.wikipedia.org/wiki/%D0%A4%D0%BB%D0%B0%D0%B3_(%D0%BA%D0%BE%D0%BC%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80%D0%BD%D0%B0%D1%8F_%D1%82%D0%B5%D1%85%D0%BD%D0%B8%D0%BA%D0%B0))

#### HashSet
Реализация интерфейса Set. На самом деле происходит вызов инстанса HashMap.
Соответственно можно сделать вывод, что для корректной работы нужна перезапись методов
hashCode и equals. Вывод элементов не гарантирован в порядке добавления.

<p>За константное время производит базовые операции add, remove, contains и size в случае
если распределение элементов хеш-функцией, между баккитов HashMap, происходит правильно.<p>

Синхронизация: `Set s = Collections.synchronizedSet(new HashSet(...));`

Поддерживает вставку одного null.

`(На самом деле, до настоящего момента, я никогда не проверял как происходит инициализация такого набора. Но это очень забавно. Там буквально написано new HashMap.)`

#### LinkedHashSet
Реализация Set с поддержкой linkedList и HashMap. Происходит инициализация LinkedHashMap.
Реализация линковки элементов - двухстороняя. Гарантирует вывод в порядке добавления. Добавление элемента не поменяет его позицию при выводе,
в случае если элемент уже находится в наборе.<p>

Синхронизация ```Set s = Collections.synchronizedSet(new LinkedHashSet(...));```

#### TreeSet
Является имплементацией NavigableSet. Содержит инстанс TreeMap (это уже перестало меня смешить).
Элементы которые помещаются в TreeSet должны либо наследовать [Comparable](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html),
либо в сам сет должен быть передан [Comporator](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html).
Для базовых операций add, remove, contains гарантированное время исполнения log(n).
Так же надо учитывать, что правильная имплементация Set гарантирована только в случае,
если equals и compareTo/compare при равенстве элементов работают согласованно. Без согласованности, сет будет работать
но будет нарушен главные контракт с Set.<p>

Синхронизация:`SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));`

Под капотом [красно-черное дерево](https://ru.wikipedia.org/wiki/%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE-%D1%87%D1%91%D1%80%D0%BD%D0%BE%D0%B5_%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%BE).

#### CopyOnWriteArraySet
Имплементация Set. Использует реализацию CopyOnWriteArrayList.<p>
1. Лучший выбор для приложений, которые не наращивают объемы данных и операции
   чтения сильно превышают операции изменения.
2. Потокобезопасная реализация
3. Операции изменения очень затратны, т.к копируют весь набор данных
4. Итераторы не поддерживают операции изменения
5. Итераторы быстры и не могут быть затронуты другими потоками, т.к. они
   зависят от отдельных снапшотов данных, сделанных во время вызова итератора.


#### ConcurrentSkipListSet
Имплементация NavigableSet. Базовые операции выполняются за log(n), аналогично TreeSet. Операции вставки, удаления и получения
выполняются потокобезопасно. Итераторы и сплитераторы слабосогласованы.
Это значит:
1. Они могут работать одновременно с другими операциями
2. Не выбросят ConcurrentModificationException, в отличии от fail-fast
3. Итератор проходится по элементам, какие они были при его создании, единожды.
   Однако, может отображать изменения, произошедшие после его создания (не гарантированно).

Операция size() не выполняется за постоянное время из-за многопоточной природы коллекции.
У операций с множеством элементов, формата .__All(), не гарантируется атомарность по тем же причинам. 

## Queue

Имеет 8 имплементаций.


#### DelayQueue
Не ограниченная по размеру очередь, состоящая из элементов, имлементирущих интерфейс Delayed.
[Delayed](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Delayed.html) - интерфейс наследующий Comparable<Delayed>. Содержит метод getDelay, который принимает в себя время по ожиданию действия
над элементом. В случае с очередью это время по ожиданию доступа к элементу.
Так же надо имлементировать метод compareTo поскольку объекты внутри очереди будут ближе к концу очереди в случае если их

#### ConcurrentLinkedQueue
Не ограниченная по размеру потокобезопасная очередь основанная на связанных узлах.
Не допускает использование null-элементов.
Эта очередь использует эффективный не блокирующий алгоритм описанный в работе 
["Simple, Fast, and Practical Non-Blocking and Blocking Concurrent Queue Algorithms"](https://www.cs.rochester.edu/~scott/papers/1996_PODC_queues.pdf)
написанной авторами _Maged M. Michael_ и _Michael L. Scott_
Итераторы слабосогласованны, и возвращают элементы очереди в какой-то отдельный момент с момента создания итератора. 
Не выбрасывает ConcurrentModificationException.
<br>size() не является операцией выполняющейся за постоянное время из-за асинхронности и может выполняться ошибочно.
У операции с объемом элементов addAll, removeAll, retainAll, containsAll, equals, и toArray не гарантированна атомарность.
Чтение и запись элементов происходит по принципу [happen-before](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html#MemoryVisibility)
, то есть, если происходит запись элемента одним потоком, то второй поток, обращающийся к этой коллекции, увидит этот элемент
только в том случае, если операция записи полностью завершилась на момент чтения этой коллекции вторым потоком.

#### LinkedBlockingQueue
Опционально ограниченная по размеру блокирующая очередь, основанная на связанных узлах.
Связанные очереди обычно имеют большую пропускную способность, чем очереди основанные на массивах, однако менее предсказуемы
в плане производительности в многопоточных приложениях. Опциональный конструктор, который содержит ограничение по объему, нужен для того,
что бы избежать ненужного расширения. Если ограничение не объявлено - используется Integer.MAX_VALUE.
Связанные узлы создаются динамически.
Большая часть операций вполняются за константное время, исключая remove(), removeFirstOccurrence(), removeLastOccurrence(), contains(),
iterator.remove() и операции с массивом элементов, которые выполняются за линейное время.

#### ArrayBlockingQueue
Ограниченная по размеру блокирующая очередь представленная массивом. Порядок доступа к элементам FIFO.
Однажды созданная по заданому размеру, более не возможна к его изменению. Операции добавления в полную очередь приведут к блокированию
этой операции. Аналогично с извлечением из пустой. Поддерживает опциональную политику очередности для получения элементов из очереди.
По дефолту, очередность не гарантированна. Многопоточная реализация.

#### LinkedTransferQueue
Неограниченная очередь, имплементирует TransferQueue. Метод size() не выполняется за константное время из-ха асинхронной природы 
коллекции. У методы addAll, removeAll, retainAll, containsAll, equals, и toArray не гарантированна атомарность.
Отличатся тем, что потоки с операциями вставки блокируются до тех пор, пока другой поток не извлечет элемент. Такой подход 
используется во всех очередях наследующихся от TransferQueue.
При вызове метода transfer() потребителем, блокирует операции вставки, если это необходимо.

#### PriorityQueue
Неограниченная блокирующая очередь, которая использует базирующаяся на _[приоритизированной куче](https://www.geeksforgeeks.org/priority-queue-using-binary-heap/)_
В нашем случае должен либо передаваться объект Comparable либо Comparator.
Приоритизированная куча имеет следующие свойства:
1. У каждого объекта есть свойство приоритета - в нашем случае сравнение приоритета происходит через известные интерфейсы
2. Элементы с меньшим приоритетом располагаются позже в очереди - compare/compareTo == -1
3. Если два элемента равны по приоритету, то они располагаются в порядке попадания в очередь - compare/compareTo == 0

Такая очередь не может содержать null элементы.
*Эта имплементация не синхронизована*
Операции извлечения, удаления, получения, и добавления выполняются за O(log(n))
contains() и remove() выполняются за линейное время

#### PriorityBlockingQueue
Аналогична PriorityQueue, однако поддерживает блокировку на получение и вставку элементов.
Является импелемнтацией интерфейса BlockingQueue

#### SynchronousQueue
Синхронизированная очередь - разновидность блокирующей очереди, в которой операции вставки и удаления ожидают друг-друга и наоборот.
Не содержит никакого внутреннего объема и размера. Невозможно выполнить peek() поскольку элементы присутствуют только при
их удалении. Невозможно совершить вставку во время того, как другой поток пытается удалить элемент из очереди. Невозможно итерировать такую
очередь, если в ней нечего итерировать. Заглавным(Head) элементом такой очереди является элемент, поток которого первым пытается его добавить в 
эту очередь. Если такого потока нет, то poll() вернет null. Для других методов интерфейса Collection, например contains(), SynchronousQueue
ведет себя как пустая коллекция. Не принимает null элементы.
<br>Такие очереди подходят для передачи данных, в ходе которых объект из одного потока должен синхронизироваться, с объектом
из другого потока.
Во время вставки в эту очередь, он блокируется до извлечения элемента,
потому объема у нее нет. Служить буквально для передачи объектов.

## Dequeu

#### ArrayDeque
Неограниченный по размеру дэк. Не потокобезопасен. Null-объекты запрещены. Документация говорит, что такой дэк
показывает более значительную производительность, чем стек, когда используется как стек и более значительную производительность 
чем связанный, когда используется как очередь.
Большая часть операций выполняются за [амортизированное](https://www.waitingforcode.com/programming/amortized-effective-constant-time/read) константное время, исключая remove(), removeFirstOccurrence(), removeLastOccurrence(), contains(),
iterator.remove() и операции с массивом элементов, которые выполняются за линейное время.
Итераторы - fail-fast

#### ConcurrentLinkedDeque
Неограниченный по размеру дэк, основанный на связанных узлах.
Потокобезопасен.
Итераторы слабосогласованы.
size() не выполняется за константное время. У операции с массивом элементов не гарантируется атомарность.
Чтение и запись элементов происходит по принципу [happen-before](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html#MemoryVisibility)
, то есть, если происходит запись элемента одним потоком, то второй поток, обращающийся к этой коллекции, увидит этот элемент 
только в том случае, если операция записи полностью завершилась на момент чтения этой коллекции вторым потоком. 

#### LinkedBlockingDeque
Опционально ограниченный по размеру дэк, максимальный размер, если не указан, принимает значение Integer.MAX_VALUE.
Большая часть операций выполняются за константное время, исключая remove(), removeFirstOccurrence(), removeLastOccurrence(), contains(),
iterator.remove() и операции с массивом элементов, которые выполняются за линейное время.

## List

#### ArrayList
Не ограниченный поо размеру список. Реализует все опциональные методы. Позволяет вставку null.
<br>size(), isEmpty(), get(), set(), iterator() и listIterator() выполняются за константное время.
Вставка выполняется за амортизированное константное время. Вставка n элементов соотвественно выполняется за O(n).
Все остальные операции, грубо говоря выполняются за линейное время.
Любой инстанс ArrayList имеет объем. Растет автоматически, с увеличением числа элементов. Детали политики роста не обозначены
в документации. Известно, что рост происходит за постоянное амортизированное время.
Для того, что бы избежать частый пересчет размера, можно вызвать метод ensureCapasity() программно.
Имплементация не синхронизованная, что бы синхронизовать - Collections.synchronizedList(new ArrayList(...)).
iterator и listIterator - fail-fast

#### CopyOnWriteList
Потокобезопасный вариант ArrayList(). Копирует сам себя при каждой вставке. 
Итераторы работают как снапшоты. Не согласованы.
Чтение и запись элементов происходит по принципу [happen-before](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html#MemoryVisibility)
, то есть, если происходит запись элемента одним потоком, то второй поток, обращающийся к этой коллекции, увидит этот элемент
только в том случае, если операция записи полностью завершилась на момент чтения этой коллекции вторым потоком.
Возможна вставка null

#### LinkedList
Не ограничен в размере.
Ноды в данном списке связаны в обе стороны.
Может включать null.
Не синхронизован, синхронизируется через Collections.synchronizedList(new LinkedList(...)).
Итераторы - fail-fast.
Операции который работают с индексами будут перебирать элементы, до тех пор пока не найдут этот индекс. С первого или с последнего элемента 
будет начинаться перебор, будет зависеть от самого индекса. Если ближе к началу, то с начала и наоборот.

#### Vector
Это неограниченный по размеру список.
Является легаси-классом.
Очень схож с ArrayList за исключением:
1. В отличие от ArrayList синхронизирован сразу
2. Увеличивает свой размер в два раза, вместо 0.5 когда требуется увеличение хранилища
3. Работает медленнее чем ArrayList, т.к. удерживает потоки обращающиеся к объектам, до тех пор пока текущий поток не 
разблокирует объект с которым работает.
4. Для перебора элементов может использоваться не только Iterator, но и Enumeration.

#### Stack
Наследник вектора, дополненный пятью методами. Классический стэк LIFO. Легаси-класс.

## Map

#### Hashtable
Легаси класс.
Хэш-таблица которая не поддерживает любые not-null объекты как ключи и значения.
У Hashtable есть два параметра которые отвечают за её производительность - initial capacity и load factor.
Initial capacity - параметр отвечающий за объем с которым хэш-таблица будет инициализирована.
Load factor - параметр отвечает за то, какой объем баккитов будет занят, перед тем как объем хэш таблицы будет увеличен
и сама таблица будет рехэширована (баккиты займут новые места во внутреннем хранилище).
Дефолтное значение load factor = 0.75.
Для успешного помещения объекта в таблицу, он должен перезаписывать методы hashCode() и equals().
[Почему?](https://www.geeksforgeeks.org/equals-hashcode-methods-java/)
Способ разрешения коллизий - "открытый". Если хэш-функция генерирует одно и тоже значение для двух разных ключей, баккит будет
включать в себя несколько вхождений ключ-значение (Баккит будет хранить связанный список).
Все современные реализации Map имеют аналогичные свойства, которые перечислены выше.

#### EnumMap
Таблица для ключей формата перечислений, значение может быть любым типом. Сохраняют "натуральную" последовательность ключей - так
как они объявлены в классе.
Итераторы слабосогласованны.
Null-ключи недопустимы.
Не синхронизирован. Обертка синхронизации - Collections.synchronizedMap(new EnumMap<EnumKey, V>(...)).
Базовые операции выполняются за постоянное время.

#### HashMap
Базовая реализация Map. Позволяет созранять null ключи и значения. Очевидно ключ такой может быть только один.
Предоставляет производительность за постоянное время для базовых операций put и get в случае, если хэш-функция распределяет
элементы равномерно между баккитами. Скорость итерации линейна - итерация по баккитам. Плюс итерация внутри баккита. Очень важно,
устанавливать не слишком большой начальный размер HashMap или не слишком маленькую степень загрузки, т.к. это уменьшит производительность
итератора.
Реализация не синхронизирована. Обёртка синхронизации - Collections.synchronizedMap(new HashMap(...)).
Итераторы - fail-fast.

#### LinkedHashMap
Хэш-таблица с предсказуемым порядком итерации. Узлы связаны в обе стороны. Порядок итерации определяется порядком вставки
элементов в хэш-таблицу. Порядок итерации не поменяется повторной вставки элемента с тем же ключом. Скорость базовых операций
определяется константой (add, contains, remove). Производительность чуть меньше чем у HashMap, из-за поддержания
связанного списка. Однако есть исключение - итерация более производительна, вне зависимости от объема таблицы. Время итерации 
пропорционально количеству вхождений.

#### IdentityHashMap
Эта таблица производит сравнение элементов при вставке через сравнение ссылок на них. То есть через k1==k2, а не как положено,
через k1.equals(k2).
Это реализация частного порядка и она нарушает основной контракт с интерфейсом Map! Класс разработан для работы с редкими, частными
случаями, где необходимо использовать семантику сравнения ссылок.
Таблица не синхронизирована. Обертка синхронизации - Collections.synchronizedMap(new IdentityHashMap(...))
Итераторы fail-fast.

#### WeakHashMap
Таблица со "слабыми" ключами. Вхождение ключ-значение в WeakHashMap автоматически уничтожается, когда более не используется.
Что имеется ввиду - вхождение пары ключ-значение в такую таблицу не предупреждает уничтожение такого объекта сборщиком мусора.
Когда значение удаляется с помощью GC оно так же удаляется и из таблицы.
Null ключи и значения поддерживаются.
Таблица не синхронизирована.
Итераторы fail-fast.

#### ConcurrentHashMap
Хэш-таблица полностью поддерживающая многопоточность при извлечении ключей и высокую производительность при их обновлении.
Включает в себя все методы, которые реализует HashMap. Однако, даже при условии того, что все операции потокобезопасны,
операции извлечения не влекут за собой блокировок, и вся реализация не предполагает функционала, которые могут привести к 
недоступности хэш-таблицы. Соответственно таблица работает по принципу happened-before. Соответственно, для операций с массивом
элементов не гарантируется атомарность. Итераторы являются снапшотом данных. Операции size(), isEmpty(), containsValue()
имеют смысл только в случае если нет потоков, которые в настоящий момент производят манипуляции с содержимым таблицы.
Не позволяет использовать null ни как значения ни как ключи.

#### TreeMap
Масштабируемая многопоточная имплементация NavigableMap. Сортировка ключей - натуральная, объекты должны наследовать
Comparable или сама таблица должна содержать Comparator во время вызова конструктора. Данная реализация, в среднем, предоставляет временную
сложность log(n) для операций containsKey, get, put и remove. Операция вставки, удаления, обновления и доступа безопасно исполняются
в нескольких потоках.
Таблица не синхронизирована. Обёртка синхронизации - Collections.synchronizedSortedMap(new TreeMap(...)).
Итераторы fail-fast.
Вхождения ключ-значения, будут являться снапшотами вхождений на момент, когда они были вставлены в таблицу.

#### ConcurrentSkipHashMap
Масштабируемая многопоточная имплементация ConcurrentNavigableMap. Сортировка ключей - натуральная, объекты должны наследовать 
Comparable или сама таблица должна содержать Comparator во время вызова конструктора. Данная реализация, в среднем, предоставляет временную
сложность log(n) для операций containsKey, get, put и remove. Операция вставки, удаления, обновления и доступа безопасно исполняются
в нескольких потоках.
Итераторы слабосогласованы.
Вхождения ключ-значения, будут являться снапшотами вхождений на момент, когда они были вставлены в таблицу.
Метод size не выполняется за постоянное время.
Операции с массивами элементов не атомарны.
Не поддерживает вставку null значений и ключей.


### Чеклист теории:

```
5 Целей фреймворка
11 Структурных элементов
9 Интерфейсов коллекций
4 Интерфейсов не коллекций
5 Многопоточных интерфейсов
12 Многопоточных реализаций

6 реализаций Set, 2 из 6 многопоточные
8 реализаций Queue, 6 из 9 многопоточные
2 реализации Deque, обе многопоточные
5 Реализаций списка, 1 многопоточная, 2 легаси класса
9 Реализаций таблиц, 2 многопоточные, 1 легаси


```

## Использованные источники
1. Вольный перевод-конспект [оффициального сайта oracle](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html)
2. Вольный перевод-конспект [Javadoc](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/reference.html)
3. Использовал для более конкретного разбора LinkedTransferQueue [java-online](https://java-online.ru/concurrent-queue-block.xhtml#linkedTQ)
4. Разбор приоритизированной кучи [geeksforgeeks.com](https://www.geeksforgeeks.org/priority-queue-using-binary-heap/)