package application;

import model.entities.Product;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

        List<Product> productsList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            // armazenei a leitura da linha em variavel str
            String products = bufferedReader.readLine();

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
                productsList.add(new Product(productName, productPrice, productQuantity));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathProject + "\\out\\summary.csv"))) {
            //insere nome do produto e valor total do produto
            for (Product product : productsList) {
                bufferedWriter.write(product.getName() + "," + product.calculatePrice());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file");;
        }


    }
}
