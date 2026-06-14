import entity.CospaDTO;
import function.Controller;
import function.CospaDAO;
import function.Display;
import java.util.ArrayList;
import java.util.Scanner;

public class DBTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        Display display = new Display();
        CospaDAO cospaDAO = new CospaDAO();
        ArrayList<CospaDTO> list = cospaDAO.load();

        for(; ; ) {
            display.listDisplay(list);
            System.out.println("何用:? (コスパ順：0 入力：1 編集：2 削除：３)");
            switch (scanner.nextLine()) {
                case "0": display.sort(scanner, list); break;
                case "1": controller.listInput(scanner, list); break;
                case "2": controller.listEdit(scanner, list); break;
                case "3": controller.delete(scanner, list); break;
                default:continue;
            }


        }
    }
}
