package application;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Program {

    public static void main( String[] args ) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Product> list = new ArrayList<>();

        System.out.print("Entre com o número de produtos: ");
        int n = sc.nextInt();

        for (int i=1; i<=n; i++) {
            System.out.println("Produto #" + i + " Dados:");
            System.out.print("Comum, usado ou importado (c/u/i)? ");
            char ch = sc.next().charAt(0);
            System.out.print("Nome: ");
            sc.nextLine();
            String name = sc.next();
            System.out.print("Preço: ");
            double price = sc.nextDouble();
            if (ch == 'i') {
                System.out.print("Preço da importação: ");
                double customsFee = sc.nextDouble();
                list.add(new ImportedProduct(name, price,customsFee));
            }
            else if (ch == 'u') {
                System.out.print("Data de fabricação: (DD/MM/YYYY): ");
                LocalDate date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                list.add(new UsedProduct(name, price, date));
            } 
            else {
                list.add(new Product(name, price));
            }
        }

        System.out.println();
        System.out.println("Nota fiscal: ");
        for (Product product : list) {
            System.out.println(product.pricetag());
        }
        sc.close();
    }
}
