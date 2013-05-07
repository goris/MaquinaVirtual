package main.pkg;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


import lib.funcs.Funcion;

public class MV {

	static int cuad_actual; //Cuadruplo a ejecutarse
	
	/**
	 * Espacios de memoria para los diferentes tipos de variables
	 */
	static HashMap<Integer, Double> num_mem_global = 
			new HashMap<Integer, Double>();		//  1000  ...  3000
	static HashMap<Integer, Double> num_mem_local = 
			new HashMap<Integer, Double>();		//  3001  ...  7000
	static HashMap<Integer, Double> num_mem_constante = 
			new HashMap<Integer, Double>();		//  7001  ...  9000
	static HashMap<Integer, Double> num_mem_tmp = 
			new HashMap<Integer, Double>();
	static HashMap<String, HashMap<Integer, Double>> num_mem;

	static HashMap<Integer, Integer> bool_mem_global = 
			new HashMap<Integer, Integer>();	//  9001  ... 10001
	static HashMap<Integer, Integer> bool_mem_local = 
			new HashMap<Integer, Integer>();	// 10001  ... 11000
	static HashMap<Integer, Integer> bool_mem_constante = 
			new HashMap<Integer, Integer>();	// 11001  ... 12000
	static HashMap<Integer, Integer> bool_mem_temp =
			new HashMap<Integer, Integer>();
	static HashMap<String, HashMap<Integer, Integer>> bool_mem;
	
	static HashMap<String, Funcion> map_funciones = 
			new HashMap<String, Funcion>();
	
	static Stack<Funcion> stack_funciones = new Stack<Funcion> ();
	
	static String[] arr_cuadruplos;
	static String[] arr_funciones;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dirCuads;
		String[] lineas = null;
		init();
		dirCuads = "cuacks.cuads";
		try  
		{
			lineas = read(dirCuads);	    
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		if(lineas == null || lineas.length == 0) {
			log("No hay Cuadruplos");
			System.exit(-1);
		}
		else {
			consumeCuadruplos(lineas);
		}
	}

	/**
	 * Se iran resolviendo los diferentes cuadruplos ya generados
	 */
	private static void consumeCuadruplos(String[] cuads) {
		// TODO Auto-generated method stub
		String[] cuadruplos = cuads;
		String[] aux;
		arr_cuadruplos = new String[cuadruplos.length];
		for(int i = 0; i < cuadruplos.length; i++){
			aux = cuadruplos[i].split(":");
			arr_cuadruplos[i] = aux[1];
		}

		generaAccion(arr_cuadruplos[0]);
	}

	/**
	 * Se separa el cuadruplo en un arreglo para manejarlo
	 * 
	 * @param cuad
	 * @return str
	 */
	private static String[] divideString(String cuad) {
		String[] str;
		cuad = cuad.replace(" ", "");
		str = cuad.split(",");	
		return str;
	}

