import meekie.properties.EncryptedProperty;

import static meekie.properties.EncryptedPropertyKt.encode;

public class Test {
    public static void main(String[] args) {
        EncryptedProperty property = new EncryptedProperty(); // можем создать из другого модуля через конструктор
        String s = "12345";
        // String encoded = s.encode("12345"); -> ошибка, компилятор не может добавить метод в существующий класс
        String encoded = encode(s); // но можно импортировать как статический метод, и передать параметр!
        System.out.println(encoded);
    }
}
