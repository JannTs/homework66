package homework66;

public class Main {
    /*
    Сотрудники банка выдают кредиты. У каждого есть диапазон по времени - максимальное время и минимальное время которое
    требуется сотруднику для выдачи кредита. ( minTime, maxTime) . Но среди них есть сотрудник/сотрудники, у которых
    вдруг возрастает работоспособность и они начинают выдавать кредиты быстрее, а затем опять скорость может снижатьс до
    обычной.
    Посчитать время, за которое каждый сотрудник выдаст N  кредитов

    1. создать классы:
    -   абстрактный класс AbstractClerk
    -   дочерние классы RegularClerk, LazyInspiredClerk
    2. Должна быть возможность на основе классов создавать потоки. Т.е.
    родительский класс  должен реализовывать Runnable
     */

    public static void main(String[] args) {
        int creditToIssue = 15;
        AbstractClerk regularClerk = new RegularClerk(
                "Regular Clerk",
                30,
                50,
                creditToIssue);
        AbstractClerk crazyClerk = new LazyInspiredClerk(
                "Crazy Clerk",
                40,
                60,
                creditToIssue,
                2.0);

        Thread thread1 = new Thread(regularClerk);
        Thread thread2 = new Thread(crazyClerk);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long regularClerkTime = regularClerk.getFinishTime() - regularClerk.getStartTime();
        long crazyClerkTime = crazyClerk.getFinishTime() - crazyClerk.getStartTime();


        System.out.println("Regular Clerk потратил времени на выдачу всех кредитов: " +
                    regularClerkTime);
        System.out.println("По каждому кредиту "+ regularClerk.getProcessingTimes().toString()
                    + "\nсреднее время, потраченное на клиента "+ regularClerkTime/creditToIssue);
        System.out.println("Crazy Clerk потратил времени на выдачу всех кредитов: "
                    + crazyClerkTime );
        System.out.println("По каждому кредиту "+ crazyClerk.getProcessingTimes().toString()
                    + "\nсреднее время, потраченное на клиента "+ crazyClerkTime/creditToIssue);
    }

}