package com.company;

import java.util.ArrayList;
import java.util.List;

public class Walking {

    //Заполнаяем список данными
    //Обьект Place имеет конструктор с параметрами: name - имя достопримечательности, time - затраченное время, importance - важность посещения.
    //Данный метод возвращает список с объектами Place
    public List<Place> getPlacesList()
    {
        List<Place> places = new ArrayList<>();
        places.add(new Place("Исаакиевский собор", 5, 10));
        places.add(new Place("Эрмитаж", 8, 11));
        places.add(new Place("Кунсткамера", 3.5, 4));
        places.add(new Place("Петропавловская крепость", 10, 7));
        places.add(new Place("Ленинградский зоопарк", 9, 15));
        places.add(new Place("Медный всадник", 1, 17));
        places.add(new Place("Казанский собор", 4, 3));
        places.add(new Place("Спас на Крови", 2, 9));
        places.add(new Place("Зимний дворец Петра I", 7, 12));
        places.add(new Place("Зоологический музей", 5.5, 6));
        places.add(new Place("Музей обороны и блокады Ленинграда", 2, 19));
        places.add(new Place("Русский музей", 5, 8));
        places.add(new Place("Навестить друзей", 12,20));
        places.add(new Place("Музей восковых фигур", 2, 13));
        places.add(new Place("Литературно-мемориальный музей Ф.М. Достоевского", 4, 2));
        places.add(new Place("Екатерининский дворец", 1.5, 5));
        places.add(new Place("Петербургский музей кукол", 1, 14));
        places.add(new Place("Музей микроминиатюры «Русский Левша»", 3, 18));
        places.add(new Place("Всероссийский музей А.С. Пушкина и филиалы", 6, 1));
        places.add(new Place("Музей современного искусства Эрарта", 7, 16));

        return places;
    }

    //метод потсроения оптимального маршрута
    public void buildRoute() throws InterruptedException {
        //Создаем массив, в котором храним значения затраченного времени
        int[] time = new int[getPlacesList().size()];
        //Создаем массив, в котором храним значения важности каждой достопримечательности
        int[] importance = new int[getPlacesList().size()];
        //Заполняем массивы данными
        for (int i = 0; i < getPlacesList().size(); i++) {
            time[i] = (int) getPlacesList().get(i).getTime();
            importance[i] = getPlacesList().get(i).getImportance();
        }


        int count = time.length;
        int maxTime = 32;

        int[][] A;
        A = new int[count + 1][];
        for (int i = 0; i < count + 1; i++) {
            A[i] = new int[maxTime + 1];
        }


        for (int k = 0; k <= count; k++) {
            //
            for (int s = 0; s <= maxTime; s++) {
                if (k == 0 || s == 0) {
                    A[k][s] = 0;
                } else {
                    if (s >= time[k - 1]) {
                        A[k][s] = Math.max(A[k - 1][s], A[k - 1][s - time[k - 1]] + importance[k - 1]);
                    } else {
                        A[k][s] = A[k - 1][s];
                    }
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        traceResult(A, time, count, maxTime, result);

        System.out.println("Надо проложить маршрут...");
        Thread.sleep(1000);
        System.out.println("Кажется, я нашел оптимальный путь...");
        Walking walking = new Walking();
        int walkTime = 0;
        for(Integer integer : result) {
            System.out.print(walking.getPlacesList().get(integer - 1).getName() + " || ");
            walkTime += walking.getPlacesList().get(integer - 1).getTime();
            if (walkTime == 16)
            {
                System.out.println();
                System.out.println("Прошло " + walkTime + " Часов...");
                System.out.println("Отличный День! \n Пора поспать...");
                Thread.sleep(2000);
                System.out.println("Новый день. \n Сегодня я посещу ...");
            }
        }
        System.out.println();
        System.out.println("Отличные выходные! К друзьям я приеду отдельно...");
        System.out.println("Потрачено времени: " + walkTime + " Час...");
    }

    private void traceResult(int[][] A, int[] time, int k, double s, ArrayList<Integer> result) {
        if (A[k][(int) s] == 0) {
            return;
        }
        if (A[k -1][(int) s] == A[k][(int) s]) {
            traceResult(A, time, k - 1, s, result);
        } else {
            traceResult(A, time, k - 1, s - time[k - 1], result);
            result.add(0, k);
        }
    }
}
