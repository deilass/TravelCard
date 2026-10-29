/**
* LAB 7 E LAB 8 DA SEMANA 4 DE FUNDAMENTOS DE PROGRAMAÇÃO
* TRABALHO REALIZADO POR: DAVID LUCERO / GONÇALO CARDIM
* CONFORME O CÓDIGO FOI SENDO ESCRITO E REESCRITO FOI-SE ADICIONANDO COMENTÁRIOS DE FORMA A IDENTIFICAR O QUE 
* PERTENCE AO LAB 7 OU AO LAB 8
*/

import java.util.Scanner;
public class TravelCard {
    private double saldo;
    private boolean inicializado;
    private boolean emUso;
    private int numViagens;
    private String transporte;
    private double desconto;
    //LAB8 NIVEL1
    private int metroTrips;
    private int trainTrips;
    private int busTrips;
    private int ferryTrips;

    //LAB7 NIVEL1
    public TravelCard(){
        this.saldo=0;
        this.inicializado=false;
        this.emUso=false;
        this.numViagens=0;
        this.transporte="N/A";
        this.desconto=0;
    }

    public void showInfo(){
        System.out.println("*** INFORMAÇÃO NO CARTÃO ***");
        System.out.printf("%-20s : %.2f€", "Saldo", saldo);
        System.out.printf("\n%-20s : %.0f%%", "Desconto", (desconto*100));
        System.out.printf("\n%-20s : %s", "Inicializado", (inicializado ? "SIM" : "NÃO"));
        System.out.printf("\n%-20s : %d", "Total de viagens", numViagens);
        System.out.printf("\n%-20s : %s", "Em utilização", (emUso ? "SIM" : "NÃO"));
        if (emUso){
            System.out.printf("\n%-20s : %s", "Transporte", transporte);
        }
    }
    
    public void menuCard(){    
        //LAB8 NIVEL1
        System.out.println("\n***** MENU *****");
        System.out.println("1 - Carregar Cartão");
        System.out.println("2 - Iniciar Viagem");
        System.out.println("3 - Finalizar Viagem");
        System.out.println("4 - Estatísticas");
        System.out.println("5 - Sari");
        int choice = 0;
        Scanner menu = new Scanner(System.in);
        choice=menu.nextInt();
        switch(choice){
            case(1):
            double valor = 0;
            Scanner saldo = new Scanner(System.in);
            System.out.println("Com quanto pretende carregar o seu cartão?");
            valor=saldo.nextDouble();
            recharge(valor);
            menu.close();
            break;
            case(2): 
            beginTrip();
            menu.close();
            break;
            case(3):
            endTrip();
            menu.close();
            break;
            case(4):
            showStatistics();
            menu.close();
            break;
            case(5):
            System.out.println("Obrigado pela sua confiança!");
            menu.close();
            break;
        }
    }
    
    //LAB7 NIVEL2
    public void initialize(double saldo){
        if (saldo<10){
            System.out.println("O valor mínimo para efetuar um carregamento é 10€");
        }
        else if (saldo >= 10 && !inicializado){
            this.saldo=saldo;
            this.inicializado=true;
            showInfo();
        }
        else {
            System.out.println("O Cartão eletrónico só pode ser inicializado uma única vez.");
        }
    }

    public double getTripPrice(char transporte){
        double preco;
        switch(transporte){
            case('M'): preco = 0.9; break;
            case ('T'): preco = 2.3; break;
            case ('B'): preco = 1.9; break;
            case ('F'): preco = 0.85; break;
            default: return -1;
        }
        return preco * (1 - desconto);
    }

    //LAB8 NIVEL2
    public void showStatistics(){
        System.out.println("*** ESTATÍSTICAS DE UTILIZAÇÃO ***");
        System.out.printf("%-30s : %d", "Metro", metroTrips);
        System.out.printf("\n%-30s : %d", "Comboio", trainTrips);
        System.out.printf("\n%-30s : %d", "Autocarro", busTrips);
        System.out.printf("\n%-30s : %d", "Ferry", ferryTrips);
        System.out.printf("\n%-30s : %d", "Número total de viagens", numViagens);
    }

