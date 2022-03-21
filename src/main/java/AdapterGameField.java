import java.util.regex.Pattern;

public class AdapterGameField {
    public static String adapter(String s) {
        String str = s;

        if (s.contains("0.0") || s.contains("0 0")) str = s.replaceAll(String.valueOf(Pattern.compile("0.0|0\s0")), "1");
        if (s.contains("0.1") || s.contains("0 1")) str = s.replaceAll(String.valueOf(Pattern.compile("0.1|0\s1")), "2");
        if (s.contains("0.2") || s.contains("0 2")) str = s.replaceAll(String.valueOf(Pattern.compile("0.2|0\s2")), "3");
        if (s.contains("1.0") || s.contains("1 0")) str = s.replaceAll(String.valueOf(Pattern.compile("1.0|1\s0")), "4");
        if (s.contains("1.1") || s.contains("1 1")) str = s.replaceAll(String.valueOf(Pattern.compile("1.1|1\s1")), "5");
        if (s.contains("1.2") || s.contains("1 2")) str = s.replaceAll(String.valueOf(Pattern.compile("1.2|1\s2")), "6");
        if (s.contains("2.0") || s.contains("2 0")) str = s.replaceAll(String.valueOf(Pattern.compile("2.0|2\s0")), "7");
        if (s.contains("2.1") || s.contains("2 1")) str = s.replaceAll(String.valueOf(Pattern.compile("2.1|2\s1")), "8");
        if (s.contains("2.2") || s.contains("2 2")) str = s.replaceAll(String.valueOf(Pattern.compile("2.2|2\s2")), "9");
        return str;
    }
}
