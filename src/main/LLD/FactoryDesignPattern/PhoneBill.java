package FactoryDesignPattern;

import java.util.Scanner;

public class PhoneBill {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of network :");
        String network = sc.nextLine();
        SelectNetworkFactory selectNetworkFactory = new SelectNetworkFactory();

        CellularPlan cp = selectNetworkFactory.getPlan(network);
        cp.getRate();
        System.out.println("Enter number of minutes");
        int min = Integer.parseInt(sc.nextLine());
       cp.processBill(min);
    }
}
