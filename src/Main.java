import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {

    public static AtomicInteger lenTree = new AtomicInteger();
    public static AtomicInteger lenFour = new AtomicInteger();
    public static AtomicInteger lenFive = new AtomicInteger();

    public static void main(String[] agrs) throws InterruptedException, ExecutionException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        List<String> texts3 = new ArrayList<>();
        List<String> texts4 = new ArrayList<>();
        List<String> texts5 = new ArrayList<>();
        for (String text : texts) {
            if (text.length() == 3) {
                texts3.add(text);
            }
            if (text.length() == 4) {
                texts4.add(text);
            }
            if (text.length() == 5) {
                texts5.add(text);
            }
        }

        new Thread(() -> {
            for (String text : texts3) {
                if (isPallindrom(text)) {
                    lenTree.incrementAndGet();
                    continue;
                }
                if (isOnlyOneCharInLine(text)) {
                    lenTree.incrementAndGet();
                    continue;
                }
                if (isLettersAreAscendingOlder(text)) {
                    lenTree.incrementAndGet();
                }
            }
        }).start();

        new Thread(() -> {
            for (String text : texts4) {
                if (isPallindrom(text)) {
                    lenFour.incrementAndGet();
                } else if (isOnlyOneCharInLine(text)) {
                    lenFour.incrementAndGet();
                } else if (isLettersAreAscendingOlder(text)) {
                    lenFour.incrementAndGet();
                }
            }
        }).start();

        new Thread(() -> {
            for (String text : texts5) {
                if (isPallindrom(text)) {
                    lenFive.incrementAndGet();
                } else if (isOnlyOneCharInLine(text)) {
                    lenFive.incrementAndGet();
                } else if (isLettersAreAscendingOlder(text)) {
                    lenFive.incrementAndGet();
                }
            }
        }).start();

        System.out.println("Красивых слов с длиной 3: " + lenTree + " шт");
        System.out.println("Красивых слов с длиной 4: " + lenFour + " шт");
        System.out.println("Красивых слов с длиной 5: " + lenFive + " шт");

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPallindrom(String text) {
        for (int i = 0; i < text.length() / 2; i++) {
            if (text.charAt(i) != text.charAt(text.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOnlyOneCharInLine(String text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) != text.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLettersAreAscendingOlder(String text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) > text.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}