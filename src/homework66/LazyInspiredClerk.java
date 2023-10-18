package homework66;

import java.util.ArrayList;
import java.util.List;

public class LazyInspiredClerk extends AbstractClerk{
    private List<Integer> processingTimes; // List для хранения статистики
    private static final int INSPIRATION_PROBABILITY = 20; //  вероятность вдохновения
    private static int INSPIRATION_TIMES = 5; // сколько раз выдает кредиты быстрее

    private double inspirationCoef; // коэфф. ускорения времени выдачи кредита (напр. 1.5 - в полтора раза быстрее)


    public LazyInspiredClerk(String name, int minTime, int maxTime, int creditsNum, double inspirationCoef){
        super(name,minTime,maxTime,creditsNum);
        this.inspirationCoef = inspirationCoef;
        processingTimes = new ArrayList<>();
    }

    @Override
    public List<Integer> getProcessingTimes() {
        return processingTimes;
    }

    @Override
    public void run(){
        startTime = System.currentTimeMillis();
        for (int i = 0; i < creditsNum; i++) {
           int timeToProcess;
            if (random.nextInt(100) < INSPIRATION_PROBABILITY) {
                // ускоряем выдачу кредитов - imitating working ecstasy
                timeToProcess = (int) ((minTime + random.nextInt(maxTime - minTime + 1))/ inspirationCoef);
                INSPIRATION_TIMES--;// уменьшаем количество приходов приступов вдохновления
            } else {
                timeToProcess = minTime + random.nextInt(maxTime - minTime + 1);
            }
            try {
                Thread.sleep(timeToProcess);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finishTime = System.currentTimeMillis();
            // eсли приступы вдохновение закончилось
            if (INSPIRATION_TIMES == 0) {
                inspirationCoef = 1.0;
            }
            // Добавляем время в List для  статистики
            processingTimes.add(timeToProcess);
        }
    }

}

