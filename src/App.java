import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {

    private static int x = 0;
    private static int y = 0;
    private static int x2 = 0;
    private static int y2 = 0;

    //Criação do HTML
    private static String start = "<!DOCTYPE html><html><body><svg height=\"1000\" width=\"1000\">";
    private static String end = "</svg></body></html>";
    private static String middle = "";

    public static void main(String[] args) {

        menu();
        results();
    }

    private static void menu() {

        System.out.println("Bem-vindo ao gerador de gráficos SVG! ");
        System.out.println("Para visualizar os resultados, verifique o arquivo de saída 'Grafico.html'");
        System.out.println("\nAgora, digite um número para os gráficos serem gerados: ");
    }


    private static void createSVG() {

        Path path = Paths.get("Grafico.html");
        String finalString = start + middle + end;

        byte[] bytes = finalString.getBytes();

        try {

            Files.write(path, bytes);

        } catch (Exception e) {
            
            System.out.println("Um erro ocorreu" + e);
        }
    
    }

    private static String resultSVG(String st) {

        String lastString = "";
        for (char i : st.toCharArray()) {

            if (i == 'A') {
                lastString += "AB";
            } else {
                lastString += "A";
            }
        }

        return lastString;
    }

    private static void generateSVG(String st) {

        for (char i : st.toCharArray()) {
            if (i == 'A') {
                x = y2;
                y = x2;
                x2 += 1;
                y2 += 1;
                circleTwo();
            } else {
                x = y2;
                y = x2;
                x2 += 10;
                y2 += 10;
                circleOne();
            }
        }
    }

    private static void circleOne() {
        String result = "<Circle ";
        result += "cx=\"" + x + "\" ";
        result += "cy=\"" + y2 + "\" ";
        result += "r=\"" + y + "\" ";
        result += "style=\"stroke:rgb(0,191,255);stroke-width:1\" fill=\"none\" />\"";
        middle += result;
        
    }

    private static void circleTwo() {

        String result = "<Circle ";
        result += "cx=\"" + x + "\" ";
        result += "cy=\"" + x2 + "\" ";
        result += "r=\"" + x + "\" ";
        result += "style=\"stroke:rgb(218,112,214);stroke-width:5\" fill=\"none\" />\"";
        middle += result;
    }

    private static void results(){

        Scanner scanner = new Scanner(System.in);

        String initial = "A";

        int result = scanner.nextInt();

        for (int i = 0; i < result; i++) {

            generateSVG(initial);
            initial = resultSVG(initial);
        }

        createSVG();
        System.out.println("\n\n\nArquivo gerado, verifique a saída ヾ(•ω•`)o");

        scanner.close();
    }
}
