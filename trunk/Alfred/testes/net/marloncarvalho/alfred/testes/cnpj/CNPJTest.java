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
package net.marloncarvalho.alfred.testes.cnpj;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.cnpj.CNPJ;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para CNPJ.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
public class CNPJTest {

	/**
	 * Testar um CNPJ que tenha menos de 15 n�meros.
	 * Deve lan�ar uma exce��o.
	 */
	@Test
	public void testarFormatarCNPJMenos15Numeros() {
		try {
			System.out.println(CNPJ.formatar("008.6061.342/0001-61"));
			Assert.fail();
		} catch(AlfredException ex) {
		}
	}

	/**
	 * Testar um CNPJ correto. 
	 * N�o deve lan�ar exce��o.
	 */
	@Test
	public void testarFormatarCNPJCorreto() {
		try {
			CNPJ.formatar("008.606.342/0001-61");
		} catch(AlfredException ex) {
			Assert.fail();
		}
	}

}