	/**
	 * Realiza la accion de cuadruplo encontrado
	 * @param cuad_i
	 * @param cuad_s
	 */
	private static void generaAccion(String cuadruplo) {
		// TODO Auto-generated method stub
		//log("cuad: " + cuad_actual);
		log("cuad:" + cuadruplo);
		String [] cuad_s = divideString(cuadruplo);
		int[] cuad_i;
		int ope;
		int op1 = -1;
		int op2 = -1;
		int avail_i = -1;
		double oper1;
		double oper2;
		double resultado;
		String op1_s = "";
		String avail_s = "";

		//log("cuad[0]: " + cuad_s[0] + " " + cuad_s.length);
		if(cuad_s[0].contains("16") || cuad_s[0].contains("17") || cuad_s[0].contains("19")) {
			ope = Integer.parseInt(cuad_s[0].trim());
			op1_s = cuad_s[1];
			if(cuad_s.length >= 3) avail_s = cuad_s[3];
		} else {
			cuad_i = convierteCuadruplo(cuad_s);
			ope = cuad_i[0];
			op1 = cuad_i[1];
			op2 = cuad_i[2];
			avail_i = cuad_i[3];
		}	

		//log(ope + ", " + op1_s + ", " + op2_s + ", " + avail_s );

		cuad_actual++;

		switch(ope) {

		case 0:
			//Suma
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			resultado = oper1 + oper2;
			asignaValor(avail_i, resultado);
			double aux = consigueValorMemoria(avail_i);
			log(oper1 + " + " + oper2 + " val: " + aux);
			break;
		case 1:
			//Resta
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			resultado = oper1 - oper2;
			asignaValor(avail_i, resultado);
			break;
		case 2:
			//Multiplicacion
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			resultado = oper1 * oper2;
			asignaValor(avail_i, resultado);
			break;
		case 3:
			//Division
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			resultado = oper1 / oper2;
			asignaValor(avail_i, resultado);
			break;
		case 4:
			//Asignacion
			oper1 = consigueValorMemoria(op1);
			asignaValor(avail_i, oper1);
			double auxEq = consigueValorMemoria(avail_i);
			log(avail_i + ": " + auxEq);
			break;
		case 5:
			//Menor que
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			if (oper1 < oper2) resultado = 1;
			else resultado = 0;
			asignaValor(avail_i, resultado);
			break;
		case 6:
			//Mayor que
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			if (oper1 > oper2) resultado = 1;
			else resultado = 0;
			asignaValor(avail_i, resultado);
			break;
		case 7:
			//Menor que o igual
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			if (oper1 <= oper2) resultado = 1;
			else resultado = 0;
			asignaValor(avail_i, resultado);
			break;
		case 8:
			//Mayor que o igual
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			if (oper1 >= oper2) resultado = 1;
			else resultado = 0;
			asignaValor(avail_i, resultado);
			break;
		case 9:
			//Diferente
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			if (oper1 != oper2) resultado = 1;
			else resultado = 0;
			asignaValor(avail_i, resultado);
			break;
		case 10:
			//Comparacion
			oper1 = consigueValorMemoria(op1);
			oper2 = consigueValorMemoria(op2);
			if (oper1 == oper2) resultado = 1;
			else resultado = 0;
			asignaValor(avail_i, resultado);
			break;
		case 13:
			//GoTo
			//log("Goto " + avail_i);
			cuad_actual = avail_i - 1;
			break;
		case 14:
			//GoToV
			break;
		case 15:
			//GoToF
			//log ("if: " + op1 + " : " + avail_i);
			oper1 = consigueValorMemoria(op1);
			if(oper1 == 0) cuad_actual = avail_i - 1;
			break;
		case 16:
			//Era
			preparaFuncion(op1_s);
			break;
		case 17:
			//GoSub
			copiarMemoriaAFuncion(op1_s);
			stack_funciones.peek().cuad_llamada = cuad_actual;
			cuad_actual = Integer.parseInt(avail_s) - 1;
			break;
		case 18:
			//Ret
			cuad_actual = (stack_funciones.peek()).cuad_llamada;
			stack_funciones.pop();
			break;
		case 19:
			//Param
			//log(op1_s + " : " + avail_s);
			op1 = Integer.parseInt(op1_s);
			parametroNuevo(op1, avail_s);
			
			break;
		case 20:
			break;
		case 21:
			break;
		case 22:
			break;
		case 23:
			break;	
		case 99:
			log("Fin de la ejecucion, champ!");
			System.exit(0);
			break;
		}	
		generaAccion(arr_cuadruplos[cuad_actual]);
	}

	/**
	 * Copia los valores de la memoria local y temporal a la de
	 * la funcion
	 * @param op1_s
	 */
	private static void copiarMemoriaAFuncion(String op1_s) {
		// TODO Auto-generated method stub
		double valor;
		//log("Copiar memoria a funcion < " + op1_s + " >");
		if(stack_funciones.size() < 2) {}
		Funcion func = map_funciones.get(op1_s);
		Iterator<Integer> iter = num_mem_local.keySet().iterator();
		while(iter.hasNext()){
			Integer key = (Integer) iter.next();
			valor = num_mem_local.get(key);
			num_mem_tmp.put(key, valor);
			log("Local Copiar dir: " + key + "  valor  : " + valor);
		}
		iter = (func.getMemNum()).keySet().iterator();
		while(iter.hasNext()) {
			Integer key = (Integer)iter.next();
			valor = (func.getMemNum()).get(key);
			num_mem_tmp.put(key, valor);
			log("Tmp Copiar dir: " + key + "  valor  : " + valor);
		}
		func.setMemNum(num_mem_tmp);
	}

