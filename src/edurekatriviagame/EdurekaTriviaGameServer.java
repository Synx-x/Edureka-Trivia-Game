package edurekatriviagame;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class EdurekaTriviaGameServer {

    public static void main(String[] args) throws Exception {
        String operator = null;

        ServerSocket ss = new ServerSocket(8000); //creates a socket and listens until it recieves a terminate call 

        System.out.println("server is starting...."); // once server has started this message is displayed

        //the range for the random number generator
        int highestNum = 10;
        int lowestNum = 1;

        //generates 2 random numbers
        int randNum1 = (int) (Math.random() * highestNum) + lowestNum;
        int randNum2 = (int) (Math.random() * highestNum) + lowestNum;

        //creates an array that stores 4 operators
        String[] array = new String[4];

        //stores the operators in the array
        array[0] = "+";
        array[1] = "-";
        array[2] = "/";
        array[3] = "*";

        //converts the array to a list
        List<String> list = Arrays.asList(array);

        //randomises the operators stored in the list
        int len = list.size();
        for (int i = 0; i < array.length; i++) {
            int index = new Random().nextInt(len);
            String shuffle = list.get(index);
            operator = shuffle;
        }

        String result = "";
        int answer = 0;
        //generates a problem for the user
        if (operator == "+") {

            result = randNum1 + "+" + randNum2;
            answer = randNum1 + randNum2;
            if (operator == "-") {

                result = randNum1 + "-" + randNum2;
                answer = randNum1 - randNum2;
            }
            if (operator == "/") {

                result = randNum1 + "/" + randNum2;
                answer = randNum1 / randNum2;
            }
            if (operator == "*") {
                result = randNum1 + "*" + randNum2;
                answer = randNum1 * randNum2;
            }
        }

        Socket s = ss.accept();//establishes connection 

        // Create data input and output streams
        DataInputStream input = new DataInputStream(s.getInputStream());
        DataOutputStream output = new DataOutputStream(s.getOutputStream());

        // sends welcome message to client
        output.writeUTF("Welcome to Edureka Trivia Game");

        //sends question to the client
        output.writeUTF("Question from the Server: " + "What is " + result);

        //recieves answer from the clientr
        int clientAnswer = (int) input.readInt();
        System.out.println("Answer from Client: " + clientAnswer);

        if (answer == clientAnswer) {
            //sends result of answer back to the client
            output.writeUTF("Welldone, that's correct!");

        } else {
            //sends result of answer back to client if it is incorrect
            output.writeUTF("Wrong Answer!, the correct answer is: " + answer);
        }

        ss.close();  // terminates the server socket if client sends exit

    }
}
