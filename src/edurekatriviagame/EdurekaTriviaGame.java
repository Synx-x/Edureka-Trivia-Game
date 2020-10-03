package edurekatriviagame;

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EdurekaTriviaGame {

    public static void main(String[] args) {
        int userInput = 0;

        try {

            DataOutputStream toServer = null;
            DataInputStream fromServer = null;
            String str = "";

            Scanner input = new Scanner(System.in);
            String clientAnswer = null;
            Socket s = new Socket("192.168.8.141", 8000);  // establishes socket connection to server

//input stream to receive data from the server
            fromServer = new DataInputStream(s.getInputStream());

//output stream to send data to the server
            toServer = new DataOutputStream(s.getOutputStream());

// reads the greeting from the server
            String greeting = (String) fromServer.readUTF();

//displays the greeting from the server
            System.out.println(greeting);

            //reads the  question from the server
            String question = (String) fromServer.readUTF();
            //displays the question from the server
            System.out.println(question);

//gets the users input
            userInput = input.nextInt();

//sends the user input to the server
            toServer.writeInt(userInput);

//displays the correct answer to the user from the server
            String correctAnswer = (String) fromServer.readUTF();
            System.out.println(correctAnswer);

            s.close();  // closes socket connection
        } catch (InputMismatchException e) {
            System.out.println("You have entered an incorrect input type, please use INTEGER NUMBERS only.");
        } catch (Exception e) {
            System.out.println(" ERROR or Right click the server class and run from that file first then the client (shift + F6)");
        }

    }
}
