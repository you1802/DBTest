package function;

import entity.CospaDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

//ファイルから読み込みと保存
public class CospaDAO {
    public static final String DB_URL = "jdbc:h2:mem:~/test";
    public static final String DB_USER = "sa";
    public static final String DB_PASSWARD = "";

    //読み込み
    public ArrayList<CospaDTO> load() {
        ArrayList<CospaDTO> list = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWARD);
            Statement sta = con.createStatement();

            sta.execute("""
     CREATE TABLE IF NOT EXISTS cospa (id INT, url VARCHAR, name VARCHAR, date VARCHAR, cost INT, number INT, purpose INT, calory INT, deleted BOOLEAN);
     """);
            ResultSet resultSet = sta.executeQuery("""
    SELECT *
    FROM cospa
    """);
            while (resultSet.next()){
                CospaDTO cospaDTO = new CospaDTO();
                cospaDTO.setUrl(resultSet.getString("url"));
                cospaDTO.setId(resultSet.getInt("id"));
                cospaDTO.setName(resultSet.getString("name"));
                cospaDTO.setDate(resultSet.getString("date"));
                cospaDTO.setCost(resultSet.getInt("cost"));
                cospaDTO.setNumber(resultSet.getInt("number"));
                cospaDTO.setPurpose(resultSet.getInt("purpose"));
                cospaDTO.setCalory(resultSet.getInt("calory"));
                cospaDTO.setDeleted(resultSet.getBoolean("deleted"));
                list.add(cospaDTO);
            }
            con.close();
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    //保存
    public void save(CospaDTO cospaDTO){

        try {
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWARD);
            PreparedStatement psta = con.prepareStatement("""
    INSERT INTO cospa(id, url, name, date, cost, number, purpose, calory, deleted)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """);
            psta.setInt(1, cospaDTO.getId());
            psta.setString(2, cospaDTO.getUrl());
            psta.setString(3, cospaDTO.getName());
            psta.setString(4, cospaDTO.getDate());
            psta.setInt(5, cospaDTO.getCost());
            psta.setInt(6, cospaDTO.getNumber());
            psta.setInt(7, cospaDTO.getPurpose());
            psta.setInt(8, cospaDTO.getCalory());
            psta.setBoolean(9, cospaDTO.isDeleted());

            psta.execute();

            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}