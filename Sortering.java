package hvl.no.dat102;

import java.util.Random;

public class Sortering {
	

	// Utvalgssortering
	public static <T extends Comparable<T>> void utvalgsSortering(T[] data) {
		int minstePos;
		for (int i = 0; i < data.length - 1; i++) {
			minstePos = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j].compareTo(data[minstePos]) < 0) {
					minstePos = j;
				}
			}

			// byte om
			T tmp = data[i];
			
			data[i] = data[minstePos];
			
			data[minstePos] = tmp;
		}
	}
	//Sortering ved innsetting
	public static <T extends Comparable<T>> void sorteringVedInnsetting(T[] data) {
		for (int i = 1; i < data.length; i++) {
			T nokkel = data[i];
			int p = i;

			while (p > 0 && nokkel.compareTo(data[p - 1]) < 0) {
				data[p] = data[p - 1];
				p--;
			}

			data[p] = nokkel;
		}
	}
	private static <T> void swap(T[] tab, int i, int j) {
		T tmp = tab[i];
		tab[i] = tab[j];
		tab[j] = tmp;
	}

	private static <T extends Comparable<T>> int finnPartisjon(T[] data, int min, int maks) {
		T temp, pivot;
		int midten = (min + maks) / 2;
		pivot = data[midten];
		swap(data, midten, min); // bytter om f�rste og midtelementet

		int venstre = min + 1;
		int hoyre = maks;
		while (venstre < hoyre) {// ytre l�kke

			/** S�ker et element som er > enn pivot */
			/** Sikrer at partisjoneringsprosessen vil fortsette s� lenge venstre < hoyre */
			while (venstre < hoyre && data[venstre].compareTo(pivot) <= 0) {
				venstre++;
			}

			/** S�ker et element som er <= enn pivot */
			while (data[hoyre].compareTo(pivot) > 0) {
				hoyre--;
			}

			/** bytter elementene desom venstre er mindre enn hoyre */
			if (venstre < hoyre) {
				swap(data, venstre, hoyre);
			}

		} // while � ytre l�kke

		/** flytter pivot til riktig og sin endelige plass */

		swap(data, min, hoyre);

		return hoyre;

	}// metode
	
	//Kvikksortering
	public static <T extends Comparable<T>> void kvikkSort(T[] data, int min, int maks) {

		// basis: 0 eller 1 element -> gjer ingenting

		if (min < maks) { // minst to element
			int posPivot = finnPartisjon(data, min, maks);
			// sorter venstre del
			kvikkSort(data, min, posPivot - 1);
			// sorter h�gre del
			kvikkSort(data, posPivot + 1, maks);
		}
	}

	private static <T extends Comparable<T>> void flette(T[] tabell, int forste, int midten, int siste) {
		/*
		 * Fletter to sorterte deltabeller, tabell[forste,midten] og
		 * tabell[midten+1,siste] til en sortert tabell Forkrav: forste <= midten <=
		 * siste Deltabellene tabell[forste � midten] og tabell[midten+1 � siste] er
		 * hver sorterte i ikke- avtagende rekkef�lge.
		 * 
		 * ResultatTabell[forste � siste] er sortert.
		 * 
		 * Implementasjon : Denne metoden fletter to deltabeller til en hjelpetabell og
		 * kopierer resultatet til den originale tabellen.
		 */

		int storrelse = siste - forste + 1;
		T[] hjelpeTabell =  (T[]) (new Comparable[storrelse]);

		int forsteV = forste;
		int sisteV = midten;
		int forsteH = midten + 1;
		int sisteH = siste;

		// indeks i hjelp
		int h = 0;

		while (forsteV <= sisteV && forsteH <= sisteH) {
			if (tabell[forsteV].compareTo(tabell[forsteH]) <= 0) {
				hjelpeTabell[h] = tabell[forsteV];
				forsteV++;
			} else {
				hjelpeTabell[h] = tabell[forsteH];
				forsteH++;
			}
			h++;
		}

		// kopiere resten av venstre del (kan v�re tom)
		while (forsteV <= sisteV) {
			hjelpeTabell[h] = tabell[forsteV];
			forsteV++;
			h++;
		} // while

		// kopiere resten av h�yre del (kan v�re tom)
		while (forsteH <= sisteH) {
			hjelpeTabell[h] = tabell[forsteH];
			forsteH++;
			h++;
		} // while

		// Kopier resultatet tilbake til den originale tabellen
		h = 0;
		for (int indeks = forste; indeks <= siste; indeks++) {
			tabell[indeks] = hjelpeTabell[h];
			h++;
		}
	}// flette */

	public static <T extends Comparable<T>> void fletteSort(T[] tabell, int forste, int siste) {
		// basis: 1 element - > gjer ingenting
		if (forste < siste) { // minst to element
			int midten = (forste + siste) / 2;
			fletteSort(tabell, forste, midten);
			fletteSort(tabell, midten + 1, siste);
			flette(tabell, forste, midten, siste);
		}
	}

	public static void main(String[] args) {
		
		Random tilfeldig = new Random();
		int n = 32000;
		int antal = 10;

		Integer[][] a = new Integer[antal][n];{
			// set inn tilfeldige heiltal i alle rekker
			for (int i = 0; i < antal; i++) {
				System.out.println();
				for (int j = 0; j < n; j++) {
					a[i][j] = tilfeldig.nextInt();	
					System.out.print(a[j]);

				}
			}
		
	}
	}
}
	


