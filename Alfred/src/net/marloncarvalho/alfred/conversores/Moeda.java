/*
 *  This file is part of Alfred Library.
 *
 *  Foobar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Alfred Library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.marloncarvalho.alfred.conversores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.net.WorldWideWeb;
import net.marloncarvalho.alfred.texto.Texto;

/**
 * Utilit�rio para convers�o entre moedas.
 * Requer conex�o com a internet para obten��o das cota��es do dia atrav�s do site do Banco Central do Brasil.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Moeda {

	private Moeda() {}

	/**
	 * Realizar a convers�o de um valor em Real para Dolar.
	 * 
	 * @param valorConversao Valor a ser convertido para dolar.
	 * @return Valor em dolar.
	 */
	public static String converterRealEmDolar(String valorConversao) {
		return Moeda.converter(valorConversao, "790", "220");
	}

	/**
	 * Realizar a convers�o de um valor em Dolar para Real.
	 * 
	 * @param valorConversao Valor a ser convertido para real.
	 * @return Valor em real.
	 */
	public static String converterDolarEmReal(String valorConversao) {
		return Moeda.converter(valorConversao, "220", "790");
	}

	/**
	 * Realizar a convers�o de uma moeda para outra.
	 * Requer conex�o com a internet.
	 * Para verificar quais os c�digos das moedas, acessar o site:
	 * http://www4.bcb.gov.br/pec/taxas/batch/tabmoedas.asp?id=tabmoeda&id=tabmoeda
	 * 
	 * @param valorConversao Valor a ser convertido.
	 * @param moedaOrigem em que moeda o valor informado est�. Trata-se de um c�digo fornecido pelo Banco Central.
	 * @param moedaDestino Para qual moeda o valor informado ser� convertido. Trata-se de um c�digo fornecido pelo Banco Central.
	 * @return Valor ap�s convers�o.
	 */
	public static String converter(String valorConversao, String moedaOrigem, String moedaDestino) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("?MoedaOrigem="); 
		queryString.append(moedaOrigem);
		queryString.append("&MoedaDestino=");
		queryString.append(moedaDestino);
		queryString.append("&DataCotacaoEnvio=");
		queryString.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		queryString.append("&ValorEnvio=");
		String valorSoNumeros = Texto.manterNumeros(valorConversao);
		String valorFinal = Texto.incluirCaracterInicio(valorSoNumeros, '0', 17-valorSoNumeros.length());
		queryString.append(valorFinal);

		// Realizar a requisi��o.
		String conteudo = WorldWideWeb.getConteudoSite("http://www4.bcb.gov.br/pec/conversao/Resultado.asp" + queryString.toString());

		//<strong>Resultado da convers&atilde;o:</strong> 19,36</td>
		// Usar express�o regular para achar o pre�o.
		Pattern padrao = Pattern.compile("<strong>Resultado da convers&atilde;o:</strong> \\d+,\\d{2}");  
		Matcher pesquisa = padrao.matcher(conteudo);

		// Deve encontrar apenas um.
		String valorConvertido = "";
		while(pesquisa.find()) {
			valorConvertido = pesquisa.group();
		}
		
		// Verificar se foi achado o valor.
		if ( "".equals(valorConvertido) )
			throw new AlfredException("N�o foi poss�vel obter o valor da convers�o solicitada. Verifique os par�metros informados ou se o site do Banco Central encontra-se indispon�vel.");

		return valorConvertido.replace("<strong>Resultado da convers&atilde;o:</strong> ","");
	}

}
