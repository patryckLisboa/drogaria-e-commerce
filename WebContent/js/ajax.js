oReq = null;  // AJAX XMLHttpRequest

objNome = document.querySelector("#nome");
objEmail = document.getElementById("email");
objusuario = document.getElementById("usuario");
objSenha= document.getElementById("senha");
objLogin= document.getElementById("login");
objTelefone= document.getElementById("telefone");
objDantaNasci= document.getElementById("dataNasci");
objCep= document.getElementById("cep");
objForm= document.getElementById("form");

objForm.addEventListener("submit", function(event){ // faz com que quando clicamos nesse container submit, ele seja atualizado
	event.preventDefault();  //previnir ação default (atualização) do formulário do form

	let dados = { // pegar as informações digitadas nos campos
		nome: objNome.value,
		email: objEmail.value
		//usuario: objusuario.value,
		//senha: objSenha.value,
		//login: objLogin.value,
		//telefone: objTelefone.value,
		//dataNasci: objDantaNasci.value,
		//cep: objCep.value
	}

	fetch('http://localhost:9877',{
		method: 'POST', // post: informar que vou enviar informação
		body: JSON.stringify(dados) //o que eu quero processar (enviar nesse caso do post)
					//precisa converter esse objeto em string com JSON;stringfy(dados)
	})	//1° parametro: url de destino
		//2° parametro: opçoes de 
	.then(function(response) {
		//console.log(response); do objeto
		return response.json() // informação tratada
	})// é um "." do fetch //mapear o tempo de resposta
	.then(function(response){
		//console.log(response) //retorna id e data da criaçao
		alert("ok cadastrado com sucesso");
	})
//Response {type: "cors", url: "https://reqres.in/api/users", redirected: false, status: 201, ok: true, …}
//body: ReadableStream
//bodyUsed: false
//headers: Headers {}
//ok: true    //se deu certo ou não
//redirected: false
//status: 201     : status code 201
//statusText: ""
//type: "cors"
//url: "https://reqres.in/api/users"
//__proto__: Response
})




function ajax() {
	limpaStatusProcessamento();


	// para baixo é o que importa
	document.getElementById("idStatusProcessamento").innerHTML = "Aguarde"; // colocar aguarde na tela
   //tirar o trasinho de algum lugar
	var url = "localhost";  // contadenando a url para chamar


	if (window.XMLHttpRequest) {  // verifica se tem esse objeto nesse navegador  (obj XMLHttpRequest)
		oReq = new XMLHttpRequest(); // para a maioria dos navegadores
	}   //XMLHttpRequest tem que usar esse ojeto para fazer requisições dentro de um servidor
	else if (window.ActiveXObject) {
		     oReq = new ActiveXObject("Microsoft.XMLHTTP"); //ajax da microsoft
	     }

    if (!oReq) {    // ve se foi possivel criar o oReq
	    alert("Não foi possível realizar a consulta.");
	    document.formCep.txtCep.focus();
		return;
	}

		// callback - uma variável do tipo função, ai vc pega a função inteira dentro da propriedade onready
		//statechange do XMLHttpRequest();
		//callback() - chamada de função
 		 //quando se coloca função com "()", acaba que nao retorna nada e apenas executa a função
	oReq.onreadystatechange = callback;  // onready mudança de estado quando tiver pronto para callback (a funçao que eu escrevi)
	// a cada mudança de estado, ele chama essa função que eu escrevi

	oReq.open("GET", url, true); // "get" para buscar uma requisição
	//"post" é quando eu quero inserir alguma coisa no servidor, envia dados e grava la no banco de dados
	//false - sincrona - sincronizado com o servidor -  faz a rquisição e em quanto não completar, não faz mais nada no sistema
	// dependendo da regra de negocio, não pode deixar prosseguir para os próximos campos se ele não selecionar o cliente antes( exemplo de requisição que depende da outra)
	//true - assincrona - faço uma requisição e vou fazer outras coisas, ai quando der a resposta, eu pego
	// pode fazer várias requisições ao memso tempo, para vários servidores

	oReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');//sempre que fazemos requisições, tem que enviar no minimo esse cabeçalio
					 //('tipo de conteudo que estou enviando' ,'estou enviando campos de um formulario html [inputs], ou simplesmente strings');
					 //o segundo se for enviar xml troca x-www-form-urlencoded por xml
	
	oReq.send();//executar o processo 
}

function callback() { //função em java script é um tipo de dado
    if (oReq.readyState == 4) { //0 acho que é verificar prota aberta, 1 enviado, 2 processando, 3 chegando, 4 chegou
	    var objRetorno = {};    // 4 significa apenas que chegou e não que chegou com sucesso
        document.getElementById("idResp").value = trataResposta(objRetorno); //verificar se chegou com sucesso
        limpaStatusProcessamento();   //apenas limpa

		if (objRetorno.retorno) {
            document.getElementById("btnBuscaCep").firstChild.nodeValue = "Nova consulta";
		}
		else {
	    	document.formCep.txtCep.value = "";
			document.formCep.txtCep.focus();
		}
	}
}

function trataResposta(objRetorno) {
	var resposta;
    objRetorno.retorno = false;

    switch (oReq.status) { //status de toda a requisição: 200 chegou com sucesso
		case 200: var objEndereco = JSON.parse(oReq.responseText);//variavel json (sem precisar de import)
																//vem em formato texto e "parse" converte em obj
		          if (!objEndereco.logradouro) {  // undefined	//caso não tenha o logradouro
				     resposta = "CEP não encontrado";
				  }
				  else {
	                 resposta = objEndereco.logradouro + "\n" +   //resposta - vou montar o objeto
	                            objEndereco.bairro + "\n" + 
	                            objEndereco.localidade + "-" + objEndereco.uf + "\nIBGE " + 
	                            objEndereco.ibge;
					 objRetorno.retorno = true; 
				  }
				  break;	
		case 400: resposta = "CEP inválido"; break; //400: cep invalio... 404: sem resposta
		default: resposta = "Erro: " + oReq.status + "\nDescrição: " + oReq.statusText;
	}
	return resposta;
}
