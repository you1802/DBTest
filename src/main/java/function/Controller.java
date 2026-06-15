package function;

import entity.CospaDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    Scanner scanner;
    CospaDAO cospaDAO;
    ArrayList<CospaDTO> list;
    Display display = new Display();

    public Controller(Scanner scanner){
        this.scanner = scanner;
        cospaDAO = new CospaDAO(scanner);
        list = cospaDAO.load();
    }

    //商品情報を入力
    public void listInput() {
        CospaDTO cospaDTO = new CospaDTO();
        cospaDTO.setId(list.isEmpty() ? 0 : list.getLast().getId() + 1);
        System.out.println("URL:?");
        cospaDTO.setUrl(scanner.nextLine());
        System.out.println("商品名:?");
        cospaDTO.setName(scanner.nextLine());
        cospaDTO.setDate(LocalDateTime.now().toString());
        System.out.println("価格:?");
        cospaDTO.setCost(scanner.nextInt());
        System.out.println("個数:?");
        cospaDTO.setNumber(scanner.nextInt());
        System.out.println("用途:? (燃料:1　甘味:2　他:3)");
        cospaDTO.setPurpose(scanner.nextInt());
        System.out.println("カロリー:?");
        cospaDTO.setCalory(scanner.nextInt());
        list.add(cospaDTO);
        cospaDAO.save(cospaDTO);
        scanner.nextLine();
    }

    //商品情報を編集
    public void listEdit() {
        System.out.println("編集ID:?");

        int id = askId();
        if (id == -1) {
            System.out.println("キャンセル");
            return;
        }

        System.out.println("編集項目:? (URL:1 商品名:2 価格:3 個数:4 用途:5 カロリー:6)");
        int item = scanner.nextInt();
        scanner.nextLine();
        if (0 > item || item > 7) {
            System.out.println("キャンセル");
            return;
        }
        ask();
        switch (item) {
            case 1: list.get(id).setUrl(cospaDAO.editDB("url", id, scanner.nextLine())); break;
            case 2: list.get(id).setName(cospaDAO.editDB("name", id, scanner.nextLine())); break;
            case 3: list.get(id).setCost(cospaDAO.editDB("cost", id, scanner.nextInt())); break;
            case 4: list.get(id).setNumber(cospaDAO.editDB("number", id, scanner.nextInt())); break;
            case 5: list.get(id).setPurpose(cospaDAO.editDB("purpose", id, scanner.nextInt())); break;
            case 6: list.get(id).setCalory(cospaDAO.editDB("calory", id, scanner.nextInt())); break;
        }
        scanner.nextLine();
    }

    //商品情報を削除
    public void delete() {
        System.out.println("削除ID:?");
        int id = askId();
        if (id == -1) {
            System.out.println("キャンセル");
            return;
        }
        list.get(id).setDeleted(true);
        cospaDAO.deleteDB(id);
    }

    private void ask() {
        System.out.println("更新値:?");
    }

    private int askId() {
        int id = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    //Displayクラスのsortメソッドを呼び出し
    public void sort(){
        display.sort(list);
        System.out.println("戻る:Enter");
        scanner.nextLine();
    }

    public void listDisplay(){
        display.listDisplay(list);
    }
}
