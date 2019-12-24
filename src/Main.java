import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        long start = System.nanoTime();
        Game game = new Game(6);
        Graph graph = game.getGraph();
        long end = System.nanoTime();

        System.out.println("Saniye: "+ TimeUnit.SECONDS.convert(end-start, TimeUnit.NANOSECONDS));
    }



}
