package calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // 한 줄 입력 받기

        try {
            int result = add(input);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    // 빈 문자열 처리
    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        return 0; // 이후 로직 추가 예정
    }
}