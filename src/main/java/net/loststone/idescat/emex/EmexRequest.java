package net.loststone.idescat.indicadors;

import java.util.ArrayList;
import java.util.List;

import net.loststone.idescat.AbstractRequest;
import net.loststone.idescat.InnerRequest;
import net.loststone.idescat.common.Format;
import net.loststone.idescat.InvalidUrlException;

/**
 * Petició a l'API d'emex
 * http://www.idescat.cat/api/emex/
 * 
 * Aquesta classe permet gestionar els paràmetres que s'utilitzaran a l'hora de fer la petició
 * específica a l'API d'emex
 * 
 * @author marc
 *
 */
public class EmexRequest extends AbstractRequest {

	public enum operacio { nodes, dades }
	
	// el servei és emex
	private final String servei = "emex";

	private int municipiId = -1; 

	private operacio op; 

	// lista d'indicadors a mostrar.
	private List<String> indicadors;
	
	
	public EmexRequest(InnerRequest innerRequest) {
		super(innerRequest);
		indicadors = new ArrayList<String>();
	}

	/**
	 * Defineix el municipi que utilitzarem.
	 */
	public void setMunicipiId(int id) {
		this.municipiId = id; 	
	}

	public int getMunicipiId() {
		return municipiId;	
	}

	@Override
	public String getUrl() throws InvalidUrlException {
		StringBuffer resultat = new StringBuffer();
		
		// crear la petició base amb els paràmetres comuns.
		this.inicialitzarParametres(resultat);
		
		// en cas de que sigui l'operacio dades mirem si hi ha els 
		// demes paràmetres.
		if (this.op == operacio.dades) {
			if (getMunicipiId() != -1) {
				throw new InvalidUrlException("L'operació 'dades' del mètode 'emex' necessita un camp 'id'");			
			}
			setConector(resultat,true);
			resultat.append("id=");
			resultat.append(getMunicipiId());
		}

		// mirar si hi ha una llista d'identificadors específics a retornar
		if (!indicadors.isEmpty()) {
			setConector(resultat, false);	
			resultat.append("i=");
			boolean first = true; 
			for (String _indicador : this.getIndicadors()) {
				if (!first) {
					resultat.append(",");
				}
				resultat.append(_indicador);
				first = false;
			}
		}
	
		// l'operació 'nodes' no té paràmetres, per tant, no cal mirar res. 	
		return resultat.toString();
	}

	public void setOperacio(operacio op) {
		this.op = op; 
	}
	
	public operacio getOperacio() {
		return op; 
	}

	@Override
	protected boolean formatPermes(Format format) {
		if ((format == Format.JSON) || (format == Format.PHP) || (format == Format.XML))
			return true;
		else 
			return false;
	}

	@Override
	protected String getOperacioString() {
		if (this.op != null)
			return this.op.toString();
		else 
			return null;
	}

	@Override
	protected String getServei() {
		return this.servei;
	}

	// TODO codi repetit posar-ho en una classe apart, junt amb el bucle
	// que els converteix en una llista separada amb comes i que tingui una 
	// classe genèrica
	/**
	 * Retorna la llista d'indicadors a utilitzar.
	 * @return
	 */
	public List<String> getIndicadors() {
		return indicadors;
	}

	/**
	 * Inserta una llista d'indicarods a mostrar.
	 * @param indicadors
	 */
	public void setIndicadors(List<String> indicadors) {
		this.indicadors = indicadors;
	}
	
	/**
	 * Afegeix un indicador a la llista d'indicadors que s'utilitzarï¿½ per parï¿½metre.
	 * @param indicador
	 */
	public void addIndicador(String indicador) {
		this.indicadors.add(indicador);
	}

	
}
