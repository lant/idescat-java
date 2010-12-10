package net.loststone.idescat.poblacions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.loststone.idescat.AbstractRequest;
import net.loststone.idescat.InnerRequest;
import net.loststone.idescat.InvalidUrlException;
import net.loststone.idescat.common.Format;

/**
 * Peticio a l'API de poblacions: 
 * http://www.idescat.cat/api/pob/
 * 
 * Aquesta classe permet gestionar els parametres que s'utilitzaran a l'hora de fer la peticio
 * especifica a l'API de poblacions. 
 * 
 * @author marc
 *
 */
public class PoblacionsRequest extends AbstractRequest {

	public enum operacio { cerca, sug }
	public enum tipus { cat, prov, com, mun, ec, es, np, dis}
	public enum order { tipus, nom }
	
	// el servei es poblacio
	private final String servei = "pob";

	// especifics del servei
	private String filtreQ = null;
	private ArrayList<tipus> filtresTipus = null;

	// especifics de l'operaico cerca
	private ArrayList<Integer> simList = null;
	boolean selec = false; 
	order orderBy = order.tipus; 
	int posicio = 0; 
	
	private operacio op; 
		
	public PoblacionsRequest(InnerRequest innerRequest) {
		super(innerRequest);
		filtresTipus = new ArrayList<tipus>();
		simList = new ArrayList<Integer>();
	}

	/**
	 * Inicialitza el filtre Q. 
	 * Selecciona entitats territorials que continguin la cadena de caracters especificada.
	 * Podeu trobar ms informacio a: http://www.idescat.cat/api/pob/#a1.2.1.1
	 * @param q
	 */
	public void setFiltreQ(String q) {
		this.filtreQ = q;
	}
	
	public String getFiltreQ() {
		return this.filtreQ;
	}
	
	/**
	 * El filtre tipus (especifica els tipus de poblacio que volem) pot ser multiple, per tant
	 * aquesta operacio afegeix els tipus sempre i quant no estiguin repetits. 
	 * Podeu trobar mes informacie a: http://www.idescat.cat/api/pob/#a1.2.1.2
	 * @param filtre
	 */
	public void afegirFiltreTipus(tipus filtre) {
		if (!this.filtresTipus.contains(filtre)) {
			this.filtresTipus.add(filtre);
		}
	}
	
	/**
	 * Retorna els filtres tipus afegits en format de llista.
	 * @return
	 */
	public List<tipus> getFiltreTipus() {
		return this.filtresTipus;
	}
	
	/**
	 * Afegeix un parametre sim a la llista sempre i quan el numero sigui valid (0,1 o 2) i
	 * no estigui ja a la llista.
	 * Podeu trobar mes informacio a: http://www.idescat.cat/api/pob/#a1.2.2.1
	 */
	public void afegirSim(Integer sim) {
		if ( (sim >= 0) && (sim <= 2) && !simList.contains(sim)) {
			simList.add(sim);
		}
	}
	
	public List<Integer> getSim() {
		return this.simList;
	}
	
	/**
	 * Especifica el parametre selec. Si el bolea es 1 la resposta nomes incloura un resultat, en cas de que 
	 * sigui 0, incloura tota la llista de resultats.
	 * Podeu trobar mes informacio a: http://www.idescat.cat/api/pob/#a1.2.2.2
	 * @param selec
	 */
	public void setSelec(boolean selec) {
		this.selec = selec;
	}
	
	public boolean getSelec() {
		return this.selec;
	}
	
	/**
	 * Selecciona si els resultats tornen ordenats per tipus d'entitat o be per ordre alfabetic.
	 */
	public void setOrderBy(order t) {
		this.orderBy = t;
	}
	
	/**
	 * Especifica la posicio per la qual comencaran els resultats.
	 * Podeu trobar mes informacio a: http://www.idescat.cat/api/pob/#a1.2.2.4
	 */
	public void setPosicio(int posicio) {
		if (posicio >= 0)
			this.posicio = posicio;
	}
	
	@Override
	protected boolean formatPermes(Format format) {
		// aquesta operacio permet tots els formats.
		return true;
	}

	/**
	 * Indica quina operacio volem utilitzar.
	 * @param op
	 */
	public void setOperacio(operacio op) {
		this.op = op;
	}

	/**
	 * Retorna l'operacio seleccionada.
	 * @return
	 */
	public operacio getOperacio() {
		return op;
	}
	
	public String getServei() {
		return servei;
	}
	
	public String getOperacioString() {
		return this.op.toString();
	}
	
	@Override
	public String getUrl() throws InvalidUrlException {
		boolean setFirstParam = true;
		StringBuffer resultat = new StringBuffer();
		
		// crear la peticio base amb els parametres comuns.
		this.inicialitzarParametres(resultat);
		
		// posem el fitreQ en cas de que hi sigui.
		if (filtreQ != null) {
			setConector(resultat, setFirstParam);
			setFirstParam = false;
			resultat.append("q=").append(this.filtreQ);
		}
		
		// posem la llista de tipus, en cas de que hi sigui.
		if (!filtresTipus.isEmpty()) {
			setConector(resultat, setFirstParam);
			setFirstParam = false;
			resultat.append("tipus=");
			Iterator<tipus> it = this.filtresTipus.iterator();
			while (it.hasNext()) {
				resultat.append(it.next().toString());
				if (it.hasNext()) {
					resultat.append(",");
				}
			}
		}
		
		// afegim els parametres expecifics de l'operacio cerca.
		if (this.op == operacio.cerca ) {
		
			// parametre sim.
			if (!getSim().isEmpty()) {
				setConector(resultat, setFirstParam);
				setFirstParam = false;
				resultat.append("sim=");
				Iterator<Integer> it = this.getSim().iterator();
				while (it.hasNext()) {
					resultat.append(it.next());
					if (it.hasNext()) {
						resultat.append(",");
					}
				}
			}
			
			// parametre selec
			setConector(resultat, setFirstParam);
			setFirstParam = false;
			resultat.append("selec=");
			if (selec) {
				resultat.append("1");
			} else {
				resultat.append("0");
			}
			
			// parametre orderBy
			setConector(resultat, setFirstParam);
			setFirstParam = false;
			resultat.append("orderby=");
			resultat.append(orderBy.toString());
			
			// parametre posicio
			setConector(resultat, setFirstParam);
			setFirstParam = false;
			resultat.append("posicio=");
			resultat.append(posicio);
		}
		
		return resultat.toString();
	}
	
}
