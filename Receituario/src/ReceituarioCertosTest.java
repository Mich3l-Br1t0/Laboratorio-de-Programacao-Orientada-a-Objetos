import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReceituarioCertosTest {	
	@Test
	void receita() {
		int erro = Receituario.receituario("5ReceitasCertas.txt");
		assertEquals(erro, 0);
	}
}

