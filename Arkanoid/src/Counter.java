/**
 * Counter: use for counting int numbers.
 */
public class Counter {
    private int num;

    /**
     * Counter constructor .
     *
     * @param number int.
     */
    public Counter(int number) {
        this.num = number;
    }

    /**
     * setCounter : setter function.
     *
     * @param number int.
     */
    public void setCounter(int number) {
        this.num = number;
    }

    /**
     * increase : add number to current count.
     *
     * @param number int.
     */
    public void increase(int number) {
        this.num = getValue() + number;
    }

    /**
     * decrease :  subtract number to current count.
     *
     * @param number int.
     */
    public void decrease(int number) {
        this.num = getValue() - number;
    }

    /**
     * getValue :  get current count.
     *
     * @return num int.
     */
    public int getValue() {
        return num;
    }
}
