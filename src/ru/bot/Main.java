package ru.bot;

import ru.bot.logic.OutputWriter;
import ru.bot.logic.Request;
import ru.bot.logic.RequestHanger;
import ru.bot.logic.Response;
import ru.bot.platforms.Bot;
import ru.bot.platforms.InputReader;

import java.util.Scanner;

class ConsoleBot implements Bot {
    public void startBot(){
        ConsoleInputReader input =new ConsoleInputReader();
        ConsoleOutputWriter output = new ConsoleOutputWriter();
        ConsoleRequestHanger hanger = new ConsoleRequestHanger();

        System.out.println("Hello");
        while(true){
            Request request=input.read();
            hanger.handle(request, output);

        }
    }
}

class ConsoleInputReader implements InputReader {
    public Request read(){
        Scanner  scanner = new Scanner(System.in);
        String user_input = scanner.nextLine();
        return new Request(user_input);
    }
}

class ConsoleOutputWriter implements OutputWriter{
    public void write(Response response){
        System.out.println(response.getMessage());
    }
}

class ConsoleRequestHanger implements RequestHanger {
    public void handle(Request request, OutputWriter writer){
        Response response = new Response("You typed: " + request.getMessage());
        writer.write(response);
    }
}
public class Main {
    public static void main(String[] args){
        ConsoleBot bot =new ConsoleBot();
        bot.startBot();
    }
}
