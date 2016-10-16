<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
var pontosGastos = 0;
var pontosTotais = 0;
var nMovimentos = 0;
$(document).ready(function(){

	$.ajax({
		type:'GET',
		url:'../PontosUsuarioServlet',
		data:null
	}).done(function(e){
		pontosTotais = +e;
		$("#pontosGerais").text(pontosGastos+'/'+pontosTotais)
		atualizarPontosGastos();
		atualizaPontuacaoNaSessao();
	});
	

	function atualizaPontuacaoNaSessao(){
		var params = {movimentos: ""+nMovimentos, pGastos: ""+pontosGastos};
		$.post("../PontosUsuarioServlet", $.param(params), function(response) {});
	}
	
	function atualizarPontosGastos(){
		var pontos = 0;
		$("input.atributoPers").each(function(i){
			pontos += +$(this).val();
		});
		//calcular valor dos movimentos também
		for(var i = 1; i<=nMovimentos; i++){
			if($('#poder'+ i).val()>150)$('#poder'+ i).val('150');
			else if($('#poder'+ i).val()<10)$('#poder'+ i).val('10');
			var pontosAtaque = $('#poder'+ i).val();
			pontos += pontosAtaque/2.5;
			
			if($('#precisao'+ i).val()>100)$('#precisao'+ i).val('100');
			else if($('#precisao'+ i).val()<10)$('#precisao'+ i).val('10');
			var pontosPrecisao = $('#precisao'+ i).val() ;
			pontos +=pontosPrecisao/5;
		}
		pontosGastos = pontos;
		$('#pontosGerais').text(pontosGastos+'/'+pontosTotais);
		if(pontosGastos>pontosTotais){
			$('#pontosGerais').css('color','red');
			$('#enviarPers').prop('disabled',true);
		}else{
			$('#pontosGerais').css('color','black');
			$('#enviarPers').prop('disabled',false);
		}
		atualizaPontuacaoNaSessao();
	}
	
	$(".atributoPers").change(function(){
		var atributoValor = $(this).val();
		if(atributoValor>15){ $(this).val('15');}
		atualizarPontosGastos();
	});
	
	$('#novoMovimento').click(function(){
		nMovimentos = nMovimentos + 1;
		$(this).parent()
		.append(nomeMovimentoInput() + classeGMovimentoInput() + classeMMovimentoInput() + poderMovimento() + precisaoMovimento());
		atualizarPontosGastos();
	});
	
	$('#removerUltimoMovimento').click(function(){
		$('.movimentoX'+nMovimentos).hide();
		$('.movimentoX'+nMovimentos).remove();
		nMovimentos = nMovimentos - 1;
		atualizarPontosGastos();
	});
	
	
	 $('body').on('change', '.atributoAccMove, .atributoAtkMove', function () {
			atualizarPontosGastos();
	 });
	 
	function precisaoMovimento(){
		return '<tr><th>Precisão do Movimento:</th><th><input type="number" class="atributoAccMove" required name="precisao'+ nMovimentos +'" id="precisao'+ nMovimentos +'" min="10" max="100" value="100" step="5">'+'</table></th></tr>';
	}
	
	function poderMovimento(){
		return '<tr><th>Poder do Movimento:</th><th><input type="number" class="atributoAtkMove"  required name="poder'+ nMovimentos +'" id="poder'+ nMovimentos +'" min="10" max="150" value="10" step="10"></th></tr>';
	}
	
	
	function  classeGMovimentoInput(){
		return '<tr><th><input type="radio"  name="classeMovimento'+nMovimentos+'" value="Fisico" checked>Físico</th>';
	}
	
	function classeMMovimentoInput(){
		return '<th><input type="radio"  name="classeMovimento'+nMovimentos+'" value="Magico">Mágico</th></th></tr>';
	}
	
	function nomeMovimentoInput(){
		return '<table class="movimentoX'+nMovimentos+'"><tr><th>Nome do Movimento:</th><th><input type="text"  required size="20" maxlength="22" name="nomeMovimento' + nMovimentos +'" id="nomeMovimento' + nMovimentos +'" placeholder="Nome do Movimento"/></th></tr>';
	}
	   
	
});
</script>

<title>CADASTRAR PERSONAGEM</title>
</head>
<body>
<form action="../SessionNavServlet" method="GET"><input type="submit" value="LOGOUT"/></form>
<form action="../SessionNavServlet" method="POST"><input type="hidden" name="target" id="target" value="View/homeView.jsp"/><input type="submit" value="HOME"/></form>

<% if(request.getSession().getAttribute("user")==null)response.sendRedirect("../index.jsp");%>

<H1 id="pontosGerais"></H1>
<form id="cadastrarPers" action="../PersonagensServlet" method="POST">
<input type="text" size="50" name="imgPers" id="imgPers" placeholder="URL da Imagem do Personagem" required><br/>
<input type="text" size="25" name="nomePers" id="nomePers" placeholder="Nome do Personagem" required><br/>
<input type="radio" name="classePers" id="mago" value="Mago">Mago | <input type="radio" name="classePers" id="guerreiro" value="Guerreiro" checked> Guerreiro<br/>
<table>
<tr><th>HP:</th><th><input type="number" class="atributoPers" name="hp" id="hp" min="1" max="15" value="1" required></th></tr>
<tr><th>Ataque Físico:</th><th><input type="number" class="atributoPers" name="atk" id="atk" min="1" max="15" value="1" required></th></tr>
<tr><th>Ataque Especial:</th><th><input type="number" class="atributoPers" name="spAtk" id="spAtk" min="1" max="15" value="1" required></th></tr>
<tr><th>Defesa Física:</th><th><input type="number" class="atributoPers" name="def" id="def" min="1" max="15" value="1" required></th></tr>
<tr><th>Defesa Especial:</th><th><input type="number" class="atributoPers" name="spDef" id="spDef" min="1" max="15" value="1" required></th></tr>
<tr><th>Velocidade:</th><th><input type="number" class="atributoPers" name="veloc" id="veloc" min="1" max="15" value="1" required></th></tr>
</table>
<div id="moveRegion">
<a href="#" class="movementChange" id="novoMovimento">Adicionar Novo Movimento</a> |
<a href="#" class="movementChange" id="removerUltimoMovimento">Remover Ultimo Movimento</a>
<!-- REGIAO ONDE ESTARAO OS INPUTS DE MOVIMENTO!! -->
</div>
<input type="submit" id="enviarPers" value="Cadastrar Personagem"/>
</form>
</body>
</html>