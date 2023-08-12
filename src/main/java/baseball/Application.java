package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {

        System.out.println("숫자 야구 게임을 시작합니다.");

        List<Integer> com = generateComNumber();
        //System.out.println("com = " + com);
        boolean game = true;

        while (game) {
            List<Integer> user = getUserNumber();

            if (doGame(com, user)) {
                game = checkNewGame();
                com = generateComNumber();
            }
        }
    }

    public static boolean checkNewGame() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료\n" +
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String check = Console.readLine();
        if (check.equals("1")) return true;
        else if (check.equals("2")) return false;
        //1 또는 2가 아닌 값을 입력할 경우 Exception 발생
        throw new IllegalArgumentException();
    }

    public static boolean doGame(List<Integer> com, List<Integer> user) {
        int ball = checkBall(com, user);
        int strike = checkStrike(com, user);
        String message = generateMessage(strike, ball);
        System.out.println(message);
        return message.contentEquals("3스트라이크");
    }

    public static String generateMessage(int strike, int ball) {
        if (strike == 3) return "3스트라이크";
        if (strike == 0 && ball == 0) return "낫싱";
        String message = "";
        if (ball != 0) message = message + ball + "볼 ";
        if (strike != 0) message = message + strike + "스트라이크";
        return message;
    }

    public static int checkBall(List<Integer> com, List<Integer> user) {
        int ball = 0;
        for (int comNum : com) {
            int userIndex = user.indexOf(comNum);
            if (userIndex == -1) continue;
            int comIndex = com.indexOf(comNum);
            if (userIndex != comIndex) ball++;
        }
        return ball;
    }

    public static int checkStrike(List<Integer> com, List<Integer> user) {
        int strike = 0;
        for (int comNum : com) {
            int userIndex = user.indexOf(comNum);
            if (userIndex == -1) continue;
            int comIndex = com.indexOf(comNum);
            if (userIndex == comIndex) strike++;
        }
        return strike;
    }

    public static List<Integer> getUserNumber() {
        System.out.print("숫자를 입력해주세요 : ");
        String userInput = Console.readLine();
        List<Integer> user = new ArrayList<>();
        //문자열의 길이가 3일경우 잘못 입력한 것이므로 Exception발생
        if (userInput.length() != 3) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < userInput.length(); i++) {
            try {
                int now = Integer.valueOf(String.valueOf(userInput.charAt(i)));
                //숫자가 0일 경우 Exception발생
                if (now == 0) throw new IllegalArgumentException();
                user.add(now);
            } catch (NumberFormatException e) {
                //숫자가 아닌 값을 입력할 경우 Exception발생
                throw new IllegalArgumentException();
            }
        }
        return user;
    }

    public static List<Integer> generateComNumber() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }
}
