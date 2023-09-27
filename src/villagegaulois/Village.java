package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbrEtalsMax) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbrEtalsMax);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom()+" cherche un endroit pour vendre "+nbProduit+" "+produit+".\n");
		int numEtal = marche.trouverEtalLibre()+1;
		
		marche.utiliserEtal(numEtal, vendeur, produit, nbProduit);
		chaine.append("Le vendeur "+vendeur.getNom()+" vend des "+produit+" à l'étal n°"+numEtal+".\n");
		
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder text = new StringBuilder();
		text.append("Les vendeurs qui proposent des " + produit+ " sont :\n");
		
		for (int i = 0; i < marche.getEtals().length; i++) {
			if (marche.etals[i].contientProduit(produit)) {
				
			}
			
		}
		
		return text.toString();	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village "+nom+" possède plusieurs étals :\n");
		chaine.append(marche.afficherMarche());
		
		return chaine.toString();
	}
	
	private static class Marche {
		private Etal[] etals;
		private int nbEtals = 0;

		public Marche(int nbrEtals) {
			super();
			this.etals = new Etal[nbrEtals-1];
			for (int i = 0; i < etals.length; i++) {
				etals[i]= new Etal();
			}
		}
		
		/**
		 * @return the etals
		 */
		public Etal[] getEtals() {
			return etals;
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			nbEtals++;
		}
		
		private int trouverEtalLibre() {
			int indiceEtal= -1;
			boolean trouver =false;
			
			for (int i = 0; (i < etals.length) && (trouver==false); i++) {
				if (etals[i]==null) {
					indiceEtal=i;
					trouver=true;
				}
			}
			return indiceEtal;		
		}
		
		private  Etal[] trouverEtals(String produit) {
			int nbrEtalsProduits=0;			
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nbrEtalsProduits+=1;
				}
			}
			
			Etal[] etalsProduits = new Etal[nbrEtalsProduits-1];
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduits[i]=etals[i];
				}
			}			
			return etalsProduits;
			
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			Etal vendeurTrouver = null;
			
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur().equals(gaulois)) {
					vendeurTrouver=etals[i];
				}
			}
			return vendeurTrouver;
		}
		
		private String afficherMarche() {
			StringBuilder listeEtal = new StringBuilder();
			int nbrEtalVide=0;
			
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()==false) {
					nbrEtalVide++;
				}
				else {
					listeEtal.append(etals[i].afficherEtal());
				}
			}
			listeEtal.append("Il reste "+nbrEtalVide+" étals non utilsés dans le marché.");
			return listeEtal.toString();
			}	
		}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