    //LAB7 NIVEL3
    public void beginTrip(){
        Scanner entry = new Scanner(System.in);
        char trip = '\0';
        do {
            System.out.println("Insira a sigla do transporte: (M, T, B, F)");
            trip = entry.next().toUpperCase().charAt(0);
            if (trip != 'M' && trip != 'T' && trip != 'B' && trip != 'F'){
                System.out.println("O TRANSPORTE DEVE SER VÁLIDO (M, T, B, F");
            }
            else if(!inicializado){
                System.out.println("Antes de poder comprar um bilhete, o cartão deve ser inicializado primeiro.");
            }
            else if(inicializado){
                if(emUso){
                    System.out.println("O cartão ainda está a ser utilizado, verifique se validou a sua saída no transporte anterior");
                }
                else{
                    switch(trip){
                        case('M'):
                        double precoMetro = getTripPrice(trip);
                        if (this.saldo < precoMetro){
                            System.out.println("Saldo insuficiente! Deve ter um saldo igual ou superior ao valor do bilhete.");
                        }
                        else{
                            this.saldo=this.saldo-precoMetro;
                            this.transporte="Metro";
                            this.emUso=true;
                            showInfo();
                            entry.close();
                        }
                        break;
                        case('T'):
                        double precoComboio = getTripPrice(trip);
                        if (this.saldo < precoComboio){
                            System.out.println("Saldo insuficiente! Deve ter um saldo igual ou superior ao valor do bilhete.");
                        }
                        else{
                            this.saldo=this.saldo-precoComboio;
                            this.transporte="Train";
                            this.emUso=true;
                            showInfo();
                            entry.close();
                        }
                        break;
                        case('B'):
                        double precoAutocarro = getTripPrice(trip);
                        if (this.saldo < precoAutocarro){
                            System.out.println("Saldo insuficiente! Deve ter um saldo igual ou superior ao valor do bilhete.");
                        }
                        else{
                            this.saldo=this.saldo-precoAutocarro;
                            this.transporte="Bus";
                            this.emUso=true;
                            showInfo();
                            entry.close();
                        }
                        break;
                        case('F'):
                        double precoFerry = getTripPrice(trip);
                        if (this.saldo < precoFerry){
                            System.out.println("Saldo insuficiente! Deve ter um saldo igual ou superior ao valor do bilhete.");
                        }
                        else{
                            this.saldo=this.saldo-precoFerry;
                            this.transporte="Ferry";
                            this.emUso=true;
                            showInfo();
                            entry.close();
                        }
                        break;
                    }
                }
            }
            
        }while (trip != 'M' && trip != 'T' && trip != 'B' && trip != 'F');
    }

    //LAB8 NIVEL3
    public void showTripStatistics(){
        char transporte = '0';
        System.out.println("******* ESTATÍSTICAS DE TRANSPORTE *******\nESCOLHA O TRANSPORTE QUE DESEJA SABER AS ESTATÍSTICAS (M, T, B, F)");
        Scanner entry = new Scanner(System.in);
        transporte = entry.next().toUpperCase().charAt(0);
        if (transporte != 'M' && transporte != 'T' && transporte != 'B' && transporte != 'F'){
            System.out.println("ERRO: TRANSPORTE INVÁLIDO");
        }
        else{
            switch(transporte){
                case('M'):
                System.out.println("*** ESTATÍSTICAS DE UTILIZAÇÃO DO METRO ***");
                System.out.println("\nNo total foram realizadas " + metroTrips + " viagens de metro");
                /*LAB8 NIVEL5*/ System.out.println("Valor gasto no total em viagens de metro = " + metroTrips*0.9);
                System.out.println("\n*********************************************");
                break;
                case('T'):
                System.out.println("*** ESTATÍSTICAS DE UTILIZAÇÃO DO COMBOIO ***");
                System.out.println("\nNo total foram realizadas " + trainTrips + " viagens de comboio");
                /*LAB8 NIVEL5*/ System.out.println("Valor gasto no total em viagens de comboio = " + trainTrips*2.3);
                System.out.println("\n*********************************************");
                break;
                case('B'):
                System.out.println("*** ESTATÍSTICAS DE UTILIZAÇÃO DO AUTOCARRO ***");
                System.out.println("\nNo total foram realizadas " + busTrips + " viagens de autocarro");
                /*LAB8 NIVEL5*/ System.out.println("Valor gasto no total em viagens de autocarro = " + busTrips*1.9);
                System.out.println("\n*********************************************");
                break;
                case('F'):
                System.out.println("*** ESTATÍSTICAS DE UTILIZAÇÃO DO FERRY ***");
                System.out.println("\nNo total foram realizadas " + ferryTrips + " viagens de ferry");
                /*LAB8 NIVEL5*/ System.out.println("Valor gasto no total em viagens de ferry = " + ferryTrips*0.85);
                System.out.println("\n*********************************************");
                break;
            }
            entry.close();
        }
    }

