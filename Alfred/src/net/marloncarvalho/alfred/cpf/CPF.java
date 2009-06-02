package net.marloncarvalho.alfred.cpf;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.numeros.Numeros;

/**
 * Utilit�rios para CPF.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class CPF {

	/**
	 * Construtor privado para evitar instancia��o desta classe.
	 */
	private CPF() {}

	public static void main(String[] args) {
		System.out.println(CPF.formatar("793.256.455-72"));
	}

	/**
	 * Obter um CPF qualquer e format�-lo.
	 * Qualquer caracter diferente de n�meros ser� ignorado. Portanto, um CPF do tipo 1111c11b11122a ser� formatado para 111.111.111-11
	 * 
	 * @param cpf N�mero do CPF.
	 * @return CPF formatado.
	 */
	public static String formatar(String cpf) {
		String cpfSoNumeros = limpar(cpf);
		// Verificar tamanho do CPF.
		if ( cpfSoNumeros.length() != 11 )
			throw new AlfredException("CPF inv�lido. Tamanho de um CPF v�lido � 11. Este CPF possui " + cpfSoNumeros.length() + " n�meros.");
		StringBuilder sb = new StringBuilder();
		sb.append(cpfSoNumeros.substring(0, 3));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(3, 6));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(6, 9));
		sb.append("-");
		sb.append(cpfSoNumeros.substring(9, 11));
		return sb.toString();
	}

	/**
	 * Verificar se um CPF � v�lido.
	 * 
	 * @param cpf CPF a ser verificado.
	 * @return Verdadeiro caso seja v�lido. Falso, caso contr�rio.
	 */
	public static boolean isValido(String cpf) {
		return false;
	}

	/**
	 * Gerar um CPF aleat�rio.
	 * 
	 * @return CPF gerado.
	 */
	public static String gerar() {
		return "";
	}

	/**
	 * Limpar o CPF mantendo somente os n�meros.
	 * N�o verifica se � um CPF v�lido.
	 * 
	 * @param cpf CPF que deve ser limpado.
	 * @return CPF apenas com n�meros.
	 */
	public static String limpar(String cpf) {
		if ( cpf == null ) 
			throw new AlfredException("O CPF informado � nulo.");
		if ( "".equals(cpf) ) 
			throw new AlfredException("O CPF informado � vazio.");
		char[] chars = cpf.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int indice=0;indice<chars.length;indice++) {
			if ( Numeros.isInteger(String.valueOf(chars[indice]) ) ) {
				sb.append(chars[indice]);
			}
		}
		return sb.toString();
	}

}