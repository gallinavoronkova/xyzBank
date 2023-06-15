package pages;

public class TrainingReturnMethod { // тренировались с return, просто пример
    public String number() {
        return "5" + "5";
    }

    public String fooNumber() {
        return "55";
    }

    public void sum() {
        Integer sum1 = Integer.valueOf(number() + fooNumber());
        System.out.println(sum1);
    }
}
