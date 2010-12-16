package net.loststone.idescat.indicadors;

import java.util.ArrayList;
import java.util.List;

import net.loststone.idescat.AbstractRequest;
import net.loststone.idescat.InnerRequest;
import net.loststone.idescat.InvalidUrlException;
import net.loststone.idescat.common.Format;

/**
 * Peticio a l'API d'indicadors: 
 * http://www.idescat.cat/api/indicadors/
 * 
 * Aquesta classe permet gestionar els parametres que s'utilitzaran a l'hora de fer la peticio
 * especifica a l'API d'indicadors. 
 * 
 * @author marc
 *
 */
public class IndicadorsRequest extends AbstractRequest {

	public static enum operacio { nodes, dades }
	
	// el servei es indicadors.
	private final String servei = "indicadors";

	// lista d'indicadors a mostrar.
	private List<Integer> indicadors;
	// numero maxim d'indicadors que volem.
	private int max=6; 
	// numero minim d'indicadors que volem.
	private int min=6; 
	// temps transcorregut des de la seva publicacio.
	private int tt=0; 
	private operacio op; 
	
	
	public IndicadorsRequest(InnerRequest innerRequest) {
		super(innerRequest);
		indicadors = new ArrayList<Integer>();
	}

	@Override
	public String getUrl() throws InvalidUrlException {
		StringBuffer resultat = new StringBuffer();
		
		// crear la peticio base amb els parametres comuns.
		this.inicialitzarParametres(resultat);
		
		// parametres especifics del servei 'Indicadors'
		if (!this.getIndicadors().isEmpty()) {
			resultat.append(this.AMPERSAND);
			boolean first = true; 
			for (Integer _indicador : this.getIndicadors()) {
				if (!first) {
					resultat.append(",");
				}
				resultat.append(_indicador);
				first = false;
			}
		}
		
		// en cas de que sigui l'operacio dades mirem si hi ha els 
		// demes parametres.
		if (this.op == operacio.dades) {
			resultat.append(this.AMPERSAND);
			resultat.append(getMax());
			resultat.append(this.AMPERSAND);
			resultat.append(getMin());
			resultat.append(this.AMPERSAND);
			resultat.append(getTt());
		}
		
		return resultat.toString();
	}

	/**
	 * Retorna la llista d'indicadors a utilitzar.
	 * @return
	 */
	public List<Integer> getIndicadors() {
		return indicadors;
	}

	/**
	 * Inserta una llista d'indicarods a mostrar.
	 * @param indicadors
	 */
	public void setIndicadors(List<Integer> indicadors) {
		this.indicadors = indicadors;
	}
	
	/**
	 * Afegeix un indicador a la llista d'indicadors que s'utilitzara per parametre.
	 * @param indicador
	 */
	public void addIndicador(Integer indicador) {
		this.indicadors.add(indicador);
	}

	/**
	 * Retorna en numero maxim d'indicadors que ens retornara l'API.
	 * @return
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Posa en numero maxim d'indicadors que ens retornara l'API.
	 * @param max
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * Retorna el numero manim d'indicadors que ens retornara l'API.
	 * @return
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Posa el numero minim d'indicadors que ens retornara l'API.
	 * @param min
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * Retorna el parametre que especifica el temps transcorregut des de la publicacio
	 * de les dades.
	 * @return
	 */
	public int getTt() {
		return tt;
	}

	/**
	 * Especifica el parametre qe indica el temps transcorregut des de la publicacio de 
	 * les dades.
	 * @param tt
	 */
	public void setTt(int tt) {
		this.tt = tt;
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
	
}
