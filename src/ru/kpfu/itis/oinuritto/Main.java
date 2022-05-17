package ru.kpfu.itis.oinuritto;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    // вводит кол-во изображений для скачивания, к апи делается столько запросов, сколько надо чтобы получить все это
        // сохранять только те, которые больше 4000 на 2000 или 2000 на 4000
        // csv файл записывает список названий, автор, идентификатор и размер
        System.out.print("Введите кол-во картинок, которое желаете сохранить: ");

        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();

        try {
            ImagesListParser.getAllInfo(count);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
