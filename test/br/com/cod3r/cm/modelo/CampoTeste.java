package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo; 
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoRealDistanciaEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistanciaDireita() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoRealDistanciaEmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistanciaEmBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistanciaDiagonal() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.minar();
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
		
	}
	
	@Test
	void testeAbrirComVizinho1() {
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		
		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);

		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeAbrirComVizinho2() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 2);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
	@Test
	void testeGetLinha() {
		assertEquals(3, campo.getLinha());	
	}
	
	@Test
	void testeGetColuna() {
		assertEquals(3, campo.getColuna());	
	}
	
	@Test
	void testeObjetivoAlcancado1() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testeObjetivoAlcancado2() {
		campo.alternarMarcacao();
		campo.minar();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testeMinasNaVizinhanca() {
		Campo vizinho22 = new Campo(2, 2);
		vizinho22.minar();
		Campo vizinho23 = new Campo(2, 3);
		Campo vizinho24 = new Campo(2, 4);
		campo.adicionarVizinho(vizinho22);
		campo.adicionarVizinho(vizinho23);
		campo.adicionarVizinho(vizinho24);
		assertEquals(1L, campo.minasNaVizinhanca());
	}
	
	@Test
	void testeReiniciar() {
		campo.abrir();
		campo.minar();
		campo.alternarMarcacao();
		
		campo.reiniciar();
		
		assertFalse(campo.isMarcado());
		assertFalse(campo.isAberto());	
	}
	
	@Test
	void testeToString1() {
		campo.alternarMarcacao();
		assertEquals("x", campo.toString());
	}
	
	@Test
	void testeToString2() {
		campo.minar();
		assertThrows(ExplosaoException.class, () ->{
			campo.abrir();
		});
		assertEquals("*", campo.toString());
	}
	
	@Test
	void testeToString3() {
		Campo vizinho22 = new Campo(2, 2);
		vizinho22.minar();
		Campo vizinho23 = new Campo(2, 3);
		Campo vizinho24 = new Campo(2, 4);
		campo.adicionarVizinho(vizinho22);
		campo.adicionarVizinho(vizinho23);
		campo.adicionarVizinho(vizinho24);
		
		campo.abrir();
		assertEquals("1", campo.toString());
	}
	
	@Test
	void testeToString4() {
		campo.abrir();
		assertEquals(" ", campo.toString());
	}
	
	@Test
	void testeToString5() {
		assertEquals("?", campo.toString());
	}
}
