import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Object> removeMatchChar(List<Character> array1, List<Character> array2) {
        for (int i = 0; i < array1.size(); i++) {
            for (int j = 0; j < array2.size(); j++) {
                if (array1.get(i).equals(array2.get(j))) {
                    char matchChar = array1.get(i);
                    array1.remove(Character.valueOf(matchChar));
                    array2.remove(Character.valueOf(matchChar));
                    List<Object> concatenatedArray = new ArrayList<>(array1);
                    concatenatedArray.add('*');
                    concatenatedArray.addAll(array2);
                    List<Object> result = new ArrayList<>();
                    result.add(concatenatedArray);
                    result.add(true);
                    return result;
                }
            }
        }
        List<Object> concatenatedArray = new ArrayList<>(array1);
        concatenatedArray.add('*');
        concatenatedArray.addAll(array2);
        List<Object> result = new ArrayList<>();
        result.add(concatenatedArray);
        result.add(false);
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player 1 name : ");
        String player1Name = scanner.nextLine().toLowerCase().replaceAll(" ", "");
        char[] player1Chars = player1Name.toCharArray();
        List<Character> player1Array = new ArrayList<>();
        for (char c : player1Chars) {
            player1Array.add(c);
        }
        System.out.print("Player 2 name : ");
        String player2Name = scanner.nextLine().toLowerCase().replaceAll(" ", "");
        char[] player2Chars = player2Name.toCharArray();
        List<Character> player2Array = new ArrayList<>();
        for (char c : player2Chars) {
            player2Array.add(c);
        }
        boolean continueLoop = true;
        while (continueLoop) {
            List<Object> returnList = removeMatchChar(player1Array, player2Array);
            List<Character> concatenatedList = (List<Character>) returnList.get(0);
            continueLoop = (boolean) returnList.get(1);
            int starIndex = concatenatedList.indexOf('*');
            player1Array = concatenatedList.subList(0, starIndex);
            player2Array = concatenatedList.subList(starIndex + 1, concatenatedList.size());
        }
        int totalCount = player1Array.size() + player2Array.size();
        String[] relationshipStatuses = {"Friends", "Love", "Affection", "Marriage", "Enemy", "Siblings"};
        while (relationshipStatuses.length > 1) {
            int splitIndex = (totalCount % relationshipStatuses.length) - 1;
            if (splitIndex >= 0) {
                String[] rightPart = new String[relationshipStatuses.length - splitIndex - 1];
                System.arraycopy(relationshipStatuses, splitIndex + 1, rightPart, 0, relationshipStatuses.length - splitIndex - 1);
                String[] leftPart = new String[splitIndex];
                System.arraycopy(relationshipStatuses, 0, leftPart, 0, splitIndex);
                String[] tempResult = new String[rightPart.length + leftPart.length];
                System.arraycopy(rightPart, 0, tempResult, 0, rightPart.length);
                System.arraycopy(leftPart, 0, tempResult, rightPart.length, leftPart.length);
                relationshipStatuses = tempResult;
            } else {
                String[] tempResult = new String[relationshipStatuses.length - 1];
                System.arraycopy(relationshipStatuses, 0, tempResult, 0, relationshipStatuses.length - 1);
                relationshipStatuses = tempResult;
            }
        }
        System.out.println("Relationship status: " + relationshipStatuses[0]);
    }
}
