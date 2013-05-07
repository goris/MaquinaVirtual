package lib.funcs;

import java.util.HashMap;

public class Funcion {
	public String name;
	public int tipo;
	public int cant_vars;
	public int cuad_ini;
	public int cuad_llamada;
	public int var_actual;
	private HashMap<Integer, Double> num_mem = 
			new HashMap<Integer, Double>();
	private HashMap<Integer, Integer> bool_mem = 
			new HashMap<Integer, Integer>();
	private HashMap<Integer, String> texto_mem = 
			new HashMap<Integer, String>();
	
	public Funcion(String str, int tipo, int vars, int ini){
		this.name = str;
		this.tipo = tipo;
		this.cant_vars = vars;
		this.cuad_ini = ini;
		var_actual = 3001;
		
	}
	
	public HashMap<Integer, Double> getMemNum(){
		return this.num_mem;
	}
	public void setMemNum(HashMap<Integer, Double> num_mem){
		this.num_mem = num_mem;
	}
	
	public HashMap<Integer, Integer> getMemBool(){
		return this.bool_mem;
	}
	public void setMemBool(HashMap<Integer, Integer> bool_mem){
		this.bool_mem = bool_mem;
	}
	
	public HashMap<Integer, String> getMemTexto(){
		return this.texto_mem;
	}
	public void setMemTexto(HashMap<Integer, String> texto_mem){
		this.texto_mem = texto_mem;
	}
	
	/**
	 * A una dir le agregas un valor numerico
	 * @param dir
	 * @param valor
	 */
	public void agregarValorNumDir(int dir, double valor){
		if(dir <= 9000 || dir >= 1000){
			num_mem.put(dir, valor);
		} else if (dir > 9001 && dir < 12000) {
			bool_mem.put(dir, (int)valor);
		}
	}
	
	/**
	 * A una dir le agregas un valor string
	 * @param dir
	 * @param valor
	 */
	public void agregarValorDir(int dir, String valor){
		
	}
	
	/**
	 * Imprime las carac de la funcion
	 */
	public void print(){
		System.out.println(
				name + ", " + tipo + ", " + cant_vars + ", " + cuad_ini);
	}

	/**
	 * Devuelve un valor de la memoria numerica
	 * @param dir
	 * @return valor
	 */
	public double getValorNum(int dir) {
		// TODO Auto-generated method stub
		if(dir < 9000) {
			return num_mem.get(dir);
		} 
		return 0;
	}
	
	/**
	 * Devuelve un valor de la memoria booleana
	 * @param dir
	 * @return valor
	 */
	public int	 getValorBool(int dir) {
		// TODO Auto-generated method stub
		if(dir > 9000 && dir < 1200) {
			return bool_mem.get(dir);
		} 
		return 0;
	}
}
