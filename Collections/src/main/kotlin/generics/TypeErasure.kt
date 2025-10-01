package generics

fun main() {
    val box1 = Box(10)
    val box2 = Box(20)
    val sum = box1.value + box2.value // нужно сделать downcast, чтобы работало сложение
    println(sum)
}

data class Box<T>(var value: T)

/* Байт код - в нем происходит стирание типов, в момент работы программы обобщенных типов не существует,
    они приводятся к типу Object (java)

    package generics;

    import kotlin.Metadata;
    import kotlin.jvm.internal.Intrinsics;
    import org.jetbrains.annotations.NotNull;
    import org.jetbrains.annotations.Nullable;

    @Metadata(
       mv = {2, 0, 0},
       k = 1,
       xi = 50,
       d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u001c\u0010\u0003\u001a\u00028\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\b\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0013"},
       d2 = {"Lgenerics/Box;", "T", "", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "setValue", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lgenerics/Box;", "equals", "", "other", "hashCode", "", "toString", "", "Collections"}
    )
    public final class Box {
       private Object value;

       public Box(Object value) {
          this.value = value;
       }

       public final Object getValue() {
          return this.value;
       }

       public final void setValue(Object var1) {
          this.value = var1;
       }

       public final Object component1() {
          return this.value;
       }

       @NotNull
       public final Box copy(Object value) {
          return new Box(value);
       }

       // $FF: synthetic method
       public static Box copy$default(Box var0, Object var1, int var2, Object var3) {
          if ((var2 & 1) != 0) {
             var1 = var0.value;
          }

          return var0.copy(var1);
       }

       @NotNull
       public String toString() {
          return "Box(value=" + this.value + ')';
       }

       public int hashCode() {
          return this.value == null ? 0 : this.value.hashCode();
       }

       public boolean equals(@Nullable Object other) {
          if (this == other) {
             return true;
          } else if (!(other instanceof Box)) {
             return false;
          } else {
             Box var2 = (Box)other;
             return Intrinsics.areEqual(this.value, var2.value);
          }
       }
    }
    // TypeErasureKt.java
    package generics;

    import kotlin.Metadata;

    @Metadata(
       mv = {2, 0, 0},
       k = 2,
       xi = 50,
       d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"},
       d2 = {"main", "", "Collections"}
    )
    public final class TypeErasureKt {
       public static final void main() {
          Box box1 = new Box(10);
          Box box2 = new Box(20);
          int sum = ((Number)box1.getValue()).intValue() + ((Number)box2.getValue()).intValue();
          System.out.println(sum);
       }

       // $FF: synthetic method
       public static void main(String[] args) {
          main();
       }
    }
 */