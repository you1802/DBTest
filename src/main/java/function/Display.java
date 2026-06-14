package function;

import entity.CospaDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    public void cospaDisplay(CospaDTO cospaDTO){
        System.out.println(
                "ID:" + cospaDTO.getId() +
                " 商品名:" + cospaDTO.getName() +
                " 日時:" + cospaDTO.getDate() +
                " ★100y毎c:" + cospaDTO.getCalory() * 100 / cospaDTO.getCost() +
                "★ カロリー:" + cospaDTO.getCalory() +
                " 価格:" + cospaDTO.getCost() +
                " 個数:" + cospaDTO.getNumber() +
                " 用途:" + cospaDTO.getPurpose() +
                " URL:" + cospaDTO.getUrl());
    }

    //商品情報を表示
    public void listDisplay(ArrayList<CospaDTO> list) {
        list.stream().filter(cospaDTO -> !cospaDTO.isDeleted()).forEach(this::cospaDisplay);
    }

    //商品情報を複製してソート
    public void sort(Scanner scanner, ArrayList<CospaDTO> list) {

        list.stream().filter(cospaDTO -> !cospaDTO.isDeleted()).sorted((a, b) -> b.getCalory() * 100 / b.getCost() - a.getCalory() * 100 / a.getCost()).forEach(this::cospaDisplay);

        System.out.println("戻る:Enter");
        scanner.nextLine();
    }
}
