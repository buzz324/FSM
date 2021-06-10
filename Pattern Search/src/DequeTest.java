import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DequeTest {
     // DequeTest entry point
     public static void main(String[] args) {
        // usage instructions
        String usage = new String("please enter: `push|put <int value>`, `get`, `print`");
        System.out.println(usage);

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String in = new String("");

        Deque deque = new Deque();
        while (in != "exit") {
            try {
                System.out.print("waiting for user input\n>");
                in = r.readLine();
                String[] cmd = in.split(" ");

                

                if (cmd.length < 1) { // Check input
                    System.err.println(usage);
                } else {
                    switch (cmd[0]) {
                        case "push":
                            if (cmd.length < 2) { // Check input
                                System.err.println(usage);
                                break;
                            }
                            int val = Integer.parseInt(cmd[1]);
                            SM test = new SM('a', Integer.parseInt(cmd[1]), 0);
                            deque.push(test);
                            break;
                        case "put":
                            if (cmd.length < 2) { // Check input
                                System.err.println(usage);
                                break;
                            }
                            val = Integer.parseInt(cmd[1]);
                            test = new SM('a', Integer.parseInt(cmd[1]), 0);
                            deque.put(test);
                            break;
                        case "get":
                            int v = deque.get().n1;
                            System.out.println("Got value: " + v);
                        case "print":
                            break;
                        default:
                            System.out.println("unexpected operation!");
                            System.out.println(usage); // Unexpected input
                    }
                    // Print state of deque
                    System.out.println("state of deque:");
                    deque.traverse();
                    System.out.println("--------");
                }
            } catch (Exception err) { // Handle error
                err.printStackTrace();
                System.err.println(usage);
                System.out.println("--------");
            }
        }
    }
}
