
import java.util.ArrayList;

/*Structure de données qui permet de stocker les informations sur les objets
 * qui deviendront le contenu du sac*/
public class ContenuSac {
    public ArrayList<String> nom_objet; 
    public ArrayList<Float> poids_objet;
    public ArrayList<Float> valeur_objet;
    
    public ContenuSac() {
        this.nom_objet = new ArrayList<String>();
        this.poids_objet = new ArrayList<Float>();
        this.valeur_objet = new ArrayList<Float>();
    }
    
    public String toString() {
        String s = "";
        for(int i=0; i<nom_objet.size() && i<poids_objet.size() && i < valeur_objet.size(); i++) {
            s += nom_objet.get(i);
            s += " ; ";
            s += String.valueOf(poids_objet.get(i));
            s += " ; ";
            s += String.valueOf(valeur_objet.get(i));
            s += "\n";
        }
        return s;
    }
    
    public ArrayList<Float> getPoids_objet() {
        return this.poids_objet;
    }
    
    public ArrayList<Float> getValeur_objet() {
        return this.valeur_objet;
    }
}
