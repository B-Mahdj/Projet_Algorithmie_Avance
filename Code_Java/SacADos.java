
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*OPTIMISATION : CREER UNE CLASSE LISTE QUI GERE LISTE OBJETS*/

public class SacADos {
	final float POIDS_MAX_DEFAUT = 30;

	float poids_maximal;
	float poids_actuel;
	ContenuSac contenu_sac; // contenu choisi dans le sac
	ContenuSac contenu_liste; // contenu possible

	/*
	 * Constructeur de la classe qui génère un sac vide Par défaut le poids maximal
	 * portable par le sac a dos sera de 30
	 */
	public SacADos() {
		this.poids_actuel = 0;
		this.poids_maximal = POIDS_MAX_DEFAUT;
		this.contenu_sac = new ContenuSac();
		this.contenu_liste = new ContenuSac();
	}

	/*
	 * Constructeur qui génère un sac vide et stocke la liste des objets possibles
	 * ainsi que le poids maximal du sac
	 * 
	 * @param[String] : chemin du fichier contenant la liste des objets possibles
	 * 
	 * @param[float] : poids maximal du sac
	 */
	public SacADos(String chemin, float poids_maximal) {
		this();
		this.poids_maximal = poids_maximal;
		try {
			File fichier = new File(chemin);
			Scanner Reader = new Scanner(fichier);
			Reader.useDelimiter(" ; |\\n");
			while (Reader.hasNext()) {
				this.contenu_liste.nom_objet.add(Reader.next());
				this.contenu_liste.poids_objet.add(Float.parseFloat(Reader.next()));
				this.contenu_liste.valeur_objet.add(Float.parseFloat(Reader.next()));
			}
			Reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}
	}

	public String toString() {
		float somme=0;
		for(float valeur:this.contenu_sac.valeur_objet) {
			somme+=valeur;
		}
		String s = "Contenu du sac:\n\n";
		s += contenu_sac.toString();
		s += "\nPoids actuel : " + String.valueOf(this.poids_actuel);
		s += " / Poids Maximal : " + String.valueOf(this.poids_maximal);
		s += " / Valeur : " + String.valueOf(somme);
		s += "\n\n\n\n";
		return s;
	}

	public String contenu_liste() {
		String s = "Contenu de la liste:\n\n";
		s += contenu_liste.toString();
		s += "\nPoids actuel : " + String.valueOf(this.poids_actuel);
		s += " / Poids Maximal : " + String.valueOf(this.poids_maximal);
		s += "\n\n\n\n";
		return s;
	}

	/* Resolution du probleme du sac a dos via un algorithme glouton */
	public void resoudre_glouton() {
		int index[] = new int[this.contenu_liste.poids_objet.size()];
		float rapport[] = new float[this.contenu_liste.poids_objet.size()];
		for (int i = 0; i < rapport.length; i++) {
			index[i] = i;
			rapport[i] = (this.contenu_liste.valeur_objet.get(i) / this.contenu_liste.poids_objet.get(i));
		}
		int taille = rapport.length;
		float tmp = 0;
		int tmpInd = 0;
		for (int i = 0; i < taille; i++) {
			for (int j = 1; j < (taille - i); j++) {
				if (rapport[j - 1] < rapport[j]) {
					// echanges des elements de tab
					tmp = rapport[j - 1];
					rapport[j - 1] = rapport[j];
					rapport[j] = tmp;
					// echanges des elements de l'index
					tmpInd = index[j - 1];
					index[j - 1] = index[j];
					index[j] = tmpInd;
				}
			}
		}
		for (int valeur : index) {
			if (poids_actuel + contenu_liste.poids_objet.get(valeur) <= poids_maximal) {
				contenu_sac.nom_objet.add(contenu_liste.nom_objet.get(valeur));
				contenu_sac.valeur_objet.add(contenu_liste.valeur_objet.get(valeur));
				contenu_sac.poids_objet.add(contenu_liste.poids_objet.get(valeur));
				poids_actuel += contenu_liste.poids_objet.get(valeur);
			}
		}
	}

	public void prog_dynamique() {

		float matrice[][] = new float[this.contenu_liste.nom_objet.size()][(int) (this.poids_maximal + 1)];
		int i, j;
		for (j = 0; j < this.poids_maximal+1; j++) { // initialisation de la première ligne de la matrice
			if (contenu_liste.poids_objet.get(0) > j)
				matrice[0][j] = 0;
			else
				matrice[0][j] = contenu_liste.valeur_objet.get(0);
		}
		for (i = 1; i < this.contenu_liste.nom_objet.size(); i++) {
			for (j = 0; j < this.poids_maximal + 1; j++) {
				if (this.contenu_liste.poids_objet.get(i) > j)
					matrice[i][j] = matrice[i - 1][j];
				else
					matrice[i][j] = Math.max(matrice[i - 1][j],
							matrice[i - 1][(int) (j - (this.contenu_liste.poids_objet.get(i)))]
									+ this.contenu_liste.valeur_objet.get(i));
			}
		}
		i--;j--;
		
		while (matrice[i][j] == matrice[i][j-1]) {
			j--;
		}
		while (j > 0) {
			while (i > 0 && matrice[i][j] == matrice[i - 1][j]) {
				i--;
			}
			j = (int) (j - (this.contenu_liste.poids_objet.get(i)));
			if (j >= 0) {
				this.contenu_sac.nom_objet.add(this.contenu_liste.nom_objet.get(i));
				this.contenu_sac.poids_objet.add(this.contenu_liste.poids_objet.get(i));
				this.contenu_sac.valeur_objet.add(this.contenu_liste.valeur_objet.get(i));
				this.poids_actuel+=this.contenu_liste.poids_objet.get(i);
			}
			i--;
		}
	}
	
	public void pse() {
		ABR abr=new ABR();
		for(int i=0; i<this.contenu_liste.nom_objet.size();i++) {
			
		}
	}

	public static void main(String[] args) {
		SacADos s = new SacADos("D:/AAV/itemsEval.txt", 15);
		System.out.print(s.contenu_liste());
		s.prog_dynamique();
		System.out.print(s.toString());
	}
}