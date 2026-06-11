package function;

import entity.CospaDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {
    //商品情報を表示
    public void listDisplay(ArrayList<CospaDTO> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("ID:" + list.get(i).getId() +
                    " 商品名:" + list.get(i).getName() +
                    " 日時:" + list.get(i).getDate() +
                    " ★100y毎c:" + list.get(i).getCalory() * 100 / list.get(i).getCost() +
                    "★ カロリー:" + list.get(i).getCalory() +
                    " 価格:" + list.get(i).getCost() +
                    " 個数:" + list.get(i).getNumber() +
                    " 用途:" + list.get(i).getPurpose() +
                    " URL:" + list.get(i).getUrl());
        }
    }

    //商品情報を複製してソート
    public void sort(Scanner scanner, ArrayList<CospaDTO> list) {
        ArrayList<CospaDTO> sortList= new ArrayList<CospaDTO>();
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