	/**
	 * Agrega un nuevo parametro a la funcion
	 * @param op1
	 * @param avail_s
	 */
	private static void parametroNuevo(int dir, String avail_s) {
		// TODO Auto-generated method stub
		Funcion func = stack_funciones.peek();
		if(dir <= 9000 || dir >= 1000){
			func.agregarValorDir(func.var_actual++, consigueValorMemoria(dir));
		}
	}

	/**
	 * Agrega la funcion correspondiente al stack de operacion
	 * @param op1_s
	 */
	private static void preparaFuncion(String op1_s) {
		// TODO Auto-generated method stub
		Funcion func = map_funciones.get(op1_s);
		stack_funciones.push(func);	
	}

	/**
	 * Genera una nueva funcion desde la tabla de procedimientos
	 * @param strr
	 * @return func
	 */
	private static Funcion nuevaFuncion(String strr) {
		// TODO Auto-generated method stub
		String[] arr_;
		arr_ = divideString(strr);
		int a = Integer.parseInt(arr_[1]);
		int b = Integer.parseInt(arr_[2]);
		int c = Integer.parseInt(arr_[3]);
		Funcion func = new Funcion(arr_[0], a, b, c);
		map_funciones.put(arr_[0], func);
		return func;
	}

	/**
	 * Va a la memoria a buscar un valor numŽrico
	 * @param op1
	 * @return valor
	 */
	private static double consigueValorMemoria(int direccion) {
		// TODO Auto-generated method stub
		int dir = direccion;
		double valor = -1;

		if(dir >= 1000 && dir <= 3000) {
			//num global
			valor = num_mem_global.get(dir);
		} else if ( dir >= 3001 && dir <= 7000) {
			//num local		
			if(stack_funciones.empty()) {
				valor = num_mem_local.get(dir);
			} else {
				valor = ((stack_funciones.peek()).getMemNum()).get(dir);
				log("valor: " + valor + "  dir: " + dir);
			}
		} else if ( dir >= 7001 && dir <= 9000) {
			//num constante
			valor = num_mem_constante.get(dir);
		} else if (dir >= 9001 && dir <= 10000) {
			//bool global
			valor = bool_mem_global.get(dir);
		} else if (dir >= 10001 && dir <= 11000) {
			//bool local
			valor = bool_mem_global.get(dir);
		} else if (dir >= 11001 && dir <= 12000) {
			//bool constante
			//			valor_s = bool_mem_global.get(dir);
			valor = bool_mem_global.get(dir);
		}

		return valor;
	}

	/**
	 * Va a la memoria a buscar un valor String
	 * @param op1
	 * @return valor
	 */
	private static String consigueValorMemoriaS(int direccion) {
		// TODO Auto-generated method stub
		int dir = direccion;
		String valor = "" + dir;

		return valor;
	}

	/**
	 * Convierte cuadruplo de String a un arreglo de enteros
	 * @param cuadruplo
	 * @return
	 */
	private static int[] convierteCuadruplo(String[] cuadruplo) {
		// TODO Auto-generated method stub
		int[] aux = new int [4]; 
		for (int i = 0; i < cuadruplo.length; i++) {
			cuadruplo[i] = cuadruplo[i].replaceAll(" ", "");
			//log("Cuad: " + cuadruplo[i]);
			if(cuadruplo[i].equalsIgnoreCase("") ||
					cuadruplo[i].equalsIgnoreCase(" ")) {
				aux[i] = -1;
			} else if (cuadruplo[i].contains("param"))  {
				aux[i] = 999999;
			} else {
				try {
					aux[i] = Integer.parseInt(cuadruplo[i].trim());
				} catch(NumberFormatException e) {
					aux[i] = 7777777;
				}
			}
		}
		return aux;
	}

