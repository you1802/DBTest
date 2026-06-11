package function;

import entity.CospaDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {
    //商品情報を表示
    public void listDisplay(ArrayList<CospaDTO> list) {
        for (CospaDTO cospaDTO : list) {
            if(cospaDTO.isDeleted()) continue;

            System.out.println("ID:" + cospaDTO.getId() +
                    " 商品名:" + cospaDTO.getName() +
                    " 日時:" + cospaDTO.getDate() +
                    " ★100y毎c:" + cospaDTO.getCalory() * 100 / cospaDTO.getCost() +
                    "★ カロリー:" + cospaDTO.getCalory() +
                    " 価格:" + cospaDTO.getCost() +
                    " 個数:" + cospaDTO.getNumber() +
                    " 用途:" + cospaDTO.getPurpose() +
                    " URL:" + cospaDTO.getUrl());
        }
    }

    //商品情報を複製してソート
    public void sort(Scanner scanner, ArrayList<CospaDTO> list) {
        ArrayList<CospaDTO> sortList= new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {                    //listをsortListに複製
            sortList.add(list.get(i).clone());
        }
        for (int i = 0; i < sortList.size() - 1; i++) {
            for (int j = 0; j < sortList.size() - 1; j++) {            //コスパ順でソート
                if (sortList.get(j).getCalory() * 100 / sortList.get(j).getCost() < sortList.get(j + 1).getCalory() * 100 / sortList.get(j + 1).getCost()) {
                    CospaDTO temp = sortList.get(j);
                    sortList.set(j, sortList.get(j + 1));
                    sortList.set(j + 1, temp);
                }
            }
        }
        listDisplay(sortList);
        System.out.println("戻る:Enter");
        scanner.nextLine();
    }
}
