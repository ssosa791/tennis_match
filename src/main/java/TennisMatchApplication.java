package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TennisMatchApplication {

    public static void main(String args[]){
    	
    	//List con los puntajes posibles
    	List<String> points = Arrays.asList("0","15","30","40","v","win");
    	
    	//Points de jugador 1
    	int points1 = 0;
    	//Victorias de jugador 1
    	int wins1 = 0;
    	//Sets ganados por jugador 1
    	int sets1 = 0;
    	//List con los sets ganados por Jugador1
    	List<Integer> pointsList1 = new ArrayList<>();
    	//Probabilidad de victoria del jugador 1
    	int winrate1 = 0;
    	
    	//Points de jugador 2
    	int points2 = 0;
    	//Victorias de jugador 2
    	int wins2 = 0;
    	//Sets ganados por jugador 2
    	int sets2 = 0;
    	//List con los sets ganados por Jugador2
    	List<Integer> pointsList2 = new ArrayList<>();
    	//Probabilidad de victoria del jugador 2
    	int winrate2 = 0;
    	
    	//Punto temporal
    	int temp = 0;
    	
    	//Jugador que saca primero
    	int service = (Math.random() <= 0.5) ? 1 : 2;
    	
    	//Lectura de datos a traves scanner
    	Scanner in = new Scanner (System.in);
    	
    	String rematch = "n";
        
        System.out.println("Ingrese los datos correspondientes al partido:\n");
        
        System.out.println("Nombre Jugador 1:");
        String player1 = in.next();
        
        System.out.println("Nombre Jugador 2:");
        String player2 = in.next();
        
        System.out.println("Nombre del Torneo:");
        String tournament = in.next();
        
        int done = 0;
        int sets = 0;
        do {
	        System.out.println("Cantidad de sets (3 o 5):");
	        sets = in.nextInt();
	        
	        if (sets==3 || sets==5) {
	        	done=1;
	        } else {
	        	System.out.println("Solo puede escojer entre el mejor de 3 o el mejor de 5.");
        		System.out.println("Por favor, reingrese la correspondiente cantidad de sets!\n");
	        }
	        
        } while (done!=1);
        
        done = 0;
        do {
        	System.out.println("Probabilidad de que " + player1 + " gane (de 0 a 100):");
        	winrate1 = in.nextInt();
        
        	System.out.println("Probabilidad de que " + player2 + " gane (de 0 a 100):");
        	winrate2 = in.nextInt();
        	
        	if((winrate1+winrate2)!=100) {
        		System.out.println("La suma de las probabilidades no puede ser superior ni inferior a 100%");
        		System.out.println("Por favor, reingrese las correspondientes probabilidades!\n");
        		
        		done = 0;
        	} else {
        		done = 1;
        	}
        	
        } while (done!=1);
        
        //Rate que determina probabilidad del puntaje por juego.
        //Como la suma de las probabilidades es 100, podemos calcularlo utilizando
        //la probabilidad del Jugador 1 sobre 100.
        double rate = winrate1*0.01;
        System.out.println("WINRATE IS " + rate);
        
        //Variables de estado del partido.
        int game = 0;
        int set = 0;
        int match = 0;
        
        do {
	        //Calculo del resultado del partido.
	        for (match=0; match < sets; match++) {
		        //Calculo de victorias de sets.
		        do {
			        //Reinicio de variables para calculo de juegos.
		        	points1=0;
		        	points2=0;
		        	game=0;
		        	
		        	//Anuncio de quien tiene servicio.
		        	if (service == 1) {
		        		System.out.println("\nSaca " + player1 + "!");
		        	} else {
		        		System.out.println("\nSaca " + player2 + "!");
		        	}
		        	
		        	//Calculo de victorias de juegos.
			        do {
			        	//Calculo de punto en base al winrate del Jugador 1.
			        	//Si el winrate del Jugador 2 es 100, el winrate del Jugador 1 es 0.
			        	temp = (Math.random() <= rate) ? 1 : 2;
			        	
			        	if (temp == 1) {
			        		System.out.println("\nPunto para " + player1 +"!");
			        		points1++;
			        	} else {
			        		System.out.println("\nPunto para " + player2 +"!");
			        		points2++;
			        	}
			        	
			        	//La victoria se determina con el primer jugador que
			        	//anote luego de 40, teniendo al menos una diferencia
			        	//de 2 puntos.
			        	//Si ambos jugadores anotan 40 - 40
			        	//se pasa a la ventaja.
			        	//Si ambos jugadores empatan en 40 o ventaja,
			        	//se reinicia a 40 - 40 hasta que
			        	//se anote una diferencia de 2.
			        	if (points1 == 4) {
			        		if (points2 == 4) {
			        			points1--;
			        			points2--;
			        		} else if(points2<3){
			        			points1=5;
			        		}
			        	} else if (points2 == 4 && points1<3){
			        		points2=5;
			        	}
			        	
			        	if (points1 == 5) {
			        		wins1++;
			        		game=1;
			        	}
			        	
			        	if (points2 == 5) {
			        		wins2++;
			        		game=1;
			        	}
			        	
			        	System.out.println(player1 + " " + wins1 + " | " + points.get(points1));
			    		System.out.println(player2 + " " + wins2 + " | " + points.get(points2));
			        	
			    		//Un sleep para darle tiempo al usuario de seguir el match.
			        	//wait(1000);
			        	
			        } while (game!=1);
			        
			        //Cambio de servicio (Por si se juega otro set).
		        	if (service == 1) {
		        		service=2;
		        	} else {
		        		service=1;
		        	}
			        
			        //Un set se determina a los 6 juegos ganados
		        	//con una diferencia de al menos 2 puntos.
		        	//Si se empata 5 - 5 se tiene que jugar hasta
		        	//los 7 puntos.
		        	//si se empata 6 - 6, se pasa a muerte subdita.
		        	if (wins1 == 6) {
		        		if (wins2 < 5) {
		        			System.out.println("Set para " + player1 + "!");
		        			pointsList1.add(wins1);
		        			pointsList2.add(wins2);
		        			sets1++;
		        			set=1;
		        		}
		        	} else if (wins2 == 6) {
		        		if (wins1 < 5) {
		        			System.out.println("Set para " + player2 + "!");
		        			pointsList1.add(wins1);
		        			pointsList2.add(wins2);
		        			sets2++;
		        			set=1;
		        		}
		        	}
		        	
		        	if (wins1 == 7) {
		        		System.out.println("Set para " + player1 + "!");
		        		pointsList1.add(wins1);
	        			pointsList2.add(wins2);
		        		sets1++;
		        		set=1;
		        		
		        	} 
		        	if (wins2 == 7) {
		        		System.out.println("Set para " + player2 + "!");
		        		pointsList1.add(wins1);
	        			pointsList2.add(wins2);
		        		sets2++;
		        		set=1;
		        	}
		        	
		        } while (set!=1);
		        
		        //Set completado.
		        //Se muestra resultado parcial.
		        System.out.println("\nFIN DEL SET!");
		        System.out.println("\nFINAL - " + tournament);
		        System.out.println(player1 + " " + sets1 + " | " + pointsList1);
		        System.out.println(player2 + " " + sets2 + " | " + pointsList2);
		        
		        //Se reinician los contadores de victorias.
		        wins1=0;
		        wins2=0;
		        set=0;
		        
		        match=matchPoints(match, sets, sets1, sets2);
	        }
	        
	        //Partido finalizado.
	        System.out.println("\nFIN DEL PARTIDO!");
	        System.out.println("\nFINAL - " + tournament + " - Finalizado.");
	        System.out.println(player1 + " " + sets1 + " | " + pointsList1);
	        System.out.println(player2 + " " + sets2 + " | " + pointsList2);
	        
	        if(sets1>sets2) {
	        	System.out.println("\nGANADOR TORNEO " + tournament + ": " + player1 + "!");
	        } else {
	        	System.out.println("\nGANADOR TORNEO " + tournament + ": " + player2 + "!");
	        }
	        
	        done = 0;
	        do {
	        	System.out.println("\nDesea la revancha? (y/n)");
	        	rematch = in.next();
	        	
	        	if (!rematch.equals("y") && !rematch.equals("n")) {
	        		System.out.println("\nPor favor, reingrese una respuesta correcta!\n");
	        	} else {
	        		//Se reinician las variables para la revancha.
	                match = 0;
	                pointsList1.clear();
	                pointsList2.clear();
	                sets1 = 0;
	                sets2 = 0;
	        		done = 1;
	        	}
	        } while (done!=1);
        } while (rematch.equals("y"));
        
        //Cierre del scanner
        in.close();
        
        System.out.println("\n\nFIN DE LA SIMULACION!");
        
    }
    
    public static void wait(int ms){
    	try {
    		Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static int matchPoints(int match, int sets, int sets1, int sets2) {
    	if (sets==3) {
        	if(sets1==2) {
        		return sets;
        	} else if(sets2==2) {
        		return sets;
        	}
        } else {
        	if(sets1==3) {
        		return sets;
        	} else if(sets2==3) {
        		return sets;
        	}
        }
		return match;
    }
}
