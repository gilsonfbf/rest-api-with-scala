package services

import com.google.inject.Inject
import dao.UsuarioDAO
import domains.Usuario

import scala.concurrent.Future

class UsuarioService @Inject()(usuarios: UsuarioDAO) {

  def adicionarUsuario(usuario: Usuario): Future[String] = {
    usuarios.adicionar(usuario)
  }

  def removerUsuario(id: Long): Future[Int] = {
    usuarios.remover(id)
  }

  def atualizarUsuario(usuario: Usuario): Future[Int] = {
    usuarios.atualizar(usuario)
  }

  def buscarUsuario(id: Long): Future[Option[Usuario]] = {
    usuarios.buscar(id)
  }

  def listarUsuario: Future[Seq[Usuario]] = {
    usuarios.listar
  }
}