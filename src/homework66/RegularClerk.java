package homework66;

import java.util.ArrayList;
import java.util.List;

public class RegularClerk extends AbstractClerk{
    private final List<Integer> processingTimes; // List для хранения статистики
    public RegularClerk(String name, int minTime, int maxTime, int creditsNum) {
        super(name, minTime, maxTime, creditsNum);
        processingTimes = new ArrayList<>();
       }

    @Override
    public List<Integer> getProcessingTimes() {
        return processingTimes;
    }

    @Override
    public void run() {

        startTime = System.currentTimeMillis();
        for (int i = 0; i < creditsNum; i++) {

            int timeToProcess = minTime + random.nextInt(maxTime - minTime + 1);
            try {
                Thread.sleep(timeToProcess);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finishTime = System.currentTimeMillis();
            // Добавляем время в List для  статистики
            processingTimes.add(timeToProcess);
        }
    }

}
