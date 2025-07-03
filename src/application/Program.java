package application;

import model.entities.Product;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        String pathProject = "c:\\users\\alish\\ideaprojects\\csv-product-manager";
        File file = new File(pathProject + "\\products.csv");
        //cria nova pasta e arquivo
        boolean outFolder = new File(pathProject + "\\out").mkdir();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            // armazenei a leitura da linha em variavel str
            String products = bufferedReader.readLine();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathProject + "\\out\\summary.csv"));

            while(products != null) {
                //criei vetor para armazenar cada item (retirando a ,)
                String[] productVect = products.split(",");
                //le a prox linha
                products = bufferedReader.readLine();

                //extrai e converte em tipos
                String productName = productVect[0].trim();
                double productPrice = Double.parseDouble(productVect[1].trim());
                int productQuantity = Integer.parseInt(productVect[2].trim());

                //insere os parâmetros convertidos
                Product product = new Product(productName, productPrice, productQuantity);
                double totalPrice = product.calculatePrice();


                //insere nome do produto e valor total do produto

                bufferedWriter.write(productName + "," + totalPrice);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
}
