import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Receituario4CertasUltimaIngredienteErrado {

	@Test
	void receitaFormatoErrado() {
		int erro = Receituario.receituario("4ReceitasCertasUltimaErrada.txt");
		assertEquals(erro, 1);
	}


}