	/**
	 * Inicializa la memoria de la MV
	 */
	private static  void init() {
		// TODO Auto-generated method stub
		//	num_mem_global.put(10000, 0);
		//	num_mem_local.add((double) 3001);
		//		num_mem_constante.add((double) 7001);	
		//		bool_mem_global.add(9001);
		//		bool_mem_local.add(10001);
		//		bool_mem_constante.add(11001);;
		//		
		cuad_actual = 0;

		num_mem = new HashMap<String, HashMap<Integer, Double>>();
		num_mem.put("mem_local", num_mem_global);
		num_mem.put("mem_global", num_mem_local);
		num_mem.put("mem_constante", num_mem_constante);
		bool_mem = new HashMap<String, HashMap<Integer, Integer>>();
		bool_mem.put("mem_local", bool_mem_global);
		bool_mem.put("mem_global", bool_mem_local);
		bool_mem.put("mem_constante", bool_mem_constante);

	}

	/** 
	 * Lee el archivo objeto y lo va transformando a sus operaciones
	 * @param str
	 * @throws IOException
	 */
	static String[] read(String str) throws IOException{
		List<String> llines = new ArrayList<String>();
		String[] lines;
		String strr = "";
		boolean constantes = false;
		boolean funcs = true;
		Scanner scanner = new Scanner(new FileInputStream(str));
		try {
			while (scanner.hasNextLine())
			{
				strr = scanner.nextLine();
				if (!constantes && funcs) {
					if(!strr.contains("&&&")) {
//						log("3 " + strr);
						nuevaFuncion(strr);
					} else {
						strr = scanner.nextLine();
						constantes = true;
						funcs = false;
					}
				}
				if (constantes && !funcs){
					int dir;
					double valor;
//					log("1" + strr);
					if(!strr.contains("&&&")) {
						dir = obtenDir(strr);
						valor = obtenValor(strr);
						asignaValor(dir, valor);
					} else  {
						constantes = false;
					}
				}
				if ( !constantes && !funcs){
//					log("2 "+strr);
					if(!strr.contains("&&&")) {
						llines.add(strr);
					}
					else {
						constantes = false;
					}
				}
			}
			lines = llines.toArray(new String[0]);			

		}finally{
			scanner.close();
		}

		/*for(String line : lines) {
			log(line);
		}*/

		return lines;
	}

	/**
	 * Obtiene el valor de la tabla de Ctes
	 * @param strr
	 * @return valor
	 */
	private static double obtenValor(String strr) {
		// TODO Auto-generated method stub
		String[] aux;
		double valor;
		aux = strr.split(",");
		valor = Double.parseDouble(aux[1]);
		return valor;
	}

	/**
	 * Obtiene la dir de la tabla de Ctes
	 * @param strr
	 * @return dir
	 */
	private static int obtenDir(String strr) {
		// TODO Auto-generated method stub
		String[] aux;
		int dir;
		aux = strr.split(",");
		dir = Integer.parseInt(aux[0]);
		return dir;
	}

	/**
	 * A una direccion le da un valor
	 * @param dir
	 * @param valor
	 */
	private static void asignaValor(int dir, double valor) {
		// TODO Auto-generated method stub
		//log("d: " + dir + " v: " + valor);
		if(dir >= 1000 && dir <= 3000) {
			//num global
			num_mem_global.put(dir, valor);
		} else if ( dir >= 3001 && dir <= 7000) {
			//num local
			if(stack_funciones.empty()) {
				num_mem_local.put(dir, valor);
			} else {
				(stack_funciones.peek().getMemNum()).put(dir, valor);
			}
		} else if ( dir >= 7001 && dir <= 9000) {
			//num constante
			num_mem_constante.put(dir, valor);
		} else if (dir >= 9001 && dir <= 10000) {
			//bool global
			bool_mem_global.put(dir, (int)valor);
		} else if (dir >= 10001 && dir <= 11000) {
			//bool local
			bool_mem_global.put(dir, (int)valor);
		} else if (dir >= 11001 && dir <= 12000) {
			//bool constante
			bool_mem_global.put(dir, (int)valor);
		}
	}

	/**
	 * Escribe un mensaje a consola
	 * @param aMessage
	 */
	private static void log(String aMessage){
		System.out.println(aMessage);
	}

}
