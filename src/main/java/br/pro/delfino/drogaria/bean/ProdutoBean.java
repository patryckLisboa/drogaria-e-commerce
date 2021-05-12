package br.pro.delfino.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.pro.delfino.drogaria.dao.FabricanteDAO;
import br.pro.delfino.drogaria.dao.ProdutoDAO;
import br.pro.delfino.drogaria.domain.Fabricante;
import br.pro.delfino.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable{
	
	private List<Produto> produtos;
	private Produto produto;
	private List<Fabricante> fabricantes;
	
	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao listar os objetos");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.merge(produto); // vai trocar o id do produto
			
			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("C:/Users/-/uploads/" + produtoRetorno.getCodigo() + ".png");			
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			produto = new Produto();

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Erro ao salvar objeto");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			
			Path origem = Paths.get("C:/Users/-/uploads/" + produto.getCodigo() + ".png");
			
			Files.deleteIfExists(origem);
			produtos = produtoDAO.listar();
			
			Messages.addGlobalInfo("Objeto excluído com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o objeto");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}
		
	}
	
	public void novo() {
		produto = new Produto();
		
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			setFabricantes(fabricanteDAO.listar());
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao gerar novo fabricante");
			erro.printStackTrace();
		}

	}
	
	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile(); // primefaces
			Path arquivoTemp = Files.createTempFile(null, null);
				// 1° nome do arquivo temporadrio (null é aleatorio) 
				// 2° via por .tmp (para arquivos temporarios);
				// path Garda a referencia do arquivo (caminho);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			// 1° origem, um imputStream(numero de bits) do arquivo temporario
			// 2° destino, tem que ser um path
			// 3° quando copiar ele vai replace se ja existir algo (subistiuir) 
			
			produto.setCaminho(arquivoTemp.toString());  // pegou o caminho
			
			Messages.addGlobalInfo("Upload realizado com sucesso");
		} catch (IOException erro) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload de arquivo");
			erro.printStackTrace();
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

}
