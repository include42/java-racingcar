package racinggame.domain.car;

public class Position {
    private int position;

    /**
     * DEFAULT_ACCELERATE는 정수형 상수값으로, 별도의 파라미터가 없을 경우 자동차가 한번에 움직이는 거리이다.
     */
    public static final int DEFAULT_ACCELERATE = 1;

    /**
     * 생성자 메서드는 정수형 파라미터를 전달받아, 이를 position에 대입한다.
     * 이 과정에서 예외가 발생하는지 검사한다.
     *
     * @param position 이 클래스에 대입할 위치 값으로, 정수형 변수이다.
     */
    public Position(int position) {
        validatePosition(position);
        this.position = position;
    }

    /**
     * validatePosition은 position값이 올바른지 검사하는 메서드이다.
     * 만약 position이 0보다 작을 경우, 예외를 발생시킨다.
     *
     * @param position 검사할 position 값이다.
     */
    private void validatePosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("위치값은 0 이상이어야 합니다.");
        }
    }

    public boolean match(int position) {
        return this.position == position;
    }

    /**
     * accelerate는 position에 값을 더해주는 메서드이다.
     * 만약 파라미터 없이 호출될 경우, 상수로 정의된 값만큼 position을 올려준다.
     */
    public void accelerate() {
        accelerate(DEFAULT_ACCELERATE);
    }

    /**
     * accelerate(오버로딩)는 position에 전달된 파라미터만큼 값을 더해주는 메서드이다.
     * position 값은 0 이상의 정수이며, 더하는 것만 가능하다.
     * 그렇기 때문에 accelerate 메서드를 통해서만 조작할 수 있도록 설계하였다.
     * 만약 파라미터가 음수이거나 더한 값이 오버플로우가 될 경우, 예외를 발생시킨다.
     *
     * @param accelerateValue 현재 위치에 더해 줄 정수 값이다.
     */
    public void accelerate(int accelerateValue) {
        if (accelerateValue <= 0) {
            throw new IllegalArgumentException("잘못된 요청이 가속 메서드에 전달되었습니다");
        }
        try {
            position = Math.addExact(position, accelerateValue);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("잘못된 요청이 가속 메서드에 전달되었습니다");
        }
    }

    /**
     * getPosition은 포지션 값을 반환해주는 메서드이다.
     *
     * @return 정수형 position 값을 반환한다.
     */
    public int getPosition() {
        return position;
    }
}