    //LAB7 NIVEL4
    public void endTrip(){
        if(!inicializado){
            System.out.println("O cartão eletrónico deverá estar inicializado");
        }
        else if(inicializado){
            if(!emUso){
                System.out.println("O cartão eletrónico deverá estar em utilização");
            }
            //LAB8 NIVEL1
            else{
                if(transporte=="Metro"){
                    metroTrips++;
                    numViagens++;
                    emUso=false;
                    transporte="";
                    showInfo();
                }
                if(transporte=="Train"){
                    trainTrips++;
                    numViagens++;
                    emUso=false;
                    transporte="";
                    showInfo();
                }
                if(transporte=="Bus"){
                    busTrips++;
                    numViagens++;
                    emUso=false;
                    transporte="";
                    showInfo();
                }
                if(transporte=="Ferry"){
                    ferryTrips++;
                    numViagens++;
                    emUso=false;
                    transporte="";
                    showInfo();
                }
            }
        }
    }

    //LAB8 NIVEL4
    public void optionsMenu(){
        System.out.println("********************** MENU DE OPÇOES ***********************");
        System.out.println("\n> DIGITE 1 PARA VER OS DADOS DE TODAS AS VIAGENS");
        System.out.println("> DIGITE 2 PARA VER OS DADOS DE UM TIPO DE VIAGEM ESPECÍFICA");
        System.out.println("> DIGITE 99 PARA TERMINAR O PROGRAMA");
        System.out.println("\n> SUA OPÇAO: ");
        int choice = 0;
        Scanner entry = new Scanner(System.in);
        choice = entry.nextInt();
        switch(choice){
            case(1):
            showStatistics();
            break;
            case(2):
            showTripStatistics();
            break;
            case(99): 
            entry.close();
            break;
        }
        
    }

    //LAB7 NIVEL5
    public void showAvailableTrips(){
        if(!inicializado){
            System.out.println("O Cartão eletrónico tem de estar inicializado");
        }
        else if (inicializado){
            if(emUso){
                System.out.println("O Cartão eletrónico não pode estar a ser utilizado numa viagem (o utilizador validou o inicio de viagem, mas ainda não validou o fim da mesma).");
            }

            else{
                System.out.println("*** VIAGENS DISPONÍVEIS ***");
                System.out.printf("%-20s : %d%n", "Metro", (int)(saldo/getTripPrice('M')));
                System.out.printf("%-20s : %d%n", "Comboio", (int)(saldo/getTripPrice('T')));
                System.out.printf("%-20s : %d%n", "Autocarro", (int)(saldo/getTripPrice('B')));
                System.out.printf("%-20s : %d%n", "Ferry", (int)(saldo/getTripPrice('F')));
            }
        }
    }

    public void recharge(double recarregar){
        if (!inicializado){
            System.out.println("O cartão deve ser inicializado primeiro");
        }
        else if (inicializado){
            if (recarregar<10.00){
                System.out.println("O valor mínimo para recarregar é de 10.00€");
            }
            else if (recarregar>=10.00) {
                this.saldo=this.saldo+recarregar;
            }
        }
    }

    public void getDiscount(){
        if(!inicializado){
            System.out.println("O cartão precisa de ser inicializado primeiro");
        }
        if (inicializado){
            if (numViagens>=10 && numViagens<20){
                this.desconto=0.02;
                System.out.println("Parabéns foi-lhe atribuído um desconto de 2%");
            }
            else if (numViagens>=20 && numViagens<50){
                this.desconto=0.03;
                System.out.println("Parabéns foi-lhe atribuído um desconto de 3%");
            }
            else if (numViagens>=50 && numViagens<100){
                this.desconto=0.04;
                System.out.println("Parabéns foi-lhe atribuído um desconto de 4%");
            }
            else if (numViagens>=100){
                this.desconto=0.05;
                System.out.println("Parabéns foi-lhe atribuído um desconto de 5%");
            }
            else {
                System.out.println("Pedimos desculpa. Não é elegível a desconto.");
            }
        }
    }
}
