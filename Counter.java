public class Counter {
    private int count=0;
    public int getValue(){
        return count;
    }
    public void increment(){
        count++;
    }
    Thread thread0=new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i=0;i<1000;i++){
                increment();
            }
        }
    });
    Thread thread1=new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i=0;i<1000;i++){
                increment();
            }
        }
    });

    public static void main(String[] args) {
        Counter myclass=new Counter();
        myclass.thread0.start();
        myclass.thread1.start();
        try {
            myclass.thread0.join();
            myclass.thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(myclass.getValue());
    }
    /*Если запустить эту программу несколько раз, то результат может быть разным.
     Это связано с тем, что оба потока одновременно обращаются к объекту Counter.
     В результате значение count может быть увеличено на разные значения в зависимости от того,
     в какой момент времени потоки обращаются к объекту Counter.

     Чтобы избежать этого, необходимо синхронизировать доступ к объекту Counter.
     Это можно сделать, используя метод synchronized, который блокирует объект, пока он используется другим потоком.*/
    //Типо того, что снизу
    class CounterFixed {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getValue() {
            return count;
        }
    }
    //Или можно было синхронизировать работу метода run() через Object
}
