import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IllegalArgumentException, ArithmeticException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку с арифметическим выражением между двумя числами:");
        String line = in.nextLine();
        int result = calc(line);
        System.out.println("Результат вычислений: ");
        System.out.println(result);
    }

    public static int calc(String input) throws IllegalArgumentException, ArithmeticException {
        String[] words = input.split(" ");
        String sortedstring = splitString(words);
        String[] elements = deleteSpaceElem(sortedstring);
        String postfixstring = postfix(elements);
        System.out.println("Строка, преобразованная из инфиксной формы записи, в постфиксную: ");
        System.out.println(postfixstring);
        return countResult(postfixstring.split(" "));
    }

    public static String splitString(String[] string) throws IllegalArgumentException {
        StringBuilder builder1 = new StringBuilder();
        for (String elem : string) {
            char[] chararray = elem.toCharArray();
            for (char ch : chararray) {
                if (((int) ch >= 48 && (int) ch <= 57)) {
                    builder1.append(ch);
                } else {
                    switch (ch) {
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                            builder1.append(' ').append(ch).append(' ');
                            break;
                        default:
                            throw new IllegalArgumentException("Выражение не соответствует требованиям!");
                    }
                }
            }
            builder1.append(' ');
        }
        System.out.println(builder1.toString().trim());
        return builder1.toString().trim();
    }

    public static String[] deleteSpaceElem(String line2) {
        StringBuilder builder2 = new StringBuilder();
        String[] masselem = line2.split(" ");
        for (String elem : masselem) {
            if (!elem.isEmpty()) {
                builder2.append(elem).append(" ");
            }
        }
        return builder2.toString().trim().split(" ");
    }

    public static String postfix(String[] args) throws IllegalArgumentException {
        String[] mass_op = new String[10];
        StringBuilder builder3 = new StringBuilder();
        if (args.length < 2) {
            throw new IllegalArgumentException("Строка не является математической операцией!");
        }
        int j = 0;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "+":
                case "-":
                case "*":
                case "/":
                    if (i == 1) {
                        mass_op[j] = args[i];
                        j++;
                    } else {
                        throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *) в инфиксной форме");
                    }
                    break;
                default:
                    if (i % 2 == 0 && i < 3) {
                        builder3.append(args[i]).append(" ");
                        if (j == 1) {
                            builder3.append(mass_op[j-1]);
                        }
                    } else {
                        throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    }
            }
        }
        return builder3.toString();
    }

    public static int countResult(String[] masselem) throws IllegalArgumentException, ArithmeticException {
        int[] arr_num = new int[5];
        int i = 0;
        for (String s : masselem) {
            switch (s) {
                case "-":
                    arr_num[i - 2] = arr_num[i - 2] - arr_num[i - 1];
                    break;
                case "+":
                    arr_num[i - 2] = arr_num[i - 2] + arr_num[i - 1];
                    break;
                case "*":
                    arr_num[i - 2] = arr_num[i - 2] * arr_num[i - 1];
                    break;
                case "/":
                    arr_num[i - 2] = arr_num[i - 2] / arr_num[i - 1];
                    break;
                default:
                    if ((Integer.parseInt(s)) > 0 && (Integer.parseInt(s) < 11)) {
                        arr_num[i] = Integer.parseInt(s);
                        i++;
                    } else {
                        throw new IllegalArgumentException("Число " + s + " не входит в указанный в задании диапазон!");
                    }
            }
        }
        return arr_num[0];
    }
}
