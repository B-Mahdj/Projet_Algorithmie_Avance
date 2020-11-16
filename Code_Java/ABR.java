import java.util.ArrayList;

public class ABR {
	ArrayList<Integer> racine;
	ABR brancheGauche;
	ABR brancheDroite;
	
	public ABR(int racine) {
		this.racine.add(racine);
		this.brancheGauche.racine=null;
	}
	
	public ABR() {
		this.racine=null;
	}
	
	public void setBrancheDroite(ABR droit) {
		this.brancheDroite=droit;
	}
	
}
