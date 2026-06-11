package function;

import entity.CospaDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    CospaDAO cospaDAO = new CospaDAO();

    //商品情報を入力
    public void listInput(Scanner scanner, ArrayList<CospaDTO> list) {
        CospaDTO cospaDTO = new CospaDTO();
        cospaDTO.setId(list.isEmpty() ? 0 : list.get(list.size() - 1).getId() + 1);
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
    public void listEdit(Scanner scanner, ArrayList<CospaDTO> list) {
        System.out.println("編集ID:?");

        int id = askId(scanner, list);
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
            case 1: list.get(id).setUrl(scanner.nextLine()); break;
            case 2: list.get(id).setName(scanner.nextLine()); break;
            case 3: list.get(id).setCost(scanner.nextInt()); break;
            case 4: list.get(id).setNumber(scanner.nextInt()); break;
            case 5: list.get(id).setPurpose(scanner.nextInt()); break;
            case 6: list.get(id).setCalory(scanner.nextInt()); break;
        }
    }

    //商品情報を削除
    public void delete(Scanner scanner, ArrayList<CospaDTO> list) {
        System.out.println("削除ID:?");
        int id = askId(scanner, list);
        if (id == -1) {
            System.out.println("キャンセル");
            return;
        }
        list.remove(id);
    }

    private void ask() {
        System.out.println("更新値:?");
    }

    private int askId(Scanner scanner, ArrayList<CospaDTO> list) {
        int id = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
