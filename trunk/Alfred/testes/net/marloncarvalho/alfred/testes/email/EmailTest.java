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
package net.marloncarvalho.alfred.testes.email;

import net.marloncarvalho.alfred.email.Email;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para E-mails.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
public class EmailTest {

	/**
	 * Testar e-mails inválidos.
	 */
	@Test
	public void testarEmailInvalido() {
		if ( ! Email.isValido("marlon.carvalho@gmail.com") )
			Assert.fail();
		if ( Email.isValido("marlon.carvalhogmail.com") )
			Assert.fail();
		if ( Email.isValido("marlon@g@g.com") )
			Assert.fail();
		if ( Email.isValido("marlong@g....com") )
			Assert.fail();
		if ( Email.isValido("marlon@.com") )
			Assert.fail();
		if ( Email.isValido("marlon@ccom") )
			Assert.fail();
		if ( Email.isValido("asf@1.com") )
			Assert.fail();
	}

}