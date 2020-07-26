import java.util.Arrays;
import java.util.List;

public class Launcher {

    private static final DBService service = new DBService();

    public static void main(String[] args) {

        List<String> commands = Arrays.asList(args);

        if (commands.contains("refill")) {
            System.out.println("Cleaning and filling DB");
            service.deleteDatabase();
            service.fillDatabase();
        }
        service.addLabelsToNodes();
    }


}