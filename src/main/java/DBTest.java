import function.Controller;

import java.util.Scanner;

public class DBTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(scanner);

        for(; ; ) {
            controller.listDisplay();
            System.out.println("何用:? (コスパ順：0 入力：1 編集：2 削除：３)");
            switch (scanner.nextLine()) {
                case "0": controller.sort(); break;
                case "1": controller.listInput(); break;
                case "2": controller.listEdit(); break;
                case "3": controller.delete();
            }
        }
    }
}
