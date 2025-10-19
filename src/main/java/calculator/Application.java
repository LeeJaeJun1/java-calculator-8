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

    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0; // 빈 문자열 처리
        }

        String delimiterRegex = ",|:"; // 기본 구분자
        String numbers = input;

        // 커스텀 구분자 처리
        if (input.startsWith("//")) {
            // 1. \n 문자의 위치를 찾습니다.
            int delimiterEnd = input.indexOf("\n");

            if (delimiterEnd == -1) {
                delimiterEnd = input.indexOf("\\n");
            }

            // 여전히 구분자 끝을 찾지 못했다면, 잘못된 형식으로 예외를 던집니다.
            if (delimiterEnd == -1) {
                throw new IllegalArgumentException("잘못된 입력 형식입니다. 커스텀 구분자 선언 형식이 올바르지 않습니다.");
            }

            // 구분자 추출: "//" 이후부터 끝까지
            String customDelimiter = input.substring(2, delimiterEnd);

            // 숫자 문자열 추출: "\n" 또는 "\\n" 이후부터 끝까지
            numbers = input.substring(delimiterEnd + 2); // "\n" 또는 "\\n"의 길이는 2

            // 구분자 정규식 업데이트
            delimiterRegex = delimiterRegex + "|" + Pattern.quote(customDelimiter);
        }

        // 잘못된 값 처리
        String[] tokens = numbers.split(delimiterRegex);
        int sum = 0;

        for (String token : tokens) {
            if (token.isEmpty()) continue; // 빈 토큰 무시

            int number;
            try {
                // trim()을 사용하여 혹시 모를 공백 제거 (안전성 강화)
                number = Integer.parseInt(token.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 입력되었습니다: " + token);
            }
            if (number < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
            }
            sum += number;
        }

        return sum;
    }
}